package mission;

public class ISS extends Missions {
    public ISS() {
        super("mission.ISS", 400, 1.2, true);
    }

    @Override
    public double calculerCarburant(double masseTotale) {
        return (masseTotale * getDistanceKm() * getCoefficientCarburant()) / 1000;
    }
}
