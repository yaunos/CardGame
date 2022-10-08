package com.openclassrooms.cardgame.model;

import java.util.ArrayList;
import java.util.List;

/* on va coder les concepts de main et de joueur */
/* La main est un ensemble de cartes détenues par un joueur */
public class Hand {
    /* Dans notre jeu on a besoin d'une seule carte par main mais on a va utilisre une liste pour que le code soit plus générique
    * Si on code d'autres jeux à l'avenir on codera facilement des mains avec plusieurs cartes*/

    private List<PlayingCard> cards;

    /* on ajoute un controleur et la possibilité d'ajouter des cartes à la main
    et d'en enlever pour qu'elles puisesnt etre remises dans le paquet */
    /* Bien sûr la main ne se préoccupe pas du deck, c'est le controleur qui fera ça
    mais on doit bien proposer la possibilité de retirer des cartes */
    public Hand() {
        cards = new ArrayList<PlayingCard>();
    }

    public void addCard(PlayingCard pc) {
        cards.add(pc);
    }

    public PlayingCard getCard(int index) {
        return cards.get(index);
    }

    public PlayingCard removeCard() {
        return cards.remove(0);
    }
}

/* Ensuite on ajoute le joueur au modele, Player */