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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author mazuh
 */
public class CSVCreator extends Application {
    
    private final TableView table = new TableView();

    
    /**
     * Main method, initializing the whole program.
     * @param args are being ignored
     */
    public static void main(String[] args) {
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
        
        Button addBtn = new Button("Add");
        
        HBox headerBox = new HBox();
        headerBox.setSpacing(10);
        headerBox.getChildren().addAll(h1, addBtn);

        // body
        
        TextField field1 = new TextField();
        field1.setPromptText("field1");
        
        TextField field2 = new TextField();
        field2.setPromptText("field2");
        
        HBox rowBox = new HBox();
        rowBox.setSpacing(2);
        rowBox.getChildren().addAll(field1);
        rowBox.getChildren().addAll(field2);
 
        // assembling
        
        fullBox.getChildren().addAll(headerBox, rowBox);
        ((Group) scene.getRoot()).getChildren().addAll(fullBox);
        
        stage.setTitle("CSV Creator v1.0.0 <mazuh@ufrn.edu.br>");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
