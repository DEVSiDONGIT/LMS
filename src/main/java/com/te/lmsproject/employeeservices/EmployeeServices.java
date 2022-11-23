package com.te.lmsproject.employeeservices;

import javax.validation.Valid;

import com.te.lmsproject.employeedto.EmployeeDto;
import com.te.lmsproject.employeeentity.Employee;
import com.te.lmsproject.lmsresponce.PageResponce;

public interface EmployeeServices {

	Employee addEmployee(@Valid EmployeeDto employeeDto);

	Employee getEmployeeDetails(@Valid int id);

	Employee updateEmployee(@Valid EmployeeDto employeeDto);

	PageResponce getAllEmployeeDetails(int pageNumber, int pageSize, String str);

	void deleteEmployee(@Valid String id);
}
