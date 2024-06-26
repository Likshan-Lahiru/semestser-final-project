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
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.OrderDeatilBO;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.bo.custom.impl.OrderDeatilBOImpl;
import lk.ijse.bo.custom.impl.SupplierBOImpl;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.dto.SupplierDto;
import lk.ijse.dto.tm.SupplierTm;
import lk.ijse.util.RegExPatterns;
import lk.ijse.util.SystemAlert;
import lk.ijse.util.TxtColours;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane newAnchor;
    @FXML
    private TableView<SupplierTm> tblSupplier;
    @FXML
    private TextField txtSearchSupplierID;
    @FXML
    private JFXTextField txtSupplierId;
    @FXML
    private JFXTextField txtSupplierName;
    @FXML
    private JFXTextField txtSupplierNIC;
    @FXML
    private JFXTextField txtSupplierAddress;
    @FXML
    private JFXTextField txtSupplierContactNumber;
    @FXML
    private TableColumn<?, ?> colSupplierContactNumber;
    @FXML
    private TableColumn<?, ?> colSupplierAddress;
    @FXML
    private TableColumn<?, ?> colSupplierNIC;
    @FXML
    private TableColumn<?, ?> colSupplierName;
    @FXML
    private TableColumn<?, ?> colSupplierD;
    SupplierBO supplierBO= (SupplierBOImpl) BOFactory.getDaoFactory().getDAO(BOFactory.BOTypes.SUPPLIER);

    public void initialize() {
        supplierCellvalueFactory();
        loadAllSupplier();
        setSupplier();
    }

    private void setSupplier() {
        tblSupplier.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldvalue, newValue) -> {
                    SupplierDto dto = new SupplierDto(
                            newValue.getSupplierId(),
                            newValue.getSupplierName(),
                            newValue.getSupplierNic(),
                            newValue.getSupplierAddress(),
                            newValue.getSupplierContactNumber()
                    );
                    SupplierSetField(dto);
                });
    }

    private void SupplierSetField(SupplierDto dto) {
        txtSupplierId.setText(dto.getSupplierId());
        txtSupplierName.setText(dto.getSupplierName());
        txtSupplierAddress.setText(dto.getSupplierNIC());
        txtSupplierNIC.setText(dto.getSupplierAddress());
        txtSupplierContactNumber.setText(dto.getSupplierContactNumber());
    }

    private void loadAllSupplier() {

        ObservableList<SupplierTm> supplierTmObservableList = FXCollections.observableArrayList();
        try {
            List<SupplierDto> supplierDtoList = supplierBO.getAll();
            for (SupplierDto dto : supplierDtoList) {
                supplierTmObservableList.add(
                        new SupplierTm(
                                dto.getSupplierId(),
                                dto.getSupplierName(),
                                dto.getSupplierNIC(),
                                dto.getSupplierAddress(),
                                dto.getSupplierContactNumber()
                        )
                );
            }
            tblSupplier.setItems(supplierTmObservableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void supplierCellvalueFactory() {
        colSupplierD.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSupplierNIC.setCellValueFactory(new PropertyValueFactory<>("supplierNic"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        colSupplierContactNumber.setCellValueFactory(new PropertyValueFactory<>("supplierContactNumber"));

    }

    public void btnSupplierIDSearchOnAction(ActionEvent actionEvent) {
        String searchSupplierIDText = txtSearchSupplierID.getText();
        if (searchSupplierIDText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Enter the Supplier Id").showAndWait();
            return;
        }

        try {
            SupplierDto dto = supplierBO.search(searchSupplierIDText);
            if (dto != null) {
                SupplierSetField(dto);
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Does not Found!").showAndWait();
                clearSupplierField();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    public void btnSupplierDeleteOnAction(ActionEvent actionEvent) {
        if (!(txtSupplierId.getText().isEmpty() || txtSupplierName.getText().isEmpty() || txtSupplierNIC.getText().isEmpty() || txtSupplierAddress.getText().isEmpty() || txtSupplierContactNumber.getText().isEmpty())) {
            if (RegExPatterns.getNamePattern().matcher(txtSupplierName.getText()).matches()) {
                TxtColours.setDefaultColours(txtSupplierName);
                if (RegExPatterns.getNICPattern().matcher(txtSupplierNIC.getText()).matches()) {
                    TxtColours.setDefaultColours(txtSupplierNIC);
                    if (RegExPatterns.getAddressPattern().matcher(txtSupplierAddress.getText()).matches()) {
                        TxtColours.setDefaultColours(txtSupplierAddress);
                        if (RegExPatterns.getContactNumberPattern().matcher(txtSupplierContactNumber.getText()).matches()) {
                            TxtColours.setDefaultColours(txtSupplierContactNumber);
                        } else {
                            TxtColours.setErrorColours(txtSupplierContactNumber);
                            return;
                        }
                    } else {
                        TxtColours.setErrorColours(txtSupplierAddress);
                        return;
                    }
                } else {
                    TxtColours.setErrorColours(txtSupplierNIC);
                    return;
                }

            }else {
                TxtColours.setErrorColours(txtSupplierName);
                return;
            }
        }else {
            TxtColours.setErrorColours(txtSupplierId);
            TxtColours.setErrorColours(txtSupplierName);
            TxtColours.setErrorColours(txtSupplierNIC);
            TxtColours.setErrorColours(txtSupplierAddress);
            TxtColours.setErrorColours(txtSupplierContactNumber);
            return;
        }
        String supplierId = txtSupplierId.getText();

        try {
            boolean isDeleted = supplierBO.delete(supplierId);
            if (isDeleted){
                new SystemAlert(Alert.AlertType.CONFIRMATION, "Success", "Supplier Deleted Successfully!", ButtonType.OK).show();
                clearSupplierField();
            }else {
                new SystemAlert(Alert.AlertType.ERROR, "Error", "Supplier Does not Found!", ButtonType.OK).show();
            }


        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearSupplierField();
    }

    public void btnSupplierUpdateOnAction(ActionEvent actionEvent) {
        if (!(txtSupplierId.getText().isEmpty() || txtSupplierName.getText().isEmpty() || txtSupplierNIC.getText().isEmpty() || txtSupplierAddress.getText().isEmpty() || txtSupplierContactNumber.getText().isEmpty())) {
            if (RegExPatterns.getNamePattern().matcher(txtSupplierName.getText()).matches()) {
                TxtColours.setDefaultColours(txtSupplierName);
                if (RegExPatterns.getNICPattern().matcher(txtSupplierNIC.getText()).matches()) {
                    TxtColours.setDefaultColours(txtSupplierNIC);
                    if (RegExPatterns.getAddressPattern().matcher(txtSupplierAddress.getText()).matches()) {
                        TxtColours.setDefaultColours(txtSupplierAddress);
                        if (RegExPatterns.getContactNumberPattern().matcher(txtSupplierContactNumber.getText()).matches()) {
                            TxtColours.setDefaultColours(txtSupplierContactNumber);
                        } else {
                            TxtColours.setErrorColours(txtSupplierContactNumber);
                            return;
                        }
                    } else {
                        TxtColours.setErrorColours(txtSupplierAddress);
                        return;
                    }
                } else {
                    TxtColours.setErrorColours(txtSupplierNIC);
                    return;
                }

            }else {
                TxtColours.setErrorColours(txtSupplierName);
                return;
            }
        }else {
            TxtColours.setErrorColours(txtSupplierId);
            TxtColours.setErrorColours(txtSupplierName);
            TxtColours.setErrorColours(txtSupplierNIC);
            TxtColours.setErrorColours(txtSupplierAddress);
            TxtColours.setErrorColours(txtSupplierContactNumber);
            return;
        }
        String supplierIdText = txtSupplierId.getText();
        String supplierNameText = txtSupplierName.getText();
        String supplierNICText = txtSupplierNIC.getText();
        String supplierAddressText = txtSupplierAddress.getText();
        String supplierContactNumberText = txtSupplierContactNumber.getText();

        SupplierDto dto = new SupplierDto(
                supplierIdText,
                supplierNameText,
                supplierNICText,
                supplierAddressText,
                supplierContactNumberText

        );

        try {
            boolean isUpdated = supplierBO.update(dto);
            if (isUpdated){
                new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Supplier Updated Successfully!", ButtonType.OK).show();
                clearSupplierField();
            }else {
                new SystemAlert(Alert.AlertType.WARNING, "Warning", "Supplier Does not Found!", ButtonType.OK).show();
            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }

    public void btnCustomerSaveOnAction(ActionEvent actionEvent) {
        if (!(txtSupplierId.getText().isEmpty() || txtSupplierName.getText().isEmpty() || txtSupplierNIC.getText().isEmpty() || txtSupplierAddress.getText().isEmpty() || txtSupplierContactNumber.getText().isEmpty())) {
            if (RegExPatterns.getNamePattern().matcher(txtSupplierName.getText()).matches()) {
                TxtColours.setDefaultColours(txtSupplierName);
                if (RegExPatterns.getNICPattern().matcher(txtSupplierNIC.getText()).matches()) {
                    TxtColours.setDefaultColours(txtSupplierNIC);
                    if (RegExPatterns.getAddressPattern().matcher(txtSupplierAddress.getText()).matches()) {
                        TxtColours.setDefaultColours(txtSupplierAddress);
                        if (RegExPatterns.getContactNumberPattern().matcher(txtSupplierContactNumber.getText()).matches()) {
                            TxtColours.setDefaultColours(txtSupplierContactNumber);
                        } else {
                            TxtColours.setErrorColours(txtSupplierContactNumber);
                            return;
                        }
                    } else {
                        TxtColours.setErrorColours(txtSupplierAddress);
                        return;
                    }
                } else {
                    TxtColours.setErrorColours(txtSupplierNIC);
                    return;
                }

            }else {
                TxtColours.setErrorColours(txtSupplierName);
                return;
            }
        }else {
            TxtColours.setErrorColours(txtSupplierId);
            TxtColours.setErrorColours(txtSupplierName);
            TxtColours.setErrorColours(txtSupplierNIC);
            TxtColours.setErrorColours(txtSupplierAddress);
            TxtColours.setErrorColours(txtSupplierContactNumber);
            return;
        }
        String supplierIdText = txtSupplierId.getText();
        String supplierNameText = txtSupplierName.getText();
        String supplierNICText = txtSupplierNIC.getText();
        String supplierAddressText = txtSupplierAddress.getText();
        String supplierContactNumberText = txtSupplierContactNumber.getText();

            SupplierDto dto = new SupplierDto(supplierIdText, supplierNameText, supplierNICText, supplierAddressText, supplierContactNumberText);

            try {
                boolean isSaved = supplierBO.save(dto);
                if (isSaved) {
                    new SystemAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Supplier Saved Successfully!", ButtonType.OK).show();

                    clearSupplierField();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            }



        }
        public void clearSupplierField () {
            txtSupplierId.clear();
            txtSupplierName.clear();
            txtSupplierAddress.clear();
            txtSupplierContactNumber.clear();
            txtSupplierNIC.clear();
            txtSearchSupplierID.clear();
        }


        public void btnStockListOnAction (ActionEvent actionEvent) throws IOException {
            Parent node = FXMLLoader.load(this.getClass().getResource("/view/tool_sctock_form.fxml"));

            this.root.getChildren().clear();
            this.root.getChildren().add(node);
        }

        public void btnDashBoardOnAction (ActionEvent actionEvent){
            try {
                Parent node = FXMLLoader.load(this.getClass().getResource("/view/dashBoard_form.fxml"));
                this.root.getChildren().clear();
                this.root.getChildren().add(node);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }