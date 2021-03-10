package com.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Muhammad Atta
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = 5800794038601763720L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String firstName;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastName;

    @NotNull
    private String userName;

    @NotNull
    @Column(unique = true)
    private String email;


    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BigDecimal virtualCurrency;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Phone> phones;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date last_login;


    public User() {		super();}

    public User(@NotNull String firstName, @NotNull String lastName,
                @NotNull String email, @NotNull String password,
                @NotNull String userName, @NotNull BigDecimal virtualCurrency,

                @NotNull List<Phone> phones) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.virtualCurrency = virtualCurrency;
        this.phones = phones;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(BigDecimal virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", virtualCurrency=" + virtualCurrency +
                ", phones=" + phones +
                ", created_at=" + created_at +
                ", last_login=" + last_login +
                '}';
    }
}

