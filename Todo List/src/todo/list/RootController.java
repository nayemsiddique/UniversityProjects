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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author nayem
 */
public class RootController implements Initializable {

    @FXML
    private ListView<Todo> todoTitleListView;
    private ObservableList<Todo>todoTitleList;
    private Todo t;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        todoTitleList=FXCollections.observableArrayList();
        try{
            RandomAccessFile input=new RandomAccessFile("input.txt","r");
            while(true){
                String date=input.readLine();
                if(date==null) break;
                String title=input.readLine();
                t=new Todo(LocalDate.parse(date),title);
                String line;
                while ((line = input.readLine()) != null) {
                    if (line.equals("#")) {
                        break;
                    }
                    t.getTodoItemList().add(line);
                }

                while ((line = input.readLine()) != null) {
                    if (line.equals("##")) {
                        break;
                    }
                    t.getCompletedItemList().add(line);
                }
                todoTitleList.add(t);
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
           System.err.println(ex); 
        }
        todoTitleListView.setItems(todoTitleList);
        
    }    

    @FXML
    private void handleNewListAction(ActionEvent event) throws IOException {
        NewListController.setTodoTitleList(todoTitleList);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newList.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));  
           stage.setTitle("New List");
           stage.show();
           
    }

    @FXML
    private void handleNewListMouseAction(MouseEvent event) throws IOException {
        NewListController.setTodoTitleList(todoTitleList);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newList.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("New List");
           stage.show();
    }

    @FXML
    private void handleTitleListViewMouseAction(MouseEvent event) throws IOException {
        t=todoTitleListView.getSelectionModel().getSelectedItem();
        if(t!=null){
            ViewController.setT(t);
        ViewController.setTodoTitleList(todoTitleList);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("View List");
           stage.show();
           
        
        }
        
    }

    @FXML
    private void handleEditAction(ActionEvent event) throws IOException {
        EditController.setTodoTitleList(todoTitleList);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit.fxml"));
           Parent root1 = (Parent) fxmlLoader.load();
           Stage stage = new Stage();
           stage.setScene(new Scene(root1));
           stage.setTitle("Edit List");
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
