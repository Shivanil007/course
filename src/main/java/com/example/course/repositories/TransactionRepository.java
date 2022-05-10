package com.example.course.repositories;

import com.example.course.datamodels.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findTransactionByTransactionId (int transactionId);

    Transaction findTransactionByCourseId(int courseId);

    List<Transaction> findTransactionsByUsername(String username);

}
