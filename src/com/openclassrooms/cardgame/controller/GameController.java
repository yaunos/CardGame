/* On va créer le chef d'orchestre de notre jeu : le GameController dans un Nouveau package Controller */
package com.openclassrooms.cardgame.controller;

/* on le codera étape par étape :
 * on sait qu'il doit manipuler des objets du modèle et l'objet vue*/

/* Donc la première chose que l'on ajoute c'est une vue simpliste comme ça le contrôleur nous laissera tranquilles */

 /* 2. C'est parti on va coder le squelette de la vue c'est à dire les méthodes que le controleur va appeler */

//  class View {
//     public void something() {
//     }
//
//    public void setController(GameController gc) {
//    };
//
//    public promptForPlayerName() {
//    }
//
//    public promptForFlip() {
//    }
//
//    public promptForNewGame() {
//    }
//
//    /* 2 Je reviens à la vue pour ajouter les méthodes qu'on vient de créer
//
//     Dans un deuxième temps toutes ces méthodes seront déplacées dans un fichier view.java externe et la classe vue sera elle-même extraite
//
//    public void showWinner(String name) {
//    }
//
//    public void showPlayerName(int size, String playerName) {
//    }
//
//    public void showFaceDownCardForPlayer(int i, String name) {
//    }
//
//    public void showCardForPlayer(int i, String name, String string, String string2) {
//    }
//
//
//}

import com.openclassrooms.cardgame.model.Deck;
import com.openclassrooms.cardgame.model.Player;
//import com.openclassrooms.cardgame.model.PlayingCard;

import com.openclassrooms.cardgame.model.PlayingCard;
import com.openclassrooms.cardgame.view.View;

import java.util.ArrayList;
import java.util.List;




/* maintenant je viens à l'implémentation du controleur */
/* On a besoin d'un deck, d'une liste de joueurs, d'un joueur qui sera le gagnant et il nous faudra aussi une vue */
/* On introduit aussi une énumération */
public class GameController {

/* on introduit aussi une énumération qui représentera l'état du jeu */
    enum GameState {
        //AddingPlayers, CardsDealt, WinnerRevealed;
        AddingPlayers, CardsDealt, WinnerRevealed;
    }

    Deck deck;
    List<Player> players;
    Player winner;
    View view;

    /* Et une variable pour contenir cette information */
    GameState gameState;

    /*Ensuite on peut écrire le constructeur du contrôleur, il prend en paramètre la vue et le deck qui seront créés ailleurs,
     il configure le reste des  propriétés */
    public GameController(Deck deck, View view) {
        super();
        this.deck = deck;
        this.view =view;
        this.players = new ArrayList<Player>();
        this.gameState = GameState.AddingPlayers;
        view.setController(this);
    }
/* 2. Maintenant on va écrire des méthodes spécifiques pour chaque action que le controleur va traiter  ... */

    /* passons maintenant aux autres méthodes, on va d'abord écrire une méthode "run" qui regarde l'état du jeu
     et appelle des méthodes de la vue pour faire quelque chose qui va bien */


    public void run() {
         /* 2. Les premiers appels que lon va corriger sont ceux de la méthode "run" */
         while(gameState == gameState.AddingPlayers) {
             /* 2. Pour l'état Addingplayers, on veut que la vue demande la saisie du playerName */
             //view.something();
             view.promptForPlayerName();
         }

         /* l'idée est de boucler sur AddingPlayers pour ajouter autant de Players qu'on veut
         * Pour l'instant la vue n'a qu'une seule méthode "something" donc pour l'instant on appelle toujours la même
         * mais plus tard on l'enrichira avec du code qui aura plus de sens*/

         switch (gameState) {
             /* 2; Quand les cartes sont distribuées, on veut retourner les cartes */
             case CardsDealt:
                 //view.something();
                 view.promptForFlip();
                 break;
                 /*2 Et finalement après avoir montré le gagnant on veut proposer une nouvelle partie -> on retourne donc à la vue et on ajoute ces méthodes  */
             case WinnerRevealed:
                //view.something();
                 view.promptForNewGame();
                 break;
         }
    }

