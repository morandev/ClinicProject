window.addEventListener( 'load' , function () {

    const form = document.querySelector("#form-one");
    const username = document.querySelector('#username')
    const password = document.querySelector('#pwd')
    const url = new URL('http://localhost:8080/authenticate/login');

    form.addEventListener( 'submit' , function( event ) {
        event.preventDefault();

        showSpinner();

        const payload = {
            username: username.value,
            password: password.value
        };

        const settings = {
            method: 'POST',
            body: JSON.stringify( payload ),
            headers: {
                'Content-Type': 'application/json'
            }
        };

        doLogin( settings );

        // CLEAR LOGIN FORM
        form.reset();
    });

    function doLogin( settings ) {

        fetch( url , settings )
            .then( response => {

                if ( response.ok != true ) {
                    alert( "Username or Password incorrect" )
                }

                return response.json();
            })
            .then( data => {

                 if ( data.jwt ) {
                    // JWT TO LOCALSTORAGE
                    localStorage.setItem( 'jwt' , JSON.stringify( data.jwt ) );
                    suppressSpinner();
                    // REDIRECT
                    location.replace( './views/home.html' );
                }
            }).catch( err => {
                suppressSpinner();
            })
    };

    document.querySelector('.no-account')?.addEventListener('click', function( e ) {
        e.preventDefault();

        location.assign(`${location.origin}/register.html`);
    });
    
});