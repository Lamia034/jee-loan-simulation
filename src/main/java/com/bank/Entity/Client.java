package com.bank.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.*;
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Client extends Person{
    @NonNull
    @Id
    private String code;
    @NonNull
    @ManyToOne
    public Employee employee;
    @NonNull
    @ManyToOne
    private Agency agency;



}
