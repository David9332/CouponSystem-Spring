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
import com.example.SpringProject.Utills.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(3)
public class TestCompany implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println(Art.COMPANIES_METHODS);
            CompaniesImpl companiesImpl = (CompaniesImpl) loginManager.login("apple@gmail.com", "12e412e4hg", ClientType.COMPANY);

            System.out.println();
            System.out.println("Successful coupon add for the logged-in company:");
            System.out.println("Before:");
            companiesImpl.getCompanyCoupons().forEach(System.out::println);
            Coupon couponAdded = Coupon.builder().companyId(companiesImpl.getCompanyLoggedIn().getCompanyId()).category(Category.ELECTRICITY).title("Added coupon").description("BOSCH washing machine, model WAN28160BY for only 1,000 NIS").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
            companiesImpl.addCoupon(couponAdded);
            System.out.println();
            System.out.println("After:");
            companiesImpl.getCompanyCoupons().forEach(System.out::println);
            System.out.println();
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful coupon add for the logged-in company - a company can't have two coupons with the same title");
//            System.out.println("Before:");
//            companiesImpl.getCompanyCoupons().forEach(System.out::println);
//            Coupon couponAdded2 = Coupon.builder().companyId(companiesImpl.getCompanyId()).category(Category.ELECTRICITY).title("SoGood night store").description("BOSCH washing machine, model WAN28160BY for only 1,000 NIS").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
//            companiesImpl.addCoupon(couponAdded2);
//            System.out.println();
//            System.out.println("After:");
//            companiesImpl.getCompanyCoupons().forEach(System.out::println);


            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful coupon update for coupon number 8");
            System.out.println("Before:");
            System.out.println(companiesImpl.getOneCoupon(8));
            Coupon couponUpdated = Coupon.builder().couponId(8).companyId(companiesImpl.getCompanyLoggedIn().getCompanyId()).category(Category.ELECTRICITY).title("xxxxxxxxxxxxxxxxxxxx").description("xxxxxxxxxxxxxxxxxxxx").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
            companiesImpl.updateCoupon(couponUpdated);
            System.out.println();
            System.out.println("After:");
            System.out.println(companiesImpl.getOneCoupon(8));
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful coupon update of coupon number 8 - can't change the id of the coupon");
//            System.out.println("Before:");
//            System.out.println(companiesImpl.getOneCoupon(8));
//            Coupon couponUpdated2 = Coupon.builder().couponId(10).companyId(companiesImpl.getCompanyId()).category(Category.ELECTRICITY).title("xxxxxxxxxxxxxxxxxxxx").description("xxxxxxxxxxxxxxxxxxxx").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
//            companiesImpl.updateCoupon(couponUpdated2);
//            System.out.println();
//            System.out.println("After:");
//            System.out.println(companiesImpl.getOneCoupon(8));

//            System.out.println();
//            System.out.println("Unsuccessful coupon update of coupon number 8 - can't change the company-id of the coupon");
//            System.out.println("Before:");
//            System.out.println(companiesImpl.getOneCoupon(8));
//            Coupon couponUpdated3 = Coupon.builder().couponId(8).companyId(companiesImpl.getCompanyId()).category(Category.ELECTRICITY).title("xxxxxxxxxxxxxxxxxxxx").description("xxxxxxxxxxxxxxxxxxxx").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
//            companiesImpl.updateCoupon(couponUpdated3);
//            System.out.println();
//            System.out.println("After:");
//            System.out.println(companiesImpl.getOneCoupon(8));

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful coupon delete of coupon number 8.");
            System.out.println("In the DB: notice that the coupon is deleted from the coupon table, the customer-VS-coupon and the company-VS-coupons.");
            System.out.println("Before:");
            companiesImpl.getCompanyCoupons(companiesImpl.getCompanyLoggedIn().getCompanyId()).forEach(System.out::println);
            companiesImpl.deleteCoupon(8);
            System.out.println();
            System.out.println("After:");
            companiesImpl.getCompanyCoupons(companiesImpl.getCompanyLoggedIn().getCompanyId()).forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful delete of coupon number 8 - a company can't delete a coupon of another company.");
//            System.out.println("Before:");
//            companiesImpl.getCompanyCoupons().forEach(System.out::println);
//            companiesImpl.deleteCoupon(19);
//            System.out.println();
//            System.out.println("After:");
//            companiesImpl.getCompanyCoupons().forEach(System.out::println);

//            System.out.println();
//            System.out.println("Unsuccessful coupon delete of coupon number 34 - can't delete a coupon that does not exist in the DB");
//            System.out.println("Before:");
//            companiesImpl.getCompanyCoupons().forEach(System.out::println);
//            companiesImpl.deleteCoupon(34);
//            System.out.println();
//            System.out.println("After:");
//            companiesImpl.getCompanyCoupons().forEach(System.out::println);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful receive of the coupons of logged-in company:");
            companiesImpl.getCompanyCoupons(companiesImpl.getCompanyLoggedIn().getCompanyId()).forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println();
            System.out.println("Successful receive of coupons of logged-in company, equal or under the price 200:");
            companiesImpl.getCompanyCouponsByMaxPrice(200).forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful receive of coupons of logged-in company by the category 'Food':");
            System.out.println(companiesImpl.getCompanyCouponsByCategory(Category.FOOD));
            System.out.println();
            System.out.println(Art.sperator);
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful receive of logged-in company details:");
            System.out.println(companiesImpl.getCompanyDetails());
            System.out.println();
            System.out.println(Art.sperator);

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
