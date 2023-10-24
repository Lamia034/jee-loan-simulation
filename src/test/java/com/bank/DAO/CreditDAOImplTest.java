package com.bank.DAO;
import com.bank.Entity.Credit;
import com.bank.Enum.CreditStatus;
import com.bank.Service.AgencyService;
import com.bank.Service.ClientService;
import com.bank.Service.CreditService;
import com.bank.Service.EmployeeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

@ApplicationScoped
class CreditDAOImplTest {
    private final AgencyService agencyService;
    private final EmployeeService employeeService;
    private final CreditService creditService;
    private final ClientService clientService;

    @Inject
    public CreditDAOImplTest(AgencyService agencyService, EmployeeService employeeService, CreditService creditService, ClientService clientService) {
        this.agencyService = agencyService;
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.creditService = creditService;
    }
    @Test
    void createCredit() {
        try {
            Credit credit = new Credit();

            credit.setValue(555);
            credit.setStatus(CreditStatus.PENDING);
            credit.setDuration(24);
            credit.setRemark("Sample remark");
            credit.setLoanTax(Credit.TAUX);

            // Set the client, agency, and employee using your service methods
            credit.setClient(clientService.findClientByCode(1));
            credit.setAgency(agencyService.findByCode("agency1"));
            credit.setEmployee(employeeService.findByRegistrationNbr(1));

            // Set modification_date and modification_time using LocalDate and LocalTime objects
            credit.setModification_date(LocalDate.of(2009, 6, 22));
            credit.setModification_time(LocalTime.of(11, 12, 8));

            Credit creditAdded = creditService.addCredit(credit);

            Assertions.assertNotNull(creditAdded);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
