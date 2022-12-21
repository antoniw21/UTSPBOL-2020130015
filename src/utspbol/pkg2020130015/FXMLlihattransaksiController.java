/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol.pkg2020130015;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author antoni
 */
public class FXMLlihattransaksiController implements Initializable {

    @FXML
    private TableView<TransaksiModel> tbvtransaksi;
    private TableView<PengunjungModel> tbvketerangan;
    @FXML
    private TextField searchbox;
    @FXML
    private TextField txttiket;
    @FXML
    private TextField txtplat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
        tbvtransaksi.getSelectionModel().selectFirst();
        setdata();
    }    
    
    @FXML
    public void setdata() {
        txttiket.setText(tbvtransaksi.getSelectionModel().getSelectedItem().getNotiket());
        txtplat.setText(tbvtransaksi.getSelectionModel().getSelectedItem().getPlat());
    }
    
    public void showdata() {
        ObservableList<TransaksiModel> data = FXMLDocumentController.dttransaksi.Load();
        if (data != null) {
            tbvtransaksi.getColumns().clear();
            tbvtransaksi.getItems().clear();
            TableColumn col = new TableColumn("notiket");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("notiket"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("plat");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("plat"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("kendaraan");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("kendaraan"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("idpetugas");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("idpetugas"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("idvalet");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("idvalet"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("masuk");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("masuk"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("keluar");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("keluar"));
            tbvtransaksi.getColumns().addAll(col);


            tbvtransaksi.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvtransaksi.getScene().getWindow().hide();;
        }
    }
    
    @FXML
    private void cariData(KeyEvent event) {
        TransaksiModel s = new TransaksiModel();
        String key = searchbox.getText();
        if (key != "") {
            ObservableList<TransaksiModel> data = FXMLDocumentController.dttransaksi.CariTransaksi(key);
            if (data != null) {
                tbvtransaksi.getColumns().clear();
                tbvtransaksi.getItems().clear();
                TableColumn col = new TableColumn("notiket");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("notiket"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("plat");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("plat"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("kendaraan");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("kendaraan"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("idpetugas");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("idpetugas"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("idvalet");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("idvalet"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("masuk");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("masuk"));
            tbvtransaksi.getColumns().addAll(col);
            col = new TableColumn("keluar");
            col.setCellValueFactory(new PropertyValueFactory<TransaksiModel, String>("keluar"));
            tbvtransaksi.getColumns().addAll(col);

                tbvtransaksi.setItems(data);
            } 
            else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
                a.showAndWait();
                tbvketerangan.getScene().getWindow().hide();
            }
        } else {
            showdata();
        }
    }
    
}
