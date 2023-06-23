import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
public class EmergencyRoom {
    private final Heap<Patient> patients;
    
    public EmergencyRoom(Comparator<Patient> comparator){
        patients = new Heap<>(comparator);
    }
    public void checkIn(Patient patient, Severity severity){
        patient.setSeverity(severity);
        patient.setArrivalTime(LocalTime.now());
        patients.add(patient);
    }

    public ArrayList<Patient> getPatients(){
        return patients.returnList();
    }

    public Patient admit(){
        if(patients.isEmpty()){
            return null;
        }
        return patients.remove();
    }

}
