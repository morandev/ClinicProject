package ar.com.digitalhouse.ctd.clinicproject.model.service.impl;

import static org.mockito.Mockito.verify;

import ar.com.digitalhouse.ctd.clinicproject.repository.IDentistRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith( MockitoExtension.class )
class DentistServiceTest {
    @Mock
    private IDentistRepository dentistRepository;
    private DentistService dentistService;

    @BeforeEach
    public void setUp() {
        dentistService = new DentistService( dentistRepository, null );
    }

    @Test
    void getAll() {
        dentistService.getAll();
        verify( dentistRepository ).findAll();
    }
}