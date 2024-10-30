package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.exception.WeakPasswordException;

@Service
public class PasswordService {

	private static final int MIN_LENGTH = 12;
    private static final int OPTIMAL_LENGTH = 14;
    

	public String isPasswordStrong(String password) throws WeakPasswordException {
		// Check length
		if (password.length() < MIN_LENGTH) {
			throw new WeakPasswordException("Password must be at least 12 characters long.");
			//return false;
		}
		// Check for at least one uppercase, lowercase, digit, and symbol
		boolean hasUpper = false, hasLower = false, hasDigit = false, hasSymbol = false;
		String symbols = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isUpperCase(c)) {
				hasUpper = true;
			}
			if (Character.isLowerCase(c)) {
				hasLower = true;
			}
			if (Character.isDigit(c)) {
				hasDigit = true;
			}
			if (symbols.indexOf(c) >= 0) {
				hasSymbol = true;
			}

			// Check for same characters next to each other
			if (i > 0 && Character.isDigit(password.charAt(i)) == false && password.charAt(i) == password.charAt(i - 1)) {
				throw new WeakPasswordException("Password cannot contain the same character next to each other.");
				
				//return false;
			}
		}

		if(hasUpper && hasLower && hasDigit && hasSymbol) {
			if (password.length() >= MIN_LENGTH && password.length() < OPTIMAL_LENGTH) {
	            return "The Password strength is medium..It's Ok,but try to improve..";
	        }else if (password.length() >= OPTIMAL_LENGTH) {
	            return "The Password strength is Good..";
	        }
		}else {
			 throw new WeakPasswordException("Password must include uppercase letters, lowercase letters, numbers, and symbols.");
        }
		return "";
	}
}
