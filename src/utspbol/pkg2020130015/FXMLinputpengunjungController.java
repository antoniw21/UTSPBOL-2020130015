/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol.pkg2020130015;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author antoni
 */
public class FXMLinputpengunjungController implements Initializable {

    boolean editdata = false;
    
    @FXML
    private TextField txtplat;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txtkendaraan;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void execute(PengunjungModel d) {
        if (!d.getPlat().isEmpty()) {
            editdata = true;
            txtplat.setText(d.getPlat());
            txtnama.setText(d.getNama());
            txtalamat.setText(d.getAlamat());
            txtkendaraan.setText(d.getKendaraan());
            txtplat.setEditable(false);
            txtnama.requestFocus();
        }
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        PengunjungModel n = new PengunjungModel();
        n.setPlat(txtplat.getText());
        n.setNama(txtnama.getText());
        n.setAlamat(txtalamat.getText());
        n.setKendaraan(txtkendaraan.getText());

        FXMLDocumentController.dtpengunjung.setPengunjungModel(n);
        if (editdata) {
            if (FXMLDocumentController.dtpengunjung.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtplat.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtpengunjung.validasi(n.getPlat()) <= 0) {
            if (FXMLDocumentController.dtpengunjung.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtplat.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtplat.setText("");
        txtnama.setText("");
        txtalamat.setText("");
        txtkendaraan.setText("");
        txtplat.requestFocus();
    }
    
}
