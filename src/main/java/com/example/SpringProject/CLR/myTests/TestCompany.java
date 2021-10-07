package com.example.SpringProject.CLR.myTests;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Service.AdminImpl;
import com.example.SpringProject.Service.ClientType;
import com.example.SpringProject.Service.CompaniesImpl;
import com.example.SpringProject.Service.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//@Component
@RequiredArgsConstructor
@Order(3)
public class TestCompany implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            //Successful login
            CompaniesImpl companiesImpl = (CompaniesImpl) loginManager.login("amazon@gmail.com", "5mq77", ClientType.COMPANY);

            //Unsuccessful login - wrong password
            //CompaniesImpl companiesImpl = (CompaniesImpl) loginManager.login("amazon@gmail.com", "xxxx", ClientType.COMPANY);

            //Unsuccessful login - wrong email
            //CompaniesImpl companiesImpl = (CompaniesImpl) loginManager.login("xxxx@gmail.com", "5mq77", ClientType.COMPANY);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /*
        //Successful coupon add
        System.out.println();
        System.out.println("Before:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
        Coupon coupon1 = Coupon.builder().companyId(companiesImpl.getCompanyId()).categoryId(Category.ELECTRICITY).title("Added coupon").description("BOSCH washing machine, model WAN28160BY for only 1,000 NIS").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
        companiesImpl.addCoupon(coupon1);
        System.out.println();
        System.out.println("After:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
*/

/*
        //Unsuccessful coupon add - a company can't have two coupons with the same title.
        System.out.println();
        System.out.println("Before:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
        Coupon coupon1 = Coupon.builder().companyId(companiesImpl.getCompanyId()).categoryId(Category.ELECTRICITY).title("Home store").description("BOSCH washing machine, model WAN28160BY for only 1,000 NIS").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
        companiesImpl.addCoupon(coupon1);
        System.out.println();
        System.out.println("After:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
*/

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
        //Successful coupon update
        System.out.println();
        System.out.println("Before:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
        Coupon coupon1 = Coupon.builder().couponId(10).companyId(companiesImpl.getCompanyId()).categoryId(Category.ELECTRICITY).title("xxxxxxxxxxxxxxxxxxxx").description("xxxxxxxxxxxxxxxxxxxx").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
        companiesImpl.updateCoupon(coupon1);
        System.out.println();
        System.out.println("After:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
*/

        /*
        //Unsuccessful coupon update - can't change the id of the company
        System.out.println();
        System.out.println("Before:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
        Coupon coupon1 = Coupon.builder().couponId(10).companyId(4).categoryId(Category.ELECTRICITY).title("xxxxxxxxxxxxxxxxxxxx").description("xxxxxxxxxxxxxxxxxxxx").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
        companiesImpl.updateCoupon(coupon1);
        System.out.println();
        System.out.println("After:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
*/
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
        //Successful coupon delete
        System.out.println();
        System.out.println("Before:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
        companiesImpl.deleteCoupon(10);
        System.out.println();
        System.out.println("After:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
*/

/*
        //Unsuccessful coupon delete - can't delete a coupon that does not exist in the DB.
        System.out.println();
        System.out.println("Before:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
        companiesImpl.deleteCoupon(24);
        System.out.println();
        System.out.println("After:");
        companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);
*/

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive of coupons of an existing company
            //companiesImpl.getCompanyCoupons(companiesImpl.getCompanyId()).forEach(System.out::println);

            //Unsuccessful attempt - company does not exist
            //companiesImpl.getCompanyCoupons(7).forEach(System.out::println);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive of coupons of an existing company, equal or under a certain price
            //companiesImpl.getCompanyCouponsByMaxPrice(companiesImpl.getCompanyId(),80).forEach(System.out::println);

            //Unsuccessful attempt - company does not exist
            //companiesImpl.getCompanyCouponsByMaxPrice(7,80).forEach(System.out::println);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive of coupons of a company by category
            //System.out.println(companiesImpl.getCompanyCouponsByCategory(companiesImpl.getCompanyId(), Category.FOOD));

            //Unsuccessful attempt - company does not exist
            //System.out.println(companiesImpl.getCompanyCouponsByCategory(7, Category.FOOD));

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive of company details
            //System.out.println(companiesImpl.getCompanyDetails(companiesImpl.getCompanyId()));

            //Unsuccessful attempt - company does not exist
            //System.out.println(companiesImpl.getCompanyDetails(7));

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
