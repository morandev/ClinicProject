package ar.com.digitalhouse.ctd.clinicproject.repository;

import ar.com.digitalhouse.ctd.clinicproject.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRepository extends JpaRepository< Appointment , Long > {}

