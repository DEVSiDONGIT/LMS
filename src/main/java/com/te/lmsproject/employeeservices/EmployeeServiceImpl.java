package com.te.lmsproject.employeeservices;

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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.te.lmsproject.employeecontroller.EmployeeController;
import com.te.lmsproject.employeedto.EmployeeDto;
import com.te.lmsproject.employeeentity.Employee;
import com.te.lmsproject.employeerepository.EmployeeRepository;
import com.te.lmsproject.lmscustomexception.CustomException;
import com.te.lmsproject.lmsresponce.PageResponce;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

	private org.slf4j.Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository repo;

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public Employee addEmployee(@Valid EmployeeDto employeeDto) {
		try {
			Optional<Employee> emp = repo.findByEmail(employeeDto.getEmail());
			if (emp.isPresent()) {
				log.error("Employee Allready Present On This Email Id");
				throw new CustomException("Employee Allready Present On This Email Id");
			} else {
				if (employeeDto == null) {
					log.error("Something Went Wrong To Add Employee");
					throw new CustomException("Something Went Wrong To Add Employee");
				} else {
					Employee employee = new Employee();
					BeanUtils.copyProperties(employeeDto, employee);
					employee.setEmployeeId(
							employeeDto.getEmployeeId() + "1121" + String.format("%03d", repo.findAll().size() + 1));
					Employee save = repo.save(employee);
					log.info("Employee Save Succesfully");
					return save;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Employee getEmployeeDetails(@Valid int id) {
		try {
			Optional<Employee> findById = repo.findById(id);
			if (!findById.isPresent()) {
				log.error("Employee Details Not Present On This Id");
				throw new CustomException("Employee Details Not Present On This Id");
			} else {
				log.info("Getting Employee Details Succesfully");
				return findById.get();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public Employee updateEmployee(@Valid EmployeeDto employeeDto) {
		try {
			Optional<Employee> emp = repo.findByEmail(employeeDto.getEmail());
			if (!emp.isPresent()) {
				log.error("Employee Not Present Which You Want To Update");
				throw new CustomException("Employee Not Present Which You Want To Update");
			} else {
				String id = emp.get().getEmployeeId();
				BeanUtils.copyProperties(employeeDto, emp.get());
				emp.get().setEmployeeId(id);
				log.info("Employee Updated Successfully");
				return repo.save(emp.get());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public PageResponce getAllEmployeeDetails(int pageNumber, int pageSize, String str) {
		try {
			Pageable p = PageRequest.of(pageNumber, pageSize);
			Page<Employee> pages = repo
					.findAllByEmployeeIdContainingIgnoreCaseOrEmployeeNameContainingIgnoreCaseOrDateOfJoiningContainingIgnoreCaseOrDateOfBirthContainingIgnoreCaseOrEmailContainingIgnoreCaseOrBloodGroupContainingIgnoreCase(
							str, str, str, str, str, str, p);
			List<Employee> content = pages.getContent();
			if (content.isEmpty()) {
				throw new CustomException("No Any Employee Present At The Moment");
			} else {
				PageResponce responce = new PageResponce();
				responce.setContent(Arrays.asList(content));
				responce.setLastPage(pages.isLast());
				responce.setTotalPages(pages.getTotalPages());
				responce.setPageNumber(pages.getNumber());
				responce.setPageSize(pages.getSize());
				responce.setTotalElements(pages.getTotalElements());
				return responce;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteEmployee(@Valid String employeeId) {
		try {
			Optional<Employee> findById = repo.findByEmployeeId(employeeId);
			if (!findById.isPresent()) {
				throw new CustomException("Which Employee You Want To Delete Is Not Present");
			} else {
				repo.delete(findById.get());
			}
		} catch (Exception e) {
			throw e;
		}
	}
}