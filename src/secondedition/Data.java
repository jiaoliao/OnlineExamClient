package secondedition;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ivy
 */
public class Data {
    private StringProperty data;
    private BooleanProperty con;
    public Data(String s2, Boolean b){
        data = new SimpleStringProperty(s2);
        con = new SimpleBooleanProperty(b);
    }
    public StringProperty dataProperty(){
        return data;
    }
    
    public BooleanProperty conProperty(){
        return con;
    }
    
}
