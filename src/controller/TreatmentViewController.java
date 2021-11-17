package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Treatment;
import view.tm.TreatmentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TreatmentViewController {
    public AnchorPane treatmentViewContext;
    public TableView tblTreatment;
    public TableColumn colTId;
    public TableColumn colTreatment;
    public TableColumn colTFee;
    public JFXButton manageTreatment;

    public void initialize(){

        try {

            colTId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colTreatment.setCellValueFactory(new PropertyValueFactory<>("treat"));
            colTFee.setCellValueFactory(new PropertyValueFactory<>("fee"));

            treatToTable(new TreatmentController().getAllTreatment());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void treatToTable(ArrayList<Treatment> allTreatments) {
        ObservableList<TreatmentTM> treatList = FXCollections.observableArrayList();
        allTreatments.forEach(e->{
            treatList.add(new TreatmentTM(e.gettId(),e.gettTreat(),
                    e.gettFee()));
        });
        tblTreatment.setItems(treatList);

    }

    public void manageTreatment_OnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/TreatmentManageView.fxml"));
        pane = fxmlLoader.load();
        treatmentViewContext.getChildren().setAll(pane);
    }
}
