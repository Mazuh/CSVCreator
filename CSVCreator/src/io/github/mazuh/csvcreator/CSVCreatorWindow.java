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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * Main class, the GUI to CSV Creator.
 * 
 * @author mazuh
 */
public class CSVCreatorWindow extends Application {

    private static CSV csv;
    
    /**
     * Main method, asks for some parameters and .
     * @param args are being used for JavaFX purposes (not user)
     */
    public static void main(String[] args) {
        try {
            int newOption = JOptionPane.showConfirmDialog(null, 
                    "Create a new CSV?\n\n(Choose 'no' to open an existent file.)");
            
            switch (newOption) {
                case JOptionPane.YES_OPTION:
                    List<String> cnames = new ArrayList<>();
                    
                    int colQtt = Integer.parseInt(JOptionPane.showInputDialog("How many columns?"));
                    
                    for (int i = 0; i < colQtt; i++){
                        String cname = JOptionPane.showInputDialog("#" + (i+1) + " column's name:");
                        if (cname == null)
                            throw new NullPointerException();
                        else
                            cnames.add(cname);
                    }
                    
                    csv = new CSV(cnames);
                    break;
                    
                case JOptionPane.NO_OPTION:
                    csv = null;
                    break;
                
                default:
                    throw new NullPointerException();
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
        
        if (csv == null){
            try{
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open a CSV file.");
                csv = CSV.load(fileChooser.showOpenDialog(stage).getAbsolutePath());
            } catch(IOException e){
                JOptionPane.showMessageDialog(null, "Failed to read such file.", "", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            } catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "You asked to load CSV file and didn't provide one.", "", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Unknown error while trying to read this so-called CSV file.", "", JOptionPane.ERROR_MESSAGE);
                System.exit(1);    
            }
        }

        // all containers
        
        VBox windowVBox = new VBox();
        
        HBox topperHBox = new HBox();
        HBox headerHBox = new HBox();
        
        ScrollPane bodyScrollContainer = new ScrollPane();
        VBox rowsVBox = new VBox();
        
        windowVBox.setSpacing(5);
        windowVBox.setPadding(new Insets(20, 20, 20, 20));
        
        // topper
 
        Label h1 = new Label("CSV Editor");
        h1.setFont(new Font("Arial", 20));
        
        Button newRowBtn = new Button("+ Row");
        
        topperHBox.setSpacing(10);
        topperHBox.getChildren().addAll(h1, newRowBtn);
        
        windowVBox.getChildren().add(topperHBox);
        
        // header
        
        headerHBox.setSpacing(2);
        
        for (String columnName : csv.getHeaderRow()) {
            headerHBox.getChildren().add(new TextField(columnName));
        }
        
        windowVBox.getChildren().add(headerHBox);
        
        // body
        
        rowsVBox.setSpacing(2);
        bodyScrollContainer.setPadding(new Insets(10, 2, 2, 2));
        bodyScrollContainer.setMaxHeight(475);
        bodyScrollContainer.setContent(rowsVBox);
        windowVBox.getChildren().add(bodyScrollContainer);
        
        // all into scene
        
        ((Group) scene.getRoot()).getChildren().add(windowVBox);

        newRowBtn.setOnAction((event) -> {
            HBox newRowHBox = new HBox();
            newRowHBox.setSpacing(2);
            for (String columnName : csv.getHeaderRow()) {
                TextField emptyField = new TextField();
                emptyField.setPromptText(columnName);
                newRowHBox.getChildren().add(emptyField);
            }
            rowsVBox.getChildren().add(newRowHBox);
        });
        
        stage.setTitle("CSV Creator v1.0.0 <mazuh@ufrn.edu.br>");
        stage.setHeight(600);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    
}
