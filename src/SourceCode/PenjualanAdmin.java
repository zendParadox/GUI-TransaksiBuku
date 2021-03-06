/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SourceCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PenjualanAdmin extends javax.swing.JFrame {

    String Tanggal;
    private DefaultTableModel model;
    
    public void totalBiaya(){
        int jumlahBaris = jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
        }
        txtTotalBayar.setText(String.valueOf(totalBiaya));
        txtTampil.setText("Rp "+ totalBiaya +",00");
    }
    
    private void autonumber(){
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM penjualan ORDER BY NoFaktur DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()) {
                String NoFaktur = r.getString("NoFaktur").substring(2);
                String TR = "" +(Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if(TR.length()==1)
                {Nol = "000";}
                else if(TR.length()==2)
                {Nol = "00";}
                else if(TR.length()==3)
                {Nol = "0";}
                else if(TR.length()==4)
                {Nol = "";}
                txtNoTransaksi.setText("TR" + Nol + TR);
            } else {
                txtNoTransaksi.setText("TR0001");
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("autonumber error");
        }
    }
    
    public void loadData(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{
            txtNoTransaksi.getText(),
            txtIDBarang.getText(),
            txtNamaBarang.getText(),
            txtJumlah.getText(),
            txtHarga.getText(),
            txtTotalBayar.getText()
        });
    }
    
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
    }
    
    public void utama(){
        txtNoTransaksi.setText("");
        txtIDBarang.setText("");
        txtNamaBarang.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
        autonumber();
    }
    
     public void clear(){
        txtIDCustomer.setText("");
        txtNamaCustomer.setText("");
        txtTotalBayar.setText("0");
        txtBayar.setText("0");
        txtKembalian.setText("0");
        txtTampil.setText("0");
    }
     
    public void clear2(){
        txtIDBarang.setText("");
        txtNamaBarang.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
    }
    
     public void tambahTransaksi(){
        int jumlah, harga, total;
        
        jumlah = Integer.valueOf(txtJumlah.getText());
        harga = Integer.valueOf(txtHarga.getText());
        total = jumlah * harga;
        
        txtTotalBayar.setText(String.valueOf(total));
        
        loadData();
        totalBiaya();
        clear2();
        txtIDBarang.requestFocus();
    }
     
     
    
    public PenjualanAdmin() {
        initComponents();
        
        model = new DefaultTableModel();
        
        jTable1.setModel(model);
        
        model.addColumn("No Transaksi");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");
        
        utama();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        
        txtTanggal.setText(s.format(date));
        txtTotalBayar.setText("0");
        txtBayar.setText("0");
        txtKembalian.setText("0");
        txtIDCustomer.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtJumlah = new javax.swing.JTextField();
        txtNoTransaksi = new javax.swing.JTextField();
        txtIDCustomer = new javax.swing.JTextField();
        txtKembalian = new javax.swing.JTextField();
        txtNamaCustomer = new javax.swing.JTextField();
        txtIDBarang = new javax.swing.JTextField();
        txtTampil = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtTotalBayar = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JLabel();
        btnHapus = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JLabel();
        btnCari = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 470, 1080, 340));

        txtJumlah.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtJumlah.setBorder(null);
        txtJumlah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtJumlahMouseClicked(evt);
            }
        });
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        getContentPane().add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1685, 385, 150, 42));

        txtNoTransaksi.setEditable(false);
        txtNoTransaksi.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtNoTransaksi.setBorder(null);
        getContentPane().add(txtNoTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 510, 42));

        txtIDCustomer.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtIDCustomer.setBorder(null);
        getContentPane().add(txtIDCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 193, 510, 42));

        txtKembalian.setEditable(false);
        txtKembalian.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtKembalian.setBorder(null);
        getContentPane().add(txtKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1335, 990, 290, 42));

        txtNamaCustomer.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtNamaCustomer.setBorder(null);
        txtNamaCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaCustomerActionPerformed(evt);
            }
        });
        getContentPane().add(txtNamaCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 256, 510, 42));

        txtIDBarang.setEditable(false);
        txtIDBarang.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtIDBarang.setBorder(null);
        getContentPane().add(txtIDBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 385, 260, 42));

        txtTampil.setEditable(false);
        txtTampil.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtTampil.setBorder(null);
        txtTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTampilActionPerformed(evt);
            }
        });
        getContentPane().add(txtTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 838, 260, 50));

        txtTanggal.setEditable(false);
        txtTanggal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtTanggal.setBorder(null);
        getContentPane().add(txtTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1585, 130, 180, 42));

        txtHarga.setEditable(false);
        txtHarga.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtHarga.setBorder(null);
        getContentPane().add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1335, 385, 290, 42));

        txtTotalBayar.setEditable(false);
        txtTotalBayar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtTotalBayar.setBorder(null);
        getContentPane().add(txtTotalBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1335, 842, 290, 42));

        txtBayar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtBayar.setBorder(null);
        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });
        getContentPane().add(txtBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1335, 916, 290, 42));

        txtNamaBarang.setEditable(false);
        txtNamaBarang.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtNamaBarang.setBorder(null);
        getContentPane().add(txtNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 385, 290, 42));

        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 370, 70));

        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahMouseClicked(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 480, 170, 60));

        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHapusMouseClicked(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1670, 560, 170, 60));

        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSimpanMouseClicked(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 840, 170, 50));

        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariMouseClicked(evt);
            }
        });
        getContentPane().add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 380, 60, 50));

        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1800, 30, 90, 30));

        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 60, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TemplateAdmin/Penjualan Admin 1.png"))); // NOI18N
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
        new TambahDataAdmin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtJumlahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJumlahMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahMouseClicked

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        tambahTransaksi();
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void btnTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahMouseClicked
        tambahTransaksi();
    }//GEN-LAST:event_btnTambahMouseClicked

    private void btnHapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int row = jTable1.getSelectedRow();
        model.removeRow(row);
        totalBiaya();
        txtBayar.setText("0");
        txtKembalian.setText("0");
    }//GEN-LAST:event_btnHapusMouseClicked

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
         int total, bayar, kembalian;
        
        total = Integer.valueOf(txtTotalBayar.getText());
        bayar = Integer.valueOf(txtBayar.getText());
        
        if (total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar - total;
            txtKembalian.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_txtBayarActionPerformed

    private void btnSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseClicked
         DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String noTransaksi = txtNoTransaksi.getText();
        String tanggal = txtTanggal.getText();
        String idCustomer = txtIDCustomer.getText();
        String total = txtTotalBayar.getText();
        
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "INSERT INTO penjualan VALUES (?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, noTransaksi);
            p.setString(2, tanggal);
            p.setString(3, idCustomer);
            p.setString(4, total);
            p.executeUpdate();
            p.close();
        } catch (Exception e) {
            System.out.println("simpan penjualan error");
        }
        
        try {
            Connection c = koneksi.getKoneksi();
            int baris = jTable1.getRowCount();
            
            for (int i = 0; i < baris; i++) {
                String sql = "INSERT INTO penjualanrinci(NoFaktur, ID_Barang, Nama_Barang, Jumlah, Harga, Total) VALUES('"
                        + jTable1.getValueAt(i, 0) +"','"+ jTable1.getValueAt(i, 1) +"','"+ jTable1.getValueAt(i, 2) 
                        +"','"+ jTable1.getValueAt(i, 3) +"','"+ jTable1.getValueAt(i, 4) +"','"+ jTable1.getValueAt(i, 5) 
                        +"')";
                PreparedStatement p = c.prepareStatement(sql);
                p.executeUpdate();
                p.close();
            }
        } catch (Exception e) {
            System.out.println("simpan penjualanrinci error");
        }
        clear();
        utama();
        autonumber();
        kosong();
        txtTampil.setText("Rp. 0");
    }//GEN-LAST:event_btnSimpanMouseClicked

    private void btnCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseClicked
