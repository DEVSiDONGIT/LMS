package com.te.lmsproject.lmsresponce;

import java.util.List;

import javax.persistence.Convert;

import com.te.lmsproject.lmslisttostringconverter.ListToString;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponce {

	private boolean error;
	
	@Convert(converter = ListToString.class)
	private List<String> message;
	
	private Object data;
}
