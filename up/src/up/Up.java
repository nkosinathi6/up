/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up;

/**
 *
 * @author Nkosinathi
 */

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Up {
    
    static String letters[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        
    static File[] drives = new File[letters.length];
    static boolean[] isDrive = new boolean[letters.length];
    static boolean usbIDFound = false;
    static String usbIDFileName = "USBID.txt";
    
    public static void InitialDriveState()
    {
        //init the file objects and the initial drive state
        for ( int i = 0; i < letters.length; ++i )
        {
            drives[i] = new File(letters[i]+ ":/");
            isDrive[i] = drives[i].canRead();
        }
    }
    
    public static void AccessRemovableDrive()
    {
        while(true)
        {
            // check each drive
            for (int i = 0; i < letters.length; ++i)
            {
                boolean pluggedIn = drives[i].canRead();
                
                // if the state has changed output a message
                if ( pluggedIn != isDrive[i] )
                {
                    //If plugged in is true: A new drive has been plugged in
                    if ( pluggedIn )
                    {
                        String path=letters[i]+ "://";
                        File files=new File(path);
                        String allFiles[]=files.list();
                        
                        
                        //Loop through all the files on the drive
                        for(int k=0; k<allFiles.length; k++)
                        {
                            File f=new File(path+"/"+allFiles[k]);
                            
                            if(f.isFile())
                            {  
                                //Look if the password.txt file exist on the drive
                                if (f.getName().equals(usbIDFileName)) {
                                    usbIDFound = true;
                                }
                                
                            }
                        }
                        
                        if (usbIDFound)
                        {
                            String usbID = "";
                            String pth = letters[i] + ":\\USBID.txt";
                            
                            try { 
                                
                                //Getting the unique ID of the USB
                                Scanner inFile = new Scanner(new FileReader(pth));
                                
                                usbID = inFile.next();
                                
                                inFile.close();
                                
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Up.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Up.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                            //Searching if the USB had been reported as lost on the database
                            DBAccess db = new DBAccess();
                            
                            int foundLost = db.getLostUSBbyID(usbID);
                            
                            if (foundLost == 1) {
                                
                                try {
                                    //Take details of the comp, lab, student
                                    //Alert the Admin
                                    
                                    String hostname = "Unknown";
                                    String ip = "";
                                    
                                    InetAddress addr;
                                    addr = InetAddress.getLocalHost();
                                    hostname = addr.getHostName();
                                    ip = addr.toString();
                                    
                                    String loggedUser = System.getProperty("user.name");
                                    
                                    //Inserting a log information
                                    db.InsertLog(usbID, ip, loggedUser, "Found a reported USB");
                                    
                                    String labNo = hostname.substring(0, 4);
                                    
                                    try {
                                        db.InsertComputer(ip, labNo);
                                    } catch (Exception ec) {
                                        
                                    }
                                    
                                    
                                    List<String> emails = db.getAdminEmails();
                                    
                                    Email em = new Email();
                                    
                                    if (!emails.equals(null))
                                    {
                                        for (String email : emails)
                                        {
                                            em.sendEmail(loggedUser, hostname, ip, email);
                                        }
                                    }
                                    
                                    
                                } catch (UnknownHostException ex) {
                                    Logger.getLogger(Up.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Up.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                            else {
                                
                                
                                try {
                                    
                                    String ip = "";
                                    String userName = System.getProperty("user.name");
                                    
                                    InetAddress addr;
                                    addr = InetAddress.getLocalHost();
                                    String hostname = addr.getHostName();
                                    ip = addr.toString();
                
                                    db.InsertLog(usbID, ip, userName, "USB Insertion");
                                    
                                    String labNo = hostname.substring(0, 4);
                                    
                                    try {
                                        db.InsertComputer(ip, labNo);
                                    } catch (Exception ec) {
                                        
                                    }
                                    
                                    
                                    
                                } catch (UnknownHostException ex) {
                                    Logger.getLogger(Up.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                            
                        }
                        else
                        {
                            uniqueIDWarningForm uFrm = new uniqueIDWarningForm();
                            
                            String letter = letters[i];
                            
                            uFrm.GetDriveLetter(letter);
                            
                            uFrm.show();
                            
                        }
                    
                    }   
                    else
                    {
                        //System.exit(0);
                    }

                    isDrive[i] = pluggedIn;
                
                }
            }
            
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            { }
        }
        
    }
    
    
    public static void main(String[] args) {
        
        InitialDriveState();
        
        AccessRemovableDrive();
      
    }
    
}
