import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lancement {

    private Fusee fusee;
    private Missions mission;
    private LocalDateTime date;
    private boolean succes;
    private String raison;
    private double coutTotal;

    public Lancement(Fusee fusee, Missions mission, boolean succes,
                     String raison, double coutTotal) {
        this.fusee = fusee;
        this.mission = mission;
        this.date = LocalDateTime.now();
        this.succes = succes;
        this.raison = raison;
        this.coutTotal = coutTotal;
    }

    // Getters
    public Fusee getFusee() { return fusee; }
    public Missions getMission() { return mission; }
    public LocalDateTime getDate() { return date; }
    public boolean isSucces() { return succes; }
    public String getRaison() { return raison; }
    public double getCoutTotal() { return coutTotal; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Date: " + date.format(formatter)
                + " | Lanceur: " + fusee.getLanceur().getNom()
                + " | Mission: " + mission.getNom()
                + " | " + (succes ? "Succes" : "Echec")
                + " | Raison: " + raison
                + " | Cout: " + coutTotal + "M€";
    }
}