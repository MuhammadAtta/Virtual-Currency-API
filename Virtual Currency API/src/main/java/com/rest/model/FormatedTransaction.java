package com.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Muhammad Atta
 *
 */

public class FormatedTransaction {
    private BigDecimal balance;
    private String fromName;
    private String toName;
    private BigDecimal amount;
    private LocalDateTime time;

    public FormatedTransaction() {
        super();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}