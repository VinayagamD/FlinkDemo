package com.vinaylogics.flinkdemo.app;

import com.vinaylogics.flinkdemo.datagenerator.LocationDataGenerator;
import com.vinaylogics.flinkdemo.datagenerator.PaymentDataGenerator;
import com.vinaylogics.flinkdemo.models.LocationData;
import com.vinaylogics.flinkdemo.models.PaymentData;
import com.vinaylogics.flinkdemo.models.Status;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class PaymentByFlatMap {

    public static void main(String[] args) throws Exception {
        // Sets up the execution environment, which is the main entry point
        // to building Flink applications.
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);
        DataStream<LocationData> locations = env.addSource(new LocationDataGenerator());
        //payments.writeAsText("D:\\Flink\\Input.txt");
        DataStream<LocationData> locationsFiltered = locations.filter((FilterFunction<LocationData>) value ->
                value.getStatus().equals(Status.STARTED.name()));

        locationsFiltered.print();

        DataStream<Tuple2<String,Integer>> counts = locationsFiltered
                .map(new LocationMapper())
                .keyBy(0)
                .sum(1);
        counts.print();
        env.execute();
    }


}

class ApplicationMapper implements MapFunction<PaymentData, Tuple2<String,Integer>> {

    @Override
    public Tuple2<String, Integer> map(PaymentData paymentData) throws Exception {
        return new Tuple2<>(paymentData.getApplication(), 1);
    }
}

class LocationMapper implements MapFunction<LocationData, Tuple2<String,Integer>> {

    @Override
    public Tuple2<String, Integer> map(LocationData locationData) throws Exception {
        return new Tuple2<>(locationData.getLocationZone(), 1);
    }
}

