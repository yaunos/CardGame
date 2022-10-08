package com.openclassrooms.cardgame.games;

/* Maintenant on va assembler toutes les pièces du puzzle MVC, commençons par créer la classe games qui aura une méthode main
* Je l'ajoute dans un nouveau package games */

import com.openclassrooms.cardgame.controller.GameController;
import com.openclassrooms.cardgame.model.Deck;
import com.openclassrooms.cardgame.view.View;

public class Games {

    public static void main(String[] args) {
        /* on a besoin d'une instance de GameController et souvenez-vous le constructeur prend en paramètres un Deck et une View */
        GameController gc = new GameController(new Deck(), new View());

        /* Maintenant tout ce que l'on a à faire c'est d'exécuter le contrôleur en appelant la méthode Run */
        gc.run();

        /* Voyons ce qui se passe */
    }
}
