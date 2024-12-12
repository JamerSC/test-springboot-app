package com.springboot.fxn.testing.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientAccountDto {

    private Long id;
    private String name;
    private String email;
    private Set<PettyCashDto> pettyCash;
}
