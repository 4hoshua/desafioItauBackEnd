package com.desafio.demo.repositories;

import com.desafio.demo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //now() + horário de ajuste de fuso
    @Query(value = "select valor from transacoes where createdat > (now() + interval '3 hours') - (:time * interval '1 second')", nativeQuery = true)
    List<Double> getLatestRecords(@Param("time") int time);

}
