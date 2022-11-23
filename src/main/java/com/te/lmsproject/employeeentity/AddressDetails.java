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
public class AddressDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressDetailsId;

	@NotEmpty(message = "Atleast Type Is Compalsary")
	@Convert(converter = ListToString.class)
	private List<String> addressType;

	@NotNull(message = "Please Enter Door Number")
	private int doorNumber;

	@NotNull(message = "Please Enter Street Name")
	@Size(max = 100)
	private String street;

	@NotNull(message = "Please Enter Locality")
	@Size(max = 100)
	private String locality;

	@NotEmpty(message = "Please Enter At Least One City Name")
	@Convert(converter = ListToString.class)
	private List<String> city;

	@NotEmpty(message = "Please Enter State Name")
	@Convert(converter = ListToString.class)
	private List<String> state;

	@NotNull(message = "Pin Code Is Mandatory")
	private int pincode;

	@NotNull(message = "Please Enter Landmark")
	private String landmark;
}