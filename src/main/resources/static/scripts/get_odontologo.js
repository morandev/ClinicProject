window.addEventListener('load', function() {

    const url = '/dentists';

    const settings = {
        method = 'GET';
    }

    fetch( url, settings )
    .then( response => response.json() )
    .then( data => {

        for ( dentist of data ) {

            const table = document.getElementById("dentistTable");
            const odontologoRow = table.insertRow();

            let tr_id = "tr_" + odontologo.id;
            odontologoRow.id = tr_id;

        }

    })

})