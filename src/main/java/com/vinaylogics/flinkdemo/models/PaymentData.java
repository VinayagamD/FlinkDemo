package com.vinaylogics.flinkdemo.models;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

import static com.vinaylogics.flinkdemo.utils.SerializerProvider.GSON;

@Getter
@Builder
public class PaymentData {
    private String application;
    private Long componentId;
    private String status;
    private Date eventTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentData)) return false;
        PaymentData that = (PaymentData) o;
        return Objects.equals(componentId, that.componentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentId);
    }

    public String toString() {
        return GSON.toJson(this);
    }
}
