package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.impl.VehicleBOImpl;
import lk.ijse.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.dto.VehicleDto;
import lk.ijse.dto.tm.VehicleTm;
import lk.ijse.util.RegExPatterns;
import lk.ijse.util.SystemAlert;
import lk.ijse.util.TxtColours;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class VehicalFormController {


    @FXML
    private AnchorPane root;
    @FXML
    private TableColumn<?,?> colVehicleServiceDate;
    @FXML
    private DatePicker pickerLastServiceDate;
    @FXML
    private JFXTextField txtVehicleStatus;
    @FXML
    private JFXTextField txtNumPlateNo;
    @FXML
    private JFXTextField txtVehicleId;
    @FXML
    private TableColumn <?,?> colNumberplateNo;
    @FXML
    private TableColumn <?,?> colVehicleStatus;
    @FXML
    private TableColumn <?,?> colVehicleId;
    @FXML
    private TableView<VehicleTm> tblVehicleDetail;

    public void initialize(){
        vehicleCellvalueFactory();
        loadAllvehicle();
        setVehicle();
    }




    private void VehicleSetField(VehicleDto dto) {
        txtVehicleId.setText(dto.getVehicleId());
        txtVehicleStatus.setText(dto.getVehicleStatus());
        txtNumPlateNo.setText(dto.getNumberPlateNo());


    }

    public void vehicleCellvalueFactory() {
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicleId"));
        colVehicleStatus.setCellValueFactory(new PropertyValueFactory<>("vehicleStatus"));
        colVehicleServiceDate.setCellValueFactory(new PropertyValueFactory<>("lastServiceDate"));
        colNumberplateNo.setCellValueFactory(new PropertyValueFactory<>("numberPlateNo"));

    }
    public void loadAllvehicle(){
        ObservableList<VehicleTm> vehicleTmObservableList = FXCollections.observableArrayList();
        try {
            List<VehicleDto>  vehicleDtos =new VehicleBOImpl().getAll();
            for (VehicleDto dto : vehicleDtos){
                vehicleTmObservableList.add(
                        new VehicleTm(
                                dto.getVehicleId(),
                                dto.getVehicleStatus(),
                                dto.getLastServiceDate(),
                                dto.getNumberPlateNo()

                        )
                );
            }
            tblVehicleDetail.setItems(vehicleTmObservableList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }
    public void btnVehcleSaveOnAction(ActionEvent actionEvent) {


        if(!(txtVehicleId.getText().isEmpty()||txtNumPlateNo.getText().isEmpty()||txtVehicleStatus.getText().isEmpty()||pickerLastServiceDate.getValue().toString().isEmpty())){
            if (RegExPatterns.getVehicleId().matcher(txtVehicleId.getText()).matches()){
                TxtColours.setDefaultColours(txtVehicleId);
                if (RegExPatterns.getVehicleNumberPlate().matcher(txtNumPlateNo.getText()).matches()){
                    TxtColours.setDefaultColours(txtNumPlateNo);

                }else {
                    TxtColours.setErrorColours(txtNumPlateNo);
                    return;
                }
            }else {
                TxtColours.setErrorColours(txtVehicleId);
                return;
            }

        }else {
            TxtColours.setErrorColours(txtVehicleId);
            TxtColours.setErrorColours(txtNumPlateNo);
            TxtColours.setErrorColours(txtVehicleStatus);
            return;

        }
        String txtVehicleIdText = txtVehicleId.getText();
        String txtNumPlateNoText = txtNumPlateNo.getText();
        String txtVehicleStatusText = txtVehicleStatus.getText();
        String pickerLastServiceDateValue = String.valueOf(pickerLastServiceDate.getValue());

        VehicleDto dto = new VehicleDto(txtVehicleIdText, txtNumPlateNoText, txtVehicleStatusText, pickerLastServiceDateValue);

        try {
            boolean isSaved = new VehicleBOImpl().save(dto);
            if (isSaved){
                new SystemAlert(Alert.AlertType.CONFIRMATION,"Confirmation","New Vehicle details is Saved!", ButtonType.OK).show();
            }else {
                new SystemAlert(Alert.AlertType.WARNING,"Warning","Vehicle details has not Saved!",ButtonType.OK).show();
            }
        }catch (SQLException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage() ).showAndWait();
        }



    }
    public void btnVehcleUpdateOnAction(ActionEvent actionEvent) {
        if(!(txtVehicleId.getText().isEmpty()||txtNumPlateNo.getText().isEmpty()||txtVehicleStatus.getText().isEmpty()||pickerLastServiceDate.getValue().toString().isEmpty())){
            if (RegExPatterns.getVehicleId().matcher(txtVehicleId.getText()).matches()){
                TxtColours.setDefaultColours(txtVehicleId);
                if (RegExPatterns.getVehicleNumberPlate().matcher(txtNumPlateNo.getText()).matches()){
                    TxtColours.setDefaultColours(txtNumPlateNo);
                }else {
                    TxtColours.setErrorColours(txtNumPlateNo);
                    return;
                }
            }else {
                TxtColours.setErrorColours(txtVehicleId);
                return;
            }

        }else {
            TxtColours.setErrorColours(txtVehicleId);
            TxtColours.setErrorColours(txtNumPlateNo);
            TxtColours.setErrorColours(txtVehicleStatus);
            return;

        }
        String txtVehicleIdText = txtVehicleId.getText();
        String txtNumPlateNoText = txtNumPlateNo.getText();
        String txtVehicleStatusText = txtVehicleStatus.getText();
        String pickerLastServiceDateValue = String.valueOf(pickerLastServiceDate.getValue());

        VehicleDto dto = new VehicleDto(txtVehicleIdText, txtNumPlateNoText, txtVehicleStatusText, pickerLastServiceDateValue);
        try {
           boolean isUpdated =  new VehicleBOImpl().update(dto);
           if (isUpdated){
               new SystemAlert(Alert.AlertType.CONFIRMATION,"Confirmation","Vehicle details is Updated!", ButtonType.OK).show();
           }else {
               new SystemAlert(Alert.AlertType.WARNING,"Warning","Vehicle details has not Updated!",ButtonType.OK).show();
           }
        }catch (SQLException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage() ).showAndWait();
        }
    }
    public void btnVehcleEditOnAction(ActionEvent actionEvent) {
        clearVehicleCol();
    }
    public void btnVehcleDeleteOnAction(ActionEvent actionEvent) {
        if(!(txtVehicleId.getText().isEmpty()||txtNumPlateNo.getText().isEmpty()||txtVehicleStatus.getText().isEmpty()||pickerLastServiceDate.getValue().toString().isEmpty())){
            if (RegExPatterns.getVehicleId().matcher(txtVehicleId.getText()).matches()){
                TxtColours.setDefaultColours(txtVehicleId);
                if (RegExPatterns.getVehicleNumberPlate().matcher(txtNumPlateNo.getText()).matches()){
                    TxtColours.setDefaultColours(txtNumPlateNo);
                }else {
                    TxtColours.setErrorColours(txtNumPlateNo);
                    return;
                }
            }else {
                TxtColours.setErrorColours(txtVehicleId);
                return;
            }

        }else {
            TxtColours.setErrorColours(txtVehicleId);
            TxtColours.setErrorColours(txtNumPlateNo);
            TxtColours.setErrorColours(txtVehicleStatus);
            return;

        }
        String txtVehicleIdText = txtVehicleId.getText();


        try {
            boolean isDeleted = new VehicleDAOImpl().delete(txtVehicleIdText);
            if (isDeleted){
                new SystemAlert(Alert.AlertType.CONFIRMATION,"Confirmation","Vehicle details is Deleted!", ButtonType.OK).show();
            }else {
                new SystemAlert(Alert.AlertType.WARNING,"Warning","Vehicle details has not Deleted!",ButtonType.OK).show();
            }
        }catch (SQLException e ){
            new Alert(Alert.AlertType.ERROR,e.getMessage() ).showAndWait();
        }


    }
    public void clearVehicleCol(){
        txtVehicleId.clear();
        txtVehicleStatus.clear();
        txtNumPlateNo.clear();

    }

    public void btnDashBoardOnAction(ActionEvent actionEvent) {
        try {
            Parent node = FXMLLoader.load(this.getClass().getResource("/view/dashBoard_form.fxml"));
            this.root.getChildren().clear();
            this.root.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void setVehicle(){
        tblVehicleDetail.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldvalue,newValue)->{
                    VehicleDto dto = new VehicleDto(
                            newValue.getVehicleId(),
                            newValue.getVehicleStatus(),
                            newValue.getNumberPlateNo(),
                            newValue.getLastServiceDate()

                    );
                    vehicleSetField(dto);
                });
    }
    private void vehicleSetField(VehicleDto dto) {
        txtVehicleId.setText(dto.getVehicleId());
        txtVehicleStatus.setText(dto.getVehicleStatus());
        txtNumPlateNo.setText(dto.getNumberPlateNo());
        pickerLastServiceDate.setValue(LocalDate.parse(dto.getLastServiceDate()));

    }
}

