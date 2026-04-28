package mission;

public class Orbite extends Missions {
    public Orbite() {
        super("mission.Orbite terrestre", 400, 1.0, false  );
    }

    @Override
    public double calculerCarburant(double masseTotale) {
        return (masseTotale * getDistanceKm() * getCoefficientCarburant()) / 1000;
    }
}
