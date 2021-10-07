package com.example.SpringProject.CLR.myTests;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Repositories.CompaniesRepo;
import com.example.SpringProject.Repositories.CouponsRepo;
import com.example.SpringProject.Repositories.CustomersRepo;
import com.example.SpringProject.Service.*;
import com.example.SpringProject.Utills.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

//@Component
@RequiredArgsConstructor
@Order(2)
public class TestAdmin implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        try {

            //Successful login
            AdminImpl adminImpl = (AdminImpl) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

            //Unsuccessful login - wrong password
            //AdminImpl adminImpl = (AdminImpl) loginManager.login("admin@admin.com", "xxxx", ClientType.ADMINISTRATOR);

            //Unsuccessful login - wrong email
            //AdminImpl adminImpl = (AdminImpl) loginManager.login("xxxx@admin.com", "admin", ClientType.ADMINISTRATOR);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*
        //Successful company add
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        System.out.println();
        Company company6 = Company.builder().name("Lenovo").email("Lenovo@gmail.com").password("455drr").build();
        adminImpl.addCompany(company6);
        System.out.println("After:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        */

       /*
        //Unsuccessful company add - company name already exists in the DB
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        System.out.println();
        Company company6 = Company.builder().name("Intel").email("Lenovo@gmail.com").password("455drr").build();
        adminImpl.addCompany(company6);
        System.out.println("After:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        */

            /*
        //Unsuccessful company add - email already exists in the DB
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        System.out.println();
        Company company6 = Company.builder().name("Lenovo").email("intel@gmail.com").password("455drr").build();
        adminImpl.addCompany(company6);
        System.out.println("After:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        */

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*
        //Successful company update
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        System.out.println();
        Company company1 = Company.builder().companyId(1).name("Microsoft").email("updatedCompany@gmail.com").password("yyy").build();
        adminImpl.updateCompany(company1);
        System.out.println("After:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        */

        /*
        //Unsuccessful company update - can't change the company's name
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        System.out.println();
        Company company1 = Company.builder().companyId(1).name("UpdatedMicrosoft").email("updatedCompany@gmail.com").password("yyy").build();
        adminImpl.updateCompany(company1);
        System.out.println("After:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        */

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
        //Successful company delete
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        System.out.println();
        adminImpl.deleteCompany(3);
        System.out.println("After:");
        adminImpl.getAllCompanies().forEach(System.out::println);
*/

        /*
        //Unsuccessful company delete - can't delete an un-existing company
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCompanies().forEach(System.out::println);
        System.out.println();
        adminImpl.deleteCompany(7);
        System.out.println("After:");
        adminImpl.getAllCompanies().forEach(System.out::println);
*/

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Receive of all companies
            //adminImpl.getAllCompanies().forEach(System.out::println);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive of an existing company
            //System.out.println(adminImpl.getOneCompany(4));

            //Unsuccessful receive of an un-existing company
            //System.out.println(adminImpl.getOneCompany(10));

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*
        //Successful customer add
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCustomers().forEach(System.out::println);
        System.out.println();
        Customer customer10 = Customer.builder().firstName("John").lastName("Doe").email("john@gmail.com").password("j87j6").build();
        adminImpl.addCustomer(customer10);
        System.out.println("After:");
        adminImpl.getAllCustomers().forEach(System.out::println);
        */

        /*
        //Unsuccessful customer add - can't add a customer with an email that already exists in the DB.
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCustomers().forEach(System.out::println);
        System.out.println();
        Customer customer10 = Customer.builder().firstName("John").lastName("Doe").email("avi@gmail.com").password("j87j6").build();
        adminImpl.addCustomer(customer10);
        System.out.println("After:");
        adminImpl.getAllCustomers().forEach(System.out::println);
        */

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*
        //Successful customer update
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCustomers().forEach(System.out::println);
        Customer customer10 = Customer.builder().customerId(1).firstName("xxxx").lastName("xxxx").email("xxxx@gmail.com").password("j87j6").build();
        adminImpl.updateCustomer(customer10);
        System.out.println();
        System.out.println("After:");
        adminImpl.getAllCustomers().forEach(System.out::println);
         */

        /*
        //Unsuccessful customer update - can't change the id of the customer.
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCustomers().forEach(System.out::println);
        Customer customer3 = Customer.builder().customerId(27).firstName("xxxx").lastName("xxxx").email("xxxx@gmail.com").password("fgdf").build();
        adminImpl.updateCustomer(customer10);
        System.out.println();
        System.out.println("After:");
        adminImpl.getAllCustomers().forEach(System.out::println);
         */

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
        //Successful customer delete
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCustomers().forEach(System.out::println);
        adminImpl.deleteCustomer(4);
        System.out.println();
        System.out.println("After:");
        adminImpl.getAllCustomers().forEach(System.out::println);
*/

        /*
        //Unsuccessful customer delete - can't delete a customer that does not exist.
        System.out.println();
        System.out.println("Before:");
        adminImpl.getAllCustomers().forEach(System.out::println);
        adminImpl.deleteCustomer(26);
        System.out.println();
        System.out.println("After:");
        adminImpl.getAllCustomers().forEach(System.out::println);
*/

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Receive of all companies
            //adminImpl.getAllCustomers().forEach(System.out::println);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Successful receive of an existing customer
            //System.out.println(adminImpl.getOneCustomer(2));

            //Unsuccessful receive of an un-existing customer
            //System.out.println(adminImpl.getOneCustomer(13));

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}

