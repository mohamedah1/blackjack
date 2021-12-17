# Blackjack

## Overview
This was an individual project (that's why the package is called individual) where I had to design a game using a GUI

## Objective
Player attempts to beat the dealer by getting a count as close to 21 as possible, without going over 21.

## Card Values and Scoring
It is up to the player if an ace is worth 1 or 11. Face cards are 10 and any other card is its pip value.

## Play of the Game
The dealer gives two cards face up to the player and then one card face up and one faced down to themselves. Thus, the player receives two cards face up, and the dealer receives one card face up and one card face down. The player goes first and must decide whether to "stand" (not ask for another card) or "hit" (ask for another card in an attempt to get closer to a count of 21, or even hit 21 exactly). Thus, a player may stand on the two cards originally dealt to them, or they may ask the dealer for additional cards, one at a time, until deciding to stand on the total (if it is 21 or under), or goes "bust" (if it is over 21). In the latter case, the player loses and the dealer wins.

The combination of an ace with a card other than a ten-card is known as a "soft hand," because the player can count the ace as a 1 or 11, and either draw cards or not. For example with a "soft 17" (an ace and a 6), the total is 7 or 17. While a count of 17 is a good hand, the player may wish to draw for a higher total. If the draw creates a bust hand by counting the ace as an 11, the player simply counts the ace as a 1 and continues playing by standing or "hitting" (asking the dealer for additional cards, one at a time).

### Dealer's Play
When the player stands, the dealer's face-down card is turned up. If the total is 17 or more, it must stand. If the total is 16 or under, they must draw a card. The dealer must continue to draw cards until the total is 17 or more, at which point the dealer must stand. If the dealer has an ace, and counting it as 11 would bring the total to 17 or more (but not over 21), the dealer must count the ace as 11 and stand. The dealer's decisions, then, are automatic on all plays, whereas the player always has the option of taking one or more cards.

