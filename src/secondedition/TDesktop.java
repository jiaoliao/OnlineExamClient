package secondedition;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
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
import org.controlsfx.control.CheckComboBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ivy
 */
public class TDesktop extends Application {

    private String id, msg;
    private int count = -1;
    private TextArea ansArea, chatArea;
    private ScrollPane chatScroll;
    private BorderPane root = new BorderPane();
    private StackPane centerPane = new StackPane();
    private ObservableList<String> mon = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    private ObservableList<String> day = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
    private ObservableList<String> second = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
    private ObservableList<String> hour = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
    private ObservableList<String> DayandNight = FXCollections.observableArrayList("AM", "PM");
    private ObservableList<String> grade = FXCollections.observableArrayList("105", "106", "107", "108", "109", "110");
    private ObservableList<String> role = FXCollections.observableArrayList("請選擇", "老師", "學生");
    private ObservableList<String> name = FXCollections.observableArrayList("任芝萱", "周意立", "黃莉玲");
    private ObservableList<String> test = FXCollections.observableArrayList("九九乘法表");
    private ObservableList<Test> data = FXCollections.observableArrayList(new Test(1, "任芝萱", "Hello java", "16'40", 90, "perfect"));
    private List<HBox> chatList = new ArrayList<HBox>();
    private List<String> dataList;
    
    private Test_server server;
    private Test_client client;
    private ImageProcessor imgPrcsr = new ImageProcessor();
    
    public TDesktop(Test_server server, String s) {
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
        // Ivy end
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
        Menu[] item = setTestandScoreitem();
        MenuItem[] menuitem = setHistoryandAboutitem();
        menu.getItems().addAll(item);
        menu.getItems().addAll(menuitem);
        // Ivy end
        return menu;
    }

    private Text setmenutitleText(String s) {
        // Ivy start
        Text menutext = new Text(s);
        menutext.setFill(Color.WHITE);
        menutext.setFont(Font.font("新細明體", FontWeight.BOLD, 20));
        // Ivy end
        return menutext;
    }

    private Text setText(String s) {
        // Ivy start
        Text t = new Text(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        // Ivy end
        return t;
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

    private Menu[] setTestandScoreitem() {
        // Ivy start
        Menu[] item = new Menu[2];
        item[0] = setTestItem();
        item[1] = setScoreItem();
        // Ivy end
        return item;
    }

    private Menu setTestItem() {
        // Ivy start
        Menu m = new Menu();
        Text t = setText("測驗");
        m.setGraphic(t);
        MenuItem[] item = setTest();
        m.getItems().addAll(item);
        // Ivy end
        return m;
    }

    private MenuItem[] setTest() {
        // Ivy start
        MenuItem[] m = new MenuItem[2];
        m[0] = setnewtest();
        m[1] = settest();
        // Ivy end
        return m;
    }

    private MenuItem setnewtest() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("新增考試");
        m.setGraphic(t);
        m.setOnAction((a) -> {
            centerPane.getChildren().add(addtest());
//            Stage test = new Stage();
//            Scene s = new Scene(addtest());
//            setstage(test, s, "新增考試 -2.2");
        });
        // Ivy end
        return m;
    }

    private MenuItem settest() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("九九乘法表");
        m.setGraphic(t);
        m.setOnAction((a) -> {
//            Stage test = new Stage();
//            Scene s = new Scene(managetest());
//            setstage(test, s, "九九乘法表 -2.3");
            centerPane.getChildren().add(managetest());
        });
        // Ivy end
        return m;
    }

    private Menu setScoreItem() {
        // Ivy start
        Menu m = new Menu();
        Text t = setText("閱卷");
        m.setGraphic(t);
        MenuItem[] item = ScoreTest();
        m.getItems().addAll(item);
        // Ivy end
        return m;
    }

    private MenuItem[] ScoreTest() {
        // Ivy start
        MenuItem[] m = new MenuItem[1];
        m[0] = setScore();
        // Ivy end
        return m;
    }

