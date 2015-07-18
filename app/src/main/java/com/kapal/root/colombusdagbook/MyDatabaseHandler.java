package com.kapal.root.colombusdagbook;


import java.util.Date;


/**
 * Created by root on 7/18/15.
 */
public class MyDatabaseHandler {


    public String DB_NAME = "TestDb";

    private String TABLE_NAME = "my_test_table";


    // Columns
    private int TASK_ID;
    private String TASK_DESCRIPTION;
    private String TASK_LOCATION;
    private float TASK_LATITUDE;
    private float TASK_LONGTITUDE;
    private Date TASK_CREATED_AT;

    public MyDatabaseHandler (){

    }


    public String createTable(){

        String col1 = "TASK_ID INT(5) , ";
        String col2 = "TASK_DESCRIPTION VARCHAR(40) , ";
        String col3 = "TASK_LOCATION INT(30) , ";
        String col4 = "TASK_LATITUDE FLOAT , ";
        String col5 = "TASK_LONGTITUDE FLOAT , ";
        String col6 = "TASK_CRATED_AT DATETIME";


        String q = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+col1 + col2 + col3 + col4 + col5 + col6+"); ";

        return q;
    }

    public String insertRow(int id, String desc, String loca, double lat, double longt, Date dt)
    {
        String q = "INSERT INTO "+ TABLE_NAME +" VALUES ("+id+",'"+desc+"','"+loca+"',"+lat+","+longt+", null)" ;

        return q;
    }






}
