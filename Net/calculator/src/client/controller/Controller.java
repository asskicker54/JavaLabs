package client.controller;

import client.model.Net;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private double x, y;
    private Net net;
    private String operator = "";
    private double num1 = 0;

    @FXML
    private Pane titlepane;
    @FXML
    private ImageView btnMinimize, btnClose;
    @FXML
    private Label lblResult;

    public void init(Stage stage) {
        titlepane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlepane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> {
            try {
                net.send(0, 0, "close");
                net.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.close();
        });

        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
        net = new Net("127.0.0.1", 1111);
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("btn",""));
        lblResult.setText(Double.parseDouble(lblResult.getText())==0?String.valueOf((double)value):String.valueOf(Double.parseDouble(lblResult.getText())*10+value));
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn","");
        if(symbol.equals("Equals")) {
            double num2 = Double.parseDouble(lblResult.getText());
            net.send(num1, num2, operator);
            try {
                lblResult.setText(net.get() + "");
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
            net.clear();
            operator = ".";
        }
        else if(symbol.equals("Clear")) {
            lblResult.setText(String.valueOf(0.0));
            operator = ".";
        }
        else {
            switch (symbol) {
                case "Plus" -> operator = "+";
                case "Minus" -> operator = "-";
                case "Multiply" -> operator = "*";
                case "Divide" -> operator = "/";
            }
            num1 = Double.parseDouble(lblResult.getText());
            lblResult.setText(String.valueOf(0.0));
        }
    }
}
