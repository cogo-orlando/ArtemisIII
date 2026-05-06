package lanceur;

// Classe abstraite représentant un lanceur spatial
// On ne peut pas faire new Lanceurs() directement
// On passe obligatoirement par une sous-classe (SaturneV, Ariane5, Falcon9, SLS)
public abstract class Lanceurs {

    // Attributs privés → encapsulation
    // Accessibles uniquement via les getters
    private String nom;
    private double prixMillions;
    private double masseTonnes;
    private double carburantMaxTonnes;    // Capacité max de carburant en tonnes
    private double chargeUtileMaxTonnes;  // Masse max que le lanceur peut transporter
    private int boostersMax;             // Nombre max de boosters acceptés
    private boolean habite;              // Peut-il transporter des astronautes ?

    // Constructeur commun à tous les lanceurs
    // Appelé via super(...) dans chaque sous-classe
    public Lanceurs(String nom, double prixMillions, double masseTonnes, double carburantMaxTonnes, double chargeUtileMaxTonnes, int boostersMax, boolean habite) {
        this.nom = nom;
        this.prixMillions = prixMillions;
        this.masseTonnes = masseTonnes;
        this.carburantMaxTonnes = carburantMaxTonnes;
        this.chargeUtileMaxTonnes = chargeUtileMaxTonnes;
        this.boostersMax = boostersMax;
        this.habite = habite;
    }

    // Méthode abstraite → chaque sous-classe DOIT la redéfinir
    // C'est la redéfinition (override) imposée par le sujet
    public abstract String getDescription();

    // Getters → seul moyen d'accéder aux attributs privés depuis l'extérieur
    public String getNom() { return nom; }
    public double getPrixMillions() { return prixMillions; }
    public double getMasseTonnes() { return masseTonnes; }
    public double getCarburantMaxTonnes() { return carburantMaxTonnes; }
    public double getChargeUtileMaxTonnes() { return chargeUtileMaxTonnes; }
    public int getBoostersMax() { return boostersMax; }
    public boolean isHabite() { return habite; }

    // Affichage du lanceur dans le menu
    // Redéfinition de toString() hérité de Object
    @Override
    public String toString() {
        return nom + " | Prix: " + prixMillions + "M€ | Charge utile: "
                + chargeUtileMaxTonnes + "t | Boosters max: " + boostersMax;
    }
}