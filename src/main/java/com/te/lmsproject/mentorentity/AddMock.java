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
public class AddMock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "Enter Atleast One Batch Id")
	@Convert(converter = ListToString.class)
	private List<String> batchId;

	@NotNull(message = "Enter Mock Number")
	private int mockNo;

	@NotEmpty(message = "Enter Atleast One Technology")
	@Convert(converter = ListToString.class)
	private List<String> technology;

	@NotEmpty(message = "Enter Atleast One Mentor Name")
	@Convert(converter = ListToString.class)
	private List<String> panel;

	@NotNull(message = "Enter Date And Time")
	private String dateAndTime;
}