//        ListBarang a = new ListBarang();
//        a.setVisible(true);
        
        new ListBarangAdmin().setVisible(true);
    }//GEN-LAST:event_btnCariMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int pernyataan = JOptionPane.showConfirmDialog(null, "Anda Yakin Akan Logout?","Konfirmasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
            if (pernyataan == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Anda Berhasil Logout");
                this.dispose();
                new LoginAdmin().setVisible(true);
            }else{
                this.dispose();
                new PenjualanAdmin().setVisible(true);
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
                new PenjualanAdmin().setVisible(true);
            }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txtNamaCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaCustomerActionPerformed
        
    }//GEN-LAST:event_txtNamaCustomerActionPerformed

    private void txtTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTampilActionPerformed
//        totalBiaya();
    }//GEN-LAST:event_txtTampilActionPerformed

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
            java.util.logging.Logger.getLogger(PenjualanAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenjualanAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenjualanAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenjualanAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PenjualanAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCari;
    private javax.swing.JLabel btnHapus;
    private javax.swing.JLabel btnSimpan;
    private javax.swing.JLabel btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBayar;
    public static javax.swing.JTextField txtHarga;
    public static javax.swing.JTextField txtIDBarang;
    private javax.swing.JTextField txtIDCustomer;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKembalian;
    public static javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNamaCustomer;
    private javax.swing.JTextField txtNoTransaksi;
    private javax.swing.JTextField txtTampil;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTotalBayar;
    // End of variables declaration//GEN-END:variables
}
