package com.te.lmsproject.mentorrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.mentorentity.AddMock;

@Repository
public interface AddMockRepo extends JpaRepository<AddMock, Integer> {

}
