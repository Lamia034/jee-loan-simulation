package com.bank.Entity;

import com.bank.Enum.CreditStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Credit {
    public static final double TAUX = 0.12;
    @NonNull
    @Id
    @GeneratedValue
    private int id;
    @NonNull
    private int value;
    @NonNull
    @Enumerated(EnumType.STRING)
    private CreditStatus status;
    @NonNull
    private int duration;
    @NonNull
    private String remark;
    @NonNull
    private double loanTax;
    @OneToOne
    private Client client;
    @NonNull
    @ManyToOne
    private Agency agency;
    @NonNull
    @ManyToOne
    private Employee employee;
}