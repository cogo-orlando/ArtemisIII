public class Mars extends Missions {
    public Mars() {
        super("Mars", 225000000, 0.000015, true );
    }

    @Override
    public double calculerCarburant(double masseTotale) {
        return (masseTotale * getDistanceKm() * getCoefficientCarburant()) / 1000;
    }
}

