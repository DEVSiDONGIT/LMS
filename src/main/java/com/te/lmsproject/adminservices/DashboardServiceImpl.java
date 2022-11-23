package com.te.lmsproject.adminservices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lmsproject.employeeentity.Employee;
import com.te.lmsproject.lmscustomexception.CustomException;
import com.te.lmsproject.mentorentity.FinalBatch;
import com.te.lmsproject.mentorrepository.FinalBatchRepo;

@Service
public class DashboardServiceImpl implements DashBoardServices {

	@Autowired
	private FinalBatchRepo repo;

	@Override
	public Map<String, Long> pichart(String batchId) {
		try {
			Optional<FinalBatch> batch = repo.findByBatchId(batchId);
			if (!batch.isPresent()) {
				throw new CustomException("Batch You Want Is Not Present");
			} else {
				List<Employee> emp = batch.get().getEmp();
				long male = emp.stream().filter(t -> t.getGender().equalsIgnoreCase("male")).count();
				long female = emp.stream().filter(t -> t.getGender().equalsIgnoreCase("female")).count();
				Map<String, Long> chart = new HashMap<>();
				chart.put("Male", male);
				chart.put("Female", female);
				return chart;
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Long> barGraph(String batchId) {
		try {
			Optional<FinalBatch> batch = repo.findByBatchId(batchId);
			if (!batch.isPresent()) {
				throw new CustomException("Batch You Want Is Not Present");
			} else {
				List<Employee> emp = batch.get().getEmp();
				Map<String, Long> chart = new HashMap<>();
				chart.put("2019", emp.stream().filter(t -> t.getEducationDetails().getYearOfPassing() == 2019).count());
				chart.put("2020", emp.stream().filter(t -> t.getEducationDetails().getYearOfPassing() == 2020).count());
				chart.put("2021", emp.stream().filter(t -> t.getEducationDetails().getYearOfPassing() == 2021).count());
				chart.put("2022", emp.stream().filter(t -> t.getEducationDetails().getYearOfPassing() == 2022).count());
				chart.put("2023", emp.stream().filter(t -> t.getEducationDetails().getYearOfPassing() == 2023).count());
				return chart;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Long> barGraphDegree(String batchId) {
		try {
			Optional<FinalBatch> batch = repo.findByBatchId(batchId);
			if (!batch.isPresent()) {
				throw new CustomException("Batch You Want Is Not Present");
			} else {
				List<Employee> emp = batch.get().getEmp();
				Map<String, Long> graph = new HashMap<>();
				graph.put("Graduation",
						emp.stream()
								.filter(t -> t.getEducationDetails().getEducationType().equalsIgnoreCase("Graduation"))
								.count());
				graph.put("Post Graduation",
						emp.stream().filter(
								t -> t.getEducationDetails().getEducationType().equalsIgnoreCase("Post Graduation"))
								.count());
				return graph;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, Long> barGraphforExperience(String batchId) {
		try {
			Optional<FinalBatch> batch = repo.findByBatchId(batchId);
			if (!batch.isPresent()) {
				throw new CustomException("Batch You Want Is Not Present");
			} else {
				List<Employee> emp = batch.get().getEmp();
				Map<String, Long> graph = new HashMap<>();
			     List<Stream<Integer>> collect = emp.stream().map(e-> e.getExperiences().stream().map(t->{return t.getYearsOfExperience();})).collect(Collectors.toList());
			     List<Integer> collect2 = collect.stream().flatMap(e-> e).collect(Collectors.toList());
			     long count = collect2.stream().filter(t-> t==10).count();
				graph.put("Fresher", count);
				
				return graph;
			}
	} catch (Exception e) {
		throw e;
	}
	}

}
