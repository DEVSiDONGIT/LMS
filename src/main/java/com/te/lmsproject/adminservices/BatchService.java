package com.te.lmsproject.adminservices;

import javax.validation.Valid;

import com.te.lmsproject.admindto.BatchDto;
import com.te.lmsproject.admindto.NewBatchPojo;
import com.te.lmsproject.adminentity.Batch;
import com.te.lmsproject.lmsresponce.PageResponce;

public interface BatchService {

	BatchDto addBatch(BatchDto batchDto);

	Batch getBatchDetails(@Valid int id);

	void deleteBatchDetails(@Valid int id);

	PageResponce getAllBatchDetails(int pageNumber, int pageSize,String name);

	Batch updateBatchDetail(@Valid NewBatchPojo batchPojo);
}
