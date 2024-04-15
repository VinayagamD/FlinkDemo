package com.vinaylogics.flinkdemo.helper;

import com.vinaylogics.flinkdemo.models.Application;
import com.vinaylogics.flinkdemo.models.ApplicationNames;
import com.vinaylogics.flinkdemo.models.LocationZone;
import com.vinaylogics.flinkdemo.models.Status;
import com.vinaylogics.flinkdemo.utils.RandomNumberGenerator;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.flink.shaded.guava31.com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataGeneratorHelper {
    private final static List<Status> TERMINAL_STATUS_LIST = Lists.newArrayList(Status.COMPLETED, Status.FAILED);
    private final static List<Status> START_STATUS_LIST = Lists.newArrayList(Status.STARTED, Status.ACKNOWLEDGED, Status.DIRTY);
    private final static List<Status> COMPLETION_STATUS_LIST  = Lists.newArrayList(Status.STARTED, Status.COMPLETED, Status.FAILED);

    private final static List<Integer> COMP_ID_LIST = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

    private final static Random RANDOM = new Random();

    public static ApplicationNames getRandomAppName(){
        return ApplicationNames.values()[RANDOM.nextInt(TERMINAL_STATUS_LIST.size())];
    }

    public static Status getRandomStartStatus(){
        return START_STATUS_LIST.get(RANDOM.nextInt(START_STATUS_LIST.size()));
    }

    public static Status getRandomCompletionStatus(){
        return COMPLETION_STATUS_LIST.get(RANDOM.nextInt(COMPLETION_STATUS_LIST.size()));
    }

    public static Integer getRandomCompId(){
        return COMP_ID_LIST.get(RANDOM.nextInt(COMP_ID_LIST.size()));
    }

    public static Date generateTime() {
        int offsetInMinis = RandomNumberGenerator.getRandomNumber(0,5);
        return DateUtils.addMinutes(new Date(), offsetInMinis);
    }

    public static Application getRandomApplication() {
        return Application.values()[RANDOM.nextInt(Application.values().length)];
    }

    public static LocationZone getRandomLocation() {
        return LocationZone.values()[RANDOM.nextInt(LocationZone.values().length)];
    }

}
