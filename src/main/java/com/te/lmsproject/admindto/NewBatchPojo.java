package com.te.lmsproject.admindto;

import java.util.List;

import javax.persistence.Convert;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.te.lmsproject.adminentity.Mentor;
import com.te.lmsproject.lmslisttostringconverter.ListToString;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBatchPojo {

	private int batchNo;
	
	@NotBlank(message = "Batch Name Mandatory")
	@Size(max = 7)
	private String batchName;

	private Mentor mentor;

	@NotNull
	private String batchId;

	@NotEmpty(message = "Technologies is Mandatory")
	@Convert(converter = ListToString.class)
	private List<String> technologies;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD:mm:yyyy")
	@NonNull
	private String startDate;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD:mm:yyyy")
	@NotNull
	private String endDate;

	private List<String> status;
}
