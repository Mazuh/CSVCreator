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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Primitive manual testing.
 * 
 * @author mazuh
 */
public class Test {
    public static void main(String[] args){
        ArrayList<String> headerRow = new ArrayList<>();
        ArrayList<List<String>> valuesRows = new ArrayList<>();
        
        headerRow.add("nome");
        headerRow.add("sobrenome");
        
        ArrayList<String> row1 = new ArrayList<>();
        row1.add("Marcell");
        row1.add("Guilherme");
        valuesRows.add(row1);
        
        ArrayList<String> row2 = new ArrayList<>();
        row2.add("Yuri");
        row2.add("Buys");
        valuesRows.add(row2);
        
        CSV csv = new CSV(headerRow, valuesRows);

        System.out.println("INSTANCE CREATED. OUTPUT: ");
        
        Test.print_testing_csv(csv);
        
        try {
            final String path = "/home/mazuh/csvcreator_test.csv"; // MY LINUX HOME
            
            System.out.println("\nTRYING TO PERSIST (" + path + ")...");
            CSV.save(csv, path);
            System.out.println("INSTANCE PERSISTED.\n");
            
            System.out.println("TRYING TO LOAD FROM THE SAME PATH...");
            CSV loaded = CSV.load(path);
            System.out.println("INSTANCE LOADED. OUTPUT:");
            Test.print_testing_csv(loaded);
 
            System.out.println("\nDONE.");
            
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    
    private static void print_testing_csv(CSV csv) {
        for (String title : csv.getHeaderRow()){
            System.out.print(title + ";");
        }
        
        System.out.println();
        
        for (List<String> row : csv.getValuesRows()){
            for (String value : row){
                System.out.print(value + ";");
            }
            System.out.println();
        }
    }
    
}
