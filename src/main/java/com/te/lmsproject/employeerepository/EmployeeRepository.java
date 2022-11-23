package com.te.lmsproject.employeerepository;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.employeeentity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmail(@NotNull(message = "Email Id Is Mandatory") String email);

	Optional<Employee> findByEmployeeId(@NotNull(message = "Employee Id Is Mandatory") String employeeId);

	Page<Employee> findAllByEmployeeIdContainingIgnoreCaseOrEmployeeNameContainingIgnoreCaseOrDateOfJoiningContainingIgnoreCaseOrDateOfBirthContainingIgnoreCaseOrEmailContainingIgnoreCaseOrBloodGroupContainingIgnoreCase(
			String str, String str2, String str3, String str4, String str5, String str6, Pageable p);
}
