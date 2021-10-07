package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.UserErrorException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {
    private Customer customer;
    private Company company;

    public void adminLogin(String email, String password) throws UserErrorException {
    }

    /**
     * Checks if the email and password, that were typed by the user, matches the user's details
     * in the data base.
     *
     * @param email    - The customer's email.
     * @param password - The customer's password.
     * @throws UserErrorException - Incorrect email or password typed by the user.
     */
    public void customerLogin(String email, String password) throws UserErrorException{
    }

    /**
     * Checks if the email and password, that were typed by the user, matches the user's details
     * in the data base.
     *
     * @param email    - The company's email.
     * @param password - The company's password.
     * @throws UserErrorException - Incorrect email or password typed by the user.
     */
    public void companyLogin(String email, String password) throws UserErrorException {
    }
}
