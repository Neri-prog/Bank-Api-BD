package br.com.neri.persistence.dto;

public class AccountDto {
    Integer accountType ;
    Double value;
    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


}