    /* Ecrivons maintenant les autres méthodes du contrôleur, la première c'est de définir des joueurs, allons-y */

    public void addPlayer(String playerName) {
        if (gameState == GameState.AddingPlayers) {
            players.add(new Player(playerName));
            /* 2. La premiere action c'est l'ajout d'un joueur, dans ce cas il faudra afficher le joueur créé */
            //view.something();
            view.showPlayerName(players.size(), playerName);
        }
    }

    /* Ensuite il faut démarrer le jeu ce qui implique de ... */
    public void startGame() {
        if (gameState != GameState.CardsDealt) {
        /* mélanger le paquet ... */
            deck.shuffle();
            int playerIndex = 1;
            /* Tirer une carte pour chaque joueur */
            for  (Player player : players)  {
                player.addCardToHand(deck.removeTopCard());
                /* 2. Quand le jeu aura débuté on voudra que tous les joueurs affichent leur jeu avec la face cachée */
                // view.something();
                view.showFaceDownCardForPlayer(playerIndex++, player.getName());
            }
            gameState = GameState.CardsDealt;
        }
        /* et passer à l'étape suivante du jeu ... en rappelant la méthode "run" */
        this.run();
    }

    /* L'étape suivante consiste à montrer les cartes ... */

    public void flipCards() {

        for (Player player : players) {
            int playerIndex =1;
            PlayingCard pc = player.getCard(0);
            pc.flip();
            /* 2 Ensuite quand les cartes seront retournées il faudra montrer la carte de chaque joueur (on rajoute au dessus int playerIndex =1*/
            //view.something();
            view.showCardForPlayer(playerIndex++, player.getName(), pc.getRank().toString(), pc.getSuit().toString());
        }

        /* ... calculer le gagnant, afficher le gagnant, reconstruire le jeu ... */
        evaluateWinner();
        displayWinner();
        rebuildDeck();
        gameState = GameState.WinnerRevealed;
        /* ... et passer à l'étape suivante en rappelant une fois de plus la méthode "run" */
        this.run();
    }

    /* Je vais d'abord implémenter les méthodes displayWinner et rebuildDeck ... */

    void displayWinner() {
        /* 2. A la fin quand le joueur gagnant aura été calculé on voudra afficher son nom */
        //view.something();
        /* je vais donc ajouter le getter à la class player */
        view.showWinner(winner.getName());

    }

    void rebuildDeck() {
        for (Player player : players) {
            deck.returnCardToDeck(player.removeCard());
        }
    }

    /* ... et enfin la méthode evaluateWinner qui va calculer le gagnant */

    void evaluateWinner() {
        Player bestPlayer = null;
        /* L'algorithme est simple, on commence en initialisant les variables */
        int bestRank = -1;
        int bestSuit = -1;

        for (Player player : players) {
            /* on va regarder la carte de chaque joueur et la comparer avec la meilleure carte qui a été trouvée jusqu'ici */

            /* Si elle est plus forte on retient le joueur qui a cette carte ainsi que le Rank et le Suit de cette carte,
                on fait ainsi de suite pour tous les joueurs. Finalement on assigne au gagnant la valeur du meilleur joueur qui ressort de la boucle
                 Voilà notre contrôleur est prêt à fonctionner, il faudra revenir sur cette classe pour préciser la vue, les interactions avec la vue et les différents évenements */

            boolean newBestPlayer = false;

                if(bestPlayer == null) {
                    newBestPlayer = true;
                } else {
                    PlayingCard pc = player.getCard(0);
                    int thisRank = pc.getRank().value();
                    if (thisRank >= bestRank) {
                        if (thisRank > bestRank) {
                            newBestPlayer = true;
                        } else {
                        if(pc.getSuit().value() > bestSuit) {
                            newBestPlayer = true;
                        }
                    }
                }
            }

            if (newBestPlayer) {
                bestPlayer = player;
                PlayingCard pc = player.getCard(0);
                bestRank = pc.getRank().value();
                bestSuit = pc.getSuit().value();
            }
        }

        winner = bestPlayer;
    }
}

