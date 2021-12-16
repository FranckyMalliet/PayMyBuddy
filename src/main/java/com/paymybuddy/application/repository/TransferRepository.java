package com.paymybuddy.application.repository;

import com.paymybuddy.application.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer>, CrudRepository<Transfer, Integer> {

    @Query(value="FROM Transfer t WHERE t.correspondence.idCorrespondence = :correspondence")
    List<Transfer> findAllTransferFromCorrespondence(@Param("correspondence") int correspondence);
}
