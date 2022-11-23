package com.te.lmsproject.lmsresponce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responce {

	private boolean error;
	private String message;
	private Object data;
}
