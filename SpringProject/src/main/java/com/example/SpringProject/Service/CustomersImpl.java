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
    private static Customer customerLoggedIn;

    /**
     * Checks if the email and password typed by the user matches an object in the database. If they
     * do, sends "true" boolean value to the "login" method at the "LoginManager" class.
     *
     * @param email    - The company's email.
     * @param password - The company's password.
     * @throws UserErrorException - Incorrect email or password typed by the user.
     */
    @Override
    public boolean login(String email, String password) throws UserErrorException {
        customerLoggedIn = customersRepo.findByEmailAndPassword(email, password);
        if (customerLoggedIn == null) {
            throw new UserErrorException("'CustomersImpl'", "'login'", "Login failed. You either typed wrong details, or this account doesn't exist.");
        }
        return true;
    }

    /**
     * Gets a coupon from the database (by id), checks that:
     * 1. It wasn't already purchased by the customer.
     * 2. That there are more the 0 coupons of that kind.
     * 3. That its end-date had not already passed.
     * If all the above are ture, it adds a purchase and decreases the amount of coupons by 1.
     *
     * @param couponID - The id of the coupon.
     * @throws UserErrorException   - 1. coupon already purchased by that customer.
     *                              2. There is no customer with that id.
     * @throws CouponStockException - 1. There are no more coupons of that kind in our stock.
     *                              2. The end date of this coupon has already passed
     */
    @Override
    public void purchaseCoupon(int couponID) throws UserErrorException, CouponStockException {
        Coupon coupon = couponsRepo.findById(couponID).orElseThrow(() -> new UserErrorException("'CustomersImpl'", "'purchaseCoupon'", "There is no coupon with the id: " + couponID));
        if (customerLoggedIn.getCoupons().contains(coupon)) {
            throw new UserErrorException("'CustomerImpl'", "'purchaseCoupon'", "This coupon was already purchased by this customer." +
                    " A customer can not have more then one of the same coupon.");
        }
        if (coupon.getAmount() == 0) {
            throw new CouponStockException("'CustomerImpl'", "'purchaseCoupon'", "Sorry, there are no more coupons of that" +
                    " kind in our stock. No purchase was done.");
        }
        if (coupon.getEndDate().before(new Date())) {
            throw new CouponStockException("'CustomerImpl'", "'purchaseCoupon'", "The end date of this coupon has already passed." +
                    " No purchase was done.");
        }
        coupon.setAmount(coupon.getAmount() - 1);
        couponsRepo.save(coupon);
        customerLoggedIn.getCoupons().add(coupon);
        customersRepo.save(customerLoggedIn);
    }

    /**
     * Gets from the database all the coupons that were purchased by the logged-in customer and
     * puts them into a list.
     *
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public List<Coupon> getCustomerCoupons() throws UserErrorException {
        return customerLoggedIn.getCoupons();
    }

    /**
     * Gets from the database all the coupons that were purchased by the logged-in customer,
     * that belongs to a specific category, and puts them into a list.
     *
     * @param category - A Category enum.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    @Override
    public List<Coupon> getCustomerCouponsByCategory(Category category) throws UserErrorException {
        return customerLoggedIn.getCoupons().stream().filter(coupon -> coupon.getCategory() == category).collect(Collectors.toList());
    }

    /**
     * Gets all the coupons of the logged-in customer, under or equal to a given price, from the database,
     * and puts them into a list.
     *
     * @param maxPrice - The price's upper limit.
     * @return - A list of Customer objects.
     * @throws UserErrorException - The maximum price can't be below 0.
     */
    @Override
    public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) throws UserErrorException {
        if (maxPrice < 0) {
            throw new UserErrorException("'CustomerImpl'", "'getCustomerCouponsByMaxPrice'", "The maximum price can't be below 0.");
        }
        return customerLoggedIn.getCoupons().stream().filter(coupon -> coupon.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    /**
     * Gets the logged-in customer from the database, and prints its details.
     *
     */
    @Override
    public void showCustomerDetails() {
        System.out.println(customerLoggedIn);
    }

    /**
     * A methode for initiating the database.
     * Gets a coupon from the database (by id), checks that:
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
    public void purchaseCouponForBuildingDB(int customerID, int couponID) throws UserErrorException, CouponStockException {
        Coupon coupon = couponsRepo.findById(couponID).orElseThrow(() -> new UserErrorException("'CustomersImpl'", "'purchaseCouponForBuildingDB'", "There is no coupon with the id: " + couponID));
        Customer customer = customersRepo.findById(customerID).orElseThrow(() -> new UserErrorException("'CustomersImpl'", "'purchaseCouponForBuildingDB'", "There is no customer with the id: " + customerID));
        if (customer.getCoupons().contains(coupon)) {
            throw new UserErrorException("'CustomerImpl'", "'purchaseCouponForBuildingDB'", "Coupon number " + couponID + " was already purchased by this customer." +
                    " A customer can not have more then one of the same coupon.");
        }
        if (coupon.getAmount() == 0) {
            throw new CouponStockException("'CustomerImpl'", "'purchaseCouponForBuildingDB'", "Sorry, coupon number " + couponID +
                    " ran-out in our stock. No purchase was done.");
        }
        if (coupon.getEndDate().before(new Date())) {
            throw new CouponStockException("'CustomerImpl'", "'purchaseCouponForBuildingDB'", "The end date of this coupon has already passed." +
                    " purchase was not done.");
        }
        coupon.setAmount(coupon.getAmount() - 1);
        couponsRepo.save(coupon);
        customer.getCoupons().add(coupon);
        customersRepo.save(customer);
    }
}
