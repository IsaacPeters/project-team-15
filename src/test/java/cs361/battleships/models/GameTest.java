package cs361.battleships.models;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class GameTest {

    @Test
    public void testGameStart() {
        Game game = new Game();

        assert(game.placeShip(new Ship("MINESWEEPER"), 3, 'D', true));
        assert(game.placeShip(new Ship("DESTROYER"), 4, 'D', true));
        assert(game.placeShip(new Ship("BATTLESHIP"), 5, 'D', true));

        List<Ship> ships = game.getPlayersBoard().getShips();
        Iterator<Ship> it = ships.iterator();
        int j = 3;
        while (it.hasNext()) {
            //System.out.println("J is: " +j);
            Ship tempShip = it.next();

            List<Square> sweepSquares = tempShip.getOccupiedSquares();
            Iterator<Square> jt = sweepSquares.iterator();
            int x = j;
            while (jt.hasNext()) {
                Square temp = jt.next();
                //System.out.println("\tPosition is: " + temp.getRow() + temp.getColumn());
                assert(temp.getRow() == x);
                assert(temp.getColumn() == 'D');
                x++;
            }
            j++;
        }
    }
}