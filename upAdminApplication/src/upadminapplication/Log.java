/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upadminapplication;

/**
 *
 * @author Nkosinathi
 */
public class Log {
    
    private String LogID;
    private String StudentNumber;
    private String USBID;
    private String ComputerID;
    private String dateTime;
    private String Status;
    
    public Log() {
        
    }
    
    public Log(String LogID, String StudentNumber, String USBID, String ComputerID, String dateTime, String Status) {
        this.LogID = LogID;
        this.StudentNumber = StudentNumber;
        this.USBID = USBID;
        this.ComputerID = ComputerID;
        this.dateTime = dateTime;
        this.Status = Status;
    }

}
