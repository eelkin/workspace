/**
 *  @author   J. Hollingsworth and YOUR NAME HERE
 *  
 *  Divide-and-Conquer approach to placing tromino
 *  tiles on a 2^k x 2^k board.
 */
import java.util.Scanner;
import java.awt.Color;

public class Tiling {

	private static Color randColor() {
		int r = (int)(Math.random() * 256);
		int g = (int)(Math.random() * 256);
		int b = (int)(Math.random() * 256);

		return new Color(r, g, b);
	}

	public static void tile(DeficientBoard db, int row, int col, int n) {

		int pieceType = 0;
		if(n>=2) {
			// find deficciency
			int dRow = db.getDeficientRow(row, col, n);
			int dCol = db.getDeficientCol(row, col, n);
			if(dRow < row + n/2 && dCol < col + n/2) {
				pieceType =  db.LR;
			}
			if(dRow < row + n/2 && dCol > col + n/2) {
				pieceType =  db.LL;
			}
			if(dRow > row + n/2 && dCol < col + n/2) {
				pieceType =  db.UR;			
			}
			if(dRow > row + n/2 && dCol > col + n/2) {
				pieceType =  db.UL;			
			}
		}

		db.placeTromino(row + n/2 - 1, col + n/2 - 1, pieceType, randColor());
		
		tile(db, row, col, n/2);
		tile(db, row, col + n/2, n/2);
		tile(db, row + n/2, col, n/2);
		tile(db, row + n/2, col + n/2, n/2);
	}

	public static void main(String[] args) {

		DeficientBoard db = new DeficientBoard(3);
		System.out.println(db);

		tile(db, 0, 0, db.getSize());

		BoardViewer bv = new BoardViewer(db);
		bv.setVisible(true);

	}

}
