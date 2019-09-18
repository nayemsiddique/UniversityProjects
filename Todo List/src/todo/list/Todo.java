/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.list;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author nayem
 */
public class Todo {
    private LocalDate date;
    private String title;
    private ArrayList<String>TodoItemList;
    private ArrayList<String>CompletedItemList;
    private Todo t;

    public Todo() {
        TodoItemList= new ArrayList<>();
        CompletedItemList= new ArrayList<>();
    }

    public Todo(LocalDate date, String title) {
        this();
        this.date = date;
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }
    public Todo geTodo() {
        return t;
    }

    public void setT(Todo t) {
        this.t = t;
    }

    public ArrayList<String> getTodoItemList() {
        return TodoItemList;
    }

    public ArrayList<String> getCompletedItemList() {
        return CompletedItemList;
    }

    @Override
    public String toString() {
        return title;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTodoItemList(ArrayList<String> TodoItemList) {
        this.TodoItemList = TodoItemList;
    }

    public void setCompletedItemList(ArrayList<String> CompletedItemList) {
        this.CompletedItemList = CompletedItemList;
    }
    
}