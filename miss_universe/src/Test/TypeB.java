/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Jesse Tan
 */
public class TypeB extends TestQuestion{
    private String imagePath;
    public TypeB(String correctAns, String questionTitle, String a, String b, String c, String d, String iP){
        super(correctAns,questionTitle,a,b,c,d);
        imagePath = iP;
       
        //set layout
        Text title = new Text(quesId + ")   " + questionTitle);
        title.setLayoutX(30);
        title.setLayoutY(50);
        title.setFont(Font.font("Impact",FontWeight.THIN, FontPosture.REGULAR, 14));
        
        Image questionPic = new Image(getClass().getResource(imagePath).toString());
        ImageView view = new ImageView(questionPic);
        view.setLayoutX(55);
        view.setLayoutY(75);
        view.setFitHeight(200);
        view.setFitWidth(200);
        
        HBox paneForRadioBtn = new HBox();
        VBox column1 = new VBox(20);
        VBox column2 = new VBox(20);
        
        btnA = new RadioButton(a);
        btnB = new RadioButton(b);
        btnC = new RadioButton(c);
        btnD = new RadioButton(d);
        
        btnA.setFont(new Font("Impact", 14));
        btnB.setFont(new Font("Impact", 14));
        btnC.setFont(new Font("Impact", 14));
        btnD.setFont(new Font("Impact", 14));

        column1.getChildren().add(btnA);
        column2.getChildren().add(btnB);
        column1.getChildren().add(btnC);
        column2.getChildren().add(btnD);
        
        paneForRadioBtn.getChildren().add(column1);
        paneForRadioBtn.getChildren().add(column2);
        
        btnA.setOnAction(this);
        btnB.setOnAction(this);
        btnC.setOnAction(this);
        btnD.setOnAction(this);
        

        paneForRadioBtn.setSpacing(40);
        //paneForRadioBtn.setVgap(20);
        //paneForRadioBtn.setAlignment(Pos.BASELINE_LEFT);
        //paneForRadioBtn.setSpacing(20);
        paneForRadioBtn.setLayoutX(55);
        paneForRadioBtn.setLayoutY(300);
        //paneForRadioBtn.setMaxWidth(100);

        //paneForRadioBtn.setPrefColumns(2);
        paneForRadioBtn.setPrefHeight(2);

        btnA.setToggleGroup(ansGrp);
        btnB.setToggleGroup(ansGrp);
        btnC.setToggleGroup(ansGrp);
        btnD.setToggleGroup(ansGrp);

        layout.getChildren().add(title);
        layout.getChildren().add(view);
        layout.getChildren().add(paneForRadioBtn);
    }
}
