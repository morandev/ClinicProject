package ar.com.digitalhouse.ctd.clinicproject;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicprojectApplication {
	public static void main(String[] args) {
		SpringApplication.run( ClinicprojectApplication.class , args );
		PropertyConfigurator.configure("log4j.properties");
	}

}
