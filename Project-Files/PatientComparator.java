import java.util.Comparator;
public class PatientComparator implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {
        int ret = Integer.compare(o2.getSeverity().val(), o1.getSeverity().val()); //comparing severity first
        if(ret==0){     //if severities are equal, compare age
            ret = Integer.compare(o2.getAge(),o1.getAge());
            if(ret == 0){   //if ages are equal, compare their arrival times
                ret = (o1.getArrivalTime()).compareTo(o2.getArrivalTime());
            }
        }
        return ret;
    }
    
}
