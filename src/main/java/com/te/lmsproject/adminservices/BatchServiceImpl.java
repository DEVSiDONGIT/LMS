package com.te.lmsproject.adminservices;

import static com.te.lmsproject.lmsconstants.Constants.BATCH_ADDED_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.BATCH_DELETED_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.BATCH_NOT_FOUND_ON_THIS_ID;
import static com.te.lmsproject.lmsconstants.Constants.BATCH_NOT_FOUND_ON_THIS_ID_WHICH_YOU_WANT_TO_DELETE;
import static com.te.lmsproject.lmsconstants.Constants.BATCH_NOT_FOUND_WHICH_YOU_WANT_TO_UPDATE;
import static com.te.lmsproject.lmsconstants.Constants.GETTING_ALL_BATCH_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.GETTING_BATCH_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.NOT_A_SINGLE_BATCHES_AVAILABLE;
import static com.te.lmsproject.lmsconstants.Constants.UPDATING_BATCH_DETAILS_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.WHICH_MENTOR_YOU_ASSIGN_IS_NOT_PRESENT_ON_THIS_ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.te.lmsproject.admindto.BatchDto;
import com.te.lmsproject.admindto.NewBatchPojo;
import com.te.lmsproject.adminentity.Batch;
import com.te.lmsproject.adminentity.Mentor;
import com.te.lmsproject.adminrepository.BatchRepo;
import com.te.lmsproject.adminrepository.MentorRepo;
import com.te.lmsproject.lmscustomexception.CustomException;
import com.te.lmsproject.lmsresponce.PageResponce;

@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepo batchRepo;

	@Autowired
	private MentorRepo mentorRepo;

	private org.slf4j.Logger log = LoggerFactory.getLogger(BatchServiceImpl.class);

	@Override
	@Transactional
	public BatchDto addBatch(BatchDto batchDto) {
		try {
			Optional<Mentor> findById = mentorRepo.findById(batchDto.getMentorNo());
			if (!findById.isPresent()) {
				log.info(WHICH_MENTOR_YOU_ASSIGN_IS_NOT_PRESENT_ON_THIS_ID);
				throw new CustomException(WHICH_MENTOR_YOU_ASSIGN_IS_NOT_PRESENT_ON_THIS_ID);
			} else {
				Batch batch = new Batch();
				BeanUtils.copyProperties(batchDto, batch);
				batch.setMentor(findById.get());
				batch.setBatchId( batchDto.getBatchId()+ String.format("%03d", batchRepo.findAll().size() + 1)+"02022");
				Batch save = batchRepo.save(batch);

				BatchDto batchDto2 = new BatchDto();
				batchDto2.setMentorNo(batchDto.getMentorNo());
				BeanUtils.copyProperties(save, batchDto2);
				batchDto2.setMentorName(findById.get().getMentorName());
				List<Batch> list = new ArrayList<>();
				list.add(save);
				Mentor mentor = findById.get();
				mentor.setBatch(list);
				mentorRepo.save(mentor);
				log.info(BATCH_ADDED_SUCCESFULLY);
				return batchDto2;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Batch getBatchDetails(@Valid int id) {
		try {
			Optional<Batch> findById = batchRepo.findById(id);
			if (!findById.isPresent()) {
				log.error(BATCH_NOT_FOUND_ON_THIS_ID);
				throw new CustomException(BATCH_NOT_FOUND_ON_THIS_ID);
			} else {
				Batch batch = findById.get();
				batch.setMentor(findById.get().getMentor());
				log.info(GETTING_BATCH_SUCCESFULLY);
				return batch;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void deleteBatchDetails(@Valid int id) {
		try {
			Optional<Batch> findById = batchRepo.findById(id);
			if (!findById.isPresent()) {
				log.error(BATCH_NOT_FOUND_ON_THIS_ID_WHICH_YOU_WANT_TO_DELETE);
				throw new CustomException(BATCH_NOT_FOUND_ON_THIS_ID_WHICH_YOU_WANT_TO_DELETE);
			} else {
				batchRepo.delete(findById.get());
				log.info(BATCH_DELETED_SUCCESFULLY);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public PageResponce getAllBatchDetails(int pageNumber, int pageSize, String str) {
		try {
			PageRequest p = PageRequest.of(pageNumber, pageSize);
			Page<Batch> page1 = batchRepo
					.findAllByBatchNameContainingIgnoreCaseOrBatchIdContainingIgnoreCaseOrStartDateContainingIgnoreCaseOrEndDateContainingIgnoreCase(
							str, str, str, str, p);
			List<Batch> content = page1.getContent();
			if (content.isEmpty()) {
				log.error(NOT_A_SINGLE_BATCHES_AVAILABLE);
				throw new CustomException(NOT_A_SINGLE_BATCHES_AVAILABLE);
			} else {
				PageResponce responce = new PageResponce();
				responce.setContent(Arrays.asList(content));
				responce.setLastPage(page1.isLast());
				responce.setTotalPages(page1.getTotalPages());
				responce.setPageNumber(page1.getNumber());
				responce.setPageSize(page1.getSize());
				responce.setTotalElements(page1.getTotalElements());
				log.info(GETTING_ALL_BATCH_DETAILS);
				return responce;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Batch updateBatchDetail(@Valid NewBatchPojo batchPojo) {
		try {
			Optional<Batch> batch = batchRepo.findById(batchPojo.getBatchNo());
			if (!batch.isPresent()) {
				log.error(BATCH_NOT_FOUND_WHICH_YOU_WANT_TO_UPDATE);
				throw new CustomException(BATCH_NOT_FOUND_WHICH_YOU_WANT_TO_UPDATE);
			} else {
				BeanUtils.copyProperties(batchPojo, batch.get());
				Batch save = batchRepo.save(batch.get());
				log.info(UPDATING_BATCH_DETAILS_SUCCESFULLY);
				return save;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
}
