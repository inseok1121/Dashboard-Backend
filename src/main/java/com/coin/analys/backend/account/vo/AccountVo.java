package com.coin.analys.backend.account.vo;

import com.coin.analys.backend.account.entity.Account;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AccountVo {

    private final String email;
    private final String password;

    public AccountVo(String email, String password){
        this.email = email;
        this.password = password;
    }

    public static AccountVo of(String email, String password){
        return new AccountVo(email, password);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        AccountVo accountVo = (AccountVo) obj;
        return email.equals(accountVo.email);
    }
}
