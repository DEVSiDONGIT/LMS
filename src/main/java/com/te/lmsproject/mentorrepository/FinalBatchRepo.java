package com.te.lmsproject.mentorrepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.mentorentity.FinalBatch;

@Repository
public interface FinalBatchRepo extends JpaRepository<FinalBatch, Integer>{

	Optional<FinalBatch> findByBatchId(String batchId);

}
