public abstract class Lanceurs {

    private String nom;
    private double prixMillions;
    private double masseTonnes;
    private double carburantMaxTonnes;
    private double chargeUtileMaxTonnes;
    private int boostersMax;
    private boolean habite;

    public Lanceurs(String nom, double prixMillions, double masseTonnes,
                    double carburantMaxTonnes, double chargeUtileMaxTonnes,
                    int boostersMax, boolean habite) {

        this.nom = nom;
        this.prixMillions = prixMillions;
        this.masseTonnes = masseTonnes;
        this.carburantMaxTonnes = carburantMaxTonnes;
        this.chargeUtileMaxTonnes = chargeUtileMaxTonnes;
        this.boostersMax = boostersMax;
        this.habite = habite;
    }

    public abstract String getDescription();
    public String getNom() { return nom; }
    public double getPrixMillions() { return prixMillions; }
    public double getMasseTonnes() { return masseTonnes; }
    public double getCarburantMaxTonnes() { return carburantMaxTonnes; }
    public double getChargeUtileMaxTonnes() { return chargeUtileMaxTonnes; }
    public int getBoostersMax() { return boostersMax; }
    public boolean isHabite() { return habite; }

    @Override
    public String toString() {
        return nom + " | Prix: " + prixMillions + "M€ | Charge utile: "
                + chargeUtileMaxTonnes + "t | Boosters max: " + boostersMax;
    }
}
