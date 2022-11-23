package com.te.lmsproject.mentorentity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class EmployeeDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;

	@NotNull(message = "Employee Id Is Mandatory")
	@Size(max = 10)
	private String employeeId;

	@NotNull(message = "Employee Name Is Mandatory")
	private String employeeName;

	@NotNull(message = "Mock taken by is Mandatory")
	private int mockTaken;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "no")
	private List<MockRatings> mockRatings;

	@NotNull(message = "Attendance is mandatory")
	private int attendance;

	@NotNull(message = "Enter Employee Status")
	private String employeeStatus;
}
