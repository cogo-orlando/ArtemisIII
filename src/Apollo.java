public class Apollo extends Capsules {

    public Apollo() {
        super("Apollo", 200, 5.6, true, 3);
    }

    @Override
    public String getDescription() {
        return "Apollo - Capsule légendaire du programme lunaire. " +
                "A transporté les premiers hommes sur la Lune.";
    }
}
