package com.tuorjp.financial_helper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationDTO {
    public String token;
    public String user;
}
