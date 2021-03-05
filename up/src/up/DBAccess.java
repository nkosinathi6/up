/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Nkosinathi
 */
public class DBAccess {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DBAccess() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/up", "root", "");
            con = DriverManager.getConnection("jdbc:mysql://den1.mysql4.gear.host/updb", "updb", "Yy7WB3_?k2m6");
            st = con.createStatement();
        }catch (Exception ex) {
            System.out.print("Error: "+ex);
        }
    }
    
    public List<String> getAdminEmails() {
        
        List<String> emails = new ArrayList();
        
        try { 
            String query = "select emailAddress from admin";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) {
                emails.add(rs.getString("emailAddress"));
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return emails;
    }
    
    public List<String> getUSBNames(String studNumber) {
        
        List<String> usbs = new ArrayList<>();
        
        try { 
            String query = "select * from usb WHERE StudentNumber = '" + studNumber + "'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) {
               
                String usbName = rs.getString("FriendlyName");
                usbs.add(usbName);
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return usbs;
    }
    
    public int getLostUSBbyID(String usbid) {
        
        int count = 0;
        
        try { 
            String query = "select * from usb where USBID = '" + usbid + "' AND Status = 'Lost'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) { 
                count++;
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        if (count > 0)
            return 1;
        else
            return 0;
    }
    
    public void InsertNewUSB(String usbID, String studNumber, String friendlyName) {
        
        try { 
            
            String query = "insert into usb VALUES('" + usbID + "', '" + studNumber + "', '" + friendlyName + "', 'Found')";
            
            st.executeUpdate(query);
            
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }   
    }
    
    public void InsertLog(String usbID, String CompID, String studNumber, String logType) {
        
        try {
            
            UUID uIDStr = UUID.randomUUID();
            
            String query = "insert into log VALUES('" + uIDStr.toString() + "', '" + studNumber + "', '" + usbID + "', '" + CompID + "', NOW(), 'Found', '" + logType + "')";
            
            st.executeUpdate(query);
            
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
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
    
}
