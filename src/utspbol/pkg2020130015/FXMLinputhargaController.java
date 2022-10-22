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
public class FXMLinputhargaController implements Initializable {

    boolean editdata = false;

    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtvalet;
    @FXML
    private TextField txtparkir;
    @FXML
    private TextField txtkendaraan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtkendaraan.setText("");
        txtparkir.setText("");
        txtvalet.setText("");
        txtkendaraan.requestFocus();
    }

    public void execute(HargaModel d) {
        if (!d.getKendaraan().isEmpty()) {
            editdata = true;
            txtkendaraan.setText(d.getKendaraan());
            txtparkir.setText(String.valueOf(d.getParkir()));
            txtvalet.setText(String.valueOf(d.getValet()));
            txtkendaraan.setEditable(false);
            txtparkir.requestFocus();
        }
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        HargaModel n = new HargaModel();
        n.setKendaraan(txtkendaraan.getText());
        n.setParkir(Double.parseDouble(txtparkir.getText()));
        n.setValet(Double.parseDouble(txtvalet.getText()));

        FXMLDocumentController.dtharga.setHargaModel(n);
        if (editdata) {
            if (FXMLDocumentController.dtharga.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtkendaraan.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtharga.validasi(n.getKendaraan()) <= 0) {
            if (FXMLDocumentController.dtharga.insert()) {
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
            txtkendaraan.requestFocus();
        }
    }

}
