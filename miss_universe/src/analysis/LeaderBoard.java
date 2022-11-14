/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import Results.ScoreProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LeaderBoard {

    public List<String> getNullContestentList() {
        return nullContestentList;
    }

    public void setNullContestentList(List<String> nullContestentList) {
        this.nullContestentList = nullContestentList;
    }

    /**
     * @return the init
     */
    public boolean isInit() {
        return init;
    }

    /**
     * @param init the init to set
     */
    public void setInit(boolean init) {
        this.init = init;
    }

    /**
     * @return the analysisFromBtn
     */
    public Button getAnalysisFormBtn() {
        return analysisFormBtn;
    }

    /**
     * @return the scoreProfileList
     */
    public ArrayList<ScoreProfile> getScoreProfileList() {
        return scoreProfileList;
    }

    /**
     * @param scoreProfileList the scoreProfileList to set
     */
    public void setScoreProfileList(ArrayList<ScoreProfile> scoreProfileList) {
        this.scoreProfileList = scoreProfileList;
    }

    /**
     * @return the lboardScene
     */
    public Scene getLboardScene() {
        return lboardScene;
    }

    private Scene lboardScene;
    private ArrayList<ScoreProfile> scoreProfileList = new ArrayList<ScoreProfile>();
    private Pane board = new Pane();
    private Button exitBtn, analysisFormBtn;
    private List<String> nullContestentList = new ArrayList<>();
    private String[] podiumList = new String[3];
    private TableView<ScoreProfile> table = new TableView<ScoreProfile>();
    private VBox firstWinner,secondWinner,thirdWinner;
    private Label firstWinnerN,secondWinnerN,thirdWinnerN;
    private boolean init = false;

    public LeaderBoard() {
        //button declaration
        exitBtn = new Button("EXIT");
        exitBtn.setLayoutX(160);
        exitBtn.setLayoutY(520);
        exitBtn.setFont(new Font("Impact", 16));
        exitBtn.setStyle("-fx-border-color: #fe2c54 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fe2c54; -fx-background-color: transparent;");
        exitBtn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                exitBtn.setStyle("-fx-border-color: #fe2c54 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fe2c54; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                exitBtn.setStyle("-fx-border-color: #fe2c54 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fe2c54; -fx-background-color: transparent;");
            }
        });
        exitBtn.setPrefHeight(50);
        exitBtn.setPrefWidth(150);
        exitBtn.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        analysisFormBtn = new Button("ANALYSE PAGE");
        analysisFormBtn.setLayoutX(480);
        analysisFormBtn.setLayoutY(520);
        analysisFormBtn.setFont(new Font("Impact", 16));
        analysisFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
        analysisFormBtn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                analysisFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                analysisFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
            }
        });
        analysisFormBtn.setPrefHeight(50);
        analysisFormBtn.setPrefWidth(150);

        /*//add names to list of total compatitors
        contestentList.add("TAN JIA CHUN");
        contestentList.add("LIM JUN YUAN");
        contestentList.add("LUTFOR RAHMAN LIPU");
        contestentList.add("HENG YI YUAN");
        contestentList.add("LAU JUNG EN");*/
        //random text
        Label t1 = new Label("LEADERBOARD");
        t1.setLayoutX(270);
        t1.setLayoutY(30);
        t1.setFont(new Font("Impact", 45));
        t1.setTextFill(Color.WHITE);

        board.getChildren().add(t1);
        board.getChildren().add(analysisFormBtn);

        BackgroundImage myBI = new BackgroundImage(new Image(getClass().getResource("Universe.png").toString(), 800, 600, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        board.setBackground(new Background(myBI));
        lboardScene = new Scene(board, 800, 600);

    }

    //create leader board ranking 
    public void init() {
        generateParticipentsThatDidNotDo();
        initTable();
    }

//    public void getWinnerOrder() {
//        for (int i = 0; i < scoreProfileList.size() - 1; i++) {
//            //linear sort to sort winner percentage
//            if (scoreProfileList.get(i).getCorrectPercentage() < scoreProfileList.get(i + 1).getCorrectPercentage()) {
//                ScoreProfile tmpP = scoreProfileList.get(i);
//                scoreProfileList.set(i, scoreProfileList.get(i + 1));
//                scoreProfileList.set(i + 1, tmpP);
//            }
//        }
//    }
    public void generateParticipentsThatDidNotDo() {
        //go through the scoreProfileList and add participants that did not do the test
        //go through array and check all names that have done
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < scoreProfileList.size(); i++) {
            tmp.add(scoreProfileList.get(i).getName());
        }
        getNullContestentList().removeAll(tmp);

        //add all remaining contestent with default value
        for (String s : getNullContestentList()) {
            Map<Integer, String> defaultAnswers = new TreeMap<>();
            for (int i = 1; i <= 25; i++) {
                defaultAnswers.put(i, "X");
            }
            double defaultCorrectPercentage = 0.0;
            ScoreProfile tmpSp = new ScoreProfile(s, defaultAnswers);
            tmpSp.setCorrectPercentage(defaultCorrectPercentage);
            scoreProfileList.add(tmpSp);
        }
    }

    public void initTable() {
        table.setPlaceholder(new Label("No rows to display"));
        ObservableList<ScoreProfile> obList = FXCollections.observableArrayList(scoreProfileList);
        table.setEditable(false);
        //disable table resize
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn nameCol = new TableColumn("Name");
        //nameCol.setMaxWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<ScoreProfile, String>("name"));

        TableColumn scoreCol = new TableColumn("Score");
        //scoreCol.setMaxWidth(50);
        scoreCol.setCellValueFactory(
                new PropertyValueFactory<ScoreProfile, Double>("correctPercentage"));

        nameCol.setStyle("-fx-alignment: CENTER;");
        scoreCol.setStyle("-fx-alignment: CENTER;");

        //populate data and add table column
        table.setItems(obList);
        table.setPrefWidth(400);
        table.setMaxSize(400, 158);
        table.setStyle("-fx-border-color: cyan; -fx-border-width: 5; ");
        table.getColumns().addAll(nameCol, scoreCol);
        table.setLayoutX(200);
        table.setLayoutY(330);

        //sorting
        scoreCol.setSortType(TableColumn.SortType.DESCENDING);
        nameCol.setSortable(false);
//        scoreCol.setSortable(false);
        table.getSortOrder().addAll(scoreCol);
        table.sort();

        //add table and exitbtn to scene
        board.getChildren().add(table);
        board.getChildren().add(exitBtn);
        init = true;
    }

