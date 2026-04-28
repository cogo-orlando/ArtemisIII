package simulateur;

import capsule.*;
import exception.CarburantInsuffisant;
import lanceur.*;
import mission.*;
import modele.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulateur {

    private static Simulateur instance;
    private List<Lancement> historique;
    private List<Lanceurs> lanceurs;
    private List<Capsules> capsules;
    private List<Boosters> boosters;
    private List<Missions> missions;
    private Scanner scanner;

    private static final double PRIX_KEROSENE_PAR_TONNE = 1200;
    private static final double PROBABILITE_ECHEC = 0.05;

    private Simulateur() {
        historique = new ArrayList<>();
        scanner = new Scanner(System.in);
        initialiserCatalogues();
    }

    public static Simulateur getInstance() {
        if (instance == null) {
            instance = new Simulateur();
        }
        return instance;
    }

    private void initialiserCatalogues() {
        lanceurs = new ArrayList<>();
        lanceurs.add(new SaturneV());
        lanceurs.add(new Ariane5());
        lanceurs.add(new Falcon9());
        lanceurs.add(new SLS());

        capsules = new ArrayList<>();
        capsules.add(new Orion());
        capsules.add(new CrewDragon());
        capsules.add(new Apollo());
        capsules.add(new CargoDragon());

        boosters = new ArrayList<>();
        boosters.add(new Boosters("EAP (Ariane)", 6470, 270, 30));
        boosters.add(new Boosters("SRB (Shuttle)", 12500, 590, 55));
        boosters.add(new Boosters("BE-3", 490, 25, 12));

        missions = new ArrayList<>();
        missions.add(new Orbite());
        missions.add(new ISS());
        missions.add(new Lune());
        missions.add(new Mars());
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n================================");
            System.out.println("  ARTEMIS III - SIMULATEUR");
            System.out.println("================================");
            System.out.println("1. Nouvelle mission");
            System.out.println("2. Historique des missions");
            System.out.println("0. Quitter");
            System.out.println("================================");
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
                    System.out.println("Choix invalide, reessayez.");
            }
        }
    }

    private void nouvelleMission() {
        System.out.println("\n--- Choisir un lanceur ---");
        for (int i = 0; i < lanceurs.size(); i++) {
            System.out.println((i + 1) + ". " + lanceurs.get(i));
        }
        System.out.print("Votre choix : ");
        Lanceurs lanceur = lanceurs.get(lireEntier() - 1);

        System.out.println("\n--- Choisir une capsule ---");
        for (int i = 0; i < capsules.size(); i++) {
            System.out.println((i + 1) + ". " + capsules.get(i));
        }
        System.out.print("Votre choix : ");
        Capsules capsule = capsules.get(lireEntier() - 1);

        Fusee fusee = new Fusee(lanceur, capsule);

        System.out.println("\n--- Ajouter des boosters (0 pour passer) ---");
        for (int i = 0; i < boosters.size(); i++) {
            System.out.println((i + 1) + ". " + boosters.get(i));
        }
        System.out.print("Votre choix (0 pour passer) : ");
        int choixBooster = lireEntier();
        if (choixBooster != 0) {
            fusee.ajouterBooster(boosters.get(choixBooster - 1));
        }

        System.out.println("\n--- Choisir une mission ---");
        for (int i = 0; i < missions.size(); i++) {
            System.out.println((i + 1) + ". " + missions.get(i));
        }
        System.out.print("Votre choix : ");
        Missions mission = missions.get(lireEntier() - 1);

        System.out.println("\n--- Resume ---");
        System.out.println(fusee);
        System.out.println("Mission : " + mission.getNom());

        System.out.print("\nConfirmer le lancement ? (1 = Oui, 0 = Non) : ");
        if (lireEntier() == 1) {
            lancerSimulation(fusee, mission);
        }
    }

    private void lancerSimulation(Fusee fusee, Missions mission) {
        boolean succes = true;
        String raison = "Lancement reussi";

        try {
            if (fusee.getBoosters().size() > fusee.getLanceur().getBoostersMax()) {
                throw new CarburantInsuffisant("Trop de boosters");
            }

            if (fusee.getMasseTotale() > fusee.getLanceur().getChargeUtileMaxTonnes()) {
                succes = false;
                raison = "Surcharge depassee";
            }

            double carburant = mission.calculerCarburant(fusee.getMasseTotale());

            if (carburant > fusee.getLanceur().getCarburantMaxTonnes()) {
                succes = false;
                raison = "Carburant insuffisant";
            }

            if (mission.isHabiteeRequise() && !fusee.getCapsule().isHabitee()) {
                succes = false;
                raison = "Capsule incompatible avec mission habitee";
            }

            if (succes) {
                double alea = Math.random();
                if (alea < PROBABILITE_ECHEC) {
                    succes = false;
                    raison = "Anomalie technique imprevue";
                }
            }

        } catch (CarburantInsuffisant e) {
            succes = false;
            raison = e.getMessage();
        }

        double carburant = mission.calculerCarburant(fusee.getMasseTotale());
        double cout = fusee.getPrixTotal() + (carburant * PRIX_KEROSENE_PAR_TONNE / 1000000);

        System.out.println("\n--- Resultat ---");
        System.out.println(succes ? "SUCCES !" : "ECHEC !");
        System.out.println("Raison : " + raison);
        System.out.println("Cout total : " + cout + "M€");

        Lancement lancement = new Lancement(fusee, mission, succes, raison, cout);
        historique.add(lancement);
    }

    private void afficherHistorique() {
        if (historique.isEmpty()) {
            System.out.println("\nAucun lancement dans l'historique.");
            return;
        }
        System.out.println("\n--- Historique des lancements ---");
        for (Lancement l : historique) {
            System.out.println(l);
        }
    }

    private int lireEntier() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entree invalide, entrez un nombre :");
            scanner.next();
        }
        return scanner.nextInt();
    }
}