public class Orion extends Capsules {

    public Orion() {
        super("Orion", 300, 10.4, true, 4);
    }

    @Override
    public String getDescription() {
        return "Orion - Capsule habitée de la NASA. " +
                "Conçue pour les missions Artemis vers la Lune.";
    }
}
