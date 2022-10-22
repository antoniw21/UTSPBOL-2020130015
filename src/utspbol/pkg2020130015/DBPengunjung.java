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
public class DBPengunjung {

    private PengunjungModel dt = new PengunjungModel();

    public PengunjungModel getPengunjungModel() {
        return (dt);
    }

    public void setPengunjungModel(PengunjungModel s) {
        dt = s;
    }

    public ObservableList<PengunjungModel> Load() {
        try {
            ObservableList<PengunjungModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from pengunjung");
            int i = 1;
            while (rs.next()) {
                PengunjungModel d = new PengunjungModel();
                d.setPlat(rs.getString("plat"));
                d.setNama(rs.getString("nama"));
                d.setAlamat(rs.getString("alamat"));
                d.setKendaraan(rs.getString("kendaraan"));
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

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pengunjung where plat = '" + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pengunjung (plat,nama,alamat,kendaraan) values (?,?,?,?)");
            con.preparedStatement.setString(1, getPengunjungModel().getPlat());
            con.preparedStatement.setString(2, getPengunjungModel().getNama());
            con.preparedStatement.setString(3, getPengunjungModel().getAlamat());
            con.preparedStatement.setString(4, getPengunjungModel().getKendaraan());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pengunjung where plat  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update pengunjung set nama = ?, alamat = ?, kendaraan = ? where  plat = ? ");
            con.preparedStatement.setString(1, getPengunjungModel().getNama());
            con.preparedStatement.setString(2, getPengunjungModel().getAlamat());
            con.preparedStatement.setString(3, getPengunjungModel().getKendaraan());
            con.preparedStatement.setString(4, getPengunjungModel().getPlat());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

}
