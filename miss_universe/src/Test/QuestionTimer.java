package Test;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class QuestionTimer {

    /**
     * @return the timerDisplay
     */
    public Label getTimerDisplay() {
        return timerDisplay;
    }

    private Label timerDisplay = new Label();
    private Integer minute = 5;
    private Integer second = 0;
    private Timeline timeline;
    private boolean timeUp;
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Time is up, press submit to continue!");
    private MediaPlayer mdPlayer15, mdPlayer5;

    /**
     * @return the timeUp
     */
    public boolean isTimeUp() {
        return timeUp;
    }

    /**
     * @param timeUp the timeUp to set
     */
    public void setTimeUp(boolean timeUp) {
        this.timeUp = timeUp;
    }

    public QuestionTimer() {
        timerDisplay.setText(Integer.toString(second));
        getTimerDisplay().setText(String.format("%02d", minute) + " : " + String.format("%02d", second));
        //timer style here
        String alarm15sec = "src/Test/alarm15sec.mp3";
        Media sound15 = new Media(new File(alarm15sec).toURI().toString());
//        String alarm5sec = "src/audio/alarm5sec.wav";
//        Media sound5 = new Media(new File(alarm5sec).toURI().toString());
        mdPlayer15 = new MediaPlayer(sound15);
    }

    public void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new EventHandler() {
            // KeyFrame event handler
            public void handle(Event event) {
                minuteSecondConversion();
                second--;
                // update timerLabel
                getTimerDisplay().setText(String.format("%02d", minute) + " : " + String.format("%02d", second));
                if (second == 0 && minute == 0) {
                    timeline.stop();
                    timeUp = true;
                    showTimeUpBox();
                } else if (second == 15 && minute == 0) {
                    mdPlayer15.play();
                } else if (second == 0 && minute == 1) {
                    timerDisplay.setStyle("-fx-text-fill: red;");
                }
            }
        }));
        timeline.playFromStart();
    }
    
    public void stopAlarm(){
        mdPlayer15.stop();
    }
    
    public void minuteSecondConversion() {
        if (second == 0 && minute > 0) {
            minute--;
            second = 60;
        }
    }

    public void resetTimer() {
        minute = 5;
        second = 0;
        getTimerDisplay().setText(String.format("%02d", minute) + " : " + String.format("%02d", second));
        startTimer();
        timeUp = false;
        timerDisplay.setStyle("-fx-text-fill: white;");
    }

    //show time up notification
    public void showTimeUpBox() {
        alert.show();
    }

    //puase timer when submit button is clicked
    public void pauseTimer() {
        timeline.stop();
    }
}