package com.example.SpringProject.Service;

import com.example.SpringProject.Beans.Category;
import com.example.SpringProject.Beans.Company;
import com.example.SpringProject.Beans.Coupon;
import com.example.SpringProject.Exceptions.CouponStockException;
import com.example.SpringProject.Exceptions.UserErrorException;
import com.example.SpringProject.Repositories.CompaniesRepo;
import com.example.SpringProject.Repositories.CouponsRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CompaniesImpl extends ClientService implements CompaniesService {
    private final CouponsRepo couponsRepo;
    private final CompaniesRepo companiesRepo;
    private int companyId;

    /**
     * Checks if the email and password, that were typed by the user, matches the user's details
     * in the data base.
     *
     * @param email    - The company's email.
     * @param password - The company's password.
     * @throws UserErrorException - Incorrect email or password typed by the user.
     */
    public void companyLogin(String email, String password) throws UserErrorException {
        Company company = companiesRepo.findByEmailAndPassword(email, password);
        if (company == null) {
            throw new UserErrorException("'CompaniesImpl'", "'companyLogin'", "Login failed. The reason could either be a typing error, or that the account you are looking for does not exist.");
        }
    }

    /**
     * Receives a Coupon object from the user, checks if the coupon already exists in the
     * data base, and if it doesn't - adds it to the data base and to the company's
     * coupons list.
     *
     * @param coupon - A Coupon object. Given by the user.
     * @throws UserErrorException - The coupon already exists in the data base.
     */
    @Override
    public void addCoupon(Coupon coupon) throws UserErrorException {
        if (couponsRepo.findByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle()) != null) {
            throw new UserErrorException("'couponsRepo'", "'findByCompanyIdAndTitle'", "The title of the coupon that you are trying to add already exists for this company." +
                    " You can not have two coupons with the same title.");
        }
        couponsRepo.save(coupon);
    }

    /**
     * Adds a list of coupons to the DB, only if the coupons don't already exist there (checks by
     * companyId and title).
     *
     * @param coupons - A list of coupons.
     * @throws UserErrorException - When a coupon already exists at the DB.
     */
    public void addCouponsListToDB(List<Coupon> coupons) throws UserErrorException {
        for (Coupon coupon : coupons) {
            if (couponsRepo.findByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle()) != null) {
                throw new UserErrorException("'couponsRepo'", "'findByCompanyIdAndTitle'", "The title of the " + coupon.getTitle() + " coupon that you are trying to add already exists for" +
                        " this company. You can not have two coupons with the same title.");
            }
            couponsRepo.save(coupon);
        }
    }

    /**
     * Adds a list of coupons to a company. First checks that the company exists at the DB (by id)
     *
     * @param companyId - The id of the Company object.
     * @param coupons   - A list of Coupons objects.
     * @throws UserErrorException   - 1. If there is no company with that id
     *                              - 2. If a coupon already exists at this company.
     * @throws CouponStockException - If the end date of this coupon has already passed.
     */
    public void addCouponsListToCompany(int companyId, List<Coupon> coupons) throws UserErrorException, CouponStockException {
        Company company = companiesRepo.findById(companyId).orElseThrow(() -> new UserErrorException("'companiesRepo'", "'findById'", "There is no company with that id."));
        for (Coupon coupon : coupons) {
            if (company.getCoupons().contains(coupon)) {
                throw new UserErrorException("'CompaniesImpl'", "'addCouponsListToCompany'", "This coupon already exists at this company.");
            }
            if (coupon.getEndDate().before(new Date())) {
                throw new CouponStockException("'CompaniesImpl'", "'addCouponsListToCompany'", "The end date of this coupon has already passed.");
            }
        }
        company.setCoupons(coupons);
        companiesRepo.save(company);
    }

    /**
     * Receives a Coupon object from the user, checks if the coupon already exists in the
     * data base, and if it does - updates it by the coupon object.
     *
     * @param coupon - A coupon object. Given by the user.
     * @throws UserErrorException - The coupon does not exist.
     */
    @Override
    public void updateCoupon(Coupon coupon) throws UserErrorException {
        if (couponsRepo.findByCompanyIdAndCouponId(coupon.getCompanyId(), coupon.getCouponId()) == null) {
            throw new UserErrorException("'couponsRepo'", "'findByCompanyIdAndCouponId'", "You can not change the company id and the coupon id.");
        }
        couponsRepo.saveAndFlush(coupon);
    }

    /**
     * Checks if a coupon exists in the data base (by id), and if it does - deletes it.
     *
     * @param couponID - The id of the coupon.
     * @throws UserErrorException - The coupon does not exist in the data base.
     */
    @Override
    public void deleteCoupon(int couponID) throws UserErrorException {
        if (!couponsRepo.existsById(couponID)) {
            throw new UserErrorException("'couponsRepo'", "'existsById'", "The coupon you are trying to delete does not exist in the DB.");
        }
        couponsRepo.deleteByCouponId(couponID);
        couponsRepo.deleteById(couponID);
    }

    @Override
    public void deleteCouponByDate(Date date){
        couponsRepo.findByEndDateBefore(date).forEach(coupon -> {
            try {
                deleteCoupon(coupon.getCouponId());
            } catch (UserErrorException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Gets all the coupons of a specific company from the data base (by company id) and puts them
     * into a list.
     *
     * @param companyID - The id of the company.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - There is no company with this id.
     */
    @Override
    public List<Coupon> getCompanyCoupons(int companyID) throws UserErrorException {
        if (!companiesRepo.existsById(companyID)) {
            throw new UserErrorException("'companiesRepo'", "'existsById'", "There is no company with that id.");
        }
        return couponsRepo.findByCompanyId(companyID);
    }

    /**
     * Gets all the coupons of a specific company, under or equal to a given price, from the data
     * base and puts them into a list.
     *
     * @param companyID - The id of the company.
     * @param maxPrice  - The price's upper limit.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - There is no company with this id.
     */
    @Override
    public List<Coupon> getCompanyCouponsByMaxPrice(int companyID, double maxPrice) throws UserErrorException {
        if (!companiesRepo.existsById(companyID)) {
            throw new UserErrorException("'companiesRepo'", "'existsById'", "There is no company with that id.");
        }
        return couponsRepo.findByCompanyIdAndPriceLessThan(companyID, maxPrice);
    }

    /**
     * Gets all the coupons of a specific company by a specific category from the data base
     * (by company id and category) and puts them into a list.
     *
     * @param companyID - The id of the company.
     * @param category  - The category of the coupon.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - There is no company with this id.
     */
    @Override
    public List<Coupon> getCompanyCouponsByCategory(int companyID, Category category) throws UserErrorException {
        if (!companiesRepo.existsById(companyID)) {
            throw new UserErrorException("'companiesRepo'", "'existsById'", "There is no company with that id.");
        }
        return couponsRepo.findAllByCompanyIdAndCategory(companyID, category);
    }

    /**
     * Receive a Company object from the data base(by id).
     *
     * @param companyID - the id of the company.
     * @throws UserErrorException - There is no company with this id.
     */
    @Override
    public Optional<Company> getCompanyDetails(int companyID) throws UserErrorException {
        if (!companiesRepo.existsById(companyID)) {
            throw new UserErrorException("'companiesRepo'", "'existsById'", "There is no company with that id.");
        }
        return companiesRepo.findById(companyID);
    }

    @Override
    public int getCompanyId(String email, String password) throws UserErrorException {
        if (companiesRepo.findByEmailAndPassword(email, password) == null) {
            throw new UserErrorException("'companiesRepo'", "'findByPasswordAndEmail'", "There is no company with that password or email.");
        }
        return companiesRepo.findByEmailAndPassword(email, password).getCompanyId();
    }
}
