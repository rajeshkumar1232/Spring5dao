package com.java.dto;

import java.io.File;
import java.sql.Blob;
import java.sql.Clob;

public class Employee {

	private int eno;
	private String name;
	private File eImage;
	private File eResume;
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public File geteImage() {
		return eImage;
	}
	public void seteImage(File eImage) {
		this.eImage = eImage;
	}
	public File geteResume() {
		return eResume;
	}
	public void seteResume(File eResume) {
		this.eResume = eResume;
	}
	
	
	
}
