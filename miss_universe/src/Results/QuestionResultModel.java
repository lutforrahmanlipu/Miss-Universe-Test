/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Results;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class QuestionResultModel {

    /**
     * @return the resultSymbol
     */
    public String getResultSymbol() {
        return resultSymbol;
    }

    /**
     * @param resultSymbol the resultSymbol to set
     */
    public void setResultSymbol(String resultSymbol) {
        this.resultSymbol = resultSymbol;
    }

    /**
     * @return the quesNo
     */
    public int getQuesNo() {
        return quesNo;
    }

    /**
     * @param quesNo the quesNo to set
     */
    public void setQuesNo(int quesNo) {
        this.quesNo = quesNo;
    }

    /**
     * @return the correctAns
     */
    public String getCorrectAns() {
        return correctAns;
    }

    /**
     * @param correctAns the correctAns to set
     */
    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    /**
     * @return the userAns
     */
    public String getUserAns() {
        return userAns;
    }

    /**
     * @param userAns the userAns to set
     */
    public void setUserAns(String userAns) {
        this.userAns = userAns;
    }

    private int quesNo;
    private String correctAns;
    private String userAns;
    private String resultSymbol;

    public QuestionResultModel(int quesNo, String userAns, String correctAns) {
        this.quesNo = quesNo;
        this.correctAns = correctAns;
        this.userAns = userAns;
        if (correctAns.equalsIgnoreCase(userAns)) {
            resultSymbol = "O";
        } else {
            resultSymbol = "X";
        }
    }
}