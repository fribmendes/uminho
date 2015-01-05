/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author tiago
 */
public class Event extends BasicModel {
    private Integer id;
    private GregorianCalendar date;
    private Float amountRaised;
    private Integer participantsNr;
    private String location;
    private String observations;
    private HashSet<Volunteer> volunteers;

    public Event() {}
    
    public Event(GregorianCalendar date, Float amountRaised, Integer participantsNr, String location, String observations, HashSet<Volunteer> volunteers) {
        this.date = date;
        this.amountRaised = amountRaised;
        this.participantsNr = participantsNr;
        this.location = location;
        this.observations = observations;
        this.volunteers = new HashSet(volunteers);
    }
    
    public Event(Event e) {
        this.date = e.getDate();
        this.amountRaised = e.getAmountRaised();
        this.participantsNr = e.getParticipantsNr();
        this.location = e.getLocation();
        this.observations = e.getObservations();
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public Float getAmountRaised() {
        return amountRaised;
    }

    public Integer getParticipantsNr() {
        return participantsNr;
    }

    public String getLocation() {
        return location;
    }

    public String getObservations() {
        return observations;
    }

    public HashSet<Integer> getVolunteers() {
        return new HashSet(volunteers);
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void setAmountRaised(Float amountRaised) {
        this.amountRaised = amountRaised;
    }

    public void setParticipantsNr(Integer participantsNr) {
        this.participantsNr = participantsNr;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setVolunteers(HashSet<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }
    
    @Override
     public Event clone(){
        return new Event(this);
    }
        
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", ");
        sb.append(date);
        sb.append(", ");
        sb.append(amountRaised);
        sb.append(", ");
        sb.append(participantsNr);
        sb.append(", ");
        sb.append(location);
        sb.append(", ");
        sb.append(observations);
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass() ) return false;
       
        Event e = (Event) o;
        
        return (super.equals(o) && this.date.equals(e.getDate()) && this.amountRaised == e.getAmountRaised() && this.participantsNr == e.getParticipantsNr() && this.location.equals(e.getLocation()) && this.observations.equals(e.getObservations()));
    }
}
