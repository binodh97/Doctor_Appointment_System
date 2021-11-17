package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Nurse;
import model.Patient;
import util.ValidationUtil;
import view.tm.NurseTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Pattern;

public class NurseManageViewController {
    public AnchorPane nurseContext;
    public JFXButton clear;
    public JFXButton delete;
    public JFXButton update;
    public JFXButton save;
    public JFXTextField searchNID;
    public TableView<NurseTM> tblNurse;
    public TableColumn colNId;
    public TableColumn colNName;
    public TableColumn colNAge;
    public TableColumn colNGender;
    public TableColumn colNAddress;
    public TableColumn colNContact;
    public TextField txtNuId;
    public TextField txtNuName;
    public TextField txtNuAge;
    public TextField txtNuGender;
    public TextField txtNuAddress;
    public TextField txtNuContact;
    public TextField txtNuSearch;


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(N00-)[0-9]{3,4}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,30}$");
    Pattern agePattern = Pattern.compile("^[0-9]{1,3}$");
    Pattern genderPattern = Pattern.compile("^(M|F){1}$");
    Pattern addressPattern = Pattern.compile("^[A-z.0-9/, '']{6,70}$");
    Pattern contactPattern = Pattern.compile("^[0-9]{3}[-]?[0-9]{7}$");


    public void initialize(){

        try {
            colNId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colNAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            colNGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colNAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colNContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

            nurseToTable(new NurseController().getAllNurse());

            storeValidations();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void storeValidations() {
        map.put(txtNuId,idPattern);
        map.put(txtNuName,namePattern);
        map.put(txtNuAge,agePattern);
        map.put(txtNuGender,genderPattern);
        map.put(txtNuAddress,addressPattern);
        map.put(txtNuContact,contactPattern);
    }

    private void nurseToTable(ArrayList<Nurse> allNurse) {
        ObservableList<NurseTM> nurseList = FXCollections.observableArrayList();
        allNurse.forEach(e->{
            nurseList.add(new NurseTM(e.getnId(),e.getnName(),e.getnAge(),e.getnGender(), e.getnAddress(), e.getnContact()));
        });
        tblNurse.setItems(nurseList);
    }

    //==================================================================================================================

    public void btnSaveNurse_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Nurse nurse = new Nurse(
                txtNuId.getText(),
                txtNuName.getText(),
                txtNuAge.getText(),
                txtNuGender.getText(),
                txtNuAddress.getText(),
                txtNuContact.getText()
        );

        if( NurseController.saveNurse(nurse)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully").show();
            clearDetail();
            nurseToTable(new NurseController().getAllNurse());
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
            clearDetail();
        }

    }

    public void nurseSearch_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nurseId =txtNuSearch.getText();
        Nurse nurse = new NurseController().getNurse(nurseId);
        if (nurse==null){
            new Alert(Alert.AlertType.ERROR,"Empty Result Set").show();
        }else{
            setData(nurse);
        }

    }

    public void btnClearDetail_OnAction(ActionEvent actionEvent) {
        clearDetail();
    }

    public void btnUpdateNurse_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Nurse nurse = new Nurse(
                txtNuId.getText(),
                txtNuName.getText(),
                txtNuAge.getText(),
                txtNuGender.getText(),
                txtNuAddress.getText(),
                txtNuContact.getText()
        );

        if (new NurseController().updateNurse(nurse)) {
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            clearDetail();
            nurseToTable(new NurseController().getAllNurse());

        } else {
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
        }
    }

    public void btnDeleteNurse_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to Delete?", yes, no);
        alert.setTitle("Confirmation alert");
        Optional<ButtonType> result = alert.showAndWait();


        if (result.orElse(no) == yes) {
            if (new NurseController().deleteNurse(txtNuId.getText())) ;
                new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                clearDetail();
                nurseToTable(new NurseController().getAllNurse());
        } else{
            new Alert(Alert.AlertType.ERROR, "Try Again").show();
            clearDetail();
        }
    }

    void setData(Nurse nurse) {
        txtNuId.setText(nurse.getnId());
        txtNuName.setText(nurse.getnName());
        txtNuAge.setText(nurse.getnAge());
        txtNuGender.setText(nurse.getnGender());
        txtNuAddress.setText(nurse.getnAddress());
        txtNuContact.setText(nurse.getnContact());
    }

    private void clearDetail() {
        txtNuId.clear();
        txtNuName.clear();
        txtNuAge.clear();
        txtNuGender.clear();
        txtNuAddress.clear();
        txtNuContact.clear();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

        Object response = ValidationUtil.validate(map,save);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField){
                TextField errorTextFiled = (TextField) response;
                errorTextFiled.requestFocus();
            }else if (response instanceof Boolean){
                new Alert(Alert.AlertType.INFORMATION,"Saved Successfully").showAndWait();
            }
        }
    }

}
