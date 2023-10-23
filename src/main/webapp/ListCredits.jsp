<%--
  Created by IntelliJ IDEA.
  User: adm
  Date: 17/10/2023
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ include file="/WEB-INF/assets/header.jsp" %>
<div class="flex justify-around mt-3">


    <form action="${pageContext.servletContext.contextPath}/find-date"  method="get" class="relative max-w-sm">
        <div class="absolute inset-y-0 left-0 flex items-center pl-3.5 pointer-events-none">
            <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                <path d="M20 4a2 2 0 0 0-2-2h-2V1a1 1 0 0 0-2 0v1h-3V1a1 1 0 0 0-2 0v1H6V1a1 1 0 0 0-2 0v1H2a2 2 0 0 0-2 2v2h20V4ZM0 18a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V8H0v10Zm5-8h10a1 1 0 0 1 0 2H5a1 1 0 0 1 0-2Z"/>
            </svg>
        </div>
        <input datepicker type="date" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Select date">
   <button type="submit">x</button>
    </form>

<form action="${pageContext.servletContext.contextPath}/find-status"  method="get">
    <select data-te-select-init class="rounded">
        <option value="1">All</option>
        <option value="2">Pending&</option>
        <option value="2">Accepted</option>
        <option value="3">Refused</option>
    </select>
    <button type="submit">x</button>
</form>
</div>
        <!-- Table -->
        <div class=" bg-white shadow-lg pt-5 m-3 rounded-sm border border-gray-200">
            <header class="px-5 py-4 border-b border-gray-100">
                <h2 class="font-semibold text-gray-800">Credits</h2>
            </header>
            <div class="p-3">
                <div class="overflow-x-auto">
                    <table class="table-auto w-full">
                        <thead class="text-xs font-semibold uppercase text-gray-400 bg-gray-50">
                        <tr>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-left">Client Name</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-left">Client Code</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-left">Employee number</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-center">Credit Number</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-center">Agency Code</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-center">Credit Value</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-center">Remark</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-center">Status</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-center">modification date/time</div>
                            </th>

                        </tr>
                        </thead>
                        <tbody class="text-sm divide-y divide-gray-100">
<c:forEach items="${credits}" var="credit">
                        <tr  data-credit-id="${credit.id}">
                            <td class="p-2 whitespace-nowrap">
                                <div class="flex items-center">
                                    <div class="w-10 h-10 flex-shrink-0 mr-2 sm:mr-3"><img class="rounded-full" src="https://raw.githubusercontent.com/cruip/vuejs-admin-dashboard-template/main/src/images/user-36-05.jpg" width="40" height="40" alt="Alex Shatov"></div>
                                    <div class="text-left pr-2">${credit.client.firstName}</div>
                                    <div class="text-left">${credit.client.lastName}</div>
                                </div>
                            </td>

                            <td class="p-2 whitespace-nowrap">
                                <div class="text-left">${credit.client.code}</div>
                            </td>
                            <td class="p-2 whitespace-nowrap">
                                <div class="text-left font-medium text-green-500">${credit.employee.registrationNbr}</div>
                            </td>
                            <td class="p-2 whitespace-nowrap">
                                <div class="text-lg text-center">${credit.id}</div>
                            </td>
                            <td class="p-2 whitespace-nowrap">
                                <div class="text-lg text-center">${credit.agency.code}</div>
                            </td>
                            <td class="p-2 whitespace-nowrap">
                                <div class="text-lg text-center">${credit.value}</div>
                            </td>

                            <td class="p-2 whitespace-nowrap">
                                <div class="text-lg text-center">${credit.remark}</div>
                            </td>
                            <td class="p-2 whitespace-nowrap">
                                <div class="text-lg text-center">${credit.status} ><form action="${pageContext.servletContext.contextPath}/update-status" method="post">
                                    <input type="hidden" name="creditId" value="${credit.id}">
                                    <select name="creditStatus" data-te-select-init class="rounded">
                                        <option value="1">Pending</option>
                                        <option value="2">Accepted</option>
                                        <option value="3">Refused</option>
                                    </select>
                                    <button type="submit">Update</button>
                                </form>
                                </div>
                            </td>
                            <td class="p-2 whitespace-nowrap">
                                <div class="text-lg text-center">${credit.modification_date} ${credit.modification_time}</div>
                            </td>
                        </tr>
</c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

<div id="popup" class="w-[500px] rounded-sm p-4 bg-white form3 hidden">
    <form action="${pageContext.servletContext.contextPath}/" method="POST" class="w-full flex flex-col justify-center items-start gap-3">
        <p class="text-center w-full font-bold">Formulaire d'ajout</p>
        <div class="flex flex-col justify-center items-start w-full">
            <label for="amount" class="font-semibold">Valeur</label>
            <input type="text" id="amount" name="amount" value="12" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
        </div>
        <div class="flex flex-col justify-center items-start w-full">
            <label for="duration" class="font-semibold">Mensualité</label>
            <input type="text" id="duration" name="duration" value="12" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
        </div>


        <div class="flex flex-col justify-center items-start w-full">
            <label for="remark" class="font-semibold">Remarque</label>
            <textarea required name="remark" id="remark" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1"></textarea>
        </div>
        <input hidden value="2" name="tax">
        <div class="flex flex-col justify-center items-start w-full">
            <label for="client" class="font-semibold">Client</label>
            <select required id="client" name="client" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
                <c:forEach items="${clients}" var="client">
                    <option value="${client.code}" name="client">${client.firstName} ${client.lastName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="flex flex-col justify-center items-start w-full">
            <label for="employee" class="font-semibold">Employee</label>
            <select required id="employee" name="employee" class=" p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
                <c:forEach items="${emploies}" var="employee">
                    <option value="${employee.registrationNbr}">${employee.firstName} ${employee.lastName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="flex flex-col justify-center items-start w-full">
            <label for="agency" class="font-semibold">Agence</label>
            <select required id="agency" name="agency" class="p-2 w-full order border-[2px] border-gray-300 rounded-[4px] mt-1">
                <c:forEach items="${agencies}" var="agency">
                    <option value="${agency.code}">${agency.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="flex w-full justify-center items-center gap-2">
            <button  class="w-full bg-[#01B062] p-4 text-white rounded-md">Ajouter crédit</button>
            <span class="w-full bg-gray-300 p-4 text-black rounded-md text-center cursor-pointer backTo">Revenir</span>
        </div>
    </form>
</div>


<script>
    if(${created})
        Swal.fire({
            title: "credit ajouter avec success",
            icon: "success",
            showCancelButton: true
        })
    else
        Swal.fire({
            title: "un des valeur est invalide",
            icon: "error",
            showCancelButton: true
        })
</script>
<script>
    // Initialization for ES Users
    import { Select, initTE } from "tw-elements";
    initTE({ Select });
</script>
<script src="../path/to/flowbite/dist/datepicker.js"></script>