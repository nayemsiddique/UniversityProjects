/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventmanagementsystem;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author nayem
 */
public class reg_events {
    private String event_code;
    private String event_name;
    //private ObservableList quota_type;
    private ObservableList sub_event;
    private LocalDate event_date;
    private int event_fee;
    private LocalDate reg_end;
    private String quota_type;
      public reg_events() {
          //quota_type=FXCollections.observableArrayList();
          sub_event=FXCollections.observableArrayList();
    }

    public reg_events(String event_code, String event_name, LocalDate event_date, int event_fee,LocalDate reg_end,String quota_type) {
        this();
        this.event_code = event_code;
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_fee = event_fee;
       // this.reg_start = reg_start;
        this.reg_end = reg_end;
        this.quota_type=quota_type;
    }

    public String getEvent_code() {
        return event_code;
    }

    public void setEvent_code(String event_code) {
        this.event_code = event_code;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    

    public LocalDate getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDate event_date) {
        this.event_date = event_date;
    }

    public int getEvent_fee() {
        return event_fee;
    }

    public void setEvent_fee(int event_fee) {
        this.event_fee = event_fee;
    }

    public LocalDate getReg_end() {
        return reg_end;
    }

    public void setReg_end(LocalDate reg_end) {
        this.reg_end = reg_end;
    }
    

  @Override
    public String toString() {
        return event_name;
    }
    public ObservableList getSub_event() {
        return sub_event;
    }

    public void setSub_event(ObservableList sub_event) {
        this.sub_event = sub_event;
    }

    public String getQuota_type() {
        return quota_type;
    }
    
    
    

    
}