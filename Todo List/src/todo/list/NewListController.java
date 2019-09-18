/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.list;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nayem
 */
public class NewListController implements Initializable {

    public static ObservableList<Todo> getTodoTitleList() {
        return todoTitleList;
    }

    public static void setTodoTitleList(ObservableList<Todo> aTodoTitleList) {
        todoTitleList = aTodoTitleList;
    }

    @FXML
    private DatePicker saveDate;
    @FXML
    private VBox newTodoListVboxCheckBox;
    @FXML
    private VBox newCompletedListVboxCheckBox;
    @FXML
    private TextField saveTitle;
    private ObservableList<String>addNewTodoList;
    private ObservableList<String>addNewCompletedList;
    private static ObservableList<Todo>todoTitleList;
    private static Todo t;
    @FXML
    private TextField addTodoListField;
    @FXML
    private Label wrongDate;
    @FXML
    private Label wrongTitle;
    @FXML
    private Label wrongTodo;
    @FXML
    private Label newCompletedCount;
    @FXML
    private Label newTodoCount;
    @FXML
    private Label wrongOperationField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addNewTodoList=FXCollections.observableArrayList();
        addNewCompletedList=FXCollections.observableArrayList();
        // TODO
    }    

    @FXML
    private void handleCancleAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Root.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("Todo List");
           stage.show();
    }

    @FXML
    private void handleAddNewTodoListAction(ActionEvent event) {
        if((addTodoListField.getText()!=null && !addTodoListField.getText().isEmpty())&& 
                (saveDate.getValue()!=null)&&(saveTitle.getText()!=null 
                && !saveTitle.getText().isEmpty())){
            addNewTodoList.add(addTodoListField.getText());
            String a=addTodoListField.getText();
            CheckBox c= new CheckBox(a);
            newTodoListVboxCheckBox.getChildren().add(c);
            newCompletedCount.setText(addNewCompletedList.size()+" Task Completed");
            newTodoCount.setText(addNewTodoList.size()+" Items Added");
            c.setOnAction(e -> {
              c.setSelected(true);
              newTodoListVboxCheckBox.getChildren().remove(e);
              newCompletedListVboxCheckBox.getChildren().add(c);
              addNewCompletedList.add(a);
              addNewTodoList.remove(a);
             // System.out.println(addNewCompletedList);
             //System.out.println(addNewTodoList);
              newCompletedCount.setText(addNewCompletedList.size()+" Task Completed");
            newTodoCount.setText(addNewTodoList.size()+" Items Added");
          });
            
            addTodoListField.clear();  
        }
         if(saveDate.getValue()==null){
            wrongDate.setText("Required!!");
        }
        if(saveTitle.getText().isEmpty()){
            wrongTitle.setText("Required!!");
        }
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        addNewCompletedList.clear();
        addNewTodoList.clear();
        newCompletedListVboxCheckBox.getChildren().clear();
        newTodoListVboxCheckBox.getChildren().clear();
        addTodoListField.clear();
        saveDate.setValue(null);
        saveTitle.clear();
        newCompletedCount.setText("");
        newTodoCount.setText("");
        wrongOperationField.setText("");
        wrongDate.setText("");
        wrongTodo.setText("");
        wrongTitle.setText("");
    }

    @FXML
    private void handleSaveAction(ActionEvent event) throws IOException {
        if(saveDate.getValue()==null || saveTitle.getText()==null &&
                saveTitle.getText().isEmpty()){
            //Operation failed .Try Again!!
            wrongOperationField.setText("Operation failed .Try Again!!");
        if(saveDate.getValue()==null){
            wrongDate.setText("Required!!");
        }
        if(saveTitle.getText().isEmpty()){
            wrongTitle.setText("Required!!");
        }
        if( addTodoListField.getText().isEmpty()){
            wrongTodo.setText("Required!!");
        }
            
        }
        else{
            
        t=new Todo(saveDate.getValue(),saveTitle.getText());
        for(int i=0;i<addNewTodoList.size();i++){
            t.getTodoItemList().add(addNewTodoList.get(i));
        }
        for(int i=0;i<addNewCompletedList.size();i++){
            t.getCompletedItemList().add(addNewCompletedList.get(i));
        }
      
        todoTitleList.add(t);
       WritetoFile();
       //System.out.println(todoTitleList);
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Root.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("Todo List");
           stage.show();
        }
    }
    
    
    public void WritetoFile(){
        try{
            RandomAccessFile output=new RandomAccessFile("input.txt","rw");
            output.setLength(0);
            for(int i=0;i<todoTitleList.size();i++){
                t=todoTitleList.get(i);
                output.writeBytes(""+t.getDate()+ "\n");
                 output.writeBytes(t.getTitle()+ "\n");
                 for(int j=0;j<t.getTodoItemList().size();j++){
                   output.writeBytes(t.getTodoItemList().get(j)+ "\n");  
                 }
                 output.writeBytes("#"+ "\n");
                 for(int k=0;k<t.getCompletedItemList().size();k++){
                    output.writeBytes(t.getCompletedItemList().get(k)+ "\n");  
                 }
                 output.writeBytes("##"+ "\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleWrongClearAction(MouseEvent event) {
        wrongOperationField.setText("");
        wrongDate.setText("");
        wrongTodo.setText("");
        wrongTitle.setText("");
    }

    @FXML
    private void wrongClearDate(MouseEvent event) {
        wrongOperationField.setText("");
        wrongDate.setText("");
        wrongTodo.setText("");
        wrongTitle.setText("");
    }

    @FXML
    private void worngClearTodo(MouseEvent event) {
        wrongOperationField.setText("");
        wrongDate.setText("");
        wrongTodo.setText("");
        wrongTitle.setText("");
    }

    @FXML
    private void handleBackToDashboardAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Root.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("Todo List");
           stage.show();
    }

    @FXML
    private void handleBackToDashboardAction2(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Root.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("Todo List");
           stage.show();
    }

    @FXML
    private void handleBackToDashboardAction(MouseEvent event) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Root.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("Todo List");
           stage.show();
    }

    @FXML
    private void handleAboutUsAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("About Us");
           stage.show();
    }

    private void handleEditListAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("About Us");
           stage.show();
    }
    
}
