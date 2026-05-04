package exception;

// Exception métier personnalisée
// Hérite de Exception → c'est une checked exception
// Levée quand une condition d'échec est détectée dans lancerSimulation()
// Exemple : trop de boosters, carburant insuffisant
public class CarburantInsuffisant extends Exception {

    // Constructeur avec message explicatif
    // Le message est récupérable via e.getMessage() dans le catch
    public CarburantInsuffisant(String message) {
        super(message);
    }
}