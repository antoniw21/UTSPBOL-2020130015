/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol.pkg2020130015;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author antoni
 */
public class FXMLPilihPetugasController implements Initializable {

    @FXML
    private TableView<PetugasModel> tbvpetugas;
    @FXML
    private ComboBox<String> cbpilih;
    @FXML
    private Button btnpilih;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btncari;
    @FXML
    private TextField txtcari;

    private int hasil = 0;
    private String idpetugashasil = "";
    
    public int getHasil() {
        return hasil;
    }

    public String getIdpetugashasil() {
        return idpetugashasil;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbpilih.setItems(FXCollections.observableArrayList("Id Petugas", "Nama"));
        cbpilih.getSelectionModel().select(0);
        showdata("idpetugas","");
    }    
    
    public void showdata(String f, String i) {
        ObservableList<PetugasModel> data = FXMLDocumentController.dtpetugas.CariPetugas(f, i);
        if (data.isEmpty()) {
            data = FXMLDocumentController.dtpetugas.Load();
            txtcari.setText("");
        }
        if (data != null) {
            tbvpetugas.getColumns().clear();
            tbvpetugas.getItems().clear();
            TableColumn col = new TableColumn("idpetugas");
            col.setCellValueFactory(new PropertyValueFactory<PetugasModel, String>("idpetugas"));
            tbvpetugas.getColumns().addAll(col);
            col = new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<PetugasModel, String>("nama"));
            tbvpetugas.getColumns().addAll(col);
            col = new TableColumn("umur");
            col.setCellValueFactory(new PropertyValueFactory<PetugasModel, String>("umur"));
            tbvpetugas.getColumns().addAll(col);
            col = new TableColumn("alamat");
            col.setCellValueFactory(new PropertyValueFactory<PetugasModel, String>("alamat"));
            tbvpetugas.getColumns().addAll(col);
            

            tbvpetugas.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvpetugas.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbvpetugas.getSelectionModel().getSelectedCells().get(0).getRow();
        idpetugashasil = tbvpetugas.getItems().get(pilihan).getIdpetugas();
        btnpilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void cariklik(ActionEvent event) {
        showdata(cbpilih.getSelectionModel().getSelectedItem(), txtcari.getText());
    }
    
}
