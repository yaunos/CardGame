package com.openclassrooms.cardgame.model;

public class PlayingCard {
    private Rank rank;
    private Suit suit;
    private boolean faceUp;

    public PlayingCard(Rank rank, Suit suit) {
        super();
        this.rank = rank;
        this.suit = suit;
    }
    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    /* notez ici qu'on ne créé pas de setter
    car on ne veut pas que quelqu'un puisse changer une carte après qu'elle aie été construite */

    /* En parlant de ça on a besoin d'un constructeur qui utilise la valeur(rank) et la couleur(suit) donc créons playingCard*/

    /* on veut aussi savoir si la carte est retournée ou non pour ça on ajoue un flag faceUp */

    /* il faut pouvoir connaître la valeur de cet attribut pour ça on ajoute un getter ... */

    public boolean isFaceUp() {
        return faceUp;
    }
    /* ... et une méthode flip qui va inverser la valeur de l'attribut */

    public boolean flip() {
        faceUp = !faceUp;
        return faceUp;
    }
}

/* Voilà une nouvelle partie de notre modèle est terminée */