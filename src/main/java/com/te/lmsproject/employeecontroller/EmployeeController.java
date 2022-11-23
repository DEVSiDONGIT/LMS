package com.te.lmsproject.employeecontroller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.employeedto.EmployeeDto;
import com.te.lmsproject.employeeentity.Employee;
import com.te.lmsproject.employeeservices.EmployeeServices;
import com.te.lmsproject.lmsresponce.PageResponce;
import com.te.lmsproject.lmsresponce.Responce;
import static com.te.lmsproject.lmsconstants.Constants.*;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeServices services;

	private org.slf4j.Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@PreAuthorize("hasAnyRole('Admin','Employee')")
	@PostMapping("/addEmployee")
	public ResponseEntity<Responce> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		log.info(ADDING_EMPLOYEE_DETAILS);
		log.warn(ENTER_DATA_CAREFULLY);
		Employee employee = services.addEmployee(employeeDto);
		return new ResponseEntity<Responce>(new Responce(false, EMPLOYEE_DETAILS_ADDED_SUCCESFULLY, employee),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin','Employee')")
	@GetMapping("/getEmployeeDetails/{id}")
	public ResponseEntity<Responce> getEmployeeDetails(@Valid @PathVariable int id) {
		log.info(GETTING_EMPLOYEE_DETAILS,id);
		log.warn(ENTER_VALID_ID);
		Employee employee = services.getEmployeeDetails(id);
		return new ResponseEntity<Responce>(new Responce(false, GETTING_EMPLOYEE_DETAILS_SUCCESFULLY, employee),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin','Employee')")
	@PutMapping("/updateEmployeeDetails")
	public ResponseEntity<Responce> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		log.info(UPDATING_EMPLOYEE_DETAILS);
		log.warn(ENTER_DATA_CAREFULLY);
		Employee employee = services.updateEmployee(employeeDto);
		return new ResponseEntity<Responce>(new Responce(false, EMPLOYEE_UPDATED_SUCCESFULLY, employee),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin','Employee')")
	@GetMapping("/getAllEmployeeDetails/{pageNumber}/{pageSize}/{str}")
	public ResponseEntity<Responce> getAllEmployeeDetails(@PathVariable int pageNumber, @PathVariable int pageSize,
			@PathVariable String str) {
		log.info(GETTING_ALL_EMPLOYEE_DETAILS);
		PageResponce list = services.getAllEmployeeDetails(pageNumber, pageSize, str);
		return new ResponseEntity<Responce>(new Responce(false, GETTING_ALL_EMPLOYEE_DETAILS, list), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin','Employee')")
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public ResponseEntity<Responce> deleteEmployee(@Valid @PathVariable String employeeId) {
		log.info(DELETING_EMPLOYEE_DETAILS,employeeId);
		log.warn(ENTER_VALID_ID2);
		services.deleteEmployee(employeeId);
		return new ResponseEntity<Responce>(new Responce(false, DELETE_EMPLOYEE_DETAILS_SUCCESFULLY, null),
				HttpStatus.OK);
	}
}
