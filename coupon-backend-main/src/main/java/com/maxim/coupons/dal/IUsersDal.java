package com.maxim.coupons.dal;

import com.maxim.coupons.dto.SuccessfulLoginDetails;
import com.maxim.coupons.dto.User;
import com.maxim.coupons.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsersDal extends CrudRepository<UserEntity, Integer> {
    @Query("SELECT new com.maxim.coupons.dto.User (u.id, u.userName, u.password, u.userType, u.companies.id) FROM UserEntity u")
    List<User> getAllUsers();

    @Query("SELECT new com.maxim.coupons.dto.User (u.id, u.userName, u.password, u.userType, u.companies.id) FROM UserEntity u WHERE u.id = :id")
    User getUser(@Param("id") int id);

    @Query("SELECT new com.maxim.coupons.dto.User (u.id, u.userName, u.password, u.userType, u.companies.id) FROM UserEntity u WHERE u.companies.id =:id")
    List<User> getByCompanyId(@Param("id") int id);

    @Query("SELECT new com.maxim.coupons.dto.SuccessfulLoginDetails (u.id, u.userType, u.companies.id) FROM UserEntity u WHERE u.userName = :userName AND u.password = :password ")
    SuccessfulLoginDetails login(@Param("userName") String userName, @Param("password") String password);
}
