package com.te.lmsproject.adminentity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8020151583440911332L;

	private final String jwttoken;
}