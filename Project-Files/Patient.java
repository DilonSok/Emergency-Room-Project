import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class Patient {
    private static int count = 0;
    private int id;
    private String name;
    private LocalDate DOB;
    public Severity severity;
    public LocalTime arrivalTime;

    public Patient(String name, LocalDate DOB, Severity severity, LocalTime arrivalTime){
        this.id = count++;
        this.name = name;
        this.DOB = DOB;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }
    public Patient(String name, LocalDate DOB){
        this.name = name;
        this.DOB = DOB;
    }

    public int getId(){
        return id;
    }
    public int getAge(){
        LocalDate currentDate = LocalDate.now();
        return Period.between(DOB, currentDate).getYears();
    }
    public String getName(){
        return name;
    }
    
    public LocalDate getDOB(){
        return DOB;
    }
    public Severity getSeverity(){
        return severity;
    }
    public LocalTime getArrivalTime(){
        return arrivalTime;
    }

    public void setName(String givenName){
        name = givenName;
    }
    public void setDOB(LocalDate givenDate){
        DOB = givenDate;
    }
    public void setSeverity(Severity givenSeverity){
        severity = givenSeverity;
    }
    public void setArrivalTime(LocalTime givenTime){
        arrivalTime = givenTime;
    }

    public String toString(){
        return name + "\n DOB: "
             + DOB + "\n Severity: " 
             + severity + "\n Arrival Time: " 
             + arrivalTime + "\n";
    }
}
