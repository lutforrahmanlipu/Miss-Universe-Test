/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Results;

import java.text.DecimalFormat;
import java.util.Map;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ScoreProfile {

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the choosenAns
     */
    public Map<Integer, String> getChoosenAns() {
        return choosenAns;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the correctPercentage
     */
    public double getCorrectPercentage() {
        return Double.parseDouble(df.format(correctPercentage));

    }

    /**
     * @param correctPercentage the correctPercentage to set
     */
    public void setCorrectPercentage(double correctPercentage) {
        this.correctPercentage = correctPercentage;
    }
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private String name;
    private Map<Integer, String> choosenAns;
    private double correctPercentage = 0.0;
    private String country;

    public ScoreProfile(String name, Map<Integer, String> choosenAns) {
        this.name = name;
        this.choosenAns = choosenAns;
    }
}
