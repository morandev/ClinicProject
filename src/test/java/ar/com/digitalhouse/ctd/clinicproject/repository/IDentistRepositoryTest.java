package ar.com.digitalhouse.ctd.clinicproject.repository;

import ar.com.digitalhouse.ctd.clinicproject.model.entity.Dentist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IDentistRepositoryTest {

    @Autowired
    private IDentistRepository dentistRepository;

    @Test
    void getActualDentistById()  {
        Dentist dentist = new Dentist();

        dentist.setName("Richard");
        dentist.setSurname("Nielsen");
        dentist.setEnrollment("abc123");

        dentistRepository.save( dentist );

        boolean result = dentistRepository.existsById( 1L );
        Assertions.assertTrue( result );
    }

    @Test
    void findActualDentistByEnrollment()  {
        Dentist dentist = new Dentist();

        dentist.setName("Richard");
        dentist.setSurname("Nielsen");
        dentist.setEnrollment("abc456");

        dentistRepository.save( dentist );

        var result = dentistRepository.findByEnrollment( "abc456" );
        Assertions.assertTrue( result.isPresent() );
    }

}