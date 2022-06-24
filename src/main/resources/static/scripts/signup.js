window.addEventListener( 'load' , function () {

    const form = document.forms[0];
    const firstName = document.querySelector('#inputName');
    const lastName = document.querySelector('#inputLastName');
    const userName = document.querySelector('#inputUsername');
    const email = document.querySelector('#inputEmail');
    const password = document.querySelector('#inputPassword');
    const url = new URL('http://localhost:8080/auth/register');

    form.addEventListener( 'submit' , function( event ) {
       event.preventDefault();

        //mostrarSpinner();

        const payload = {
            firstName: firstName.value,
            lastName: lastName.value, 
            userName: userName.value,
            email: email.value,
            password: password.value
        };

        url.searchParams.append( "firstName" , payload.firstName );
        url.searchParams.append( "lastName" , payload.lastName );
        url.searchParams.append( "userName" , payload.userName );
        url.searchParams.append( "email" , payload.email );
        url.searchParams.append( "password" , payload.password );

        const settings = {
            method: 'POST',
            mode: 'no-cors',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        doSignUp( settings );

        //limpio los campos del formulario
        form.reset();
    });

    function doSignUp( settings ) {

        // console.log( "Lanzando la consulta a la API" );
        // console.log( "La URL: " + url );
        
        fetch( url , settings )
            .then( response => {
                // console.log(response);

                if ( response.ok != true ) {
                    alert( "Alguno de los datos es incorrecto." ) 
                }

                return response.json();
            })
            .then( data => {
                // console.log( "Promesa cumplida:" );
                // console.log( data );
            }).catch(err => {
                //Ocultamos el spinner en caso de error
                // ocultarSpinner();
                console.log("Promesa rechazada:");
                console.log(err);
            })
    };

    document.querySelector('.account')?.addEventListener('click', function( e ) {
        e.preventDefault();
        
        location.assign("../index.html");
    });

    document.querySelector('.no-security')?.addEventListener('click', function( e ) {
        e.preventDefault();

        location.assign("../views/home.html");
    });

});