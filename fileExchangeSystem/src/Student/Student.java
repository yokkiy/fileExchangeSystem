package Student;

public class Student {
  private String name;
  private int grade;
  private String subject;
  private String mailAddress;
  private String creditCard;
  private String userName;
  private String password;
  private String studentID;
  private int masterflag;//1:管理人、0:学生

  public int getMasterflag() {
	return masterflag;
}
public void setMasterflag(int masterflag) {
	this.masterflag = masterflag;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getGrade() {
	return grade;
}
public void setGarade(int garade) {
	this.grade = garade;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getMailAddress() {
	return mailAddress;
}
public void setMailAddress(String mailAddress) {
	this.mailAddress = mailAddress;
}
public String getCreditCard() {
	return creditCard;
}
public void setCreditCard(String creditCard) {
	this.creditCard = creditCard;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getStudentID() {
	return studentID;
}
public void setStudentID(String studentID) {
	this.studentID = studentID;
}





}
