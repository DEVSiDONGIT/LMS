package com.te.lmsproject.admincontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.adminservices.DashBoardServices;
import com.te.lmsproject.lmsresponce.Responce;

/**
 * @author TYSS
 *
 */
@RestController
public class DashBoardController {

	@Autowired
	private DashBoardServices services;
//	piChart for Gender
	@GetMapping("/piChart/{batchId}")
	public ResponseEntity<Responce> pichart(@PathVariable String batchId){
		Map<String, Long> chart= services.pichart(batchId);
		return new ResponseEntity<Responce>(new Responce(false, "Pi Chart", chart),HttpStatus.OK);
	}
	
	@GetMapping("/barGraph/{batchId}")
	public ResponseEntity<Responce> barGraph(@PathVariable String batchId){
		Map<String, Long> chart= services.barGraph(batchId);
		return new ResponseEntity<Responce>(new Responce(false, "barGraph", chart),HttpStatus.OK);
	}
	
	@GetMapping("/barGraphforDegree/{batchId}")
	public ResponseEntity<Responce> barGraphDegree(@PathVariable String batchId){
		Map<String, Long> chart= services.barGraphDegree(batchId);
		return new ResponseEntity<Responce>(new Responce(false, "barGraphforDegree", chart),HttpStatus.OK);
	}
	
	@GetMapping("/barGraphforExperience/{batchId}")
	public ResponseEntity<Responce> barGraphforExperience(@PathVariable String batchId){
		Map<String, Long> chart= services.barGraphforExperience(batchId);
		return new ResponseEntity<Responce>(new Responce(false, "barGraphforExperience", chart),HttpStatus.OK);
	}
	
}
