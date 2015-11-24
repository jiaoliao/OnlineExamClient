package secondedition;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ivy
 */
public class Sdesktop extends Application {

    private ScrollPane chatScroll;
    private TextArea ansArea = new TextArea();
    private TextArea chatArea = new TextArea();
    private BorderPane root = new BorderPane();
    private StackPane centerPane = new StackPane();
    private String id, msg, fileName;
    private int count=-1;
    private int flag_send = 0;
    private boolean isExist = true;
    private ObservableList<Test> data = FXCollections.observableArrayList(new Test(1, "任芝萱", "Hello java", "16'40", 90, "perfect"));
    private List<String> dataList;
    private List<HBox> chatList = new ArrayList<HBox>();
    private ExecutorService application = Executors.newCachedThreadPool();

    private Test_server server;
    private Test_client client;
    private ImageProcessor image;
    
    public Sdesktop(Test_server server, String s) {
        // Christine start
        this.server = server;
        this.client = new Test_client();
        // Christine end
        
        // Ivy start
        this.id = s;
        Stage myStage = new Stage();
        start(myStage);
        // Ivy end
    }

    public void start(Stage primaryStage) {
        // Ivy start
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Student Desktop -1");
        Scene s = new Scene(root, 1024, 668);
        primaryStage.setScene(s);
        root.setStyle("-fx-background-color: #000000;");
        root.setTop(addtop());
        root.setCenter(centerPane);
        primaryStage.show();
        // Ivy endprimaryStage
    }

    private GridPane addtop() {
        // Ivy start
        GridPane topPane = new GridPane();
        MenuBar mbm = setmenubar();
        Text time = setdate();
        MenuBar mbusr = setuserbar();
        add2topPane(topPane, mbm, time, mbusr);
        settopformat(topPane, time, mbusr);
        // Ivy end
        return topPane;
    }

    private MenuBar setmenubar() {
        // Ivy start
        MenuBar mbm = new MenuBar();
        Menu menu = setmenu();
        mbm.getMenus().addAll(menu);
        mbm.setPrefSize(50, 100);
        mbm.setStyle("-fx-background-color: #000000;");
        // Ivy end
        return mbm;
    }

    private Menu setmenu() {
        // Ivy start
        Menu menu = new Menu();
        Text t = setmenutitleText("MENU");
        menu.setGraphic(t);
        MenuItem[] menuitem = setmenuitem();
        menu.getItems().addAll(menuitem);
        // Ivy end
        return menu;
    }

    private MenuItem[] setmenuitem() {
        // Ivy start
        MenuItem[] menuitem = new MenuItem[3];
        menuitem[0] = settestitem();
        menuitem[1] = sethistoryitem();
        menuitem[2] = setaboutitem();
        // Ivy end
        return menuitem;
    }

