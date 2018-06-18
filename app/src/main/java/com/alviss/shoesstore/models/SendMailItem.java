package com.alviss.shoesstore.models;



public class SendMailItem {

    private String _subject;
    private String _fromMail;
    private String _fromName;
    private String _mailTo;
    private String _html;

    public SendMailItem(String _subject, String _fromMail, String _fromName, String _mailTo, String _html) {
        this._subject = _subject;
        this._fromMail = _fromMail;
        this._fromName = _fromName;
        this._mailTo = _mailTo;
        this._html = _html;
    }

    public String get_subject() {
        return _subject;
    }

    public void set_subject(String _subject) {
        this._subject = _subject;
    }

    public String get_fromMail() {
        return _fromMail;
    }

    public void set_fromMail(String _fromMail) {
        this._fromMail = _fromMail;
    }

    public String get_fromName() {
        return _fromName;
    }

    public void set_fromName(String _fromName) {
        this._fromName = _fromName;
    }

    public String get_mailTo() {
        return _mailTo;
    }

    public void set_mailTo(String _mailTo) {
        this._mailTo = _mailTo;
    }

    public String get_html() {
        return _html;
    }

    public void set_html(String _html) {
        this._html = _html;
    }
}
