package Test;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jesse Tan
 */
public class TypeC extends TestQuestion {

    public TypeC(String correctAns, String questionTitle, String a, String b, String c, String d) {
        super(correctAns, questionTitle, a, b, c, d);

        Text title = new Text(quesId + ")   " + questionTitle);
        title.setLayoutX(30);
        title.setLayoutY(50);
        title.setFont(Font.font("Impact",FontWeight.THIN, FontPosture.REGULAR, 14));

        TilePane paneForRadioBtn = new TilePane();
        Image qA = new Image(getClass().getResource(a).toString(), 100, 100, false, false);
        btnA = new RadioButton();
        btnA.setGraphic(new ImageView(qA));

        Image qB = new Image(getClass().getResource(b).toString(), 100, 100, false, false);
        btnB = new RadioButton();
        btnB.setGraphic(new ImageView(qB));

        Image qC = new Image(getClass().getResource(c).toString(), 100, 100, false, false);
        btnC = new RadioButton();
        btnC.setGraphic(new ImageView(qC));

        Image qD = new Image(getClass().getResource(d).toString(), 100, 100, false, false);
        btnD = new RadioButton();
        btnD.setGraphic(new ImageView(qD));
        

        paneForRadioBtn.getChildren().add(btnA);
        paneForRadioBtn.getChildren().add(btnB);
        paneForRadioBtn.getChildren().add(btnC);
        paneForRadioBtn.getChildren().add(btnD);

        btnA.setOnAction(this);
        btnB.setOnAction(this);
        btnC.setOnAction(this);
        btnD.setOnAction(this);

        paneForRadioBtn.setHgap(40);
        paneForRadioBtn.setVgap(20);
        //paneForRadioBtn.setSpacing(20);
        paneForRadioBtn.setLayoutX(40);
        paneForRadioBtn.setLayoutY(80);
        paneForRadioBtn.setAlignment(Pos.BOTTOM_LEFT);
        //paneForRadioBtn.setMaxWidth(200);

        paneForRadioBtn.setPrefColumns(2);
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
