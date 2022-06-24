window.addEventListener( 'load' , function () {

    const form = document.querySelector("#form-one");
    const username = document.querySelector('#username')
    const password = document.querySelector('#pwd')
    const url = new URL('http://localhost:8080/auth/login');

    form.addEventListener( 'submit' , function( event ) {
        event.preventDefault();

        //Aquí podemos mostrar el spinner 
        // mostrarSpinner();


        const payload = {
            username: username.value,
            password: password.value
        };

        url.searchParams.append( "username", payload.username );
        url.searchParams.append( "password", payload.password );

        const settings = {
            method: 'POST',
            mode: 'no-cors'
        };

        //lanzamos la consulta de login a la API
        doLogin( settings );

        //limpio los campos del formulario
        form.reset();
    });

    function doLogin( settings={} ) {
        // console.log( "Lanzando la consulta a la API..." );
        // console.log( "La URL: " + url );

        fetch( url , settings )
            .then( response => {
                console.log( response );

                if ( response.ok != true ) {
                    alert( "Username or Password incorrect" )
                }

                return response;
            })
            .then( data => {
                // console.log( "Promesa cumplida:" );
                // console.log( { data } );

            }).catch( err => {
                //Ocultamos el spinner en caso de error
                // ocultarSpinner();
                console.log( "Promesa rechazada:" );
                console.log( err );
            })
    };

    document.querySelector('.no-account')?.addEventListener('click', function( e ) {
        e.preventDefault();

        location.assign("../register.html");
    });

    document.querySelector('.no-security')?.addEventListener('click', function( e ) {
        e.preventDefault();

        location.assign("../views/home.html");
    });
    
});