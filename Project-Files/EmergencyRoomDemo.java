import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class EmergencyRoomDemo {
    private static JTextArea patientInfoArea;
    private static EmergencyRoom eRoom;

    public static void main(String[] args) throws InterruptedException {
        PatientComparator comparator = new PatientComparator();
        eRoom = new EmergencyRoom(comparator);

        eRoom.checkIn(new Patient("Mirabella Jones", LocalDate.of(1973, 7, 11)), Severity.SEVEN);
        TimeUnit.MILLISECONDS.sleep(10);
        eRoom.checkIn(new Patient("Ruth Mendez", LocalDate.of(1965, 1, 22)), Severity.SEVEN);
        TimeUnit.MILLISECONDS.sleep(10);
        eRoom.checkIn(new Patient("Melvin Ingram", LocalDate.of(1965, 1, 22)), Severity.SEVEN);
        TimeUnit.MILLISECONDS.sleep(10);
        eRoom.checkIn(new Patient("Tara Silva", LocalDate.of(1975, 5, 8)), Severity.EIGHT);
        TimeUnit.MILLISECONDS.sleep(10);
        eRoom.checkIn(new Patient("Jeff Barnes", LocalDate.of(1968, 12, 19)), Severity.EIGHT);
        TimeUnit.MILLISECONDS.sleep(10);

        JFrame frame = new JFrame("Emergency Room Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        JLabel titleLabel = new JLabel("Emergency Room Demo");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton viewPatientsButton = new JButton("View Patients");
        viewPatientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllPatientInfo();
            }
        });

        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewPatient();
            }
        });

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAllPatientInfo();
            }
        });

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewPatientsButton);
        buttonPanel.add(addPatientButton);
        buttonPanel.add(refreshButton);

        patientInfoArea = new JTextArea();
        patientInfoArea.setEditable(false); // Make the text area non-editable

        JScrollPane scrollPane = new JScrollPane(patientInfoArea);
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Set preferred size

        frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }


    private static void addNewPatient() {
        String name = JOptionPane.showInputDialog("Enter patient name:");
        String dobString = JOptionPane.showInputDialog("Enter patient date of birth (yyyy-MM-dd):");
        LocalDate dob = LocalDate.parse(dobString);

        // Use a JComboBox instead of JOptionPane for severity selection
        JComboBox<Severity> severityComboBox = new JComboBox<>(Severity.values());
        severityComboBox.setSelectedItem(Severity.SEVEN); // Set default selection
        int result = JOptionPane.showConfirmDialog(null, severityComboBox, "Select patient severity",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Severity severity = (Severity) severityComboBox.getSelectedItem();

            Patient newPatient = new Patient(name, dob, severity, LocalTime.now());
            eRoom.checkIn(newPatient, severity); // Add the patient to eRoom

            showAllPatientInfo();
        }
    }

    private static void showAllPatientInfo() {
    List<Patient> patientList = eRoom.getPatients(); // Fetch the patient list from the EmergencyRoom
    StringBuilder infoBuilder = new StringBuilder();
    for (Patient patient : patientList) {
        infoBuilder.append(patient.toString()).append("\n");
    }
    patientInfoArea.setText(infoBuilder.toString());
}

}
