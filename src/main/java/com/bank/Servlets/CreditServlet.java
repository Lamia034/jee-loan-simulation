package com.bank.Servlets;

import com.bank.Entity.Agency;
import com.bank.Entity.Credit;
import com.bank.Enum.CreditStatus;
import com.bank.Service.AgencyService;
import com.bank.Service.CreditService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
    }*/

}
