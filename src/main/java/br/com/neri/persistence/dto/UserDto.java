package br.com.neri.persistence.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank(message = "Opa! campo obrigatório.")

    String name;
    Integer age;

    String phone;
    String address;


    public String getName(){
        return name;
    }

    public void setName(String name){
    this.name = name;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
