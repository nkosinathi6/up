/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upclientapplication;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.*;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JOptionPane;

/**
 *
 * @author Nkosinathi
 */
public class MyUSB extends javax.swing.JFrame {
    
    private JLabel jlb;
    private JLabel jlbDesc;
    private JPanel jpan;
    private JButton jBtn;
    private JButton jExtBtn;
    private ReportButtonHandler rbHandler;
    private ExitButtonHandler exHandler;
    private DBAccess db = new DBAccess();
    
    private Email em;
    
    private List<String> USBList;
    
    
    public MyUSB() {
        
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        String userName = LoginScreen.userNameVal;
        String password = LoginScreen.passwordVal;
        
        //String loggedUser = System.getProperty("user.name");
        
        String studNumber = db.getStudNumberByName(userName, password);
        
        HashMap<String, String> map = db.getStudentUSBs(studNumber);
        
        jlb = new JLabel("Manage My USBs");
        jlbDesc = new JLabel("Report your lost USB flash drive");
        jpan = new JPanel();
        
        
        setLayout(null);
        
        jlb.setBounds(50, 20, 150, 30);
        jlbDesc.setBounds(50, 75, 250, 30);
        jpan.setBounds(50, 110, 350, 300);
        jpan.setOpaque(true);
        jpan.setBackground(Color.lightGray);
        
        jpan.setLayout(new BoxLayout(jpan, Y_AXIS));
       
        add(jlb);
        add(jlbDesc);
        add(jpan);
        
        int num = map.size();
        
        JCheckBox jBoxs[] = new JCheckBox[num];
        
        int i = 0;
            
        for (String s : map.keySet()) {
            
            jBoxs[i] = new JCheckBox(s);
            jBoxs[i].setMargin(new Insets(2,2,2,2));
            jBoxs[i].setBackground(Color.lightGray);
            jpan.add(jBoxs[i]);
            
            i++;
        }
        
        jExtBtn = new JButton("Exit");
        exHandler = new ExitButtonHandler();
        jExtBtn.addActionListener(exHandler);
        jExtBtn.setBorderPainted(false);
        jExtBtn.setBackground(new Color(204, 204, 204));
        jExtBtn.setBounds(50, 450, 85, 30);
        add(jExtBtn);
        
        jBtn = new JButton("Report");
        rbHandler = new ReportButtonHandler();
        jBtn.addActionListener(rbHandler);
        jBtn.setBorderPainted(false);
        jBtn.setBackground(new Color(204, 204, 204));
        jBtn.setBounds(313, 450, 85, 30);
        add(jBtn);
       
    }
    
    private int count = 0;
    private String emailUSBName = "";
    
    private class ReportButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Component[] comps = jpan.getComponents();
            
            USBList = new ArrayList();
            
            for (Component comp : comps) {
                if (comp instanceof JCheckBox) {
                    JCheckBox box = (JCheckBox)comp;
                    
                    if (box.isSelected()) {
                        db.InsertLostStatus(box.getText());
                        USBList.add(box.getText());
                        count++;
                    }
                    
                }
            }
            
            int i = 0;
            
            for (String usb : USBList) {
                
                i++;
                
                if (count > 1) {
                    
                    if (i == count) {
                        emailUSBName += " and " + usb;
                    } else {
                        emailUSBName += ", " + usb;
                    }
                
                } else {
                    emailUSBName += usb;
                }  
               
            }
            
            em = new Email();
            String username = System.getProperty("user.name");
                        
            try {
                em.reportLostUSB(username, emailUSBName, username);
            } catch (IOException ex) {
                Logger.getLogger(MyUSB.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            try {
                
                InetAddress addr;
                
                addr = InetAddress.getLocalHost();
                String hostname = addr.getHostName();
                String ip = addr.toString();
                
                db.InsertLog("", hostname, username, "Lost USB reporting");
                
                String labNo = hostname.substring(0, 4);
                
                db.InsertComputer(ip, labNo);
                
            } catch (UnknownHostException ex) {
                Logger.getLogger(MyUSB.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
            String msg = "Your USB has been reported to the Admin!";
            
            JOptionPane.showMessageDialog(null, msg, "up", JOptionPane.INFORMATION_MESSAGE);
            
            
            for (Component comp : comps) {
                if (comp instanceof JCheckBox) {
                    JCheckBox box = (JCheckBox)comp;
                    
                    if (box.isSelected()) {
                        box.setSelected(false);
                    }
                    
                }
            }
            
        }
    }
    
    private class ExitButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            MyUSB frm = new MyUSB();
            frm.hide();
        }
    }
    
}
