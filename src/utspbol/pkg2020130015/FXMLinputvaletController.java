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
public class FXMLinputvaletController implements Initializable {

    boolean editdata = false;

    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txtumur;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtvalet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void execute(ValetModel d) {
        if (!d.getIdvalet().isEmpty()) {
            editdata = true;
            txtvalet.setText(d.getIdvalet());
            txtnama.setText(d.getNama());
            txtumur.setText(String.valueOf(d.getUmur()));
            txtalamat.setText(d.getAlamat());
            txtvalet.setEditable(false);
            txtnama.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtvalet.setText("");
        txtnama.setText("");
        txtumur.setText("");
        txtalamat.setText("");
        txtvalet.requestFocus();
    }

    @FXML
    private void simpanklik(ActionEvent event) {

        ValetModel n = new ValetModel();
        n.setIdvalet(txtvalet.getText());
        n.setNama(txtnama.getText());
        n.setUmur(Integer.parseInt(txtumur.getText()));
        n.setAlamat(txtalamat.getText());

        FXMLDocumentController.dtvalet.setValetModel(n);
        if (editdata) {
            if (FXMLDocumentController.dtvalet.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtvalet.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtvalet.validasi(n.getIdvalet()) <= 0) {
            if (FXMLDocumentController.dtvalet.insert()) {
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
            txtvalet.requestFocus();
        }
    }

}
