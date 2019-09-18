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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author nayem
 */
public class adminController implements Initializable {
    public static void setT(Connection aT) {
        connection = aT;
    }
    

    private ObservableList<PieChart.Data>pieList;
    @FXML
    private Pane adminDashPane;
    @FXML
    private Pane addEventPane;
    @FXML
    private Pane addSubEventPane;
    @FXML
    private Pane AddQuotaPane;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button newEventButton;
    @FXML
    private Pane viewEventPane;
    @FXML
    private Pane detalisPane;
    @FXML
    private Button viewEventButton;
    private static Connection connection;
    @FXML
    private TextField A_eventNameField;
    @FXML
    private DatePicker A_endRegDatePicker;
    @FXML
    private DatePicker A_startRegDatePicker;
    @FXML
    private DatePicker A_eventDatePicker;
    @FXML
    private TextField A_eventFee;
    @FXML
    private TextField A_event_code;
    String eventName;
    LocalDate eventDate;
    LocalDate startReg;
    LocalDate endReg;
    int eventFee;
    String eventCode;
    String sub_eventName;
    int sub_eventFee;
    String startTime;
    String endTime;
    String quota_type;
    int quota_fee;
    String url;
    boolean isactive=true;
    Statement statement;
    ResultSet result;
    @FXML
    private TextField A_subEventName;
    @FXML
    private TextField A_subEventFee;
    @FXML
    private TextField A_subEventStartTime;
    @FXML
    private TextField A_subEventEndTime;
    
    private ObservableList quotaList;
    @FXML
    private TextField A_quotaType;
    @FXML
    private TextField A_quotaFee;
    @FXML
    private ListView<?> quotaView;
    @FXML
    private TextField v_eventNameField;
    @FXML
    private DatePicker v_startRegDate;
    @FXML
    private DatePicker v_eventDatePicker;
    @FXML
    private TextField v_eventFeeField;
    @FXML
    private ListView<info> viewEventList;
    @FXML
    private DatePicker v_endRegDate;
    @FXML
    private ComboBox<info> v_subEventComboBox;
    @FXML
    private TextField v_subEventFee;
    @FXML
    private TextField v_subEventTime;
    @FXML
    private ComboBox<info> v_quotaTypeComboBox;
    @FXML
    private TextField v_quotaFeeField;
    
    
    private ObservableList<info> eventList;
     
    
    
    ResultSet result_quota;
    ResultSet result_subEvent;
    Statement s2;
    Statement s3;
    boolean flag=true;
            
