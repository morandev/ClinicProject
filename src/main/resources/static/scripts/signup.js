//TODO: Limpiar los logs y reemplazar comentarios a ingles

window.addEventListener( 'load' , function () {

    const form = document.forms[0];
    const firstName = document.querySelector('#inputName');
    const lastName = document.querySelector('#inputLastName');
    const userName = document.querySelector('#inputUsername');
    const email = document.querySelector('#inputEmail');
    const password = document.querySelector('#inputPassword');
    const url = new URL('http://localhost:8080/authenticate/register');

    form.addEventListener( 'submit' , function( event ) {
       event.preventDefault();

        // showSpinner();

        const payload = {
            name: firstName.value,
            lastName: lastName.value, 
            username: userName.value,
            email: email.value,
            password: password.value
        };

        const settings = {
            method: 'POST',
            body: JSON.stringify( payload ),
            headers: {
                'Content-Type': 'application/json'
            }
        };

        doSignUp( settings );

        // CLEAR REGISTER FORM
        form.reset();
    });

    function doSignUp( settings ) {
        
        fetch( url , settings )
            .then( response => {

                if ( response.ok != true ) {
                    alert( "Error" ) 
                }

                return response.json();
            })
            .then( data => {

                if ( data.jwt ) {
                    // suppressSpinner();
                    // REDIRECT
                    location.replace('index.html');
                }
            }).catch(err => {
                // suppressSpinner();
                console.log( err );
            })
    };

    document.querySelector('.account')?.addEventListener('click', function( e ) {
        e.preventDefault();
        
        location.assign(`${location.origin}`);
    });

});