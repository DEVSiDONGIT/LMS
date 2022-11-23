package com.te.lmsproject.admindto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.te.lmsproject.adminentity.Batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorDto {

	@NotBlank(message = "Name is mandatory")
	@Size(max = 25)
	private String mentorName;
	
	@NotNull(message = "Employee Id Mandatory")
	@Size(max = 10)
	private String employeeId="TYSS";
	
	@Email(message = "Valid Email Address")
	@Size(max = 40)
	private String email;
	
	@NotEmpty(message = "Skills mandatory")
	private List<String> skills;
	
	private List<Batch> batch;
}
