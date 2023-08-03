
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Kendaraan extends javax.swing.JInternalFrame {
Connection koneksi;
PreparedStatement pst;
    /**
     * Creates new form Kendaraan1
     */
    public Kendaraan() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        koneksi = DatabaseConnection.getKoneksi("localhost","3306","root","","sawargi_rent_car");
        showData();
    }
String jenisf,statusf;
    DefaultTableModel dtm; 
    public void showData(){
        String[] kolom={"JENIS","NAMA","NOPOL","MERK","TAHUN","HARGA","STATUS"};
 
        dtm = new DefaultTableModel(null,kolom);
        try{
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM tb_kendaran";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String Jenis = rs.getString("Jenis");
                String Nama = rs.getString("Nama");
                String Nopol = rs.getString("Nopol");
                String Merk = rs.getString("Merk");
                String Tahun = rs.getString("Tahun");
                String Harga = rs.getString("Harga");
                String Status = rs.getString("Status");
                
                dtm.addRow(new String[]{Jenis,Nama,Nopol,Merk,Tahun,Harga,Status});
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        tb_kendaraan.setModel(dtm);
    }
    public void searchData(){
        String[] kolom={"JENIS","NAMA","NOPOL","MERK","TAHUN","HARGA","STATUS"};
 
        dtm = new DefaultTableModel(null,kolom);
        try{
            ResultSet rs = koneksi.createStatement().executeQuery("SELECT * From tb_kendaran WHERE Nopol LIKE '%"+search.getText()+"%'OR Nama LIKE '%"+search.getText()+"%'");
            while(rs.next()){
                String Jenis = rs.getString("Jenis");
                String Nama = rs.getString("Nama");
                String Nopol = rs.getString("Nopol");
                String Merk = rs.getString("Merk");
                String Tahun = rs.getString("Tahun");
                String Harga = rs.getString("Harga");
                String Status = rs.getString("Status");
                
                dtm.addRow(new String[]{Jenis,Nama,Nopol,Merk,Tahun,Harga,Status});
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        tb_kendaraan.setModel(dtm);
    }
    
    private void update(){
    try{
        String sql ="select * from tb_kendaran";
        pst=koneksi.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        tb_kendaraan.setModel(DbUtils.resultSetToTableModel(rs));
    }catch (Exception ex)
    { }
    showData();
  }

    public void jenis(){
    if(J.getSelectedItem()=="Motor")
    jenisf="Motor";   
    if(J.getSelectedItem()=="Mobil")
    jenisf="Motor";   
    }
    
    public void status(){
    if(J.getSelectedItem()=="Motor")
    statusf="Ada";   
    if(J.getSelectedItem()=="Mobil")
    statusf="Keluar";   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        J = new javax.swing.JComboBox<>();
        N = new javax.swing.JTextField();
        NP = new javax.swing.JTextField();
        M = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        T = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        H = new javax.swing.JTextField();
        S = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_kendaraan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        search = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(1100, 640));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 490));

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel3.setText("NO POLISI :");

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel4.setText("MERK: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel5.setText("TAHUN :");

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel6.setText("HARGA :");

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel7.setText("STATUS :");

        J.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        J.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Motor", "Mobil" }));
        J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JActionPerformed(evt);
            }
        });

        N.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        NP.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        NP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NPActionPerformed(evt);
            }
        });

        M.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel1.setText("JENIS :");

        T.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel2.setText("NAMA :");

        H.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        S.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        S.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ada", "Keluar" }));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/add.png"))); // NOI18N
        jButton1.setText("TAMBAH");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/pencil.png"))); // NOI18N
        jButton2.setText("EDIT");
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/delete.png"))); // NOI18N
        jButton3.setText("HAPUS");
        jButton3.setBorderPainted(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel8.setText("CARI :");

        tb_kendaraan.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_kendaraan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_kendaraanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_kendaraan);

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("KENDARAAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(0, 45));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        search.setBackground(new java.awt.Color(153, 204, 255));
        search.setFont(new java.awt.Font("Segoe UI Historic", 0, 14)); // NOI18N
        search.setBorder(null);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addComponent(J, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jLabel5)
                .addGap(23, 23, 23)
                .addComponent(T, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jLabel6)
                .addGap(24, 24, 24)
                .addComponent(H, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NP, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jLabel7)
                .addGap(22, 22, 22)
                .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton1)
                .addGap(27, 27, 27)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1064, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(J, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(H, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(NP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7)
                    .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jenis();
        status();
        try{
            String sql = " INSERT INTO tb_kendaran VALUES('" + jenisf + "','"
            + N.getText() + "','"
            + NP.getText() + "','"
            + T.getText() + "','"
            + M.getText() + "','"
            + H.getText() + "','"
            + statusf + "')";

            pst = koneksi.prepareStatement(sql);
            pst.execute();
            showData();
            JOptionPane.showMessageDialog(null, "Proses Simpan Berhasil");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Isi Data Dengan Benar!", "Error", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jenis();
        status();
        try{
            String sql = "UPDATE tb_kendaran SET `Jenis`='" + J.getSelectedItem()
            + "',`Nama`='" + N.getText()
            + "',`Merk`='" + M.getText()
            + "',`Tahun`='" + T.getText()
            + "',`Harga`='" + H.getText()
            + "',`Status`='" + S.getSelectedItem()
            + "' WHERE `Nopol`= '" + NP.getText() + "'";
            pst = koneksi.prepareStatement (sql);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Proses Edit Berhasil");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        update();
        showData(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            String sql ="DELETE FROM `tb_kendaran` WHERE `Nopol`='"+NP.getText()+"'";
            pst=koneksi.prepareStatement(sql);
            pst.executeUpdate();

            showData();
            N.setText("");
            NP.setText("");
            M.setText("");
            T.setText("");
            H.setText("");

            JOptionPane.showMessageDialog(null, "berhasil di hapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "gagal di hapus");
        }
        showData();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tb_kendaraanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_kendaraanMouseClicked
        J.setSelectedItem(String.valueOf(tb_kendaraan.getValueAt(tb_kendaraan.getSelectedRow(),0)));
        N.setText(String.valueOf(tb_kendaraan.getValueAt(tb_kendaraan.getSelectedRow(),1)));
        NP.setText(String.valueOf( tb_kendaraan.getValueAt(tb_kendaraan.getSelectedRow(),2)));
        M.setText(String.valueOf( tb_kendaraan.getValueAt(tb_kendaraan.getSelectedRow(),3)));
        T.setText(String.valueOf( tb_kendaraan.getValueAt(tb_kendaraan.getSelectedRow(),4)));
        H.setText(String.valueOf( tb_kendaraan.getValueAt(tb_kendaraan.getSelectedRow(),5)));
        S.setSelectedItem(String.valueOf(tb_kendaraan.getValueAt(tb_kendaraan.getSelectedRow(),6)));        // TODO add your handling code here:
    }//GEN-LAST:event_tb_kendaraanMouseClicked

    private void NPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NPActionPerformed

    private void JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        searchData();        // TODO add your handling code here:
    }//GEN-LAST:event_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField H;
    private javax.swing.JComboBox<String> J;
    private javax.swing.JTextField M;
    private javax.swing.JTextField N;
    private javax.swing.JTextField NP;
    private javax.swing.JComboBox<String> S;
    private javax.swing.JTextField T;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField search;
    private javax.swing.JTable tb_kendaraan;
    // End of variables declaration//GEN-END:variables
}