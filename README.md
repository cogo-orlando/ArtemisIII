# ArtemisIII - Simulateur de lancement spatial

## Description
Java en console simulant des lancements de fusées spatiales.
L'utilisateur configure une fusée, choisit une mission et simule le lancement.

## Compilation et exécution

### Depuis IntelliJ
1. Ouvrir le projet dans IntelliJ IDEA
2. Clic droit sur `Main.java` → Run

### Depuis le terminal
```bash
cd ArtemisIII/src
javac *.java
java Main
```

## Fonctionnalités
- Configurer une fusée (lanceur, capsule, boosters)
- Choisir une mission (Orbite, ISS, Lune, Mars, Nibiru)
- Simuler le lancement (succès ou échec)
- Consulter l'historique des lancements (sauvegardé dans `historique.csv`)

## Mission personnelle : Nibiru
Nibiru est une planète mystérieuse et hypothétique située à 900 000 000 km.
- Mission habitée : oui
- Distance : 900 000 000 km
- Coefficient carburant : 0.000008
- C'est la mission la plus lointaine et la plus coûteuse du simulateur

## Structure du projet

src/
├── Main.java
├── lanceur/
│   ├── Lanceurs.java       (abstraite)
│   ├── SaturneV.java
│   ├── Ariane5.java
│   ├── Falcon9.java
│   └── SLS.java
├── capsule/
│   ├── Capsules.java       (abstraite)
│   ├── Orion.java
│   ├── CrewDragon.java
│   ├── Apollo.java
│   └── CargoDragon.java
├── mission/
│   ├── Missions.java       (abstraite)
│   ├── Orbite.java
│   ├── ISS.java
│   ├── Lune.java
│   ├── Mars.java
│   └── Nibiru.java         (mission personnelle)
├── modèle/
│   ├── Boosters.java
│   ├── Fusee.java
│   └── Lancement.java
├── exception/
│   └── CarburantInsuffisant.java
└── simulateur/
└── Simulateur.java

## Notions POO utilisées
- **Héritage** : Lanceurs, Capsules, Missions sont des classes abstraites
- **Polymorphisme** : List<Lanceurs> contient SaturneV, Ariane5, Falcon9, SLS
- **Composition** : Fusee contient un Lanceur, une Capsule et des Boosters
- **Encapsulation** : attributs privés accessibles via getters
- **Surcharge** : deux méthodes ajouterBooster() dans Fusee
- **Exception métier** : CarburantInsuffisant
- **Singleton** : une seule instance de Simulateur

## Déclaration IA
Ce projet a été réalisé avec l'aide de Claude (Anthropic).
L'IA a été utilisée pour :
- Aider à structurer le code
- Corriger les erreurs de compilation
- Expliquer les notions POO