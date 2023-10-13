const module = document.querySelector(".module");
const addBtn = document.querySelector(".add-btn");
let toggle = false;
const close = document.querySelector(".close");
const formDelete = document.querySelector(".form-delete");
addBtn.addEventListener("click", ()=>{
    toggle = true;
    module.style.display = "flex";
});

close.addEventListener("click", ()=>{
    toggle = false;
    module.style.display = "none";
});

formDelete.addEventListener("submit", (e)=>{
    e.preventDefault();
    Swal.fire({
        title: "supprimer l'employee?",
        showCancelButton: true,
        confirmButtonText: 'supprimer',
    }).then((result) => {
        if (result.isConfirmed) {
            e.target.submit();
        }
    })
});