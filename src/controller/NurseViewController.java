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
import model.Nurse;
import view.tm.NurseTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class NurseViewController {
    public AnchorPane nurseViewContext;
    public TableView<NurseTM> tblNurse;
    public TableColumn colNId;
    public TableColumn colNName;
    public TableColumn colNAge;
    public TableColumn colNGender;
    public TableColumn colNAddress;
    public TableColumn colNContact;
    public JFXButton manageDoctor;

    public void initialize(){
        try {
            colNId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colNAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colNGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colNAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colNContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

            nurseToTable(new NurseController().getAllNurse());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void nurseToTable(ArrayList<Nurse> allNurse) {
        ObservableList<NurseTM> nurseList = FXCollections.observableArrayList();
        allNurse.forEach(e->{
            nurseList.add(new NurseTM(e.getnId(),e.getnName(),e.getnAge(),e.getnGender(), e.getnAddress(), e.getnContact()));
        });
        tblNurse.setItems(nurseList);
    }

    //==================================================================================================================

    public void manageNurse_OnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane pane;
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/NurseManageView.fxml"));
        pane = fxmlLoader.load();
        nurseViewContext.getChildren().setAll(pane);

    }
}