    private MenuItem settestitem() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("測驗");
        m.setGraphic(t);
        m.setOnAction((a) -> {
            centerPane.getChildren().add(addtest());
            
            // Christine start   
            startScreenShot();
//            overseeAnsField();
            // Christine end  
        });
        // Ivy end
        return m;
    }

    private MenuItem sethistoryitem() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("歷次測驗");
        m.setGraphic(t);
        m.setOnAction((a) -> {
            centerPane.getChildren().add(searchistory());
//            Stage history = new Stage();
//            Scene s = new Scene(searchistory());
//            setstage(history, s, "歷次測驗 -1.3");
        });
        // Ivy end
        return m;
    }

    private MenuItem setaboutitem() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("關於我們");
        m.setGraphic(t);
        m.setOnAction((a) -> {
            showabout();
        });
        // Ivy end
        return m;
    }

    private Text setmenutitleText(String s) {
        // Ivy start
        Text menutext = new Text(s);
        menutext.setFill(Color.WHITE);
        menutext.setFont(Font.font("新細明體", FontWeight.BOLD, 20));
        // Ivy end
        return menutext;
    }

    private void setstage(Stage test, Scene s, String title) {
        // Ivy start
        test.initStyle(StageStyle.UTILITY);
        test.setTitle(title);
        test.setScene(s);
        test.setResizable(false);
        test.show();
        // Ivy end
    }

    private void showleavealert(Stage test) {
        // Ivy start
        Alert alert = new Alert(Alert.AlertType.WARNING, "離開後將終止本測驗", ButtonType.NO, ButtonType.YES);
        alert.setTitle("您確定要離開？");
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(null);
        alert.showAndWait();
        alert.setOnCloseRequest((a) -> {
            alert.close();
        });
        // Ivy end
    }

    private void showabout() {
        // Ivy start
        Alert alert = new Alert(AlertType.INFORMATION, "我們是三個傻瓜！！！");
        alert.setTitle("關於我們 -1.4");
        alert.setHeaderText(null);
        alert.showAndWait();
        // Ivy end
    }

    private Text setdate() {
        // Ivy start
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Text time = new Text(dateFormat.format(date));
        time.setFill(Color.WHITE);
        time.setFont(Font.font("新細明體", FontWeight.BOLD, 30));
        // Ivy end
        return time;
    }

    private MenuBar setuserbar() {
        // Ivy start
        MenuBar mbusr = new MenuBar();
        Menu menu = setusrmenu();
        mbusr.getMenus().addAll(menu);
        mbusr.setPrefSize(50, 100);
        mbusr.setStyle("-fx-background-color: #000000;");
        // Ivy end
        return mbusr;
    }

    private Menu setusrmenu() {
        // Ivy start
        Menu menu = new Menu();
        Text t = setmenutitleText("Ivy-student");
        menu.setGraphic(t);
        MenuItem[] menuitem = setusrmenuitem();
        menu.getItems().addAll(menuitem);
        menu.setStyle("-fx-translate-x: 100;");
        // Ivy end
        return menu;
    }

    private MenuItem[] setusrmenuitem() {
        // Ivy start
        MenuItem[] menuitem = new MenuItem[3];
        menuitem[0] = setmodifyitem();
        menuitem[1] = setlogoutitem();
        menuitem[2] = setexititem();
        // Ivy end
        return menuitem;
    }

    private MenuItem setmodifyitem() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("修改個人資料");
        m.setGraphic(t);
        m.setOnAction((a) -> {
            centerPane.getChildren().add(modifydata());
//            Stage modify = new Stage();
//            Scene s = new Scene(modifydata());
//            setstage(modify, s, "修改個人資料 -1.1");
        });
        // Ivy end
        return m;
    }

    private MenuItem setlogoutitem() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("登出");
        m.setGraphic(t);
        m.setOnAction((a) -> {
            Login l = new Login();
            l.start(new Stage());
            Stage s = (Stage) root.getScene().getWindow();
            s.close();
        });
        // Ivy end
        return m;
    }

    private MenuItem setexititem() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("結束");
        m.setGraphic(t);
        m.setOnAction((a) -> {
            Platform.exit();
        });
        // Ivy end
        return m;
    }

    private void add2topPane(GridPane topPane, MenuBar mbm, Text time, MenuBar mbusr) {
        // Ivy start
        topPane.add(mbm, 0, 0);
        topPane.add(time, 1, 0);
        topPane.add(mbusr, 2, 0);
        // Ivy end
    }

    private void settopformat(GridPane topPane, Text time, MenuBar mbusr) {
        // Ivy start
        topPane.setPrefHeight(30);
        topPane.setGridLinesVisible(true);
        topPane.getColumnConstraints().add(new ColumnConstraints(256));
        topPane.getColumnConstraints().add(new ColumnConstraints(512));
        topPane.getColumnConstraints().add(new ColumnConstraints(256));
        GridPane.setHalignment(time, HPos.CENTER);
        GridPane.setHalignment(mbusr, HPos.RIGHT);
        GridPane.setValignment(topPane, VPos.CENTER);
        // Ivy end
    }

    private GridPane addtest() {
        // Ivy start
        count++;
        int tmp=count;
        GridPane testPane = new GridPane();
        Text qtitle = setText("考試題目");
        ScrollPane qscroll = setquestionscroll();
        ScrollPane anscroll = setAnswerScroll();
        Button databtn = setdatabutton("設定測值");
        Button commandbtn = setcommandbutton("執行");
        Button finishbtn = setfinishbutton("交卷",tmp);
        HBox testbtnBox = settestbuttonbox(databtn, commandbtn, finishbtn);

        Text chatitle = setText("討論區");
        ScrollPane chatscroll = setchatscroll();

        TextArea chatTextArea = setchatTextArea();
        Button messageBtn = setmessagebutton("留言");
        HBox chatBox = setchatbox(chatTextArea, messageBtn);

        add2testPane(testPane, qtitle, qscroll, anscroll, testbtnBox, chatitle, chatscroll, chatBox);
        settestPaneformat(testPane, qtitle, testbtnBox, chatitle);
        // Ivy end
        return testPane;
    }

    private void settestPaneformat(GridPane testPane, Text qtitle, HBox testbtnBox, Text chatitle) {
        // Ivy start
        testPane.setGridLinesVisible(true);
        testPane.setPrefSize(1000, 600);
        testPane.getColumnConstraints().add(new ColumnConstraints(512));
        testPane.getColumnConstraints().add(new ColumnConstraints(512));
        testPane.getRowConstraints().add(new RowConstraints(40));
        testPane.getRowConstraints().add(new RowConstraints(120));
        testPane.getRowConstraints().add(new RowConstraints(412));
        testPane.getRowConstraints().add(new RowConstraints(60));
        testPane.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setHalignment(qtitle, HPos.CENTER);
        GridPane.setHalignment(testbtnBox, HPos.CENTER);
        GridPane.setHalignment(chatitle, HPos.CENTER);
        // Ivy end
    }

    private ScrollPane setquestionscroll() {
        // Ivy start
        ScrollPane s = new ScrollPane();
        TextArea question = setQuestionTextArea("把一乘到九，由下而上排列\n使用語言：JAVA");
        s.setContent(question);
        // Ivy end
        return s;
    }

    private TextArea setQuestionTextArea(String s) {
        // Ivy start
        TextArea t = new TextArea(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        t.setPrefSize(520, 110);
        t.setEditable(false);
        // Ivy end
        return t;
    }

    private ScrollPane setAnswerScroll() {
        // Ivy start
        ScrollPane s = new ScrollPane();
        TextArea ans = setAnsTextArea();
        s.setContent(ans);
        // Ivy end
        return s;
    }

    private TextArea setAnsTextArea() {
        // Ivy start
        TextArea t = new TextArea();
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        t.setPrefSize(500, 500);
        // Ivy end
        return t;
    }

    private Text setText(String s) {
        // Ivy start
        Text t = new Text(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        // Ivy end
        return t;
    }

    private Button setdatabutton(String s) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().add(addDataFlowPane());
//            Stage data = new Stage();
//            Scene scene = new Scene(dataPane);
//            setstage(data, scene, "輸入測值 -1.2.1");
        });
        return b;
    }

    private FlowPane addDataFlowPane() {
        count++;
        int tmp=count;
        FlowPane data = new FlowPane();
        setDataFlowPaneFormat(data);
        dataList = new ArrayList<String>();
        TableView t = new TableView();
        TableColumn nt = setNumTableColumn("num");
        TableColumn dt = setDataTableColumn("data");
        TableColumn ct = setCheckBoxTableColumn("check");
        t.getColumns().setAll(nt, dt, ct);
        t.setPrefWidth(380);
        t.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        t.setEditable(true);

        TextArea area = new TextArea("請輸入測資");
        area.setPrefSize(380, 100);
        Button explain = setExplainButton("說明");
        Button add = setAddButton("新增測資", area, t);
        Button delete = setDeleteButton("刪除選取測資", ct, t);
        Button OK = setOKButton("送出",tmp);
        HBox h = setDataBox(explain,add, delete, OK);
        data.getChildren().addAll(t, area, h);
        data.setAlignment(Pos.CENTER);
        data.setPadding(new Insets(10,10,10,10));
        data.setMinSize(780, 500);
        data.setMaxSize(780, 500);
        data.setStyle("-fx-background-color: #DDDDDD;");
        return data;
    }

    private TableColumn setNumTableColumn(String s) {
        TableColumn nt = new TableColumn(s);
        nt.setMinWidth(70);
        nt.setMaxWidth(70);
        nt.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new TableCell() {
                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(null);
                        setText(empty ? null : getIndex() + 1 + "");
                        setAlignment(Pos.CENTER);
                    }
                };
            }
        });
        return nt;
    }

    private TableColumn setDataTableColumn(String s) {
        TableColumn dt = new TableColumn(s);
        dt.setCellValueFactory(new PropertyValueFactory<Data, String>("data"));
        return dt;
    }

    private TableColumn setCheckBoxTableColumn(String s) {
        TableColumn ct = new TableColumn(s);
        ct.setCellValueFactory(new PropertyValueFactory<Data, String>("con"));
        ct.setCellFactory(CheckBoxTableCell.forTableColumn(ct));
        ct.setEditable(true);
        ct.setMinWidth(70);
        ct.setMaxWidth(70);
        return ct;
    }

    private Button setExplainButton(String s) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().add(setDataExplain());
