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
public class FXMLPilihValetController implements Initializable {

    @FXML
    private TextField txtcari;
    @FXML
    private Button btncari;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnpilih;
    @FXML
    private ComboBox<String> cbpilih;
    @FXML
    private TableView<ValetModel> tbvvalet;
    
    private int hasil = 0;
    private String idvalethasil = "";
    
    public int getHasil() {
        return hasil;
    }

    public String getIdvalethasil() {
        return idvalethasil;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbpilih.setItems(FXCollections.observableArrayList("Id Valet", "Nama"));
        cbpilih.getSelectionModel().select(0);
        showdata("idvalet","");
    }    
    
    public void showdata(String f, String i) {
        ObservableList<ValetModel> data = FXMLDocumentController.dtvalet.CariValet(f, i);
        if (data.isEmpty()) {
            data = FXMLDocumentController.dtvalet.Load();
            txtcari.setText("");
        }
        if (data != null) {
            tbvvalet.getColumns().clear();
            tbvvalet.getItems().clear();
            TableColumn col = new TableColumn("idvalet");
            col.setCellValueFactory(new PropertyValueFactory<ValetModel, String>("idvalet"));
            tbvvalet.getColumns().addAll(col);
            col = new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<ValetModel, String>("nama"));
            tbvvalet.getColumns().addAll(col);
            col = new TableColumn("umur");
            col.setCellValueFactory(new PropertyValueFactory<ValetModel, String>("umur"));
            tbvvalet.getColumns().addAll(col);
            col = new TableColumn("alamat");
            col.setCellValueFactory(new PropertyValueFactory<ValetModel, String>("alamat"));
            tbvvalet.getColumns().addAll(col);
            

            tbvvalet.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvvalet.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void cariklik(ActionEvent event) {
        showdata(cbpilih.getSelectionModel().getSelectedItem(), txtcari.getText());
    }

    @FXML
    private void batalklik(ActionEvent event) {
        hasil = 0;
        btnbatal.getScene().getWindow().hide();
    }

    @FXML
    private void pilihklik(ActionEvent event) {
        hasil = 1;
        int pilihan = tbvvalet.getSelectionModel().getSelectedCells().get(0).getRow();
        idvalethasil = tbvvalet.getItems().get(pilihan).getIdvalet();
        btnpilih.getScene().getWindow().hide();
    }
    
}
