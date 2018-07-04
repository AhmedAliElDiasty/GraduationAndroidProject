package com.example.ahmedel_diasty.mas.Sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Ahmed El-Diasty on 19/11/2017.
 */

public class DBConnection {

    final String  DEFAULT = "default";
    private DBMain dbMain;


    public DBConnection(Context context){
        dbMain = new DBMain(context);
    }


    // Insert Data in Database
    public long dataInsert(String lectureName,String description,String time){
        SQLiteDatabase sqLiteDatabase = dbMain.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBMain.LECTURE_NAME,lectureName);
        contentValues.put(DBMain.DESCRIPTION,description);
        contentValues.put(DBMain.TIME,time);
        return sqLiteDatabase.insert(DBMain.TABLE_NAME,null,contentValues);
    }

    // Retrieve Data from Database (select)
    public ArrayRowObjects retrieveData(){

        RowObject rowObject = new RowObject();
        ArrayList<RowObject> arrayList = new ArrayList<>();
        ArrayRowObjects arrayRowObjects = new ArrayRowObjects();


        SQLiteDatabase sqLiteDatabase = dbMain.getWritableDatabase();
        String []columns = {DBMain.LECTURE_NAME,DBMain.DESCRIPTION,DBMain.TIME};
        String activityName , description, time= DEFAULT;

        int i = 0;
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(DBMain.TABLE_NAME,columns,
                null,null,null,null,null);
//        StringBuilder stringBuffer = new StringBuilder();

        while (cursor.moveToNext()){
            activityName = cursor.getString(0);
            description = cursor.getString(1);
            time = cursor.getString(2);
//            stringBuffer.append(activityName).append(" ").append(description).append(" ").append(time).append("\n");
            rowObject.setActivityName(activityName);
            rowObject.setDescription(description);
            rowObject.setTime(time);
            arrayList.add(i,rowObject);
            i++;
//            Log.i("+++++++++++++++",""+rowObject);
        }
        arrayRowObjects.setRowObjects(arrayList);
        return arrayRowObjects;
    }



    // Delete Data in Database
    public int deleteRow(String name){
        SQLiteDatabase sqLiteDatabase = dbMain.getWritableDatabase();
        String whereArgs[] = {name};
        String userName = DBMain.DESCRIPTION+" =? ";
        return sqLiteDatabase.delete(DBMain.TABLE_NAME,userName,whereArgs);
    }

    // Delete All data
    public void deleteAll(){
        SQLiteDatabase sqLiteDatabase = dbMain.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+ DBMain.TABLE_NAME);
    }




//    This is a class is inner other class for more security
//    We use The inner variables in outer , but we can't change it

    static public class DBMain extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "notification.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "firstTable";
        private static final String UID = "id";
        private static final String LECTURE_NAME = "lectureName";
        private static final String DESCRIPTION = "description";
        private static final String TIME = "time";


        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
        private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "
                +UID+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", "+LECTURE_NAME+" VARCHAR(255), "+DESCRIPTION+" VARCHAR(255), "+TIME+" VARCHAR(255));";
        private Context context;



        public DBMain(Context context) {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try{
                Toast.makeText(context,"OnCreate Method",Toast.LENGTH_SHORT).show();
                sqLiteDatabase.execSQL(CREATE_TABLE);
            }catch (SQLException e){
                Toast.makeText(context,"This error due to "+e,Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                Toast.makeText(context,"OnUpgrade Method",Toast.LENGTH_SHORT).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (SQLException e){
                Toast.makeText(context,"This error due to "+e,Toast.LENGTH_SHORT).show();
            }
        }
    }

}
