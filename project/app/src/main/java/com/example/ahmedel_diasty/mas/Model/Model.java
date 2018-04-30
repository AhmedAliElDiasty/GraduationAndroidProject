package com.example.ahmedel_diasty.mas.Model;

import java.util.ArrayList;

/**
 * Created by Ahmed El-Diasty on 05/03/2018.
 */

public class Model {

    ArrayList<Data> data ;

    public Model(ArrayList<Data> data) {
        this.data = data;
    }

    public Model() {
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}
