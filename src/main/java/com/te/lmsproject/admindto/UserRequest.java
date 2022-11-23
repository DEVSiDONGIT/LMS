package com.te.lmsproject.admindto;


import lombok.Data;

@Data
public class UserRequest {

	private String username;

	private String password;

	private String role;
}
