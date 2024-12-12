package com.springboot.fxn.testing.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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
    private Set<ClientAccount> accounts;
}
