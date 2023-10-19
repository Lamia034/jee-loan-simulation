package com.bank.Servlets;

import com.bank.Entity.Client;
import com.bank.Enum.CreditStatus;
import com.bank.Service.AgencyService;
import com.bank.Service.ClientService;
import com.bank.Service.CreditService;
import com.bank.Service.EmployeeService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.bank.Entity.Credit;

import java.io.IOException;

@WebServlet("/")
public class CreditServlet extends HttpServlet {
    @Inject
    private Credit credit;
    @Inject
    private ClientService clientService;
    @Inject
    private EmployeeService employeeService;
    @Inject
    private CreditService creditService;
    @Inject
    private AgencyService agencyService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("clients", clientService.findAllClients());
        request.setAttribute("agencies", agencyService.find());
        request.setAttribute("emploies", employeeService.findAll());
        System.out.println(agencyService.find().size());
        request.getRequestDispatcher("/credit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            credit.setLoanTax(
                    Double.parseDouble(request.getParameter("tax"))
            );
            credit.setStatus(CreditStatus.PENDING);
            credit.setDuration(
                    Integer.parseInt(request.getParameter("duration"))
            );
            credit.setValue(
                    Integer.parseInt(
                            request.getParameter("value")
                    )
            );
            credit.setClient(
                    clientService.findClientByCode(
                            Integer.parseInt(request.getParameter("client"))
                    )
            );
            credit.setEmployee(
                    employeeService.findByRegistrationNbr(
                            Integer.parseInt(request.getParameter("client"))
                    )
            );
            if(creditService.addCredit(credit) != null){
                request.getRequestDispatcher("/dash.jsp").forward(request, response);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}