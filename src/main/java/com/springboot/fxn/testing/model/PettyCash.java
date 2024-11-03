package com.springboot.fxn.testing.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "petty_cash")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "accounts")
public class PettyCash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "activity_description")
    private String activityDescription;

    @Column(name = "activity_category")
    private String activityCategory;

    @Column(name = "soa_category")
    private String soaCategory;

    @Column(name = "amount")
    private Double amount;

    @ManyToMany
    @JoinTable(
            name = "petty_cash_accounts",
            joinColumns = @JoinColumn(name = "petty_cash_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    //@JsonBackReference
    private Set<Account> accounts;
}
