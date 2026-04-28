package capsule;

public class CargoDragon extends Capsules {

    public CargoDragon() {
        super("Cargo Dragon", 100, 9.5, false, 0);
    }

    @Override
    public String getDescription() {
        return "Cargo Dragon - Capsule cargo de SpaceX. " +
                "Non habitée, transporte du matériel vers l'mission.ISS.";
    }
}