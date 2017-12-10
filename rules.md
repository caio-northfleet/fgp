Poker Hands

Your job is to compare two given poker hands and to indicate which, if either, has a higher rank.


Poker rules description

A poker deck contains 52 cards - each card has a suit which is one of clubs, diamonds, hearts, or spades (denoted C, D, H, and S in the input data). Each card also has a value which is one of 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king, ace (denoted 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A). For scoring purposes, the suits are unordered while the values are ordered as given above, with 2 being the lowest and ace the highest value.

A poker hand consists of 5 cards dealt from the deck. Poker hands are ranked by the following partial order from lowest to highest.

* High Card: Hands which do not fit any higher category are ranked by the value of their highest card. If the highest cards have the same value, the hands are ranked by the next highest, and so on.
* Pair: 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of the cards forming the pair. If these values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order.
* Two Pairs: The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
* Three of a Kind: Three of the cards in the hand have the same value. Hands which both contain three of a kind are ranked by the value of the 3 cards.
* Straight: Hand contains 5 cards with consecutive values. Hands which both contain a straight are ranked by their highest card.
* Flush: Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
* Full House: 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
* Four of a kind: 4 cards with the same value. Ranked by the value of the 4 cards.
* Straight flush: 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.


Remarks

* You are free to decide if it's a standalone application or a web application.
* Please use Java for the implementation.
* You are free to decide on how the interface works - can be e.g. stdin or a web form. Please follow the format in the description. Example (orange denotes input):

1st hand: <span style="color:orange">C5 D3 D4 S7 C6</span>\
2nd hand: <span style="color:orange">DA D3 D5 H8 S8</span>\
1st hand wins! (Straight)

* UI doesn't matter - it doesn't have to look good, it has to work. =)
* You don't have to check for duplicate cards in the input - you can assume the user to enter unique cards for both hands.
* Please do an estimation beforehand and let us know when you plan on delivering. Speed is not decisive - if you're busy all week and need a week or more that's fine, but please let us know beforehand when you expect to be ready.
* It's required to use a public code repository (GitHub, BitBucket, ...). Please commit regularly so we can see how the project has evolved.
* Please include instructions on how to run the application in the repository.
* Deliverable is: a link to above mentioned repository where we can download the result.
* Apply a level of test coverage which you think is appropriate.