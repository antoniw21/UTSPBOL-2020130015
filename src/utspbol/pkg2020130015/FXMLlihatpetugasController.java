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
public class FXMLlihatpetugasController implements Initializable {

    @FXML
    private TableView<PetugasModel> tbvpetugas;
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
        ObservableList<PetugasModel> data = FXMLDocumentController.dtpetugas.Load();
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
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLinputpetugas.fxml"));
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
        PetugasModel s = new PetugasModel();
        s = tbvpetugas.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLinputpetugas.fxml"));
            Parent root = (Parent) loader.load();
            FXMLinputpetugasController isidt = (FXMLinputpetugasController) loader.getController();
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
        tbvpetugas.getSelectionModel().selectFirst();
        tbvpetugas.requestFocus();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        PetugasModel s = new PetugasModel();
        s = tbvpetugas.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtpetugas.delete(s.getIdpetugas())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            tbvpetugas.getSelectionModel().selectFirst();
            tbvpetugas.requestFocus();
        }
    }
    
    
}
