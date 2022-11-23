package com.te.lmsproject.mentorcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.lmsresponce.Responce;
import com.te.lmsproject.mentordto.AddMockDto;
import com.te.lmsproject.mentordto.ChangeStatusDto;
import com.te.lmsproject.mentordto.EmployeeDetailsDto;
import com.te.lmsproject.mentordto.FinalBatchDto;
import com.te.lmsproject.mentorentity.AddMock;
import com.te.lmsproject.mentorentity.EmployeeDetail;
import com.te.lmsproject.mentorentity.FinalBatch;
import com.te.lmsproject.mentorservices.Mentorservices;

@RestController
public class MentorModuleController {

	@Autowired
	Mentorservices services;

	@PreAuthorize("hasAnyRole('Admin','Mentor')")
	@PostMapping("/assignFinalBatch")
	public ResponseEntity<Responce> assignBatch(@RequestBody FinalBatchDto batchDto) {
		FinalBatch batch = services.assignBatch(batchDto);
		return new ResponseEntity<Responce>(new Responce(false, "Batch Assign Succesfully", batch), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin','Mentor')")
	@GetMapping("/batchList")
	public ResponseEntity<Responce> getBatchList() {
		List<FinalBatch> batch = services.getBatchList();
		return new ResponseEntity<Responce>(new Responce(false, "Batch List", batch), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin','Mentor')")
	@PostMapping("/employeeRatings")
	public ResponseEntity<Responce> employeeRatings(@RequestBody EmployeeDetailsDto empdto) {
		EmployeeDetail emp = services.employeeRatings(empdto);
		return new ResponseEntity<Responce>(new Responce(false, "Employee Ratings Assign Succesfully", emp),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin','Mentor')")
	@PostMapping("/addMock")
	public ResponseEntity<Responce> addMock(@RequestBody AddMockDto mockdto) {
		AddMock emp = services.employeeRatings(mockdto);
		return new ResponseEntity<Responce>(new Responce(false, "Mock Added Succesfully", emp), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin','Mentor')")
	@PostMapping("/changeStatus")
	public ResponseEntity<Responce> changeStatus(@RequestBody ChangeStatusDto dto) {
		services.changeStatus(dto);
		return new ResponseEntity<Responce>(new Responce(false, "Change Status Succesfully", null), HttpStatus.OK);
	}

}
