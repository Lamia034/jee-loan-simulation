package com.bank.DAO;

import com.bank.Entity.AgencyEmployee;

import java.time.LocalDate;
import java.util.List;

public interface AgencyEmployeeDAO {
    public boolean create(int registrationNbr, String agency_code, LocalDate date);
    public List<AgencyEmployee> find();
}
