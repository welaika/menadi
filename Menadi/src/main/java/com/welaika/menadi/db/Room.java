package com.welaika.menadi.db;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Rooms")
public class Room extends Model {

    @Column(name = "OrderNumber", unique = true)
    public Integer orderNumber;

    @Column(name = "IdS", unique = true)
    public Integer idS;

    @Column(name = "Name")
    public String name;

    @Column(name = "Info")
    public String info;

    public Room() {

        super();
    }

    public Room(Integer orderNumber, Integer idS, String name, String info) {

        this.orderNumber = orderNumber;
        this.idS = idS;
        this.name = name;
        this.info = info;
    }

    public static List<Room> getAll() {
        return new Select()
                .from(Room.class)
                .orderBy("OrderNumber ASC, Name ASC")
                .execute();
    }


    public static Room getRoomById(int idRoom) {
        return new Select()
                .from(Room.class)
                .where("IdS = ?", idRoom)
                .executeSingle();
    }

    public static Room getRoomByName(String roomName) {
        return new Select()
                .from(Room.class)
                .where("Name = ?", roomName)
                .executeSingle();
    }
}
