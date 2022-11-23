package com.te.lmsproject.mentorentity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.te.lmsproject.employeeentity.Employee;
import com.te.lmsproject.lmslisttostringconverter.ListToString;

import lombok.Data;

@Data
@Entity
public class FinalBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int finalBatchNo;

	private int batchNo;

	@NotNull(message = "Batch Id Is Mandatory")
	private String batchId;

	@NotBlank(message = "Batch Name Mandatory")
	@Size(max = 7)
	private String batchName;

	@NotEmpty(message = "Technologies is Mandatory")
	@Convert(converter = ListToString.class)
	private List<String> technologies;

	@NotNull(message = "Start Date Is Mandatory")
	private String startDate;

	@NotNull(message = "End Date Is Mandatory")
	private String endDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "finalBatchNo")
	private List<Employee> emp;

	// @NotNull(message = "Status is Mandatory")
	private String batchStatus;

	@NotNull(message = "Strength is Mandatory")
	private int strength;
}
