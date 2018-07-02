package com.example.ahmedel_diasty.mas.Sqlite;

import java.util.ArrayList;

public class ArrayRowObjects {
    ArrayList<RowObject> rowObjects;

    public ArrayRowObjects(ArrayList<RowObject> rowObjects) {
        this.rowObjects = rowObjects;
    }

    public ArrayRowObjects() {
    }

    public ArrayList<RowObject> getRowObjects() {
        return rowObjects;
    }

    public void setRowObjects(ArrayList<RowObject> rowObjects) {
        this.rowObjects = rowObjects;
    }
}
