package secondedition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafx.application.Application.launch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ivy
 */
public class Login extends Application {

    private BorderPane root = new BorderPane();
    private StackPane stackPane = new StackPane();
    private Test_server server = new Test_server();

    /**
     * Generate a login interface, the size is 1024*668, background color is
     * #000000, the top is @addtop(); the center is @addcenter();
     */
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Log in - 0");
        Scene s = new Scene(root, 1024, 668);
        primaryStage.setScene(s);
        root.setStyle("-fx-background-color: #000000;");
        root.setTop(addtop());
        root.setCenter(addcenter());
        primaryStage.show();
        
        // christine
        server.start();
    }

    /**
     * split the top up into three part, the left is null, the center is time,
     * the right is power button,
     *
     * @param setdate() set time function,
     * @param addpower() include exitButton and cancelButton, then put them in a
     * HBox,
     * @param add2topPane() put time and @powerbox to @topPane,
     * @return GridPane,
     */
    private GridPane addtop() {
        // Ivy start
        GridPane topPane = new GridPane();
        Text time = setdate();
        HBox powerbox = addpower();
        Button powerBtn = setpowerbutton(powerbox);
        add2topPane(topPane, time, powerBtn);
        settopPaneformat(topPane, time, powerBtn);
        // Ivy end
        return topPane;
    }

    /**
     * set the time now, but it is static now should be change to dynamic,
     *
     * @return Text,
     */
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

    private HBox addpower() {
        // Ivy start
        GridPane powerPane = setpowerPaneformat();
        Button exitBtn = setexitbutton();
        Button cancelBtn = setcancelbutton();
        add2powerPane(powerPane, exitBtn, cancelBtn);
        HBox powerbox = setpowerbox(powerPane);
        return powerbox;
    }

    private GridPane setpowerPaneformat() {
        // Ivy start
        GridPane g = new GridPane();
        g.setHgap(10);
        g.setVgap(10);
        g.setStyle("-fx-background-color: #DDDDDD;");
        g.setMaxSize(300, 120);
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setAlignment(Pos.CENTER);
        g.setGridLinesVisible(true);
        // Ivy end
        return g;
    }

    /**
     * if you click the exitButton, the System will be closed
     *
     * @return Button
     */
    private Button setexitbutton() {
        // Ivy start
        Button exitBtn = new Button("結束");
        exitBtn.setFont(Font.font("新細明體", FontWeight.NORMAL, 30));
        exitBtn.setOnAction((a) -> {
            Platform.exit();
        });
        // Ivy end
        return exitBtn;
    }

    private Button setcancelbutton() {
        // Ivy start
        Button cancelBtn = new Button("取消");
        cancelBtn.setFont(Font.font("新細明體", FontWeight.NORMAL, 30));
        cancelBtn.setOnAction((a) -> {
            stackPane.getChildren().remove(1);
        });
        // Ivy end
        return cancelBtn;
    }

    private Button setpowerbutton(HBox b) {
        // Ivy start
        Button powerBtn = new Button("on/off");
        powerBtn.setOnAction((a) -> {
            if (stackPane.getChildren().size() == 1) {
                stackPane.getChildren().add(b);
            }
        });
        powerBtn.setFont(Font.font("新細明體", FontWeight.BOLD, 20));
        // Ivy end
        return powerBtn;
    }

    private void add2powerPane(GridPane g, Button e, Button c) {
        // Ivy start
        g.add(e, 0, 0);
        g.add(c, 1, 0);
        // Ivy end
    }

    private HBox setpowerbox(GridPane g) {
        // Ivy start
        HBox b = new HBox(10);
        b.getChildren().add(g);
        b.setAlignment(Pos.CENTER);
        // Ivy end
        return b;
    }

    private void add2topPane(GridPane g, Text t, Button b) {
        // Ivy start
        g.add(t, 1, 0);
        g.add(b, 2, 0);
        // Ivy end
    }

    private void settopPaneformat(GridPane g, Text t, Button b) {
        // Ivy start
        g.setPrefHeight(30);
        g.setGridLinesVisible(true);
        g.getColumnConstraints().add(new ColumnConstraints(256));
        g.getColumnConstraints().add(new ColumnConstraints(510));
        g.getColumnConstraints().add(new ColumnConstraints(250));
        GridPane.setHalignment(t, HPos.CENTER);
        GridPane.setHalignment(b, HPos.RIGHT);
        GridPane.setValignment(g, VPos.CENTER);
        // Ivy end
    }

    /**
     * @param loginBtnbox include @okBtn,@registerBtn,@forgetBtn
     * @param centerbox put the whole centerPane into @centerbox then add
     * @centerbox into @stackPane
     * @return StackPane
     */
    private StackPane addcenter() {
        // Ivy start
        GridPane centerPane = new GridPane();
        setcenterPaneformat(centerPane);
        Text sceneTitleText = setSceneTitle("  Welcome\n         線上測驗系統");
        Text idText = setText("   帳號:");
        TextField idTextField = setTextField();
        Text pwText = setText("   密碼 :");
        PasswordField passwordField = setPWField();
        Button okBtn = setokButton("登入", idTextField);
        Button registerBtn = setRegisterButton("註冊");
        Button forgetBtn = setForgetButton("忘記密碼");
        HBox loginBtnbox = setLoginBtnBox(registerBtn, forgetBtn, okBtn);
        add2LoginCenterPane(centerPane, sceneTitleText, idText, idTextField, pwText, passwordField, loginBtnbox);
        HBox centerbox = setCenterBox(centerPane);
        stackPane.getChildren().add(centerbox);
        // Ivy end
        return stackPane;
    }

    private GridPane setcenterPaneformat(GridPane g) {
        // Ivy start
        g.setMaxSize(400, 360);
        g.setGridLinesVisible(true);
        g.setVgap(30);
        g.setHgap(10);
        g.setStyle("-fx-background-color: #AAAAAA;");
        // Ivy end
        return g;
    }

    private Text setSceneTitle(String s) {
        // Ivy start
        Text t = new Text(s);
        t.setFont(Font.font("新細明體", FontWeight.BOLD, 45));
        // Ivy end
        return t;
    }

    private Text setText(String s) {
        // Ivy start
        Text t = new Text(s);
        t.setFont(Font.font("新細明體", FontWeight.NORMAL, 30));
        // Ivy end
        return t;
    }

    private TextField setTextField() {
        // Ivy start
        TextField t = new TextField();
        t.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        t.setPrefWidth(250);
        // Ivy end
        return t;
    }

    private PasswordField setPWField() {
        // Ivy start
        PasswordField p = new PasswordField();
        p.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        p.setPrefWidth(250);
        // Ivy end
        return p;
    }

    private Button setokButton(String s, TextField t) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
        b.setOnAction((a) -> {
            okClicked(b, t);
        });
        // Ivy end
        return b;
    }

    private Button setRegisterButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
        FlowPane registerPane = addregister();
        b.setOnAction((a) -> {
            stackPane.getChildren().add(registerPane);
            //root.setCenter(registerPane);
        });
        // Ivy end
        return b;
    }

    private void okClicked(Button b, TextField t) {
        // Ivy start
        System.out.println(" " + t.getText() + " ");
        if (t.getText().equals("1")) {
            Sdesktop s = new Sdesktop(server, t.getText());
            Stage stage = (Stage) b.getScene().getWindow();
            stage.close();
        } else if (t.getText().equals("2")) {
            TDesktop d = new TDesktop(server, t.getText());
            Stage stage = (Stage) b.getScene().getWindow();
            stage.close();
        }
        // Ivy end
    }

    private Button setForgetButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
        FlowPane forgetPane = addForgetPane();
        b.setOnAction((a) -> {
            stackPane.getChildren().add(forgetPane);
            //root.setCenter(forgetPane);
        });
        // Ivy end
        return b;
    }

    private HBox setLoginBtnBox(Button registerBtn, Button forgetBtn, Button okBtn) {
        // Ivy start
        HBox b = new HBox(20);
        b.setAlignment(Pos.BOTTOM_CENTER);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.getChildren().addAll(registerBtn, forgetBtn, okBtn);
        // Ivy end
        return b;
    }

    private void add2LoginCenterPane(GridPane centerPane, Text sceneTitleText, Text idText, TextField idTextField, Text pwText, PasswordField passwordField, HBox loginBtnbox) {
        // Ivy start
        centerPane.add(sceneTitleText, 0, 0, 6, 1);
        centerPane.add(idText, 2, 1);
        centerPane.add(idTextField, 3, 1);
        centerPane.add(pwText, 2, 2);
        centerPane.add(passwordField, 3, 2);
        centerPane.add(loginBtnbox, 0, 3, 7, 1);
        // Ivy end
    }

    private HBox setCenterBox(GridPane g) {
        // Ivy start
        HBox b = new HBox(10);
        b.setAlignment(Pos.CENTER);
        b.setPadding(new Insets(20, 20, 20, 20));
        b.getChildren().add(g);
        // Ivy end
        return b;
    }

    /**
     * new a register interface which is a FlowPane
     *
     * @return FlowPane
     */
    private FlowPane addregister() {
        // Ivy start
        FlowPane p = new FlowPane();

        GridPane centerPane = new GridPane();
        Text sceneTitleText = setSceneTitle("  會員註冊 -0.2");
        Text ridText = setText("    帳號 ：");
        TextField ridTextField = setTextField();
        Text pwText = setText("    密碼 ：");
        PasswordField rpasswordField = setPWField();
        Text nameText = setText("    姓名 ：");
        TextField rnameTextField = setTextField();
        Text roleText = setText("    身份 ：");
        ComboBox roletool = setRoolComboBox();
        Text gradeText = setText("    年班 ：");
        TextField rgradeTextField = setTextField();
        Text mailText = setText("    E-MAIL ：");
        TextField rmailTextField = setTextField();
        Text photoText = setText("    大頭貼  ：");
        TextField rphotoTextField = setTextField();
        Button searchbtn = setearchbuBtton("...");
        HBox photobox = setPhotoBox(rphotoTextField, searchbtn);
        Button sentBtn = setSentButton("送出");
        Button backBtn = setBackButton("返回");
        HBox btnbox = setRegisterButtonbox(sentBtn, backBtn);
        add2RegisterCenterPane(centerPane, sceneTitleText, ridText, ridTextField, pwText, rpasswordField, nameText, rnameTextField, roleText, roletool, gradeText, rgradeTextField, mailText, rmailTextField, photoText, photobox, btnbox);
        setRegisterCenterPaneformat(centerPane);

        HBox registerbox = setRegisterBox(centerPane);
        p.setAlignment(Pos.CENTER);
        p.getChildren().add(registerbox);
        // Ivy end
        return p;
    }

    private GridPane setRegisterCenterPaneformat(GridPane g) {
        // Ivy start
        g.setGridLinesVisible(true);
        g.setVgap(10);
        g.setHgap(10);
        g.setStyle("-fx-background-color: #AAAAAA;");
        // Ivy end
        return g;
    }

    private ComboBox setRoolComboBox() {
        // Ivy start
        ObservableList<String> role = FXCollections.observableArrayList("請選擇", "老師", "學生");
        ComboBox c = new ComboBox<String>(role);
        c.setValue("請選擇");
        c.setMinSize(100, 30);
        // Ivy end
        return c;
    }

    private HBox setPhotoBox(TextField t, Button b) {
        // Ivy start
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().addAll(t, b);
        // Ivy end
        return hb;
    }

    private Button setearchbuBtton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        // Ivy end
        return b;
    }

    private Button setSentButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        return b;
    }

    private Button setBackButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            backClicked();
        });
        // Ivy end
        return b;
    }

    private HBox setRegisterButtonbox(Button s, Button b) {
        // Ivy start
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.getChildren().addAll(s, b);
        // Ivy end
        return hb;
    }

    private void add2RegisterCenterPane(GridPane g, Text sceneTitleText, Text ridText, TextField ridTextField, Text pwText, PasswordField rpasswordField, Text nameText, TextField rnameTextField, Text roleText, ComboBox roletool, Text gradeText, TextField rgradeTextField, Text mailText, TextField rmailTextField, Text photoText, HBox photobox, HBox btnbox) {
        // Ivy start
        g.add(sceneTitleText, 0, 0, 6, 1);
        g.add(ridText, 2, 1);
        g.add(ridTextField, 3, 1);
        g.add(pwText, 2, 2);
        g.add(rpasswordField, 3, 2);
        g.add(nameText, 2, 3);
        g.add(rnameTextField, 3, 3);
        g.add(roleText, 2, 4);
        g.add(roletool, 3, 4);
        g.add(gradeText, 2, 5);
        g.add(rgradeTextField, 3, 5);
        g.add(mailText, 2, 6);
        g.add(rmailTextField, 3, 6);
        g.add(photoText, 2, 7);
        g.add(photobox, 3, 7);
        g.add(btnbox, 3, 8);
        // Ivy end
    }

    private HBox setRegisterBox(GridPane g) {
        // Ivy start
        HBox b = new HBox(10);
        b.setAlignment(Pos.CENTER);
        b.setPadding(new Insets(20, 20, 20, 20));
        b.getChildren().add(g);
        // Ivy end
        return b;
    }

    /**
     * new a forget interface which is a FlowPane
     *
     * @return FlowPane
     */
    private FlowPane addForgetPane() {
        // Ivy start
        FlowPane bottomPane = new FlowPane();
        bottomPane.setAlignment(Pos.CENTER);
        GridPane forgetcenterPane = new GridPane();
        Text sceneTitleText = setSceneTitle("  忘記密碼 -0.3");
        Text idText = setText("    帳號 ：");
        TextField fidTextField = setTextField();
        Text noticeText = setText("＊＊系統將發送通知信至您的信箱");
        Button fsentBtn = setForgetandSentButton("送出");
        Button fbackBtn = setForgetandBackButton("返回");
        HBox forgetBtnbox = setForgetButtonBox(fsentBtn, fbackBtn);
        add2ForgetCenterPane(forgetcenterPane, sceneTitleText, idText, fidTextField, noticeText, forgetBtnbox);
        setforgetCenterPaneformat(forgetcenterPane, noticeText);
        HBox forgetbox = setForgetBox(forgetcenterPane);
        bottomPane.getChildren().add(forgetbox);
        // Ivy end
        return bottomPane;
    }

    private Button setForgetandSentButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        return b;
    }

    private Button setForgetandBackButton(String s) {
        // Ivy start
        Button b = new Button(s);
        b.setFont(Font.font("新細明體", FontWeight.NORMAL, 20));
        b.setOnAction((a) -> {
            backClicked();
        });
        // Ivy end
        return b;
    }

    private HBox setForgetButtonBox(Button fsentBtn, Button fbackBtn) {
        // Ivy start
        HBox b = new HBox(10);
        b.setAlignment(Pos.BOTTOM_RIGHT);
        b.setPadding(new Insets(10, 10, 10, 10));
        b.getChildren().addAll(fsentBtn, fbackBtn);
        // Ivy end
        return b;
    }

    private void add2ForgetCenterPane(GridPane forgetcenterPane, Text sceneTitleText, Text idText, TextField fidTextField, Text noticeText, HBox forgetBtnbox) {
        // Ivy start
        forgetcenterPane.add(sceneTitleText, 0, 0, 6, 1);
        forgetcenterPane.add(idText, 2, 1);
        forgetcenterPane.add(fidTextField, 3, 1);
        forgetcenterPane.add(noticeText, 2, 2, 2, 1);
        forgetcenterPane.add(forgetBtnbox, 3, 3);
        // Ivy end
    }

    private GridPane setforgetCenterPaneformat(GridPane g, Text t) {
        // Ivy start
        g.setGridLinesVisible(true);
        g.setVgap(30);
        g.setHgap(10);
        g.setStyle("-fx-background-color: #AAAAAA;");
        GridPane.setHalignment(t, HPos.CENTER);
        // Ivy end
        return g;
    }

    private HBox setForgetBox(GridPane forgetcenterPane) {
        // Ivy start
        HBox b = new HBox(10);
        b.setAlignment(Pos.CENTER);
        b.setPadding(new Insets(20, 20, 20, 20));
        b.getChildren().add(forgetcenterPane);
        // Ivy end
        return b;
    }

    private void backClicked() {
        // Ivy start
        stackPane.getChildren().remove(1);
        // Ivy end
    }

    public static void main(String[] args) {
        launch(args);
    }
}
