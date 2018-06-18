package com.alviss.shoesstore.models;

import com.alviss.shoesstore.utils.ProtocolModel;

public class UserItem extends BaseModel implements ProtocolModel {
    public String _email;

    public UserItem(String _id, String _email) {
        super(_id);
        this._email = _email;
    }

    public UserItem(String _email) {
        this._email = _email;
    }


}
