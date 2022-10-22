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
public class DBPetugas {

    private PetugasModel dt = new PetugasModel();

    public PetugasModel getPetugasModel() {
        return (dt);
    }

    public void setPetugasModel(PetugasModel s) {
        dt = s;
    }

    public ObservableList<PetugasModel> Load() {
        try {
            ObservableList<PetugasModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from petugas");
            int i = 1;
            while (rs.next()) {
                PetugasModel d = new PetugasModel();
                d.setIdpetugas(rs.getString("idpetugas"));
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from petugas where idpetugas = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into petugas (idpetugas,nama,umur,alamat) values (?,?,?,?)");
            con.preparedStatement.setString(1, getPetugasModel().getIdpetugas());
            con.preparedStatement.setString(2, getPetugasModel().getNama());
            con.preparedStatement.setInt(3, getPetugasModel().getUmur());
            con.preparedStatement.setString(4, getPetugasModel().getAlamat());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from petugas where idpetugas  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update petugas set nama = ?, umur = ?, alamat = ? where  idpetugas = ? ");
            con.preparedStatement.setString(1, getPetugasModel().getNama());
            con.preparedStatement.setInt(2, getPetugasModel().getUmur());
            con.preparedStatement.setString(3, getPetugasModel().getAlamat());
            con.preparedStatement.setString(4, getPetugasModel().getIdpetugas());
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
