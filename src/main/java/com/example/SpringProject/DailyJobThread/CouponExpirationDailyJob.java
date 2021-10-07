package com.example.SpringProject.DailyJobThread;

import com.example.SpringProject.Repositories.CompaniesRepo;
import com.example.SpringProject.Repositories.CouponsRepo;
import com.example.SpringProject.Service.CompaniesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Order(5)
public class CouponExpirationDailyJob {
    private final CompaniesImpl companiesImpl;

    @Scheduled(cron = "*/30 * * * * *")
    public void deleteExpiredCoupons() throws InterruptedException {
        Date date = new Date();
        companiesImpl.deleteCouponByDate(date);
    }
}
