window.addEventListener( 'load' , function () {
   /* ---------------------- obtenemos variables globales ---------------------- */
    const form = document.querySelector("#form-one");
    const username = document.querySelector('#username')
    const password = document.querySelector('#pwd')
    const url = new URL('http://localhost:8080/auth/login');

    form.addEventListener( 'submit' , function( event ) {
        event.preventDefault();

        //Aquí podemos mostrar el spinner para indicar a la persona que se ha iniciado el proceso de login
        // mostrarSpinner();

        //creamos el cuerpo de la request
        //TODO: EL PAYLOAD ES IGUAL QUE EL SIGNUP
        //TODO: EL PAYLOAD ES IGUAL QUE EL SIGNUP
        //TODO: EL PAYLOAD ES IGUAL QUE EL SIGNUP
        //TODO: EL PAYLOAD ES IGUAL QUE EL SIGNUP
        //TODO: EL PAYLOAD ES IGUAL QUE EL SIGNUP
        //TODO: EL PAYLOAD ES IGUAL QUE EL SIGNUP
        const payload = {
            username: username.value,
            password: password.value
        };

        // let data = JSON.stringify( payload );
        // data = JSON.parse( data );

        // for ( let k in data ) { 
        //     url.searchParams.append( k , data[k] );
        // }

        url.searchParams.append( "username", payload.username );
        url.searchParams.append( "password", payload.password );

        //configuramos la request del Fetch
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
        console.log( "Lanzando la consulta a la API..." );
        console.log( "La URL: " + url );

        fetch( url , settings )
            .then( response => {
                console.log( response );

                if ( response.ok != true ) {
                    alert( "Username or Password incorrect" )
                }

                return response;
            })
            .then( data => {
                console.log( "Promesa cumplida:" );
                console.log( { data } );

                // if ( data.jwt ) {
                //     //guardo en LocalStorage el objeto con el token
                //     localStorage.setItem('jwt', JSON.stringify(data.jwt));

                //     //Una vez obtenida la respuesta de la API, ocultamos el spinner
                    
                //     //redireccionamos a la página
                //     location.replace('./mis-tareas.html');
                //     // ocultarSpinner();
                // }
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
    
});