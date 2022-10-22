/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol.pkg2020130015;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    }
    
}
