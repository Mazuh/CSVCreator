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

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
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
        stage.setTitle("CSV Creator v1.0.0 <mazuh@ufrn.edu.br>");
        stage.setWidth(800);
        stage.setHeight(600);
 
        Label h1 = new Label("CSV Editor");
        h1.setFont(new Font("Arial", 20));
 
        this.table.setEditable(true);
 
        TableColumn col1 = new TableColumn("col_1");
        TableColumn col2 = new TableColumn("col_2");
        TableColumn col3 = new TableColumn("col_3");
        
        this.table.getColumns().addAll(col1, col2, col3);
 
        VBox verticalBox = new VBox();
        verticalBox.setSpacing(5);
        verticalBox.setPadding(new Insets(10, 0, 0, 10));
        verticalBox.getChildren().addAll(h1, this.table);
        
        ((Group) scene.getRoot()).getChildren().addAll(verticalBox);
 
        stage.setScene(scene);
        stage.show();
    }
    
}
