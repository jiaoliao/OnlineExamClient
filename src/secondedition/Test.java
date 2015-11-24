package secondedition;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivy
 */
public class Test {
    private SimpleStringProperty num;
    private SimpleStringProperty name;
    private SimpleStringProperty testname;
    private SimpleStringProperty time;
    private SimpleStringProperty score;
    private SimpleStringProperty advice;
    
    public Test(int num,String name,String testname,String time, int score,String advice){
        this.num=new SimpleStringProperty(num+"");
        this.name=new SimpleStringProperty(name);
        this.testname=new SimpleStringProperty(testname);
        this.time=new SimpleStringProperty(time);
        this.score=new SimpleStringProperty(score+"");
        this.advice=new SimpleStringProperty(advice);
    }
    
    public SimpleStringProperty numProperty(){
        return num;
    }
    
    public SimpleStringProperty nameProperty(){
        return name;
    }
    
    public SimpleStringProperty testnameProperty(){
        return testname;
    }
    
    public SimpleStringProperty timeProperty(){
        return time;
    }
    
    public SimpleStringProperty scoreProperty(){
        return score;
    }
    
    public SimpleStringProperty adviceProperty(){
        return advice;
    }
    
}
