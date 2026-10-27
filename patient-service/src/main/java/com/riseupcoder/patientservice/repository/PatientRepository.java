package com.riseupcoder.patientservice.repository;

import com.riseupcoder.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {


    @Query(value = """ 
            SELECT EXISTS ( SELECT 1 FROM patients p WHERE p.email = :email )""", nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);


    @Query(value = """
            SELECT EXISTS ( SELECT 1 FROM patients p WHERE p.email = :email AND p.id != :patientId)""",
            nativeQuery = true)
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("patientId") UUID patientId);
}
