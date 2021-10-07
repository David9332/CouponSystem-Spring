package com.example.SpringProject.CLR.myTests;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Service.ClientType;
import com.example.SpringProject.Service.CompaniesImpl;
import com.example.SpringProject.Service.CustomersImpl;
import com.example.SpringProject.Service.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//@Component
@RequiredArgsConstructor
@Order(4)
public class TestCustomer implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            //Successful login
            //CustomersImpl customersImpl = (CustomersImpl) loginManager.login("avi@gmail.com", "cvvk", ClientType.CUSTOMER);

            //Unsuccessful login - wrong email
            //CustomersImpl customersImpl = (CustomersImpl) loginManager.login("xxx@gmail.com", "cvvk", ClientType.CUSTOMER);

            //Unsuccessful login - wrong password
            //CustomersImpl customersImpl = (CustomersImpl) loginManager.login("avi@gmail.com", "xxx", ClientType.CUSTOMER);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*
        //Successful coupon purchase (please notice the amount went down by 1).
        System.out.println();
        System.out.println("Before:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
        customersImpl.purchaseCoupon(customersImpl.getCustomerId(),7);
        System.out.println();
        System.out.println("After:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
*/
/*
        //Unsuccessful coupon purchase - coupon doe not exists
        System.out.println();
        System.out.println("Before:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
        customersImpl.purchaseCoupon(customersImpl.getCustomerId(),23);
        System.out.println();
        System.out.println("After:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
*/
         /*
        //Unsuccessful coupon purchase - coupon amount is 0.
        System.out.println();
        System.out.println("Before:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
        customersImpl.purchaseCoupon(customersImpl.getCustomerId(),14);
        System.out.println();
        System.out.println("After:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
        */

         /*
        //Unsuccessful coupon purchase - coupon's end-date had already passed.
        System.out.println();
        System.out.println("Before:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
        customersImpl.purchaseCoupon(customersImpl.getCustomerId(),15);
        System.out.println();
        System.out.println("After:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
        */

            /*
        //Unsuccessful coupon purchase - customer was already purchased buy this coupon.
        System.out.println();
        System.out.println("Before:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
        customersImpl.purchaseCoupon(customersImpl.getCustomerId(),12);
        System.out.println();
        System.out.println("After:");
        customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);
        */


            ///////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive off customer coupons
            //customersImpl.getCustomerCoupons(customersImpl.getCustomerId()).forEach(System.out::println);

            //Unsuccessful attempt - customer does not exist
            //customersImpl.getCustomerCoupons(15).forEach(System.out::println);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive off customer coupons by category
            //customersImpl.getCustomerCouponsByCategory(customersImpl.getCustomerId(), Category.FOOD).forEach(System.out::println);

            //Unsuccessful attempt - customer does not exist
            //customersImpl.getCustomerCouponsByCategory(13, Category.FOOD).forEach(System.out::println);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive of coupons of a customer, equal or under a certain price
            //customersImpl.getCustomerCouponsByMaxPrice(customersImpl.getCustomerId(),65).forEach(System.out::println);

            //Unsuccessful attempt - customer does not exist
            //customersImpl.getCustomerCouponsByMaxPrice(13,65).forEach(System.out::println);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive of customer's details
            //customersImpl.showCustomerDetails(customersImpl.getCustomerId());

            //Unsuccessful attempt - customer does not exist
            //customersImpl.showCustomerDetails(13);

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

    }
}
