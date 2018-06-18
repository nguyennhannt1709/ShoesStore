package com.alviss.shoesstore.models;

/**
 * Created by nguyennhan on 6/18/18.
 */

public class NotificationItem extends BaseModel {
    public String _title;
    public String _content;
    public String _subject;

    public NotificationItem(String _id, String _title, String _content, String _subject) {
        super(_id);
        this._title = _title;
        this._content = _content;
        this._subject = _subject;
    }

    public NotificationItem(String _title, String _content, String _subject) {
        this._title = _title;
        this._content = _content;
        this._subject = _subject;
    }
}

