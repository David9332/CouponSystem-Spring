package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.UserErrorException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AdminService {

    /**
     * Adds a company to th DB. Checks first if it meets the validation conditions, then checks if it
     * already exists there (by name or email), and adds it only if it does not.
     *
     * @param company - A Company object.
     * @throws UserErrorException - When there is already a company with this name or email, or when there are validation problems.
     */
    void addCompany(Company company) throws UserErrorException;

    /**
     * A methode for initiating the database.
     * Adds a list of companies to the DB. Checks first if it meets the validation conditions, then Checks if they already exists there (by name
     * and email), and adds them only if they don't.
     *
     * @param companies - A list of companies.
     * @throws UserErrorException - When there is already a company with this name or email, or when there are validation problems.
     */
    void addCompaniesListToDB(List<Company> companies) throws UserErrorException;

    /**
     * Updates a company in the DB. Checks first if it meets the validation conditions, then checks that this
     * company exists in the DB (by companyId and name), then replaces the old Company object with a new one.
     *
     * @param company - A Company object.
     * @throws UserErrorException - When the user tries to change the company's name (which
     *                            we do not allow), or when there are validation problems.
     */
    void updateCompany(Company company) throws UserErrorException;

    /**
     * Deletes a Company object from the DB. First checks that the company exists in the DB (by id).
     * First deletes the company coupons, then delete the company itself with cascade delete.
     *
     * @param companyID - The id of the company.
     * @throws UserErrorException - When there is no company with that id in the DB.
     */
    void deleteCompany(int companyID) throws UserErrorException;

    /**
     * Gets all the companies in the DB into a list.
     *
     * @return - A list of companies.
     */
    List<Company> getAllCompanies();

    /**
     * Gets a specific Company Object from the DB (by companyId).
     *
     * @param companyID - The id of the company.
     * @return - A single Company object.
     * @throws UserErrorException - When there is no company with this id.
     */
    Optional<Company> getOneCompany(int companyID) throws UserErrorException;

    /**
     * Adds a Customer Object to the DB. Checks first if it meets the validation conditions, then
     * checks that it does not already exist there (by email).
     *
     * @param customer - A Customer object.
     * @throws UserErrorException - When there's already a customer with that email in the DB , or when there
     *                            are validation problems.
     */
    void addCustomer(Customer customer) throws UserErrorException;

    /**
     * A methode for initiating the database.
     * Adds a list of customers to the DB. Checks first if it meets the validation conditions, then Checks if they
     * already exist there (by email), and adds them only if they don't.
     *
     * @param customers - A list of customers.
     * @throws UserErrorException - When there's already a customer with that email, or when there
     *                            are validation problems.
     */
    void addCustomersListToDB(List<Customer> customers) throws UserErrorException;

    /**
     * Receives a Customer object from the user, first Checks if the customer exists in the database then (by
     * id), then checks if it meets the validation conditions, and if it does - updates it by
     * the given customer object.
     *
     * @param customer - A Customer object. Given by the user.
     * @throws UserErrorException - The customer does not exists in the data base.
     */
    void updateCustomer(Customer customer) throws UserErrorException;

    /**
     * Checks if a customer exists at the database (by id). Then deletes its coupons, and after that
     * deletes the customer itself with cascade delete.
     *
     * @param customerId - The id of the customer.
     * @throws UserErrorException - There is no customer with that id.
     */
    void deleteCustomer(int customerID) throws UserErrorException;

    /**
     * Gets all the customers in the database into a list.
     *
     * @return - A list of Customer objects.
     */
    List<Customer> getAllCustomers();

    /**
     * Gets one specific customer from the database (by id).
     *
     * @param customerID - The id of the customer.
     * @return - A Customer object.
     * @throws UserErrorException - There is no customer with that id.
     */
    Optional<Customer> getOneCustomer(int customerID) throws UserErrorException;
}
