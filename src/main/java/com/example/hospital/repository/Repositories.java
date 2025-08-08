
package com.example.hospital.repository;

import com.example.hospital.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {}
public interface PatientRepository extends JpaRepository<Patient, Long> {}
public interface StayRepository extends JpaRepository<Stay, Long> {}
public interface BillRepository extends JpaRepository<Bill, Long> {}
