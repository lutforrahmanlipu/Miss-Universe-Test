package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class TestForm implements EventHandler<ActionEvent> {

    /**
     * @param marked the marked to set
     */
    public void setMarked(boolean marked) {
        this.marked = marked;
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

    public QuestionTimer getqTimer() {
        return qTimer;
    }

    /**
     * @return the correctAnsList
     */
    public Map<Integer, String> getCorrectAnsList() {
        return correctAnsList;
    }

    /**
     * @return the nextFormBtn
     */
    public Button getNextFormBtn() {
        return nextFormBtn;
    }

    /**
     * @return the prevFormBtn
     */
    public Button getPrevFormBtn() {
        return prevFormBtn;
    }

    /**
     * @return the testScene
     */
    public Scene getTestScene() {
        return testScene;
    }

    private Scene testScene;
    private Button nextFormBtn;
    private Button prevFormBtn;
    private Button nextQuesBtn, prevQuesBtn;
    private Button submitBtn;
    private TestQuestion currentQues;
    private int max_question;
    private Map<Integer, TestQuestion> questionList;
    private Map<Integer, String> ansList;
    private Map<Integer, String> correctAnsList = new TreeMap<>();
    private QuestionNavigationBar navBar;
    private Label quesNo, participantName, participantCountry;
    private BorderPane root = new BorderPane();
    private QuestionTimer qTimer;
    public Pane rightLayout = new Pane();
    private FileWriter fileWritter;
    private boolean marked = false;
    private ImageView view;
    private Timer aTimer;
    private VBox contestant = new VBox();
    private Map<String, Boolean> userDoneStatusList = new HashMap<>();

    //to be decided
    public TestForm() {

        loadQuestions();
        try {
            fileWritter = new FileWriter(new File("src/Data/student.txt"));
        } catch (IOException ie) {

        }
        HBox buttonBox = new HBox();

        //title component for top(stackpane)
        participantName = new Label();
        participantCountry = new Label();
        navBar = new QuestionNavigationBar(max_question);
        registerNavButtons();

        //timer declaration 
        qTimer = new QuestionTimer();

        aTimer = new Timer();
        aTimer.schedule(
                new TimerTask() {

            @Override
            public void run() {
                handle(new ActionEvent());
//                System.out.println("Running....");
            }
        }, 10000, 1000);

        //topLayout
        Pane topBox = new Pane();
        topBox.setPrefWidth(800);
        topBox.setPrefHeight(100);
        //topBox.setStyle("-fx-border-color: black");

        quesNo = new Label(getQuesNoFormat());
        quesNo.setLayoutX(600);
        quesNo.setLayoutY(30);
        quesNo.setFont(new Font("Impact", 25));
        quesNo.setStyle("-fx-text-fill: white;");

        qTimer.getTimerDisplay().setLayoutX(330);
        qTimer.getTimerDisplay().setLayoutY(20);
        qTimer.getTimerDisplay().setFont(new Font("Impact", 45));
        qTimer.getTimerDisplay().setStyle("-fx-text-fill: white;");

        participantName.setFont(new Font("Impact", 20));
        participantName.setStyle("-fx-text-fill: white;");

        topBox.getChildren().add(contestant);
        topBox.getChildren().add(qTimer.getTimerDisplay());
        topBox.getChildren().add(quesNo);
        root.setTop(topBox);

        //leftLayout
        Pane leftBox = new Pane();
        leftBox.setPrefWidth(200);
        leftBox.setPrefHeight(400);
        //leftBox.setStyle("-fx-border-color: black");
        Polygon lTriangle = new Polygon();
        lTriangle.getPoints().addAll(new Double[]{
            20.0, 10.0,
            10.0, 15.0,
            20.0, 20.0
        });
        prevQuesBtn = new Button("");
        prevQuesBtn.setStyle("-fx-border-color: cyan ; -fx-border-hover-color: red;-fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
        prevQuesBtn.setPrefWidth(50);
        prevQuesBtn.setPrefHeight(50);
        prevQuesBtn.setShape(lTriangle);
        prevQuesBtn.setLayoutX(100);
        prevQuesBtn.setLayoutY(175);
        prevQuesBtn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                prevQuesBtn.setStyle("-fx-border-color: AFEEEE ; -fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
            } else {
                prevQuesBtn.setStyle("-fx-border-color: cyan ; -fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
            }
        });
        prevQuesBtn.setDisable(true);
        prevQuesBtn.setOnAction(this);
        leftBox.getChildren().add(prevQuesBtn);
        root.setLeft(leftBox);

        //rightLayout
        Pane rightBox = new Pane();
        rightBox.setPrefWidth(200);
        rightBox.setPrefHeight(400);
        //rightBox.setStyle("-fx-border-color: black");

        Polygon rTriangle = new Polygon();
        rTriangle.getPoints().addAll(new Double[]{
            0.0, 10.0,
            10.0, 15.0,
            0.0, 20.0
        });
        nextQuesBtn = new Button("");
        nextQuesBtn.setStyle("-fx-border-color: cyan ;-fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
        nextQuesBtn.setPrefWidth(50);
        nextQuesBtn.setPrefHeight(50);
        nextQuesBtn.setShape(rTriangle);
        nextQuesBtn.setLayoutX(50);
        nextQuesBtn.setLayoutY(175);
        nextQuesBtn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                nextQuesBtn.setStyle("-fx-border-color: AFEEEE ;-fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
            } else {
                nextQuesBtn.setStyle("-fx-border-color: cyan ; -fx-border-width: 5; -fx-border-radius: 30; -fx-background-color: transparent;");
            }
        });
        nextQuesBtn.setOnAction(this);

        submitBtn = new Button("Submit Questions");
        submitBtn.setFont(new Font("Impact", 16));
        //submitBtn.setFontColor(Color.BLACK);
        submitBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
        submitBtn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                submitBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                submitBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
            }
        });
        submitBtn.setLayoutX(30);
        submitBtn.setLayoutY(350);
        submitBtn.prefHeight(50);
        submitBtn.prefWidth(100);
        submitBtn.setVisible(false);
        submitBtn.setOnAction(this);

        rightBox.getChildren().add(nextQuesBtn);
        rightBox.getChildren().add(submitBtn);
        rightLayout.getChildren().add(rightBox);

        root.setRight(rightLayout);

        //BottomLayout
        Pane bottomBox = new Pane();
        bottomBox.setPrefWidth(800);
        bottomBox.setPrefHeight(100);
        //bottomBox.setStyle("-fx-border-color: black");

        prevFormBtn = new Button("BACK TO LOGIN");
        prevFormBtn.setFont(new Font("Impact", 16));
        prevFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");

        prevFormBtn.setLayoutX(50);
        prevFormBtn.setLayoutY(30);
        prevFormBtn.prefHeight(50);
        prevFormBtn.prefWidth(100);
        prevFormBtn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                prevFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                prevFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
            }
        });

        nextFormBtn = new Button("FINISH");
        nextFormBtn.setFont(new Font("Impact", 16));
        nextFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");

        nextFormBtn.setDisable(true);
        nextFormBtn.setLayoutX(630);
        nextFormBtn.setLayoutY(30);
        nextFormBtn.prefHeight(50);
        nextFormBtn.prefWidth(100);
        nextFormBtn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                nextFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                nextFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
            }
        });

        navBar.getQuestionScroll().setLayoutX(200);
        navBar.getQuestionScroll().setLayoutY(20);
        navBar.getQuestionScroll().setStyle("-fx-border-color:cyan; -fx-border-width: 5;");

        bottomBox.getChildren().add(prevFormBtn);
        bottomBox.getChildren().add(nextFormBtn);
        bottomBox.getChildren().add(navBar.getQuestionScroll());
        root.setBottom(bottomBox);

        BackgroundImage myBI = new BackgroundImage(new Image(getClass().getResource("Images/Universe.png").toString(), 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        testScene = new Scene(root, 800, 600);
    }

    public void assignCountryFlag() {
        contestant.getChildren().remove(view);
        contestant.getChildren().remove(participantName);
        if ("NORWAY".equals(participantCountry.getText())) {
            Image countryFlag = new Image(getClass().getResource("Images/Norway.png").toString());
            view = new ImageView(countryFlag);
            view.setFitHeight(50);
            view.setFitWidth(100);
            contestant.getChildren().add(view);
        } else if ("OMAN".equals(participantCountry.getText())) {
            Image countryFlag = new Image(getClass().getResource("Images/Oman.jpg").toString());
            view = new ImageView(countryFlag);
            view.setFitHeight(50);
            view.setFitWidth(100);
            contestant.getChildren().add(view);
        } else if ("PAPUA NEW GUINEA".equals(participantCountry.getText())) {
            Image countryFlag = new Image(getClass().getResource("Images/PG.png").toString());
            view = new ImageView(countryFlag);
            view.setFitHeight(50);
            view.setFitWidth(100);
            contestant.getChildren().add(view);
        } else if ("PAKISTAN".equals(participantCountry.getText())) {
            Image countryFlag = new Image(getClass().getResource("Images/Pakistan.png").toString());
            view = new ImageView(countryFlag);
            view.setFitHeight(50);
            view.setFitWidth(100);
            contestant.getChildren().add(view);
        } else if ("PANAMA".equals(participantCountry.getText())) {
            Image countryFlag = new Image(getClass().getResource("Images/Panama.png").toString());
            view = new ImageView(countryFlag);
            view.setFitHeight(50);
            view.setFitWidth(100);
            contestant.getChildren().add(view);
        }

        contestant.getChildren().add(participantName);
        contestant.setLayoutX(20);
        contestant.setLayoutY(15);

    }

    public void loadQuestions() {
        //use keymap to store questions
        questionList = new TreeMap<Integer, TestQuestion>();
        ansList = new TreeMap<Integer, String>();

        //load from txt into this map
        readQuestionsFromFile(new File("src/Data/questions.txt"));

        //find num of question
        max_question = questionList.size();

        //set first question
        currentQues = questionList.get(1);
        root.setCenter(currentQues.getLayout());
    }

    //register all navigation buttons from scrollbar
    public void registerNavButtons() {
        for (Button btn : navBar.getBtnList()) {
            btn.setOnAction(e -> goToQuestion(Integer.parseInt(btn.getText())));
        }
    }

    public void setParticipantDetails(String name, String country) {
        this.participantName.setText(name);
        this.participantCountry.setText(country);
    }

    //go to question from navigation bar
    public void goToQuestion(int no) {

        if (!marked) {
            //set color
            setQuestionStatus();
        }
        
        currentQues = questionList.get(no);
        root.setCenter(currentQues.getLayout());
        quesNo.setText(getQuesNoFormat());
        //disable.enable buttons
        checkStopNavigation();
        //show or hide submit button 
        checkLast();

    }

    public void checkStopNavigation() {
        nextQuesBtn.setDisable(false);
        prevQuesBtn.setDisable(false);
        if (currentQues.getQuesId() == max_question) {
            nextQuesBtn.setDisable(true);
        }
        if (currentQues.getQuesId() == 1) {
            prevQuesBtn.setDisable(true);
        }
    }

    @Override
    public void handle(ActionEvent event) {
        //setQuestionStatus();
        if (event.getSource() == submitBtn) {
            //load user ans into another map
            ansList = getUserAnsList();
            qTimer.stopAlarm();

            //if all done move into file (INVERSED)
            if (allAnswered()) {
                //check and highlight correct ans
                verifyAnswers();
                //write to file
                writeAnstoFile(participantName.getText(), participantCountry.getText());
                getqTimer().pauseTimer();
                submitBtn.setDisable(true);
                nextFormBtn.setDisable(false);
                setMarked(true);
                setDone();
            } else {
                System.out.println("NOT DONE!");
            }

        } else if (event.getSource() == nextQuesBtn) {
            //go to next question if have
            if (!qTimer.isTimeUp()) {
                setQuestionStatus();
            }
            int tmp = currentQues.getQuesId();
            currentQues = questionList.get(++tmp);
            checkStopNavigation();
            root.setCenter(currentQues.getLayout());
            quesNo.setText(getQuesNoFormat());
        } else if (event.getSource() == prevQuesBtn) {

            //go to prev question if have
            if (!qTimer.isTimeUp()) {
                setQuestionStatus();
            }
            int tmp = currentQues.getQuesId();
            currentQues = questionList.get(--tmp);
            checkStopNavigation();
            root.setCenter(currentQues.getLayout());
            quesNo.setText(getQuesNoFormat());

        }
        //if not time up than allow do
        if (getqTimer().isTimeUp()) {
            //show submit btn
            submitBtn.setDisable(false);
            submitBtn.setVisible(true);
            //disable all questions
            for (int i = 1; i <= questionList.size(); i++) {
                questionList.get(i).disableButtons();
            }
            prevFormBtn.setDisable(true);
            //show pop up table asking to submit
        } else {
            //if at last question show submit button
            checkLast();
        }

    }

    public void checkLast() {
        if (currentQues.getQuesId() == max_question) {
            submitBtn.setVisible(true);
        } else {
            submitBtn.setVisible(false);
        }
    }

    //put user ans into an map
    public Map<Integer, String> getUserAnsList() {
        //go through questionList
        Map<Integer, String> userAnsList = new TreeMap<>();
        for (int i = 1; i < questionList.size() + 1; i++) {
            String userAns = questionList.get(i).getUserAns();
            userAnsList.put(i, userAns);
        }

        return userAnsList;
    }

    //get formateed title
    public String getQuesNoFormat() {
        String formatedNo = "Question: " + currentQues.getQuesId() + " / " + max_question;
        return formatedNo;
    }

    //read all questions from file
    public void readQuestionsFromFile(File file) {
        String question = "";
        String tmpQuesType, tmpQuesAns, tmpQuesTitle, tmpQuesA, tmpQuesB, tmpQuesC, tmpQuesD, tmpQuesPic;
        //to count id when putting into map
        int questCounter = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                question = scanner.nextLine();
                String[] tmpQues = question.split(":");
                tmpQuesType = tmpQues[0];
                tmpQuesAns = tmpQues[1];
                tmpQuesTitle = tmpQues[2];
                tmpQuesA = tmpQues[3];
                tmpQuesB = tmpQues[4];
                tmpQuesC = tmpQues[5];
                tmpQuesD = tmpQues[6];
                questCounter++;
                //differentiate into diff ques types(NOT DONE)
                if ("A".equals(tmpQuesType)) {
                    TypeA tq = new TypeA(tmpQuesAns, tmpQuesTitle, tmpQuesA, tmpQuesB, tmpQuesC, tmpQuesD);
                    questionList.put(questCounter, tq);
                } else if ("B".equals(tmpQuesType)) {
                    tmpQuesPic = tmpQues[7];
                    TypeB tq = new TypeB(tmpQuesAns, tmpQuesTitle, tmpQuesA, tmpQuesB, tmpQuesC, tmpQuesD, tmpQuesPic);
                    questionList.put(questCounter, tq);
                } else if ("C".equals(tmpQuesType)) {
                    TypeC tq = new TypeC(tmpQuesAns, tmpQuesTitle, tmpQuesA, tmpQuesB, tmpQuesC, tmpQuesD);
                    questionList.put(questCounter, tq);
                }
                getCorrectAnsList().put(questCounter, tmpQuesAns);

            }

        } catch (FileNotFoundException e) {

        }
    }

    //write user answer to file
    public void writeAnstoFile(String contestentName, String country) {
        //name:ans:ans....:percentageCorrect
        try {

            fileWritter.write(contestentName + ":");
            for (int i = 1; i <= ansList.size(); i++) {
                fileWritter.write(ansList.get(i) + ":");
            }
            //            fileWritter.write(Double.toString(calculateRightQuestions()));
            fileWritter.write(country);
            fileWritter.write("\n");
            fileWritter.flush();
        } catch (IOException e) {

        }
    }

    //calculate correct question and return percentageCorrect
