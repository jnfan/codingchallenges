package affirm;

import java.util.*;
import java.util.LinkedList;

public class CardGame {
    class Card{
        int num;
        public Card(int num) {
            this.num = num;
        }
    }

    class CardAndPlayer{
        int num;
        int player;
        public CardAndPlayer(int num, int player) {
            this.num = num;
            this.player = player;
        }
    }
    // Two players play
    public void play(List<List<Card>> decks) {
        List<Card> player1Deck = new LinkedList<>();
        player1Deck = decks.get(0);
        List<Card> player2Deck = decks.get(1);
        int round = 0;
        while(round < 26) {
            Card p1Card = player1Deck.poll();
            Card p2Card = player1Deck.poll();
            if (p1Card.num > p2Card.num) {
                player1Deck.addLast(p1Card);
                player1Deck.addLast(p2Card);
            } else if (p1Card.num < p2Card.num) {
                player2Deck.addLast(p1Card);
                player2Deck.addLast(p2Card);
            } else {
                continue;
            }
            if (player1Deck.size() == 0) {
                System.out.println("Player 2 wins");
                break;
            }
            if (player2Deck.size() == 0) {
                System.out.println("Player 1 wins");
                break;
            }
            round++;
        }
        if (player1Deck.size() == player2Deck.size()) {
            for (Card player1card : player1Deck) {
                if (player1card.num == 52) {
                    System.out.println("Player 1 wins");
                    return;
                }
            }
            System.out.println("Player 2 wins");
        }
    }
    // Deal when multiple players
    public List<List<CardAndPlayer>> deal(int cardCount, int numOfPlayers) {
        List<Card> cardDeck = new ArrayList<Card>();
        for (int i = 1; i <= cardCount; i++) {
            cardDeck.add(new Card(i));
        }
        Collections.shuffle(cardDeck, new Random());
        List<List<CardAndPlayer>> res = new ArrayList<List<CardAndPlayer>>();
        for (int j = 1; j <= numOfPlayers; j++) {
            List<CardAndPlayer> playerDeck = new LinkedList<>();
            res.add(playerDeck);
        }

        for (int i = 1; i <= cardCount; i++) {
            for (int j = 1; j <= numOfPlayers; j++) {
                res.get(j).add(new CardAndPlayer(i, j));
            }
        }
        return res;
    }
    // Deal when two players
    public List<List<Card>> deal() {
        List<Card> cardDeck = new ArrayList<Card>();
        for (int i = 1; i <= 52; i++) {
            cardDeck.add(new Card(i));
        }
        Collections.shuffle(cardDeck, new Random());
        List<Card> player1Deck = new LinkedList<>();
        List<Card> player2Deck = new LinkedList<>();
        player1Deck.addAll(cardDeck.subList(0, 25));
        player2Deck.addAll(cardDeck.subList(26, cardDeck.size()));
        List<List<Card>> res = new ArrayList<List<Card>>();
        res.add(player1Deck);
        res.add(player2Deck);
        return res;
    }
}
