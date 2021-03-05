/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upclientapplication;

import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author Nkosinathi
 */
public class DBAccess {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    private Boolean conError = false;
    
    public DBAccess() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/up", "root", "");
            con = DriverManager.getConnection("jdbc:mysql://den1.mysql4.gear.host/updb", "updb", "Yy7WB3_?k2m6");
            st = con.createStatement();
        }catch (Exception ex) {
            //System.out.print("Error: "+ex);
            
            conError = true;
            String msg = "Sorry currently there is no connection to the database";
            
            JOptionPane.showMessageDialog(null, msg, "up", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    public String getPassword(String oldPassword, String userName) {
        
        String password = "";
        
        try { 
            String query = "select * from student where StudentNumber = '" + userName + "' AND Password = '" + oldPassword + "'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) { 
                password = rs.getString("Password");
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        if (conError) {
            return "-1";
        }else {
           return password;
        }    
        
    }
    
    public int getLoginDetails(String username, String password) {
        
        int count = 0;
        
        try { 
            String query = "select * from student where UserName = '" + username + "' AND Password = '" + password + "'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) { 
                count++;
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        if (conError) {
            return -1;
        }else {
            if (count > 0)
                return 1;
            else
                return 0;
        }    
        
    }
    
    
    
    public void InsertComputer(String CompID, String LabNo) {
        
        try {
            
            String query = "insert into computer VALUES('" + CompID + "', '" + LabNo + "')";
            
            st.executeUpdate(query);
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }   
    }
    
    public String getStudNumberByName(String username, String password) {
        
        String studNumber = "";
        
        try { 
            String query = "select StudentNumber from student where UserName = '" + username + "' AND Password = '" + password + "'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) { 
                studNumber = rs.getString("StudentNumber");
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return studNumber;   
        
    }
    
    public Student getStudentDetails(String studNumber) {
        
        Student std = new Student();
        
        try { 
            String query = "select StudentNumber, UserName from student where StudentNumber = '" + studNumber + "'";
            
            rs = st.executeQuery(query);
            
            while (rs.next()) {
                std.setStudentNumber(rs.getString("StudentNumber"));
                std.setUserName(rs.getString("UserName"));
            }
            
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        return std;
    }
    
    public void InsertStudent(String studNumber, String UserName, String studEmail, String studPassword) {
        
        try { 
            
            String query = "insert into student VALUES('" + studNumber + "', '" + UserName + "', '" + studEmail +"', '" + studPassword +"')";
            
            st.executeUpdate(query);
            
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }   
    }
    
    public HashMap<String, String> getStudentUSBs(String studNumber) {
        HashMap<String, String> map = new HashMap<String, String> ();
        ComboItem cmi;
        
        try { 
            String query = "select USBID, friendlyName from usb WHERE StudentNumber = '" + studNumber + "'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) {
                cmi = new ComboItem(rs.getString("USBID"), rs.getString("friendlyName"));
                map.put(cmi.getLabel(), cmi.getValue());
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return map;  
    }
    
    
    public List<USB> getStudentAllUSBs(String studNumber) {
        
        List<USB> usbs = new ArrayList<>();
        USB usb = new USB();
        
        try { 
            String query = "select * from usb WHERE StudentNumber = '" + studNumber + "'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) {
                usb.setUSBID(rs.getString("USBID"));
                usb.setStudentNumber(rs.getString("StudentNumber"));
                usb.setFriendlyName(rs.getString("FriendlyName"));
                usb.setStatus(rs.getString("Status"));
                
                usbs.add(usb);
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return usbs;
    }
    
    public void updatePassword(String studNumber, String newPassword) {
        
        try { 
            String query = "update student set Password = '" + newPassword + "' where StudentNumber = '" + studNumber + "'";
            
            st.executeUpdate(query);
           
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
    }
    
    public void InsertLostStatus(String usbName) {
        
        try {
            
            String query = "update usb SET Status = 'Lost' WHERE friendlyName = '" + usbName + "'";
            
            st.executeUpdate(query);
            
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }   
    }
    
    public void optOut(String studNumber) {
        
        try { 
            String query1 = "delete from student where StudentNumber = '" + studNumber + "'";
            String query2 = "delete from usb where StudentNumber = '" + studNumber + "'";
            
            st.executeUpdate(query1);
            st.executeUpdate(query2);
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
    }
    
//    public String getUSBID(String friendlyName) {
//        
//        String usbID = "";
//        
//        try { 
//            String query = "select USBID from usb WHERE friendlyName = '" + friendlyName + "'";
//            
//            rs = st.executeQuery(query);
//            
//            while(rs.next()) {
//                usbID = rs.getString("USBID");
//            }
//            
//        }catch (Exception ex){
//            System.out.println("Error: " + ex);
//        }
//        
//        return usbID;
//    }
    
    public void InsertLog(String usbID, String CompID, String studNumber, String logType) {
        
        try {
            
            UUID uIDStr = UUID.randomUUID();
            
            String query = "insert into log VALUES('" + uIDStr.toString() + "', '" + studNumber + "', '" + usbID + "', '" + CompID + "', NOW(), 'Found', '" + logType + "')";
            
            st.executeUpdate(query);
            
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }   
    }
    
}
