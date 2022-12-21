/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol.pkg2020130015;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author antoni
 */
public class FXMLBuatTransaksiController implements Initializable {

    public ItungTransaksiModel dt = new ItungTransaksiModel();

    @FXML
    private CheckBox chbvalet;
    @FXML
    private Button btnbuattransaksi;
    @FXML
    private TextField txttiket;
    @FXML
    private TextField txtplat;
    @FXML
    private TextField txtkendaraan;
    @FXML
    private TextField txtpetugas;
    @FXML
    private TextField txtvalet;
    @FXML
    private DatePicker dtmasuk;
    @FXML
    private DatePicker dtkeluar;
    @FXML
    private Button btncariplat;
    @FXML
    private Button btncaripetugas;
    @FXML
    private Button btncarivalet;
    @FXML
    private TextField txtwaktumasuk;
    @FXML
    private TextField txtwaktukeluar;
    @FXML
    private Button btnhapus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dtmasuk.setEditable(false);
        dtkeluar.setEditable(false);
        txtplat.setEditable(false);
        txtpetugas.setEditable(false);
        txtvalet.setEditable(false);
        txtkendaraan.setEditable(false);
        btncarivalet.setDisable(true);
        txtplat.requestFocus();
    }

    @FXML
    private void buattransaksiklik(ActionEvent event) {
        
        SimpleDateFormat tgl = new SimpleDateFormat("yyyy-MM-dd"); //"dd-MM-yyyy"
        SimpleDateFormat wkt = new SimpleDateFormat("HH:mm:ss");
        try {
            dt.setTglmasuk(tgl.parse(dtmasuk.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            dt.setTglkeluar(tgl.parse(dtkeluar.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            dt.setWktmasuk(wkt.parse(txtwaktumasuk.getText()));
            dt.setWktkeluar(wkt.parse(txtwaktukeluar.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long stgl = Math.round((dt.getTglkeluar().getTime() - dt.getTglmasuk().getTime()) / (1000 * 60 * 60 * 24));
        long swkt = Math.round((dt.getWktkeluar().getTime() - dt.getWktkeluar().getTime()) / 1000);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "No tiket: " + txttiket.getText() + "\n\n" +
                "No Polisi / Plat: " + txtplat.getText() + "\n\n" +
                "Kendaraan: " + txtkendaraan.getText() + "\n\n" +
                "Id Petugas: " + txtpetugas.getText() + "\n\n" + 
                "Id Valet: " + txtvalet.getText() + "\n\n" +
                "Durasi Parkir: " + stgl + "hari " + swkt + "jam");
        alert.setHeaderText("Berhasil melakukan Transaksi");
        alert.showAndWait();

    }

    @FXML
    private void cariplatklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPilihPlat.fxml"));
            Parent root = (Parent) loader.load();

            FXMLPilihPlatController isidt = (FXMLPilihPlatController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtplat.setText(isidt.getPlathasil());
                txtkendaraan.setText(isidt.getKendaraanhasil());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void caripetugasklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPilihPetugas.fxml"));
            Parent root = (Parent) loader.load();

            FXMLPilihPetugasController isidt = (FXMLPilihPetugasController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtpetugas.setText(isidt.getIdpetugashasil());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void carivaletklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLPilihValet.fxml"));
            Parent root = (Parent) loader.load();

            FXMLPilihValetController isidt = (FXMLPilihValetController) loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if (isidt.getHasil() == 1) {
                txtvalet.setText(isidt.getIdvalethasil());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        dtmasuk.getEditor().clear();
        dtkeluar.getEditor().clear();
        txtkendaraan.setText("");
        txtpetugas.setText("");
        txtplat.setText("");
        txttiket.setText("");
        txtvalet.setText("");
        txtwaktukeluar.setText("");
        txtwaktumasuk.setText("");
        txttiket.requestFocus();
    }

    @FXML
    private void usevalet(ActionEvent event) {
        if (chbvalet.isSelected()) {
            btncarivalet.setDisable(false);
        } else {
            txtvalet.setText("");
            btncarivalet.setDisable(true);
        }
    }

}
