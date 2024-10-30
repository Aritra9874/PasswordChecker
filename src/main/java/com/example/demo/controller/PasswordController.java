package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.WeakPasswordException;
import com.example.demo.service.PasswordService;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

	private final PasswordService passwordService;

	public PasswordController(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@PostMapping("/check")
	public ResponseEntity<String> checkPasswordStrength(@RequestBody String password) throws WeakPasswordException {
		String passwordStrong = passwordService.isPasswordStrong(password);
		return ResponseEntity.ok(passwordStrong);
	}
}
