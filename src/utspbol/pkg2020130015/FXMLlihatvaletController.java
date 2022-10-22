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
public class FXMLlihatvaletController implements Initializable {

    @FXML
    private TableView<ValetModel> tbvvalet;
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
        ObservableList<ValetModel> data = FXMLDocumentController.dtvalet.Load();
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
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLinputvalet.fxml"));
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
        ValetModel s = new ValetModel();
        s = tbvvalet.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLinputvalet.fxml"));
            Parent root = (Parent) loader.load();
            FXMLinputvaletController isidt = (FXMLinputvaletController) loader.getController();
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
        tbvvalet.getSelectionModel().selectFirst();
        tbvvalet.requestFocus();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        ValetModel s = new ValetModel();
        s = tbvvalet.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtvalet.delete(s.getIdvalet())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            tbvvalet.getSelectionModel().selectFirst();
            tbvvalet.requestFocus();
        }
    }
    
}
