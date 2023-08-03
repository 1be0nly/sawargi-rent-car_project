
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Peminjaman extends javax.swing.JInternalFrame {
static String nama;
static String telp;
static String nik;
static String alamat;
Connection koneksi;
PreparedStatement pst;
    /**
     * Creates new form Peminjaman
     */
    public Peminjaman(String nama, String telp, String alamat, String nik) {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        Nama_TF.setText(nama);
        Telp_TF.setText(telp);
        NIK_TF.setText(nik);
        Alamat_TA.setText(alamat);
        koneksi = DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "sawargi_rent_car");
        setTitle("Peminjaman");
        kendaraan();
        transaksi();
        kode();
        tampil();
        supir_CMB.setEnabled(false);
    }

    DefaultTableModel dtm,tbl;
        public void kendaraan(){
            
            String[] kolom = {"Jenis", "Nama", "Nopol", "Merk", "Tahun", "Harga", "Status"};
            
            dtm = new DefaultTableModel(null, kolom);
            try {
                Statement stmt = koneksi.createStatement();
                String sql = "SELECT * FROM tb_kendaran Where `Status` ='Ada'";
                ResultSet rs = stmt.executeQuery(sql);
                
            while(rs.next()){
                String Nama = rs.getString("Nama");
                String Harga = rs.getString("Harga");
                String Status = rs.getString("Status");
                String Jenis = rs.getString("Jenis");
                String Nopol = rs.getString("Nopol");
                String Merk = rs.getString("Merk");
                String Tahun = rs.getString("Tahun");
                dtm.addRow(new String[]{Jenis, Nama, Nopol, Merk, Tahun, Harga, Status});      
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        Tbl_Kendaraan.setModel(dtm);
            }
        public void transaksi(){
            
            String[] kolom = {"ID", "Penyewa", "Nama Kendaraan", "Nopol", "Harga", "Supir", "Tgl Pinjam", "Tgl Kembali", "Hari", "Total"};
            
            tbl = new DefaultTableModel(null, kolom);
            try {
                Statement stmt = koneksi.createStatement();
                String sql = "SELECT * FROM tb_transaksi_tmp";
                ResultSet rs = stmt.executeQuery(sql);
                
            while(rs.next()){
                String ID = rs.getString("id_trans");
                String Penyewa = rs.getString("Nama");
                String NK = rs.getString("Kendaraan");
                String Nopol = rs.getString("Nopol");
                String Harga = rs.getString("Harga");
                String Supir = rs.getString("Supir");
                String Pinjam = rs.getString("Tgl_pinjam");
                String Kembali = rs.getString("Tgl_kembali");
                String Hari = rs.getString("Jum_hari");
                String Total = rs.getString("Total");
                tbl.addRow(new String[]{ID, Penyewa, NK, Nopol, Harga, Supir, Pinjam, Kembali, Hari, Total});
                
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        Tbl_Peminjaman.setModel(tbl);
            }
   
    private void kode() {
        try {
            Statement s = koneksi.createStatement();

            String sql = "SELECT * FROM `tb_transaksi_tmp` ORDER by `id_trans` desc";
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

                no_id.setText("KT" + Nol + AN);
            } else {
                no_id.setText("KT001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }}
    private void update(){
        try {
        String supir = (String) supir_CMB.getSelectedItem();
        String urut = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(urut);
        String sewa = String.valueOf(fm.format(TglSewa.getDate()));
        String kembali = String.valueOf(fm.format(TglKembali.getDate()));
        String sql = "insert into `tb_transaksi_tmp` values (?,?,?,?,?,?,?,?,?,?)";
        pst = koneksi.prepareStatement(sql);
        pst.setString(1, no_id.getText());
        pst.setString(2, Nama_TF.getText());
        pst.setString(3, NamaK_TF.getText());
        pst.setString(4, Nopol_TF.getText());
        pst.setString(5, Harga_TF.getText());
        pst.setString(6, supir);
        pst.setString(7, sewa);
        pst.setString(8, kembali);
        pst.setString(9, Lama_TF.getText());
        pst.setString(10, Total_TF.getText());
        
        pst.executeUpdate();
        pst.close();
        
        transaksi();
        kendaraan();
        kode();
        //tampil();
        status_kendaraan();
        status_supir();
        JOptionPane.showMessageDialog(this, "Peminjaman Berhasil");
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Peminjaman Gagal! " + e, "Pesan", JOptionPane.ERROR_MESSAGE);
    }
    no_id.setText(null);
    Nama_TF.setText(null);
    NIK_TF.setText(null);
    Telp_TF.setText(null);
    Alamat_TA.setText(null);
    Nopol_TF.setText(null);
    Jenis_TF.setText(null);
    NamaK_TF.setText(null);
    Merk_TF.setText(null);
    Tahun_TF.setText(null);
    Harga_TF.setText(null);
    Status_TF.setText(null);
    Supir_RB.setSelected(false);
    Tanpa_RB.setSelected(false);
    supir_CMB.setSelectedItem(null);
    TglSewa.setDate(null);
    TglKembali.setDate(null);
    Lama_TF.setText(null);
    Total_TF.setText(null);
           
    }
    private void auto_kendaraan(){
    try{
        String sql ="select * from tb_kendaran";
        pst=koneksi.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        Tbl_Kendaraan.setModel(DbUtils.resultSetToTableModel(rs));
    }catch (Exception ex)
    {}
    kendaraan();
    }
  
   private void tampil()
    {
    try {
        String status = "Ada";
        String sql = "select * from `tb_supir` where `Status`='"+status+"'";
        pst=koneksi.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
         while(rs.next()){
         String supir = rs.getString("Nama");
         supir_CMB.addItem(supir);
         }       
    }catch (Exception e){
    Logger.getLogger(Peminjaman.class.getName()).log(Level.SEVERE, null, e);
    }}
    private void status_kendaraan(){
        try{
            String update = "Keluar";
            String sqla = "update `tb_kendaran` set `Status`='"+update+"' where `Nopol`='"+Nopol_TF.getText()+"'";
            pst=koneksi.prepareStatement(sqla);
            pst.executeUpdate();       
        }catch (SQLException e){   
        JOptionPane.showMessageDialog(null, "Data gagal diubah" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);}
    }
    private void status_supir(){
        try{
            String update = "Keluar";
            String sqla = "update `tb_supir` set `Status`='"+update+"' where `Nama`='"+supir_CMB.getSelectedItem().toString()+"'";
            pst=koneksi.prepareStatement(sqla);
            pst.executeUpdate();      
            //tampil();
        }catch (SQLException e){   
        JOptionPane.showMessageDialog(null, "Data gagal diubah" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);}
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        no_id = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        harga_supir = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Lama_TF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tbl_Peminjaman = new javax.swing.JTable();
        Nopol_TF = new javax.swing.JLabel();
        Jenis_TF = new javax.swing.JLabel();
        NamaK_TF = new javax.swing.JLabel();
        Merk_TF = new javax.swing.JLabel();
        Tahun_TF = new javax.swing.JLabel();
        Harga_TF = new javax.swing.JLabel();
        Status_TF = new javax.swing.JLabel();
        NIK_TF = new javax.swing.JLabel();
        Telp_TF = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Alamat_TA = new javax.swing.JTextArea();
        Total_TF = new javax.swing.JLabel();
        Hitung_Btn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        supir_CMB = new javax.swing.JComboBox<>();
        Supir_RB = new javax.swing.JRadioButton();
        Tanpa_RB = new javax.swing.JRadioButton();
        Sewa_Btn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tbl_Kendaraan = new javax.swing.JTable();
        TglKembali = new com.toedter.calendar.JDateChooser();
        TglSewa = new com.toedter.calendar.JDateChooser();
        Nama_TF = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1100, 640));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("PEMINJAMAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(489, 489, 489)
                .addComponent(jLabel10)
                .addContainerGap(473, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 45));

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

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 1084, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel1.setText("Nama :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel2.setText("NIK :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel3.setText("Telp :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel4.setText("Alamat :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel5.setText("Jenis :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel6.setText("Nama :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel7.setText("Merk :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel8.setText("Tahun :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel9.setText("No Polisi :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel11.setText("Harga :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel12.setText("Status :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel13.setText("Tgl Sewa :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel14.setText("Tgl Kembali :");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel15.setText("Lama Pinjam :");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));

        Lama_TF.setEditable(false);
        Lama_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Lama_TF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lama_TFActionPerformed(evt);
            }
        });
        jPanel1.add(Lama_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 121, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel16.setText("Rp.");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, -1, -1));

        Tbl_Peminjaman.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Tbl_Peminjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(Tbl_Peminjaman);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 353, 755, 180));

        Nopol_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Nopol_TF.setText("nopol");
        jPanel1.add(Nopol_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 100, -1));

        Jenis_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Jenis_TF.setText("jenis kendaraan");
        jPanel1.add(Jenis_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 100, -1));

        NamaK_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        NamaK_TF.setText("nama kendaraan");
        jPanel1.add(NamaK_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 100, -1));

        Merk_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Merk_TF.setText("merk");
        jPanel1.add(Merk_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 100, -1));

        Tahun_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Tahun_TF.setText("harga");
        jPanel1.add(Tahun_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 100, -1));

        Harga_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Harga_TF.setText("tahun");
        jPanel1.add(Harga_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 100, -1));

        Status_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Status_TF.setText("status");
        jPanel1.add(Status_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 100, -1));

        NIK_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        NIK_TF.setText("NIK Penyewa");
        jPanel1.add(NIK_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 100, -1));

        Telp_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Telp_TF.setText("Telp Penyewa");
        jPanel1.add(Telp_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 100, -1));

        Alamat_TA.setEditable(false);
        Alamat_TA.setColumns(20);
        Alamat_TA.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Alamat_TA.setRows(5);
        jScrollPane1.setViewportView(Alamat_TA);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        Total_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 36)); // NOI18N
        Total_TF.setText("TOTAL");
        jPanel1.add(Total_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 282, 150, 60));

        Hitung_Btn.setBackground(new java.awt.Color(255, 255, 255));
        Hitung_Btn.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Hitung_Btn.setText("HITUNG");
        Hitung_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Hitung_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hitung_BtnActionPerformed(evt);
            }
        });
        jPanel1.add(Hitung_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        jLabel17.setText("Supir :");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, 20));

        supir_CMB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        supir_CMB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supir_CMBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                supir_CMBMouseEntered(evt);
            }
        });
        supir_CMB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supir_CMBActionPerformed(evt);
            }
        });
        jPanel1.add(supir_CMB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 120, 20));

        Supir_RB.setBackground(new java.awt.Color(255, 255, 255));
        Supir_RB.setText("Supir");
        Supir_RB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Supir_RB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supir_RBActionPerformed(evt);
            }
        });
        jPanel1.add(Supir_RB, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, 20));

        Tanpa_RB.setBackground(new java.awt.Color(255, 255, 255));
        Tanpa_RB.setText("Tanpa");
        Tanpa_RB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Tanpa_RB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tanpa_RBActionPerformed(evt);
            }
        });
        jPanel1.add(Tanpa_RB, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, -1, 20));

        Sewa_Btn.setBackground(new java.awt.Color(255, 255, 255));
        Sewa_Btn.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Sewa_Btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/tenant.png"))); // NOI18N
        Sewa_Btn.setText("SEWA");
        Sewa_Btn.setBorderPainted(false);
        Sewa_Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Sewa_Btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Sewa_Btn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Sewa_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sewa_BtnActionPerformed(evt);
            }
        });
        jPanel1.add(Sewa_Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 274, -1, -1));

        Tbl_Kendaraan.setModel(new javax.swing.table.DefaultTableModel(
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
        Tbl_Kendaraan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tbl_KendaraanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Tbl_Kendaraan);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 429, 181));
        jPanel1.add(TglKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 120, -1));
        jPanel1.add(TglSewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 120, -1));

        Nama_TF.setFont(new java.awt.Font("Segoe UI Historic", 0, 12)); // NOI18N
        Nama_TF.setText("Nama Penyewa");
        jPanel1.add(Nama_TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 100, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Lama_TFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Lama_TFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Lama_TFActionPerformed

    private void Hitung_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hitung_BtnActionPerformed
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            if(Supir_RB.isSelected()) {
                String strDate1 = df.format(TglSewa.getDate());
                String strDate2 = df.format(TglKembali.getDate());
                Date Tanggal1 = df.parse(strDate1);
                Date Tanggal2 = df.parse(strDate2);
                long Hari1 = Tanggal1.getTime();
                long Hari2 = Tanggal2.getTime();
                long diff = Hari2 - Hari1;
                long Lama = diff / (24 * 60 * 60 * 1000);
                long supir = 250000 * Lama;
                String Hasil = (Long.toString(Lama));
                String supir1 = (Long.toString(supir));
                harga_supir.setText(supir1);
                Lama_TF.setText(Hasil);

                int harga_sewa = Integer.parseInt(Tahun_TF.getText());
                int lama_sewa = Integer.parseInt(Lama_TF.getText());
                int supir2 = Integer.parseInt(harga_supir.getText());
                int Total = harga_sewa * lama_sewa + supir2;
                String a = Integer.toString(Total);
                Total_TF.setText(a);
            }
            else if(Tanpa_RB.isSelected()){
              
                String strDate1 = df.format(TglSewa.getDate());
                String strDate2 = df.format(TglKembali.getDate());
                Date Tanggal1 = df.parse(strDate1);
                Date Tanggal2 = df.parse(strDate2);
                long Hari1 = Tanggal1.getTime();
                long Hari2 = Tanggal2.getTime();
                long diff = Hari2 - Hari1;
                long Lama = diff / (24 * 60 * 60 * 1000);
                String Hasil = (Long.toString(Lama));
                Lama_TF.setText(Hasil);

                int harga_sewa = Integer.parseInt(Tahun_TF.getText());
                int lama_sewa = Integer.parseInt(Lama_TF.getText());
                int Total = harga_sewa * lama_sewa;
                String a = Integer.toString(Total);
                Total_TF.setText(a);
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(this, "Masukan Tanggal Peminjaman dan Tanggal Pengembalian");
        }     // TODO add your handling code here:
    }//GEN-LAST:event_Hitung_BtnActionPerformed

    private void supir_CMBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supir_CMBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_supir_CMBMouseClicked

    private void supir_CMBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supir_CMBMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_supir_CMBMouseEntered

    private void supir_CMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supir_CMBActionPerformed
        //tampil();        // TODO add your handling code here:
    }//GEN-LAST:event_supir_CMBActionPerformed

    private void Supir_RBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supir_RBActionPerformed
        Tanpa_RB.setSelected(false);
        Supir_RB.setSelected(true);
        supir_CMB.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_Supir_RBActionPerformed

    private void Tanpa_RBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tanpa_RBActionPerformed
        Supir_RB.setSelected(false);
        Tanpa_RB.setSelected(true);
        supir_CMB.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_Tanpa_RBActionPerformed

    private void Sewa_BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sewa_BtnActionPerformed
        update();
        auto_kendaraan();
        supir_CMB.setEnabled(false);
        menu_utama.DP.removeAll();
        Penyewa sewa= new Penyewa();
        menu_utama.DP.add(sewa).setVisible(true); // TODO add your handling code here:
    }//GEN-LAST:event_Sewa_BtnActionPerformed

    private void Tbl_KendaraanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tbl_KendaraanMouseClicked
        int baris = Tbl_Kendaraan.rowAtPoint(evt.getPoint());
        Object jenis=Tbl_Kendaraan.getValueAt(baris, 0);
        Object nama=Tbl_Kendaraan.getValueAt(baris, 1);
        Object nopol=Tbl_Kendaraan.getValueAt(baris, 2);
        Object merk=Tbl_Kendaraan.getValueAt(baris, 3);
        Object tahun=Tbl_Kendaraan.getValueAt(baris, 4);
        Object harga=Tbl_Kendaraan.getValueAt(baris, 5);
        Object status=Tbl_Kendaraan.getValueAt(baris, 6);

        Jenis_TF.setText(jenis.toString());
        NamaK_TF.setText(nama.toString());
        Nopol_TF.setText(nopol.toString());
        Merk_TF.setText(merk.toString());
        Harga_TF.setText(tahun.toString());
        Tahun_TF.setText(harga.toString());
        Status_TF.setText(status.toString());        // TODO add your handling code here:
    }//GEN-LAST:event_Tbl_KendaraanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Alamat_TA;
    private javax.swing.JLabel Harga_TF;
    private javax.swing.JButton Hitung_Btn;
    private javax.swing.JLabel Jenis_TF;
    private javax.swing.JTextField Lama_TF;
    private javax.swing.JLabel Merk_TF;
    private javax.swing.JLabel NIK_TF;
    private javax.swing.JLabel NamaK_TF;
    private javax.swing.JLabel Nama_TF;
    private javax.swing.JLabel Nopol_TF;
    private javax.swing.JButton Sewa_Btn;
    private javax.swing.JLabel Status_TF;
    private javax.swing.JRadioButton Supir_RB;
    private javax.swing.JLabel Tahun_TF;
    private javax.swing.JRadioButton Tanpa_RB;
    private javax.swing.JTable Tbl_Kendaraan;
    private javax.swing.JTable Tbl_Peminjaman;
    private javax.swing.JLabel Telp_TF;
    private com.toedter.calendar.JDateChooser TglKembali;
    private com.toedter.calendar.JDateChooser TglSewa;
    private javax.swing.JLabel Total_TF;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField harga_supir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField no_id;
    private javax.swing.JComboBox<String> supir_CMB;
    // End of variables declaration//GEN-END:variables
}
