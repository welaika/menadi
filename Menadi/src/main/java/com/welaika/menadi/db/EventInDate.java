package com.welaika.menadi.db;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "EventsInData")
public class EventInDate extends Model{

    @Column(name = "IdEID")
    public int idEID;

    @Column(name = "Data")
    public String data;

    @Column(name = "Room")
    public Room room;

    @Column(name = "IdA")
    public Event idA;

    @Column(name = "StartH")
    public String startH;

    @Column(name = "EndH")
    public String endH;

    @Column(name = "IsChecked")
    public boolean isChecked;

    public List<Relator> relators() {
        return RelatorEvent.getRelators(idEID);
    }

    public EventInDate() {
        super();
    }

    public EventInDate(int idEID, String data, Room room, Event idA, String startH, String endH) {

        this.idEID = idEID;
        this.data = data;
        this.room = room;
        this.idA = idA;
        this.startH = startH;
        this.endH = endH;
        this.isChecked = false;
    }

    public void setChecked() {
        this.isChecked = true;
    }

    public void setNotChecked() {
        this.isChecked = false;
    }

    public static List<EventInDate> getAll(String day) {
        return new Select()
                .from(EventInDate.class)
                .where("Data = ?", day)
                .orderBy("StartH ASC")
                .execute();
    }

    public static List<EventInDate> getEventsInRoom(Room room, String day) {
        return new Select()
                .from(EventInDate.class)
                .where("Room = " + room.getId() + " AND Data = " + day)
                .orderBy("StartH ASC")
                .execute();
    }

    public static EventInDate getEventInDateById(int id) {
        return new Select()
                .from(EventInDate.class)
                .where("IdEID = ?", id)
                .executeSingle();
    }




    public static List<EventInDate> getAllChecked(String day) {
        return new Select()
                .from(EventInDate.class)
                .where("Data = ?", day + " AND IsChecked = true")
                .orderBy("StartH ASC")
                .execute();
    }

    public static List<EventInDate> getCheckedEventsInRoom(Room room, String day) {
        return new Select()
                .from(EventInDate.class)
                .where("Room = " + room.getId() + " AND Data = " + day + " AND IsChecked = true")
                .orderBy("StartH ASC")
                .execute();
    }
}
