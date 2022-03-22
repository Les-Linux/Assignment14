package com.elbicon;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class App 
{
    public static void main( String[] args )
    {
       try{

           File jsonFile = new File("src/response.json");
           ObjectMapper om = new ObjectMapper();
           Root root = om.readValue(jsonFile, Root.class);
           System.out.println("Root:" + root);
           //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/tmp/multicloud-customers.csv"));
           BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/tmp/multicloud-customers-utf16.csv",true), StandardCharsets.UTF_16));
           root.results.stream().forEach(f -> {
               //System.out.println(f.customer_id + "," + f.customer_name + "," + f.bp_category_text);
               try {
                   bufferedWriter.write(f.customer_id + "," + f.customer_name.replace(",","") + "," + f.bp_category_text + "\n");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           });
           bufferedWriter.close();
       } catch(Exception e){
           System.out.printf("Exception Caught: " + e.getMessage());
       }
    }
}
