package missuniverse;

import Login.LoginForm;
import Results.ResultsPage;
import Test.TestForm;
import analysis.*;
import java.io.File;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MissUniverse extends Application implements EventHandler<ActionEvent> {

    //create instance of each form here
    TestForm testForm = new TestForm();
    ResultsPage r1 = new ResultsPage();
    LoginForm loginForm = new LoginForm();
    AnalysisForm analysisForm = new AnalysisForm();
    LeaderBoard leaderBoardForm = new LeaderBoard();

    String participantName = "USER";
    String participantCountry = "-";
    Stage primaryStage;

    static FileWriter participantAnswersWriter;

    //set login form -> question form -> result form -> statistic form
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        registerNavBtn();

        primaryStage.setTitle("MISS UNIVERSE COMPETITION 2021");
        loginForm.loadUsers();
        primaryStage.setScene(loginForm.getLoginScene());
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            testForm.stopTimer();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //register all buttons
    public void registerNavBtn() {
        loginForm.getBtnContinue().setOnAction(this);
        loginForm.getBtnResults().setOnAction(this);
        testForm.getPrevFormBtn().setOnAction(this);
        testForm.getNextFormBtn().setOnAction(this);
        r1.getPrevForm().setOnAction(this);
        r1.getNextForm().setOnAction(this);
        analysisForm.getLeaderBoardBtn().setOnAction(this);
        analysisForm.getResultFormBtn().setOnAction(this);
        leaderBoardForm.getAnalysisFormBtn().setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        //test form -> login form
        if (event.getSource() == testForm.getPrevFormBtn()) {
            primaryStage.setScene(loginForm.getLoginScene());
            loginForm.removeWrongPassword();
            testForm.getqTimer().stopAlarm();
        } //result form -> login form
        else if (event.getSource() == r1.getPrevForm()) {
            primaryStage.setScene(loginForm.getLoginScene());
        } //result form -> analysis form
        else if (event.getSource() == r1.getNextForm()) {
            primaryStage.setScene(analysisForm.getAnalysisScene());
            //pass list
            analysisForm.setScoreProfileList(r1.getScoreProfileList());
            //update anslysis page data
            analysisForm.updateAnalysisData();
        } //login form -> test form
        else if (event.getSource() == loginForm.getBtnContinue()) {
            //if participant havent do only let do
            if (loginForm.checkPassword()) {
                if (!loginForm.checkUserEligibility()) {
                    if (loginForm.checkCountrySelected()) {
                        //clear msg
                        loginForm.clearErrMsg();
                        //pass eleigibility table
                        testForm.setUserDoneStatusList(loginForm.getUserDoneStatusList());
                        participantName = (String) loginForm.getUsernameCombo().getValue();
                        participantCountry = (String) loginForm.getCountryCombo().getValue();
                        testForm.setParticipantDetails(participantName, participantCountry);
                        testForm.assignCountryFlag();
                        testForm.resetQuestions();
                        testForm.setMarked(false);
                        primaryStage.setScene(testForm.getTestScene());
                    }else{
                        loginForm.getErrMsg().setText("ⓘ Please select a country!");
                        System.out.println("contru");
                    }

                } else {
                    loginForm.getErrMsg().setText("ⓘ Already done cannot do again!");
                }

            } else {
                loginForm.alertWrongPassword();
                loginForm.removeWrongPassword();

            }

        }//login form -> result form 
        else if (event.getSource() == loginForm.getBtnResults()) {
            //pass correct ans list into result page
            r1.setCorrectAnsList(testForm.getCorrectAnsList());
            r1.readResults(new File("src/Data/student.txt"));
            //put userlist into result form
            r1.setNames(loginForm.getUsername());
            r1.loadUserList();
            r1.initCorrectPercentage();
            primaryStage.setScene(r1.getMyScene());
            testForm.closeOutputStream();
            System.out.println("closed");

        }// test form -> login form 
        else if (event.getSource() == testForm.getNextFormBtn()) {
            //update newest list
            loginForm.setUserDoneStatusList(testForm.getUserDoneStatusList());
            primaryStage.setScene(loginForm.getLoginScene());
            loginForm.removeWrongPassword();
            testForm.getqTimer().stopAlarm();
        }//analysis form -> leaderboard form 
        else if (event.getSource() == analysisForm.getLeaderBoardBtn()) {
            primaryStage.setScene(leaderBoardForm.getLboardScene());
            leaderBoardForm.setNullContestentList(r1.getNames());
            leaderBoardForm.setScoreProfileList(r1.getScoreProfileList());
            if (!leaderBoardForm.isInit()) {
                leaderBoardForm.init();
                leaderBoardForm.generateWinnerNames();
            }
            leaderBoardForm.showWinnerPicture();
        } //analysis form -> result form
        else if (event.getSource() == analysisForm.getResultFormBtn()) {
            primaryStage.setScene(r1.getMyScene());
        }//leaderboard form -> analysis form
        else if (event.getSource() == leaderBoardForm.getAnalysisFormBtn()) {
            primaryStage.setScene(analysisForm.getAnalysisScene());
        }

    }
}
