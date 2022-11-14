package Test;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class QuestionNavigationBar {

    /**
     * @return the btnList
     */
    public ArrayList<Button> getBtnList() {
        return btnList;
    }

    /**
     * @return the questionScroll
     */
    public ScrollPane getQuestionScroll() {
        return questionScroll;
    }

    private HBox buttonBar;
    private ScrollPane questionScroll;
    private ArrayList<Button> btnList = new ArrayList<>();

    public QuestionNavigationBar(int quesNo) {
        questionScroll = new ScrollPane();
        questionScroll.setVbarPolicy(ScrollBarPolicy.NEVER);
        buttonBar = new HBox();
        init(quesNo);

        questionScroll.setFitToWidth(true);
        questionScroll.setContent(buttonBar);
        questionScroll.setPrefWidth(400);
        
        buttonBar.setPadding(new Insets(5, 0, 20, 0));
    }

    //create buttons for each map entry
    public void init(int quesNo) {
        int counter = 1;
        while (counter <= quesNo) {
            Button btnTmp = new Button(Integer.toString(counter));
            btnTmp.setMaxWidth(20);
            btnTmp.setStyle("-fx-background-color: white;");
            btnTmp.setFont(new Font("Impact", 13));
            btnTmp.setMinSize(
            Region.USE_PREF_SIZE,
            Region.USE_PREF_SIZE
            );
            buttonBar.getChildren().add(btnTmp);
            getBtnList().add(btnTmp);
            counter++;
        }

    }
    
}