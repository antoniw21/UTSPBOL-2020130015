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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author antoni
 */
public class FXMLlihattransaksiController implements Initializable {

    @FXML
    private TableView<TransaksiModel> tbvtransaksi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
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
    
}
