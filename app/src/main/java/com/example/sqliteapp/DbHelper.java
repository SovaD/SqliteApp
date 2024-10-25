package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(@Nullable Context context) {
        super(context, "contacts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table people(" +
                "_id INTEGER primary key autoincrement," +
                "name TEXT not null," +
                "email TEXT not null unique," +
                "image TEXT)");
//add name/email
//        db.execSQL("insert into people(name,email) values (\"Tom\",\"tom@gmail.com\")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists people");
        onCreate(db);
    }

    public List<Person> getData() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from people", null);
        List<Person> people = new ArrayList<>();

        while (cursor.moveToNext()) {
            Person person = new Person(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            person.setId(cursor.getInt(0));
            people.add(person);
        }
        return people;
    }

    public Person getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from people where _id=?",
                new String[]{id + ""});

        if (cursor.getCount() > 0) {

            cursor.moveToNext();
            Person person = new Person(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            person.setId(cursor.getInt(0));

            return person;
        }
        return null;
    }

    public Boolean insertData(Person person){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content= new ContentValues();
        content.put("name",person.getName());
        content.put("email",person.getEmail());
        content.put("image",person.getImage());

        long result= db.insert("people",null,content);
        return result==-1?false:true;
    }
}