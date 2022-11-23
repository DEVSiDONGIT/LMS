package com.te.lmsproject.adminentity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.te.lmsproject.lmslisttostringconverter.ListToString;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@JsonInclude(value = Include.NON_NULL)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mentorNo;

	@NotBlank(message = "Name is Mandatory")
	@Size(max = 25,message = "Name Which You Entered Is Greater Than 25 Characters")
	private String mentorName;

	@NotNull(message = "Employee Id Mandatory")
	@Size(max = 10,message = "Employee Id Should Be Less Than 10 Characters")
	private String employeeId;

	@Email(message = "Valid Email Address")
	@Size(max = 40,message = "email Id Should Be Less Than 40 Characters")
	private String email;

	@NotEmpty(message = "Skills mandatory")
	@Convert(converter = ListToString.class)
	private List<String> skills;

	@Exclude
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "mentorNo")
	private List<Batch> batch;
}