//    public void updateTableData() {
//        table.setItems(FXCollections.observableArrayList(scoreProfileList));
//    }
    public void generateWinnerNames() {
        ObservableList<ScoreProfile> colList = table.getItems();
        int counter = 0;
        for (int i = 0; i < colList.size(); i++) {
            if (counter < 3) {
                ScoreProfile tmp = colList.get(i);
                podiumList[i] = tmp.getName();
                counter++;
            }

        }
    }
    
    public void showWinnerPicture(){
        //FirstPlace
        firstWinner = new VBox();
        firstWinnerN = new Label();
        if ("TAN JIA CHUN".equals(podiumList[0])){
            ImageView JC1 = new ImageView(new Image(getClass().getResource("JC1.jpg").toString(), 135, 140, false, true));
            firstWinner.getChildren().addAll(JC1);
        } else if ("LAU JING EN".equals(podiumList[0])){
            ImageView JE2 = new ImageView(new Image(getClass().getResource("JE2.jpg").toString(), 135, 140, false, true));
            firstWinner.getChildren().add(JE2);
        } else if ("LUTFOR RAHMAN LIPU".equals(podiumList[0])){
            ImageView LIPU1 = new ImageView(new Image(getClass().getResource("LIPU1.jpg").toString(), 135, 140, false, true));
            firstWinner.getChildren().add(LIPU1);
        }else if ("LIM JUN YUAN".equals(podiumList[0])){
            ImageView JY1 = new ImageView(new Image(getClass().getResource("JY1.jpg").toString(), 135, 140, false, true));
            firstWinner.getChildren().add(JY1);
        }else if ("HENG YI YUAN".equals(podiumList[0])){
            ImageView YY1 = new ImageView(new Image(getClass().getResource("YY1.jpeg").toString(), 135, 140, false, true));
            firstWinner.getChildren().add(YY1);
        }
        firstWinner.setLayoutX(325);
        firstWinner.setLayoutY(100);
        firstWinner.setStyle("-fx-border-color: #D2BF37 ; -fx-border-width: 5;");
        firstWinnerN.setText(podiumList[0]);
        firstWinnerN.setLayoutX(325);
        firstWinnerN.setLayoutY(250);
        firstWinnerN.setFont(new Font("Impact", 20));
        firstWinnerN.setTextFill(Color.WHITE);
        
        //Second Winner
        secondWinner = new VBox();
        secondWinnerN = new Label();
        if ("TAN JIA CHUN".equals(podiumList[1])){
            ImageView JC1 = new ImageView(new Image(getClass().getResource("JC1.jpg").toString(), 135, 140, false, true));
            secondWinner.getChildren().addAll(JC1);
        } else if ("LAU JING EN".equals(podiumList[1])){
            ImageView JE2 = new ImageView(new Image(getClass().getResource("JE2.jpg").toString(), 135, 140, false, true));
            secondWinner.getChildren().add(JE2);
        } else if ("LUTFOR RAHMAN LIPU".equals(podiumList[1])){
            ImageView LIPU1 = new ImageView(new Image(getClass().getResource("LIPU1.jpg").toString(), 135, 140, false, true));
            secondWinner.getChildren().add(LIPU1);
        }else if ("LIM JUN YUAN".equals(podiumList[1])){
            ImageView JY1 = new ImageView(new Image(getClass().getResource("JY1.jpg").toString(), 135, 140, false, true));
            secondWinner.getChildren().add(JY1);
        }else if ("HENG YI YUAN".equals(podiumList[1])){
            ImageView YY1 = new ImageView(new Image(getClass().getResource("YY1.jpeg").toString(), 135, 140, false, true));
            secondWinner.getChildren().add(YY1);
        }
        secondWinner.setLayoutX(130);
        secondWinner.setLayoutY(130);
        secondWinner.setStyle("-fx-border-color: #C0C0C0 ; -fx-border-width: 5;");
        secondWinnerN.setText(podiumList[1]);
        secondWinnerN.setLayoutX(130);
        secondWinnerN.setLayoutY(280);
        secondWinnerN.setFont(new Font("Impact", 20));
        secondWinnerN.setTextFill(Color.WHITE);
        
        //Third Winner
        thirdWinner = new VBox();
        thirdWinnerN = new Label();
        if ("TAN JIA CHUN".equals(podiumList[2])){
            ImageView JC1 = new ImageView(new Image(getClass().getResource("JC1.jpg").toString(), 135, 140, false, true));
            thirdWinner.getChildren().addAll(JC1);
        } else if ("LAU JING EN".equals(podiumList[2])){
            ImageView JE2 = new ImageView(new Image(getClass().getResource("JE2.jpg").toString(), 135, 140, false, true));
            thirdWinner.getChildren().add(JE2);
        } else if ("LUTFOR RAHMAN LIPU".equals(podiumList[2])){
            ImageView LIPU1 = new ImageView(new Image(getClass().getResource("LIPU1.jpg").toString(), 135, 140, false, true));
            thirdWinner.getChildren().add(LIPU1);
        }else if ("LIM JUN YUAN".equals(podiumList[2])){
            ImageView JY1 = new ImageView(new Image(getClass().getResource("JY1.jpg").toString(), 135, 140, false, true));
            thirdWinner.getChildren().add(JY1);
        }else if ("HENG YI YUAN".equals(podiumList[2])){
            ImageView YY1 = new ImageView(new Image(getClass().getResource("YY1.jpeg").toString(), 135, 140, false, true));
            thirdWinner.getChildren().add(YY1);
        }
        thirdWinner.setLayoutX(510);
        thirdWinner.setLayoutY(140);
        thirdWinner.setStyle("-fx-border-color: #CD7F32 ; -fx-border-width: 5;");
        thirdWinnerN.setText(podiumList[2]);
        thirdWinnerN.setLayoutX(510);
        thirdWinnerN.setLayoutY(290);
        thirdWinnerN.setFont(new Font("Impact", 20));
        thirdWinnerN.setTextFill(Color.WHITE);
        
        
        board.getChildren().addAll(firstWinner,firstWinnerN,secondWinner,secondWinnerN,thirdWinner,thirdWinnerN);
       
    }
}
