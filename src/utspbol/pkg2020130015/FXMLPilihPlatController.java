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
public class FXMLPilihPlatController implements Initializable {
    
    private int hasil = 0;
    private String plathasil = "";
    private String kendaraanhasil = "";

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
    private TableView<PengunjungModel> tbvplat;

    public int getHasil() {
        return hasil;
    }

    public String getPlathasil() {
        return plathasil;
    }
    
    public String getKendaraanhasil() {
        return kendaraanhasil;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbpilih.setItems(FXCollections.observableArrayList("No. Polisi / Plat", "Nama"));
        cbpilih.getSelectionModel().select(0);
        showdata("plat","");
    }    

    public void showdata(String f, String i) {
        ObservableList<PengunjungModel> data = FXMLDocumentController.dtpengunjung.CariPengunjung(f, i);
        if (data.isEmpty()) {
            data = FXMLDocumentController.dtpengunjung.Load();
            txtcari.setText("");
        }
        if (data != null) {
            tbvplat.getColumns().clear();
            tbvplat.getItems().clear();
            TableColumn col = new TableColumn("plat");
            col.setCellValueFactory(new PropertyValueFactory<PengunjungModel, String>("plat"));
            tbvplat.getColumns().addAll(col);
            col = new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<PengunjungModel, String>("nama"));
            tbvplat.getColumns().addAll(col);
            col = new TableColumn("alamat");
            col.setCellValueFactory(new PropertyValueFactory<PengunjungModel, String>("alamat"));
            tbvplat.getColumns().addAll(col);
            col = new TableColumn("kendaraan");
            col.setCellValueFactory(new PropertyValueFactory<PengunjungModel, String>("kendaraan"));
            tbvplat.getColumns().addAll(col);

            tbvplat.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvplat.getScene().getWindow().hide();;
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
        int pilihan = tbvplat.getSelectionModel().getSelectedCells().get(0).getRow();
        plathasil = tbvplat.getItems().get(pilihan).getPlat();
        kendaraanhasil = tbvplat.getItems().get(pilihan).getKendaraan();
        btnpilih.getScene().getWindow().hide();
    }
    
}
