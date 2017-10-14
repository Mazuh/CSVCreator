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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Comma Separated Value domain entity, has some static methods to deal with its life cycle too.
 * 
 * @author mazuh
 */
public class CSV {
    
    private final List<String> headerRow;
    private final List<List<String>> valuesRows;

    /**
     * Create an instance of a Comma Separated Value file.
     *
     * @param headerRow list of titles for each column
     * @param valuesRows list of lists where each of them is a row of csv
     */
    public CSV(List<String> headerRow, List<List<String>> valuesRows){
        this.headerRow = (ArrayList<String>) headerRow;
        this.valuesRows = (ArrayList<List<String>>) valuesRows;
    }
    
    /**
     * Create an instance of a CSV interface file (unpopulated body).
     *
     * @param headerRow list of titles for each column
     */
    public CSV(List<String> headerRow){
        this.headerRow = (ArrayList<String>) headerRow;
        this.valuesRows = new ArrayList<>();
    }

    /**
     * Create an instance of a CSV interface (unpopulated at all, column quantity set but empty header).
     *
     * @param colQtt for the quantity of columns headers to initialize
     */
    public CSV(int colQtt) {
        this.headerRow = new ArrayList<>(colQtt);
        for (int i = 0; i < colQtt; i++)
            headerRow.add("");
        this.valuesRows = new ArrayList<>();
    }
    
    
    /**
     * Getter for headerRow.
     * @return list of titles for each column
     */
    public List<String> getHeaderRow() {
        return headerRow;
    }

    /**
     * Getter for headerRow.
     * @return list of lists where each of them is a row of values
     */
    public List<List<String>> getValuesRows() {
        return valuesRows;
    }
    
    
    /** 
     * Factory method for CSV from a persisted file.
     *
     * @param path string filename that holds the CSV content
     * @return CSV valid instance if no exception was raised
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NullPointerException
     */
    public static CSV load(String path) throws FileNotFoundException,
                                                   IOException,
                                                   NullPointerException{
        
        try (BufferedReader buffered = new BufferedReader(new FileReader(path))){
            List<String> header = new ArrayList<>();
            List<List<String>> body = new ArrayList<>();
            String row;
            String[] cells;
            
            row = buffered.readLine();
            if (row != null && (cells = row.split(",")).length != 0){
                for (int i = 0; i < cells.length; i++){
                    if (cells[i].charAt(0) == '"' && cells[i].charAt(cells[i].length()-1) == '"'){
                        cells[i] = cells[i].substring(1, cells[i].length()-1);
                    }
                }
                
                header.addAll(Arrays.asList(cells));
            } else{
                throw new NullPointerException("Couldn't find the header line (the 1st one) for this CSV.");
            }

            row = buffered.readLine();
            while(row != null){
                cells = row.split(",");
                for (int i = 0; i < cells.length; i++){
                    if (cells[i].charAt(0) == '"' && cells[i].charAt(cells[i].length()-1) == '"'){
                        cells[i] = cells[i].substring(1, cells[i].length()-1);
                    }
                }
                body.add(Arrays.asList(cells));
                row = buffered.readLine();
            }
            
            return new CSV(header, body);
        }
    }
    
    
    /**
     * Persist an instance of CSV into a path.
     * 
     * @param csvFile as an instance of CSV
     * @param path string filename to be persisted
     * @return true if could be done, otherwise false
     * @throws IOException
     */
    public static boolean save(CSV csvFile, String path) throws IOException{
        try (BufferedWriter buffered = new BufferedWriter(new FileWriter(path))){
            
            int i;
            
            i = 0;
            for (String colName : csvFile.getHeaderRow()){
                buffered.write("\"" + colName + "\"");
                
                if (++i < csvFile.getHeaderRow().size())
                    buffered.write(',');
                else
                    buffered.write(System.lineSeparator());
            }
            
            for (List<String> valuesRow : csvFile.getValuesRows()){
                i = 0;
                
                for (String value : valuesRow){
                    buffered.write("\"" + value + "\"");
                    if (++i < valuesRow.size())
                        buffered.write(',');
                    else
                        buffered.write(System.lineSeparator());
                }
            }
            
            return true;
        } catch(Exception e){
            throw e;
        }
    }

}
