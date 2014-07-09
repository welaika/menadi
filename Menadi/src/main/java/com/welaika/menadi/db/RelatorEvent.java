package com.welaika.menadi.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

@Table(name = "RelatorsEvents")
public class RelatorEvent extends Model {

    @Column(name = "IdR")
    public Integer idR;

    @Column(name = "Relator")
    public Relator relator;

    @Column(name = "IdEInD")
    public Integer idEInD;

    @Column(name = "EventInD")
    public EventInDate eventInD;

    public RelatorEvent() {
        super();
    }

    public RelatorEvent(int idR, Relator relator, int idEInD, EventInDate eventInD) {
        this.idR = idR;
        this.relator = relator;
        this.idEInD = idEInD;
        this.eventInD = eventInD;
    }

    public static List<RelatorEvent> getEvents(int idRel) {
        return new Select()
                .from(RelatorEvent.class)
                .where("IdR = ?", idRel)
                .execute();
    }


    public static List<Relator> getRelators(int idEventInDate) {
        List<RelatorEvent> list_r2e =  new Select()
                .from(RelatorEvent.class)
                .where("IdEInD = ?", idEventInDate)
                .execute();

        List<Relator> relators = new ArrayList<Relator>();

        for(RelatorEvent r2e : list_r2e){
            relators.add(r2e.relator);
        }

        return relators;
    }
}
