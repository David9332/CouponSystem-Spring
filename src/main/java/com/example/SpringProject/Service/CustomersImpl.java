package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Repositories.CompaniesRepo;
import com.example.SpringProject.Repositories.CouponsRepo;
import com.example.SpringProject.Repositories.CustomersRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CustomersImpl extends ClientService implements CustomersService {
    private final CustomersRepo customersRepo;
    private final CouponsRepo couponsRepo;
    private final CompaniesRepo companiesRepo;
    private int customerId;

    /**
     * Checks if the email and password, that were typed by the user, matches the user's details
     * in the data base.
     *
     * @param email    - The customer's email.
     * @param password - The customer's password.
     * @throws UserErrorException - Incorrect email or password typed by the user.
     */
    public void customerLogin(String email, String password) throws UserErrorException {
        Customer customer = customersRepo.findByEmailAndPassword(email, password);
        if (customer == null) {
            throw new UserErrorException("'CustomersImpl'", "'customerLogin'", "Login failed. The reason could either be a typing error, or that the account you are looking for does not exist.");
        }
    }

    /**
     * Gets a coupon from the data base (by id), checks that:
     * 1. It wasn't already purchased by the customer.
     * 2. That there are more the 0 coupons of that kind.
     * 3. That its end-date had not already passed.
     * If all the above are ture, it adds a purchase and decreases the amount of coupons by 1.
     *
     * @param customerID - The id of the customer.
     * @param couponID   - The id of the coupon.
     * @throws UserErrorException   - 1. coupon already purchased by that customer.
     *                              2. There is no customer with that id.
     * @throws CouponStockException - 1. There are no more coupons of that kind in our stock.
     *                              2. The end date of this coupon has already passed
     */
    @Override
    public void purchaseCoupon(int customerID, int couponID) throws UserErrorException, CouponStockException {
        Customer customer = customersRepo.findById(customerID).orElseThrow(() -> new UserErrorException("'customersRepo'", "'findById'", "There is no customer with that id."));
        Coupon coupon = couponsRepo.findById(couponID).orElseThrow(() -> new UserErrorException("'couponsRepo'", "'findById'", "There is no coupon with that id."));
        if (customer.getCoupons().contains(coupon)) {
            throw new UserErrorException("'CustomerImpl'", "'purchaseCoupon'", "This coupon was already purchased by this customer. A customer can not have more then one of the same coupon.");
        }
        if (coupon.getAmount() == 0) {
            throw new CouponStockException("'CustomerImpl'", "'purchaseCoupon'", "Sorry, there are no more coupons of that kind in our stock. No purchase was done.");
        }
        if (coupon.getEndDate().before(new Date())) {
            throw new CouponStockException("'CustomerImpl'", "'purchaseCoupon'", "The end date of this coupon has already passed. No purchase was done.");
        }
        coupon.setAmount(coupon.getAmount() - 1);
        couponsRepo.save(coupon);
        customer.getCoupons().add(coupon);
        customersRepo.save(customer);
    }

/*
    @Override
    public void purchaseCouponsList(int customerID, List<Integer> couponsID) throws UserErrorException, CouponStockException {
        Customer customer = customersRepo.findById(customerID).orElseThrow(() -> new UserErrorException("'customersRepo'", "'findById'", "There is no customer with that id."));
        for (Integer integer : couponsID) {
            Coupon coupon = couponsRepo.findById(integer).orElseThrow(() -> new UserErrorException("'couponsRepo'", "'findById'", "There is no coupon with that id."));
            if (customer.getCoupons().contains(coupon)) {
                throw new UserErrorException("'CustomerImpl'", "'purchaseCoupon'", "This coupon was already purchased by this customer. A customer can not have more then one of the same coupon.");
            }
            if (coupon.getAmount() == 0) {
                throw new CouponStockException("'CustomerImpl'", "'purchaseCoupon'", "Sorry, there are no more coupons of that kind in our stock. No purchase was done.");
            }
            if (coupon.getEndDate().before(new Date())) {
                throw new CouponStockException("'CustomerImpl'", "'purchaseCoupon'", "The end date of one of the coupons in the list has already passed. No purchase was done.");
            }
            coupon.setAmount(coupon.getAmount() - 1);
            couponsRepo.saveAndFlush(coupon);
            customer.getCoupons().add(coupon);
            customersRepo.saveAndFlush(customer);
        }
    }
*/

    /**
     * Gets from the data base all the coupons that were purchased by a specific customer and
     * puts them into a list.
     *
     * @param customerID - The id of the customer.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public List<Coupon> getCustomerCoupons(int customerID) throws UserErrorException {
        Customer customer = customersRepo.findById(customerID).orElseThrow(() -> new UserErrorException("'customersRepo'", "'findById'", "There is no customer with that id."));
        return customer.getCoupons();
    }

    /**
     * Gets from the data base all the coupons that were purchased by a specific customer
     * that belongs to a specific category, and puts them into a list.
     *
     * @param customerID - The id of the customer.
     * @param category   - A Category enum.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public List<Coupon> getCustomerCouponsByCategory(int customerID, Category category) throws UserErrorException {
        Customer customer = customersRepo.findById(customerID).orElseThrow(() -> new UserErrorException("'customersRepo'", "'findById'", "There is no customer with that id."));
        return customer.getCoupons().stream().filter(coupon -> coupon.getCategory() == category).collect(Collectors.toList());
    }

    /**
     * Gets all the coupons of a specific customer, under or equal to a given price, from the data base,
     * and puts them into a list.
     *
     * @param customerID - The id of the customer.
     * @param maxPrice   - The price's upper limit.
     * @return - A list of Customer objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public List<Coupon> getCustomerCouponsByMaxPrice(int customerID, double maxPrice) throws UserErrorException {
        Customer customer = customersRepo.findById(customerID).orElseThrow(() -> new UserErrorException("'customersRepo'", "'findById'", "There is no customer with that id."));
        return customer.getCoupons().stream().filter(coupon -> coupon.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    /**
     * Gets a customer from the data base, and prints its details.
     *
     * @param customerID - The id of the customer.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public void showCustomerDetails(int customerID) throws UserErrorException {
        Customer customer = customersRepo.findById(customerID).orElseThrow(() -> new UserErrorException("'customersRepo'", "'findById'", "There is no customer with that id."));
        System.out.println(customer);
    }

    @Override
    public int getCustomerId(String email, String password) throws UserErrorException {
        if (customersRepo.findByEmailAndPassword(email, password) == null) {
            throw new UserErrorException("'customersRepo'", "'findByPasswordAndEmail'", "There is no customer with that password or email.");
        }
        return customersRepo.findByEmailAndPassword(email, password).getCustomerId();
    }

}
