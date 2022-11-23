package com.te.lmsproject.employeedto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.te.lmsproject.employeeentity.AddressDetails;
import com.te.lmsproject.employeeentity.BankDetails;
import com.te.lmsproject.employeeentity.ContactDetails;
import com.te.lmsproject.employeeentity.EducationDetails;
import com.te.lmsproject.employeeentity.Experience;
import com.te.lmsproject.employeeentity.SecondaryInfo;
import com.te.lmsproject.employeeentity.TechnicalSkills;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto { 

//	@NotNull(message = "Employee Id Is Mandatory")
//	@Size(max = 10)
	private String employeeId = "TYC";

//	@NotNull(message = "Employee Name Is Mandatory")
	private String employeeName;

//	@NotNull(message = "Please Enter Date Of Joining")
	private String dateOfJoining;

//	@NotNull(message = "Please Enter Date Of Joining")
	private String dateOfBirth;

//	@NotNull(message = "Email Id Is Mandatory")
	@Email
	private String email;

//	@NotNull(message = "Please Enter Blood Group")
//	@Size(max = 3)
	private String bloodGroup;

//	@Size(min = 1, message = "Please Enter Designation")
	private List<String> designation;

//	@NotNull(message = "Please Enter Gender")
	private String gender;

//	@Size(min = 1, message = "Please Enter Nationality")
	private List<String> nationality;

//	@Size(min = 1, message = "Please Enter Employee Status")
	private List<String> employeeStatus;

	private SecondaryInfo secondaryInfo;

	private EducationDetails educationDetails;

	private List<AddressDetails> addressDetails;

	private BankDetails bankDetails;

	private List<TechnicalSkills> technicalskills;

	private List<Experience> experiences;

	private List<ContactDetails> contactDetails;
}
