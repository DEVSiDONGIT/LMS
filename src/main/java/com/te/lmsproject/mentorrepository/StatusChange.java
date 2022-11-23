package com.te.lmsproject.mentorrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.mentorentity.ChangeStatus;

@Repository
public interface StatusChange extends JpaRepository<ChangeStatus, Integer>{

}
