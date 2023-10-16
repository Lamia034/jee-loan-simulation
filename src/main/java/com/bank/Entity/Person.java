package com.bank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public abstract class Person {
    @NonNull
    protected String firstName;
    @NonNull
    protected String lastName;
    @NonNull
    protected LocalDate birthDay;
    @NonNull
    protected String phone;
    @NonNull
    protected String address;
}