package com.te.lmsproject.adminrepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.adminentity.Mentor;

@Repository
public interface MentorRepo extends JpaRepository<Mentor, Integer> {

	Optional<Mentor> findByEmail(String email);

	Page<Mentor> findAllByMentorNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrEmployeeIdContainingIgnoreCase(
			String str, String str2, String str3, Pageable p);
}
