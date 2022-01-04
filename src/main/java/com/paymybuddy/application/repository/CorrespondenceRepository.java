package com.paymybuddy.application.repository;

import com.paymybuddy.application.model.Correspondence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorrespondenceRepository extends JpaRepository<Correspondence, Integer>, CrudRepository<Correspondence, Integer> {

    //READ
    @Query(value="FROM Correspondence c WHERE c.user.email= :email")
    List<Correspondence> findAllCorrespondenceUser(@Param("email") String email);

    @Query(value="FROM Correspondence c WHERE c.user.email = :email and c.emailCorrespondence = :emailCorrespondence")
    Correspondence findUserCorrespondence(@Param("email") String email, @Param("emailCorrespondence") String emailCorrespondence);
}
