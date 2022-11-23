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
public class SecondaryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int secondaryInfoId;

	@NotNull(message = "Pan Card Number is Mandatory")
	@Size(max = 10, min = 10)
	private String panNumber;

	@NotNull(message = "Aadhar Card Number Is Mandatory")
	private String aadharNumber;

	@NotNull(message = "Father Name Is Mandatory")
	private String fatherName;

	@NotNull(message = "Mother Name Is Mandatory")
	private String motherName;

	@NotNull(message = "Spouse Name Is Mandatory")
	private String spouseName;

	@NotNull(message = "Passport Number Is Mandatory")
	private String passpoprtNumber;

	@NotEmpty(message = "Enter At Least One Marital Status")
	@Convert(converter = ListToString.class)
	private List<String> marritalStatus;
}
