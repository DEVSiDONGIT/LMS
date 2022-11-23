package com.te.lmsproject.mentorentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class ChangeStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Employee Id Is Mandatory")
	@Size(max = 10)
	private String employeeId;

	@NotNull(message = "Employee Name Is Mandatory")
	private String employeeName;
	
	@NotNull(message="Please Change The Status")
	private String employeeStatus;
	
	@NotNull(message="Please Give Valid Reason")
	private String reasonForChangeStatus;
}
