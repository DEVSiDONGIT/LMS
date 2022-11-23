package com.te.lmsproject.mentorservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lmsproject.adminentity.Batch;
import com.te.lmsproject.adminrepository.BatchRepo;
import com.te.lmsproject.employeeentity.Employee;
import com.te.lmsproject.employeerepository.EmployeeRepository;
import com.te.lmsproject.lmscustomexception.CustomException;
import com.te.lmsproject.mentordto.AddMockDto;
import com.te.lmsproject.mentordto.ChangeStatusDto;
import com.te.lmsproject.mentordto.EmployeeDetailsDto;
import com.te.lmsproject.mentordto.FinalBatchDto;
import com.te.lmsproject.mentorentity.AddMock;
import com.te.lmsproject.mentorentity.ChangeStatus;
import com.te.lmsproject.mentorentity.EmployeeDetail;
import com.te.lmsproject.mentorentity.FinalBatch;
import com.te.lmsproject.mentorrepository.AddMockRepo;
import com.te.lmsproject.mentorrepository.EmployeeDetailsRepo;
import com.te.lmsproject.mentorrepository.FinalBatchRepo;
import com.te.lmsproject.mentorrepository.StatusChange;

@Service
public class MentorServicesImpl implements Mentorservices {

	@Autowired
	private StatusChange statusRepo;

	@Autowired
	private BatchRepo batchRepo;

	@Autowired
	private FinalBatchRepo repo;

	@Autowired
	private EmployeeRepository emprepo;

	@Autowired
	private EmployeeDetailsRepo employeeDetailsRepo;

	@Autowired
	private AddMockRepo mockrepo;

	@Override
	public FinalBatch assignBatch(FinalBatchDto dto) {
		try {

			Optional<Batch> findById = batchRepo.findById(dto.getBatchNo());
			System.out.println(findById.get());
			if (!findById.isPresent()) {
				throw new CustomException("Which Batch You Assign Is Not Present");
			} else {
				FinalBatch batch = new FinalBatch();
				List<Employee> emp = new ArrayList<>();
				for (int i = 0; i < dto.getEmployeeId().size(); i++) {
					Optional<Employee> findByEmployeeId = emprepo.findByEmployeeId(dto.getEmployeeId().get(i));
					if (!findByEmployeeId.isPresent()) {
						throw new CustomException("Which Employee You Add Is Not Present And That EmployeeId is :"+dto.getEmployeeId().get(i));
					}
					System.out.println(findByEmployeeId.get());
					emp.add(findByEmployeeId.get());
				}
				BeanUtils.copyProperties(findById.get(), batch);
				BeanUtils.copyProperties(dto, batch);

				batch.setStrength(emp.size());
				batch.setEmp(emp);
				FinalBatch save = repo.save(batch);
				return save;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<FinalBatch> getBatchList() {
		try {
			List<FinalBatch> findAll = repo.findAll();
			if (findAll.isEmpty()) {
				throw new CustomException("No One Batch Is Available At The Moment");
			} else {
				return findAll;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public EmployeeDetail employeeRatings(EmployeeDetailsDto empdto) {
		try {
			Optional<Employee> employee = emprepo.findByEmployeeId(empdto.getEmployeeId());

			if (!employee.isPresent()) {
				throw new CustomException("Employee Which You Giving Rating Is Not Present On This Employee Id");
			} else {
				EmployeeDetail detail = new EmployeeDetail();
				BeanUtils.copyProperties(employee.get(), detail);
				BeanUtils.copyProperties(empdto, detail);
				EmployeeDetail save = employeeDetailsRepo.save(detail);
				return save;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public AddMock employeeRatings(AddMockDto mockdto) {
		try {
			AddMock mock = new AddMock();
			BeanUtils.copyProperties(mockdto, mock);
			AddMock save = mockrepo.save(mock);
			return save;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void changeStatus(ChangeStatusDto dto) {
		try {
			Optional<EmployeeDetail> detail = employeeDetailsRepo.findByEmployeeId(dto.getEmployeeId());
			if (!detail.isPresent()) {
				throw new CustomException("Which Employee Status You Want To Change Is Not Available");
			} else {
				ChangeStatus changeStatus = new ChangeStatus();
				BeanUtils.copyProperties(dto, changeStatus);
				changeStatus.setEmployeeName(detail.get().getEmployeeName());
				detail.get().setEmployeeStatus(dto.getEmployeeStatus());
				employeeDetailsRepo.save(detail.get());
				statusRepo.save(changeStatus);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
