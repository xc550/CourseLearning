# 接口文档

[TOC]

## 公共部分

### 作业附件下载

url: /main_homeworkdownload

data: {

​	type: <String>("homeworkaccessory" || "studentaccessory"),

​	filename: <String>

}

returnData: {

​	file: <stream>

}

### 课程列表

url: /sidebar\_*\_courselist

data: null

returnData: {

​	courselist: <[Course]>

}

session: {

​	username: <String>,

​	role: <String>

}

### 章节列表

url: /sidebar\_*\_sectionlist

data: {

​	courseid: <int>

}

returnData: {

​	sectionlist: <[Section]>,

​	course_name: <String>,	

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 事件列表

url: /sidebar\_*\_eventlist

data: {

​	sectionid: <int>

}

returnData: {

​	eventlist: <[Section]>,

​	section_name: <String>,	

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

## 教师部分

### 登录

url: /teacher_login

data: {

​	teacher: {

​		loginname: <String>,

​		password: <String>

​	}

}

returnData: null

session: {

​	username: <String>,

​	role: <String>("teacher")

}

### 登出

url: /teacher_logout

data: null

returnData: null

session: null

### 作业列表

url: /teacher_managehomework

data: null

returnData: {

​	sectionlist: <[Section]>,

​	homeworklist: <[homework]>,

​	user_id: <Integer>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 添加作业

url: /teacher_addhomework

data: {

​	homework: {

​		homework_title: <String>,

​		section_id: <int>,

​		homework_content: <String>,

​		homework_starttime: <String>,

​		homework_endtime: <String>

​	},

​	upload: <File>,

​	uploadContentType: <String>,

​	uploadFileName: <String>	

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 删除作业

url: /teacher_deletehomework

data: {

​	homework_id: <int>

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 删除作业附件

url: /teacher_deletehomeworkaccessory

data: {

​	homework_id: <int>

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 更新作业附件

url: /teacher_updatehomeworkaccessory

data: {

​	homework_id: <int>,

​	upload: <File>,

​	uploadContentType: <String>,

​	uploadFileName: <String>	

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 获取学生作业列表

url: /teacher_getstudenthomeworklist

data: {

​	homework_id: <int>

}

returnData: {

​	homework: <Homework>,

​	homeworklist: <[HomeworkStudent]>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 学生作业评分

url: /teacher_scorehomework

data: {

​	section_id: <int>,

​	homework: <int>,

​	sectionscore: [{

​		student_id: <int>,

​		homework: <double>

​	}]

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 章节管理

url: /teacher_managesection

data: null

returnData: {

​	sectionlist: <[Section]>,

​	course_name: <String>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 添加章节

url: /teacher_addsection

data: {

​	section: {

​		section_name: <String>

​	}

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 删除章节

url: /teacher_deletesection

data: {

​	section_id: <int>

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 知识点权值管理

url: /teacher_manageknowledgeweight

data: null

returnData: {

​	columns: <[String]>,

​	weightlist: <Knowled

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

### 更新知识点权值

url: /teacher_updateknowledgeweight

data: {

​	knowlegeweight: {

​		listening_weight: <double>,

​		answer_weight: <double>,

​		attendance_weight: <double>,

​		homework_weight: <double>,

​		experiment_weight: <double>,

​		reviewandpreview_weight: <double>

​	}

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

### 章节权值管理

url: /teacher_managesectionweight

data: {

​	section_id: <int>(0)

}

returnData: {

​	columns: <[String]>,

​	section_id: <Integer>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 更新章节权值

url: /teacher_updatesectionweight

data: {

​	section_id: <int>,

​	section: [{

​		section_weight: <double>

​	}]

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 事件管理

url: /teacher_manageevent

data: null

returnData: {

​	eventlist: <[Event]>,

​	section_name: <String>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

### 添加事件

url: /teacher_addevent

data: {

​	event: {

​		event_content: <String>,

​		event_type: <String>,

​		endtime: <String>

​	}

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

### 删除事件

url: /teacher_deleteevent

data: {

​	event_id: <int>

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

### 获取事件详情

url: /teacher_geteventinfo

data: {

​	event_id: <int>

}

returnData: {

​	learningstatuscolumns: <[String]>,

​	bbs: <[BBS]>,

​	event: <Event>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>,

​	event_id: <Integer>

}

### 图表展示

url: /teacher_displayChart

data: {

​	pilename: <String>("classtime" || "inclass" || "outclass")

}

returnData: {

​	chart: <JFreeChart>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>,

​	event_id: <Integer>

}

### 提交BBS

url: /teacher_submitbbs

data: {

​	reply_id: <int>,

​	bbs: {

​		bbs_content: <String>

​	}

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>,

​	event_id: <Integer>

}

### 获取评分

url: /teacher_getscore

data: null

returnData: {

​	section_name: <String>,

​	sectioncolumns: <[String]>,

​	scorearray: <[SectionScore]>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

### 更新评分

url: /teacher_updatescore

data: {

​	sectionscore: [{

​		student_id: <int>,

​		listening: <double>,

​		answer: <double>,

​		attendance: <double>,

​		homework: <double>,

​		experiment: <double>,

​		reviewandpreview: <double>

​	}]

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

### 获取章节总评

url: /teacher_getsectionsummary

data: null

// 普通章节

returnData: {

​	sectioncolumns: <[String]>,

​	scorearray: <[SectionScore]>,

​	average: <BigDecimal>,

​	max: <BigDecimal>,

​	min: <BigDecimal>,

​	maxscore: <[Student]>,

​	minscore: <[Student]>,

​	higher: <[Student]>,

​	lower: <[Student]>,

​	cheating: <[Student]>

}

// 章节总结

returnData: {

​	sectioncolumns: <[String]>,

​	scorearray: <[CourseScore]>,

​	average: <BigDecimal>,

​	max: <BigDecimal>,

​	min: <BigDecimal>,

​	maxscore: <[Student]>,

​	minscore: <[Student]>,

​	higher: <[Student]>,

​	lower: <[Integer]>,

​	cheating: <[Integer]>,

​	coreproblem: <[Section]>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

## 学生部分

### 登录

url: /student_login

data: {

​	student: {

​		loginname: <String>,

​		password: <String>

​	}

}

returnData: null

session: {

​	username: <String>,

​	role: <String> ("student")

}

### 登出

url: /student_logout

data: null

returnData: null

session: null

### 获取事件详情

url: /student_geteventinfo

data: {

​	event_id: <int>

}

returnData: {

​	learningstatuscolumns: <[String]>,

​	learningstatus: <LearningStatus>,

​	bbs: <[BBS]>,

​	event: <Event>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>,

​	event_id: <Integer>

}

### 提交学习问卷

url: /student_submitlearningstatus

data: {

​	learningstatus: {

​		classtime: <int>,

​		inclass: <int>,

​		outclass: <int>,

​		method: <String>		

​	}

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>,

​	event_id: <Integer>

}

### 提交BBS

url: /student_submitbbs

data: {

​	reply_id: <int>,

​	bbs: {

​		bbs_content: <String>

​	}

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>,

​	event_id: <Integer>

}

### 获取作业列表

url: /student_gethomework

data: null

returnData: {

​	sectionlist: <[Section]>,

​	homeworklist: <[Homework]>

​	user_id: <int>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 删除作业

url: /student_deletehomework

data: {

​	homworkstudent_id: <int>

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 提交作业

url: /student_submithomework

data: {

​	homework_id: <int>

​	homeworkcomment: <String>,

​	upload: <File>,

​	uploadContentType: <String>,

​	uploadFileName: <String>

}

returnData: null

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>

}

### 获取章节总结

url: /student_getsectionsummary

data: null

returnData: {

​	sectioncolumns: <[String]>,

​	scorearray: <[SectionScore]> / <[CourseScore]>,

​	section_name: <String>

}

session: {

​	username: <String>,

​	role: <String>,

​	course_id: <Integer>,

​	class_id: <Integer>,

​	section_id: <Integer>

}

## 管理员部分