    private MenuItem setScore() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("Hello Java");
        m.setGraphic(t);
        m.setOnAction((a) -> {
//            Stage scoring = new Stage();
//            Scene s = new Scene(score());
//            setstage(scoring, s, "閱卷 - Hello Java -2.4");
            centerPane.getChildren().add(score());
        });
        // Ivy end
        return m;
    }

    private MenuItem[] setHistoryandAboutitem() {
        // Ivy start
        MenuItem[] m = new MenuItem[2];
        m[0] = sethistory();
        m[1] = setabout();
        // Ivy end
        return m;
    }

    private MenuItem sethistory() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("歷次測驗");
        m.setGraphic(t);
        m.setOnAction((a) -> {
//            Stage history = new Stage();
//            Scene s = new Scene(searchistory());
//            setstage(history, s, "歷次成績 -2.5");
            centerPane.getChildren().add(searchistory());
        });
        // Ivy end
        return m;
    }

    private MenuItem setabout() {
        // Ivy start
        MenuItem m = new MenuItem();
        Text t = setText("關於我們");
        m.setGraphic(t);
        m.setOnAction((a) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "我們是三個傻瓜！！！");
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("關於我們 -2.6");
            alert.setHeaderText(null);
            alert.showAndWait();
        });
        // Ivy end
        return m;
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
        Text t = setmenutitleText("Ivy-teacher");
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
//            Stage modify = new Stage();
//            Scene s = new Scene(modifydata());
//            setstage(modify, s, "修改個人資料 -2.1");
            centerPane.getChildren().add(modifydata());
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

    private void setstage(Stage test, Scene s, String title) {
        // Ivy start
        test.setTitle(title);
        test.initStyle(StageStyle.UTILITY);
        test.setScene(s);
        test.setResizable(false);
        test.show();
        // Ivy end
    }

    private BorderPane score() {
        // Ivy start
        count++;
        int tmp = count;
        BorderPane scorePane = new BorderPane();
        scorePane.setPrefSize(1000, 600);
        scorePane.setLeft(vmenubar(scorePane, tmp));
        scorePane.setCenter(totalscore(tmp));
        // Ivy end
        return scorePane;
    }

    private ScrollPane vmenubar(BorderPane scorePane, int tmp) {
        // Ivy start
        ScrollPane s = new ScrollPane();
        VBox studentmenu = new VBox();
        setStudentMenuBoxformat(studentmenu);

        Button s1 = setAllButton("ALL", scorePane, tmp);
        Button s2 = setScoringStudentButton("任芝萱", scorePane);
        Button s3 = setScoringStudentButton("周意立", scorePane);
        Button s4 = setScoringStudentButton("黃莉玲", scorePane);

        studentmenu.getChildren().addAll(s1, s2, s3, s4);
        s.setContent(studentmenu);
        // Ivy end
        return s;
    }

    private Button setAllButton(String s, BorderPane scorePane, int tmp) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.BOLD, 20));
        b.setPrefSize(100, 50);
        b.setOnAction((a) -> {
            scorePane.setCenter(totalscore(tmp));
        });
        // Ivy end
        return b;
    }

    private Button setScoringStudentButton(String s, BorderPane scorePane) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.BOLD, 20));
        b.setPrefSize(100, 50);
        b.setOnAction((a) -> {
            scorePane.setCenter(scoring());
        });
        // Ivy end
        return b;
    }

    private void setStudentMenuBoxformat(VBox studentmenu) {
        // Ivy start
        studentmenu.setStyle("-fx-background-color: AAAAAA;");
        studentmenu.setPrefSize(102, 630);
        studentmenu.setFillWidth(true);
        // Ivy end
    }

    private FlowPane totalscore(int tmp) {
        // Ivy start
        FlowPane totalscorePane = new FlowPane();
        Text title = settitleText("成績總覽");
        TableView scoretable = new TableView();
        TableColumn NumCol = setNumColumn("編號");
        TableColumn NameCol = setNameColumn("姓名");
        TableColumn ScoreCol = setScoreColumn("成績");
        TableColumn AdviceCol = setAdviceColumn("評語");
        scoretable.setItems(data);
        scoretable.getColumns().addAll(NumCol, NameCol, ScoreCol, AdviceCol);
        Button back = setTotalScoreBackButton("OK", tmp);
        VBox totalbox = new VBox(50);
        totalbox.getChildren().addAll(title, scoretable, back);
        setTotalBoxformat(totalbox);
        setTotalScorePaneformat(totalscorePane);
        totalscorePane.getChildren().add(totalbox);
        // Ivy end
        return totalscorePane;
    }

    private TableColumn setNumColumn(String s) {
        // Ivy start
        TableColumn NumCol = new TableColumn(s);
        NumCol.setPrefWidth(100);
        NumCol.setStyle("-fx-alignment: CENTER;");
        NumCol.setCellValueFactory(new PropertyValueFactory<Test, String>("num"));
        //Ivy end
        return NumCol;
    }

    private TableColumn setNameColumn(String s) {
        // Ivy start
        TableColumn NameCol = new TableColumn(s);
        NameCol.setPrefWidth(100);
        NameCol.setStyle("-fx-alignment: CENTER;");
        NameCol.setCellValueFactory(new PropertyValueFactory<Test, String>("name"));
        // Ivy end
        return NameCol;
    }

    private TableColumn setScoreColumn(String s) {
        // Ivy start
        TableColumn ScoreCol = new TableColumn(s);
        ScoreCol.setPrefWidth(100);
        ScoreCol.setStyle("-fx-alignment: CENTER;");
        ScoreCol.setCellValueFactory(new PropertyValueFactory<Test, String>("score"));
        //Ivy end
        return ScoreCol;
    }

    private TableColumn setAdviceColumn(String s) {
        // Ivy start
        TableColumn AdviceCol = new TableColumn(s);
        AdviceCol.setPrefWidth(150);
        AdviceCol.setStyle("-fx-alignment: CENTER;");
        AdviceCol.setCellValueFactory(new PropertyValueFactory<Test, String>("advice"));
        // Ivy end
        return AdviceCol;
    }

    private Button setTotalScoreBackButton(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }

    private void setTotalBoxformat(VBox totalbox) {
        // Ivy start
        totalbox.setStyle("-fx-background-color: #DDDDDD;");
        totalbox.setAlignment(Pos.TOP_CENTER);
        totalbox.setPadding(new Insets(20, 20, 20, 20));
        // Ivy end
    }

    private void setTotalScorePaneformat(FlowPane totalscorePane) {
        // Ivy start
        totalscorePane.setAlignment(Pos.CENTER);
        totalscorePane.setStyle("-fx-background-color: #DDDDDD;");
        // Ivy end
    }

    private GridPane scoring() {
        // Ivy start
        GridPane scorePane = new GridPane();
        Text testText = setText("考試時間:");
        Text testTime = setText("2015-11-10 08:00 - 09:00");
        Text screenshootText = setText("    擷取畫面：");
        Text screenTime = setText("30s");
        Text overtestcondition = setText("    提早交卷");
        HBox conditionBox = setScoringConditionBox(testText, testTime, screenshootText, screenTime, overtestcondition);
        Text qtitle = setText("考試題目");
        ScrollPane qscroll = setQuestionScrollPane();
        ScrollPane anscroll = setScoringAnswerScrollPane();
        Button databtn = setdatabutton("設定測值");
        Button commandbtn = setScoringCommandButton("執行");
        HBox testbtnBox = settestbuttonbox(databtn, commandbtn);
        Text phototext = setText("考試畫面 - 按時間排序");
        ScrollPane photoscroll = setScoringphotoScrollPane();
        Text advicetext = setText("評語 :");
        TextArea adviceArea = setScorindAdviceArea();
        HBox advicebox = setScoringAdviceBox(advicetext, adviceArea);
        Text score = setText("成績 :");
        TextField scorefield = setScoreTextField();
        Button ok = setScoringOKButton("送出");
        HBox scorebox = setScoringscoreBox(score, scorefield, ok);

        add2ScoringScorePane(conditionBox, scorePane, qtitle, qscroll, anscroll, testbtnBox, phototext, photoscroll, advicebox, scorebox);
        setscoreingPaneformat(scorePane, qtitle, phototext, scorebox);
        // Ivy end
        return scorePane;
    }

    private HBox setScoringConditionBox(Text testText, Text testTime, Text screenshootText, Text screenTime, Text overtestcondition) {
        //Ivy start
        HBox h = new HBox(10);
        h.setAlignment(Pos.CENTER_LEFT);
        h.setPadding(new Insets(10, 10, 10, 10));
        h.getChildren().addAll(testText, testTime, screenshootText, screenTime, overtestcondition);
        //Ivy end
        return h;
    }

    private ScrollPane setQuestionScrollPane() {
        // Ivy start
        ScrollPane s = new ScrollPane();
        Text question = setText("把一乘到九，由下而上排列\n使用語言：JAVA");
        s.setContent(question);
        // Ivy end
        return s;
    }

    private ScrollPane setScoringAnswerScrollPane() {
        // Ivy start
        ScrollPane s = new ScrollPane();
        s.setPrefSize(445, 555);
        // Ivy end
        
        // Christine start
        ansArea = new TextArea();
        ansArea.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        ansArea.setPrefSize(445, 555);
//        ansArea.setAlignment(Pos.TOP_LEFT);
        ansArea.setText(getContent());
        ansArea.setEditable(false);
        s.setContent(ansArea);
        // Christine end
        return s;
    }

    private Button setdatabutton(String s) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().add(addDataFlowPane());
