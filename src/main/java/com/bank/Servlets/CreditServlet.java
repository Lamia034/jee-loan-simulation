package com.bank.Servlets;

import com.bank.Entity.Client;
import com.bank.Enum.CreditStatus;
import com.bank.Exception.InsertionException;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet( name= "CreditServlet" ,urlPatterns = {"/","/ListCredits","find-date","/find-status","/get-credit-details"})
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
    private String requestURL;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.requestURL = request.getServletPath();

        switch (this.requestURL) {
            case "/":
                request.setAttribute("clients", clientService.findAllClients());
                request.setAttribute("agencies", agencyService.find());
                request.setAttribute("emploies", employeeService.findAll());
                request.getRequestDispatcher("/credit.jsp").forward(request, response);
                break;  // Add this break statement to exit the switch-case

            case "/ListCredits":
                try {
                    request.setAttribute("credits", creditService.findAll());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                request.getRequestDispatcher("/ListCredits.jsp").forward(request, response);
                break;


                case "/find-date":

                    try {
                        request.setAttribute("credits", creditService.findByDate(LocalDate.parse((request.getParameter("date")))));
                        request.getRequestDispatcher("/ListCreditsByDate.jsp").forward(request, response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
            case "/find-status":

                try {
                    request.setAttribute("credits", creditService.findByStatus((request.getParameter("status"))));
                    request.getRequestDispatcher("/ListCreditsByStatus.jsp").forward(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/get-credit-details":
                int creditId = Integer.parseInt(request.getParameter("creditId"));
                Credit credit = null;
                try {
                    credit = creditService.findById(creditId);

                    JSONObject json = new JSONObject();
                    json.put("amount", credit.getValue());
                    json.put("duration", credit.getDuration());
                    json.put("remark", credit.getRemark());
                    json.put("client", credit.getClient().getCode());
                    json.put("employee", credit.getEmployee().getRegistrationNbr());
                    json.put("agency", credit.getAgency().getCode());


                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    response.getWriter().write(json.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

    
                break;
            }

        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.requestURL = request.getServletPath();


        try {
            System.out.println("in");
            System.out.println("duration:" + request.getParameter("duration"));
            System.out.println("amount:" + request.getParameter("amount"));
            credit.setLoanTax(Double.parseDouble(request.getParameter("tax")));
            System.out.println("here is the tax:" +credit.getLoanTax());
            credit.setStatus(CreditStatus.PENDING);
            System.out.println(credit.getStatus());
            credit.setDuration(Integer.parseInt(request.getParameter("duration")));
            System.out.println("here is f dusration:" +credit.getDuration());
            credit.setValue(Integer.parseInt(request.getParameter("amount")));
            System.out.println(credit.getValue());
            System.out.println(request.getParameter("client"));
            credit.setClient(clientService.findClientByCode(Integer.parseInt(request.getParameter("client"))));
            System.out.println("here is the client" +credit.getClient());
            System.out.println("remark:" + request.getParameter("remark"));
            credit.setRemark(request.getParameter("remark"));
            System.out.println(credit.getRemark());
            credit.setEmployee(employeeService.findByRegistrationNbr(Integer.parseInt(request.getParameter("employee"))));
            credit.setAgency(agencyService.findByCode(request.getParameter("agency")));
            System.out.println("credit===>");
            boolean result = creditService.addCredit(credit);
            System.out.println("client servlet: " + result);
            if (result) {
                request.setAttribute("created", true);
            } else {
                request.setAttribute("created", false);
            }

            request.setAttribute("credits", creditService.findAll());
            request.getRequestDispatcher("/ListCredits.jsp").forward(request, response);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }    }





    }

/*
@WebServlet("/Credit")

public class CreditServlet extends HttpServlet{
    @Inject
    private CreditService creditService;
    @Inject
    private Credit credit;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Credit> credits = null;
        try {
            credits = creditService.getAllCredits();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("credits", credits);
        request.getRequestDispatcher("ListCredits.jsp").forward(request, response);
    }
/*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            int value = Integer.parseInt(request.getParameter("value"));
            int duration = Integer.parseInt(request.getParameter("duration"));
            String remark = request.getParameter("remark");
            double loanTax = Double.parseDouble(request.getParameter("loanTax"));
            CreditStatus status = CreditStatus.valueOf(request.getParameter("status"));
            String agency_code = request.getParameter("agency_code");
            String client_code = request.getParameter("client_code");
            int credit_id = Integer.parseInt(request.getParameter("credit_id"));
            int employee_registrationnbr = Integer.parseInt(request.getParameter("employee_registrationnbr"));

            Credit credit = new Credit();
            credit.setValue(value);
            credit.setDuration(duration);
            credit.setRemark(remark);
            credit.setStatus(status);
            credit.setLoanTax(loanTax);
            credit.setId(credit_id);
          //  credit.setAgency(Agency);




            Credit addedCredit = creditService.addCredit(credit);

            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (Exception e) {

            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
*/