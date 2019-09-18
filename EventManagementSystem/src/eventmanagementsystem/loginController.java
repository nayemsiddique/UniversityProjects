/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventmanagementsystem;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author nayem
 */
public class loginController implements Initializable {
    

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    Connection connection;
    Statement statement;
    ResultSet result;
    String url;
    @FXML
    private Pane loginpane;
    @FXML
    private Pane createAccPane;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField passField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox<String> genderField;
    @FXML
    private TextField pinField;
    @FXML
    private DatePicker dateOfBirthField;
    private ObservableList<String> gender;
    @FXML
    private TextField UserNameField;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender=FXCollections.observableArrayList();
        gender.add("male");
        gender.add("female");
        gender.add("other");
        genderField.setItems(gender); 
        loginpane.setVisible(true);
        createAccPane.setVisible(false);
        try {
            connection=DriverManager.getConnection("jdbc:mysql://localhost/test?user=nayem&password=12345");
            statement=connection.createStatement();
            System.out.println("okey");
            
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) {
       

            
          
                
                String user=UserNameField.getText().trim();
                String pass=passwordField.getText();
                url="select * from user where binary username='"+user+"' and binary password=md5('"+pass+"')";
                //System.out.println(url);
                try {
                    result=statement.executeQuery(url);
                } catch (SQLException ex) {
                    Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if(result.next()){
                       // System.out.println("yeeesssssss");
                        Image img= new Image("/ok.png");
                        Notifications buildNotification= Notifications.create()
                                //.title("Notification")
                                .text("Login Successful")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(2))
                                .position(Pos.TOP_RIGHT);
                                buildNotification.show();
                                
                                
                                
                        if(user.equals("admin")){
                            adminController.setT(connection);
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
                            
                            try {
                                Parent root1 = (Parent) fxmlLoader.load();
                                Stage stage = EventManagementSystem.getMainStage();
                            stage.setScene(new Scene(root1));
                            stage.setTitle("Dashboard");
                            stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                            
                        }
                        else{
                            userController.setT(connection);
                            userController.setusr(UserNameField.getText().trim());
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user.fxml"));
                           // root1;
                            try {
                                Parent  root1 = (Parent) fxmlLoader.load();
                            Stage stage = EventManagementSystem.getMainStage();
                            stage.setScene(new Scene(root1));
                            stage.setTitle("Dashboard");
                            stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                        }
                      
                        
                    } 
               else{
                            Alert a=new Alert(Alert.AlertType.WARNING);
                            a.setTitle("Wrong Password");
                            a.setContentText("Invalid Username Or password !");
                            a.setHeaderText(null);
                            
                            a.showAndWait();
                        } 
                
                } catch (SQLException ex) {
                    Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                }
    
    

    

            }

    @FXML
    private void handleForgetPasswordAction(MouseEvent event) {
    }

    @FXML
    private void handleCreateAnAccountAction(ActionEvent event) {
        loginpane.setVisible(false);
        createAccPane.setVisible(true);
        firstNameField.clear();
        lastNameField.clear();
        userNameField.clear();
        passField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
        //genderField.
        dateOfBirthField.getEditor().setText("");
        
        
    }

    @FXML
    private void handleCreateAction(ActionEvent event) {
        if(Check()){
            if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || userNameField.getText().isEmpty() || 
                    passField.getText().isEmpty() || emailField.getText().isEmpty() || addressField.getText().isEmpty() ||
                    genderField.getSelectionModel().isEmpty() || pinField.getText().isEmpty() ||dateOfBirthField.getValue()==null){
                
                Alert a=new Alert(Alert.AlertType.WARNING);
                a.setTitle("Wrong");
                a.setContentText("Field should not be empty..");
                a.setHeaderText(null);
                
                a.showAndWait();
            }
            else{
        try {
            
            String fname=firstNameField.getText().trim();
            String lname=lastNameField.getText().trim();
            String username=userNameField.getText().trim();
            String pass=passField.getText();
            String phone=phoneField.getText().trim();
            String email=emailField.getText().trim();
            String add=addressField.getText().trim();
            String gen=genderField.getSelectionModel().getSelectedItem();
            String pinc=pinField.getText().trim();
            LocalDate Date=dateOfBirthField.getValue();
            url="insert into user values('"+username+"',md5('"+pass+"'))";
            statement.executeUpdate(url);
            //System.out.println(url);
            url="insert into user_details values('"+username+"','"+fname+"','"+lname+"','"+phone+"','"+email+"','"+add+"','"+gen+"','"+pinc+"','"+Date+"')";
            statement.executeUpdate(url);
             loginpane.setVisible(true);
        createAccPane.setVisible(false);
        
                        Image img= new Image("/ok.png");
                        Notifications buildNotification= Notifications.create()
                                //.title("Notification")
                                .text("Account Created")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(2))
                                .position(Pos.TOP_RIGHT);
                                buildNotification.show();
            
        } catch (SQLException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        } 
        }
        
    }

    @FXML
    private void handleCheckUsernameAction(ActionEvent event) {
        Check();
        
    }
    private boolean Check(){
            
        try {
            url="select * from user where username='"+userNameField.getText().trim()+"';";
            result=statement.executeQuery(url);
            if(result.next()){
                
                Alert a=new Alert(Alert.AlertType.WARNING);
                a.setTitle("Wrong");
                a.setContentText("username already taken");
                a.setHeaderText(null);
                
                a.showAndWait();
                return false;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @FXML
    private void handleMouseClickAction(MouseEvent event) {
    }

    @FXML
    private void handleCancleAction(ActionEvent event) {
        loginpane.setVisible(true);
        createAccPane.setVisible(false);
        firstNameField.clear();
        
        lastNameField.clear();
        userNameField.clear();
        passField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();
        genderField.getEditor().setText("");
        pinField.clear();
        dateOfBirthField.getEditor().setText("");
    }
        
    }



   /*f(r_eventNameField.getText().trim().equals("") || r_eventNameField.getText().isEmpty() ||
                r_eventDatePicker.getValue().equals(null) || r_eventDatePicker.getValue().isEqual(null) ||
                r_eventFee.getText().trim().equals("") || r_eventFee.getText().trim().isEmpty() ||
                r_QuotaFee.getText().trim().equals("") || r_QuotaFee.getText().trim().isEmpty() || r_subEventFee.getText().trim().equals("")||
                r_subEventFee.getText().trim().isEmpty()|| r_SubEventTime.getText().trim().equals("") ||
                r_SubEventTime.getText().trim().isEmpty() || r_totalFeeee.getText().equals("") || r_totalFeeee.getText().isEmpty())*/




