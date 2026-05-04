package lanceur;

public class Falcon9 extends Lanceurs {

    public Falcon9() {
        super("Falcon 9", 60, 549, 500, 22, 0, true);
    }

    @Override
    public String getDescription() {
        return "Falcon 9 - Lanceur de SpaceX, réutilisable. " +
                "Rapport qualité/prix imbattable.";
    }
}
