
function showSpinner() {
    const spinElement = document.createElement("div");
    spinElement.classList.add("my-5", "spin");

    // MAIN ELEMENT
    const main = document.getElementById("main");
    const formContainer = document.getElementById("form-container");

    spinElement.innerHTML = `<div class="d-flex justify-content-center">
        <div class="spinner-border text-light" role="status">
        <span class="visually-hidden">Loading...</span>
        </div>
        </div>`;    

    // HIDE FORM CONTAINER
    formContainer.classList.add("d-none");
    // SHOW SPINNER
    main.appendChild( spinElement );
    return;
}

function suppressSpinner() {
    const spinElement = document.querySelector(".spin");

    // MAIN ELEMENT
    const main = document.getElementById("main");
    const formContainer = document.getElementById("form-container");

    // HIDE SPINNER
    main.removeChild( spinElement );
    // SHOW FORM CONTAINER
    formContainer.classList.remove("d-none");
    return;
}