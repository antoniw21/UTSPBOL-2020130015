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
public class DBValet {
    
    private ValetModel dt = new ValetModel();

    public ValetModel getValetModel() {
        return (dt);
    }

    public void setValetModel(ValetModel s) {
        dt = s;
    }

    public ObservableList<ValetModel> Load() {
        try {
            ObservableList<ValetModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from valet");
            int i = 1;
            while (rs.next()) {
                ValetModel d = new ValetModel();
                d.setIdvalet(rs.getString("idvalet"));
                d.setNama(rs.getString("nama"));
                d.setUmur(rs.getInt("umur"));
                d.setAlamat(rs.getString("alamat"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from valet where idvalet = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into valet (idvalet,nama,umur,alamat) values (?,?,?,?)");
            con.preparedStatement.setString(1, getValetModel().getIdvalet());
            con.preparedStatement.setString(2, getValetModel().getNama());
            con.preparedStatement.setInt(3, getValetModel().getUmur());
            con.preparedStatement.setString(4, getValetModel().getAlamat());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from valet where idvalet  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update valet set nama = ?, umur = ?, alamat = ? where  idvalet = ? ");
            con.preparedStatement.setString(1, getValetModel().getNama());
            con.preparedStatement.setInt(2, getValetModel().getUmur());
            con.preparedStatement.setString(3, getValetModel().getAlamat());
            con.preparedStatement.setString(4, getValetModel().getIdvalet());
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
