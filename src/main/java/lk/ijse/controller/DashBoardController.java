package lk.ijse.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.dao.factory.DAOFactory;
import lk.ijse.util.SoundsAssits;
import lk.ijse.util.SystemAlert;
import lombok.SneakyThrows;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblEmployee;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblOrders;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblDate;

    CustomerBO customerBO= (CustomerBOImpl) BOFactory.getDaoFactory().getDAO(BOFactory.BOTypes.CUSTOMER);
    EmployeeBO employeeBO= (EmployeeBOImpl) BOFactory.getDaoFactory().getDAO(BOFactory.BOTypes.EMPLOYEE);
    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDateandTime();
        allDetailsLoader();
        //welcomeVoice();
    }
    private void loadDateandTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(format.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e ->{
            DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalTime.now().format(format2));
        }), new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void welcomeVoice(){
        new SoundsAssits().welcome();
    }

    public void allDetailsLoader() throws SQLException {
        try {
            String totalCustomer = customerBO.getTotalCustomers();
            String totalEmployee =  employeeBO.getTotalEmployees();
            String totalOrders = new OrderDAOImpl().getAllOrdersCount();
            System.out.println(totalCustomer+" "+totalEmployee+" "+totalOrders);
            lblCustomer.setText(totalCustomer);
            lblEmployee.setText(totalEmployee);
            lblOrders.setText(totalOrders);
        }catch (SQLException e){
            e.printStackTrace();
            new SystemAlert(Alert.AlertType.WARNING,"Warrning","Something went wrong").show();
        }


    }

}
