package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Beans.Customer;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;

import java.sql.SQLException;
import java.util.List;

public interface CustomersService {

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
    void purchaseCoupon(int couponID) throws UserErrorException, CouponStockException;

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
    void purchaseCouponForBuildingDB(int customerID, int couponID) throws UserErrorException, CouponStockException;

    /**
     * Gets from the database all the coupons that were purchased by the logged-in customer and
     * puts them into a list.
     *
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    List<Coupon> getCustomerCoupons() throws UserErrorException;

    /**
     * Gets from the database all the coupons that were purchased by the logged-in customer,
     * that belongs to a specific category, and puts them into a list.
     *
     * @param category - A Category enum.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - When there is no customer with that id.
     */
    List<Coupon> getCustomerCouponsByCategory(Category category) throws UserErrorException;

    /**
     * Gets all the coupons of the logged-in customer, under or equal to a given price, from the database,
     * and puts them into a list.
     *
     * @param maxPrice - The price's upper limit.
     * @return - A list of Customer objects.
     * @throws UserErrorException - The maximum price can't be below 0.
     */
    List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) throws UserErrorException;

    /**
     * Gets the logged-in customer from the database, and prints its details.
     *
     */
    void showCustomerDetails() throws UserErrorException;

}
