/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import Results.ScoreProfile;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.text.DecimalFormat;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AnalysisForm {

    /**
     * @return the resultFormBtn
     */
    public Button getResultFormBtn() {
        return resultFormBtn;
    }

    /**
     * @return the leaderBoardBtn
     */
    public Button getLeaderBoardBtn() {
        return leaderBoardBtn;
    }

    /**
     * @param scoreProfileList the scoreProfileList to set
     */
    public void setScoreProfileList(ArrayList<ScoreProfile> scoreProfileList) {
        this.scoreProfileList = scoreProfileList;
    }

    /**
     * @return the AnalysisScene
     */
    public Scene getAnalysisScene() {
        return AnalysisScene;
    }

    private Scene AnalysisScene;
    private ArrayList<ScoreProfile> scoreProfileList = new ArrayList<ScoreProfile>();
    private Text resulth = new Text();
    private Text resultsd = new Text();
    private Text resultl = new Text();
    private Text resulta = new Text();
    private Label resultm = new Label();
    private Text resultmo = new Text();
    private ArrayList<Double> results = new ArrayList<Double>();
    private Button leaderBoardBtn,resultFormBtn;

    //constructor
    public AnalysisForm() {
        init();
    }

    //methods
    public String getMaximum() {
        int counter = 1;
        String returnText = "Out of " + scoreProfileList.size() + " participants, the highest scoring participant";
        Double max = scoreProfileList.get(0).getCorrectPercentage();
        ArrayList<ScoreProfile> highestScorerList = new ArrayList<ScoreProfile>();
        highestScorerList.add(scoreProfileList.get(0));
        for (int i = 1; i < scoreProfileList.size(); i++) {
            if (scoreProfileList.get(i).getCorrectPercentage() > max) {
                max = scoreProfileList.get(i).getCorrectPercentage();
                highestScorerList.removeAll(highestScorerList);
                highestScorerList.add(scoreProfileList.get(i));
            } else if(scoreProfileList.get(i).getCorrectPercentage() == max) {
                highestScorerList.add(scoreProfileList.get(i));
            }
        } 
        for (ScoreProfile participant : highestScorerList) {
            if(highestScorerList.size() == 1) {
                returnText += " is " + participant.getName() + " with " + max.toString();
            } else {
                if(highestScorerList.size() == counter) {
                    returnText += participant.getName() + " with " + max.toString();
                } else {
                    returnText += "s are " + participant.getName() + ", ";
                }  
            }
            counter++;
        }
        return returnText;
    }

    public String getMinimum() {
        int counter = 1;
        String returnText = "Out of " + scoreProfileList.size() + " participants, the lowest scoring participant";
        Double min = scoreProfileList.get(0).getCorrectPercentage();
        ArrayList<ScoreProfile> lowestScorerList = new ArrayList<ScoreProfile>();
        lowestScorerList.add(scoreProfileList.get(0));
        for (int i = 1; i < scoreProfileList.size(); i++) {
            if (scoreProfileList.get(i).getCorrectPercentage() < min) {
                min = scoreProfileList.get(i).getCorrectPercentage();
                lowestScorerList.removeAll(lowestScorerList);
                lowestScorerList.add(scoreProfileList.get(i));
            } else if(scoreProfileList.get(i).getCorrectPercentage() == min) {
                lowestScorerList.add(scoreProfileList.get(i));
            }
        } 
        for (ScoreProfile participant : lowestScorerList) {
            if(lowestScorerList.size() == 1) {
                returnText += " is " + participant.getName() + " with " + min.toString();
            } else {
                if(lowestScorerList.size() == counter) {
                    returnText += participant.getName() + " with " + min.toString();
                } else {
                    returnText += "s are " + participant.getName() + ", ";
                }  
            }
            counter++;
        }
        return returnText;
    }

    public String getAverage(ArrayList<Double> m) {
        Double sum = 0.0;
        String returnText = "Out of " + m.size() + " participants, the average scoring is ";
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < m.size(); i++) {
            sum += m.get(i);
        }
        
        Double j = sum / m.size();
        String k = df.format(j);
        return returnText + k;
    }

    public String getMedian(ArrayList<Double> m) {
        String returnText = "Out of " + m.size() + " participants, median number of the score is ";
        int mid = m.size() / 2;
        String median = "";
        if (m.size() % 2 == 1) {
            median = m.get(mid).toString();
        } else {
            Double j = (m.get(mid - 1) + m.get(mid)) / 2.0;
            median = j.toString();
        }
        
        return returnText + median;
    }

    public String getMode(ArrayList<Double> a) {
        Double maxValue = 0.0;
        int maxCount = 0;
        String returnText = "";
        for (int i = 0; i < a.size(); ++i) {
            int count = 0;
            for (int j = 0; j < a.size(); ++j) {
                if (a.get(j).equals(a.get(i))) {
                    ++count;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = a.get(i);
            }
        }
        if(maxCount <= 1 ){
            //if there is no repeated number, there will be no Mode value for this set of score
            returnText = "No repeated score. Mode number is non existing.";
        } else {
             returnText = "Out of " + a.size() + " participants, mode number of the score is " + maxValue.toString();
        }
        return returnText;
    }

    public String getStandardDeviation(ArrayList<Double> a) {
        Double sum = 0.0;
        Double standardDeviation = 0.0;
        Double mean;
        Double res;
        Double sq;
        String returnText = "Out of " + a.size() + " participants, the standard deviation is ";

        int n = a.size();

        for (int i = 0; i < n; i++) {
            sum = sum + a.get(i);
        }
        mean = sum / (n);
        for (int i = 0; i < n; i++) {
            standardDeviation
                    = standardDeviation + Math.pow((a.get(i) - mean), 2);
        }
        sq = standardDeviation / n;
        res = Math.sqrt(sq);
        DecimalFormat df = new DecimalFormat("#.00");
        String k = df.format(res);
        return returnText + k;
    }

    public void getScoreListData() {
        int counter = 0;
        for (ScoreProfile sp : scoreProfileList) {
            results.add(sp.getCorrectPercentage());
            counter++;
        }
    }

    public void init() {
        Label labResult = new Label();
        labResult.setLayoutX(50);
        labResult.setLayoutY(500);
        
        //btn declaration
        leaderBoardBtn = new Button("LEADERBOARD");
        leaderBoardBtn.setLayoutX(50);
        leaderBoardBtn.setLayoutY(300);
        leaderBoardBtn.setFont(new Font("Impact", 16));
        leaderBoardBtn.setStyle("-fx-border-color: #fcf4a3 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fcf4a3; -fx-background-color: transparent;");
        leaderBoardBtn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                leaderBoardBtn.setStyle("-fx-border-color: #fcf4a3 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fcf4a3; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                leaderBoardBtn.setStyle("-fx-border-color: #fcf4a3 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#fcf4a3; -fx-background-color: transparent;");
            }
        });
        leaderBoardBtn.setPrefHeight(50);
        leaderBoardBtn.setPrefWidth(150);
        
        
        resultFormBtn = new Button("RESULT FORM");
        resultFormBtn.setLayoutX(480);
        resultFormBtn.setLayoutY(500);
        resultFormBtn.setFont(new Font("Impact", 16));
        resultFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
        resultFormBtn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
                resultFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: #D3D3D3; -fx-background-radius: 30; ");
            } else {
                resultFormBtn.setStyle("-fx-border-color: #27fdb6 ; -fx-border-width: 5; -fx-border-radius: 30; -fx-text-fill:#27fdb6; -fx-background-color: transparent;");
            }
        });
        resultFormBtn.setPrefHeight(50);
        resultFormBtn.setPrefWidth(150);
        
        Button btnExit = new Button("EXIT");
        btnExit.setLayoutX(160);
        btnExit.setLayoutY(500);
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
        btnExit.setPrefWidth(150);
        btnExit.setOnAction((ActionEvent event) -> {
            Platform.exit();
        }
        );

        String options[] = {"Highest", "Lowest", "Average","Median", "Mode","Standard Deviation"};

        // Create a combo box
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(options));
        combo_box.setPromptText("Please select: ");
        combo_box.setLayoutX(50);
        combo_box.setLayoutY(140);
        combo_box.setPrefWidth(290);
        combo_box.setPrefHeight(50);
        combo_box.setStyle("-fx-font-family: Impact; -fx-background-color: white; -fx-font-size: 20;");
        
        Label cbLabel = new Label("Choose a criteria to analyse: ");
        cbLabel.setFont(new Font("Impact", 25));
        cbLabel.setLayoutX(50);
        cbLabel.setLayoutY(100);
        cbLabel.setTextFill(Color.WHITE);

        //Result
        resultm.setLayoutX(50);
        resultm.setLayoutY(233);
        resultm.setFont(Font.font("Impact", 20));
        resultm.setTextFill(Color.WHITE);

        // Create action event to display text according to user selection
        combo_box.setOnAction((e) -> {
                if (combo_box.getValue().equals("Highest")){
                    resultm.setText(getMaximum());
                } else if (combo_box.getValue().equals("Lowest")){
                    resultm.setText(getMinimum());
                } else if (combo_box.getValue().equals("Average")){
                    resultm.setText(getAverage(results));
                } else if (combo_box.getValue().equals("Median")){
                    resultm.setText(getMedian(results));
                } else if (combo_box.getValue().equals("Mode")){
                    resultm.setText(getMode(results));
                } else if (combo_box.getValue().equals("Standard Deviation")){
                    resultm.setText(getStandardDeviation(results));
            }
        }
        );
       
        Pane p1 = new Pane();
        p1.getChildren().add(btnExit);
        p1.getChildren().add(combo_box);
        p1.getChildren().add(cbLabel);
        p1.getChildren().add(resultm);
        p1.getChildren().add(getLeaderBoardBtn());
        p1.getChildren().add(resultFormBtn);
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResource("Universe.png").toString(),800,600,false,true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
        p1.setBackground(new Background(myBI));

        AnalysisScene = new Scene(p1, 800, 600);
    }
    
    public void updateAnalysisData(){
       getScoreListData();
    }
    
    
}