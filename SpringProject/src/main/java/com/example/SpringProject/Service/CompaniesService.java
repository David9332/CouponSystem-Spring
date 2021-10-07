package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;

import java.util.Date;
import java.util.List;

public interface CompaniesService {

    /**
     * Receives a Coupon object from the user, first checks the validation conditions, then checks if the coupon
     * already exists in the database, and if it doesn't - adds it to the database and to the company's
     * coupons list.
     *
     * @param coupon - A Coupon object. Given by the user.
     * @throws UserErrorException - The coupon already exists in the database.
     */
    void addCoupon(Coupon coupon) throws UserErrorException;

    /**
     * A methode for initiating the database.
     * Adds a list of coupons to the DB, only if the coupons don't already exist there (checks by
     * companyId and title), and matches the validation conditions.
     *
     * @param coupons - A list of coupons.
     * @throws UserErrorException - When a coupon already exists at the DB, or when there is a problem
     *                            with the validations.
     */
    void addCouponsListToDB(List<Coupon> coupons) throws UserErrorException;

    /**
     * A methode for initiating the database.
     * Adds a list of coupons to a company. First checks that the company exists at the DB (by id)
     *
     * @param companyId - The id of the Company object.
     * @param coupons   - A list of Coupons objects.
     * @throws UserErrorException - 1. If there is no company with that id
     *                            - 2. If a coupon already exists at this company.
     *                            - 3. If the end date of this coupon has already passed.
     */
    void addCouponsListToCompany(int companyId, List<Coupon> coupons) throws UserErrorException, CouponStockException;

    /**
     * Receives a Coupon object from the user, checks if the coupon already exists in the
     * database, and if it does - updates it by the coupon object. Also checks validations.
     *
     * @param coupon - A coupon object. Given by the user.
     * @throws UserErrorException - The coupon does not exist, or validations problems.
     */
    void updateCoupon(Coupon coupon) throws UserErrorException;

    /**
     * Checks if a coupon exists in the database (by id), and if it does - delete it.
     *
     * @param couponID - The id of the coupon.
     * @throws UserErrorException - The coupon does not exist in the database.
     *                            - A company can't delete coupons of other companies.
     */
    void deleteCoupon(int couponID) throws UserErrorException;

    /**
     * Deletes coupons by end-dates that had already passed.
     *
     * @param date - A Date object.
     */
    void deleteCouponByDate(Date date);

    /**
     * Gets all the coupons of the logged-in company from the database and puts them
     * into a list.
     *
     * @return - A list of Coupon objects.
     */
    List<Coupon> getCompanyCoupons();

    List<Coupon> getCompanyCoupons(int companyId);

    /**
     * Gets all the coupons of the logged-in company, under or equal to a given price, from the database,
     * and puts them into a list.
     *
     * @param maxPrice - The price's upper limit.
     * @return - A list of Coupon objects.
     */
    List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws UserErrorException;

    /**
     * Gets all the coupons of the logged-in company by a specific category from the database
     * and puts them into a list.
     *
     * @param category - The category of the coupon.
     * @return - A list of Coupon objects.
     */
    List<Coupon> getCompanyCouponsByCategory(Category category);

    /**
     * Gets a specific coupon from the database by its id.
     *
     * @param couponId - The id of the coupon.
     * @return - A Coupon object.
     * @throws UserErrorException - If there is no coupon with that id.
     */
    Coupon getOneCoupon(int couponId) throws UserErrorException;

    /**
     * Gets the logged-in company object from the database.
     *
     * @return - A Company object.
     */
    Company getCompanyDetails() throws UserErrorException;

}
