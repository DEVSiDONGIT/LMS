package com.te.lmsproject.employeeentity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class TechnicalSkills {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int technicalSkillsId;

	@NotBlank(message = "Skill Type Is Mandatory")
	@Size(max = 40, message = "Skill Type You Entered Is More Than 40 Characters")
	private String skillType;

	@NotNull(message = "Skill Rating Is Mandatory")
	//@Size(min = 1,max = 10,message = "Please Enter Skill Rating Between 1 To 10")
	private int skillRating;

	@NotNull(message = "Year Of Experience Should Be Mandatory")
	//@Size(max = 40, message = "Please Enter Less Tnan 40 Years Of Experience")
	private int yearOfExperience;
}
