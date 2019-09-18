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
public class ViewController implements Initializable {

    public static Todo getT() {
        return t;
    }

    public static void setT(Todo aT) {
        t = aT;
    }

    public static ObservableList<Todo> getTodoTitleList() {
        return todoTitleList;
    }

    public static void setTodoTitleList(ObservableList<Todo> aTodoTitleList) {
        todoTitleList = aTodoTitleList;
    }

    @FXML
    private DatePicker viewDateField;
    @FXML
    private TextField viewTitleField;
    private static Todo t;
    private static ObservableList<Todo>todoTitleList;
    @FXML
    private VBox viewTodoListCheckBoxVbox;
    @FXML
    private VBox viewCompletedListCheckBoxVbox;
    @FXML
    private Label countField;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        //t.geTodo();
       //System.out.println("*" + t.getTitle());
       viewTitleField.setText(t.getTitle());
       viewDateField.setValue(t.getDate());
       viewTodoListCheckBoxVbox.getChildren().clear();
       for(int i=0;i<t.getTodoItemList().size();i++){
           String line=t.getTodoItemList().get(i);
           CheckBox c=new CheckBox(line);
          c.setOnAction(e -> {
              viewTodoListCheckBoxVbox.getChildren().remove(c);
              c.setSelected(true);
              viewCompletedListCheckBoxVbox.getChildren().add(c);
              t.getTodoItemList().remove(line);
              t.getCompletedItemList().add(line);
              countField.setText(t.getCompletedItemList().size()+" Task Completed");
          });
           viewTodoListCheckBoxVbox.getChildren().add(c);
       }
       
       viewCompletedListCheckBoxVbox.getChildren().clear();
       countField.setText(t.getCompletedItemList().size()+" Task Completed");
       for(int i=0;i<t.getCompletedItemList().size();i++){
           String line= t.getCompletedItemList().get(i);
           CheckBox c= new CheckBox(line);
           c.setSelected(true);
           c.setOnAction(e -> {
               
                viewCompletedListCheckBoxVbox.getChildren().remove(c);
                c.setSelected(false);
                viewTodoListCheckBoxVbox.getChildren().add(c);
                t.getTodoItemList().add(line);
                t.getCompletedItemList().remove(line);
                //System.out.println(t.getCompletedItemList()+"\n");
                //System.out.println(t.getTodoItemList()+"\n");
                countField.setText(t.getCompletedItemList().size() +" Task Completed");
            });
           viewCompletedListCheckBoxVbox.getChildren().add(c);
       }
       
        // TODO
    }    

    @FXML
    private void handleBackMouseAction(MouseEvent event) throws IOException {
        WritetoFile();
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
        } catch (FileNotFoundException fnf) {
            System.err.println(fnf);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    @FXML
    private void handleBackToDeshBoardAction2(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Root.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("Todo List");
           stage.show();
    }

    @FXML
    private void handleBackToDeshBoardActioN(ActionEvent event) throws IOException {
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

    
}
