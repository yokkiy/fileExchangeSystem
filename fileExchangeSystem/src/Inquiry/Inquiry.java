package Inquiry;

public class Inquiry {
private int inquiryID;
private String subjectOfQuestion;
private String QuestionContent;
private String date;
private String student;
public int getInquiryID() {
	return inquiryID;
}
public void setInquiryID(int inquiryID) {
	this.inquiryID = inquiryID;
}
public String getSubjectOfQuestion() {
	return subjectOfQuestion;
}
public void setSubjectOfQuestion(String subjectOfQuestion) {
	this.subjectOfQuestion = subjectOfQuestion;
}
public String getQuestionContent() {
	return QuestionContent;
}
public void setQuestionContent(String questionContent) {
	QuestionContent = questionContent;
}
public String getDate() {
	return date;
}
public void setDate(String string) {
	this.date = string;
}
public String getStudent() {
	return student;
}
public void setStudent(String student) {
	this.student = student;
}
}
