package com.te.lmsproject.mentordto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ChangeStatusDto {
	
	@NotNull(message = "Employee Id Is Mandatory")
	@Size(max = 10)
	private String employeeId;
	
	@NotNull(message="Please Change The Status")
	private String employeeStatus;
	
	@NotNull(message="Please Give Valid Reason")
	private String reasonForChangeStatus;
}
