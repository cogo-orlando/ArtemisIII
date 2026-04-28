package mission;

public abstract class Missions {

    private String nom;
    private double distanceKm;
    private double coefficientCarburant;
    private boolean habiteeRequise;

    public Missions(String nom, double distanceKm,
                   double coefficientCarburant, boolean habiteeRequise) {
        this.nom = nom;
        this.distanceKm = distanceKm;
        this.coefficientCarburant = coefficientCarburant;
        this.habiteeRequise = habiteeRequise;
    }

    // Méthode abstraite → chaque mission calcule son carburant
    public abstract double calculerCarburant(double masseTotale);

    // Getters
    public String getNom() { return nom; }
    public double getDistanceKm() { return distanceKm; }
    public double getCoefficientCarburant() { return coefficientCarburant; }
    public boolean isHabiteeRequise() { return habiteeRequise; }

    @Override
    public String toString() {
        return nom + " | Distance: " + (long)distanceKm + "km | Habitee requise: " + habiteeRequise;
    }
}
