/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utspbol.pkg2020130015;

import java.util.Date;

/**
 *
 * @author antoni
 */
public class ItungTransaksiModel {
    String notiket, plat, kendaraan, idpetugas,idvalet;
    int biayaparkir;
    Date tglmasuk = new Date();
    Date tglkeluar = new Date();
    Date wktmasuk = new Date();
    Date wktkeluar = new Date();

    public String getNotiket() {
        return notiket;
    }

    public void setNotiket(String notiket) {
        this.notiket = notiket;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(String kendaraan) {
        this.kendaraan = kendaraan;
    }

    public String getIdpetugas() {
        return idpetugas;
    }

    public void setIdpetugas(String idpetugas) {
        this.idpetugas = idpetugas;
    }

    public String getIdvalet() {
        return idvalet;
    }

    public void setIdvalet(String idvalet) {
        this.idvalet = idvalet;
    }

    public Date getTglmasuk() {
        return tglmasuk;
    }

    public void setTglmasuk(Date tglmasuk) {
        this.tglmasuk = tglmasuk;
    }

    public Date getTglkeluar() {
        return tglkeluar;
    }

    public void setTglkeluar(Date tglkeluar) {
        this.tglkeluar = tglkeluar;
    }

    public Date getWktmasuk() {
        return wktmasuk;
    }

    public void setWktmasuk(Date wktmasuk) {
        this.wktmasuk = wktmasuk;
    }

    public Date getWktkeluar() {
        return wktkeluar;
    }

    public void setWktkeluar(Date wktkeluar) {
        this.wktkeluar = wktkeluar;
    }

    public int getBiayaparkir() {
        return biayaparkir;
    }

    public void setBiayaparkir(int biayaparkir) {
        this.biayaparkir = biayaparkir;
    }
    
    
    
}
