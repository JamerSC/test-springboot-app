package com.springboot.fxn.testing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PettyCashDto {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String activityDescription;
    private String activityCategory;
    private String soaCategory;
    private Double amount;
    private Set<Long> accountIds;
}
