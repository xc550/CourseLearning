package com.cl.entity;

public class CourseHead {
	private int coursehead_id;
	private int course_id;
	private int id;				// userid
	private int class_id;
	
	public int getCoursehead_id() {
		return coursehead_id;
	}

	public void setCoursehead_id(int coursehead_id) {
		this.coursehead_id = coursehead_id;
	}

	public int getCourse_id() {
		return course_id;
	}
	
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	
}
