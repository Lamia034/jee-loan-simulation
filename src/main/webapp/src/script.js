const amountInput = document.querySelector("input[type='number']");
const amountDisplayer = document.querySelector(".amount");
const monthsRange = document.querySelector(".range-input");
const monthsDisplayer = document.querySelectorAll(".months");
const totalDisplayer = document.querySelector(".total");
total = 0;
const interest = 7;
let month = 4;
monthsRange.addEventListener("input", (e)=>{
    for(let tmp of monthsDisplayer){
        month = Number.parseInt(e.target.value);
        tmp.innerText = month;
    }
    if(total >= 1000 )
        totalDisplayer.innerText = calcule(total) + " dh";

});

amountInput.addEventListener("input", (e)=>{
    total = Number.parseInt(e.target.value);
    amountDisplayer.innerText = total + " dh";
    totalDisplayer.innerText = calcule(total) + " dh";
});

const calcule = (value)=>{
    let a = value * (interest /12);
    let b = 1 - Math.pow((1+(interest/12)), -month);
    return a/b;
};