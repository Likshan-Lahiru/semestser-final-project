package lk.ijse.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.bo.custom.impl.LoginBOImpl;
import lk.ijse.dao.custom.impl.LoginDAOImpl;
import lk.ijse.dto.LoginDto;
import lk.ijse.dto.SignUpDto;
import lk.ijse.util.Mail;
import lk.ijse.util.SoundsAssits;
import lk.ijse.util.SystemAlert;
import java.io.IOException;
import java.sql.SQLException;


public class LoginFormController {
    @FXML
    private Label lblTime;
    @FXML
    private ImageView lblClose;
    @FXML
    private ImageView lblOpenEye;
    @FXML
    private TextField txtShowPassword;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView textPng;

    @FXML

    String time;
    LoginBO loginBO= (LoginBO) BOFactory.getDaoFactory().getDAO(BOFactory.BOTypes.LOGIN);

    private String hiru = "lahiru212001@gmail.com";
    public void initialize(){
        setTime();
    }
    public void setTime(){
       lblTime.setText(java.time.LocalTime.now().toString());

    }

    public LoginFormController() {
    }

    @FXML
    public void btnSignInOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/signup_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign up page ");
        stage.centerOnScreen();
    }


    public void btnLoginPageOnAction(ActionEvent actionEvent) throws SQLException, IOException {

        String nameText = txtUserName.getText();


        String passwordText = txtPassword.getText();
        if (nameText.isEmpty()||passwordText.isEmpty()){
            txtPassword.setStyle("-fx-border-color: #ff004f;");
            txtUserName.setStyle("-fx-border-color: #ff004f;");
            return;
        }

        SignUpDto dto1 = new LoginBOImpl().getName(nameText);
        String name = dto1.getUserName();
        String name2 = dto1.getScondName();

        System.out.println(dto1.getFirstName());
        System.out.println(dto1.getScondName());
        System.out.println(dto1.getEmail());
        System.out.println(dto1.getPasssword());
        System.out.println(dto1.getUserName());




       LoginDto dto = new LoginDto(nameText,passwordText);

       try {
            boolean checked =  loginBO.checkCredentianl(dto);
            if (checked){
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Main_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Order Form");
        stage.centerOnScreen();


          try {
                Mail mail = new Mail();
                mail.setMsg("Dear "+name2+" "+name+" "+"emplloyee Login into Ashen Enterprise management System at"+"   "+lblTime.getText());


               mail.setTo(hiru);
                mail.setSubject("Ashen Enterprise Management System Login");

                Thread thread = new Thread(mail);
                thread.start();
                new SystemAlert(Alert.AlertType.INFORMATION, "Success", "Mail sent!", ButtonType.OK).show();
            }catch (Exception e){
                new SystemAlert(Alert.AlertType.ERROR, "Mail ", e.getMessage(), ButtonType.OK).show();
            }



           } else {
                txtPassword.setStyle("-fx-border-color: #ff004f;");
                txtUserName.setStyle("-fx-border-color: #ff004f;");
                new SystemAlert(Alert.AlertType.WARNING, "Error", "User Name or password is wrong!", ButtonType.OK).show();

            }
        }catch (SQLException e){

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
      }
    }
    public void welcomeVoice(){
        new SoundsAssits().welcome();
    }



    public void showPasswordOnAction(KeyEvent keyEvent) {
      //  Password = txtPassword.getText();
      //  txtShowPassword.setText(Password);
    }

    public void HidePasswordOnAction(KeyEvent keyEvent) {
      //  Password = txtShowPassword.getText();
       // txtPassword.setText(Password);
    }

    public void openOnAction(MouseEvent mouseEvent) {
       // txtShowPassword.setVisible(true);
       // lblOpenEye.setVisible(true);
        //lblClose.setVisible(false);
     //   txtPassword.setVisible(false);
    }

    public void colseOnAction(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(false);
        lblOpenEye.setVisible(false);
        lblClose.setVisible(true);
        txtPassword.setVisible(true);
    }

    public void btnUserNameClearOnAction(MouseEvent mouseEvent) {
        txtUserName.setStyle("");

        txtUserName.clear();
    }

    public void btnUserPasswordClearOnAction(MouseEvent mouseEvent) {
        txtPassword.setStyle("");
        txtPassword.clear();
    }
}
