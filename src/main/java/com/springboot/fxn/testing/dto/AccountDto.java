package com.springboot.fxn.testing.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountDto {

    private Long id;
    private String name;
    private String email;
}
