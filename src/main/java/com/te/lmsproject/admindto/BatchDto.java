package com.te.lmsproject.admindto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDto {

	@NotBlank(message = "Batch Name Mandatory")
	@Size(max = 7)
	private String batchName;

	private int mentorNo;

	private String mentorName;

	private String batchId = "Batch";

	@NotEmpty(message = "Technologies is Mandatory")
	private List<String> technologies;

	private String startDate;

	private String endDate;

	@NotEmpty(message = "Status is Mendatory")
	private List<String> status;
}
