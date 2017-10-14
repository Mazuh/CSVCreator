/*
 * The MIT License
 *
 * Copyright 2017 Marcell "Mazuh" Guilherme Costa da Silva
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.mazuh.csvcreator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * Main class, the GUI to CSV Creator.
 * 
 * @author mazuh
 */
public class CSVCreatorWindow extends Application {

    final static List<String> COLUMNS_NAME = new ArrayList<>();
    
    /**
     * Main method, asks for some parameters and .
     * @param args are being used for JavaFX purposes (not user)
     */
    public static void main(String[] args) {
        try {
            int colQtt = Integer.parseInt(JOptionPane.showInputDialog("How many columns?"));
            for (int i = 0; i < colQtt; i++){
                String cname = JOptionPane.showInputDialog("#" + (i+1) + " column's name:");
                if (cname == null)
                    throw new NullPointerException();
                else
                    COLUMNS_NAME.add(cname);
            }
        } catch(NumberFormatException | NullPointerException e){
            JOptionPane.showMessageDialog(null, "Canceled by user.", "", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        
        launch(args);
    }
    
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        
        VBox fullBox = new VBox();
        fullBox.setSpacing(5);
        fullBox.setPadding(new Insets(20, 20, 20, 20));
        
        // header
 
        Label h1 = new Label("CSV Editor");
        h1.setFont(new Font("Arial", 20));
        
        Button addBtn = new Button("+ Row");
        
        HBox headerBox = new HBox();
        headerBox.setSpacing(10);
        headerBox.getChildren().addAll(h1, addBtn);

        // body
        
        HBox rowBox = new HBox();
        rowBox.setSpacing(2);
        
        for (String columnName : CSVCreatorWindow.COLUMNS_NAME) {
            TextField field = new TextField();
            field.setPromptText(columnName);
            rowBox.getChildren().add(field);
        }
 
        // assembling
        
        fullBox.getChildren().addAll(headerBox, rowBox);
        ((Group) scene.getRoot()).getChildren().addAll(fullBox);
        
        stage.setTitle("CSV Creator v1.0.0 <mazuh@ufrn.edu.br>");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    
}
