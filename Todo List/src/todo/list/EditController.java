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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nayem
 */
public class EditController implements Initializable {

    public static ObservableList<Todo> getTodoTitleList() {
        return todoTitleList;
    }

    public static void setTodoTitleList(ObservableList<Todo> aTodoTitleList) {
        todoTitleList = aTodoTitleList;
    }

    @FXML
    private DatePicker editDateField;
    @FXML
    private TextField editTodoAndCompletedField;
    @FXML
    private VBox viewTodoListCheckBoxVbox;
    @FXML
    private VBox viewCompletedListCheckBoxVbox;
    @FXML
    private TextField editTitleField;
    @FXML
    private ListView<Todo> todoTitleListView;
    private static Todo t;
    private static ObservableList<Todo>todoTitleList;
    private Todo Selected;
    private boolean isFinished;
    private String flag;
    private String save;
    private String saveTitle;
    //private Todo p=
    @FXML
    private Button saveButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        todoTitleListView.setItems(todoTitleList);
        isFinished=true;
        // TODO
    }    

    @FXML
    private void handleBackMouseAction(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Root.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("Todo List");
           stage.show();
    }

    @FXML
    private void handleTitleListViewMouseAction(MouseEvent event) {
        if(isFinished){
            saveButton.setText("");
              saveButton.setText("Save");
         t=todoTitleListView.getSelectionModel().getSelectedItem();
         if(t!=null){
       Selected=t;
       saveTitle=Selected.getTitle();
        editTitleField.setText(t.getTitle());
        editDateField.setValue(t.getDate());   
        
        
        viewTodoListCheckBoxVbox.getChildren().clear();
       for(int i=0;i<t.getTodoItemList().size();i++){
           String line=t.getTodoItemList().get(i);
           CheckBox c=new CheckBox(line);
          
              c.setOnAction(e -> {
                  if(isFinished){
            editTodoAndCompletedField.setText(line);
            save=line;
           viewTodoListCheckBoxVbox.getChildren().remove(c);
           isFinished=false;
           flag="todo";
           }

          });
              
          
           viewTodoListCheckBoxVbox.getChildren().add(c);
       }
       
       viewCompletedListCheckBoxVbox.getChildren().clear();
       for(int i=0;i<t.getCompletedItemList().size();i++){
           String line= t.getCompletedItemList().get(i);
           CheckBox c= new CheckBox(line);
           c.setSelected(true);
               c.setOnAction(e -> {
                   if(isFinished){
                   isFinished=false;
                   flag="completed";
                editTodoAndCompletedField.setText(line);
                save=line;
                viewCompletedListCheckBoxVbox.getChildren().remove(c);
                   }
            });
           
           viewCompletedListCheckBoxVbox.getChildren().add(c);
       }
        
        }
    }
    }
    @FXML
    private void handleResetAction(ActionEvent event) {
        todoTitleListView.setItems(todoTitleList);
        isFinished=true;
        Selected=null;
        save=null;
        saveTitle=null;
        editTitleField.clear();
       editTodoAndCompletedField.clear();
       editDateField.setValue(null);
       viewTodoListCheckBoxVbox.getChildren().clear();
       viewCompletedListCheckBoxVbox.getChildren().clear();
        
    }
    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        if(Selected!=null){
            
            Selected.setDate(editDateField.getValue());
            Selected.setTitle(editTitleField.getText());
            isFinished=true;
            //System.out.println("1");
            if(flag!=null){
                //System.out.println("2");
                if(flag.equals("todo")){
                    //System.out.println("3");
                    for(int i=0;i<Selected.getTodoItemList().size();i++){
                        if(Selected.getTodoItemList().get(i).equals(save)){
                            //System.out.println(Selected.getTodoItemList().get(i));
                            Selected.getTodoItemList().remove(i);
                            Selected.getTodoItemList().add(editTodoAndCompletedField.getText());
                             //System.out.println(Selected.getTodoItemList());
                        }
                    }
                }
                else if(flag.equals("completed")){
                    for(int i=0;i<Selected.getCompletedItemList().size();i++){
                        if(Selected.getCompletedItemList().get(i).equals(save)){
                            Selected.getCompletedItemList().remove(i);
                            Selected.getTodoItemList().add(editTodoAndCompletedField.getText());
                        }
                        
                    }
                }
                editTodoAndCompletedField.clear();
                editTitleField.clear();
               // todoTitleList.add(t);
                
               //todoTitleListView.setItems(todoTitleList);
               //System.out.println(t);
              WritetoFile();
              //todoTitleListView.getItems().clear();
              todoTitleListView.setItems(todoTitleList);
              viewTodoListCheckBoxVbox.getChildren().clear();
              viewCompletedListCheckBoxVbox.getChildren().clear();
              saveButton.setText("");
              saveButton.setText("Saved");
             // saveButton.
              
            }
        }
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
    private void handleBackMouseAction2(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Root.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("Todo List");
           stage.show();
    }

    @FXML
    private void handleDashboardAction(ActionEvent event) throws IOException {
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

    private void handleNewAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newList.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("New List");
           stage.show();
    }
}
