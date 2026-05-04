package mission;

public class Lune extends Missions {
    public Lune() {
        super("Lune", 400000, 0.005, true);
    }

    @Override
    public double calculerCarburant(double masseTotale) {
        return (masseTotale * getDistanceKm() * getCoefficientCarburant()) / 1000;
    }
}
