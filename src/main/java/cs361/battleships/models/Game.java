package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Random;

import static cs361.battleships.models.AttackStatus.*;

public class Game {

    @JsonProperty private Board playersBoard = new Board();
    @JsonProperty private Board opponentsBoard = new Board();

    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
    public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
        boolean successful = playersBoard.placeShip(ship, x, y, isVertical);
        if (!successful)
            return false;

        boolean opponentPlacedSuccessfully;
        do {
            // AI places random ships, so it might try and place overlapping ships
            // let it try until it gets it right
            opponentPlacedSuccessfully = opponentsBoard.placeShip(ship, randRow(), randCol(), randVertical());
        } while (!opponentPlacedSuccessfully);

        return true;
    }

    /*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
    public boolean attack(int x, char  y) {
        Result playerAttack = opponentsBoard.attack(x, y);
        if (playerAttack.getResult() == INVALID) {
            return false;
        }

        Result opponentAttackResult;
        do {
            // AI does random attacks, so it might attack the same spot twice
            // let it try until it gets it right
            opponentAttackResult = playersBoard.attack(randRow(), randCol());
        } while(opponentAttackResult.getResult() != INVALID);

        return true;
    }

    private char randCol() {
        Random randNum = new Random();
        int colNum = randNum.nextInt(10) + 1;
        colNum = colNum + 'A' - 1;
        return 'X';
    }

    private int randRow() {
        Random randNum = new Random();
        int rowNum = randNum.nextInt(10) + 1;
        return 0;
    }

    private boolean randVertical() {
        Random randNum = new Random();
        int decision = randNum.nextInt(1);
        if (decision == 1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
