<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/assets/header.jsp" %>
<div class="w-[400px] bg-white p-6 flex flex-col gap-5">
    <p class="font-bold">Loan Calculator</p>
    <div class="flex flex-col gap-4">
        <label for="amount" class="font-bold text-[12px]">Loan Amount</label>
        <input type="number" min="1000" name="amount" id="amount" class="border border-[2px] border-gray-300 rounded-[4px] p-1 w-full ">
    </div>
    <div class="flex flex-col gap-3">
        <label for="months" class="font-bold flex w-full justify-between items-center text-[12px]">
            <span>months</span>
            <span class="text-[13px] mt-3 months">4</span>
        </label>
        <input type="range" min="4" max="24" name="months" id="months" class="range-sm range-input">
        <div class="flex w-full justify-between items-center text-gray-400 font-light text-[12px]">
            <span>4</span>
            <span>24</span>
        </div>
    </div>
</div>
<div class="w-[400px] bg-white p-6 flex flex-col gap-3">
    <p class="font-bold">Summary</p>
    <p class="w-full bg-gray-300 text-right font-bold px-3 py-1">
        total
    </p>
    <div class="w-full flex justify-between items-center  py-2 border-b border-1 border-gray-300 border-dashed text-[12px] font-medium">
        <span>Loan amount</span>
        <span class="amount">0 dh</span>
    </div>
    <div class="w-full flex justify-between items-center  py-2 border-b border-1 border-gray-300 border-dashed text-[12px] font-medium">
        <span>interest rate</span>
        <span >12%</span>
    </div>
    <div class="w-full flex justify-between items-center  py-2 border-b border-1 border-gray-300 border-dashed text-[12px] font-medium">
        <span>months</span>
        <span class="months">24</span>
    </div>
    <div class="w-full flex justify-between items-center  py-2 border-t border-1 border-gray-300 mt-7">
        <span>total</span>
        <span class="total">0 dh</span>
    </div>
    <button class="w-full bg-[#01B062] text-[15px] rounded-md p-2 text-white">next</button>
</div>
<script src="${pageContext.servletContext.contextPath}/src/script.js" type="text/javascript"></script>
<%@ include file="/WEB-INF/assets/footer.jsp" %>