package com.rest.service;

import com.rest.model.Transactions;

import java.util.List;

public interface TransferServiceInterface {

    void saveTransactions(Transactions transactions);

    List<Transactions> getAllTransactionsFrom(String id);

    List<Transactions> getAllTransactionsTo(String id);

}
