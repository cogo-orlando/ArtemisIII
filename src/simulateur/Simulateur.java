package simulateur;

import capsule.*;
import exception.CarburantInsuffisant;
import lanceur.*;
import mission.*;
import modele.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulateur {

    // Instance unique du simulateur (pattern Singleton)
    // Une seule instance dans tout le programme
    private static Simulateur instance;

    // Liste qui stocke tous les lancements effectués pendant la session
    private List<Lancement> historique;

    // Catalogues de composants disponibles pour assembler la fusée
    private List<Lanceurs> lanceurs;
    private List<Capsules> capsules;
    private List<Boosters> boosters;
    private List<Missions> missions;

    // Scanner pour lire les entrées de l'utilisateur dans la console
    private Scanner scanner;

    // Prix du kérosène par tonne en euros
    private static final double PRIX_KEROSENE_PAR_TONNE = 1200;
    // Probabilité d'échec aléatoire (5%)
    private static final double PROBABILITE_ECHEC = 0.05;
    // Nom du fichier de sauvegarde de l'historique
    private static final String FICHIER_HISTORIQUE = "historique.csv";

    // Constructeur PRIVE → on ne peut pas faire new Simulateur() depuis l'extérieur
    private Simulateur() {
        historique = new ArrayList<>();
        scanner = new Scanner(System.in);
        initialiserCatalogues();
        System.out.println("Historique charge !");
    }

    // Méthode statique qui retourne l'instance unique du Simulateur
    // Si elle n'existe pas encore, on la crée
    public static Simulateur getInstance() {
        if (instance == null) {
            instance = new Simulateur();
        }
        return instance;
    }

    // Initialise tous les catalogues de composants disponiblese
    // Polymorphisme : on stocke des SaturneV, Ariane5... dans une List<Lanceurs>
    private void initialiserCatalogues() {

        //Lanceurs disponibles
        lanceurs = new ArrayList<>();
        lanceurs.add(new SaturneV());
        lanceurs.add(new Ariane5());
        lanceurs.add(new Falcon9());
        lanceurs.add(new SLS());

        //Capsules disponibles
        capsules = new ArrayList<>();
        capsules.add(new Orion());
        capsules.add(new CrewDragon());
        capsules.add(new Apollo());
        capsules.add(new CargoDragon());

        //Boosters disponibles
        // Boosters est une classe simple, on crée directement les instances
        boosters = new ArrayList<>();
        boosters.add(new Boosters("EAP (Ariane)", 6470, 270, 30));
        boosters.add(new Boosters("SRB (Shuttle)", 12500, 590, 55));
        boosters.add(new Boosters("BE-3", 490, 25, 12));

        //Missions disponibles
        missions = new ArrayList<>();
        missions.add(new Orbite());
        missions.add(new ISS());
        missions.add(new Lune());
        missions.add(new Mars());
        missions.add(new Nibiru());
    }

    //Menu principal
    public void start() {
        // On charge l'historique depuis le fichier CSV au démarrage
        chargerHistorique();
        boolean running = true;
        while (running) {
            System.out.println("\n================================");
            System.out.println(" ARTEMIS III ");
            System.out.println("================================");
            System.out.println("1. Nouvelle mission");
            System.out.println("2. Historique des missions");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            int choix = lireEntier();

            switch (choix) {
                case 1:
                    nouvelleMission();
                    break;
                case 2:
                    afficherHistorique();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    running = false;
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    // Gère le flux complet d'une nouvelle mission :
    // 1. Choix du lanceur
    // 2. Choix de la capsule
    // 3. Choix des boosters
    // 4. Choix de la mission
    // 5. Résumé et confirmation
    private void nouvelleMission() {
        System.out.println("\n--- Choisir un lanceur ---");
        for (int i = 0; i < lanceurs.size(); i++) {
            System.out.println((i + 1) + ". " + lanceurs.get(i));
        }
        System.out.print("Votre choix : ");
        int choixLanceur = lireEntier();
        while (choixLanceur < 1 || choixLanceur > lanceurs.size()) {
            System.out.println("Choix invalide, reessayez :");
            choixLanceur = lireEntier();
        }
        Lanceurs lanceur = lanceurs.get(choixLanceur - 1);

        // Capsule
        System.out.print("Votre choix : ");
        int choixCapsule = lireEntier();
        while (choixCapsule < 1 || choixCapsule > capsules.size()) {
            System.out.println("Choix invalide, reessayez :");
            choixCapsule = lireEntier();
        }
        Capsules capsule = capsules.get(choixCapsule - 1);

        // Création de la fusée par composition : lanceur + capsule
        Fusee fusee = new Fusee(lanceur, capsule);

        // Choix boosters → on peut en ajouter plusieurs
        System.out.println("\n--- Ajouter des boosters (0 pour terminer) ---");
        for (int i = 0; i < boosters.size(); i++) {
            System.out.println((i + 1) + ". " + boosters.get(i));
        }

        boolean ajouterBoosters = true;
        while (ajouterBoosters) {
            System.out.print("Votre choix (0 pour terminer) : ");
            int choixBooster = lireEntier();
            if (choixBooster == 0) {
                ajouterBoosters = false;
            } else if (choixBooster >= 1 && choixBooster <= boosters.size()) {
                fusee.ajouterBooster(boosters.get(choixBooster - 1));
                System.out.println("Booster ajouté ! Total : " + fusee.getBoosters().size());
            } else {
                System.out.println("Choix invalide !");
            }
        }

        System.out.print("Votre choix (0 pour passer) : ");
        int choixBooster = lireEntier();
        // On ajoute le booster seulement si l'utilisateur n'a pas choisi 0
        if (choixBooster != 0) {
            fusee.ajouterBooster(boosters.get(choixBooster - 1));
        }

        // Mission
        System.out.print("Votre choix : ");
        int choixMission = lireEntier();
        while (choixMission < 1 || choixMission > missions.size()) {
            System.out.println("Choix invalide, reessayez :");
            choixMission = lireEntier();
        }
        Missions mission = missions.get(choixMission - 1);

        // Affiche le résumé complet de la fusée et de la mission choisie
        System.out.println("\n--- Resume ---");
        System.out.println(fusee);
        System.out.println("Mission : " + mission.getNom());

        // Demande confirmation avant de lancer la simulation
        System.out.print("\nConfirmer le lancement ? (1 = Oui, 0 = Non) : ");
        if (lireEntier() == 1) {
            lancerSimulation(fusee, mission);
        }
    }

    // Coeur de la simulation : vérifie toutes les conditions et détermine le résultat
    private void lancerSimulation(Fusee fusee, Missions mission) {
        boolean succes = true;
        String raison = "Lancement réussi";

        try {
            // Vérification 1 : trop de boosters par rapport à la limite du lanceur
            if (fusee.getBoosters().size() > fusee.getLanceur().getBoostersMax()) {
                throw new CarburantInsuffisant("Trop de boosters");
            }

            // Vérification 2 : masse totale dépasse la charge utile max du lanceur
            if (fusee.getMasseTotale() > fusee.getLanceur().getChargeUtileMaxTonnes()) {
                succes = false;
                raison = "Surcharge dépassée";
            }

            // Calcul du carburant nécessaire → appel polymorphique
            // Chaque mission redéfinit calculerCarburant() avec son propre coefficient
            double carburant = mission.calculerCarburant(fusee.getMasseTotale());

            // Vérification 3 : carburant nécessaire dépasse la capacité du lanceur
            if (carburant > fusee.getLanceur().getCarburantMaxTonnes()) {
                succes = false;
                raison = "Carburant insuffisant";
            }

            // Vérification 4 : mission habitée mais capsule cargo
            if (mission.isHabiteeRequise() && !fusee.getCapsule().isHabitee()) {
                succes = false;
                raison = "Capsule incompatible avec mission habitée";
            }

            // Si toutes les vérifications sont OK, on tire un nombre aléatoire
            // 5% de chance d'échec technique aléatoire
            if (succes) {
                double alea = Math.random();
                if (alea < PROBABILITE_ECHEC) {
                    succes = false;
                    raison = "Anomalie technique imprévue";
                }
            }

        } catch (CarburantInsuffisant e) {
            // On attrape l'exception métier personnalisée
            succes = false;
            raison = e.getMessage();
        }

        // Calcul du coût total : prix fusée + (carburant × prix kérosène)
        double carburant = mission.calculerCarburant(fusee.getMasseTotale());
        double cout = fusee.getPrixTotal() + (carburant * PRIX_KEROSENE_PAR_TONNE / 1000000);

        // Affichage du résultat
        System.out.println("\n--- Résultat ---");
        System.out.println(succes ? "SUCCES !" : "ECHEC !");
        System.out.println("Raison : " + raison);
        System.out.printf("Cout total : %.2f M€%n", cout);

        // On crée un objet Lancement pour archiver le résultat
        Lancement lancement = new Lancement(fusee, mission, succes, raison, cout);
        historique.add(lancement);
        // On sauvegarde immédiatement dans le fichier CSV
        sauvegarderHistorique();
    }

    // Affiche l'historique en relisant le fichier CSV
    private void afficherHistorique() {
        System.out.println("\n--- Historique des lancements ---");
        chargerHistorique();
    }

    // Lit un entier depuis la console
    // Boucle tant que l'entrée n'est pas un nombre valide
    private int lireEntier() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrée invalide, entrez un nombre :");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Sauvegarde le dernier lancement dans le fichier CSV
    // On ouvre le fichier en mode "append" (true) pour ne pas écraser l'existant
    private void sauvegarderHistorique() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_HISTORIQUE, true))) {
            Lancement l = historique.get(historique.size() - 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            // Format CSV : chaque champ séparé par un ";"
            writer.write(
                    l.getDate().format(formatter) + ";" +
                            l.getFusee().getLanceur().getNom() + ";" +
                            l.getFusee().getCapsule().getNom() + ";" +
                            l.getMission().getNom() + ";" +
                            l.isSucces() + ";" +
                            l.getRaison() + ";" +
                            String.format("%.2f", l.getCoutTotal())
            );
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde : " + e.getMessage());
        }
    }

    // Charge et affiche l'historique depuis le fichier CSV
    private void chargerHistorique() {
        File fichier = new File(FICHIER_HISTORIQUE);
        if (!fichier.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            // On lit ligne par ligne et on reconstitue l'affichage
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(";");
                System.out.println("Date: " + parts[0] +
                        " | Lanceur: " + parts[1] +
                        " | Capsule: " + parts[2] +
                        " | Mission: " + parts[3] +
                        " | " + (parts[4].equals("true") ? "Succes" : "Echec") +
                        " | Raison: " + parts[5] +
                        " | Cout: " + parts[6] + "M€");
            }
        } catch (IOException e) {
            System.out.println("Erreur chargement : " + e.getMessage());
        }
    }
}