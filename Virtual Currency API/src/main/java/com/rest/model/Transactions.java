package com.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Muhammad Atta
 *
 */

@Entity
public class Transactions implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;

    @OneToOne
    private User from;

    @ManyToOne
    private User to;

    LocalDateTime time;

    public Transactions() { super(); }
    @JsonCreator
    public Transactions (@JsonProperty("id") Long id ) {
        this.id = id;
    }

    public Transactions(BigDecimal amount, User from, User to, LocalDateTime time) {
        super();
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.time = time;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public User getFrom() {
        return from;
    }
    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }
    public void setTo(User to) {
        this.to = to;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", amount=" + amount +
                ", from=" + from +
                ", to=" + to +
                ", time=" + time +
                '}';
    }
}