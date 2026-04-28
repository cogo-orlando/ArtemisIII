package capsule;

public class CrewDragon extends Capsules {

    public CrewDragon() {
        super("Crew Dragon", 150, 12.0, true, 7);
    }

    @Override
    public String getDescription() {
            return "Crew Dragon - Capsule habitée de SpaceX. " +
                    "Transporte jusqu'à 7 astronautes vers l'mission.ISS.";
        }
}
