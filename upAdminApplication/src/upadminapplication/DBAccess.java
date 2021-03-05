/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upadminapplication;

import static java.lang.Integer.parseInt;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

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
    
    public int getLoginDetails(String username, String password) {
        
        int count = 0;
        
        try { 
            String query = "select * from admin where userName = '" + username + "' AND password = '" + password + "'";
            
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
    
    public DefaultTableModel getLog() {
        
        int count = 0;
        
        DefaultTableModel model = new DefaultTableModel(new String[]{"LogID", "StudentNumber", "USBID", "ComputerID", "dateTime", "Status", "logType"}, 0);
        
        try { 
            String query = "select * from log";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) { 
                String lgID = rs.getString("LogID");
                String stNmb = rs.getString("StudentNumber");
                String usbID = rs.getString("USBID");
                String cmpID = rs.getString("ComputerID");
                String dtTm = rs.getString("dateTime");
                String st = rs.getString("Status");
                String lgType = rs.getString("logType");
                model.addRow(new Object[] {lgID, stNmb, usbID, cmpID, dtTm, st, lgType});
                count++;
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return model; 
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
    
    public List<String> getLabs() {
        List<String> labs = new ArrayList();
        
        try { 
            String query = "select  DISTINCT LabNo from computer";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) {
                labs.add(rs.getString("LabNo"));
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return labs;
        
    }
    
    public int getFoundUSBPerLab(String LabNo) {
        
        int count = 0;
        
        try { 
            String query = "select COUNT(ComputerID) AS labsNo from log where ComputerID LIKE '" + LabNo + "%' AND logType = 'Found a reported USB'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) { 
                count = parseInt(rs.getString("labsNo"));
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return count;
        
    }
    
    public void RegisterAdmin(String stuffNumber, String UserName, String email, String password) {
        
        try {
            
            String query = "insert into admin VALUES('" + stuffNumber + "', '" + UserName + "', '" + email +"', '" + password +"')";
            
            st.executeUpdate(query);
            
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }   
    }
    
    
    public void InsertLostStatus(String usbID) {
        
        try {
            
            String query = "update usb SET Status = 'Lost' WHERE USBID = '" + usbID + "'";
            
            st.executeUpdate(query);
            
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }   
    }
    
    public int getLostNumber() {
        
        int count = 0;
             
        try { 
            String query = "select distinct USBID from log where logType = 'Lost USB reporting'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) { 
                count++;
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return count; 
    }
    
    public void InsertComputer(String CompID, String LabNo) {
        
        try {
            
            String query = "insert into computer VALUES('" + CompID + "', '" + LabNo + "')";
            
            st.executeUpdate(query);
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }   
    }
    
    public int getFoundNumber() {
        
        int count = 0;
             
        try { 
            String query = "select distinct USBID from log where logType = 'Found a reported USB'";
            
            rs = st.executeQuery(query);
            
            while(rs.next()) { 
                count++;
            }
            
        }catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        
        return count; 
    }
    
    
}
