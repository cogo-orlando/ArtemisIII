package capsule;

public abstract class Capsules {

    private String nom;
    private double prixMillions;
    private double masseTonnes;
    private boolean habitee;
    private int occupantsMax;

    public Capsules(String nom, double prixMillions, double masseTonnes, boolean habitee, int occupantsMax) {
        this.nom = nom;
        this.prixMillions = prixMillions;
        this.masseTonnes = masseTonnes;
        this.habitee = habitee;
        this.occupantsMax = occupantsMax;
    }

    public abstract String getDescription();

    public String getNom() { return nom; }
    public double getPrixMillions() { return prixMillions; }
    public double getMasseTonnes() { return masseTonnes; }
    public boolean isHabitee() { return habitee; }
    public int getOccupantsMax() { return occupantsMax; }

    @Override
    public String toString() {
        return nom + " | Prix: " + prixMillions + "M€ | Masse: "
                + masseTonnes + "t | Habitée: " + habitee
                + " | Occupants max: " + occupantsMax;
    }
}
