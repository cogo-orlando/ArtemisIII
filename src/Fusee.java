import java.util.ArrayList;
import java.util.List;

public class Fusee {

    private Lanceurs lanceur;
    private Capsules capsule;
    private List<Boosters> boosters;

    public Fusee(Lanceurs lanceur, Capsules capsule) {
        this.lanceur = lanceur;
        this.capsule = capsule;
        this.boosters = new ArrayList<>();
    }

    // Ajouter un booster
    public void ajouterBooster(Boosters booster) {
        boosters.add(booster);
    }

    // Calcul masse totale
    public double getMasseTotale() {
        double masse = lanceur.getMasseTonnes() + capsule.getMasseTonnes();
        for (Boosters b : boosters) {
            masse += b.getMasseTonnes();
        }
        return masse;
    }

    // Calcul prix total
    public double getPrixTotal() {
        double prix = lanceur.getPrixMillions() + capsule.getPrixMillions();
        for (Boosters b : boosters) {
            prix += b.getPrixMillions();
        }
        return prix;
    }

    // Getters
    public Lanceurs getLanceur() { return lanceur; }
    public Capsules getCapsule() { return capsule; }
    public List<Boosters> getBoosters() { return boosters; }

    @Override
    public String toString() {
        return "Fusée: " + lanceur.getNom() + " | Capsule: " + capsule.getNom()
                + " | Boosters: " + boosters.size()
                + " | Masse totale: " + getMasseTotale() + "t"
                + " | Prix total: " + getPrixTotal() + "M€";
    }
}