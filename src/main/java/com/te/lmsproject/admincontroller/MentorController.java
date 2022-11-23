package com.te.lmsproject.admincontroller;

import static com.te.lmsproject.lmsconstants.Constants.ADDING_MENTOR_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.ALL_MENTOR_DATA;
import static com.te.lmsproject.lmsconstants.Constants.DELITING_MENTOR_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.ENTER_DATA_CAREFULLY;
import static com.te.lmsproject.lmsconstants.Constants.ENTER_VALID_MENTOR_NUMBER;
import static com.te.lmsproject.lmsconstants.Constants.GETTING_ALL_MENTOR_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.GETTING_MENTOR_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.MENTOR_ADDED_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.MENTOR_DETAILS;
import static com.te.lmsproject.lmsconstants.Constants.UPDATING_MENTOR_DETAILS;

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

import com.te.lmsproject.admindto.MentorDto;
import com.te.lmsproject.adminentity.Mentor;
import com.te.lmsproject.adminservices.MentorService;
import com.te.lmsproject.lmsresponce.PageResponce;
import com.te.lmsproject.lmsresponce.Responce;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MentorController {

	@Autowired
	private MentorService service;

	@PreAuthorize("hasAnyRole('Admin')")
	@PostMapping("/addMentor")
	public ResponseEntity<Responce> addMentor(@Valid @RequestBody MentorDto dto) {
		log.info(ADDING_MENTOR_DETAILS);
		log.warn(ENTER_DATA_CAREFULLY);
		Mentor mentor = service.addMentor(dto);
		return new ResponseEntity<Responce>(new Responce(false, MENTOR_ADDED_SUCCESFULLY, mentor), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@GetMapping("/getMentorDetails/{mentorNo}")
	public ResponseEntity<Responce> getMentor(@Valid @PathVariable int mentorNo) {
		log.info(GETTING_MENTOR_DETAILS);
		log.warn(ENTER_VALID_MENTOR_NUMBER);
		Mentor mentor = service.getMentor(mentorNo);
		return new ResponseEntity<Responce>(new Responce(false, MENTOR_DETAILS, mentor), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@PutMapping("/updateMentorDetails")
	public ResponseEntity<Responce> updateMentorDetails(@Valid @RequestBody MentorDto dto) {
		log.info(UPDATING_MENTOR_DETAILS);
		log.warn(ENTER_DATA_CAREFULLY);
		Mentor mentor = service.updateMentorDetails(dto);
		return new ResponseEntity<Responce>(new Responce(false, MENTOR_DETAILS, mentor), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@DeleteMapping("/deleteMentorDetials/{mentorNo}")
	public ResponseEntity<Responce> deleteMentorDetails(@Valid @PathVariable int mentorNo) {
		log.info(DELITING_MENTOR_DETAILS);
		log.warn(ENTER_VALID_MENTOR_NUMBER);
		String str = service.deleteMentorDetails(mentorNo);
		return new ResponseEntity<Responce>(new Responce(false, str, null), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@GetMapping("/getAllMentorDetails/{pageNumber}/{pageSize}/{name}")
	public ResponseEntity<Responce> getAllMentorDetails(@PathVariable int pageNumber, @PathVariable int pageSize,
			@PathVariable String name) {
		log.info(GETTING_ALL_MENTOR_DETAILS);
		PageResponce mentorList = service.getAllMentorDetails(pageNumber, pageSize, name);
		return new ResponseEntity<Responce>(new Responce(false, ALL_MENTOR_DATA, mentorList), HttpStatus.OK);
	}
}
