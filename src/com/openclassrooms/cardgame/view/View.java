package com.openclassrooms.cardgame.view;

import com.openclassrooms.cardgame.controller.GameController;

import java.util.Scanner;

/*L'implémentation de la vue est très basique, on appelle juste des systèmes Out pour afficher l'information que le controleur aura passée */
/* et c'est très bien comme ça la vue n'a pas besoin de savoir ce qui se passe en coulisse */
public class View {

    /* d'abord on peut supprimer la methode something qui etait une methode bouche trou */
    public void something() {
    }

    /* le cors de setcontroller lui va juste definir l'instance de notre controleur (on va l'écrire en premier) */

    GameController controller;
    Scanner keyboard = new Scanner(System.in);
    public void setController(GameController gc) {
        this.controller = gc;
    };

    /*les informations qu'envoient les utilisateurs sont saisises au clavier donc on fournit à notre classe un simple scanner qui fera la lecture */

    /* La première méthode qu'appelle le controleur est prompt for Player name*/
    public void promptForPlayerName() {
        System.out.println("Enter player:");
        String name = keyboard.nextLine();
        /* L'indication de fin de saisie sera une chaîne vide */
        if (name.isEmpty()) {
            controller.startGame();
        }else {
            controller.addPlayer(name);
        }
    }

    /* On implémente ensuite promptForFlip  et prompForNewgame, pour toutes ces méthodes qui doivent afficher quelque chose
    on appelle SystemOut avec la chaîne de caractères forunie par le contrôleur */
    public void promptForFlip() {
        System.out.println("Press enter to reveal cards");
        keyboard.nextLine();
        controller.flipCards();
    }

    public void promptForNewGame() {
        System.out.println("Press enter to deal again");
        keyboard.nextLine();
        controller.startGame();
    }

    /* 2 Je reviens à la vue pour ajouter les méthodes qu'on vient de créer
     */
    public void showWinner(String playerName) {
        System.out.println("The winner is " + playerName + " !");
    }

    public void showPlayerName(int playerIndex, String playerName) {
        System.out.println("["+playerIndex+"]["+playerIndex+"]");
    }

    public void showFaceDownCardForPlayer(int playerIndex, String playerName) {
        System.out.println("["+playerIndex+"]["+playerName+"][x][x]");
    }

    public void showCardForPlayer(int i, String playerName, String rank, String suit) {
        System.out.println("["+i+"]["+playerName+"]["+rank+"]["+suit+"]");
    }
}

/* Voilà on a une implémentation très simple de notre vue en ligne de console */