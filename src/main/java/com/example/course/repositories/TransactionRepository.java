package com.example.course.repositories;

import com.example.course.datamodels.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findTransactionByTransactionId (int transactionId);

    Transaction findTransactionByCourseId(int courseId);

}
