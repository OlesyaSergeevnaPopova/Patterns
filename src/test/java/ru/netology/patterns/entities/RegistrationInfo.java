package ru.netology.patterns.entities;


import lombok.Data;

@Data
public class RegistrationInfo {
    public final String city;
    public final String name;
    public final String phone;
//    public final String cardNumber;
}