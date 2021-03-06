/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upclientapplication;

import java.io.File;
import javax.crypto.Cipher;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Nkosinathi
 */
public class EncryptionConfirmation extends javax.swing.JFrame {
    
    File selectedFile = null;
    
    /**
     * Creates new form EncryptionConfirmation
     */
    public EncryptionConfirmation() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public void EncryptionM(File selectedFile) {
        
        this.selectedFile = selectedFile;
        
        lblPath.setText("This is the file you are going to encrypt: " + selectedFile.getName());
    }
    
    public void EncryptionAction(File selectedFile) {
        
        String key = "Nantsi i Himitsu";
        File inputFile = selectedFile;
            
        File encryptedFile = new File(selectedFile.getAbsolutePath().toString() + ".encrypted");

        try {
            Crypto.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
               
            selectedFile.delete(); 
                 
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }  
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEncrypt = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblPath = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEncrypt.setText("Encrypt");
        btnEncrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncryptActionPerformed(evt);
            }
        });

        btnExit.setText("Cancil");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblPath.setText("This is the file you are going to encrypt: ");
        lblPath.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnEncrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPath)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblPath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEncrypt)
                    .addComponent(btnExit))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.hide();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnEncryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncryptActionPerformed
        
        EncryptionAction(selectedFile);
        
        if (selectedFile.isDirectory())
        {
            File[] dirList = selectedFile.listFiles();
                
            for (File child : dirList) {
                if (!child.getName().endsWith(".encrypted"))
                    EncryptionAction(child);
            }
            
            String msg = "Your files has successfully been encrypted";
          
            JOptionPane.showMessageDialog(null, msg, "up", JOptionPane.INFORMATION_MESSAGE);
            
            this.hide();
            CryptoFrame CF = new CryptoFrame();
            CF.hide();
        }
        else {
            
            if (!selectedFile.getName().endsWith(".encrypted"))
                EncryptionAction(selectedFile);
            
            String msg = "Your file has successfully been encrypted";
          
            JOptionPane.showMessageDialog(null, msg, "up", JOptionPane.INFORMATION_MESSAGE);
            
            this.hide();
            CryptoFrame CF = new CryptoFrame();
            CF.hide();
        }
        
    }//GEN-LAST:event_btnEncryptActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EncryptionConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EncryptionConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EncryptionConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EncryptionConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EncryptionConfirmation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEncrypt;
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel lblPath;
    // End of variables declaration//GEN-END:variables
}