//            Stage data = new Stage();
//            Scene scene = new Scene(dataPane);
//            setstage(data, scene, "輸入測值 -1.2.1");
//            data.setAlwaysOnTop(true);
        });
        return b;
    }

    private FlowPane addDataFlowPane() {
        count++;
        int tmp = count;
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
        Button OK = setOKButton("送出", tmp);
        HBox h = setDataBox(explain, add, delete, OK);
        data.getChildren().addAll(t, area, h);
        data.setAlignment(Pos.CENTER);
        data.setPadding(new Insets(10, 10, 10, 10));
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
        b.setOnAction(
                (a) -> {
                    centerPane.getChildren().add(setDataExplain());
//                    String content = "一筆測資內，值與值間的分個為enter鍵\n如：1\n2\n3\n為一筆測資內的三個值\n若為1 2 3\n則為一筆測資內的一個值，如需個別取出1,2,3則須自行切割字串";
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.YES);
//                    alert.setTitle("使用說明");
//                    alert.initStyle(StageStyle.UTILITY);
//                    alert.setHeaderText(null);
//                    alert.showAndWait();
                });
        return b;
    }

    private GridPane setDataExplain() {
        count++;
        int tmp = count;
        GridPane explain = new GridPane();
        Text title = setText("使用說明:");
        Text t = setText("一筆測資內，值與值間的分隔為enter鍵\n如：\n1\n2\n3\n為一筆測資內的三個值\n若為1 2 3\n則為一筆測資內的一個值，如需個別取出1,2,3則須自行切割字串");
        Button OK = setExplainOKButton("OK", tmp);
        explain.add(title, 0, 0, 2, 1);
        explain.add(t, 0, 1, 2, 1);
        explain.add(OK, 0, 2, 2, 1);
        setExplainFormat(explain, title, t, OK);
        return explain;
    }

    private Button setExplainOKButton(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }

    private void setExplainFormat(GridPane explain, Text title, Text t, Button OK) {
        explain.setPadding(new Insets(10, 10, 10, 10));
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

    private Button setOKButton(String s, int tmp) {
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

    private HBox setDataBox(Button explain, Button add, Button delete, Button OK) {
        HBox h = new HBox(10);
        h.setPadding(new Insets(20, 20, 20, 20));
        h.getChildren().addAll(explain, add, delete, OK);
        return h;
    }

    private void setDataFlowPaneFormat(FlowPane dataPane) {
        // Ivy start
        dataPane.setAlignment(Pos.CENTER);
        dataPane.setStyle("-fx-background-color: #DDDDDD;");
        dataPane.setPrefSize(350, 610);
        // Ivy end
    }

    private Button setScoringCommandButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        // Ivy end
        
//        Christine start
        b.setOnAction((a) -> {
            showCommandAlert(b);
        });
//        Christine end
        return b;
    }
    
    /**
     * execute the program
     * @author Christine
     *
     * @param cmdButton
     * @param exec
     *
     * Output-Dialog
     */
    private void showCommandAlert(Button cmdButton) {
        Alert alert;
        String file_name = "Test";
        String file_path = "/Users/christine/NetBeansProjects/command/src/command/Test/";
        
        JavaExecLab exec = new JavaExecLab(file_name, file_path);
        
        String result = exec.printResult();

        alert = new Alert(Alert.AlertType.NONE, null, ButtonType.CLOSE);
            
        alert.setTitle("Result");
        alert.setHeaderText("Output");
        alert.setContentText(result);
        alert.show();
        
        if(alert.getResult() == ButtonType.CLOSE){
            Stage s = (Stage) cmdButton.getScene().getWindow();
            s.close();
        }
    }

    private HBox settestbuttonbox(Button databtn, Button commandbtn) {
        // Ivy start
        HBox b = new HBox(10);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.getChildren().addAll(databtn, commandbtn);
        // Ivy end
        return b;
    }

    private ScrollPane setScoringphotoScrollPane() {
        // Ivy start
        ScrollPane s = new ScrollPane();
        s.setContent(getphoto());
        // Ivy end
        return s;
    }

    private TextArea setScorindAdviceArea() {
        // Ivy start
        TextArea t = new TextArea();
        t.setPrefSize(400, 190);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        // Ivy end
        return t;
    }

    private HBox setScoringAdviceBox(Text advicetext, TextArea adviceArea) {
        // Ivy start
        HBox h = new HBox(10);
        h.setAlignment(Pos.CENTER_RIGHT);
        h.setPadding(new Insets(20, 20, 20, 20));
        h.getChildren().addAll(advicetext, adviceArea);
        return h;
    }

    private TextField setScoreTextField() {
        // Ivy start
        TextField t = new TextField();
        t.setPrefWidth(265);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        // Ivy end
        
//        christine
        t.setText("Score");
        t.setEditable(false);
//        christine
        return t;
    }

    private Button setScoringOKButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        // Ivy end
        return b;
    }

    private HBox setScoringscoreBox(Text score, TextField scorefield, Button ok) {
        // Ivy start
        HBox h = new HBox(10);
        h.setAlignment(Pos.CENTER);
        h.setPadding(new Insets(20, 20, 20, 20));
        h.getChildren().addAll(score, scorefield, ok);
        // Ivy end
        return h;
    }

    private void add2ScoringScorePane(HBox conditionBox, GridPane scorePane, Text qtitle, ScrollPane qscroll, ScrollPane anscroll, HBox testbtnBox, Text phototext, ScrollPane photoscroll, HBox advicebox, HBox scorebox) {
        // Ivy start
        scorePane.add(conditionBox, 0, 0, 2, 1);
        scorePane.add(qtitle, 0, 1);
        scorePane.add(qscroll, 0, 2);
        scorePane.add(anscroll, 0, 3, 1, 2);
        scorePane.add(testbtnBox, 0, 5);
        scorePane.add(phototext, 1, 1);
        scorePane.add(photoscroll, 1, 2, 1, 2);
        scorePane.add(advicebox, 1, 4);
        scorePane.add(scorebox, 1, 5);
        // Ivy end
    }

    private void setscoreingPaneformat(GridPane scorePane, Text qtitle, Text phototext, HBox scorebox) {
        // Ivy start
        scorePane.setGridLinesVisible(true);
        scorePane.setPrefSize(898, 598);
        scorePane.getColumnConstraints().add(new ColumnConstraints(460));
        scorePane.getColumnConstraints().add(new ColumnConstraints(460));
        scorePane.getRowConstraints().add(new RowConstraints(40));
        scorePane.getRowConstraints().add(new RowConstraints(40));
        scorePane.getRowConstraints().add(new RowConstraints(100));
        scorePane.getRowConstraints().add(new RowConstraints(194));
        scorePane.getRowConstraints().add(new RowConstraints(198));
        scorePane.getRowConstraints().add(new RowConstraints(60));
        scorePane.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setHalignment(qtitle, HPos.CENTER);
        GridPane.setHalignment(phototext, HPos.CENTER);
        GridPane.setHalignment(scorebox, HPos.CENTER);
        // Ivy end
    }

    private FlowPane addtest() {
        // Ivy start
        count++;
        int tmp = count;
        FlowPane addtestPane = new FlowPane();
        GridPane centerPane = new GridPane();
        Text addText = settitleText("新增考試");
        Text title = setText("考試題目：");
        TextField titleTextField = setTitleTextField();
        Text question = setText("考試題目說明：");
        ScrollPane questionPane = setquestionPane();
        Text command = setText("編譯器指令:");
        ScrollPane commandPane = setcommandPane();
        Text total = setText("考試時間：");
        HBox totalbox = settotalTime();
        Text start = setText("開始時間：");
        HBox startbox = setstartDate();
        Text end = setText("結束時間：");
        HBox endbox = setendDate(totalbox);
//        setEndTimeAction(totalbox, endbox);
        Text gradeText = setText("選擇學生年班：");
        CheckComboBox gradetool = new CheckComboBox<String>(grade);
        gradetool.setPadding(new Insets(5, 5, 5, 18));
        Text studentText = setText("選擇學生：");
        CheckComboBox studenttool = new CheckComboBox<String>(name);
        studenttool.setPadding(new Insets(5, 5, 5, 18));
        Button databtn = setdatabutton("設定測值");
        Button okbutton = setNewTestokButton("確定", tmp);
        Button cancelbutton = setNewTestcancelButton("取消", tmp);
        HBox buttonbox = setNewTestButtonBox(databtn,okbutton, cancelbutton);
        add2TestcenterPane(centerPane, addText, title, titleTextField, question, questionPane, command,commandPane,total, totalbox, start, startbox, end, endbox, gradeText, gradetool, studentText, studenttool, buttonbox);
        setTestcenterPaneformat(centerPane, addText, totalbox, buttonbox);
        addtestPane.getChildren().add(centerPane);
        setAddtestPaneformat(addtestPane);
        // Ivy end
        return addtestPane;
    }

    private Text settitleText(String s) {
        // Ivy start
        Text t = new Text(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 40));
        // Ivy end
        return t;
    }

    private TextField setTitleTextField() {
        // Ivy start
        TextField t = new TextField();
        t.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        t.setPrefSize(100, 30);
        // Ivy end
        return t;
    }

    private ScrollPane setquestionPane() {
        // Ivy start
        ScrollPane s = new ScrollPane();
        TextArea questionTextArea = setquestionTextArea();
        s.setContent(questionTextArea);
        // Ivy end
        return s;
    }

    private TextArea setquestionTextArea() {
        // Ivy start
        TextArea t = new TextArea();
        t.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        t.setPrefSize(425, 60);
        // Ivy end
        return t;
    }

    private ScrollPane setcommandPane() {
        // Ivy start
        ScrollPane s = new ScrollPane();
        TextArea commandTextArea = setcommandTextArea();
        s.setContent(commandTextArea);
        // Ivy end
        return s;
    }
    
    private TextArea setcommandTextArea() {
        // Ivy start
        TextArea t = new TextArea();
        t.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        t.setPrefSize(425, 60);
        // Ivy end
        return t;
    }

    private HBox settotalTime() {
        //Ivy start
        TextField t = new TextField("60");
        t.setPrefSize(50, 30);
        Text test = setText("分");
        HBox h = new HBox(5);
        h.setAlignment(Pos.CENTER_LEFT);
        h.setPadding(new Insets(5, 5, 5, 20));
        h.getChildren().addAll(t, test);
        //Ivy end
        return h;
    }

    private HBox setstartDate() {
        // Ivy start
        Calendar now = Calendar.getInstance();
        ComboBox startmontool = new ComboBox<>(mon);
        startmontool.setValue((now.get(Calendar.MONTH) + 1) + "");
        startmontool.setStyle("-fx-font-size: 12;");
        Text startmontext = setText("月");

        ComboBox startdattool = new ComboBox<>(day);
        startdattool.setValue(now.get(Calendar.DATE) + "");
        startdattool.setStyle("-fx-font-size: 12;");
        Text startdaytext = setText("日");

        ComboBox starthourtool = new ComboBox<>(hour);
        starthourtool.setValue(now.get(Calendar.HOUR) + "");
        starthourtool.setStyle("-fx-font-size: 12;");
        Text starttimetext = setText(":");

        ComboBox startsectool = new ComboBox<>(second);
        startsectool.setValue(now.get(Calendar.MINUTE) + "");
        startsectool.setStyle("-fx-font-size: 12;");

        ComboBox startDayandNighttool = new ComboBox<>(DayandNight);
        if (now.get(Calendar.AM_PM) == 0) {
            startDayandNighttool.setValue("AM");
        } else {
            startDayandNighttool.setValue("PM");
        }
        startDayandNighttool.setStyle("-fx-font-size: 12;");
        HBox startbox = new HBox(5);
        startbox.setAlignment(Pos.CENTER);
        startbox.setPadding(new Insets(5, 5, 5, 5));
        startbox.getChildren().addAll(startmontool, startmontext, startdattool, startdaytext, starthourtool, starttimetext, startsectool, startDayandNighttool);
        // Ivy end
        return startbox;
    }

    private HBox setendDate(HBox totalbox) {
        // Ivy start
        TextField t = (TextField) totalbox.getChildren().get(0);
        int time = Integer.parseInt(t.getText());

        ComboBox endDayandNighttool = new ComboBox<>(DayandNight);
        ComboBox endmontool = new ComboBox<>(mon);
        Text endmontext = setText("月");
        ComboBox enddaytool = new ComboBox<>(day);
        Text enddaytext = setText("日");
        ComboBox endhourtool = new ComboBox<>(hour);
        Text endtimetext = setText(":");
        ComboBox endsectool = new ComboBox<>(second);

        setEndtime(time, endDayandNighttool, endmontool, enddaytool, endhourtool, endsectool);
        setEndBoxStyle(endDayandNighttool, endmontool, enddaytool, endhourtool, endsectool);

        HBox endbox = new HBox(5);
        endbox.setAlignment(Pos.CENTER);
        endbox.setPadding(new Insets(5, 5, 5, 5));
        endbox.getChildren().addAll(endmontool, endmontext, enddaytool, enddaytext, endhourtool, endtimetext, endsectool, endDayandNighttool);
        // Ivy end
        return endbox;
    }

    private void setEndTimeAction(HBox totalbox, HBox endbox) {
        TextField t = (TextField) totalbox.getChildren().get(0);
        t.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")) {
            } else {
                int time = Integer.parseInt(newValue);
                ComboBox endDayandNighttool = (ComboBox) endbox.getChildren().get(7);
                ComboBox endmontool = (ComboBox) endbox.getChildren().get(0);
                ComboBox enddaytool = (ComboBox) endbox.getChildren().get(2);
                ComboBox endhourtool = (ComboBox) endbox.getChildren().get(4);
                ComboBox endsectool = (ComboBox) endbox.getChildren().get(6);
                setEndtime(time, endDayandNighttool, endmontool, enddaytool, endhourtool, endsectool);
            }
        });
    }

    private void setEndtime(int time, ComboBox endDayandNighttool, ComboBox endmontool, ComboBox enddaytool, ComboBox endhourtool, ComboBox endsectool) {
        Calendar now = Calendar.getInstance();
        String con;
        int m = now.get(Calendar.MONTH) + 1;
        int d = now.get(Calendar.DATE);
        int h = now.get(Calendar.HOUR);
        int min = now.get(Calendar.MINUTE);
        if (now.get(Calendar.AM_PM) == 0) {
            con = "AM";
        } else {
            con = "PM";
        }
        if ((min + (time % 60)) > 60) {
            h++;
            if (h + (time / 60) > 13 && con == "AM") {
                endDayandNighttool.setValue("PM");
                endhourtool.setValue((h + (time / 60) - 12) + "");
            } else {
                endhourtool.setValue(h + (time / 60) + "");
                endDayandNighttool.setValue(con);
            }
            endsectool.setValue((min + (time % 60) - 60) + "");
            enddaytool.setValue(d + "");
            endmontool.setValue(m + "");
        } else {
            endsectool.setValue(min + (time % 60) + "");
            if (h + (time / 60) > 13 && con == "AM") {
                endDayandNighttool.setValue("PM");
                endhourtool.setValue((h + (time / 60) - 12) + "");
            } else {
                endhourtool.setValue(h + (time / 60) + "");
                endDayandNighttool.setValue(con);
            }
            endsectool.setValue(min + (time % 60) + "");
            enddaytool.setValue(d + "");
            endmontool.setValue(m + "");
        }
    }

    private void setEndBoxStyle(ComboBox endDayandNighttool, ComboBox endmontool, ComboBox enddaytool, ComboBox endhourtool, ComboBox endsectool) {
        endDayandNighttool.setStyle("-fx-font-size: 12;");
        endmontool.setStyle("-fx-font-size: 12;");
        enddaytool.setStyle("-fx-font-size: 12;");
        endhourtool.setStyle("-fx-font-size: 12;");
        endsectool.setStyle("-fx-font-size: 12;");
    }

    private Button setNewTestokButton(String s, int tmp) {
        // Ivy start
        Button ok = new Button(s);
        ok.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        ok.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        // Ivy end
        return ok;
    }

    private Button setNewTestcancelButton(String s, int tmp) {
        // Ivy start
        Button cancel = new Button(s);
        cancel.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        cancel.setOnAction((a) -> {
//            Stage stage = (Stage) cancel.getScene().getWindow();
//            stage.close();
            centerPane.getChildren().remove(tmp);
            count--;
        });
        // Ivy end
        return cancel;
    }

    private HBox setNewTestButtonBox(Button databtn,Button a, Button b) {
        // Ivy start
        HBox box = new HBox(5);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10, 10, 10, 10));
        box.getChildren().addAll(databtn,a, b);
        // Ivy end
        return box;
    }

    private void add2TestcenterPane(GridPane centerPane, Text addText, Text title, TextField titleTextField, Text question, ScrollPane questionPane,Text command, ScrollPane commandPane, Text total, HBox totalbox, Text start, HBox startbox, Text end, HBox endbox, Text gradeText, CheckComboBox gradetool, Text studentText, CheckComboBox studenttool, HBox buttonbox) {
        // Ivy start
        centerPane.add(addText, 0, 0, 2, 1);
        centerPane.add(title, 0, 1);
        centerPane.add(titleTextField, 1, 1);
        centerPane.add(question, 0, 2);
        centerPane.add(questionPane, 1, 2);
        centerPane.add(command, 0, 3);
        centerPane.add(commandPane, 1, 3);
        centerPane.add(total, 0, 4);
        centerPane.add(totalbox, 1, 4);
        centerPane.add(start, 0, 5);
        centerPane.add(startbox, 1, 5);
        centerPane.add(end, 0, 6);
        centerPane.add(endbox, 1, 6);
        centerPane.add(gradeText, 0, 7);
        centerPane.add(gradetool, 1, 7);
        centerPane.add(studentText, 0, 8);
        centerPane.add(studenttool, 1, 8);
        centerPane.add(buttonbox, 0, 9, 2, 1);
        // Ivy end
    }

    private void setTestcenterPaneformat(GridPane centerPane, Text addText, HBox totalbox, HBox buttonbox) {
        // Ivy start
        centerPane.setGridLinesVisible(true);
        centerPane.setVgap(20);
        centerPane.setHgap(10);
        centerPane.setStyle("-fx-background-color: #DDDDDD;");
        centerPane.getColumnConstraints().add(new ColumnConstraints(150));
        centerPane.getColumnConstraints().add(new ColumnConstraints(425));
        GridPane.setHalignment(addText, HPos.CENTER);
        GridPane.setHalignment(totalbox, HPos.LEFT);
        GridPane.setHalignment(buttonbox, HPos.RIGHT);
        // Ivy end
    }

    private void setAddtestPaneformat(FlowPane addPane) {
        // Ivy start
        addPane.setAlignment(Pos.CENTER);
        addPane.setMinSize(600, 625);
        addPane.setMaxSize(600, 625);
        addPane.setStyle("-fx-background-color: #DDDDDD;");
        // Ivy end
    }

    private GridPane managetest() {
        // Ivy start
        count++;
        int tmp = count;
        GridPane testPane = new GridPane();
        FlowPane facephotoPane = new FlowPane(20, 20);

        Text testText = setText("考試時間:");
        Text testTime = setText("2015-11-10 08:00 - 09:00");
        Text screenshootText = setText(" 擷取畫面：");
        Text screenTime = setText("30s");
        Button close = setManageCloseButton("關閉視窗", tmp);
        HBox conditionBox = setTestConditionBox(testText, testTime, screenshootText, screenTime, close);

        Button suspendBtn = setSuspendButton("暫停");
        Button stopBtn = setStopButton("結束");
        Button manageBtn = setManageButton("管理");
        Text qtitle = setText("考試題目");
        TextArea question = setQuestionTextArea("把一乘到九，由下而上排列\n使用語言：JAVA");
        ScrollPane qscroll = setQuestionScrollPanePane(question);
        Button editbtn = setManageEditButton("編輯", question);
        ScrollPane anscroll = setManageAnswerScrollPane();

        Text studenttitle = setText("考生");
        ScrollPane studentscroll = setTestStudentScroll();
        Button newbtn = setManageNewButton("新增");
        Button delbtn = setManageDeleteButton("刪除", newbtn, studentscroll, facephotoPane, studenttitle);
        Button person1 = setPerson1Button("A", newbtn, delbtn, studentscroll, studenttitle);
        add2facephotoPane(person1, facephotoPane);
        studentscroll.setContent(facephotoPane);
        Text chatitle = setText("討論區");
        ScrollPane chatscroll = setChatScroll();
        TextArea chatTextField = setchatTextArea();
        Button messageButton = setMessageButton("留言");
        HBox chatBox = setChatBox(chatTextField, messageButton);

        add2managetestPane(testPane, conditionBox, suspendBtn, stopBtn, manageBtn, qtitle, qscroll, editbtn, anscroll, studenttitle, newbtn, delbtn, studentscroll, chatitle, chatscroll, chatBox);
        setfacephotoPaneformat(facephotoPane);
        setAddtestPaneformat(testPane, conditionBox, suspendBtn, stopBtn, manageBtn, qtitle, editbtn, studenttitle, newbtn, delbtn, chatitle);
        // Ivy end
        return testPane;
    }

    private Button setManageCloseButton(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 10));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }

    private HBox setTestConditionBox(Text testText, Text testTime, Text screenshootText, Text screenTime, Button close) {
        HBox h = new HBox(10);
        h.setAlignment(Pos.CENTER_LEFT);
        h.setPadding(new Insets(10, 10, 10, 10));
        h.getChildren().addAll(testText, testTime, screenshootText, screenTime, close);
        return h;
    }

    private Button setSuspendButton(String s) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 10));
        b.setOnAction((a) -> {
            if (b.getText() == s) {
                b.setText("繼續");
            } else {
                b.setText(s);
            }
        });
        return b;
    }

    private Button setStopButton(String s) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 10));
        b.setOnAction((a) -> {
            centerPane.getChildren().add(setStopAlert());
        });
        return b;
    }

    private GridPane setStopAlert() {
        // Ivy start
        count++;
        int tmp = count;
        GridPane alert = new GridPane();
        Text t = setText("您確定要提早結束考試？\n");
        Button yes = setYesBtn("YES", tmp);
        Button no = setNoBtn("NO", tmp);
        alert.add(t, 0, 0, 2, 1);
        alert.add(yes, 0, 1);
        alert.add(no, 1, 1);
        setAlertFormat(alert, t, yes, no);
        return alert;
    }

    private Button setYesBtn(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }

    private Button setNoBtn(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }

    private void setAlertFormat(GridPane alert, Text t, Button yes, Button no) {
        alert.setPadding(new Insets(10, 10, 10, 10));
        alert.getColumnConstraints().add(new ColumnConstraints(95));
        alert.getColumnConstraints().add(new ColumnConstraints(95));
        alert.setMinSize(230, 120);
        alert.setMaxSize(230, 120);
        alert.setGridLinesVisible(true);
        alert.setVgap(10);
        alert.setHgap(30);
        alert.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setValignment(t, VPos.CENTER);
        GridPane.setHalignment(yes, HPos.CENTER);
        GridPane.setHalignment(no, HPos.CENTER);
    }

    private Button setManageButton(String s) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 10));
        b.setOnAction((a) -> {
            centerPane.getChildren().add(ResetTest());
//            Stage resettest = new Stage();
//            Scene scene = new Scene(ResetTest());
//            setstage(resettest, scene, "管理考試 -2.3.1");
        });
        return b;
    }

    private FlowPane ResetTest() {
        count++;
        int tmp = count;
        FlowPane resetPane = new FlowPane();
        resetPane.setStyle("-fx-background-color: #DDDDDD;");
        GridPane g = new GridPane();
        Text title = setText("擷取畫面時間:");
        TextField time = setTimeTextField("30");
        Button okBtn = setResetOKButton("送出", tmp);
        Button cancelBtn = setResetCancelButton("取消", tmp);
        add2GridPane(g, title, time, okBtn, cancelBtn);
        setgformat(g, okBtn, cancelBtn);
        resetPane.getChildren().add(g);
        resetPane.setAlignment(Pos.CENTER);
        resetPane.setMinSize(230, 130);
        resetPane.setMaxSize(230, 130);
        return resetPane;
    }

    private TextField setTimeTextField(String s) {
        TextField t = new TextField(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        t.setPrefSize(30, 30);
        return t;
    }

    private Button setResetOKButton(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }

    private Button setResetCancelButton(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }

    private void add2GridPane(GridPane g, Text title, TextField time, Button okBtn, Button cancelBtn) {
        g.add(title, 0, 0);
        g.add(time, 1, 0);
        g.add(okBtn, 0, 1);
        g.add(cancelBtn, 1, 1);
    }

    private void setgformat(GridPane g, Button okBtn, Button cancelBtn) {
        g.setGridLinesVisible(true);
        g.setVgap(10);
        g.setHgap(10);
        g.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setHalignment(okBtn, HPos.CENTER);
        GridPane.setHalignment(cancelBtn, HPos.CENTER);
    }

    private TextArea setQuestionTextArea(String s) {
        // Ivy start
        TextArea t = new TextArea(s);
        t.setEditable(false);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        // Ivy end
        return t;
    }

    private ScrollPane setQuestionScrollPanePane(TextArea question) {
        // Ivy start
        ScrollPane s = new ScrollPane();
        s.setContent(question);
        // Ivy end
        return s;
    }

    private Button setManageEditButton(String s, TextArea question) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 10));
        b.setOnAction((a) -> {
            if (question.isEditable() == true) {
                question.setEditable(false);
                b.setText("編輯");
            } else {
                question.setEditable(true);
                b.setText("確認");
            }
        });
        // Ivy end
        return b;
    }

    private ScrollPane setTestStudentScroll() {
        // Ivy start
        ScrollPane s = new ScrollPane();
        // Ivy end
        return s;
    }

    private Button setManageNewButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 10));
        b.setOnAction((a) -> {
            centerPane.getChildren().add(NewStudent());
        });
        // Ivy end
        return b;
    }

    private GridPane NewStudent() {
        //Ivy start
        count++;
        int tmp = count;
        GridPane newstudentPane = new GridPane();
        Text gradeText = setText("選擇學生年班：");
        CheckComboBox gradetool = new CheckComboBox<String>(grade);
        Text studentText = setText("選擇學生：");
        CheckComboBox studenttool = new CheckComboBox<String>(name);
        Button newBtn = setnewButton("OK", tmp);
        Button cancelBtn = seetcancelButton("Cancel", tmp);
        HBox newbuttonbox = setnewButtonBox(newBtn, cancelBtn);
        add2newstudentPane(newstudentPane, gradeText, gradetool, studentText, studenttool, newbuttonbox);
        setnewstudentformat(newstudentPane);
        //Ivy end
        return newstudentPane;
    }

    private Button setnewButton(String s, int tmp) {
        //Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        //Ivy end
        return b;
    }

    private Button seetcancelButton(String s, int tmp) {
        //Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        //Ivy end
        return b;
    }

    private HBox setnewButtonBox(Button newBtn, Button cancelBtn) {
        //Ivy start
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.getChildren().addAll(newBtn, cancelBtn);
        //Ivy end
        return hb;
    }

    private void add2newstudentPane(GridPane newstudentPane, Text gradeText, CheckComboBox gradetool, Text studentText, CheckComboBox studenttool, HBox newbuttonbox) {
        //Ivy start
        newstudentPane.add(gradeText, 0, 0);
        newstudentPane.add(gradetool, 1, 0);
        newstudentPane.add(studentText, 0, 1);
        newstudentPane.add(studenttool, 1, 1);
        newstudentPane.add(newbuttonbox, 0, 2, 2, 1);
        //Ivy end
    }

    private void setnewstudentformat(GridPane newstudentPane) {
        //Ivy start
        newstudentPane.setMaxSize(275, 200);
        newstudentPane.setAlignment(Pos.CENTER);
        newstudentPane.setGridLinesVisible(true);
        newstudentPane.setVgap(10);
        newstudentPane.setHgap(10);
        newstudentPane.setStyle("-fx-background-color: #DDDDDD;");
        newstudentPane.getColumnConstraints().add(new ColumnConstraints(125));
        newstudentPane.getColumnConstraints().add(new ColumnConstraints(100));
        //Ivy end
    }

    private Button setManageDeleteButton(String s, Button newbtn, ScrollPane studentscroll, FlowPane facephotoPane, Text studenttitle) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 10));
        b.setOnAction((a) -> {
            if (newbtn.isVisible() == false) {
                newbtn.setVisible(true);
                b.setText("刪除");
                studenttitle.setText("考生");
                studentscroll.setContent(facephotoPane);
            }
        });
        // Ivy end
        return b;
    }

    private ScrollPane setManageAnswerScrollPane() {
        // Ivy start
        ScrollPane s = new ScrollPane();
        //Ivy end
        return s;
    }

    private Button setPerson1Button(String s, Button newbtn, Button delbtn, ScrollPane studentscroll, Text studenttitle) {
    // Christine start
        ImageView imageView = new ImageView(new Image("file:/Users/christine/Pictures/student.jpg"));
        imageView.setFitHeight(80);
        imageView.setFitWidth(70);
        
        Button button = new Button("student", imageView);
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
        // Ivy start
        button.setPrefSize(80, 80);
        button.setOnAction((a) -> {
            newbtn.setVisible(false);
            delbtn.setText("返回");
            studenttitle.setText(s);
            studentscroll.setContent(getphoto());
        });
        // Ivy end
        return button;
    }

    private void add2facephotoPane(Button person1, FlowPane facephotoPane) {
        // Ivy start
        facephotoPane.getChildren().add(person1);
        // Ivy end
    }

    private ScrollPane setChatScroll() {
        // Ivy start
        chatScroll = new ScrollPane();
        // Ivy end
        return chatScroll;
    }

    private Label setphotoLabel(String s) {
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
        Text t = new Text(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 16));
        return t;
    }

    private HBox settalkbox(Label photo, Text talk) {
        //Ivy start
        HBox b = new HBox(10);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.setPrefSize(500, 80);
        b.setAlignment(Pos.CENTER_LEFT);
        b.getChildren().addAll(photo, talk);
        //Ivt end
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

    private Button setMessageButton(String s) {
        // Ivy start
        Button messageButton = new Button(s);
        messageButton.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        messageButton.setPrefSize(80, 40);
        //Ivy end
        
//        Christine start
        messageButton.setOnAction((a) -> {
            sendMsg(chatArea);
            chatArea.setText("");
            
            if(msg!="") {
                addChatList();
                printChatHistory();
            }
        });
//        Christine end
        return messageButton;
    }

    private HBox setChatBox(TextArea chatTextField, Button messageButton) {
        // Ivy start
        HBox chatBox = new HBox(10);
        chatBox.setPadding(new Insets(10, 10, 10, 10));
        chatBox.getChildren().addAll(chatTextField, messageButton);
        // Ivy end
        return chatBox;
    }

    private void add2managetestPane(GridPane testPane, HBox conditionBox, Button suspendBtn, Button stopBtn, Button manageBtn, Text qtitle, ScrollPane qscroll, Button editbtn, ScrollPane anscroll, Text studenttitle, Button newbtn, Button delbtn, ScrollPane studentscroll, Text chatitle, ScrollPane chatscroll, HBox chatBox) {
        // Ivy start
        testPane.add(conditionBox, 0, 0, 3, 1);
        testPane.add(suspendBtn, 3, 0);
        testPane.add(stopBtn, 4, 0);
        testPane.add(manageBtn, 5, 0);
        testPane.add(qtitle, 0, 1);
        testPane.add(qscroll, 0, 2, 2, 1);
        testPane.add(editbtn, 1, 1);
        testPane.add(anscroll, 0, 3, 2, 6);
        testPane.add(studenttitle, 2, 1);
        testPane.add(newbtn, 4, 1);
        testPane.add(delbtn, 5, 1);
        testPane.add(studentscroll, 2, 2, 4, 3);
        testPane.add(chatitle, 2, 5);
        testPane.add(chatscroll, 2, 6, 4, 2);
        testPane.add(chatBox, 2, 8, 4, 1);
        // Ivy end
    }

    private void setfacephotoPaneformat(FlowPane facephotoPane) {
        // Ivy start
        facephotoPane.setPadding(new Insets(20, 20, 20, 20));
        facephotoPane.setAlignment(Pos.TOP_LEFT);
        // Ivy end
    }

    private void setAddtestPaneformat(GridPane testPane, HBox conditionBox, Button suspendBtn, Button stopBtn, Button manageBtn, Text qtitle, Button editbtn, Text studenttitle, Button newbtn, Button delbtn, Text chatitle) {
        // Ivy start
        testPane.setGridLinesVisible(true);
        testPane.setPrefSize(1000, 600);
        testPane.getColumnConstraints().add(new ColumnConstraints(465));
        testPane.getColumnConstraints().add(new ColumnConstraints(50));
        testPane.getColumnConstraints().add(new ColumnConstraints(360));
        testPane.getColumnConstraints().add(new ColumnConstraints(50));
        testPane.getColumnConstraints().add(new ColumnConstraints(50));
        testPane.getColumnConstraints().add(new ColumnConstraints(50));
        testPane.getRowConstraints().add(new RowConstraints(40));
        testPane.getRowConstraints().add(new RowConstraints(40));
        testPane.getRowConstraints().add(new RowConstraints(120));
        testPane.getRowConstraints().add(new RowConstraints(73));
        testPane.getRowConstraints().add(new RowConstraints(100));
        testPane.getRowConstraints().add(new RowConstraints(40));
        testPane.getRowConstraints().add(new RowConstraints(60));
        testPane.getRowConstraints().add(new RowConstraints(100));
        testPane.getRowConstraints().add(new RowConstraints(60));
        testPane.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setHalignment(conditionBox, HPos.CENTER);
        GridPane.setHalignment(suspendBtn, HPos.CENTER);
        GridPane.setHalignment(stopBtn, HPos.CENTER);
        GridPane.setHalignment(manageBtn, HPos.CENTER);
        GridPane.setHalignment(qtitle, HPos.CENTER);
        GridPane.setHalignment(editbtn, HPos.CENTER);
        GridPane.setHalignment(studenttitle, HPos.CENTER);
        GridPane.setHalignment(newbtn, HPos.CENTER);
        GridPane.setHalignment(delbtn, HPos.CENTER);
        GridPane.setHalignment(chatitle, HPos.CENTER);
        // Ivy end
    }

    private FlowPane getphoto() {
        // Ivy start
        FlowPane photoPane = new FlowPane(20, 20);
        Button photo1 = setPhotoButton("photo1", "images", "file:/Users/christine/Pictures/card/");
        Button photo2 = setPhotoButton("photo2", "images1", "file:/Users/christine/Pictures/card/");
        photoPane.getChildren().addAll(photo1, photo2);
        photoPaneformat(photoPane);
        // Ivy end
        return photoPane;
    }

    private void photoPaneformat(FlowPane photoPane) {
        // Ivy start
        photoPane.setPadding(new Insets(20, 20, 20, 20));
        photoPane.setAlignment(Pos.TOP_LEFT);
        // Ivy end
    }

    private Button setPhotoButton(String bName, String fileName, String path) {
        // Ivy start
//        Button b = new Button(s);
//        b.setPrefSize(100, 100);
        // Ivy end
        
        // Christine start
        ImageView imageView = new ImageView(new Image(path+fileName+".jpg"));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        
        Button button = new Button(bName, imageView);
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                readImage(fileName, path);
            }
        });
        // Christine end
        return button;
    }
    
    /**
    * 
    * @param fileName
    * @param path 
    */
    private void readImage(String fileName, String path) {
        Alert alert = new Alert(Alert.AlertType.NONE);
            
        alert.setTitle("Display Image");
        alert.show();
        
        if(alert.getResult() == ButtonType.CLOSE){
//            Stage s = (Stage) this.getScene().getWindow();
//            s.close();
        }
    }

    private BorderPane searchistory() {
        // Ivy start
        count++;
        int tmp = count;
        BorderPane searchPane = new BorderPane();
        searchPane.setTop(addchoice(searchPane, tmp));
        setsearchPaneformat(searchPane);
        // Ivy end
        return searchPane;
    }

    private void setsearchPaneformat(BorderPane searchPane) {
        // Ivy start
        searchPane.setMinSize(750, 600);
        searchPane.setMaxSize(750, 600);
        searchPane.setStyle("-fx-background-color: #DDDDDD;");
        // Ivy end
    }

    private GridPane addchoice(BorderPane searchPane, int tmp) {
        // Ivy start
        GridPane choicePane = new GridPane();

        Text gradetext = setText("年班 ：");
        ComboBox gradetool = setgradecombobox();
        Text nametext = setText("姓名 ：");
        ComboBox nametool = setnamecombobox();
        Text testtext = setText("考試題目 ：");
        ComboBox testtool = settestcombobox();
        Button searchbtn = setSearchButton("查詢", searchPane, tmp);

        add2choicePane(choicePane, gradetext, gradetool, nametext, nametool, testtext, testtool, searchbtn);
        setaddchoicePaneformat(choicePane, searchbtn);
        // Ivy end
        return choicePane;
    }

    private ComboBox setgradecombobox() {
        // Ivy start
        ObservableList<String> grade = FXCollections.observableArrayList("請選擇", "105", "106", "107", "108", "109", "110");
        ComboBox c = new ComboBox<String>(grade);
        c.setValue("請選擇");
        c.setMinSize(100, 30);
        // Ivy end
        return c;
    }

    private ComboBox setnamecombobox() {
        // Ivy start
        ObservableList<String> name = FXCollections.observableArrayList("任芝萱", "周意立", "黃莉玲");
        ComboBox c = new ComboBox<String>(name);
        c.setValue("請選擇");
        c.setMinSize(100, 30);
        // Ivy end
        return c;
    }

    private ComboBox settestcombobox() {
        // Ivy start
        ObservableList<String> test = FXCollections.observableArrayList("九九乘法表");
        ComboBox c = new ComboBox<String>(test);
        c.setValue("請選擇");
        c.setMinSize(100, 30);
        return c;
    }

    private ComboBox setrolecombobox() {
        // Ivy start
        ObservableList<String> role = FXCollections.observableArrayList("請選擇", "老師", "學生");
        ComboBox c = new ComboBox<String>(role);
        c.setValue("請選擇");
        c.setMinSize(100, 30);
        // Ivy end
        return c;
    }

    private Button setSearchButton(String s, BorderPane searchPane, int tmp) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction(a -> {
            searchPane.setCenter(addtable(tmp));
        });
        // Ivy end
        return b;
    }

    private void add2choicePane(GridPane choicePane, Text gradetext, ComboBox gradetool, Text nametext, ComboBox nametool, Text testtext, ComboBox testtool, Button searchbtn) {
        // Ivy start
        choicePane.add(gradetext, 0, 0);
        choicePane.add(gradetool, 1, 0);
        choicePane.add(nametext, 2, 0);
        choicePane.add(nametool, 3, 0);
        choicePane.add(testtext, 4, 0);
        choicePane.add(testtool, 5, 0);
        choicePane.add(searchbtn, 6, 0);
        // Ivy end
    }

    private void setaddchoicePaneformat(GridPane choicePane, Button searchbtn) {
        // Ivy start
        choicePane.setGridLinesVisible(true);
        choicePane.setAlignment(Pos.CENTER);
        choicePane.setPrefSize(800, 100);
        choicePane.setHgap(15);
        choicePane.setVgap(10);
        GridPane.setHalignment(searchbtn, HPos.RIGHT);
        // Ivy end
    }

    public ScrollPane addtable(int tmp) {
        // Ivy start
        ScrollPane resultPane = new ScrollPane();
        FlowPane showdata = new FlowPane();
        Button back = setShowDataBackButton("OK", tmp);
        showdata.getChildren().add(back);
        showdata.setAlignment(Pos.CENTER);
        resultPane.setContent(showdata);
        // Ivy end
        return resultPane;
    }

    private Button setShowDataBackButton(String s, int tmp) {
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        return b;
    }

    private FlowPane modifydata() {
        // Ivy start
        count++;
        int tmp = count;
        FlowPane modifyPane = new FlowPane();
        GridPane centerPane = new GridPane();
        Text sceneTitleText = settitleText("修改個人資料");
        Button modifyphoto = setmodifyphotobutton("上傳照片");
        Text pwText = setText("密碼 ：");
        PasswordField rpasswordField = setpwField();
        Text nameText = setText("姓名 ：");
        TextField rnameTextField = setTextField();
        Text roleText = setText("身份 ：");
        ComboBox roletool = setrolecombobox();
        Text gradeText = setText("年班 ：");
        TextField rgradeTextField = setTextField();
        Text mailText = setText("E-MAIL ：");
        TextField rmailTextField = setTextField();
        Button sentBtn = setsentbutton("送出", tmp);
        Button backBtn = setbackbutton("返回", tmp);
        HBox modifybuttonbox = setmodifybuttonbox(sentBtn, backBtn);
        add2modifycenterPane(centerPane, sceneTitleText, modifyphoto, pwText, rpasswordField, nameText, rnameTextField, roleText, roletool, gradeText, rgradeTextField, mailText, rmailTextField, modifybuttonbox);
        setmodifycenterPaneformat(centerPane, modifyphoto);
        VBox box = setmodifycontentbox(centerPane);

        modifyPane.setPrefSize(400, 450);
        modifyPane.getChildren().add(box);
        modifyPane.setAlignment(Pos.CENTER);
        // Ivy end
        return modifyPane;
    }

    private Button setmodifyphotobutton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
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

    private Button setsentbutton(String s, int tmp) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            centerPane.getChildren().remove(tmp);
            count--;
        });
        // Ivy end
        return b;
    }

    private Button setbackbutton(String s, int tmp) {
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
        box.setPadding(new Insets(20, 20, 20, 20));
        box.getChildren().add(centerPane);
        return box;
    }

    private void setmodifycenterPaneformat(GridPane centerPane, Button modifyphoto) {
        // Ivy start
        centerPane.setGridLinesVisible(true);
        centerPane.setVgap(10);
        centerPane.setHgap(10);
        centerPane.setStyle("-fx-background-color: #DDDDDD;");
        GridPane.setHalignment(modifyphoto, HPos.CENTER);
        centerPane.setMinSize(415, 435);
        centerPane.setMaxSize(415, 435);
        centerPane.setPadding(new Insets(10, 10, 10, 10));
        // Ivy end
    }
    
    /**
     * Server get message from Client
     */
    public String getContent() {
        Client_Recv cr = this.client.getRecv();
        Server_sen ss = this.server.getSen();
        cr.run();
        String content = cr.getMsg();
        System.out.println("~Content~"+content);
        
        
        return content;
    }
    
    /**
     * chat to another one
     * @param textArea 
     */
    private void sendMsg(TextArea textArea) {
        msg = textArea.getText();
        Client_send cs = this.client.getSend();
        cs.run(msg); //Client send answer sheet to Server
    }

    /**
    * add content of the chat to List
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
