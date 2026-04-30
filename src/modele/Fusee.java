package modele;

import capsule.Capsules;
import lanceur.Lanceurs;

import java.util.ArrayList;
import java.util.List;

// Classe Fusee : assemblage par COMPOSITION
// Une fusée N'HERITE de rien, elle CONTIENT un lanceur, une capsule et des boosters
public class Fusee {

    private Lanceurs lanceur;
    private Capsules capsule;
    private List<Boosters> boosters;

    // Constructeur 1 : fusée sans booster
    // SURCHARGE : même nom, paramètres différents
    public Fusee(Lanceurs lanceur, Capsules capsule) {
        this.lanceur = lanceur;
        this.capsule = capsule;
        this.boosters = new ArrayList<>();
    }

    // Constructeur 2 : fusée avec un booster direct
    // SURCHARGE : même nom que le constructeur du dessus mais avec un booster en plus
    public Fusee(Lanceurs lanceur, Capsules capsule, Boosters booster) {
        this.lanceur = lanceur;
        this.capsule = capsule;
        this.boosters = new ArrayList<>();
        this.boosters.add(booster);
    }

    // Constructeur 3 : fusée avec une liste de boosters
    // SURCHARGE : même nom mais avec une liste entière de boosters
    public Fusee(Lanceurs lanceur, Capsules capsule, List<Boosters> boosters) {
        this.lanceur = lanceur;
        this.capsule = capsule;
        this.boosters = new ArrayList<>(boosters);
    }

    // Ajoute un seul booster à la fusée
    // SURCHARGE avec la méthode du dessous
    public void ajouterBooster(Boosters booster) {
        boosters.add(booster);
    }

    // Ajoute plusieurs fois le même booster
    // SURCHARGE : même nom mais avec un nombre en plus
    public void ajouterBooster(Boosters booster, int nombre) {
        for (int i = 0; i < nombre; i++) {
            boosters.add(booster);
        }
    }

    // Calcul de la masse totale : lanceur + capsule + tous les boosters
    public double getMasseTotale() {
        double masse = lanceur.getMasseTonnes() + capsule.getMasseTonnes();
        for (Boosters b : boosters) {
            masse += b.getMasseTonnes();
        }
        return masse;
    }

    // Calcul du prix total : lanceur + capsule + tous les boosters
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
        return "Fusee: " + lanceur.getNom() + " | Capsule: " + capsule.getNom()
                + " | Boosters: " + boosters.size()
                + " | Masse totale: " + getMasseTotale() + "t"
                + " | Prix total: " + getPrixTotal() + "M€";
    }
}