package com.coin.analys.backend.account.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AccountDto {

    private final String email;
    private final String password;

    public AccountDto(String email, String password){
        this.email = email;
        this.password = password;
    }

    public static AccountDto of(String email, String password){
        return new AccountDto(email, password);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        AccountDto accountDto = (AccountDto) obj;
        return email.equals(accountDto.email);
    }
}
