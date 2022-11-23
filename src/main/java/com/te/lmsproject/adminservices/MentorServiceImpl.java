package com.te.lmsproject.adminservices;

import static com.te.lmsproject.lmsconstants.Constants.ADDED_MENTORN_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.EMAIL_ID_IS_ALREADY_PRESENT_SO_ENTER_ANOTHER_EMAIL_ID;
import static com.te.lmsproject.lmsconstants.Constants.GETTING_ALL_MENTOR_DETAILS_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.GETTING_MENTOR_DETAILS_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.MENTOR_DELETED_SUCCESFULLY;
import static com.te.lmsproject.lmsconstants.Constants.MENTOR_NOT_PRESENT_ON_THIS_ID;
import static com.te.lmsproject.lmsconstants.Constants.MENTOR_NOT_PRESENT_ON_THIS_ID_WHICH_YOU_WANT_TO_UPDATE;
import static com.te.lmsproject.lmsconstants.Constants.MENTOR_NOT_PRESENT_WHICH_YOU_WANT_TO_DELETE;
import static com.te.lmsproject.lmsconstants.Constants.NOT_ENTER_THE_DATA_CORRECTLY;
import static com.te.lmsproject.lmsconstants.Constants.NO_ANY_MENTORS_PRESENTS_AT_THE_MOMENT;
import static com.te.lmsproject.lmsconstants.Constants.UPDATE_MENTOR_SUCCESFULLY;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.te.lmsproject.admindto.MentorDto;
import com.te.lmsproject.adminentity.Mentor;
import com.te.lmsproject.adminrepository.MentorRepo;
import com.te.lmsproject.lmscustomexception.CustomException;
import com.te.lmsproject.lmsresponce.PageResponce;

@Service
public class MentorServiceImpl implements MentorService {

	@Autowired
	MentorRepo repo;

	private org.slf4j.Logger log = LoggerFactory.getLogger(MentorServiceImpl.class);

	@Override
	public Mentor addMentor(@Valid MentorDto dto) {
		try {
			Mentor mentor = new Mentor();
			Optional<Mentor> findByEmail = repo.findByEmail(dto.getEmail());
			if (findByEmail.isPresent()) {
				log.error(EMAIL_ID_IS_ALREADY_PRESENT_SO_ENTER_ANOTHER_EMAIL_ID);
				throw new CustomException(EMAIL_ID_IS_ALREADY_PRESENT_SO_ENTER_ANOTHER_EMAIL_ID);
			}
			BeanUtils.copyProperties(dto, mentor);
			System.out.println(mentor.getMentorNo());
			List<Mentor> findAll = repo.findAll();
			mentor.setEmployeeId(dto.getEmployeeId() + "022" + String.format("%03d", findAll.size() + 1));
			Mentor save = repo.save(mentor);
			if (save == null) {
				log.error(NOT_ENTER_THE_DATA_CORRECTLY);
				throw new CustomException(NOT_ENTER_THE_DATA_CORRECTLY);
			} else {
				log.info(ADDED_MENTORN_SUCCESFULLY);
				return save;
			}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Mentor getMentor(@Valid int id) {
		try {
			Optional<Mentor> findById = repo.findById(id);
			if (!findById.isPresent()) {
				log.error(MENTOR_NOT_PRESENT_ON_THIS_ID);
				throw new CustomException(MENTOR_NOT_PRESENT_ON_THIS_ID);
			} else {
				log.info(GETTING_MENTOR_DETAILS_SUCCESFULLY);
				return findById.get();
			}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public String deleteMentorDetails(@Valid int id) {
		try {
			Optional<Mentor> findById = repo.findById(id);
			if (!findById.isPresent()) {
				log.error(MENTOR_NOT_PRESENT_WHICH_YOU_WANT_TO_DELETE);
				throw new CustomException(MENTOR_NOT_PRESENT_WHICH_YOU_WANT_TO_DELETE);
			} else {
				repo.delete(findById.get());
				log.info(MENTOR_DELETED_SUCCESFULLY);
				return MENTOR_DELETED_SUCCESFULLY;
			}
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public PageResponce getAllMentorDetails(int pageNumber, int pageSize, String str) {
		try {
			Pageable p = PageRequest.of(pageNumber, pageSize);
			Page<Mentor> page = repo
					.findAllByMentorNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrEmployeeIdContainingIgnoreCase(
							str, str, str, p);
			List<Mentor> content = page.getContent();
			if (content.isEmpty()) {
				log.error(NO_ANY_MENTORS_PRESENTS_AT_THE_MOMENT);
				throw new CustomException(NO_ANY_MENTORS_PRESENTS_AT_THE_MOMENT);
			} else {
				PageResponce responce = new PageResponce();
				responce.setContent(Arrays.asList(content));
				responce.setLastPage(page.isLast());
				responce.setTotalPages(page.getTotalPages());
				responce.setPageNumber(page.getNumber());
				responce.setPageSize(page.getSize());
				responce.setTotalElements(page.getTotalElements());
				log.info(GETTING_ALL_MENTOR_DETAILS_SUCCESFULLY);
				return responce;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Mentor updateMentorDetails(MentorDto dto) {
		try {
			Optional<Mentor> mentor = repo.findByEmail(dto.getEmail());
			if (!mentor.isPresent()) {
				log.error(MENTOR_NOT_PRESENT_ON_THIS_ID_WHICH_YOU_WANT_TO_UPDATE);
				throw new CustomException(MENTOR_NOT_PRESENT_ON_THIS_ID_WHICH_YOU_WANT_TO_UPDATE);
			} else {
				BeanUtils.copyProperties(dto, mentor.get());
				Mentor save = repo.save(mentor.get());
				log.info(UPDATE_MENTOR_SUCCESFULLY);
				return save;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
}
