package com.openclassrooms.cardgame.model;

/* Le joueur a un nom, on ajoute un string,
et on va s'assurer que le joueur a un nom dès le début donc on fait ca dans le constructeur */
/* Le joueur a aussi une main qui contient ses cartes */

public class Player {
    private String name;
    private Hand hand;

    public Player (String name) {
        super();
        this.name = name;
        hand = new Hand();
    }

    /* et on a besoin de methode pour passer ou enlever des cartes à la main */

    public void addCardToHand(PlayingCard pc) {
        hand.addCard(pc);
    }

    public PlayingCard getCard(int index) {
        return hand.getCard(index);
    }

    public PlayingCard removeCard() {
        return hand.removeCard();
    }

    /* 2. j'ajoute donc le getter à la classe player */
     public String getName() {

        return name;
    }

    /* Voilà les objets de notre modèle sont tous prêts */

}
