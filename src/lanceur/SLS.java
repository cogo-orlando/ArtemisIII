package lanceur;

public class SLS extends Lanceurs {

    public SLS() {
        super("SLS", 2000, 2600, 2600, 130, 2, true);
    }

    @Override
    public String getDescription() {
        return "lanceur.SLS - Space Launch System de la NASA. " +
                "Successeur de Saturne V pour les missions Artemis.";
    }
}