//                    String content="一筆測資內，值與值間的分隔為enter鍵\n如：1\n2\n3\n為一筆測資內的三個值\n若為1 2 3\n則為一筆測資內的一個值，如需個別取出1,2,3則須自行切割字串";
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION,content,ButtonType.YES);
//                    alert.setTitle("使用說明");
//                    alert.initStyle(StageStyle.UTILITY);
//                    alert.setHeaderText(null);
//                    alert.showAndWait();
                });
        return b;
    }
    
    private GridPane setDataExplain(){
        count++;
        int tmp=count;
        GridPane explain = new GridPane();
        Text title = setText("使用說明:");
        Text t = setText("一筆測資內，值與值間的分隔為enter鍵\n如：\n1\n2\n3\n為一筆測資內的三個值\n若為1 2 3\n則為一筆測資內的一個值，如需個別取出1,2,3則須自行切割字串");
        Button OK = setExplainOKButton("OK",tmp);
        explain.add(title,0,0,2,1);
        explain.add(t, 0, 1,2,1);
        explain.add(OK,0,2,2,1);
        setExplainFormat(explain,title,t,OK);
        return explain;
    }
    
    private Button setExplainOKButton(String s,int tmp){
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a)->{
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }
    
    private void setExplainFormat(GridPane explain,Text title,Text t,Button OK){
        explain.setPadding(new Insets(10,10,10,10));
        explain.setMinSize(600, 280);
        explain.setMaxSize(600, 280);
        explain.setGridLinesVisible(true);
        explain.setHgap(30);
        explain.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setHalignment(t, HPos.CENTER);
        GridPane.setHalignment(OK, HPos.CENTER);
    }

    private Button setAddButton(String s, TextArea area, TableView t) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction(
                (a) -> {
                    if (area.getText().equals("") || area.getText().equals("請輸入測資")) {
                    } else {
                        dataList.add(area.getText());
                        t.setItems(FXCollections.observableArrayList(dataList));
                    }
                }
        );
        return b;
    }

    private Button setOKButton(String s,int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
//            Stage stage = (Stage) b.getScene().getWindow();
//            stage.close();
            saveTestValue();
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }

    private Button setDeleteButton(String s, TableColumn ct, TableView t) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            for (int i = 0; i < dataList.size(); i++) {
                if (ct.getCellObservableValue(i).getValue() == Boolean.TRUE) {
                    dataList.remove(i);
                    t.setItems(FXCollections.observableArrayList(dataList));
                    i = 0;
                }
            }
        });
        return b;
    }

    private HBox setDataBox(Button explain,Button add, Button delete, Button OK) {
        HBox h = new HBox(10);
        h.setPadding(new Insets(20, 20, 20, 20));
        h.getChildren().addAll(explain,add, delete, OK);
        return h;
    }

    private void setDataFlowPaneFormat(FlowPane dataPane) {
        // Ivy start
        dataPane.setAlignment(Pos.CENTER);
        dataPane.setStyle("-fx-background-color: #DDDDDD;");
        dataPane.setPrefSize(350, 610);
        // Ivy end
    }

    private Button setcommandbutton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().add(setProjectName());

        });
        // Ivy end
        return b;
    }
    
    private GridPane setProjectName() {
        // Ivy start
        count++;
        int tmp = count;
        GridPane namePane = new GridPane();
            
//      Christin - whether the file is exiist
        if(isExist) {
            Text t = setText("設定檔名:");
            TextField tf = setNameTextField(".java");
            HBox box = setnameBox(t,tf);
            Button yes = setNameYesBtn("YES", tmp);
            Button no = setNameNoBtn("Cancel", tmp);
            namePane.add(box, 0, 0, 2, 1);
            namePane.add(yes, 0, 1);
            namePane.add(no, 1, 1);
            setnamePaneFormat(namePane, t, yes, no);
            
            isExist = false;
        }
        return namePane;
    }
    
    private TextField setNameTextField(String s){
        TextField t = new TextField(s);
        return t;
    }
    
    private HBox setnameBox(Text t,TextField tf){
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.getChildren().addAll(t,tf);
        return hb;
    }

    private Button setNameYesBtn(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
            
            sendMsg(ansArea, true);
            showCommandAlert(b);
        });
        return b;
    }

    private Button setNameNoBtn(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }
    
    private void setnamePaneFormat(GridPane alert, Text t, Button yes, Button no) {
        alert.setPadding(new Insets(10, 10, 10, 10));
        alert.getColumnConstraints().add(new ColumnConstraints(100));
        alert.getColumnConstraints().add(new ColumnConstraints(100));
        alert.setMinSize(250, 120);
        alert.setMaxSize(250, 120);
        alert.setGridLinesVisible(true);
        alert.setVgap(10);
        alert.setHgap(30);
        alert.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setValignment(t, VPos.CENTER);
        GridPane.setHalignment(yes, HPos.CENTER);
        GridPane.setHalignment(no, HPos.CENTER);
    }
    

    private Button setfinishbutton(String s,int tmp) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setPrefSize(80, 40);
        b.setOnAction((a) -> {
            centerPane.getChildren().add(setfinishalert(tmp));
        });
        // Ivy end
        return b;
    }

    private GridPane setfinishalert(int tmp) {
        // Ivy start
        int test_tmp=tmp;
        count++;
        int alert_tmp = count;
        GridPane alert = new GridPane();
        Text t = setText("您確定要交卷？\n交卷後將無法更改作答\n");
        Button yes = setYesBtn("YES",test_tmp,alert_tmp);
        Button no = setNoBtn("NO",alert_tmp);
        alert.add(t, 0, 0,2,1);
        alert.add(yes, 0, 1);
        alert.add(no, 1, 1);
        setAlertFormat(alert,t,yes,no);
        
        return alert;
        // Ivy end
    }
    
    private Button setYesBtn(String s,int test_tmp,int tmp){
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a)->{
            centerPane.getChildren().remove(test_tmp, tmp+1);
            count=count-2;
            
//      Christine start
            setProjectName();
            sendMsg(ansArea, true);
//      Christine end
        });
        return b;
    }
    
    private Button setNoBtn(String s,int tmp){
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a)->{
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }
    
    private void setAlertFormat(GridPane alert,Text t,Button yes, Button no){
        alert.setPadding(new Insets(10,10,10,10));
        alert.getColumnConstraints().add(new ColumnConstraints(85));
        alert.getColumnConstraints().add(new ColumnConstraints(85));
        alert.setMinSize(220, 140);
        alert.setMaxSize(220, 140);
        alert.setGridLinesVisible(true);
        alert.setVgap(10);
        alert.setHgap(30);
        alert.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setValignment(t, VPos.CENTER);
        GridPane.setHalignment(yes, HPos.CENTER);
        GridPane.setHalignment(no, HPos.CENTER);
    }

    private HBox settestbuttonbox(Button databtn, Button commandbtn, Button finishbtn) {
        // Ivy start
        HBox b = new HBox(20);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.getChildren().addAll(databtn, commandbtn, finishbtn);
        // Ivy end
        return b;
    }

    private ScrollPane setchatscroll() {
        // Ivy start
        chatScroll = new ScrollPane();
        // Ivy end
        return chatScroll;
    }

    private Label setphotoLabel(String usr) {
        //Ivy start
        Label l = new Label();
        l.setPadding(new Insets(10, 10, 10, 10));
        l.setMaxSize(10, 10);
        //Ivy end
        
//        Christine start
        ImageView imageView = new ImageView(new Image("file:/Users/christine/NetBeansProjects/online_test_2/test.jpg"));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        l.setGraphic(imageView);
//        Christine end
        return l;
    }

    private Text setshowchatText(String s) {
        //Ivy start
        Text t = new Text(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 16));
        //Ivy end
        return t;
    }

    private HBox settalkbox(Label photo, Text talk) {
        //Ivy start
        HBox b = new HBox(10);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setPrefSize(500, 80);
        b.setAlignment(Pos.CENTER_LEFT);
        b.getChildren().addAll(photo, talk);
        //Ivy end
        return b;
    }

    private TextArea setchatTextArea() {
        // Ivy start
        chatArea = new TextArea();
        chatArea.setFont(Font.font("新細明體", FontWeight.NORMAL, 16));
        chatArea.setPrefSize(390, 30);
        // Ivy end
        return chatArea;
    }

    private Button setmessagebutton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setPrefSize(80, 40);
        // Ivy end
        
