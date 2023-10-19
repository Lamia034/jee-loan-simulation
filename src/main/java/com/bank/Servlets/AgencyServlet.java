package com.bank.Servlets;

import com.bank.Entity.Agency;
import com.bank.Service.AgencyService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Agency")
public class AgencyServlet extends HttpServlet {

    @Inject
    private AgencyService agencyService;
    @Inject
    private Agency agency;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        agency.setCode("agency1");
        agency.setName("agency1");
        agency.setAddress("address1");
        agency.setPhone("123456789");
        agency.setEmployees(null);
        agency.setClients(null);
        agency.setEmployeeHistory(null);
        agencyService.create(agency);
        PrintWriter out=resp.getWriter();
        out.print("<h1>This is get method of my servlet</h1>");
    }
}