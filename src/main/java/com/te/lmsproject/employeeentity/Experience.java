package com.te.lmsproject.employeeentity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.te.lmsproject.lmslisttostringconverter.ListToString;

import lombok.Data;

@Entity
@Data
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int experienceId;

	@NotNull(message = "Company Name Is Mandatory")
	private String companyName;

	@NotNull(message = "Experience Is Mandatory")
	//@Size(max = 40, message = "Please Enter Less Tnan 40 Years Of Experience")
	private int yearsOfExperience;

	@NotNull(message = "Experience Is Mandatory")
	private String dateOfJoinning;

	@NotNull(message = "Experience Is Mandatory")
	private String dateOfRelieving;

	@NotEmpty(message = "Please Select At Least One Designation")
	@Size(min = 1)
	@Convert(converter = ListToString.class)
	private List<String> designation;

	@NotNull(message = "Location Details Is Mandatory")
	private String location;
}