//        Christine start
        b.setOnAction((a) -> {
            sendMsg(chatArea, false);
            chatArea.setText("");

            if (msg != "") {
                addChatList();
                printChatHistory();
            }
        });
//        Christine end
        return b;
    }

    private HBox setchatbox(TextArea chatTextArea, Button messageBtn) {
        // Ivy start
        HBox chatBox = new HBox(10);
        chatBox.setAlignment(Pos.CENTER);
        chatBox.setPadding(new Insets(10, 10, 10, 10));
        chatBox.getChildren().addAll(chatTextArea, messageBtn);
        // Ivy end
        return chatBox;
    }

    private void add2testPane(GridPane testPane, Text qtitle, ScrollPane qscroll, ScrollPane anscroll, HBox testbtnBox, Text chatitle, ScrollPane chatscroll, HBox chatBox) {
        // Ivy start
        testPane.add(qtitle, 0, 0);
        testPane.add(qscroll, 0, 1);
        testPane.add(anscroll, 0, 2);
        testPane.add(testbtnBox, 0, 3);
        testPane.add(chatitle, 1, 0);
        testPane.add(chatscroll, 1, 1, 1, 2);
        testPane.add(chatBox, 1, 3);
        // Ivy end
    }

    private VBox searchistory() {
        // Ivy start
        count++;
        int tmp = count;
        Text title = settitleText("我的成績單");

        TableView historytable = new TableView();
        TableColumn NumCol = setNumColumn("項次");
        TableColumn TestCol = setTestColumn("測驗");
        TableColumn TimeCol = setTimeColumn("時間");
        TableColumn ScoreCol = setScoreColumn("成績");
        historytable.setItems(data);
        historytable.getColumns().addAll(NumCol, TestCol, TimeCol, ScoreCol);
        Button back = setHistoryBackButton("返回",tmp);

        VBox box = sethistorybox(title, historytable,back);
        // Ivy end
        return box;
    }
    
    
    private Button setHistoryBackButton(String s,int tmp){
        Button b = new Button(s);
        b.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        b.setOnAction((a)->{
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }
    
    private VBox sethistorybox(Text title, TableView historytable, Button back) {
        // Ivy start
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.setMinSize(400, 550);
        box.setMaxSize(400, 550);
        box.setStyle("-fx-background-color: #DDDDDD;");
        box.getChildren().addAll(title, historytable,back);
        // Ivy end
        return box;
    }

    private Text settitleText(String s) {
        // Ivy start
        Text t = new Text(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 40));
        // Ivy end
        return t;
    }

    private FlowPane modifydata() {
        // Ivy start
        count++;
        int tmp=count;
        FlowPane modifyPane = new FlowPane();
        GridPane centerPane = new GridPane();

        Text sceneTitleText = settitleText("修改個人資料");
        Button modifyphoto = setmodifyphotobutton("上傳照片");
        Text pwText = setText("密碼 ：");
        PasswordField rpasswordField = setpwField();
        Text nameText = setText("姓名 ：");
        TextField rnameTextField = setTextField();
        Text roleText = setText("身份 ：");
        ComboBox roletool = setroolcombobox();
        Text gradeText = setText("年班 ：");
        TextField rgradeTextField = setTextField();
        Text mailText = setText("E-MAIL ：");
        TextField rmailTextField = setTextField();
        Button sentBtn = setsentbutton("送出",tmp);
        Button backBtn = setbackbutton("返回",tmp);
        HBox modifybuttonbox = setmodifybuttonbox(sentBtn, backBtn);
        add2modifycenterPane(centerPane, sceneTitleText, modifyphoto, pwText, rpasswordField, nameText, rnameTextField, roleText, roletool, gradeText, rgradeTextField, mailText, rmailTextField, modifybuttonbox);
        setmodifycenterPaneformat(centerPane, modifyphoto);
        VBox box = setmodifycontentbox(centerPane);
        modifyPane.getChildren().add(box);
        modifyPane.setAlignment(Pos.CENTER);
        // Ivy end
        return modifyPane;
    }

    private Button setmodifyphotobutton(String s) {
        // Ivy start
        Button b = new Button(s);
        // Ivy end
        return b;
    }

    private PasswordField setpwField() {
        // Ivy start
        PasswordField p = new PasswordField();
        p.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        p.setPrefWidth(250);
        // Ivy end
        return p;
    }

    private TextField setTextField() {
        // Ivy start
        TextField t = new TextField();
        t.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        t.setPrefWidth(250);
        // Ivy end
        return t;
    }

    private ComboBox setroolcombobox() {
        // Ivy start
        ObservableList<String> role = FXCollections.observableArrayList("請選擇", "老師", "學生");
        ComboBox c = new ComboBox<String>(role);
        c.setValue("請選擇");
        c.setMinSize(100, 30);
        // Ivy end
        return c;
    }

    private Button setsentbutton(String s,int tmp) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a)->{
            centerPane.getChildren().remove(tmp);
            count--;
        });
        // Ivy end
        return b;
    }

    private Button setbackbutton(String s,int tmp) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
