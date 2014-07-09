package com.welaika.menadi.db;

import android.widget.ArrayAdapter;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Relators")
public class Relator extends Model {

    @Column(name = "IdR", unique = true)
    public Integer idR;

    @Column(name = "Name")
    public String name;

    @Column(name = "Info")
    public String info;

    @Column(name = "Email")
    public String email;

    @Column(name = "Url")
    public String url;


    public List<RelatorEvent> events() {
        return RelatorEvent.getEvents(idR);
    }


    public Relator() {

        super();
    }

    public Relator(Integer idR, String name, String email, String url, String info) {
        this.idR = idR;
        this.name = name;
        this.email = email;
        this.url = url;
        this.info = info;
    }


    public static List<Relator> getAll() {
        return new Select()
                .from(Relator.class)
                .orderBy("Name ASC")
                .execute();
    }

    public static Relator getRelatorById(int id) {
        return new Select()
                .from(Relator.class)
                .where("IdR = ?", id)
                .executeSingle();
    }
}