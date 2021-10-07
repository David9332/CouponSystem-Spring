package com.example.SpringProject.Repositories;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface CouponsRepo extends JpaRepository<Coupon, Integer> {
    /**
     * Gets one coupon from the DB by it's company id and title.
     *
     * @param companyId - The id of the company.
     * @param title     - The title of the coupon.
     * @return - A Coupon object.
     */
    Coupon findByCompanyIdAndTitle(int companyId, String title);

    /**
     * Gets one coupon from the DB by it's company id and coupon id.
     *
     * @param companyId - The id of the company.
     * @param couponId  - The id of the coupon.
     * @return - A Coupon object.
     */
    Coupon findByCompanyIdAndCouponId(int companyId, int couponId);

    /**
     * Gets a list of coupons from the DB by company id.
     *
     * @param companyId - The id of the company.
     * @return - A list of coupons.
     */
    List<Coupon> findByCompanyId(int companyId);

    /**
     * Gets a list of coupons from the DB by company id and maximum price.
     *
     * @param companyId - The id of the company.
     * @param maxPrice  - The upper price limit.
     * @return - A list of coupons.
     */
    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double maxPrice);

    /**
     * Gets a list of coupons from the DB by company id and category.
     *
     * @param companyID - The id of the company.
     * @param category  - The category of the coupon.
     * @return
     */
    List<Coupon> findAllByCompanyIdAndCategory(int companyID, Category category);

    /**
     * Gets a list of coupons from the DB that are before a specific date.
     *
     * @param date - A date object.
     * @return - A list of coupon.
     */
    List<Coupon> findByEndDateBefore(Date date);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customer_coupons WHERE coupons_coupon_id=:couponId", nativeQuery = true)
    void deleteByCouponId(int couponId);

}
