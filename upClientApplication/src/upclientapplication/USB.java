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
public class USB {

    private String USBID;
    private String StudentNumber;
    private String friendlyName;
    private String Status;
    
    public String getUSBID() {
        return USBID;
    }

    
    public void setUSBID(String USBID) {
        this.USBID = USBID;
    }

    
    public String getStudentNumber() {
        return StudentNumber;
    }

    
    public void setStudentNumber(String StudentNumber) {
        this.StudentNumber = StudentNumber;
    }

    
    public String getFriendlyName() {
        return friendlyName;
    }

    
    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    
    public String getStatus() {
        return Status;
    }

    
    public void setStatus(String Status) {
        this.Status = Status;
    } 
    
    
}
