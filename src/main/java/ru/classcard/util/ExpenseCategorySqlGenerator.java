package ru.classcard.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ExpenseCategorySqlGenerator {

    public static void main(String... args) {
        Map<String, Long> mccToCat = new HashMap<>();
        Map<String, Long> categories = new HashMap<>();
        String csvFile = "E:/mcc.csv";
        String exportFile = "E:/mcc.sql";

        mapCategories(mccToCat, categories, csvFile);
        convertToSql(mccToCat, categories, exportFile);
    }

    private static void convertToSql(Map<String, Long> mccToCat, Map<String, Long> categories, String exportFile) {
        try(PrintWriter out = new PrintWriter(exportFile)  ){

            for (Map.Entry<String, Long> category : categories.entrySet()) {
                out.println("INSERT INTO EXPENSE_CATEGORY (id, desc) VALUES (" + category.getValue() + ", '" + category.getKey() + "');");
            }

            for (Map.Entry<String, Long> mcc : mccToCat.entrySet()) {
                out.println("INSERT INTO MCC_CODE (id, code, category) VALUES (S_MCC_CODE_ID.NEXTVAL, '" + mcc.getKey() +"', " + mcc.getValue() + ");");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void mapCategories(Map<String, Long> mccToCat, Map<String, Long> categories, String csvFile) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Long categoryCounter = 0L;
        Long currentCategoryId;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            //to skip the header
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] mcc = line.split(cvsSplitBy);
                System.out.println("Mcc [code= " + mcc[0] + " , cat=" + mcc[1] + "]");
                String code = mcc[0].trim();
                String category = mcc[1].trim();

                if (!categories.containsKey(category)){
                    categoryCounter++;
                    currentCategoryId = categoryCounter;
                    categories.put(category, currentCategoryId);
                } else {
                    currentCategoryId = categories.get(category);
                }

                mccToCat.put(code, currentCategoryId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeReader(br);
        }
    }

    private static void closeReader(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
