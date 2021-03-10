package com.rest.service;

import com.rest.exceptions.BusinessException;
import com.rest.model.Transactions;
import com.rest.model.User;
import com.rest.repository.TransactionsRepository;
import com.rest.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransferService implements TransferServiceInterface {

    private static final AtomicLong counter = new AtomicLong();
    private static List<Transactions> transactions;

    TransactionsRepository transactionsRepository;
    UserRepository userRepository;

    public TransferService(TransactionsRepository transactionsRepository, UserRepository userRepository) {
        this.transactionsRepository = transactionsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean transfer(Long from,Long to, BigDecimal amount) throws BusinessException {
        try {
            if (from == to) {
                throw new BusinessException("It's illegal to transfer from your account to yourself.");
            }

            Optional<User> fromUser = userRepository.findById(from);
            Optional<User> toUser = userRepository.findById(to);

            if (!fromUser.isPresent() && !toUser.isPresent()) {
                throw new BusinessException("From Account or To account is not valid.");
            }
            if (fromUser.get().getVirtualCurrency().compareTo(amount) <0) {
                throw new BusinessException("There is no enough money.");
            }
            // Transferring money
            Transactions transaction = new Transactions();
            transaction.setFrom(fromUser.get());
            transaction.setTo(toUser.get());
            transaction.setAmount(amount);
            transaction.setTime(LocalDateTime.now());

            transactionsRepository.save(transaction);
            // Subtracting from source
            fromUser.get().setVirtualCurrency(fromUser.get().getVirtualCurrency().subtract(amount));
            userRepository.save(fromUser.get());
            // Adding to destination
            toUser.get().setVirtualCurrency(toUser.get().getVirtualCurrency().add(amount));
            userRepository.save(toUser.get());

            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void saveTransactions(Transactions transaction) {
            transaction.setId(counter.incrementAndGet());
            transactions.add(transaction);
        }



    @Override
    public List<Transactions> getAllTransactionsFrom(String userId) {
        List<Transactions> transactions = new ArrayList<>();
        transactionsRepository.findByFrom(userId).forEach(transactions::add);
        System.out.println("getAllTransactions .... " + transactions);
        return  transactions;
    }

    @Override
    public List<Transactions> getAllTransactionsTo(String userId) {
        List<Transactions> transactions = new ArrayList<>();
        transactionsRepository.findByTo(userId).forEach(transactions::add);
        System.out.println("getAllTransactions .... " + transactions);
        return  transactions;
    }

    //find all
    public List<Transactions> findAll(){
        return (List<Transactions>) transactionsRepository.findAll();
    }





}