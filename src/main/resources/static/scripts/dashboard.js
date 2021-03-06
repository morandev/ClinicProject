// IF NO JWT, REDIRECT
if ( !localStorage.jwt ) {
  location.assign(`${location.origin}`);
}

window.addEventListener( 'load' , function() {
    const token = JSON.parse( localStorage.jwt );

    const urlDentists = 'http://localhost:8080/dentists';
    const urlAddDentist = 'http://localhost:8080/dentists/add';

    const formAddDentist = document.getElementById('form-add');
    const formFindDentist = document.getElementById('form-find');

    const inputEnrollmentSearch = document.getElementById('input-enrollment-search');

    const inputName = document.getElementById('inputName');
    const inputSurName = document.getElementById('inputSurname');
    const inputEnrollment = document.getElementById('inputEnrollment');

    const table = document.querySelector( '.table' );
    const tblBody = document.createElement( "tbody" );

    const btnCloseSession = document.querySelector('#close-app');
    const btnBackHome = document.querySelector('#back-home');

    /**
    *   START WITH FILLING THE TABLE AND GETTING USERNAME
    */
    fillTable(); 
    getUserName();

    if ( btnCloseSession ) {
      /**
       *  CLOSE SESSION BUTTON
       */
      btnCloseSession.addEventListener( 'click' , function () {
  
        const closeSessionConfirm = confirm("¿Desea cerrar sesión?");
  
        if ( closeSessionConfirm ) {
          // CLEAR LOCALSTORAGE AND REDIRECT
          localStorage.clear();
          location.assign(`${location.origin}`);
        }
  
      });
    }

    if ( btnBackHome ) {
        /**
         *  BACK TO HOME PAGE BUTTON
         */
        btnBackHome.addEventListener( 'click' , function () {
            location.assign(`${location.origin}/views/home.html`);
        });
    }

    if ( formFindDentist ) {
        /**
         *  FORM FIND DENTIST
         */
        formFindDentist.addEventListener('submit', function( e ) {
          
            e.preventDefault()
            e.stopPropagation()
            
            const settings = {
              method: 'GET',
              headers: { Authorization: `Bearer ${token}` }
            };
    
            let enrollment = inputEnrollmentSearch.value;
            enrollment = enrollment.trim();
    
            fetch( `${urlDentists}/?enrollment=${enrollment}`, settings )
              .then( response => {
    
                if ( !response.ok  ) {
                  alert( "Dentist Not Found, Enrollment: " + enrollment )
                  return response;
                } 
    
                return response.json();
              })
              .then( dentist => {
                
                
                if( tblBody.innerHTML && dentist ) {
      
                  const row = document.querySelector( `.row-${ dentist.id }` );
                  
                  if ( row ) {
                    row.classList.add( 'table-warning' );
                    setTimeout(() => {
                      row.classList.remove( 'table-warning' );
                    }, 1000);
                  }
    
                }
              })
              .catch( error => console.log( error ) );
        
            // CLEAR FORM FIND 
            formFindDentist.reset();
        }); 
    }

    if ( formAddDentist ) {
        /**
         *  FORM ADD DENTIST
         */
        formAddDentist.addEventListener('submit', function( e ) {
    
          e.preventDefault()
          e.stopPropagation()
        
          const payload = {
              "name" : inputName.value.trim(),
              "surname" : inputSurName.value.trim(),
              "enrollment" : inputEnrollment.value.trim()
          };
            
          const settings = {
            method: 'POST',
            body: JSON.stringify( payload ),
            headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` }
          };
    
          fetch( urlAddDentist, settings )
          .then( response => {
    
            if( !response.ok ) {
              alert("Error: Ya existe dentista con enrollment: " + payload.enrollment );
              return response;
            }
    
            return response.json();
          })
          .then( data => {
            /**
            *   FILLING THE TABLE
            */
            fillTable();
          })
          .catch( error => console.log( error ));
      
          // CLEAR FORM ADD 
          formAddDentist.reset();
        });
    }


    /**
     *  FILL TABLE WITH DENTIST
     */
    function fillTable() {

      tblBody.innerHTML = '';
      tblBody.classList.add( "text-center" );

      const settings = {
        method: 'GET',
        headers: { Authorization: `Bearer ${token}` }
      };

      /**
       *  FETCH WITH GET METHOD
       */
      fetch( urlDentists , settings )
        .then( response => response.json() )
        .then( data => {
          
          // SORT RESULTS
          data.sort( (da, db) => {

            if( da.id > db.id ) 
              return 1;
            
            if( da.id < db.id ) 
              return -1;

            return 0;
          } );

          // ITERATING SORTED RESULTS
          data.forEach( dentist => {
            
            // ROW
            const row = document.createElement("tr");
            // EDITABLE CELLS
            const nameCell = document.createElement("td");
            nameCell.setAttribute( "contenteditable" , "true" );
            const surnameCell = document.createElement("td");
            surnameCell.setAttribute( "contenteditable" , "true" );
            const enrollmentCell = document.createElement("td");
            enrollmentCell.setAttribute( "contenteditable" , "true" );
            // ACTIONS CELL
            const actionsCell = document.createElement("td");
            // ACTIONS CELL BTN'S
            const btnEliminar = document.createElement("button");
            const btnGuardar = document.createElement("button");
            const iEliminar = document.createElement("i");
            const iGuardar = document.createElement("i");

            /**
             *  ACTIONS CELL BTN ELIMINAR
             */
            btnEliminar.setAttribute( "type" , "button" );
            btnEliminar.setAttribute( "data-bs-toggle" , "tooltip" );
            btnEliminar.setAttribute( "data-bs-placement" , "top" );
            btnEliminar.setAttribute( "title" , "delete" );
            btnEliminar.classList.add( "btn" , "btn-delete" , "btn-danger" , "mx-1" );
            
            // ACTIONS CELL BTN ELIMINAR APPEND'S
            iEliminar.classList.add( "far" , "fa-trash-alt" );
            btnEliminar.appendChild( iEliminar );

            /**
             *  BTN ELIMINAR CLICK
             */
            btnEliminar.addEventListener( 'click' , () => {
              tblBody.removeChild( row );
              deleteDentist( dentist.id );
            });
            
            /**
             *  ACTIONS CELL BTN SAVE
             */
            btnGuardar.setAttribute( "type" , "button" );
            btnGuardar.setAttribute( "data-bs-toggle" , "tooltip" );
            btnGuardar.setAttribute( "data-bs-placement" , "top" );
            btnGuardar.setAttribute( "title" , "update" );
            btnGuardar.classList.add( "btn" , "btn-save" , "btn-primary", "mx-1" );

            // ACTIONS CELL BTN SAVE APPEND'S
            iGuardar.classList.add( "fa-solid" , "fa-floppy-disk" );
            btnGuardar.appendChild( iGuardar );

            /**
             *  BTN SAVE CLICK
             */
            btnGuardar.addEventListener( 'click' , () => {
              const tds = row.getElementsByTagName( 'td' );

              const name = tds[0].innerHTML;
              const surname = tds[1].innerHTML;
              const enrollment = tds[2].innerHTML;
              
              updateDentist( dentist.id , name , surname , enrollment );

              row.classList.add( 'table-success' );
              setTimeout(() => {
                row.classList.remove( 'table-success' );
              }, 1000);
            });

            // CELLS APPEND'S
            nameCell.append( dentist.name );
            surnameCell.append( dentist.surname );
            enrollmentCell.append( dentist.enrollment );
            actionsCell.appendChild( btnGuardar );
            actionsCell.appendChild( btnEliminar );

            // ROW APPEND'S
            row.classList.add( `row-${ dentist.id }` );
            row.appendChild( nameCell );
            row.appendChild( surnameCell );
            row.appendChild( enrollmentCell );
            row.appendChild( actionsCell );

            // TABLE BODY APPEND'S
            tblBody.appendChild( row );
          });
          
          // TABLE APPENDS'S
          table?.appendChild( tblBody );
        })
        .catch( error => console.log( error ) );
    };

    /**
     *  FETCH WITH DELETE METHOD
     * 
     * @param {*} id 
     */
    function deleteDentist( id ) {

      const settings = {
        method: 'DELETE',
        headers: { Authorization: `Bearer ${token}` }
      };

      fetch( `${urlDentists}/${id}` , settings )
        .then( response => response )
        .catch( error => console.log( error ) );
    }

    /**
     * FETCH WITH PUT METHOD
     * 
     * 
     * @param {*} id 
     * @param {*} name 
     * @param {*} surname 
     * @param {*} enrollment 
     */
    function updateDentist( id , name , surname , enrollment ) {

      const payload = {
        name,
        surname,
        enrollment
      };

      const settings = {
        method: 'PUT',
        body: JSON.stringify( payload ),
        headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` }
      };

      fetch( `${urlDentists}/${id}` , settings )
      .then( response => {
        
        if( !response.ok ) {
          return response.json();
        }

        return response;
      })
      .catch( error => console.log( error ) );

    }


  /**
   *  FETCH WITH GET METHOD
   */
  function getUserName() {

    const urlUsuario = "http://localhost:8080/users";

    const settings = {
      method: 'GET',
      headers: { Authorization: `Bearer ${token}` }
    };
    
    fetch( urlUsuario , settings )
      .then( response => response.json() )
      .then( data => {
        const userTag = document.querySelector( '.usertag' );

        if ( userTag ) 
          userTag.innerText = `@${data.username}`;
        
      })
      .catch( error => console.log( error ) );
  }

});