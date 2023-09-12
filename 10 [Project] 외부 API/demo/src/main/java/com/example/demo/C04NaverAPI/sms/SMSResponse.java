package com.example.demo.C04NaverAPI.sms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMSResponse {
    private String requestId;
    private String requestTime;
    private String statusCode;
    private String statusName;
}
