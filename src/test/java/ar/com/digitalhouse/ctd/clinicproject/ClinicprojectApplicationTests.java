package ar.com.digitalhouse.ctd.clinicproject;

import ar.com.digitalhouse.ctd.clinicproject.controller.DentistController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClinicprojectApplicationTests {

	@Autowired
	DentistController dentistController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull( dentistController );
	}

}
