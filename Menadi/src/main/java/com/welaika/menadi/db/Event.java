package com.welaika.menadi.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;


@Table(name = "Events")
public class Event extends Model {

    @Column(name = "IdE", unique = true)
    public Integer idE;

    @Column(name = "Title")
    public String title;

    @Column(name = "Description")
    public String description;

    public List<Relator> relatorsPROVA() {
        return RelatorEvent.getRelators(idE);
    }


    public Event() {super();}


    public Event(Integer idE, String title, String description) {
        this.idE = idE;
        this.title = title;
        this.description = description;
    }

    public static Event getEventById(int id) {
        return new Select()
                .from(Event.class)
                .where("IdE = ?", id)
                .executeSingle();
    }

}
