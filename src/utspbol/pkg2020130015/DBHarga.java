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
public class DBHarga {

    private HargaModel dt = new HargaModel();

    public HargaModel getHargaModel() {
        return (dt);
    }

    public void setHargaModel(HargaModel s) {
        dt = s;
    }

    public ObservableList<HargaModel> Load() {
        try {
            ObservableList<HargaModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from harga");
            int i = 1;
            while (rs.next()) {
                HargaModel d = new HargaModel();
                d.setKendaraan(rs.getString("kendaraan"));
                d.setParkir(rs.getDouble("parkir"));
                d.setValet(rs.getDouble("valet"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from harga where kendaraan = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into harga (kendaraan,parkir,valet) values (?,?,?)");
            con.preparedStatement.setString(1, getHargaModel().getKendaraan());
            con.preparedStatement.setDouble(2, getHargaModel().getParkir());
            con.preparedStatement.setDouble(3, getHargaModel().getValet());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from harga where kendaraan  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update harga set parkir = ?, valet = ? where  kendaraan = ? ");
            con.preparedStatement.setDouble(1, getHargaModel().getParkir());
            con.preparedStatement.setDouble(2, getHargaModel().getValet());
            con.preparedStatement.setString(3, getHargaModel().getKendaraan());
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

//    public ObservableList<HargaModel> CariHarga(String kode, String nama) {
//        try {
//            ObservableList<HargaModel> tableData;
//            tableData = FXCollections.observableArrayList();
//            Koneksi con = new Koneksi();
//            con.bukaKoneksi();
//            con.statement = (Statement) con.dbKoneksi.createStatement();
//            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from harga WHERE kendaraan LIKE '" + kode + "%' OR nama LIKE '" + nama + "%'");
//            int i = 1;
//            while (rs.next()) {
//                HargaModel d = new HargaModel();
//                d.setKendaraan(rs.getString("kendaraan"));
//                d.setParkir(rs.getDouble("parkir"));
//                d.setValet(rs.getDouble("valet"));
//                tableData.add(d);
//                i++;
//            }
//            con.tutupKoneksi();
//            return tableData;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
