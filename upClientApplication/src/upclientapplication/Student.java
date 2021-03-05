/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upclientapplication;

/**
 *
 * @author Nkosinathi
 */
public class Student {

    private String studentNumber;
    private String userName;
    private String studentEmail;
    private String password;
    
    public Student()
    {
        
    }
    
    public Student(String studentNumber, String userName, String studentEmail, String password)
    {
        this.studentNumber = studentNumber;
        this.userName = userName;
        this.studentEmail = studentEmail;
        this.password = password;
    }
    
    public String getStudentNumber() {
        return studentNumber;
    }

    
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

     String getStudentEmail() {
        return studentEmail;
    }

    
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
