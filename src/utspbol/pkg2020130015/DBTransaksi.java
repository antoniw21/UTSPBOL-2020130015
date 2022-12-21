/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol.pkg2020130015;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author antoni
 */
public class DBTransaksi {
    
    private TransaksiModel dt = new TransaksiModel();

    public TransaksiModel getTransaksiModel() {
        return (dt);
    }

    public void setTransaksiModel(TransaksiModel s) {
        dt = s;
    }

    public ObservableList<TransaksiModel> Load() {
        try {
            ObservableList<TransaksiModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from transaksi");
            int i = 1;
            while (rs.next()) {
                TransaksiModel d = new TransaksiModel();
                d.setNotiket(rs.getString("notiket"));
                d.setPlat(rs.getString("plat"));
                d.setKendaraan(rs.getString("kendaraan"));
                d.setIdpetugas(rs.getString("idpetugas"));
                d.setIdvalet(rs.getString("idvalet"));
                d.setMasuk(rs.getString("masuk"));
                d.setKeluar(rs.getString("keluar"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }//how to insert mysql date time to db
    
//    public boolean insert() {
//        boolean berhasil = false;
//        Koneksi con = new Koneksi();
//        try {
//            con.bukaKoneksi();
//            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into transaksi (notiket,plat,kendaraan,idpetugas,idvalet,masuk,keluar) values (?,?,?,?,?,?,?)");
//            con.preparedStatement.setString(1, getTransaksiModel().getNotiket());
//            con.preparedStatement.setString(2, getTransaksiModel().getPlat());
//            con.preparedStatement.setString(3, getTransaksiModel().getKendaraan());
//            con.preparedStatement.setString(4, getTransaksiModel().getIdpetugas());
//            con.preparedStatement.setString(5, getTransaksiModel().getIdvalet());
//            con.preparedStatement.setDate(6, getTransaksiModel().getMasuk());
//            con.preparedStatement.setString(7, getTransaksiModel().getKeluar());
//            con.preparedStatement.executeUpdate();
//            berhasil = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            berhasil = false;
//        } finally {
//            con.tutupKoneksi();
//            return berhasil;
//        }
//    }
    
    public ObservableList<TransaksiModel> CariTransaksi(String kode) {
        try {
            ObservableList<TransaksiModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select notiket, plat, kendaraan, idpetugas, idvalet, masuk, keluar from transaksi WHERE notiket LIKE '" + kode + "%'");
            int i = 1;
            while (rs.next()) {
                TransaksiModel d = new TransaksiModel();
                d.setNotiket(rs.getString("notiket"));
                d.setPlat(rs.getString("plat"));
                d.setKendaraan(rs.getString("kendaraan"));
                d.setIdpetugas(rs.getString("idpetugas"));
                d.setIdvalet(rs.getString("idvalet"));
                d.setMasuk(rs.getString("masuk"));
                d.setKeluar(rs.getString("keluar"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
