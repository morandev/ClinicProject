package ar.com.digitalhouse.ctd.clinicproject.controller;

import ar.com.digitalhouse.ctd.clinicproject.dto.DentistDto;
import ar.com.digitalhouse.ctd.clinicproject.model.service.IDentistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class ) //initializes mocks and handles strict stubbings
class DentistControllerTest {

    @InjectMocks //create DentistController class and also inject the mocked IDentistService instance.
    DentistController dentistController;

    @Mock //create mock object for IDentistService dependency.
    IDentistService dentistService;

    @Test
    void add() {
        /*
            supply the request context where code under test needs it.
         */
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes( new ServletRequestAttributes( request ) );

        DentistDto dentistDto = new DentistDto();

        dentistDto.setId( 1 );
        dentistDto.setName( "name" );
        dentistDto.setSurname( "surname" );
        dentistDto.setEnrollment( "abc123" );

        /*
            mock the desired behavior.
         */
        when( dentistService.add( any( DentistDto.class ) ) ).thenReturn( Optional.of( dentistDto ) );

        ResponseEntity<DentistDto> responseEntity = dentistController.add( dentistDto );

        Assertions.assertTrue( responseEntity.getStatusCode().is2xxSuccessful() );
        Assertions.assertEquals( "/dentists/1" , responseEntity.getHeaders().getLocation().getPath() );

    }

}