package com.cl.entity;

import java.util.ArrayList;

public class Pie {
	private String piename;
	ArrayList<PiePart> parts;
	public String getPiename() {
		return piename;
	}
	public void setPiename(String piename) {
		this.piename = piename;
	}
	public ArrayList<PiePart> getParts() {
		return parts;
	}
	public void setParts(ArrayList<PiePart> parts) {
		this.parts = parts;
	}
	
	
}
