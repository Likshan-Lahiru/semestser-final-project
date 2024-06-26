package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.impl.SupplierBOImpl;
import lk.ijse.bo.custom.impl.ToolBOImpl;
import lk.ijse.dao.custom.impl.StockListDAOImpl;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.dao.custom.impl.ToolDAOImpl;
import lk.ijse.dto.*;
import lk.ijse.dto.tm.StockListTm;
import lk.ijse.util.SystemAlert;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class toolStockController {

    @FXML
    private JFXToggleButton testToggle;
    @FXML
    private TableColumn colStockQtyOnHand;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblSuppliedDate;
    @FXML
    private TextField txtToolPriceUnit;
    @FXML
    private TableColumn<?, ?> colToolId;
    @FXML
    private TableColumn<?, ?> colToolName;
    @FXML
    private TableColumn<?, ?> colToolUnitPrice;
    @FXML
    private TableColumn<?, ?> ColQty;
    @FXML
    private TableColumn<?, ?> colTotalPrice;
    @FXML
    private TableColumn<?, ?> colAction;
    @FXML
    private TableView tblSuppliedDetail;
    @FXML
    private Label lblNetTotal;
    @FXML
    private JFXComboBox cmbSupplierId;
    @FXML
    private Label lblSupplierName;
    @FXML
    private JFXComboBox cmbToolID;
    @FXML
    private Label lblToolName;
    @FXML
    private Label lblQtyOnHand;
    @FXML
    private TextField txtToolQuantitySuppliedCount;
    @FXML
    private TableView<StockListTm> tblStockList;
    @FXML
    private TableColumn<?, ?> colStockListName;
    @FXML
    private TableColumn<?, ?> colStockListId;
    @FXML
    private TableColumn<?, ?> colOrderDetailsQty;
    @FXML
    private TableColumn<?, ?> colStockListWasteCount;
    @FXML
    private TableColumn<?, ?> colStockListUpdatedate;
    private Label lblToolId;
    @FXML
    private DatePicker pickerStockListLastUpdateDate;
    @FXML
    private Label lblStckListToolId;
    @FXML
    private Label lblStockToolName;
    @FXML
    private TextField txtStckListWasteCount;
    @FXML
    private TextField txtStckListQtyOnHand;


    private final ObservableList<StockListTm> obList = FXCollections.observableArrayList();

    public toolStockController() {
    }

    public void initialize() {

        loadToolid();
        loadSupplierIds();
        setDate();
        setCellValueFactory();
        setStockListCellValueFactory();
        loadStockList();
        settoolData();


    }


    private void setCellValueFactory() {
        colToolId.setCellValueFactory(new PropertyValueFactory<>("toolId"));
        colToolName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        colToolUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        ColQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void setStockListCellValueFactory() {
        colStockListId.setCellValueFactory(new PropertyValueFactory<>("toolId"));
        colStockListName.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        colStockListUpdatedate.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedDate"));
        colStockListWasteCount.setCellValueFactory(new PropertyValueFactory<>("wasteCount"));
        colStockQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));


    }

    private void loadStockList() {

        ObservableList<StockListTm> obList = FXCollections.observableArrayList();

        try {
            List<ToolDto> dtoList = new ToolBOImpl().getAll();

            for (ToolDto dto : dtoList) {
                obList.add(
                        new StockListTm(
                                dto.getToolId(),
                                dto.getToolName(),
                                dto.getQtyOnhand()


                        )
                );
            }

            tblStockList.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblSuppliedDate.setText(date);
    }

    private void loadToolid() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ToolDto> toolDtoList = new ToolBOImpl().getAll();

            for (ToolDto toolDto : toolDtoList) {
                obList.add(toolDto.getToolId());
            }

            cmbToolID.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSupplierIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SupplierDto> cusList = new SupplierBOImpl().getAll();

            for (SupplierDto dto : cusList) {
                obList.add(dto.getSupplierId());
            }
            cmbSupplierId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddToListOnAction(ActionEvent actionEvent) {
        try {
            String toolId = (String) cmbToolID.getValue();
            String supplierIdd = (String) cmbSupplierId.getValue();
            if ((toolId.isEmpty() || supplierIdd.isEmpty()||txtToolQuantitySuppliedCount.getText().isEmpty()||txtToolPriceUnit.getText().isEmpty())){
                new SystemAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields", ButtonType.OK).show();
                return;
            }
        }catch (Exception e){
            new SystemAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields", ButtonType.OK).show();
            return;
        }




            String supplierId = (String) cmbSupplierId.getValue();
            String toolID = (String) cmbToolID.getValue();
            String toolName = lblToolName.getText();
            int qtyOnHand = Integer.valueOf(lblQtyOnHand.getText());
            int toolQuantitySuppliedCount = Integer.valueOf(txtToolQuantitySuppliedCount.getText());
            Double toolPriceUnit = Double.valueOf(txtToolPriceUnit.getText());
            String supplierNameText = lblSupplierName.getText();
            String orderDate = lblSuppliedDate.getText();
            String lastUpdatedDate = String.valueOf(pickerStockListLastUpdateDate.getValue());
            String wasteCount = txtStckListWasteCount.getText();

            Double total = calTotal(toolPriceUnit, toolQuantitySuppliedCount);
            Button btn = new Button("remove");
            btn.setCursor(Cursor.HAND);

            btn.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new SystemAlert(Alert.AlertType.INFORMATION, "Information", "Are you sure to remove?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                    int index = tblSuppliedDetail.getSelectionModel().getSelectedIndex();

                    if (index >= 0 && index < obList.size()) {
                        obList.remove(index);
                        tblSuppliedDetail.refresh();
                        calculateNetTotal();
                    } else {

                        new Alert(Alert.AlertType.WARNING, "Please select an item to remove.", ButtonType.OK).show();
                    }
                }
            });
            for (int i = 0; i < tblSuppliedDetail.getItems().size(); i++) {
                if (toolID.equals(colToolId.getCellData(i))) {
                    toolQuantitySuppliedCount += (int) colOrderDetailsQty.getCellData(i);
                    total += (Double) colTotalPrice.getCellData(i) * toolQuantitySuppliedCount;

                    obList.get(i).setQty(toolQuantitySuppliedCount);
                    obList.get(i).setTotal(total);

                    tblSuppliedDetail.refresh();
                    calculateNetTotal();
                    return;
                }
            }

            obList.add(new StockListTm(
                    supplierId,
                    supplierNameText,
                    toolID,
                    toolName,
                    orderDate,
                    qtyOnHand,
                    toolQuantitySuppliedCount,
                    toolPriceUnit,
                    total,
                    btn,
                    lastUpdatedDate,
                    wasteCount));

            tblSuppliedDetail.setItems(obList);
            calculateNetTotal();
            clearHistory();

    }

    public void clearHistory() {


        lblSupplierName.setText("");
        lblToolName.setText("");
        lblQtyOnHand.setText("");
        txtToolQuantitySuppliedCount.clear();
        txtToolPriceUnit.clear();
    }

    private void calculateNetTotal() {

        double total = 0;
        for (int i = 0; i < tblSuppliedDetail.getItems().size(); i++) {
            total += (double) colTotalPrice.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(total));

    }

    private Double calTotal(Double toolPriceUnit, int toolQuantitySuppliedCount) {
        double total = toolPriceUnit * toolQuantitySuppliedCount;
        return total;
    }

    @FXML
    public void cmbToolIdOnAction(ActionEvent actionEvent) {
        String code = (String) cmbToolID.getValue();


        try {
            ToolDto dto = new ToolBOImpl().search(code);

            lblToolName.setText(dto.getToolName());
            lblQtyOnHand.setText(String.valueOf(dto.getQtyOnhand()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnPlaceNewStockOnAction(ActionEvent actionEvent) {
        try {
            ObservableList<StockListTm> dataList = tblSuppliedDetail.getItems();

            if (dataList.isEmpty()) {
                new SystemAlert(Alert.AlertType.WARNING, "Warning", "Stock List is Empty!", ButtonType.OK).show();
                return;
            }
        }catch (Exception e){
            System.out.println("TableView is empty");
        }

        List<StockListTm> stockList = new ArrayList<>();
        for (StockListTm stockListTm : obList) {
            stockList.add(stockListTm);
        }
        var stockListDto = new StockListDto(stockList);
        try {
            boolean isAdded = new StockListDAOImpl().addStockList(stockListDto);
            if (isAdded) {
                new SystemAlert(Alert.AlertType.INFORMATION, "Information", "Stock List Added Successfully!", ButtonType.OK).show();
                tblSuppliedDetail.getItems().clear();
            } else {
                new SystemAlert(Alert.AlertType.WARNING, "Error", "Stock List Not Added!", ButtonType.OK).show();
            }
        } catch (SQLException e) {
            new SystemAlert(Alert.AlertType.ERROR, "Error", e.getMessage(), ButtonType.OK).show();
        }

    }

    public void cmbSupplierIdOnAction(ActionEvent actionEvent) throws SQLException {
        String id = (String) cmbSupplierId.getValue();
        SupplierDto dto = new SupplierBOImpl().search(id);

        lblSupplierName.setText(dto.getSupplierName());
    }

    public void btnNewSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/supplier_form.fxml"));

        this.root.getChildren().clear();
        this.root.getChildren().add(node);
    }

    public void btnGetReportOnAction(ActionEvent actionEvent) throws SQLException {

        try {


            InputStream design = getClass().getResourceAsStream("/report/Supplier_history.jrxml");
            JasperDesign load = JRXmlLoader.load(design);

            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
           e.printStackTrace();


        }
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
    @FXML
    void btnWasteToolStock(ActionEvent event) {
        try {
            if ((lblStckListToolId.getText().isEmpty()||txtStckListWasteCount.getText().isEmpty()||
                    txtStckListQtyOnHand.getText().isEmpty()||txtStckListWasteCount.getText().isEmpty())) {
                new SystemAlert(Alert.AlertType.WARNING, "Warning", "Fill All Fields", ButtonType.OK).show();
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        String toolId = lblStckListToolId.getText();
        String toolName = lblStockToolName.getText();
        String wasteCount = txtStckListWasteCount.getText();
        String qtyOnHand = txtStckListQtyOnHand.getText();
        String lastUpdatedDate = String.valueOf(pickerStockListLastUpdateDate.getValue());

        ToolWasteDetailDto dto = new ToolWasteDetailDto(toolId,toolName,Integer.parseInt(qtyOnHand),String.valueOf(wasteCount),lastUpdatedDate);
        try {
            boolean isAdded = new ToolDAOImpl().addToolWasteDetail(dto);
            if (isAdded) {
                new SystemAlert(Alert.AlertType.INFORMATION, "Information", "Tool Waste Detail Added Successfully!", ButtonType.OK).show();
            }else {
                new SystemAlert(Alert.AlertType.WARNING, "Warning", "Tool Waste Detail Not Added!", ButtonType.OK).show();
            }
        }catch (SQLException e){
            new SystemAlert(Alert.AlertType.ERROR, "Error", e.getMessage(), ButtonType.OK).show();
        }


    }
    private void settoolData(){
        tblStockList.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldvalue,newValue)->{
                    ToolWasteDetailDto dto = new ToolWasteDetailDto(
                            newValue.getToolId(),
                            newValue.getToolName(),
                            newValue.getQtyOnHand(),
                            newValue.getWasteCount(),
                            newValue.getLastUpdatedDate()


                    );
                    toolSetField(dto);
                });
    }

    private void toolSetField(ToolWasteDetailDto dto) {
        lblStckListToolId.setText(dto.getToolId());
        lblStockToolName.setText(dto.getToolName());
        txtStckListWasteCount.setText(String.valueOf(dto.getWasteCount()));
        txtStckListQtyOnHand.setText(String.valueOf(dto.getQtyOnhand()));
    }

    public void btnWasteReportInAction(ActionEvent actionEvent) {
        try {


            InputStream design = getClass().getResourceAsStream("/report/Waste_report.jrxml");
            JasperDesign load = JRXmlLoader.load(design);

            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();


        }
    }
}
