public class Boosters {

    private String nom;
    private double pousseekN;
    private double masseTonnes;
    private double prixMillions;

    public Boosters(String nom, double pousseekN, double masseTonnes, double prixMillions) {
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

    @Override
    public String toString() {
        return nom + " | Poussée: " + pousseekN + "kN | Masse: "
                + masseTonnes + "t | Prix: " + prixMillions + "M€";
    }
}