//            Stage stage = (Stage) b.getScene().getWindow();
//            stage.close();
            centerPane.getChildren().remove(tmp);
            count--;
        });
        // Ivy end
        return b;
    }

    private HBox setmodifybuttonbox(Button s, Button b) {
        // Ivy start
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.getChildren().addAll(s, b);
        // Ivy end
        return hb;
    }

    private void add2modifycenterPane(GridPane centerPane, Text sceneTitleText, Button modifyphoto, Text pwText, PasswordField rpasswordField, Text nameText, TextField rnameTextField, Text roleText, ComboBox roletool, Text gradeText, TextField rgradeTextField, Text mailText, TextField rmailTextField, HBox modifybuttonbox) {
        // Ivy start
        centerPane.add(sceneTitleText, 0, 0, 6, 1);
        centerPane.add(modifyphoto, 0, 1, 6, 1);
        centerPane.add(pwText, 2, 3);
        centerPane.add(rpasswordField, 3, 3);
        centerPane.add(nameText, 2, 4);
        centerPane.add(rnameTextField, 3, 4);
        centerPane.add(roleText, 2, 5);
        centerPane.add(roletool, 3, 5);
        centerPane.add(gradeText, 2, 6);
        centerPane.add(rgradeTextField, 3, 6);
        centerPane.add(mailText, 2, 7);
        centerPane.add(rmailTextField, 3, 7);
        centerPane.add(modifybuttonbox, 3, 8);
        // Ivy end
    }

    private VBox setmodifycontentbox(GridPane centerPane) {
        // Ivy start
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(centerPane);
        
        // Ivy end
        return box;
    }

    private void setmodifycenterPaneformat(GridPane centerPane, Button modifyphoto) {
        // Ivy start
        centerPane.setGridLinesVisible(true);
        centerPane.setVgap(10);
        centerPane.setHgap(10);
        centerPane.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setHalignment(modifyphoto, HPos.CENTER);
        centerPane.setMinSize(415, 425);
        centerPane.setMaxSize(415, 425);
        centerPane.setPadding(new Insets(10,10,10,10));
        // Ivy end
    }

    private TableColumn setNumColumn(String s) {
        // Ivy start
        TableColumn NumCol = new TableColumn(s);
        NumCol.setPrefWidth(50);
        NumCol.setStyle("-fx-alignment: CENTER;");
        NumCol.setCellValueFactory(new PropertyValueFactory<Test, String>("num"));
        // Ivy end
        return NumCol;
    }

    private TableColumn setTestColumn(String s) {
        // Ivy start
        TableColumn TestCol = new TableColumn(s);
        TestCol.setPrefWidth(150);
        TestCol.setStyle("-fx-alignment: CENTER;");
        TestCol.setCellValueFactory(new PropertyValueFactory<Test, String>("testname"));
        // Ivy end
        return TestCol;
    }

    private TableColumn setTimeColumn(String s) {
        // Ivy start
        TableColumn ScoreCol = new TableColumn(s);
        ScoreCol.setPrefWidth(50);
        ScoreCol.setStyle("-fx-alignment: CENTER;");
        ScoreCol.setCellValueFactory(new PropertyValueFactory<Test, String>("score"));
        // Ivy end
        return ScoreCol;
    }

    private TableColumn setScoreColumn(String s) {
        // Ivy start
        TableColumn NumCol = new TableColumn(s);
        NumCol.setPrefWidth(50);
        NumCol.setStyle("-fx-alignment: CENTER;");
        NumCol.setCellValueFactory(new PropertyValueFactory<Test, String>("num"));
        // Ivy end
        return NumCol;
    }
    
    /**
     * @author Christine
     * take the picture of the screen
     */
    private void startScreenShot() {
        image = new ImageProcessor();
        // main code
//        Thread imgThread = new Thread( image );
//        imgThread.start();
        application.execute(image);
    }
    
    /**
    * set name of the program
    * @param finishbtn 
    */
    private void setFileName(Button finishbtn) {
        if (isExist) {
            TextInputDialog textInputDialog = new TextInputDialog(".java");
            textInputDialog.setTitle("設定檔名");
            textInputDialog.setHeaderText(null);
            textInputDialog.setContentText("請輸入您的檔名:");
            Optional<String> opt = textInputDialog.showAndWait();
            fileName = opt.toString();
            
            isExist = false;
        }
    }
    
    /**
     * Client send message to Server 
     * @param textArea
     * @param isSaved 
     */
    private void sendMsg(TextArea textArea, boolean isSaved) {
        msg = textArea.getText();
        Client_send cs = this.client.getSend();
        cs.run(msg); //Client send answer sheet to Server

        if (isSaved) {
            Server_Rec sr = this.server.getRec();
            sr.saveMsg(fileName);
        }
    }

    /**
     * print the result of compile and execute
     * @param cmdButton 
     */
    private void showCommandAlert(Button cmdButton) {
        Alert alert;
        String file_name = fileName;
        String file_path = "/Users/christine/NetBeansProjects/SecondEdition/";

        JavaExecLab exec = new JavaExecLab(file_name, file_path);

        String result = exec.printResult();

        alert = new Alert(AlertType.NONE, null, ButtonType.CLOSE);

        alert.setTitle("Result");
        alert.setHeaderText("Output");
        alert.setContentText(result);
        alert.show();

        if (alert.getResult() == ButtonType.CLOSE) {
            Stage s = (Stage) cmdButton.getScene().getWindow();
            s.close();
        }
    }
    
    /**
     * add chatBox to the list
     * @return 
     */
    private List<HBox> addChatList() {
        chatList.add(settalkbox(setphotoLabel("ppp"), setshowchatText(msg)));

        return chatList;
    }

    /**
     * print the chatList
     * @return 
     */
    private FlowPane printChatHistory() {
        FlowPane flowPane = new FlowPane();
        for (HBox chat : chatList) {
            flowPane.getChildren().add(chat);
        }
        chatScroll.setContent(flowPane);

        return flowPane;
    }
    
    /**
     * save the data to test program
     */
    public void saveTestValue() {
        GroupFile dataFile;
        for(int i=0; i<dataList.size(); i++) {
            dataFile = new GroupFile("Data", "data"+(i+1), ".txt");
            dataFile.createNewFile();
            try {
                dataFile.writeToFile(dataList.get(i).toString());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
    
}
