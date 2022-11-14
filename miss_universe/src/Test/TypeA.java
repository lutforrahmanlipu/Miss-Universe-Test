/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Jesse Tan
 */
public class TypeA extends TestQuestion {
    public TypeA (String correctAns, String questionTitle, String a, String b, String c, String d){
        super(correctAns,questionTitle,a,b,c,d);
        
        
        Text title = new Text(quesId + ")   " + questionTitle);
        title.setLayoutX(30);
        title.setLayoutY(50);
        title.setFont(Font.font("Impact",FontWeight.THIN, FontPosture.REGULAR, 14));

        VBox paneForRadioBtn = new VBox();
        btnA = new RadioButton(a);
        btnB = new RadioButton(b);
        btnC = new RadioButton(c);
        btnD = new RadioButton(d);

        btnA.setFont(new Font("Impact", 14));
        btnB.setFont(new Font("Impact", 14));
        btnC.setFont(new Font("Impact", 14));
        btnD.setFont(new Font("Impact", 14));
        
        paneForRadioBtn.getChildren().add(btnA);
        paneForRadioBtn.getChildren().add(btnB);
        paneForRadioBtn.getChildren().add(btnC);
        paneForRadioBtn.getChildren().add(btnD);

        btnA.setOnAction(this);
        btnB.setOnAction(this);
        btnC.setOnAction(this);
        btnD.setOnAction(this);

        //paneForRadioBtn.setHgap(40);
        //paneForRadioBtn.setVgap(20);
        paneForRadioBtn.setSpacing(20);
        paneForRadioBtn.setLayoutX(55);
        paneForRadioBtn.setLayoutY(75);
        paneForRadioBtn.setAlignment(Pos.BOTTOM_LEFT);
        //paneForRadioBtn.setMaxWidth(200);

        //paneForRadioBtn.setPrefColumns(1);
        paneForRadioBtn.setPrefHeight(4);
        
        //Image abc = new Image(getClass().getResource("./abc.jpg.jpg").toString());
        //ImageView ar = new ImageView(abc);
        //ar.setLayoutX(70);
        //ar.setLayoutY(100);
        
        btnA.setToggleGroup(ansGrp);
        btnB.setToggleGroup(ansGrp);
        btnC.setToggleGroup(ansGrp);
        btnD.setToggleGroup(ansGrp);
        

        layout.getChildren().add(title);
        layout.getChildren().add(paneForRadioBtn);
        //layout.getChildren().add(ar);
    }
}
