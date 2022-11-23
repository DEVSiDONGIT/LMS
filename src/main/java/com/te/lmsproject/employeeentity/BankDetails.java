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
public class BankDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankDetailsId;

	@NotNull(message = "Account Number Is Mandatory")
	private long accountNumber;

	@NotNull(message = "Bank Name Is Mandatory")
	private String bankName;

	@NotEmpty(message = "Please Enter Which Type Of This Bank Account Is")
	@Convert(converter = ListToString.class)
	private List<String> accountType;

	@NotNull(message = "IFSC Code Is Mandatory")
	private String ifscCode;

	@NotEmpty(message = "Please Enter Branch City Name")
	@Convert(converter = ListToString.class)
	private List<String> branch;

	@NotEmpty(message = "Please Enter State Name")
	@Convert(converter = ListToString.class)
	private List<String> state;
}