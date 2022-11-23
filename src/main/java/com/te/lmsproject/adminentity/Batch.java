package com.te.lmsproject.adminentity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.te.lmsproject.lmslisttostringconverter.ListToString;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@JsonInclude(value = Include.NON_NULL)
@Entity
@Data
@NoArgsConstructor
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int batchNo;

	@NotBlank(message = "Batch Name Mandatory")
	@Size(max = 7)
	private String batchName;

	@Exclude
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Mentor mentor;

	@NotNull(message = "Batch Id Is Mandatory")
	private String batchId;

	@NotEmpty(message = "Technologies is Mandatory")
	@Convert(converter = ListToString.class)
	private List<String> technologies;

	@NotNull(message = "Start Date Is Mandatory")
	private String startDate;

	@NotNull(message = "End Date Is Mandatory")
	private String endDate;

	@NotEmpty(message = "Status is Mendatory")
	@Convert(converter = ListToString.class)
	private List<String> status;
}
