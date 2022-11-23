package com.te.lmsproject.mentorservices;

import java.util.List;

import com.te.lmsproject.mentordto.AddMockDto;
import com.te.lmsproject.mentordto.ChangeStatusDto;
import com.te.lmsproject.mentordto.EmployeeDetailsDto;
import com.te.lmsproject.mentordto.FinalBatchDto;
import com.te.lmsproject.mentorentity.AddMock;
import com.te.lmsproject.mentorentity.EmployeeDetail;
import com.te.lmsproject.mentorentity.FinalBatch;

public interface Mentorservices {

	FinalBatch assignBatch(FinalBatchDto batchDto);

	List<FinalBatch> getBatchList();

	EmployeeDetail employeeRatings(EmployeeDetailsDto empdto);

	AddMock employeeRatings(AddMockDto mockdto);

	void changeStatus(ChangeStatusDto dto);
}
