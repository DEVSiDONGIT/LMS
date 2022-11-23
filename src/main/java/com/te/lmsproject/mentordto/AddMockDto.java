package com.te.lmsproject.mentordto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddMockDto {

	@NotEmpty(message = "Enter Atleast One Batch Id")
	private List<String> batchId;

	@NotNull(message = "Enter Mock Number")
	private int mockNo;

	@NotEmpty(message = "Enter Atleast One Technology")
	private List<String> technology;

	@NotEmpty(message = "Enter Atleast One Mentor Name")
	private List<String> panel;

	@NotNull(message = "Enter Date And Time")
	private String dateAndTime;
}
