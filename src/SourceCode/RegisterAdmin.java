package SourceCode;

import static SourceCode.PenjualanAdmin.txtHarga;
import static SourceCode.PenjualanAdmin.txtIDBarang;
import static SourceCode.PenjualanAdmin.txtNamaBarang;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author rafli
 */
public class RegisterAdmin extends javax.swing.JFrame {

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    private void autonumber(){
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM login_admin ORDER BY id_loginadmin DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String id_loginadmin = r.getString("id_loginadmin").substring(2);
                String TR = "" +(Integer.parseInt(id_loginadmin)+1);
                String Nol = "";
                
                if(TR.length()==1)
                {Nol = "000";}
                else if(TR.length()==2)
                {Nol = "00";}
                else if(TR.length()==3)
                {Nol = "0";}
                else if(TR.length()==4)
                {Nol = "";}
                txtID.setText("LGN" + Nol + TR);
            } else {
                txtID.setText("LGN0001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }
    
    public void utama(){
        txtID.setText("");
        txtNamaLengkap.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        autonumber();
    }
    
    public RegisterAdmin() {
        initComponents();
        
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        utama();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        txtID = new javax.swing.JTextField();
        txtNamaLengkap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.0F);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtEmail.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtEmail.setBorder(null);
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 567, 470, 50));

        txtPassword.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtPassword.setBorder(null);
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 706, 470, 50));

        txtID.setEditable(false);
        txtID.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtID.setBorder(null);
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        getContentPane().add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 470, 50));

        txtNamaLengkap.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtNamaLengkap.setBorder(null);
        txtNamaLengkap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaLengkapActionPerformed(evt);
            }
        });
        getContentPane().add(txtNamaLengkap, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 470, 50));

        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 840, 640, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TemplateAdmin/Register Admin 1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        for (double i = 0.0; i <= 1.0; i = i + 0.1){
            String val = i + "";
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try{
                Thread.sleep(50);
            }
            catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtNamaLengkapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaLengkapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaLengkapActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        try {
        stat = con.createStatement();
    
    stat.executeUpdate("INSERT INTO login_admin VALUES('" 
        + txtID.getText() + "','"
        + txtNamaLengkap.getText() + "','"
        + txtEmail.getText() + "','"
        + txtPassword.getText() + "')");
    JOptionPane.showMessageDialog(null, "Simpan Berhasil");
    txtID.setText("");
    txtNamaLengkap.setText("");
    txtEmail.setText("");
    txtPassword.setText("");
    this.dispose();
    new LoginAdmin().setVisible(true);

  } catch (Exception e) {
    e.printStackTrace();
  }
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(RegisterAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNamaLengkap;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
