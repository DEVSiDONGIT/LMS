package com.te.lmsproject.employeeentity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.te.lmsproject.lmslisttostringconverter.ListToString;

import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeDetailsId;

	@NotNull(message = "Employee Id Is Mandatory")
	@Size(max = 10)
	private String employeeId;

	@NotNull(message = "Employee Name Is Mandatory")
	private String employeeName;

	@NotNull(message = "Please Enter Date Of Joining")
	private String dateOfJoining;

	@NotNull(message = "Please Enter Date Of Joining")
	private String dateOfBirth;

	@NotNull(message = "Email Id Is Mandatory And In Propper Format")
	@Email
	private String email;

	@NotNull(message = "Please Enter Blood Group")
	@Size(max = 3)
	private String bloodGroup;

	@Size(min = 1, message = "Please Enter Designation")
	@Convert(converter = ListToString.class)
	private List<String> designation;

	@NotNull(message = "Please Enter Gender")
	private String gender;

	@Size(min = 1, message = "Please Enter Nationality")
	@Convert(converter = ListToString.class)
	private List<String> nationality;

	@Size(min = 1, message = "Please Enter Employee Status")
	@Convert(converter = ListToString.class)
	private List<String> employeeStatus;

	@OneToOne
	@Cascade(CascadeType.ALL)
	private SecondaryInfo secondaryInfo;

	@OneToOne
	@Cascade(CascadeType.ALL)
	private EducationDetails educationDetails;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "employeeDetailsId")
	private List<AddressDetails> addressDetails;

	@OneToOne
	@Cascade(CascadeType.ALL)
	private BankDetails bankDetails;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "employeeDetailsId")
	private List<TechnicalSkills> technicalskills;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "employeeDetailsId")
	private List<Experience> experiences;

	@OneToMany
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "employeeDetailsId")
	private List<ContactDetails> contactDetails;
}
