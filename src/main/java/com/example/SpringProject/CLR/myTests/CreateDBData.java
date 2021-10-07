package com.example.SpringProject.CLR.myTests;


import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.DailyJobThread.CouponExpirationDailyJob;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Repositories.CompaniesRepo;
import com.example.SpringProject.Repositories.CouponsRepo;
import com.example.SpringProject.Repositories.CustomersRepo;
import com.example.SpringProject.Service.AdminImpl;
import com.example.SpringProject.Service.CompaniesImpl;
import com.example.SpringProject.Service.CustomersImpl;
import com.example.SpringProject.Utills.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static com.example.SpringProject.Utills.Art.INIT_DATA;

@Component
@Order(1)
@RequiredArgsConstructor
public class CreateDBData implements CommandLineRunner {
    private final AdminImpl adminImpl;
    private final CompaniesImpl companiesImpl;
    private final CustomersImpl customersImpl;
    private final CouponExpirationDailyJob couponExpirationDailyJob;

    @Override
    public void run(String... args) throws Exception {

        try {
        //System.out.println(Art.ADMINISTRATOR);
        //System.out.println(Art.sperator);

        Company company1 = Company.builder().name("Microsoft").email("microsoft@gmail.com").password("1b34").build();
        Company company2 = Company.builder().name("Apple").email("apple@gmail.com").password("12e4").build();
        Company company3 = Company.builder().name("Amazon").email("amazon@gmail.com").password("5mq77").build();
        Company company4 = Company.builder().name("Samsung").email("samsung@gmail.com").password("w9f6").build();
        Company company5 = Company.builder().name("Intel").email("intel@gmail.com").password("jl5i").build();

        List<Company> companies = Arrays.asList(company1, company2, company3, company4, company5);
        adminImpl.addCompaniesListToDB(companies);

        Customer customer1 = Customer.builder().firstName("David").lastName("Birger").email("david@gmail.com").password("qasw").build();
        Customer customer2 = Customer.builder().firstName("Tom").lastName("Hacarmeli").email("tom@gmail.com").password("tyygy").build();
        Customer customer3 = Customer.builder().firstName("Yossi").lastName("Cohen").email("yossi@gmail.com").password("fgdf").build();
        Customer customer4 = Customer.builder().firstName("Avi").lastName("Levy").email("avi@gmail.com").password("cvvk").build();
        Customer customer5 = Customer.builder().firstName("Israel").lastName("Israeli").email("israel@gmail.com").password("3447").build();
        Customer customer6 = Customer.builder().firstName("Ron").lastName("Alon").email("ron@gmail.com").password("k43yy").build();
        Customer customer7 = Customer.builder().firstName("Benjamin").lastName("Netanyahu").email("benjamin@gmail.com").password("8g57").build();
        Customer customer8 = Customer.builder().firstName("Sara").lastName("Netanyahu").email("sara@gmail.com").password("55h34").build();
        Customer customer9 = Customer.builder().firstName("Benny").lastName("Gantz").email("benny@gmail.com").password("748f").build();
        Customer customer10 = Customer.builder().firstName("Naftali").lastName("Bennet").email("naftali@gmail.com").password("j87j6").build();

        List<Customer> customers = Arrays.asList(customer1, customer2, customer3, customer4, customer5, customer6, customer7, customer8, customer9, customer10);
        adminImpl.addCustomersListToDB(customers);

        Coupon coupon1 = Coupon.builder().companyId(1).category(Category.ELECTRICITY).title("Ace").description("BOSCH washing machine, model WAN28160BY for only 1,000 NIS").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
        Coupon coupon2 = Coupon.builder().companyId(1).category(Category.FOOD).title("Osher Ad").description("1 kg fresh veal sirloin - 79.90 NIS").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-03-29")).amount(50).price(79.90).image("Image").build();
        Coupon coupon3 = Coupon.builder().companyId(1).category(Category.RESTAURANT).title("Caffe caffe").description("Israeli breakfast for 57 NIS").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-03-29")).amount(50).price(57.00).image("Image").build();
        Coupon coupon4 = Coupon.builder().companyId(1).category(Category.VACATION).title("Club hotel").description("Double or Twin Room – Breakfast included, FREE cancellation • No prepayment needed").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-03-29")).amount(50).price(1500.00).image("Image").build();
        Coupon coupon5 = Coupon.builder().companyId(2).category(Category.ELECTRICITY).title("Electric Shop").description("75″ Class TU8000 Crystal UHD 4K Smart TV (2020)").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(2000.00).image("Image").build();
        Coupon coupon6 = Coupon.builder().companyId(2).category(Category.FOOD).title("SoGood night store").description("Buy at 200 and get products at 300").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(200.00).image("Image").build();
        Coupon coupon7 = Coupon.builder().companyId(2).category(Category.RESTAURANT).title("Pizza boom").description("1 plus 1 on buying a family pizza").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(50.00).image("Image").build();
        Coupon coupon8 = Coupon.builder().companyId(2).category(Category.VACATION).title("Royal Beach").description("2 rooms Double bed with balcony For two nights").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1000.00).image("Image").build();
        Coupon coupon9 = Coupon.builder().companyId(3).category(Category.ELECTRICITY).title("Big Electric").description("LG 70 Class 4K UHD 2160P Smart TV with HDR - 70UN7070PUA 2020 Model").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(2500.00).image("Image").build();
        Coupon coupon10 = Coupon.builder().companyId(3).category(Category.FOOD).title("Home store").description("Olive oil for 10 NIS").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(10.00).image("Image").build();
        Coupon coupon11 = Coupon.builder().companyId(3).category(Category.RESTAURANT).title("Meat meat").description("The house sandwich includes drink and dessert").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(49.90).image("Image").build();
        Coupon coupon12 = Coupon.builder().companyId(3).category(Category.VACATION).title("Jerusalem tours").description("A tour in Jerusalem in the holy places includes lunch").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(100.00).image("Image").build();
        Coupon coupon13 = Coupon.builder().companyId(4).category(Category.ELECTRICITY).title("Electric city").description("S3821w-C0 38-Inch Sound Bar with Bluetooth Wireless Sub").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(500.00).image("Image").build();
        Coupon coupon14 = Coupon.builder().companyId(4).category(Category.FOOD).title("Best big").description("Buy at 100 and get products at 200").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(0).price(100.00).image("Image").build();
        Coupon coupon15 = Coupon.builder().companyId(4).category(Category.RESTAURANT).title("Italiano").description("A meal for two that includes a main course and dessert").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2020-08-08")).amount(50).price(99.90).image("Image").build();
        Coupon coupon16 = Coupon.builder().companyId(4).category(Category.VACATION).title("Gil hotel").description("2 nights per couple including breakfast").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-04-12")).amount(50).price(500.00).image("Image").build();
        Coupon coupon17 = Coupon.builder().companyId(5).category(Category.ELECTRICITY).title("David's Electrics").description("SAMSUNG 70 Class LED 4K (2160P) LED Smart TV QN70Q60 2021").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(1500.00).image("Image").build();
        Coupon coupon18 = Coupon.builder().companyId(5).category(Category.FOOD).title("Super pharma").description("Chicken for 5 NIS").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(5.00).image("Image").build();
        Coupon coupon19 = Coupon.builder().companyId(5).category(Category.RESTAURANT).title("Burger burger").description("400g hamburger with fries and a drink").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(49.90).image("Image").build();
        Coupon coupon20 = Coupon.builder().companyId(5).category(Category.VACATION).title("Dolphin Reef").description("A tour of the Dolphin Reef including all the attractions").startDate(Date.valueOf("2020-04-04")).endDate(Date.valueOf("2022-08-08")).amount(50).price(53.00).image("Image").build();

        List<Coupon> coupons = Arrays.asList(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6, coupon7, coupon8, coupon9, coupon10, coupon11, coupon12, coupon13, coupon14, coupon15, coupon16, coupon17, coupon18, coupon19, coupon20);
        companiesImpl.addCouponsListToDB(coupons);

        List<Coupon> coupons1 = Arrays.asList(coupon1,coupon2,coupon3,coupon4);
        List<Coupon> coupons2 = Arrays.asList(coupon5,coupon6,coupon7,coupon8);
        List<Coupon> coupons3 = Arrays.asList(coupon9,coupon10,coupon11,coupon12);
        List<Coupon> coupons4 = Arrays.asList(coupon13,coupon16);
        List<Coupon> coupons5 = Arrays.asList(coupon17,coupon18,coupon19,coupon20);

        companiesImpl.addCouponsListToCompany(1,coupons1);
        companiesImpl.addCouponsListToCompany(2,coupons2);
        companiesImpl.addCouponsListToCompany(3,coupons3);
        companiesImpl.addCouponsListToCompany(4,coupons4);
        companiesImpl.addCouponsListToCompany(5,coupons5);

        customersImpl.purchaseCoupon(1,10);
        customersImpl.purchaseCoupon(2,9);
        customersImpl.purchaseCoupon(3,8);
        customersImpl.purchaseCoupon(4,11);
        customersImpl.purchaseCoupon(4,12);
        customersImpl.purchaseCoupon(4,17);
        customersImpl.purchaseCoupon(4,18);
        customersImpl.purchaseCoupon(4,19);
        //List<Integer> couponsList1 = Arrays.asList(11,12,17,18,19);
        //customersImpl.purchaseCouponsList(4,couponsList1);
        customersImpl.purchaseCoupon(5,6);
        customersImpl.purchaseCoupon(6,5);
        customersImpl.purchaseCoupon(7,6);
        customersImpl.purchaseCoupon(7,9);
        customersImpl.purchaseCoupon(7,10);
        customersImpl.purchaseCoupon(7,18);
        customersImpl.purchaseCoupon(7,19);
        //List<Integer> couponsList2 = Arrays.asList(6,9,10,18,19);
        //customersImpl.purchaseCouponsList(7,couponsList2);
        customersImpl.purchaseCoupon(8,12);
        customersImpl.purchaseCoupon(9,13);
        customersImpl.purchaseCoupon(10,5);

        //couponExpirationDailyJob.deleteExpiredCoupons();

        } catch (UserErrorException | CouponStockException err) {
            if (err instanceof UserErrorException) {
                System.out.println(err.getMessage());
            } else {
                System.out.println(err.getMessage());
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}