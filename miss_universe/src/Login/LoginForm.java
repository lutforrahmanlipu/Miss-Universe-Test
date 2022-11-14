package Login;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LoginForm {

    /**
     * @return the username
     */
    public ArrayList<String> getUsername() {
        return username;
    }

    /**
     * @return the userDoneStatusList
     */
    public Map<String, Boolean> getUserDoneStatusList() {
        return userDoneStatusList;
    }

    /**
     * @param userDoneStatusList the userDoneStatusList to set
     */
    public void setUserDoneStatusList(Map<String, Boolean> userDoneStatusList) {
        this.userDoneStatusList = userDoneStatusList;
    }

    /**
     * @return the errMsg
     */
    public Label getErrMsg() {
        return errMsg;
    }

    /**
     * @param errMsg the errMsg to set
     */
    public void setErrMsg(String errMsg) {
        this.errMsg.setText(errMsg);
    }

    /**
     * @return the CountryCombo
     */
    public ComboBox getCountryCombo() {
        return CountryCombo;
    }

    /**
     * @return the usernameCombo
     */
    public ComboBox getUsernameCombo() {
        return usernameCombo;
    }

    /**
     * @return the btnResults
     */
    public Button getBtnResults() {
        return btnResults;
    }

    /**
     * @return the btnContinue
     */
    public Button getBtnContinue() {
        return btnContinue;
    }

    /**
     * @return the mainScene1
     */
    public Scene getLoginScene() {
        return loginScene;
    }

    public boolean getCheckPassword() {
        return checkPassword();
    }

    public Map<String, String> getNamePwList() {
        return namePwList;
    }

    private PasswordField password;
    private TextField txtpass;
    private Button btnExit, btnContinue, btnResults, lButton, rButton;
    private ComboBox CountryCombo, usernameCombo;
    private BorderPane loginPane = new BorderPane();
    private Pane centerBox;
    private Label errMsg = new Label();
    private Scene loginScene;
    private ArrayList<String> username = new ArrayList<String>();
    private Map<String, String> namePwList = new HashMap<>();
    private Map<String, Boolean> userDoneStatusList = new HashMap<>();
    private List<String> pPic = new ArrayList<String>();
    private ImageView picView;
    private int j = 0;

    public LoginForm() {

        //topPane
        Pane topBox = new Pane();
        topBox.setPrefWidth(800);
        topBox.setPrefHeight(100);

        Label lab = new Label("WELCOME TO MISS UNIVERSE QUIZ");
        lab.setFont(Font.font("Showcard Gothic", FontWeight.BOLD, 40));
        lab.setTextFill(Color.WHITE);
        lab.setLayoutX(85);
        lab.setLayoutY(30);

        topBox.getChildren().add(lab);

        loginPane.setTop(topBox);

        //centerPane
        centerBox = new Pane();
        centerBox.setPrefWidth(500);
        centerBox.setPrefHeight(400);

        ImageView Unknown = new ImageView(new Image(getClass().getResource("Images/Anonymous.jpg").toString(), 190, 220, false, true));
        Unknown.setLayoutX(330);
        Unknown.setLayoutY(20);

        Label labelUser = new Label("Name: ");
        labelUser.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        // labelUser.setStyle("-fx-base: green");
        labelUser.setTextFill(Color.WHITE);
        labelUser.setLayoutX(280);
        labelUser.setLayoutY(270);

        usernameCombo = new ComboBox();
        usernameCombo.setLayoutX(340);
        usernameCombo.setLayoutY(270);
        usernameCombo.setValue("Select Username");
        usernameCombo.setPrefWidth(170);
        usernameCombo.setPrefHeight(30);
        usernameCombo.setStyle("-fx-font-family: Impact; -fx-background-color: white;");
        usernameCombo.setOnAction(e -> {
            if (usernameCombo.getValue().equals("TAN JIA CHUN")) {
                Unknown.setImage(null);
                pPic.clear();
                pPic.add("Images/JC1.jpg");
                pPic.add("Images/JC2.jpg");
                pPic.add("Images/JC3.jpg");
            } else if (usernameCombo.getValue().equals("LAU JING EN")) {
                Unknown.setImage(null);
                pPic.clear();
                pPic.add("Images/JE2.jpg");
                pPic.add("Images/JE1.jpg");
                pPic.add("Images/JE3.jpg");
            } else if (usernameCombo.getValue().equals("LUTFOR RAHMAN LIPU")) {
                Unknown.setImage(null);
                pPic.clear();
                pPic.add("Images/LIPU1.jpg");
                pPic.add("Images/LIPU2.jpg");
                pPic.add("Images/LIPU3.jpg");
            } else if (usernameCombo.getValue().equals("LIM JUN YUAN")) {
                Unknown.setImage(null);
                pPic.clear();
                pPic.add("Images/JY1.jpg");
                pPic.add("Images/JY2.jpg");
                pPic.add("Images/JY3.jpg");
            } else if (usernameCombo.getValue().equals("HENG YI YUAN")) {
                Unknown.setImage(null);
                pPic.clear();
                pPic.add("Images/YY1.jpeg");
                pPic.add("Images/YY2.jpeg");
                pPic.add("Images/YY3.jpeg");
            }
            lButton = new Button("");
            lButton.setLayoutX(280);
            lButton.setLayoutY(120);

            Polygon lTriangle = new Polygon();
            lTriangle.getPoints().addAll(new Double[]{
                20.0, 10.0,
                10.0, 15.0,
                20.0, 20.0
            });
            lButton.setStyle("-fx-border-color: cyan ; -fx-border-hover-color: red;-fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
            lButton.setPrefWidth(30);
            lButton.setPrefHeight(30);
            lButton.setShape(lTriangle);
            lButton.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                if (newValue) {
                    lButton.setStyle("-fx-border-color: AFEEEE ; -fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
                } else {
                    lButton.setStyle("-fx-border-color: cyan ; -fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
                }
            });

            rButton = new Button("");
            rButton.setLayoutX(538);
            rButton.setLayoutY(120);

            Polygon rTriangle = new Polygon();
            rTriangle.getPoints().addAll(new Double[]{
                0.0, 10.0,
                10.0, 15.0,
                0.0, 20.0
            });
            rButton.setStyle("-fx-border-color: cyan ;-fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
            rButton.setPrefWidth(30);
            rButton.setPrefHeight(30);
            rButton.setShape(rTriangle);
            rButton.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
                if (newValue) {
                    rButton.setStyle("-fx-border-color: AFEEEE ;-fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
                } else {
                    rButton.setStyle("-fx-border-color: cyan ; -fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
                }
            });

            Image images[] = new Image[pPic.size()];
            for (int i = 0; i < pPic.size(); i++) {
                images[i] = new Image(getClass().getResource(pPic.get(i)).toString());
            }

            picView = new ImageView(images[j]);
            picView.setFitWidth(190);
            picView.setFitHeight(220);

            rButton.setOnAction(f -> {
                j = j + 1;
                if (j == pPic.size()) {
                    j = 0;
                }
                picView.setImage(images[j]);

            });

            lButton.setOnAction(f -> {
                j = j - 1;
                if (j == -1) {
                    j = 2;
                }
                picView.setImage(images[j]);

            });
            HBox slideShow = new HBox();
            slideShow.getChildren().add(picView);
            slideShow.setLayoutX(325);
            slideShow.setLayoutY(20);
            slideShow.setStyle("-fx-border-color: cyan ; -fx-border-width: 5;");

            centerBox.getChildren().addAll(slideShow, lButton, rButton);
        });

        //usernameCombo.getItems().addAll("LUTFOR RAHMAN LIPU","TAN JIA CHUN","LIM JUN YUAN","HENG YI YUAN","LAU JUNG EN");
        Label labelCountry = new Label("Country:");

        labelCountry.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        labelCountry.setTextFill(Color.WHITE);
        labelCountry.setLayoutX(262);
        labelCountry.setLayoutY(310);

        //ComboBox Country
        CountryCombo = new ComboBox();
        CountryCombo.getItems().addAll(
                "NORWAY",
                "OMAN",
                "PANAMA",
                "PAPUA NEW GUINEA",
                "PAKISTAN"
        );
        CountryCombo.setLayoutX(340);
        CountryCombo.setLayoutY(310);
        CountryCombo.setValue("Select Country");
        CountryCombo.setPrefWidth(170);
        CountryCombo.setPrefHeight(30);
        CountryCombo.setStyle("-fx-font-family: Impact; -fx-background-color: white;");

        //Label Password
        Label labelPass = new Label("Password:");
        labelPass.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        labelPass.setTextFill(Color.WHITE);
        labelPass.setLayoutX(247);
        labelPass.setLayoutY(350);

        password = new PasswordField();
        password.setLayoutX(340);
        password.setLayoutY(350);
        password.setPromptText("Enter Password");
        password.setPrefHeight(30);
        password.setPrefWidth(170);
        password.setStyle("-fx-background-color: white;");

        centerBox.getChildren().addAll(Unknown, labelUser, usernameCombo, labelCountry, CountryCombo, labelPass, password);
        loginPane.setCenter(centerBox);

        //bottomPane
        Pane bottomBox = new Pane();
        bottomBox.setPrefWidth(800);
        bottomBox.setPrefHeight(100);

        btnExit = new Button("EXIT");
        btnExit.setLayoutX(300);
        btnExit.setLayoutY(10);
        btnExit.setFont(new Font("Impact", 16));
        btnExit.setStyle("-fx-border-color: #fe2c54 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fe2c54; -fx-background-color: transparent;");
        btnExit.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                btnExit.setStyle("-fx-border-color: #fe2c54 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fe2c54; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                btnExit.setStyle("-fx-border-color: #fe2c54 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fe2c54; -fx-background-color: transparent;");
            }
        });
        btnExit.setPrefHeight(50);
        btnExit.setPrefWidth(100);

        btnExit.setOnAction(e -> {
            System.exit(0);

        });

        btnResults = new Button("Results");
        btnResults.setLayoutX(700);
        btnResults.setLayoutY(40);
        btnResults.setFont(new Font("Impact", 13));
        btnResults.setStyle("-fx-border-color: #fcf4a3 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fcf4a3; -fx-background-color: transparent;");
        btnResults.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                btnResults.setStyle("-fx-border-color: #fcf4a3 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fcf4a3; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                btnResults.setStyle("-fx-border-color: #fcf4a3 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fcf4a3; -fx-background-color: transparent;");
            }
        });
        btnResults.setPrefHeight(40);
        btnResults.setPrefWidth(80);

        //Button Continue
        btnContinue = new Button("START");
        btnContinue.setLayoutX(440);
        btnContinue.setLayoutY(10);
        btnContinue.setFont(new Font("Impact", 16));
        btnContinue.setStyle("-fx-border-color:#27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
        btnContinue.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                btnContinue.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                btnContinue.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
            }
        });
        btnContinue.setPrefHeight(50);
        btnContinue.setPrefWidth(100);

        bottomBox.getChildren().addAll(btnExit, btnResults, btnContinue);
        loginPane.setBottom(bottomBox);

        //Main
        BackgroundImage myBI = new BackgroundImage(new Image(getClass().getResource("Images/Universe.png").toString(), 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        loginPane.setBackground(new Background(myBI));

        //error text
        centerBox.getChildren().add(getErrMsg());
        getErrMsg().setFont(new Font("Impact", 14));
        getErrMsg().setTextFill(Color.web("#fe2c54", 1.0));
        getErrMsg().setLayoutX(340);
        getErrMsg().setLayoutY(382);
        
        loginScene = new Scene(loginPane, 800, 600);

    }

    public void loadUsers() {
        try {
            //use keymap to store questions
            //questionList = new TreeMap<Integer, TestQuestion>();

            //load from txt into this map
            readUsersFromFile(new File("src/Data/contestant.txt"));

            username = new ArrayList<String>(namePwList.keySet());

            //initilaize do or not status list
            for (String name : getUsername()) {
                getUserDoneStatusList().put(name, Boolean.FALSE);
            }

            usernameCombo.setItems(FXCollections.observableArrayList(getUsername()));
            //find num of question
            //max_question = namePwList.size();

            //set first question
            //currentQues = questionList.get(1);
            //root.setCenter(currentQues.getLayout());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //check if user done or not
    public boolean checkUserEligibility() {
        String currentUser = (String) usernameCombo.getValue();
        return getUserDoneStatusList().get(currentUser);
    }

    public void readUsersFromFile(File file) throws FileNotFoundException {
        String contestant = "";
        String user, password;
        //to count id when putting into map
        int questCounter = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                contestant = scanner.nextLine();
                String[] tmpQues = contestant.split(":");
                user = tmpQues[0];
                password = tmpQues[1];
                //differentiate into diff ques types(NOT DONE)
                getNamePwList().put(user, password);

            }
        } catch (FileNotFoundException e) {

        }
    }

    public boolean checkPassword() {
        if (password.getText().equals(namePwList.get(usernameCombo.getValue()))) {
            return true;
        } else {
            return false;
        }
    }

    public void alertWrongPassword() {
        errMsg.setText("ⓘ Incorrect Password");
    }

    public void removeWrongPassword() {
//        centerBox.getChildren().remove(getErrMsg());
        password.setText("");
    }

    public void clearErrMsg() {
        errMsg.setText("");
    }

    //check if user selected a country
    public boolean checkCountrySelected() {
        if (((String) CountryCombo.getValue()).equalsIgnoreCase("Select Country")) {
            return false;
        } else {
            return true;
        }
    }
}
