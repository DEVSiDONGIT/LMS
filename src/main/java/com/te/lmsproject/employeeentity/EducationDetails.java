package com.te.lmsproject.employeeentity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.te.lmsproject.lmslisttostringconverter.ListToString;

import lombok.Data;

@Data
@Entity
public class EducationDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int educationDetailsId;

	@NotNull(message = "Please Enter Which Type Of Education Is")
	private String educationType;

	@NotNull(message = "Year Of Passing Is Mandatory")
	private int yearOfPassing;

	@NotNull(message = "Percentage Is Mandatory")
	private double percentage;

	@NotEmpty(message = "Please Enter University Name")
	@Convert(converter = ListToString.class)
	private List<String> universityName;

	@NotNull(message = "Please Enter Institute Name")
	private String instituteName;

	@NotNull(message = "Please Enter Secialization")
	private String specialization;

	@NotEmpty(message = "Please enter State Name")
	@Convert(converter = ListToString.class)
	private List<String> state;
}
