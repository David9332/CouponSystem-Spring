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
import com.example.SpringProject.Utills.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(4)
public class TestCustomer implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println();
            System.out.println(Art.CUSTOMERS_METHODS);
         CustomersImpl customersImpl = (CustomersImpl) loginManager.login("benjamin@gmail.com", "8g57dgb8g57", ClientType.CUSTOMER);

            System.out.println();
            System.out.println("Successful purchase of coupon number 7 by the logged-in customer:");
            System.out.println("Please notice the amount of that coupon went down by 1");
            System.out.println("Before:");
            customersImpl.getCustomerCoupons().forEach(System.out::println);
            customersImpl.purchaseCoupon(7);
            System.out.println();
            System.out.println("After:");
            customersImpl.getCustomerCoupons().forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful coupon purchase by logged-in customer - coupon doe not exists");
//            System.out.println("Before:");
//            customersImpl.getCustomerCoupons().forEach(System.out::println);
//            customersImpl.purchaseCoupon(customersImpl.getCustomerId(), 28);
//            System.out.println();
//            System.out.println("After:");
//            customersImpl.getCustomerCoupons().forEach(System.out::println);

//            System.out.println();
//            System.out.println("Unsuccessful coupon purchase of coupon 14 by logged-in customer - coupon amount is 0");
//            System.out.println("Before:");
//            customersImpl.getCustomerCoupons().forEach(System.out::println);
//            customersImpl.purchaseCoupon(customersImpl.getCustomerId(), 14);
//            System.out.println();
//            System.out.println("After:");
//            customersImpl.getCustomerCoupons().forEach(System.out::println);



//            System.out.println();
//            System.out.println("Unsuccessful purchase of coupon 18 by logged-in customer - customer has already purchased this coupon");
//            System.out.println("Before:");
//            customersImpl.getCustomerCoupons().forEach(System.out::println);
//            customersImpl.purchaseCoupon(18);
//            System.out.println();
//            System.out.println("After:");
//            customersImpl.getCustomerCoupons().forEach(System.out::println);

            ///////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful receive of customer coupons of logged-in customer:");
            customersImpl.getCustomerCoupons().forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful receive of logged-in customer coupons by the category 'Food':");
            customersImpl.getCustomerCouponsByCategory(Category.FOOD).forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful receive of coupons of logged-in customer, equal or under the price 50:");
            customersImpl.getCustomerCouponsByMaxPrice(50).forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful receive of logged-in customer details:");
            customersImpl.showCustomerDetails();
            System.out.println();
            System.out.println(Art.sperator);

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

    }
}
