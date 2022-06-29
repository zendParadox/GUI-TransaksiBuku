/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.rmi.activation.Activatable.register;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class TambahDataAdmin extends javax.swing.JFrame {

    koneksi koneksi = new koneksi();
    
    private DefaultTableModel model;
    
    private void autonumber(){
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM barang ORDER BY ID_Barang DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("ID_Barang").substring(2);
                String BR = "" +(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if (BR.length()==1) 
                    {Nol = "00";}
                else if(BR.length()==2)
                    {Nol = "0";}
                else if(BR.length()==3)
                    {Nol = "";}
                
                txtIDBarang.setText("BR" + Nol + BR);  
            }else{
                txtIDBarang.setText("BR001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }
    
    public void clear(){
        txtNamaBarang.setText("");
        txtHargaBeli.setText("");
        txtHargaJual.setText("");
        txtStok.setText("");
        txtPenulis.setText("");
    }
    
    public void loadData(){
        model.getDataVector().removeAllElements();
        
        model.fireTableDataChanged();
        
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            
            String sql = "SELECT * FROM barang";
            ResultSet r = s.executeQuery(sql);
            
            while (r.next()) {
                Object[] o = new Object[7];
                o [0] = r.getString("ID_Barang");
                o [1] = r.getString("Nama_Barang");
                o [2] = r.getString("Jenis");
                o [3] = r.getString("Penulis");
                o [4] = r.getString("HargaBeli");
                o [5] = r.getString("HargaJual");
                o [6] = r.getString("Stok");
                
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("terjadi kesalahan");
        }
    }
    
     public void cariData(){
        DefaultTableModel tabel = new DefaultTableModel();
        
        tabel.addColumn("ID Barang");
        tabel.addColumn("Nama Barang");
        tabel.addColumn("Jenis");
        tabel.addColumn("Penulis");
        tabel.addColumn("HargaBeli");
        tabel.addColumn("HargaJual");
        tabel.addColumn("Stok");
        
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "Select * from barang where ID_Barang like '%" + txtCariData.getText() + "%'" +
                    "or Nama_Barang like '%" + txtCariData.getText() + "%'";
            Statement stat = c.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {                
                tabel.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                });
            }
            jTable1.setModel(tabel);
            loadData();
        } catch (Exception e) {
            System.out.println("Cari Data Error");
        }finally{
        }
    }
    
    public TambahDataAdmin() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        model = new DefaultTableModel();
        
        jTable1.setModel(model);
        
        model.addColumn("ID_Barang");
        model.addColumn("Nama_Barang");
        model.addColumn("Jenis");
        model.addColumn("Ukuran");
        model.addColumn("HargaBeli");
        model.addColumn("HargaJual");
        model.addColumn("Stok");
        
        loadData();
        autonumber();
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
    }
    
    public void excel() throws FileNotFoundException, IOException{
        try{
        Class.forName("com.mysql.jdbc.Driver");
        com.mysql.jdbc.Connection koneksi = (com.mysql.jdbc.Connection)
       DriverManager.getConnection("jdbc:mysql://localhost:3306/transaksi_tugasutama","root","");;
        com.mysql.jdbc.Statement statement = (com.mysql.jdbc.Statement)
       koneksi.createStatement();
        FileOutputStream fileOut;
        // Hasil Export
        fileOut = new FileOutputStream("C:/Users/rafli/Documents/Tugas Utama/Tugas Utama.xls");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("Tugas Utama");
        // Nama Field
        Row row1 = worksheet.createRow((short)0);
        row1.createCell(0).setCellValue("ID Barang");
        row1.createCell(1).setCellValue("Nama Barang");
        row1.createCell(2).setCellValue("Jenis");
        row1.createCell(3).setCellValue("Penulis");
        row1.createCell(4).setCellValue("Harga Beli");
        row1.createCell(5).setCellValue("Harga Jual");
        row1.createCell(6).setCellValue("Stok");
        Row row2 ;
        ResultSet rs = statement.executeQuery("select* from barang");
        while(rs.next()){
        int a = rs.getRow();
       row2 = worksheet.createRow((short)a);
        // Sesuaikan dengan Jumlah Field
        row2.createCell(0).setCellValue(rs.getString(1));
        row2.createCell(1).setCellValue(rs.getString(2));
        row2.createCell(2).setCellValue(rs.getString(3));
        row2.createCell(3).setCellValue(rs.getString(4));
        row2.createCell(4).setCellValue(rs.getString(5));
        row2.createCell(5).setCellValue(rs.getString(6));
        row2.createCell(6).setCellValue(rs.getString(7));

        }
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        rs.close();
        statement.close();
        koneksi.close();
        JOptionPane.showMessageDialog(this,"Save to Excel Success !!");
        }catch(ClassNotFoundException e){
        System.out.println(e);
        }catch(SQLException ex){
        System.out.println(ex);
        }catch(IOException ioe){
        System.out.println(ioe);
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

        jLabel2 = new javax.swing.JLabel();
        txtCariData = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtIDBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtPenulis = new javax.swing.JTextField();
        txtHargaBeli = new javax.swing.JTextField();
        txtHargaJual = new javax.swing.JTextField();
        cbJenis = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSimpan = new javax.swing.JLabel();
        btnEdit = new javax.swing.JLabel();
        btnHapus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
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

        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 370, 70));

        txtCariData.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtCariData.setBorder(null);
        txtCariData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariDataActionPerformed(evt);
            }
        });
        getContentPane().add(txtCariData, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 138, 510, 40));

        txtStok.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtStok.setBorder(null);
        getContentPane().add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 397, 340, 40));

        txtIDBarang.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtIDBarang.setBorder(null);
        getContentPane().add(txtIDBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 235, 340, 40));

        txtNamaBarang.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtNamaBarang.setBorder(null);
        txtNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaBarangActionPerformed(evt);
            }
        });
        getContentPane().add(txtNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 316, 340, 40));

        txtPenulis.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtPenulis.setBorder(null);
        getContentPane().add(txtPenulis, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 478, 340, 40));

        txtHargaBeli.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtHargaBeli.setBorder(null);
        getContentPane().add(txtHargaBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 235, 340, 40));

        txtHargaJual.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtHargaJual.setBorder(null);
        getContentPane().add(txtHargaJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 316, 340, 40));

        cbJenis.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Novel", "Komik", "Biografi", "Karya Ilmiah" }));
        cbJenis.setBorder(null);
        getContentPane().add(cbJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, 370, 50));

        jTable1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 570, 1260, 400));

        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 1000, 170, 50));

        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });
        getContentPane().add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 1000, 170, 50));

        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 1000, 170, 50));

        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 60, 20));

        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1810, 30, 70, 30));

        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1000, 170, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TemplateAdmin/Tambah Data Barang 2.png"))); // NOI18N
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

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        new PenjualanAdmin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtCariDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariDataActionPerformed
        cariData();
    }//GEN-LAST:event_txtCariDataActionPerformed

    private void txtNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaBarangActionPerformed

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
         int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id = (String) model.getValueAt(i, 0);
        String nama = txtNamaBarang.getText();
        String jenis = (String)cbJenis.getSelectedItem();
        String penulis = txtPenulis.getText();
        String hargaBeli = txtHargaBeli.getText();
        String hargaJual = txtHargaJual.getText();
        String stok = txtStok.getText();
        
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "UPDATE barang SET Nama_Barang = ?, Jenis = ?, Penulis = ?, HargaBeli = ?, HargaJual = ?, Stok = ? WHERE ID_Barang = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, nama);
            p.setString(2, jenis);
            p.setString(3, penulis);
            p.setString(4, hargaBeli);
            p.setString(5, hargaJual);
            p.setString(6, stok);
            p.setString(7, id);
            
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Terubah");
            btnSimpan.setEnabled(true);
            btnEdit.setEnabled(false);
            btnHapus.setEnabled(false);
            clear();
        } catch (Exception e) {
            System.out.println("update error");
        }finally{
            loadData();
            autonumber();
        }
    }//GEN-LAST:event_btnEditMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        
        String id = (String) model.getValueAt(i, 0);
        
        int pernyataan = JOptionPane.showConfirmDialog(null, "Yakin Data Akan Dihapus","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pernyataan== JOptionPane.OK_OPTION) {
            try {
                Connection c = koneksi.getKoneksi();
                String sql = "DELETE FROM barang WHERE ID_Barang = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, id);
                p.executeUpdate();
                p.close();
                JOptionPane.showMessageDialog(null, "Data Terhapus");
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan");
            }finally{
                btnSimpan.setEnabled(true);
                btnEdit.setEnabled(false);
                btnHapus.setEnabled(false);
                loadData();
                autonumber();
                clear();
            }
        }
        if (pernyataan== JOptionPane.CANCEL_OPTION) {
            
        }
    }//GEN-LAST:event_btnHapusMouseClicked

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
         String id = txtIDBarang.getText();
        String nama = txtNamaBarang.getText();
        String jenis = (String)cbJenis.getSelectedItem();
        String penulis = txtPenulis.getText();
        String hargaBeli = txtHargaBeli.getText();
        String hargaJual = txtHargaJual.getText();
        String stok = txtStok.getText();
        
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "INSERT INTO BARANG VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, id);
            p.setString(2, nama);
            p.setString(3, jenis);
            p.setString(4, penulis);
            p.setString(5, hargaBeli);
            p.setString(6, hargaJual);
            p.setString(7, stok);
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
            loadData();
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan");
        }finally{
            autonumber();
            clear();
        }
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         btnSimpan.setEnabled(false);
        btnEdit.setEnabled(true);
        btnHapus.setEnabled(true);
        
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            return;
        }
        
        String id = (String) model.getValueAt(i, 0);
        String nama = (String) model.getValueAt(i, 1);
        String jenis = (String) model.getValueAt(i, 2);
        String penulis = (String) model.getValueAt(i, 3);
        String hargaBeli = (String) model.getValueAt(i, 4);
        String hargaJual = (String) model.getValueAt(i, 5);
        String stok = (String) model.getValueAt(i, 6);
        
        txtIDBarang.setText(id);
        txtNamaBarang.setText(nama);
        cbJenis.setSelectedItem(jenis);
        txtPenulis.setText(penulis);
        txtHargaBeli.setText(hargaBeli);
        txtHargaJual.setText(hargaJual);
        txtStok.setText(stok);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        cariData();
    }//GEN-LAST:event_jTable1KeyTyped

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int pernyataan = JOptionPane.showConfirmDialog(null, "Anda Yakin Akan Logout?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if (pernyataan == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Anda Berhasil Logout");
                this.dispose();
                new LoginAdmin().setVisible(true);
            }else{
                this.dispose();
                new TambahDataAdmin().setVisible(true);
            }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        int pernyataan = JOptionPane.showConfirmDialog(null, "Anda Yakin Akan Logout?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if (pernyataan == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Anda Berhasil Logout");
                this.dispose();
                new LoginAdmin().setVisible(true);
            }else{
                this.dispose();
                new TambahDataAdmin().setVisible(true);
            }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        try {
            excel();
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(TambahDataAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TambahDataAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TambahDataAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TambahDataAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TambahDataAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnEdit;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JComboBox<String> cbJenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCariData;
    private javax.swing.JTextField txtHargaBeli;
    private javax.swing.JTextField txtHargaJual;
    private javax.swing.JTextField txtIDBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