//    public double calculateRightQuestions() {
//        double correct = 0, wrong = 0;
//        int counter = 1;
//        while (counter <= ansList.size() && counter <= questionList.size()) {
//            if (ansList.get(counter).equals(questionList.get(counter).getCorrectAns())) {
//                correct++;
//            } else {
//                wrong++;
//            }
//            counter++;
//        }
//        double correctPercent = (correct / (correct + wrong)) * 100;
//        return correctPercent;
//    }
    //verify and show correct ans 
    public void verifyAnswers() {
        //check answers for each questions
        for (int i = 1; i <= questionList.size(); i++) {
            questionList.get(i).disableButtons();
        }
        double correct = 0, wrong = 0;
        int counter = 1;
        while (counter <= ansList.size() && counter <= questionList.size()) {
            if (ansList.get(counter).equals(questionList.get(counter).getCorrectAns())) {
                questionList.get(counter).highlightCorrect();
                navBar.getBtnList().get(counter - 1).setStyle("-fx-background-color:Green;");
            } else {
                questionList.get(counter).highlightWrong();
                navBar.getBtnList().get(counter - 1).setStyle("-fx-background-color:Red;");
            }
            counter++;
        }
    }

    //set color of navigation button 
    public void setQuestionStatus() {
        //change color to green if done 
        if (currentQues.isChecked()) {
            Button tmpBtn = navBar.getBtnList().get(currentQues.getQuesId() - 1);
            tmpBtn.setStyle("-fx-background-color: grey;");
        }
    }

    //check if all questions are answered
    public boolean allAnswered() {
        boolean done = false;
        for (int i = 1; i <= ansList.size(); i++) {
            if (ansList.get(i) == null) {
                return done;
            }
        }
        done = true;
        return done;
    }

    //reset questions
    public void resetQuestions() {
        //set all selections to none
        for (int i = 1; i <= questionList.size(); i++) {
            TestQuestion tq = questionList.get(i);
            tq.buttonReset();
            tq.resetUserAnswer();
            tq.setChecked(false);
        }
        //set navigation bar button back to green
        for (Button btn : navBar.getBtnList()) {
            btn.setStyle("-fx-background-color: white; ");

        }
        //set first question
        if (!questionList.isEmpty()) {
            currentQues = questionList.get(1);
            root.setCenter(currentQues.getLayout());

        }
        //set title back
        quesNo.setText(getQuesNoFormat());

        //clear answer list
        ansList.clear();

        //disable/enable button
        checkStopNavigation();

        //reset timer
        getqTimer().resetTimer();

        //enable or disable button
        checkLast();

        nextFormBtn.setDisable(true);
        submitBtn.setDisable(false);
        prevFormBtn.setDisable(false);

    }

    //close output stream to 'student.txt'
    public void closeOutputStream() {
        try {
            fileWritter.close();

        } catch (IOException ie) {

        }
    }

    //stop backgrund timer
    public void stopTimer() {
        aTimer.cancel();
    }

    //mark the participant as done so cannot do again
    public void setDone() {
        userDoneStatusList.replace(participantName.getText(), Boolean.TRUE);
    }
}
