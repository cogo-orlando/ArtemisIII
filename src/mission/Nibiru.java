package mission;

// Mission personnelle : voyage vers la planète mystérieuse Nibiru
// Plus lointaine que Mars, mission habitée
public class Nibiru extends Missions {

    public Nibiru() {
        // Nom, distance en km, coefficient carburant, habitée requise
        super("Nibiru", 900000000, 0.000008, true);
    }

    // Calcul du carburant spécifique à cette mission
    // Même formule que les autres missions mais avec les paramètres de Nibiru
    @Override
    public double calculerCarburant(double masseTotale) {
        return (masseTotale * getDistanceKm() * getCoefficientCarburant()) / 1000;
    }
}