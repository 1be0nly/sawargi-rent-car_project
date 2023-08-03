
import java.sql.Connection;
import java.sql.DriverManager;
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
public class Pengembalian extends javax.swing.JInternalFrame {
Connection koneksi;
    PreparedStatement pst;
    /**
     * Creates new form Pengembalian
     */
    public Pengembalian() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        koneksi = DatabaseConnection.getKoneksi("localhost","3306","root","","sawargi_rent_car");
        showData();
//        update();
    }
    DefaultTableModel dtm; 
    public void showData(){
        String[] kolom={"NAMA","KENDARAAN","NOPOL","HARGA","SUPIR","TGL PINJAM","TGL KEMBALI","JUM HARI","TOTAL"};
 
        dtm = new DefaultTableModel(null,kolom);
        try{
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * FROM tb_transaksi_tmp";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
               // String id_trans = rs.getString("id_trans");
                String Nama = rs.getString("Nama");
                String Mobil = rs.getString("Kendaraan");
                String Nopol = rs.getString("Nopol");
                String Harga = rs.getString("Harga");
                String Supir = rs.getString("Supir");
                String pinjam = rs.getString("Tgl_pinjam");
                String kembali = rs.getString("Tgl_kembali");
                String jum_hari = rs.getString("jum_hari");
                String total = rs.getString("Total");
                
                dtm.addRow(new String[]{Nama,Mobil,Nopol,Harga,Supir,pinjam,kembali,jum_hari,total});
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        tb_kembali.setModel(dtm);
    }
//    private void update(){
//    try{
//        String sql ="select * from tb_transaksi_tmp";
//        pst=koneksi.prepareStatement(sql);
//        ResultSet rs = pst.executeQuery();
//        tb_kembali.setModel(DbUtils.resultSetToTableModel(rs));
//    }catch (Exception ex)
//    { }
//    showData();
 //   }
    public void tambahk(){
            try{
            String sql = " INSERT INTO tb_kembali VALUES('" + ID.getText() + "','" 
                    + N.getText() + "','" 
                    + K.getText() + "','"
                    + NP.getText() + "','"
                    + NS.getText() + "','"
                    + TS.getText() + "','"
                    + TK.getText() + "','"
                    + JH.getText() + "','"
                    + TH.getText() + "')";
           
            pst = koneksi.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Proses Simpan Berhasil");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Isi Data Dengan Benar!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
private void statuss(){
    try{       
    Statement s = koneksi.createStatement();
            String sql = "SELECT * FROM tb_supir WHERE `Nama` ='"+NS.getText()+"'";
            ResultSet rs = s.executeQuery(sql);
            if (NS.getText().equals("-")){
                statusk();
            }
            else if(rs.next()){
                if(NS.getText().equals(rs.getString("Nama"))){    
                try{
                    String update = "Ada";
                    String sqla = "update tb_supir set Status='"+update+"' where Nama='"+NS.getText()+"'";
                    pst=koneksi.prepareStatement(sqla);
                    pst.executeUpdate();
                }catch (SQLException e){
                    JOptionPane.showMessageDialog(null, "Data gagal diubah" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);}
                }statusk();
            }
    }
    catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());}
}
private void statusk(){
       try{
            String update = "Ada";
            String sqla = "update tb_kendaran set Status='"+update+"' where Nopol='"+NP.getText()+"'";
            pst=koneksi.prepareStatement(sqla);
            pst.executeUpdate();
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Data gagal diubah" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);}
       hapuss();
    }
private void hapuss(){
        try{
            String sql ="DELETE FROM `tb_transaksi_tmp` WHERE `Nopol`='"+NP.getText()+"'";
            pst=koneksi.prepareStatement(sql);
            pst.executeUpdate();
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Data gagal diubah" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);}
    }
private void clear(){
    ID.setText(null);
    N.setText(null);
    NP.setText(null);
    K.setText(null);
    TS.setText(null);
    TK.setText(null);
    JH.setText(null);
    TH.setText(null);
    NS.setText(null);
}
private void kode() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/sawargi_rent_car", "root", "");
            Statement s = cn.createStatement();

            String sql = "SELECT * FROM `tb_kembali` ORDER by `id_trans` desc";
            ResultSet r = s.executeQuery(sql);

            if (r.next()) {
                String no = r.getString("id_trans").substring(2);
                String AN = "" + (Integer.parseInt(no) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "00";
                } else if (AN.length() == 2) {
                    Nol = "0";
                } else if (AN.length() == 3) {
                    Nol = "";
                } 

                ID.setText("KT" + Nol + AN);
            } else {
                ID.setText("KT001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JH = new javax.swing.JTextField();
        TH = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TK = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TS = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        N = new javax.swing.JTextField();
        K = new javax.swing.JTextField();
        NP = new javax.swing.JTextField();
        ID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        NS = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_kembali = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1100, 640));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        JH.setEditable(false);
        JH.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        TH.setEditable(false);
        TH.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel6.setText("TANGGAL KEMBALI :");

        TK.setEditable(false);
        TK.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel4.setText("TOTAL HARGA :");

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel5.setText("TANGGAL SEWA :");

        TS.setEditable(false);
        TS.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel7.setText("JUMLAH HARI :");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/car.png"))); // NOI18N
        jButton1.setText("KEMBALIKAN ");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        N.setEditable(false);
        N.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        K.setEditable(false);
        K.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        NP.setEditable(false);
        NP.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        ID.setEditable(false);
        ID.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel1.setText("NAMA PEMINJAM :");

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel8.setText("ID TRANSAKSI :");

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel2.setText("KENDARAAN :");

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel3.setText("NO POLISI :");

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel9.setText("NAMA SUPIR :");

        NS.setEditable(false);
        NS.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N

        tb_kembali.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_kembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_kembaliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_kembali);

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

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

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("PENGEMBALIAN");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel8)
                .addGap(49, 49, 49)
                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jLabel5)
                .addGap(46, 46, 46)
                .addComponent(TS, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel9)
                .addGap(4, 4, 4)
                .addComponent(NS, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jLabel6)
                .addGap(29, 29, 29)
                .addComponent(TK, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NP, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JH, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TH, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(268, 268, 268)
                .addComponent(jButton1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(TS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(NS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(TK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tambahk();
        statuss();
        showData();
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_kembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_kembaliMouseClicked
       //ID.setText(String.valueOf(tb_kembali.getValueAt(tb_kembali.getSelectedRow(),0)));
        N.setText(String.valueOf( tb_kembali.getValueAt(tb_kembali.getSelectedRow(),0)));
        K.setText(String.valueOf( tb_kembali.getValueAt(tb_kembali.getSelectedRow(),1)));
        NP.setText(String.valueOf( tb_kembali.getValueAt(tb_kembali.getSelectedRow(),2)));
        NS.setText(String.valueOf( tb_kembali.getValueAt(tb_kembali.getSelectedRow(),4)));
        TS.setText(String.valueOf( tb_kembali.getValueAt(tb_kembali.getSelectedRow(),5)));
        TK.setText(String.valueOf( tb_kembali.getValueAt(tb_kembali.getSelectedRow(),6)));
        JH.setText(String.valueOf( tb_kembali.getValueAt(tb_kembali.getSelectedRow(),7)));
        TH.setText(String.valueOf( tb_kembali.getValueAt(tb_kembali.getSelectedRow(),8)));
        kode();
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_kembaliMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID;
    private javax.swing.JTextField JH;
    private javax.swing.JTextField K;
    private javax.swing.JTextField N;
    private javax.swing.JTextField NP;
    private javax.swing.JTextField NS;
    private javax.swing.JTextField TH;
    private javax.swing.JTextField TK;
    private javax.swing.JTextField TS;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_kembali;
    // End of variables declaration//GEN-END:variables
}
