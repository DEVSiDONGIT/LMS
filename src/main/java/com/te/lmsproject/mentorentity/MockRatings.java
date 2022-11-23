package com.te.lmsproject.mentorentity;

import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.te.lmsproject.lmslisttostringconverter.ListToString;

import lombok.Data;

@Data
@Entity
public class MockRatings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Enter Mock Type")
	@Convert(converter = ListToString.class)
	private List<String> mockType;

	@NotEmpty(message = "Enter Technologies")
	@Convert(converter = ListToString.class)
	private List<String> technology;

	@NotNull(message = "Enter Mock Taken By")
	private String mockTakenBy;

	@NotNull(message = "Enter Practcal Marks")
	// @Size(min = 0, max = 100)
	private int practicalKnowledge;

	@NotNull(message = "Enter Therotical Marks Marks")
	// @Size(min = 0, max = 100)
	private int theoreticalKnowledge;

	@NotEmpty(message = "Enter Over All Feedback")
	@Convert(converter = ListToString.class)
	List<String> overAllFeedback;

	@NotNull(message = "Enter Detailed Feedback")
	private String detailedFeedback;
}
