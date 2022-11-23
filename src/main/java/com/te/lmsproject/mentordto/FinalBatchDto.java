package com.te.lmsproject.mentordto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinalBatchDto {

	private int batchNo;

	private String batchStatus;
	
	private List<String> employeeId;

	private int strength;
}
