package com.openclassrooms.cardgame.model;


/* Maintenant qu'on sait créer une carte, on peut en assembler plusieurs dans un jeu (Deck en anglais) */

//import com.openclassrooms.cardgame.PlayingCard;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* Donc ajoutons la classe */
public class Deck {

    /* Elle a une simple collection de cartes qu'on va implémenter sous forme d'une liste */

    private List<PlayingCard> cards;

    /* maintenant il faut créer les 52 cartes, on va faire ça dans le contrôleur */

    /* et pour ça on va coder une double boucle */

    public Deck() {
        cards = new ArrayList<PlayingCard>();
        /* Peu importe l'orde qu'on choisit, l'important c'est de créer toutes les combinaisons*/
        /* on commence par exemple par rank à l'extérieur, puis suit à l'intérieur */
        for(Rank rank : Rank.values()) {
            for (Suit suit: Suit.values()) {
                System.out.println("Creating card ["+rank+"]["+suit+"]");
                cards.add(new PlayingCard(rank, suit));
            }
        }
        /* Ensuite on veut mélanger les cartes grâce au mécanisme shuffle */
        shuffle();
    }

    /* on va écrire la méthode qui est simple, on parcourt les cartes en les échangeant
    et c'est la méthode swap qui va faire le travail pour nous */
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < cards.size(); i++) {
            Collections.swap(cards, i, random.nextInt (cards.size()));
        }
    }

    /* Pour finir on a besoin de méthodes pour d'une part tirer une carte du paquet
    et d'autre part remettre la carte dans le paquet */

    public PlayingCard removeTopCard() {
        return cards.remove(0);
    }

    public void returnCardToDeck(PlayingCard pc) {
        cards.add(pc);
    }
    /* J'ajoute aussi un getter sur la liste de PlayingCards */

    public List<PlayingCard> getCards() {
        return cards;
    }

    /* on a maintenant ajouté notre jeu de cartes à notre modèle */

}
