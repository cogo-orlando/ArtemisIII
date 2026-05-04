package modele;

// Classe Boosters : propulseur d'appoint attaché à la fusée
// Classe simple, non abstraite, instanciée directement dans le Simulateur
public class Boosters {

    private String nom;
    private double pousseekN;    // Poussée additionnelle en kilonewtons
    private double masseTonnes;  // Masse du booster en tonnes
    private double prixMillions; // Prix en millions d'euros

    // Constructeur avec tous les attributs
    public Boosters(String nom, double pousseekN, double masseTonnes,
                    double prixMillions) {
        this.nom = nom;
        this.pousseekN = pousseekN;
        this.masseTonnes = masseTonnes;
        this.prixMillions = prixMillions;
    }

    // Getters
    public String getNom() { return nom; }
    public double getPousseekN() { return pousseekN; }
    public double getMasseTonnes() { return masseTonnes; }
    public double getPrixMillions() { return prixMillions; }

    // Affichage du booster dans le menu
    @Override
    public String toString() {
        return nom + " | Poussee: " + pousseekN + "kN | Masse: "
                + masseTonnes + "t | Prix: " + prixMillions + "M€";
    }
}