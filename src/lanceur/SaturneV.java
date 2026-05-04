package lanceur;

// Sous-classe concrète de Lanceurs
// Héritage : SaturneV EST UN Lanceurs
// Récupère automatiquement tous les attributs et méthodes de Lanceurs
public class SaturneV extends Lanceurs {

    // Constructeur : appelle le constructeur parent avec les valeurs du sujet
    // super(nom, prix, masse, carburantMax, chargeUtile, boostersMax, habite)
    public SaturneV() {
        super("Saturne V", 1500, 2800, 2700, 140, 0, true);
    }

    // Redéfinition obligatoire de la méthode abstraite de Lanceurs
    @Override
    public String getDescription() {
        return "Saturne V - Lanceur légendaire du programme Apollo. " +
                "Le plus puissant jamais construit.";
    }
}