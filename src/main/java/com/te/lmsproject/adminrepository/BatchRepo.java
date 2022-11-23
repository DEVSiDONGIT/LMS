package com.te.lmsproject.adminrepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.adminentity.Batch;

@Repository
public interface BatchRepo extends JpaRepository<Batch, Integer> {

	Page<Batch> findAllByBatchNameContainingIgnoreCaseOrBatchIdContainingIgnoreCaseOrStartDateContainingIgnoreCaseOrEndDateContainingIgnoreCase(
			String str, String str2, String str3, String str4, PageRequest p);
}
