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

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class TestAdmin implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        try {
            System.out.println(Art.sperator);
            System.out.println();
            System.out.print(Art.ADMIN_METHODS);
            AdminImpl adminImpl = (AdminImpl) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

            System.out.println();
            System.out.println("Successful company add:");
            System.out.println("Before:");
            adminImpl.getAllCompanies().forEach(System.out::println);
            System.out.println();
            Company AddedCompany = Company.builder().name("Lenovo").email("Lenovo@gmail.com").password("455v34cc3rdrr").build();
            adminImpl.addCompany(AddedCompany);
            System.out.println("After:");
            adminImpl.getAllCompanies().forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful company add - company name already exists in the DB");
//            System.out.println("Before:");
//            adminImpl.getAllCompanies().forEach(System.out::println);
//            System.out.println();
//            company6 = Company.builder().name("Intel").email("Lenovo@gmail.com").password("455v34cc3rdrr").build();
//            adminImpl.addCompany(company6);
//            System.out.println("After:");
//            adminImpl.getAllCompanies().forEach(System.out::println);

//            System.out.println();
//            System.out.println("Unsuccessful company add - company email already exists in the DB");
//            System.out.println("Before:");
//            adminImpl.getAllCompanies().forEach(System.out::println);
//            System.out.println();
//            company6 = Company.builder().name("SomeCompany").email("intel@gmail.com").password("455v34cc3rdrr").build();
//            adminImpl.addCompany(company6);
//            System.out.println("After:");
//            adminImpl.getAllCompanies().forEach(System.out::println);

          /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful company update of company number 1:");
            System.out.println();
            System.out.println("Before:");
            System.out.println(adminImpl.getOneCompany(1));
            System.out.println();
            Company updatedCompany = Company.builder().companyId(1).name("Microsoft").email("updatedCompany@gmail.com").password("y2524234235yy").build();
            adminImpl.updateCompany(updatedCompany);
            System.out.println("After:");
            System.out.println(adminImpl.getOneCompany(1));
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful company update - can't change the company's name");
//            System.out.println("Before:");
//            System.out.println(adminImpl.getOneCompany(1));
//            System.out.println();
//            Company updatedCompany2 = Company.builder().companyId(1).name("UpdatedMicrosoft").email("updatedCompany@gmail.com").password("y2524234235yy").build();
//            adminImpl.updateCompany(updatedCompany2);
//            System.out.println("After:");
//            System.out.println(adminImpl.getOneCompany(1));

//            System.out.println();
//            System.out.println("Unsuccessful company update - can't change the company's id");
//            System.out.println("Before:");
//            System.out.println(adminImpl.getOneCompany(1));
//            System.out.println();
//            Company updatedCompany3 = Company.builder().companyId(2).name("UpdatedMicrosoft").email("updatedCompany@gmail.com").password("y2524234235yy").build();
//            adminImpl.updateCompany(updatedCompany3);
//            System.out.println("After:");
//            System.out.println(adminImpl.getOneCompany(1));

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful company delete of company number 3:");
            System.out.println("In the database: please notice that all of the company coupons were deleted from the coupon table.");
            System.out.println("In the database: please notice that all of the clients purchase history of that company coupons were deleted from the customer-VS-coupons table.");
            System.out.println();
            System.out.println("Before:");
            adminImpl.getAllCompanies().forEach(System.out::println);
            System.out.println();
            adminImpl.deleteCompany(3);
            System.out.println("After:");
            adminImpl.getAllCompanies().forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful company delete - can't delete an un-existing company (number 7)");
//            System.out.println("Before:");
//            adminImpl.getAllCompanies().forEach(System.out::println);
//            System.out.println();
//            adminImpl.deleteCompany(7);
//            System.out.println("After:");
//            adminImpl.getAllCompanies().forEach(System.out::println);


            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println();
            System.out.println("Successful Receive of all companies in the company table:");
            adminImpl.getAllCompanies().forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);


            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println();
            System.out.println("Successful receive of one existing company (company number 4):");
            System.out.println(adminImpl.getOneCompany(4));
            System.out.println();
            System.out.println(Art.sperator);


//            System.out.println();
//            System.out.println("Unsuccessful receive of an un-existing company (number 10)");
//            System.out.println(adminImpl.getOneCompany(10));

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful customer add:");
            System.out.println("Before:");
            adminImpl.getAllCustomers().forEach(System.out::println);
            System.out.println();
            Customer customerAdded = Customer.builder().firstName("John").lastName("Doe").email("john@gmail.com").password("j8fer4eff7j6").build();
            adminImpl.addCustomer(customerAdded);
            System.out.println("After:");
            adminImpl.getAllCustomers().forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);


//            System.out.println();
//            System.out.println("Unsuccessful customer add - can't add a customer with an email that already exists in the DB");
//            System.out.println("Before:");
//            adminImpl.getAllCustomers().forEach(System.out::println);
//            System.out.println();
//            Customer customerAdded2 = Customer.builder().firstName("John").lastName("Doe").email("avi@gmail.com").password("j87jf4redefe6").build();
//            adminImpl.addCustomer(customerAdded2);
//            System.out.println("After:");
//            adminImpl.getAllCustomers().forEach(System.out::println);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful customer update of customer number 1:");
            System.out.println("Before:");
            System.out.println(adminImpl.getOneCustomer(1));
            Customer customerUpdated = Customer.builder().customerId(1).firstName("xxxx").lastName("xxxx").email("xxxx@gmail.com").password("j85fffdffg7j6").build();
            adminImpl.updateCustomer(customerUpdated);
            System.out.println();
            System.out.println("After:");
            System.out.println(adminImpl.getOneCustomer(1));
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful customer update of customer number 1 - can't change the id of the customer");
//            System.out.println("Before:");
//            System.out.println(adminImpl.getOneCustomer(1));
//            Customer customerUpdated2 = Customer.builder().customerId(27).firstName("xxxx").lastName("xxxx").email("xxxx@gmail.com").password("ffsdfdsfsdgdf").build();
//            adminImpl.updateCustomer(customerUpdated2);
//            System.out.println();
//            System.out.println("After:");
//            System.out.println(adminImpl.getOneCustomer(1));

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful customer delete of customer number 4:");
            System.out.println("In the database: please notice that all of the clients purchase history was deleted from the customer-VS-coupons table.");
            System.out.println("Before:");
            adminImpl.getAllCustomers().forEach(System.out::println);
            adminImpl.deleteCustomer(4);
            System.out.println();
            System.out.println("After:");
            adminImpl.getAllCustomers().forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful customer delete of cutomer number 15 - can't delete a customer that does not exist");
//            System.out.println("Before:");
//            adminImpl.getAllCustomers().forEach(System.out::println);
//            adminImpl.deleteCustomer(15);
//            System.out.println();
//            System.out.println("After:");
//            adminImpl.getAllCustomers().forEach(System.out::println);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println();
            System.out.println("Successful Receive of all customers from the customer table:");
            adminImpl.getAllCustomers().forEach(System.out::println);
            System.out.println();
            System.out.println(Art.sperator);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println();
            System.out.println("Successful receive of an existing customer (customer number 2):");
            System.out.println(adminImpl.getOneCustomer(2));
            System.out.println();
            System.out.println(Art.sperator);

//            System.out.println();
//            System.out.println("Unsuccessful receive of an un-existing customer (customer number 13)");
//            System.out.println(adminImpl.getOneCustomer(13));

            System.out.println();

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}

