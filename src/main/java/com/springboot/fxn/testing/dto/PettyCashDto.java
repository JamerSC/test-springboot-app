package com.springboot.fxn.testing.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PettyCashDto {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String activityDescription;
    private String activityCategory;
    private String soaCategory;
    private Double amount;
    private Set<Long> accountIds; // Custom mapping to store account IDs'
    //private Set<Long> accountIds; // Keep accountIds for IDs
    private Set<String> accountDetails; // Add a new field to store account details (like name and email)
}
