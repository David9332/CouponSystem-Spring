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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CompaniesImpl extends ClientService implements CompaniesService {
    private final CouponsRepo couponsRepo;
    private final CompaniesRepo companiesRepo;
    private static Company companyLoggedIn;

    /**
     * Checks if the email and password typed by the user matches an object the database. If they
     * do, sends "true" boolean value to the "login" method at the "LoginManager" class.
     *
     * @param email    - The company's email.
     * @param password - The company's password.
     * @throws UserErrorException - Incorrect email or password typed by the user.
     */
    @Override
    public boolean login(String email, String password) throws UserErrorException {
        companyLoggedIn = companiesRepo.findByEmailAndPassword(email, password);
        if (companyLoggedIn == null) {
            throw new UserErrorException("'CompaniesImpl'", "'login'", "Login failed. You either typed wrong details, or this account doesn't exist.");
        }
        return true;
    }

    /**
     * Receives a Coupon object from the user, first checks the validation conditions, then checks if the coupon
     * already exists in the database, and if it doesn't - adds it to the database and to the company's
     * coupons list.
     *
     * @param coupon - A Coupon object. Given by the user.
     * @throws UserErrorException - The coupon already exists in the database.
     */
    @Override
    public void addCoupon(Coupon coupon) throws UserErrorException {
        if (couponsRepo.findByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle()) != null) {
            throw new UserErrorException("'CompaniesImpl'", "'addCoupon'", "The title of the coupon that you are trying to add already exists for this company." +
                    " You can not have two coupons with the same title.");
        }
        if (coupon.getCompanyId() != companyLoggedIn.getCompanyId()) {
            throw new UserErrorException("'CompaniesImpl'", "'addCoupon'", "A company can't add a coupon with a company id " +
                    "of another company. Adding failed.");
        }
        if (coupon.getDescription().equals("")) {
            throw new UserErrorException("'CompaniesImpl'", "'addCoupon'", "The description must be at least 2 notes long." +
                    " \nAdding failed.");
        }
        if (coupon.getImage().equals("")) {
            throw new UserErrorException("'CompaniesImpl'", "'addCoupon'", "You must enter an image link." +
                    " \nAdding failed.");
        }
        if (coupon.getEndDate().before(new Date())) {
            throw new UserErrorException("'CompaniesImpl'", "'addCoupon'", "The end-date must be in a future date." +
                    " \nAdding failed.");
        }
        if (coupon.getPrice() < 0) {
            throw new UserErrorException("'CompaniesImpl'", "'addCoupon'", "The price can't be below zero." +
                    " \nAdding failed.");
        }
        couponsRepo.save(coupon);
        List<Coupon> coupons = companyLoggedIn.getCoupons();
        coupons.add(coupon);
        companyLoggedIn.setCoupons(coupons);
        companiesRepo.save(companyLoggedIn);
    }

    /**
     * A methode for initiating the database.
     * Adds a list of coupons to the DB, only if the coupons don't already exist there (checks by
     * companyId and title), and matches the validation conditions.
     *
     * @param coupons - A list of coupons.
     * @throws UserErrorException - When a coupon already exists at the DB, or when there is a problem
     *                            with the validations.
     */
    public void addCouponsListToDB(List<Coupon> coupons) throws UserErrorException {
        for (Coupon coupon : coupons) {
            if (couponsRepo.findByCompanyIdAndTitle(coupon.getCompanyId(), coupon.getTitle()) != null) {
                throw new UserErrorException("'CompaniesImpl'", "'addCouponsListToDB'", "The title '" + coupon.getTitle() + "' you are trying to add already exists for this company." +
                        " You can not have two coupons with the same title.");
            }
            if (coupon.getTitle().equals("")) {
                throw new UserErrorException("'CompaniesImpl'", "'addCouponsListToDB'", "Problem with coupon '" + coupon.getTitle() + "': title field can't be empty." +
                        " \nAdding failed.");
            }
            if (coupon.getDescription().equals("")) {
                throw new UserErrorException("'CompaniesImpl'", "'addCouponsListToDB'", "Problem with coupon '" + coupon.getTitle() + "': description field can't be empty." +
                        " \nAdding failed.");
            }
            if (coupon.getImage().equals("")) {
                throw new UserErrorException("'CompaniesImpl'", "'addCouponsListToDB'", "Problem with coupon '" + coupon.getTitle() + "': image can't be empty." +
                        " \nAdding failed.");
            }
            if (coupon.getEndDate().before(new Date())) {
                throw new UserErrorException("'CompaniesImpl'", "'addCouponsListToDB'", "Problem with coupon '" + coupon.getTitle() + "': The end-date must be in a future date." +
                        " \nAdding failed.");
            }
            if (coupon.getPrice() < 0) {
                throw new UserErrorException("'CompaniesImpl'", "'addCouponsListToDB'", "Problem with coupon '" + coupon.getTitle() + "': The price can't be below zero." +
                        " \nAdding failed.");
            }
            couponsRepo.save(coupon);
        }
    }

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
    public void addCouponsListToCompany(int companyId, List<Coupon> coupons) throws UserErrorException {
        Company company = companiesRepo.findById(companyId).orElseThrow(() -> new UserErrorException("'companiesRepo'", "'findById'", "There is no company with that id."));
        for (Coupon coupon : coupons) {
            if (company.getCoupons().contains(coupon)) {
                throw new UserErrorException("'CompaniesImpl'", "'addCouponsListToCompany'", "The coupon '" + coupon.getTitle() + "' already exists at this company.");
            }
            if (coupon.getEndDate().before(new Date())) {
                throw new UserErrorException("'CompaniesImpl'", "'addCouponsListToCompany'", "The end date of coupon '" + coupon.getTitle() + "' had already passed.");
            }
        }
        company.setCoupons(coupons);
        companiesRepo.save(company);
    }

    /**
     * Receives a Coupon object from the user, checks if the coupon already exists in the
     * database, and if it does - updates it by the coupon object. Also checks validations.
     *
     * @param coupon - A coupon object. Given by the user.
     * @throws UserErrorException - The coupon does not exist, or validations problems.
     */
    @Override
    public void updateCoupon(Coupon coupon) throws UserErrorException {
        if (couponsRepo.findByCompanyIdAndCouponId(coupon.getCompanyId(), coupon.getCouponId()) == null) {
            throw new UserErrorException("'CompaniesImpl'", "'updateCoupon'", "You can't change the company-id and the coupon-id of the coupon." +
                    "Update failed.");
        }
        if (coupon.getCompanyId() != companyLoggedIn.getCompanyId()) {
            throw new UserErrorException("'CompaniesImpl'", "'updateCoupon'", "A company can't add a coupon with a company id " +
                    "of another company. Update failed.");
        }
        if (coupon.getDescription().equals("")) {
            throw new UserErrorException("'CompaniesImpl'", "'updateCoupon'", "The description must be at least 2 notes long." +
                    " \nUpdate failed.");
        }
        if (coupon.getImage().equals("")) {
            throw new UserErrorException("'CompaniesImpl'", "'updateCoupon'", "You must enter an image link." +
                    " \nUpdate failed.");
        }
        if (coupon.getEndDate().before(new Date())) {
            throw new UserErrorException("'CompaniesImpl'", "'updateCoupon'", "The end-date must be in a future date." +
                    " \nUpdate failed.");
        }
        if (coupon.getPrice() < 0) {
            throw new UserErrorException("'CompaniesImpl'", "'updateCoupon'", "The price can't be below zero." +
                    " \nUpdate failed.");
        }
        couponsRepo.saveAndFlush(coupon);
    }

    /**
     * Checks if a coupon exists in the database (by id), and if it does - delete it.
     *
     * @param couponId - The id of the coupon.
     * @throws UserErrorException - The coupon does not exist in the database.
     *                            - A company can't delete coupons of other companies.
     */
    @Override
    public void deleteCoupon(int couponId) throws UserErrorException {
        if (!couponsRepo.existsById(couponId)) {
            throw new UserErrorException("'CompaniesImpl'", "'deleteCoupon'", "The coupon you are trying to delete does not exist in the DB. " +
                    "Delete failed.");
        }
        Optional<Coupon> couponForDelete = couponsRepo.findById(couponId);
        if (companyLoggedIn.getCompanyId() != couponForDelete.get().getCompanyId()) {
            throw new UserErrorException("'CompaniesImpl'", "'deleteCoupon'", "You can't delete coupons of other companies. " +
                    "Delete failed.");
        }
        couponsRepo.deleteByCouponId(couponId);
        couponsRepo.deleteByCouponsId(couponId);
        couponsRepo.deleteById(couponId);
    }

    /**
     * Deletes coupons by end-dates that had already passed.
     *
     * @param date - A Date object.
     */
    @Override
    public void deleteCouponByDate(Date date) {
        couponsRepo.findByEndDateBefore(date).forEach(coupon -> {
            try {
                deleteCoupon(coupon.getCouponId());
            } catch (UserErrorException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Gets all the coupons of the logged-in company from the database and puts them
     * into a list.
     *
     * @return - A list of Coupon objects.
     */
    @Override
    public List<Coupon> getCompanyCoupons(int companyId) {
        return couponsRepo.findAllByCompanyId(companyId);
    }

    /**
     * Gets all the coupons of the logged-in company, under or equal to a given price, from the database,
     * and puts them into a list.
     *
     * @param maxPrice - The price's upper limit.
     * @return - A list of Coupon objects.
     */
    @Override
    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws UserErrorException {
        if (maxPrice < 0) {
            throw new UserErrorException("'CompaniesImpl'", "'getCompanyCouponsByMaxPrice'", "The maximum price can't be below 0.");
        }
        return companyLoggedIn.getCoupons().stream().filter(coupon -> coupon.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    /**
     * Gets all the coupons of the logged-in company by a specific category from the database
     * and puts them into a list.
     *
     * @param category - The category of the coupon.
     * @return - A list of Coupon objects.
     * @throws UserErrorException - There is no company with this id.
     */
    @Override
    public List<Coupon> getCompanyCouponsByCategory(Category category) {
        return couponsRepo.findAllByCompanyIdAndCategory(companyLoggedIn.getCompanyId(), category);
    }

    /**
     * Gets a specific coupon from the database by its id.
     *
     * @param couponId - The id of the coupon.
     * @return - A Coupon object.
     * @throws UserErrorException - If there is no coupon with that id.
     */
    public Coupon getOneCoupon(int couponId) throws UserErrorException {
        return couponsRepo.findById(couponId).orElseThrow(() -> new UserErrorException("'CompaniesImpl'", "'getOneCoupon'", "There is no coupon with that id."));
    }

    /**
     * Gets the logged-in company object from the database.
     *
     * @return - A Company object.
     */
    @Override
    public Company getCompanyDetails() {
        return companyLoggedIn;
    }

    /**
     * Gets all the coupons of the logged-in company from the database and puts them
     * into a list.
     *
     * @return - A list of Coupon objects of the logged-in company.
     */
    @Override
    public List<Coupon> getCompanyCoupons() {
        return companyLoggedIn.getCoupons();
    }

    /**
     * Gets back the Company object "companyLoggedIn".
     * "
     * @return - A Company object.
     */
    public Company getCompanyLoggedIn(){
        return companyLoggedIn;
    }

}