package com.example.topupapi.model;

import lombok.Data;

@Data
public class KafkaSendReq {
    private String id;
    private String message;
}
