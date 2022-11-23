package com.te.lmsproject.mentordto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.te.lmsproject.mentorentity.MockRatings;

import lombok.Data;

@Data
public class EmployeeDetailsDto {

	@NotNull(message = "Employee Id Is Mandatory")
	@Size(max = 10)
	private String employeeId;

	@NotNull(message = "Mock taken by is Mandatory")
	private int mockTaken;

	private List<MockRatings> mockRatings;

	@NotNull(message = "Attendance is mandatory")
	private int attendance;

	private String employeeStatus;
}
