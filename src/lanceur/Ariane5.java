package lanceur;

public class Ariane5 extends Lanceurs {

    public Ariane5() {
        super("Ariane 5", 180, 777, 700, 20, 2, false);
    }

    @Override
    public String getDescription() {
        return "Ariane 5 - Lanceur européen fiable. " +
                "Spécialisé dans les satellites, non habité.";
    }
}
