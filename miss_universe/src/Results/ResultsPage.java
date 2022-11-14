package Results;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ResultsPage {

    /**
     * @return the names
     */
    public ArrayList<String> getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    //variables for the buttons/ boxes...
    private Label labName, labCountry, labResults, labQuestion, labCDisplay, labRDisplay, labPercent, labPDisplay;
    private ComboBox comName;
    private Button prevForm, nextForm;

    private ArrayList<String> names = new ArrayList<String>();
    private String countries[];
    private int results[];
    private Scene myScene;

    private ArrayList<ScoreProfile> scoreProfileList = new ArrayList<>();
    private Map<Integer, String> correctAnsList;
    private TableView resultTableView;
    private ScrollPane resultScrollPane = new ScrollPane();
    private Pane rootPane;

    public ResultsPage() {
        init();
    }

    public ArrayList<ScoreProfile> getScoreProfileList() {
        return scoreProfileList;
    }

    public void setScoreProfileList(ArrayList<ScoreProfile> scoreProfileList) {
        this.scoreProfileList = scoreProfileList;
    }

    public void setCorrectAnsList(Map<Integer, String> correctAnsList) {
        this.correctAnsList = correctAnsList;
    }

    public Button getPrevForm() {
        return prevForm;
    }

    public void setPrevForm(Button prevForm) {
        this.prevForm = prevForm;
    }

    public Button getNextForm() {
        return nextForm;
    }

    public Scene getMyScene() {
        return myScene;
    }

    public void init() {

        //nav buttons
        prevForm = new Button("LOGIN PAGE");
        prevForm.setLayoutX(150);
        prevForm.setLayoutY(500);
        prevForm.setFont(new Font("Impact", 16));
        prevForm.setStyle("-fx-border-color:#27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
        prevForm.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                prevForm.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                prevForm.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
            }
        });
        prevForm.setPrefHeight(50);
        prevForm.setPrefWidth(150);

        nextForm = new Button("ANALYSE PAGE");
        nextForm.setLayoutX(500);
        nextForm.setLayoutY(500);
        nextForm.setFont(new Font("Impact", 16));
        nextForm.setStyle("-fx-border-color:#27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
        nextForm.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                nextForm.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                nextForm.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
            }
        });
        nextForm.setPrefHeight(50);
        nextForm.setPrefWidth(150);

        labelSetup();

        //Drop down box for name
        comName = new ComboBox();
        comName.setLayoutX(160);
        comName.setLayoutY(200);
        comName.setPrefWidth(150);
        comName.setStyle("-fx-font-family: Impact; -fx-background-color: white;");
        comName.setPromptText("Select Name ");
        //Action for selecting a name, displays the country/flag and results.
        comName.setOnAction(e -> {
            setDisplay(comName.getValue());
            generateQuestionResultTable(comName.getValue());
        });

        //Pane for the scene
        rootPane = new Pane();
        rootPane.getChildren().add(labName);
        rootPane.getChildren().add(labCountry);
        rootPane.getChildren().add(comName);
        rootPane.getChildren().add(labCDisplay);
        rootPane.getChildren().add(labRDisplay);
        rootPane.getChildren().add(labResults);
        rootPane.getChildren().add(labPercent);
        rootPane.getChildren().add(labPDisplay);
        rootPane.getChildren().add(prevForm);
        rootPane.getChildren().add(nextForm);

        rootPane.getChildren().add(resultScrollPane);
        resultScrollPane.setVisible(false);

        BackgroundImage myBI = new BackgroundImage(new Image(getClass().getResource("Universe.png").toString(), 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        rootPane.setBackground(new Background(myBI));

        myScene = new Scene(rootPane, 800, 600);

    }

    public void labelSetup() {
        //Label for name
        labName = new Label("Name : ");
        labName.setLayoutX(100);
        labName.setLayoutY(200);
        labName.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        labName.setTextFill(Color.WHITE);

        //Label for country
        labCountry = new Label("Country : ");
        labCountry.setLayoutX(100);
        labCountry.setLayoutY(230);
        labCountry.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        labCountry.setTextFill(Color.WHITE);

        //Label for country display
        labCDisplay = new Label();
        labCDisplay.setLayoutX(180);
        labCDisplay.setLayoutY(230);
        labCDisplay.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        labCDisplay.setTextFill(Color.web("#fe2c54", 1.0));

        labResults = new Label("Results : ");
        labResults.setLayoutX(100);
        labResults.setLayoutY(260);
        labResults.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        labResults.setTextFill(Color.WHITE);

        labRDisplay = new Label();
        labRDisplay.setLayoutX(180);
        labRDisplay.setLayoutY(260);
        labRDisplay.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        labRDisplay.setTextFill(Color.web("#fe2c54", 1.0));

        labPercent = new Label("Percentage : ");
        labPercent.setLayoutX(100);
        labPercent.setLayoutY(290);
        labPercent.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        labPercent.setTextFill(Color.WHITE);

        labPDisplay = new Label();
        labPDisplay.setLayoutX(210);
        labPDisplay.setLayoutY(290);
        labPDisplay.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        labPDisplay.setTextFill(Color.web("#fe2c54", 1.0));
    }

    public void setDisplay(Object x) {

        ScoreProfile currentProfile = findContestent(String.valueOf(x));
        //check if null or not
        if (currentProfile != null) {
            
            Label result = new Label();
            result.setText(currentProfile.getCorrectPercentage() + " / " + 25);
            labRDisplay.setText(result.getText());
            labPDisplay.setText(currentProfile.getCorrectPercentage() + "%");
            labCDisplay.setText(currentProfile.getCountry());

        } else {
            labCDisplay.setText("No Data");
            labRDisplay.setText("No Data");
            labPDisplay.setText("No Data");

        }

    }

    public void initCorrectPercentage() {
        double correct = 0;
        double total = 0;
        for (int i = 0; i < scoreProfileList.size(); i++) {
            ScoreProfile currentProfile = scoreProfileList.get(i);
            for (int g = 1; g <= currentProfile.getChoosenAns().size(); g++) {

                if (currentProfile.getChoosenAns().get(g).equalsIgnoreCase(correctAnsList.get(g))) {
                    correct++;
                }
                total++;
            }
            double correctPercentage = (correct / total) * 100;
            currentProfile.setCorrectPercentage(correctPercentage);
            //check and compare answer here
            int totalCorrect = (int) correct;
            int totalAnswer = (int) total;
        }
    }

    //return single participant data for table view use
    public ArrayList<QuestionResultModel> generateTableViewModelList(Object x) {
        ScoreProfile currentProfile = findContestent(String.valueOf(x));
        if (currentProfile != null) {
            ArrayList<QuestionResultModel> dataList = new ArrayList<>();
            Map<Integer, String> tmpMap = currentProfile.getChoosenAns();
            QuestionResultModel tmpObj;
            for (int i = 1; i < tmpMap.size() + 1; i++) {
                tmpObj = new QuestionResultModel(i, tmpMap.get(i), correctAnsList.get(i));
                dataList.add(tmpObj);
            }
            return dataList;
        } else {
            return null;
        }

    }

    public void generateQuestionResultTable(Object x) {
        resultTableView = new TableView();
        resultScrollPane.setVisible(false);

        TableColumn quesNoColumn = new TableColumn("Ques No");
        quesNoColumn.setCellValueFactory(new PropertyValueFactory<>("quesNo"));

        TableColumn userAnsColumn = new TableColumn("User answer");
        userAnsColumn.setCellValueFactory(new PropertyValueFactory<>("userAns"));

        TableColumn correctAnsColumn = new TableColumn("Correct Answer");
        correctAnsColumn.setCellValueFactory(new PropertyValueFactory<>("correctAns"));

        TableColumn trueFalseColumn = new TableColumn("O/X");
        trueFalseColumn.setCellValueFactory(new PropertyValueFactory<>("resultSymbol"));

        resultTableView.setStyle("-fx-border-color: cyan; -fx-border-width: 5;");
        resultTableView.getColumns().addAll(quesNoColumn, userAnsColumn, correctAnsColumn, trueFalseColumn);

        if (generateTableViewModelList(x) != null) {
            ObservableList<QuestionResultModel> tmp = FXCollections.observableArrayList(generateTableViewModelList(x));

            resultTableView.getItems().addAll(tmp);
            resultScrollPane.setVisible(true);

        }

        resultScrollPane.setContent(resultTableView);
        resultScrollPane.setLayoutX(400);
        resultScrollPane.setLayoutY(60);

    }

    public void readResults(File file) {
        try {
            Scanner scanner = new Scanner(file);
            String name = "";
            String tmp = "";
            String country = "";
            String[] splitedData;
            int curIndex = 0;
            Map<Integer, String> userAns;
            while (scanner.hasNext()) {
                userAns = new TreeMap<>();
                tmp = scanner.nextLine();
                splitedData = tmp.split(":");
                name = splitedData[curIndex];
                curIndex++;
                //replace static number with max question size+1
                for (int i = 1; i < splitedData.length - 1; i++) {
                    userAns.put(i, splitedData[i]);
                    curIndex++;
                }
                country = splitedData[curIndex];
                ScoreProfile sp1 = new ScoreProfile(name, userAns);
                sp1.setCountry(country);
                scoreProfileList.add(sp1);

                curIndex = 0;
            }

        } catch (FileNotFoundException fe) {
        }
    }

    public ScoreProfile findContestent(String name) {
        ScoreProfile profile = null;
        for (ScoreProfile sp : getScoreProfileList()) {
            //search score sheet for contestent
            if (name.toUpperCase().equals(sp.getName().toUpperCase())) {
                profile = sp;

            }
        }
        return profile;
    }

    //load user list from login form
    public void loadUserList() {
        comName.setItems(FXCollections.observableArrayList(getNames()));
    }

}
