package com.alviss.shoesstore.models;

public class BaseModel {
    public String _id;

    public BaseModel(String _id) {
        this._id = _id;
    }

    public BaseModel() { }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
