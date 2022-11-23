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
public class ContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contactDetailsId;
	
	@NotEmpty(message = "Enter which Type Of Contact Type IS")
	@Convert(converter = ListToString.class)
	private List<String> contactType;
	
	@NotNull(message = "Contact Number Is Mandatory")
	private long contactNumber;
}