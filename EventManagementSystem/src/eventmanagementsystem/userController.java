/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sun.awt.X11.InfoWindow;

/**
 * FXML Controller class
 *
 * @author nayem
 */
public class userController implements Initializable {
    
    public static void setT(Connection aT) {
        connection = aT;
    }
    public static void setusr(String user){
        username=user;
    }

    @FXML
    private Button dashboardButton;
    
    @FXML
    private ListView<?> viewEventList;
    private static Connection connection;
    @FXML
    private Pane regEventPane;
    @FXML
    private Pane viewRegEventPane;
    @FXML
    private Pane userDashPane;
    @FXML
    private Button regButton;
    @FXML
    private Button viewRegEventButton;
    @FXML
    private TextField r_eventNameField;
    @FXML
    private DatePicker r_eventDatePicker;
    @FXML
    private TextField r_eventFee;
    @FXML
    private ComboBox<info> r_subEventComboBox;
    @FXML
    private TextField r_QuotaFee;
    @FXML
    private TextField r_subEventFee;
    @FXML
    private TextField r_SubEventTime;
    @FXML
    private TextField r_totalFee;
    Statement statement;
    String url;
    ResultSet result;
    private ObservableList eventList;
     info info;
    
    ResultSet result_quota;
    ResultSet result_subEvent;
    Statement s2;
    Statement s3;
    boolean flag=true;
    @FXML
    private ListView<reg_events> viewRegEventList;
    @FXML
    private ComboBox<?> r_quotaTypeComboBox;
    private int total=0;
    String sub_name;
    int fee;
    private static String username;
    String quotaType;
    LocalDate date;
    reg_events r;
    private ObservableList regEventList;
    @FXML
    private TextField r_eventName;
    @FXML
    private DatePicker r_eventDate;
    @FXML
    private DatePicker r_endRegDate;
    @FXML
    private ComboBox<?> r_subCombo;
    @FXML
    private TextField r_quota;
    @FXML
    private TextField r_subEventTime;
    @FXML
    private Label totalEventLabel;
    @FXML
    private Label TotalActiveEventLable;
    @FXML
    private Label regEventLabel;
    @FXML
    private Label userIDLabel;
    private ObservableList<PieChart.Data>pieList;
    @FXML
    private PieChart pie;
    int t_e;
    int a_e;
    int r_e;
    @FXML
    private TextField p_subEventFee;
    @FXML
    private TextField r_totalFeeee;
   boolean flag2=false;
    @FXML
    private Pane profilePane;
    @FXML
    private Pane changePassPane;
    @FXML
    private Pane changePinPane;
    @FXML
    private TextField pro_firstName;
    @FXML
    private TextField pro_lastName;
    @FXML
    private TextField pro_userName;
    @FXML
    private TextField pro_number;
    @FXML
    private TextField pro_email;
    @FXML
    private TextField pro_gender;
    @FXML
    private TextField pro_address;
    @FXML
    private DatePicker pro_dOfB;
    @FXML
    private TextField cp_currentPass;
    @FXML
    private TextField cp_newPassword;
    @FXML
    private TextField cp_ConfirmPassword;
    @FXML
    private TextField c_curPin;
    @FXML
    private TextField c_newPin;
    @FXML
    private TextField c_conPin;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            userIDLabel.setText(username.toUpperCase());
            regEventList=FXCollections.observableArrayList();
            eventList=FXCollections.observableArrayList();
        try {
            viewRegEventPane.setVisible(false);
            userDashPane.setVisible(true);
            regEventPane.setVisible(false);
            profilePane.setVisible(false);
            changePinPane.setVisible(false);
            changePassPane.setVisible(false);
            
            statement=connection.createStatement();
            s2=connection.createStatement();
            s3=connection.createStatement();
            updateDash();
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource()==dashboardButton){
            clear();
            viewRegEventPane.setVisible(false);
        userDashPane.setVisible(true);
        regEventPane.setVisible(false);
        changePinPane.setVisible(false);
            changePassPane.setVisible(false);
            profilePane.setVisible(false);
        updateDash();
            
        }
        if(event.getSource()==regButton){
            clear();
        viewRegEventPane.setVisible(false);
        userDashPane.setVisible(false);
        regEventPane.setVisible(true);
        changePinPane.setVisible(false);
            changePassPane.setVisible(false);
            profilePane.setVisible(false);
        loadData();
            
        }
        if(event.getSource()==viewRegEventButton){
            clear();
           viewRegEventPane.setVisible(true);
        userDashPane.setVisible(false);
        regEventPane.setVisible(false); 
        changePinPane.setVisible(false);
            changePassPane.setVisible(false);
            profilePane.setVisible(false);
        loadRegData();
        }
    }
    




    @FXML
    private void handleQuotaTypeSelectAction(ActionEvent event) {
        String name=r_quotaTypeComboBox.getSelectionModel().getSelectedItem()+"";
        flag2=true;
        quotaType=name;
        if(!name.equals("null")){
          
           
            try {
                url="select * from quota where quota_type='"+name+"' and event_code='"+info.getEvent_code()+"'";
                result=statement.executeQuery(url);
                if(result.next()){
                    int fee=result.getInt("quota_fee");
                    r_QuotaFee.setText(fee+"");
                    
                }
                else{
                    r_QuotaFee.setText(info.getEvent_fee()+"");
                }
            } catch (SQLException ex) {
                Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
            }
            } 
    }
    

    @FXML
    private void handleRegisterAction(ActionEvent event) {
        if(!flag2){
            Alert a=new Alert(Alert.AlertType.ERROR);
                a.setTitle("Wrong");
                a.setContentText("Field Should not be Empty !");
                a.setHeaderText(null);
                
                a.showAndWait();
        }
        else{
            
        
        try {
            url="select sum(sub_event_fee) from sub_event_reg_info where event_code='"+info.getEvent_code()+"' and username='"+username+"';";
            result=statement.executeQuery(url);
           if(result.next()){
               int sum=result.getInt(1);
               sum+=Integer.parseInt(r_QuotaFee.getText());
               url="select * from event_reg_info where username='"+username+"' and event_code='"+info.getEvent_code()+"';";
               result=statement.executeQuery(url);
               if(result.next()){
                   Alert a=new Alert(Alert.AlertType.ERROR);
                a.setTitle("Wrong");
                a.setContentText("you have already registered!");
                a.setHeaderText(null);
                
                a.showAndWait();
                   
               }
               else{
                   
               url="insert into event_reg_info values('"+username+"','"+info.getEvent_code()+"','"+LocalDate.now()+"','"+quotaType+"',"+sum+")";
               statement.executeUpdate(url);
               Image img= new Image("/ok.png");
                        Notifications buildNotification= Notifications.create()
                                //.title("Notification")
                                .text("Added to Database...")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(2))
                                .position(Pos.TOP_RIGHT);
                                buildNotification.show();
                                clear();
               //int num=0;
               }
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }

    
     private void loadData(){
        
        eventList.clear();
        
        try {
            url="select * from event_info;";
            result=statement.executeQuery(url);
            //System.out.println("yes");
            while(result.next()){
                viewEventList.setItems(eventList);
               
                String event_name=result.getString("name");
                String event_code=result.getString("event_code");
                Date event_date=result.getDate("event_date");
                Date end_reg=result.getDate("end_reg");
                Date start_reg=result.getDate("start_reg");
                int event_fee=result.getInt("event_fee");
                 
                
                //System.out.println(event_code);
                info=new info(event_code,event_name,LocalDate.parse(event_date+""),event_fee,LocalDate.parse(start_reg+""),LocalDate.parse(end_reg+""));
                eventList.add(info);
                url="select * from quota where event_code='"+event_code+"'";
                info.getQuota_type().add("other");
                result_quota=s2.executeQuery(url);
                while(result_quota.next()){
                    info.getQuota_type().add(result_quota.getString("quota_type"));
                }
                url="select * from sub_event_info where event_code='"+event_code+"'";
                result_subEvent=s3.executeQuery(url);
                while(result_subEvent.next()){
                    info.getSub_event().add(result_subEvent.getString("sub_event_name"));
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

    @FXML
    private void handleSubEventRegAction(ActionEvent event) {
        sub_name=r_subEventComboBox.getSelectionModel().getSelectedItem()+"";
        String name=sub_name;
        
        if(!name.equals("null")){
           
           
            try {
                url="select * from sub_event_info where event_code='"+info.getEvent_code()+"' and sub_event_name='"+name+"';";
                result=statement.executeQuery(url);
                if(result.next()){
                     fee=result.getInt("sub_event_fee");
                    Time start=result.getTime("start_time");
                    Time End =result.getTime("end_time");
                    String nn=start+" to "+ End;
                    r_subEventFee.setText(fee+"");
                    r_SubEventTime.setText(nn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }


    @FXML
    private void handleRegEventMouseClickAction(MouseEvent event) {
        
      info = (eventmanagementsystem.info) viewEventList.getSelectionModel().getSelectedItem();
      //System.out.println(info);
      flag=true;
      total=100;
        if(info!=null){
            //System.out.println(info.getSub_event());
            r_eventNameField.setText(info.getEvent_name());
            r_eventDatePicker.setValue(info.getEvent_date());
            r_eventFee.setText(info.getEvent_fee()+"");
            
            r_subEventComboBox.setItems(info.getSub_event());
            r_quotaTypeComboBox.setItems(info.getQuota_type());
        }
           
        
    }

    @FXML
    private void handleRegSubEventButtonAction(ActionEvent event) {
        if(!flag2){
            Alert a=new Alert(Alert.AlertType.ERROR);
                a.setTitle("Wrong");
                a.setContentText("Field Should not be Empty !");
                a.setHeaderText(null);
                
                a.showAndWait();
        }else{
        try {
            url="select * from sub_event_reg_info where sub_event_name='"+sub_name+"' and event_code='"+info.getEvent_code()+"' and username='"+username+"';";
            //System.out.println(url);
            result=statement.executeQuery(url);
            if(result.next()){
                Alert a=new Alert(Alert.AlertType.WARNING);
                a.setTitle("Wrong");
                a.setContentText("Already Registered !");
                a.setHeaderText(null);
                
                a.showAndWait();
            }
            else{
            if(flag){
                //System.out.println("ikkkk");
                total=total+Integer.parseInt(r_QuotaFee.getText())+Integer.parseInt(r_subEventFee.getText());
                r_totalFeeee.setText(total+"");
                flag=false;
            }
            else{
                total+=Integer.parseInt(r_subEventFee.getText());
                r_totalFeeee.setText(total+"");
            }
            url="insert into sub_event_reg_info values('"+sub_name+"',"+fee+",'"+info.getEvent_code()+"','"+username+"');";
            statement.executeUpdate(url);
            
            Image img= new Image("/ok.png");
                        Notifications buildNotification= Notifications.create()
                                //.title("Notification")
                                .text("Sub-Event Added..")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(2))
                                .position(Pos.TOP_RIGHT);
                                buildNotification.show();
               //int num=0;
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
    }
    private void loadRegData(){
        regEventList.clear();
        try {
            url="select event_info.name,event_reg_info.event_fee,event_reg_info.quota_type,event_info.event_code,event_info.event_date,event_info.end_reg,event_info.isactive from event_info inner join "
                    + "event_reg_info on event_info.event_code =event_reg_info.event_code where event_reg_info.username='"+username+"';";
            result=statement.executeQuery(url);
            while(result.next()){
                if(result.getBoolean("isactive")){
                    String eventName=result.getString("name");
                    int eventFee=result.getInt("event_fee");
                    String eventCode=result.getString("event_code");
                    Date eventDate=result.getDate("event_date");
                    Date endReg=result.getDate("end_reg");
                    String quota_type=result.getString("quota_type");
                    r=new reg_events(eventCode,eventName,LocalDate.parse(eventDate+""),eventFee,LocalDate.parse(endReg+""),quota_type);
                    regEventList.add(r);
                    url=" select * from sub_event_reg_info where event_code='"+eventCode+"' and username='"+username+"';";
                    result_subEvent=s2.executeQuery(url);
                    while(result_subEvent.next()){
                        String sub_event=result_subEvent.getString("sub_event_name");
                        r.getSub_event().add(sub_event);
                        
                    }
                    viewRegEventList.setItems(regEventList);
                    
                    
                    
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleRegisterdEventListClickAction(MouseEvent event) {
        r=viewRegEventList.getSelectionModel().getSelectedItem();
        if(r!=null){
            r_eventName.setText(r.getEvent_name());
            r_quota.setText(r.getQuota_type());
            r_eventDate.setValue(r.getEvent_date());
            r_endRegDate.setValue(r.getReg_end());
            r_subCombo.setItems(r.getSub_event());
            r_totalFee.setText(r.getEvent_fee()+"");
        }
        
    }

    @FXML
    private void handleRegComboAction(ActionEvent event) {
        String subEvent=r_subCombo.getSelectionModel().getSelectedItem()+"";
        if(!subEvent.equals("") && !subEvent.isEmpty()){
            url="select * from sub_event_info where event_code='"+r.getEvent_code()+"' and sub_event_name='"+subEvent+"'";
            try {
                result=statement.executeQuery(url);
                if(result.next()){
                    int feee=result.getInt("sub_event_fee");
                    Time start=result.getTime("start_time");
                    Time end=result.getTime("end_time");
                    String sToe=start+" to "+end;
                    p_subEventFee.setText(feee+"");
                    r_subEventTime.setText(sToe);
                    
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        else{
            
        }
    }

    private void updateDash() {
        regEventLabel.setText(username.toUpperCase());
        
        try {
            url="select count(name) from event_info";
            result=statement.executeQuery(url);
            if(result.next()){
                t_e=result.getInt(1);
                if(result.getInt(1)<=9){
                    String nn="0"+result.getInt(1);
                    totalEventLabel.setText(nn);
                }
                else{
                    totalEventLabel.setText(result.getInt(1)+"");
                }
                
            }
            url="select count(name) from event_info where isactive='1'";
            result=statement.executeQuery(url);
            if(result.next()){
                a_e=result.getInt(1);
                
                if(result.getInt(1)<=9){
                    String nn="0"+result.getInt(1);
                    TotalActiveEventLable.setText(nn);
                }
                else{
                    TotalActiveEventLable.setText(result.getInt(1)+"");
                } 
                
                
                
            }
            url="select count(username) from event_reg_info where username='"+username+"';";
            result=statement.executeQuery(url);
            if(result.next()){
                r_e=result.getInt(1);
                if(result.getInt(1)<=9){
                    String nn="0"+result.getInt(1);
                    regEventLabel.setText(nn);
                }
                else{
                    regEventLabel.setText(result.getInt(1)+"");
                } 
              
            }
            pieList=FXCollections.observableArrayList(new PieChart.Data("TOTAL EVENT", t_e),
                new PieChart.Data("ACTIVE EVENT", a_e),
                new PieChart.Data("REGISTERED EVENT", r_e));
                pie.setData(pieList);
            
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleUserCancleAction(ActionEvent event) {
        clear();
        regEventPane.setVisible(false);
        userDashPane.setVisible(true);
    }
    
    

    @FXML
    private void handleClearAction(ActionEvent event) {
        clear();
    }
    
    private boolean ch(){
        if(r_eventNameField.getText().trim().equals("") || r_eventNameField.getText().isEmpty() ||
                r_eventDatePicker.getValue().equals("") || r_eventDatePicker.getValue().isEqual(null) ||
                r_eventFee.getText().trim().equals("") || r_eventFee.getText().trim().isEmpty() ||
                r_QuotaFee.getText().trim().equals("") || r_QuotaFee.getText().trim().isEmpty() || r_subEventFee.getText().trim().equals("")||
                r_subEventFee.getText().trim().isEmpty()|| r_SubEventTime.getText().trim().equals("") ||
                r_SubEventTime.getText().trim().isEmpty() || r_totalFeeee.getText().equals("") || r_totalFeeee.getText().isEmpty() ||
                        r_quotaTypeComboBox.getSelectionModel().getSelectedItem().equals("") || 
                r_subEventComboBox.getSelectionModel().getSelectedItem().equals("")){
            return false;
        }
        else return true;
    }

    @FXML
    private void handleuLogoutAction(MouseEvent event) {
         Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Logout");
                a.setContentText("Are You Sure You Want to Log Out?");
                a.setHeaderText(null);
                Optional<ButtonType> r=a.showAndWait();
                if(r.get()!=ButtonType.YES){
             
             try {
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
                 Parent root1 = (Parent) fxmlLoader.load();
                 Stage stage = EventManagementSystem.getMainStage();
                 stage.setScene(new Scene(root1));
                 stage.setTitle("Result");
                 stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
    }
            /* profile section*/
    @FXML
    private void handleProfileButtonAction(ActionEvent event) {
       
        try {
            viewRegEventPane.setVisible(false);
            userDashPane.setVisible(false);
            regEventPane.setVisible(false);
            changePinPane.setVisible(false);
            changePassPane.setVisible(false);
            profilePane.setVisible(true);
            url="select * from user_details where username='"+username+"'";
            result=statement.executeQuery(url);
            if(result.next()){
                String user=result.getString("username");
                String fname=result.getString("first_name");
                String lname=result.getString("last_name");
                String phone=result.getString("phone");
                String email=result.getString("email");
                String gender=result.getString("gender");
                String address=result.getString("address");
                Date dofb=result.getDate("dofb");
                pro_firstName.setText(fname);
                pro_lastName.setText(lname);
                pro_userName.setText(user);
                pro_number.setText(phone);
                pro_email.setText(email);
                pro_gender.setText(gender);
                pro_address.setText(address);
                pro_dOfB.setValue(LocalDate.parse(dofb+""));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }

   
}

    @FXML
    private void handleChangePasswordAction(ActionEvent event) {
        changePinPane.setVisible(false);
            changePassPane.setVisible(true);
            profilePane.setVisible(false);
            
    }

    @FXML
    private void handleChangePinAction(ActionEvent event) {
        changePinPane.setVisible(true);
            changePassPane.setVisible(false);
            profilePane.setVisible(false);
    }

    @FXML
    private void handleProfieUpdateAction(ActionEvent event) {
        
        
        try {
            String firstName=pro_firstName.getText().trim();
            String lstname=pro_lastName.getText().trim();
            
            String num=pro_number.getText().trim();
            String email=pro_email.getText().trim();
            
            String add=pro_address.getText().trim();
            LocalDate dofb=pro_dOfB.getValue();
            url="update user_details set email='"+email+"',first_name='"+firstName+"',last_name='"+lstname+"',"
                    + "phone='"+num+"',address='"+add+"',dofb='"+dofb+"' where username='"+username+"';";
            statement.executeUpdate(url);
                         Image img= new Image("/ok.png");
                        Notifications buildNotification= Notifications.create()
                                //.title("Notification")
                                .text("Profile Updated")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(2))
                                .position(Pos.TOP_RIGHT);
                                buildNotification.show();
           // handleProfileButtonAction();
            //System.out.println(url);
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void handleChangepAction(ActionEvent event) {
        String curPass=cp_currentPass.getText();
            String np=cp_newPassword.getText();
            String cnp=cp_ConfirmPassword.getText();
            if(np.equals(cnp) && !np.isEmpty() && !cnp.isEmpty()){
            try {
                url="update user set password=md5('"+np+"') where username='"+username+"' and password=md5('"+curPass+"')";
                statement.executeUpdate(url);
                
                Image img= new Image("/ok.png");
                        Notifications buildNotification= Notifications.create()
                                //.title("Notification")
                                .text("Password Changed")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(2))
                                .position(Pos.TOP_RIGHT);
                                buildNotification.show();
                                
            changePinPane.setVisible(false);
            changePassPane.setVisible(false);
            profilePane.setVisible(true);
            clear();
                
            } catch (SQLException ex) {
                Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
            }else{
                Alert a=new Alert(Alert.AlertType.WARNING);
                a.setTitle("Wrong");
                a.setContentText("Your password and confirmation password do not matched..");
                a.setHeaderText(null);
                
                a.showAndWait();
            }
    }

    @FXML
    private void handleChangePassCancleAction(ActionEvent event) {
        
        changePinPane.setVisible(false);
            changePassPane.setVisible(false);
            profilePane.setVisible(true);
            clear();
        
    }

    @FXML
    private void handleChangePinChangeAction(ActionEvent event) {
        String pin=c_curPin.getText().trim();
        String nwPin=c_newPin.getText().trim();
        String comPin=c_conPin.getText().trim();
        if(nwPin.equals(comPin) && !nwPin.isEmpty() && !comPin.isEmpty()){
            try {
                url="update user_details set pin='"+nwPin+"' where username='"+username+"' and pin='"+pin+"'";
                statement.executeUpdate(url);
                
                Image img= new Image("/ok.png");
                        Notifications buildNotification= Notifications.create()
                                //.title("Notification")
                                .text("Pin Changed")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(2))
                                .position(Pos.TOP_RIGHT);
                                buildNotification.show();
                                
            changePinPane.setVisible(false);
            changePassPane.setVisible(false);
            profilePane.setVisible(true);
            clear();
                
            } catch (SQLException ex) {
                Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
                Alert a=new Alert(Alert.AlertType.WARNING);
                a.setTitle("Wrong");
                a.setContentText("Your Pin and confirmation pin do not matched..");
                a.setHeaderText(null);
                
                a.showAndWait();
            }
    }

    @FXML
    private void handleCancleChangePinAction(ActionEvent event) {
        
        changePinPane.setVisible(false);
            changePassPane.setVisible(false);
            profilePane.setVisible(true);
        
    }
    void clear(){
        r_eventNameField.clear();
        r_eventDatePicker.getEditor().setText("");
        r_eventFee.clear();
        //String value="daete";
        //r_quotaTypeComboBox.;
        r_QuotaFee.clear();
        r_subEventFee.clear();
        r_SubEventTime.clear();
        r_totalFeeee.clear();
        c_curPin.clear();
        c_newPin.clear();
        c_conPin.clear();
        c_curPin.clear();
        c_newPin.clear();
        c_conPin.clear();
        r_eventNameField.clear();
        r_eventName.clear();
        r_quota.clear();
        r_endRegDate.getEditor().setText("");
        r_eventDate.getEditor().setText("");
        r_subCombo.getEditor().setText("");
        p_subEventFee.clear();
        r_subEventTime.clear();
        r_totalFee.clear();
        
    }

    
    }