    info info;
    @FXML
    private ListView<info> all_eventLIst;
    @FXML
    private ListView<String> all_quotList;
    @FXML
    private ListView<String> all_subEventList;
    @FXML
    private Label all_totalFee;
    @FXML
    private Button allDetailsButton;
    private ObservableList quota;
    private ObservableList sub;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label totaluserField;
    @FXML
    private Label totalEventField;
    @FXML
    private Label activeEventField;
    @FXML
    private PieChart pie;
    @FXML
    private Label totalquota;
    @FXML
    private Label subTotal;
    private boolean flaggg=false;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventList=FXCollections.observableArrayList();
        quotaList=FXCollections.observableArrayList();
        quota=FXCollections.observableArrayList();
        sub=FXCollections.observableArrayList();
        try {
           // dashboardButton.setUnderline(true);
            adminDashPane.setVisible(true);
            addEventPane.setVisible(false);
            addSubEventPane.setVisible(false);
            AddQuotaPane.setVisible(false);
            viewEventPane.setVisible(false);
            detalisPane.setVisible(false);
            statement=connection.createStatement();
            s2=connection.createStatement();
            s3=connection.createStatement();
            loadDash();
        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
       
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource()==dashboardButton && flag){
            //dashboardButton.setUnderline(true);
            adminDashPane.setVisible(true);
        addEventPane.setVisible(false);
        addSubEventPane.setVisible(false);
        AddQuotaPane.setVisible(false);
         viewEventPane.setVisible(false);
         detalisPane.setVisible(false);
         loadDash();
            
        }
        if(event.getSource()==newEventButton && flag){
        adminDashPane.setVisible(false);
        addEventPane.setVisible(true);
        addSubEventPane.setVisible(false);
        AddQuotaPane.setVisible(false);
         viewEventPane.setVisible(false);
         detalisPane.setVisible(false);
            
        }
        if(event.getSource()==viewEventButton && flag){
        adminDashPane.setVisible(false);
        addEventPane.setVisible(false);
        addSubEventPane.setVisible(false);
        AddQuotaPane.setVisible(false);
        viewEventPane.setVisible(true);
        detalisPane.setVisible(false);
        eventList.clear();
        loadData();
            
        }
        if(event.getSource()==allDetailsButton && flag){
           // System.out.println("yssssssaa");
            adminDashPane.setVisible(false);
        addEventPane.setVisible(false);
        addSubEventPane.setVisible(false);
        AddQuotaPane.setVisible(false);
        viewEventPane.setVisible(false);
        detalisPane.setVisible(true);
       eventList.clear();
       loadData();
            
        }
    }

    @FXML
    private void handleAddEventNextAction(ActionEvent event) {
           
            if(A_eventNameField.getText().isEmpty() || A_eventDatePicker.getValue()==null || A_startRegDatePicker.getValue()==null
                    || A_endRegDatePicker.getValue()==null || A_eventFee.getText().isEmpty() || A_event_code.getText().isEmpty()) {
                  Alert a=new Alert(Alert.AlertType.WARNING);
                a.setTitle("Wrong");
                a.setContentText("Field should not be empty..");
                a.setHeaderText(null);
                
                a.showAndWait();
            }
            else{
            
        try {
          
            
            
             eventName=A_eventNameField.getText().trim();
            eventDate=A_eventDatePicker.getValue();
            startReg=A_startRegDatePicker.getValue();
            endReg=A_endRegDatePicker.getValue();
            eventFee=Integer.parseInt(A_eventFee.getText());
            eventCode=A_event_code.getText().trim();
            
            url="insert into event_info values('"+eventName+"',"
                    + "'"+eventDate+"','"+startReg+"','"+endReg+"',"+eventFee+",'"+eventCode+"',"+isactive+");";
              flag=false;
            statement.executeUpdate(url);
            adminDashPane.setVisible(false);
            addEventPane.setVisible(false);
            addSubEventPane.setVisible(true);                                       /**  add new sub event **/
            AddQuotaPane.setVisible(false);
            viewEventPane.setVisible(false);
            //statement.executeUpdate(url);
        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
         
         
    }

    @FXML
    private void handleAddSubEventNextAction(ActionEvent event) {
        adminDashPane.setVisible(false);
        addEventPane.setVisible(false);
        addSubEventPane.setVisible(false);                                           /**  add new sub event **/
        AddQuotaPane.setVisible(true);
         viewEventPane.setVisible(false);
         
         
    }

    @FXML
    private void handleNewEventFinishAction(ActionEvent event) {
        if(flaggg){
            
        
        flag=true;
        adminDashPane.setVisible(true);
        addEventPane.setVisible(false);
        addSubEventPane.setVisible(false);
        AddQuotaPane.setVisible(false);
         viewEventPane.setVisible(false);
          quotaList.clear();
        }
        else{
             Alert a=new Alert(Alert.AlertType.WARNING);
                a.setTitle("Wrong");
                a.setContentText("At Least One Category Must Needed..");
                a.setHeaderText(null);
                
                a.showAndWait();
            
        }
    }

    @FXML
    private void handleSubEventAddAction(ActionEvent event) {
        try {
            sub_eventName=A_subEventName.getText().trim();
            sub_eventFee=Integer.parseInt(A_subEventFee.getText().trim());
            startTime=A_subEventStartTime.getText().trim();
            endTime=A_subEventEndTime.getText().trim();
            
            url="insert into sub_event_info values('"+eventCode+"','"+sub_eventName+"',"+sub_eventFee+",'"+startTime+":00','"+endTime+":00');";
            statement.executeUpdate(url);
            Image img= new Image("/ok.png");
                        Notifications buildNotification= Notifications.create()
                                //.title("Notification")
                                .text("Sub-Event Added!")
                                .graphic(new ImageView(img))
                                .hideAfter(Duration.seconds(2))
                                .position(Pos.TOP_RIGHT);
                                buildNotification.show();
            System.out.println(url);
            A_subEventName.clear();
            A_subEventFee.clear();
            A_subEventStartTime.clear();
            A_subEventEndTime.clear();
        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleAddQuotaAction(ActionEvent event) {
        if(A_quotaType.getText().isEmpty() || A_quotaFee.getText().isEmpty()){
            
                Alert a=new Alert(Alert.AlertType.ERROR);
                a.setTitle("Wrong");
                a.setContentText("Field Should Not be Empty !! ");
                a.setHeaderText(null);
                
                a.showAndWait();
        }else{
        try {
            quotaView.setItems(quotaList);
            quota_type=A_quotaType.getText().trim();
            quota_fee=Integer.parseInt(A_quotaFee.getText());
            quotaList.add(quota_type+"  $"+quota_fee);
            url="insert into quota values('"+quota_type+"',"+quota_fee+",'"+eventCode+"');";
            statement.executeUpdate(url);
            flaggg=true;
            A_quotaType.clear();
            A_quotaFee.clear();
        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void handleViewEventClickAction(MouseEvent event) {
        v_subEventFee.clear();
        v_subEventTime.clear();
        v_quotaFeeField.clear();
        
        info=viewEventList.getSelectionModel().getSelectedItem();
        if(info!=null){
            v_eventNameField.setText(info.getEvent_name());
            v_eventDatePicker.setValue(info.getEvent_date());
            v_eventFeeField.setText(info.getEvent_fee()+"");
            v_startRegDate.setValue(info.getReg_start());
            v_endRegDate.setValue(info.getReg_end());
            v_subEventComboBox.setItems(info.getSub_event());
            v_quotaTypeComboBox.setItems(info.getQuota_type());
        }
    }

    @FXML
    private void handleSubEventViewAction(ActionEvent event) {
        String name=v_subEventComboBox.getSelectionModel().getSelectedItem()+"";
        
        if(!name.equals("null")){
           
            try {
                url="select * from sub_event_info where event_code='"+info.getEvent_code()+"' and sub_event_name='"+name+"';";
                result=statement.executeQuery(url);
                if(result.next()){
                    int fee=result.getInt("sub_event_fee");
                    Time start=result.getTime("start_time");
                    Time End =result.getTime("end_time");
                    String nn=start+" to "+ End;
                    v_subEventFee.setText(fee+"");
                    v_subEventTime.setText(nn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        
        
    }
    
    
    private void loadData(){
        
        
        
        try {
            url="select * from event_info;";
            result=statement.executeQuery(url);
            //System.out.println("yes");
            while(result.next()){
                viewEventList.setItems(eventList);
                all_eventLIst.setItems(eventList);
               
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
    private void handleQuotaTypeSelectAction(ActionEvent event) {
        
        String name=v_quotaTypeComboBox.getSelectionModel().getSelectedItem()+"";
        if(!name.equals("null")){
          
            try {
                url="select * from quota where quota_type='"+name+"' and event_code='"+info.getEvent_code()+"'";
                result=statement.executeQuery(url);
                if(result.next()){
                    int fee=result.getInt("quota_fee");
                    v_quotaFeeField.setText(fee+"");
                    
                }
                else{
                    v_quotaFeeField.setText(info.getEvent_fee()+"");
                }
            } catch (SQLException ex) {
                Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
   
    
}

    @FXML
    private void allDetailsMouseClickAction(MouseEvent event) {
        try {
            quota.clear();
            sub.clear();
            info=all_eventLIst.getSelectionModel().getSelectedItem();
            all_quotList.setItems(quota);
            all_subEventList.setItems(sub);
             int sum=0;
            for(int i=0;i<info.getQuota_type().size();i++){
                    try {
                       
                        url="select count(quota_type) from event_reg_info where quota_type='"+info.getQuota_type().get(i)+"' and event_code='"+info.getEvent_code()+"'";
                        //url=""
                        result=statement.executeQuery(url);
                        if(result.next()){
                            int rnum=result.getInt(1);
                            url="select * from quota where quota_type='"+info.getQuota_type().get(i)+"' and event_code='"+info.getEvent_code()+"'";
                            result=statement.executeQuery(url);
                            if(result.next()){
                            int ramn=result.getInt("quota_fee");
                            
                                sum=sum+rnum*ramn;
                                quota.add(info.getQuota_type().get(i) + "   " +rnum+"      $"+rnum*ramn);
                            
                            }
                            
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                
                
            }
            totalquota.setText("$ " + sum);
            sum=0;
            for(int i=0;i<info.getSub_event().size();i++){
                try {
                    url="select count(username),sum(sub_event_fee) from sub_event_reg_info where event_code='"
                            +info.getEvent_code()+"' and sub_event_name='"+info.getSub_event().get(i)+"';";
                    result=statement.executeQuery(url);
                    if(result.next()){
                        int num=result.getInt(1);
                        int amm=result.getInt(2);
                       // System.out.println(num +" " +amm +"\n");
                        sum=sum+amm;
                        sub.add(info.getSub_event().get(i)+"  " +num+"  $   "+amm);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            subTotal.setText(" $  "+sum);
            url="select sum(event_fee) from event_reg_info where event_code='"+info.getEvent_code()+"';";            
            result=statement.executeQuery(url);
            if(result.next()){
                all_totalFee.setText(result.getInt(1)+"");
            }
                } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDash() {
         int a_u;
         int al_e = 0;
         int ac_e = 0;
        try {
            url="select count(username) from user;";
            result=statement.executeQuery(url);
            if(result.next()){
                 a_u=result.getInt(1);
                --a_u;
                if(a_u<=9){
                    totaluserField.setText("0"+a_u);
                }
                else totaluserField.setText("0"+a_u);
                url="select count(name) from event_info";
                result=statement.executeQuery(url);
                if(result.next()){
                    al_e=result.getInt(1);
                    if(result.getInt(1)<=9) totalEventField.setText("0"+al_e);
                    else totalEventField.setText(al_e+"");
                    
                }
                url="select count(name) from event_info where isactive='1'";
                result=statement.executeQuery(url);
                if(result.next()){
                    ac_e=result.getInt(1);
                    if(ac_e<=9) activeEventField.setText("0"+ac_e);
                    else activeEventField.setText(ac_e+"");
                    
                }
                pieList=FXCollections.observableArrayList(new PieChart.Data("TOTAL USER", a_u),
                new PieChart.Data("TOTAL EVENT", al_e),
                new PieChart.Data("ACTIVE EVENT", ac_e));
                pie.setData(pieList);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void handleLogoutAction(MouseEvent event) {
        if(flag){
         Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Logout");
                a.setContentText("Are You Sure You Want to Log Out?");
                a.setHeaderText(null);
                Optional<ButtonType> r=a.showAndWait();
                if(ButtonType.OK!=r.get()){
                } else {
                   // System.out.println("yesssssssssssssssssssssssssss");
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = EventManagementSystem.getMainStage();
                        stage.setScene(new Scene(root1));
                        stage.setTitle("Log In");
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
                
                
               // a.showAndWait();
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        A_eventNameField.clear();
        A_eventDatePicker.getEditor().setText("");
        A_eventFee.clear();
        A_startRegDatePicker.getEditor().setText("");
        A_endRegDatePicker.getEditor().setText("");
        A_event_code.clear();
        A_subEventName.clear();
        A_subEventFee.clear();
        A_subEventStartTime.clear();
        A_subEventEndTime.clear();
        A_quotaType.clear();
        A_quotaFee.clear();
    }

    @FXML
    private void handleCancleAction(ActionEvent event) {
        dashboardButton.setUnderline(true);
            adminDashPane.setVisible(true);
        addEventPane.setVisible(false);
        addSubEventPane.setVisible(false);
        AddQuotaPane.setVisible(false);
         viewEventPane.setVisible(false);
         detalisPane.setVisible(false);
        
    }
}
