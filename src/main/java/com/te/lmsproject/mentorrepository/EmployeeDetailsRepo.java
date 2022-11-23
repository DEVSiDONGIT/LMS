package com.te.lmsproject.mentorrepository;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.mentorentity.EmployeeDetail;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetail, Integer>{

	Optional<EmployeeDetail> findByEmployeeId(@NotNull(message = "Employee Id Is Mandatory") String employeeId);

}
