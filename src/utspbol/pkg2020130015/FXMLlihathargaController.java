/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package utspbol.pkg2020130015;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author antoni
 */
public class FXMLlihathargaController implements Initializable {

    @FXML
    private TableView<HargaModel> tbvharga;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnhapus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdata();
    }

    public void showdata() {
        ObservableList<HargaModel> data = FXMLDocumentController.dtharga.Load();
        if (data != null) {
            tbvharga.getColumns().clear();
            tbvharga.getItems().clear();
            TableColumn col = new TableColumn("kendaraan");
            col.setCellValueFactory(new PropertyValueFactory<HargaModel, String>("kendaraan"));
            tbvharga.getColumns().addAll(col);
            col = new TableColumn("parkir");
            col.setCellValueFactory(new PropertyValueFactory<HargaModel, String>("parkir"));
            tbvharga.getColumns().addAll(col);
            col = new TableColumn("valet");
            col.setCellValueFactory(new PropertyValueFactory<HargaModel, String>("valet"));
            tbvharga.getColumns().addAll(col);

            tbvharga.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvharga.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLinputharga.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateklik(ActionEvent event) {
        HargaModel s = new HargaModel();
        s = tbvharga.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLinputharga.fxml"));
            Parent root = (Parent) loader.load();
            FXMLinputhargaController isidt = (FXMLinputhargaController) loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        tbvharga.getSelectionModel().selectFirst();
        tbvharga.requestFocus();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        HargaModel s = new HargaModel();
        s = tbvharga.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtharga.delete(s.getKendaraan())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            tbvharga.getSelectionModel().selectFirst();
            tbvharga.requestFocus();
        }
    }

}
