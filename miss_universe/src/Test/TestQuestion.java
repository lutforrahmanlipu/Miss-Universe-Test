package Test;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class TestQuestion implements EventHandler<ActionEvent> {

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @return the correctAns
     */
    public String getCorrectAns() {
        return correctAns;
    }

    /**
     * @return the userAns
     */
    public String getUserAns() {
        return userAns;
    }

    /**
     * @return the quesId
     */
    public int getQuesId() {
        return quesId;
    }

    /**
     * @return the layout
     */
    public Pane getLayout() {
        return layout;
    }

    private String correctAns;
    private String userAns;
    private String questionTitle;
    private boolean checked = false;
    public int quesId;
    private static int idCounter = 1;

    ToggleGroup ansGrp = new ToggleGroup();
    RadioButton btnA, btnB, btnC, btnD,btnX;
    public Pane layout = new Pane();

    public TestQuestion(String correctAns, String questionTitle, String a, String b, String c, String d) {
        this.correctAns = correctAns;
        this.questionTitle = questionTitle;
        this.quesId = idCounter;
        idCounter++;
        userAns = "X";
        
        layout.setPrefWidth(400);
        layout.setPrefHeight(400);
        layout.setStyle("-fx-border-color: cyan; -fx-border-width: 5; -fx-background-color:rgba(176, 193, 199, 0.79); -fx-background-opacity: 1.0;");
        
        

    }

    //set answer as
    public void setUserAnswer() {
        if (btnA.isSelected()) {
            userAns = "A";
            setChecked(true);
        } else if (btnB.isSelected()) {
            userAns = "B";
            setChecked(true);

        } else if (btnC.isSelected()) {
            userAns = "C";
            setChecked(true);

        } else if (btnD.isSelected()) {
            userAns = "D";
            setChecked(true);

        }
    }

    public void handle(ActionEvent event) {
        setUserAnswer();
    }

    //disable all buttons when checking
    public void disableButtons() {
        btnA.setDisable(true);
        btnB.setDisable(true);
        btnC.setDisable(true);
        btnD.setDisable(true);
    }

    //reenable question when reset
    public void buttonReset() {
        btnA.setDisable(false);
        btnB.setDisable(false);
        btnC.setDisable(false);
        btnD.setDisable(false);
        ansGrp.selectToggle(null);
        btnA.setStyle("-fx-background-color: null");
        btnB.setStyle("-fx-background-color: null");
        btnC.setStyle("-fx-background-color: null");
        btnD.setStyle("-fx-background-color: null");
    }

    //change color of right questions
    public void highlightCorrect() {
        if (correctAns.toUpperCase().equals("A")) {
            btnA.setStyle("-fx-background-color: #00FF00");
        } else if (correctAns.toUpperCase().equals("B")) {
            btnB.setStyle("-fx-background-color: #00FF00");
        } else if (correctAns.toUpperCase().equals("C")) {
            btnC.setStyle("-fx-background-color: #00FF00");
        } else if (correctAns.toUpperCase().equals("D")) {
            btnD.setStyle("-fx-background-color: #00FF00");
        }
    }

    //change color of wrong questions
    public void highlightWrong() {
        if (correctAns.toUpperCase().equals("A")) {
            btnA.setStyle("-fx-background-color: #ff0000");
        } else if (correctAns.toUpperCase().equals("B")) {
            btnB.setStyle("-fx-background-color: #ff0000");
        } else if (correctAns.toUpperCase().equals("C")) {
            btnC.setStyle("-fx-background-color: #ff0000");
        } else if (correctAns.toUpperCase().equals("D")) {
            btnD.setStyle("-fx-background-color: #ff0000");
        }
        
    }
    
    public void resetUserAnswer(){
        userAns = "X";
    }

}
