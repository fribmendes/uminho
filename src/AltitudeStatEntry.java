
import java.util.GregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaorodrigues
 */
public class AltitudeStatEntry extends DistanceStatEntry{
    private int totalAltitude;
    private double avgAltitude;
    
    public AltitudeStatEntry(){
        super();
        this.totalAltitude = 0;
        this.avgAltitude = 0.0;
    }

    public AltitudeStatEntry(int totalAltitude, double avgAltitude) {
        this.totalAltitude = totalAltitude;
        this.avgAltitude = avgAltitude;
    }

    public AltitudeStatEntry(String name, int calories, GregorianCalendar duration, int totalDistance, double avgDistance, int totalAltitude, double avgAltitude) {
        super(name, calories, duration, totalDistance, avgDistance);
        this.totalAltitude = totalAltitude;
        this.avgAltitude = avgAltitude;
    }

    public AltitudeStatEntry(String name, int totalCalories, double avgCalories, GregorianCalendar totalDuration, GregorianCalendar avgDuration, int nrEntries, int totalDistance, double avgDistance,  int totalAltitude, double avgAltitude) {
        super(name, totalCalories, avgCalories, totalDuration, avgDuration, nrEntries, totalDistance, avgDistance);
        this.totalAltitude = totalAltitude;
        this.avgAltitude = avgAltitude;
    }

    public AltitudeStatEntry(AltitudeActivity act) {
        super(act);
        this.totalAltitude = 0;
        this.avgAltitude = 0.0;
        
        updateStat(act);
    }
    
    public AltitudeStatEntry(AltitudeStatEntry ase){
        super(ase);
        
        this.totalAltitude = ase.getTotalAltitude();
        this.avgAltitude = ase.getAvgAltitude();
    }
    
    
    public void updateStat(AltitudeActivity act){
        super.updateStat(act);
        
        this.totalAltitude += act.getAltitude();
        this.avgAltitude = this.totalAltitude/this.getNrEntries();
    }
    
    public void updateStat(int calories, GregorianCalendar duration,int distance, int altitude){
        super.updateStat(calories, duration, distance);
        
        this.totalAltitude += altitude;
        this.avgAltitude = this.totalAltitude/this.getNrEntries();
    }
    
    

    public int getTotalAltitude() {
        return totalAltitude;
    }

    public double getAvgAltitude() {
        return avgAltitude;
    }

    public void setTotalAltitude(int totalAltitude) {
        this.totalAltitude = totalAltitude;
    }

    public void setAvgAltitude(double avgAltitude) {
        this.avgAltitude = avgAltitude;
    }
    
    
    public AltitudeStatEntry clone() {
       return (new AltitudeStatEntry(this));
    }
    
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("\nTotal altitude: ");
        result.append(this.totalAltitude);
        result.append("\nAverage altitude per session: ");
        result.append(this.avgAltitude);
        
        return super.toString() + result.toString();
    }
    
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass() ) return false;
       
        AltitudeStatEntry ase = (AltitudeStatEntry) o;
       
       return ( super.equals(o) && this.totalAltitude == ase.getTotalAltitude() && this.avgAltitude == ase.getAvgAltitude() );
   }
    
}
