package com.te.lmsproject.admincontroller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.admindto.UserRequest;
import com.te.lmsproject.adminentity.JwtRequest;
import com.te.lmsproject.adminentity.JwtResponse;
import com.te.lmsproject.adminservices.UserServiceImpl;
import com.te.lmsproject.config.JwtTokensUtil;
import com.te.lmsproject.config.JwtUsersDetailService;

@RestController
@CrossOrigin
public class JwtAutheticationController {

	private static final String GENERATING_TOKEN_USING_USERNAME_AND_PASSWORD = null;

	private static final String ENTER_VALID_USERNAME_AND_PASSWORD_FOR_TOKEN = null;

	private static final String REGISTERING_NEW_ROLE = null;

	private static final String ENTER_VALID_USERNAME_AND_PASSWORD = null;

	private static final String USER_DISABLED = null;

	private static final String INVALID_CREDENTIALS = null;

	private org.slf4j.Logger log = LoggerFactory.getLogger(JwtAutheticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokensUtil jwtTokenUtil;

	@Autowired
	private JwtUsersDetailService userDetailsService;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		log.info(GENERATING_TOKEN_USING_USERNAME_AND_PASSWORD);
		log.warn(ENTER_VALID_USERNAME_AND_PASSWORD_FOR_TOKEN);
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUsers(@RequestBody UserRequest request) {
		log.info(REGISTERING_NEW_ROLE);
		log.warn(ENTER_VALID_USERNAME_AND_PASSWORD);
		return ResponseEntity.ok(userServiceImpl.saveUser(request));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception(USER_DISABLED, e);
		} catch (BadCredentialsException e) {
			throw new Exception(INVALID_CREDENTIALS, e);
		}

	}
}
