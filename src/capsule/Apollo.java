package capsule;

public class Apollo extends Capsules {

    public Apollo() {
        super("capsule.Apollo", 200, 5.6, true, 3);
    }

    @Override
    public String getDescription() {
        return "capsule.Apollo - Capsule légendaire du programme lunaire. " +
                "A transporté les premiers hommes sur la mission.Lune.";
    }
}
