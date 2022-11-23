package com.te.lmsproject.adminservices;

import javax.validation.Valid;

import com.te.lmsproject.admindto.MentorDto;
import com.te.lmsproject.adminentity.Mentor;
import com.te.lmsproject.lmsresponce.PageResponce;

public interface MentorService {

	Mentor addMentor(@Valid MentorDto dto);

	Mentor getMentor(@Valid int id);

	String deleteMentorDetails(@Valid int id);

	PageResponce getAllMentorDetails(int pageNumber, int pageSize, String str);

	Mentor updateMentorDetails(MentorDto dto);
}
