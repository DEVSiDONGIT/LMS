package com.te.lmsproject.admincontroller;

import static com.te.lmsproject.lmsconstants.Constants.ADDING_BATCH;
import static com.te.lmsproject.lmsconstants.Constants.ALL_BATCH_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.BATCH_ADDED_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.BATCH_DELETED_SUCCESSFULLY;
import static com.te.lmsproject.lmsconstants.Constants.BATCH_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.DELETING_BATCH_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.ENTER_DATA_CAREFULLY;
import static com.te.lmsproject.lmsconstants.Constants.ENTER_VALID_ID;
import static com.te.lmsproject.lmsconstants.Constants.ENTER_VALID_INFORMATION;
import static com.te.lmsproject.lmsconstants.Constants.GETTING_ALL_BATCH_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.GETTING_BATCH_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.UPDATING_BATCH_DETAILS;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.admindto.BatchDto;
import com.te.lmsproject.admindto.NewBatchPojo;
import com.te.lmsproject.adminentity.Batch;
import com.te.lmsproject.adminservices.BatchService;
import com.te.lmsproject.lmsresponce.PageResponce;
import com.te.lmsproject.lmsresponce.Responce;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TYSS
 *
 */

@RestController
@Slf4j
public class BatchController {

	@Autowired
	private BatchService service;

	@PreAuthorize("hasAnyRole('Admin')")
	@PostMapping("/add-Batch")
	public ResponseEntity<Responce> addBatch(@Valid @RequestBody BatchDto batchDto) {
		log.info(ADDING_BATCH);
		log.warn(ENTER_DATA_CAREFULLY);
		BatchDto batch = service.addBatch(batchDto);
		return new ResponseEntity<Responce>(new Responce(false, BATCH_ADDED_SUCCESFULLY, batch), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@GetMapping("/getBatchDetails/{id}")
	public ResponseEntity<Responce> addBatch(@Valid @PathVariable int id) {
		log.info(GETTING_BATCH_DETAILS, id);
		log.warn(ENTER_VALID_ID);
		Batch batch = service.getBatchDetails(id);
		return new ResponseEntity<Responce>(new Responce(false, BATCH_DETAILS, batch), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@DeleteMapping("/deleteBatchDetails/{id}")
	public ResponseEntity<Responce> deleteBatchDetails(@Valid @PathVariable int id) {
		log.info(DELETING_BATCH_DETAILS);
		log.warn(ENTER_VALID_ID);
		service.deleteBatchDetails(id);
		return new ResponseEntity<Responce>(new Responce(false, BATCH_DELETED_SUCCESSFULLY, null), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@GetMapping("/getAllBatchDetails/{pageNumber}/{pageSize}/{name}")
	public ResponseEntity<Responce> getAllBatchDetails(@PathVariable int pageNumber, @PathVariable int pageSize,
			@PathVariable String name) {
		log.info(GETTING_ALL_BATCH_DETAILS);
		PageResponce batch = service.getAllBatchDetails(pageNumber, pageSize, name);
		return new ResponseEntity<Responce>(new Responce(false, ALL_BATCH_DETAILS, batch), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@PutMapping("/updateBatchDetail")
	public ResponseEntity<Responce> updateBatchDetail(@Valid @RequestBody NewBatchPojo batchPojo) {
		log.info(UPDATING_BATCH_DETAILS);
		log.warn(ENTER_VALID_INFORMATION);
		Batch batch = service.updateBatchDetail(batchPojo);
		return new ResponseEntity<Responce>(new Responce(false, BATCH_DETAILS, batch), HttpStatus.OK);
	}
}
