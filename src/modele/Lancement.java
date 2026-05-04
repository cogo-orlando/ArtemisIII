package modele;

import mission.Missions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Classe Lancement : représente le résultat archivé d'une simulation
// Une fois créé, un lancement ne peut pas être modifié (pas de setters)
public class Lancement {

    private Fusee fusee;          // La fusée utilisée pour ce lancement
    private Missions mission;     // La mission tentée
    private LocalDateTime date;   // Date et heure automatiques du lancement
    private boolean succes;       // true = succès, false = échec
    private String raison;        // Raison du succès ou de l'échec
    private double coutTotal;     // Coût total en millions d'euros

    // Constructeur : la date est automatiquement définie à maintenant
    public Lancement(Fusee fusee, Missions mission, boolean succes,
                     String raison, double coutTotal) {
        this.fusee = fusee;
        this.mission = mission;
        this.date = LocalDateTime.now(); // Date automatique
        this.succes = succes;
        this.raison = raison;
        this.coutTotal = coutTotal;
    }

    // Getters uniquement, pas de setters car un lancement est immuable
    public Fusee getFusee() { return fusee; }
    public Missions getMission() { return mission; }
    public LocalDateTime getDate() { return date; }
    public boolean isSucces() { return succes; }
    public String getRaison() { return raison; }
    public double getCoutTotal() { return coutTotal; }

    // Affichage complet du lancement pour l'historique
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