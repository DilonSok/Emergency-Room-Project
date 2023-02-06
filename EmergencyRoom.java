import java.util.Comparator;
public class EmergencyRoom {
    private final Heap<Patient> patients;

    public EmergencyRoom(Comparator<Patient> comparator){
        patients = new Heap<>(comparator);
    }
    public void checkIn(Patient patient, Severity severity)
}
