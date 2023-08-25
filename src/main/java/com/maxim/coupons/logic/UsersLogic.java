package com.maxim.coupons.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxim.coupons.dal.IUsersDal;
import com.maxim.coupons.dto.SuccessfulLoginDetails;
import com.maxim.coupons.dto.User;
import com.maxim.coupons.dto.UserLoginData;
import com.maxim.coupons.entity.UserEntity;
import com.maxim.coupons.enums.ErrorType;
import com.maxim.coupons.exceptions.ApplicationException;
import com.maxim.coupons.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UsersLogic {

    private IUsersDal usersDal;
    private final int MINIMUM_PASSWORD_LENGTH = 6;

    @Autowired
    public UsersLogic(IUsersDal usersDal) {
        this.usersDal = usersDal;
    }

    public UsersLogic() {
    }

    public void addUser(User user) throws ApplicationException {
        validateUser(user);
        UserEntity userEntity = new UserEntity(user);
        this.usersDal.save(userEntity);
    }

    public void removeUser(int id) throws ApplicationException {
        usersDal.deleteById(id);
    }

    public List<User> getAllUsers() throws ApplicationException {
        return this.usersDal.getAllUsers();
    }

    public User getUser(int id) throws ApplicationException {
        return this.usersDal.getUser(id);
    }

    public void updateUser(User user) throws ApplicationException {
        validateUser(user);
        UserEntity userEntity = new UserEntity(user);
        this.usersDal.save(userEntity);
    }

    public List<User> getByCompanyId(int companyId) throws ApplicationException {
        return this.usersDal.getByCompanyId(companyId);
    }

    public String login(UserLoginData loginDetails) throws ApplicationException {
        validateUserLoginData(loginDetails);
        String userName = loginDetails.getUser();
        String password = loginDetails.getPassword();
        SuccessfulLoginDetails successfulLoginDetails = this.usersDal.login(userName, password);
        // If no user info was returned from the dal
        // we'll deduce that the LOGIN FAILED
        if (successfulLoginDetails == null) {
            throw new ApplicationException(ErrorType.FAILED_TO_LOGIN, "Failed to login,please try again later");
        }

        try {
            String token = JWTUtils.createJWT(successfulLoginDetails);
            return token;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateUserLoginData(UserLoginData loginDetails) throws ApplicationException {
        validateUserName(loginDetails.getUser());
        validatePassword(loginDetails.getPassword());
    }

    private void validateUser(User user) throws ApplicationException {
        validateUserName(user.getUserName());
        validatePassword(user.getPassword());
        validateCompanyId(user.getCompanyId());
    }

//    private void validateUserName(String userName) throws ApplicationException {
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
//                "[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//                "A-Z]{2,7}$";
//        Pattern pat = Pattern.compile(emailRegex);
//        if (userName == null || !(pat.matcher(userName).matches())) {
//            throw new ApplicationException(ErrorType.INVALID_EMAIL);
//        }
//
//    }

    public static void validateUserName(String emailAddress) throws ApplicationException {
        String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailFormat);
        if (!pat.matcher(emailAddress).matches()) {
            throw new ApplicationException(ErrorType.INVALID_EMAIL);
        }
    }

    private void validateCompanyId(Integer companyID) throws ApplicationException {
        if (companyID != null && companyID <= 0) {
            throw new ApplicationException(ErrorType.INVALID_COMPANY_ID, " Company Id must be positive number or null " + companyID);
        }
    }

    private void validatePassword(String password) throws ApplicationException {
        if (password.length() < MINIMUM_PASSWORD_LENGTH) {
            throw new ApplicationException(ErrorType.INVALID_PASSWORD);
        }
    }
}
