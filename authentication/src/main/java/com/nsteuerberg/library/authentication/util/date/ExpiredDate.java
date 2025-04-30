package com.nsteuerberg.library.authentication.util.date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class ExpiredDate {

    @Value("${jwt.expired-minutes}")
    private Integer minutes;

    @Bean
    public Date getExpiredDate() {
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now.plusMinutes(minutes).atZone(ZoneId.systemDefault()).toInstant());
    }
}
