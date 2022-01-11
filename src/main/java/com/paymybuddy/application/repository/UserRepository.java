package com.paymybuddy.application.repository;

import com.paymybuddy.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, CrudRepository<User, String> {

    User findUserByEmail(@Param("email") String email);

    @Query(value="SELECT password FROM user WHERE Email = :email", nativeQuery = true)
    String findUserPassword(@Param("email") String email);

    @Modifying
    @Query(value ="UPDATE User user SET account =:transactionValue WHERE user.Email =:email", nativeQuery=true)
    void updateUserAccount(@Param("transactionValue") double account, @Param("email") String email);
}
