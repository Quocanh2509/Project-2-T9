package com.javaweb.customexception;

public class FileRequireException extends RuntimeException {
	public FileRequireException(String s) {
		super(s);
	}
}
