/**
 * AI Project 0: Vacuum Agent
 * @author Evan Elkin
 */
package ai.worlds.vacuum;

public class EvanVacuumAgent extends VacuumAgent{

	//used for sensing bot direction
	private final int NORTH = 1;
	private final int EAST = 2;
	private final int SOUTH = 3;
	private final int WEST = 4;
	
	//keep track of vacuum orientation
	private int direction = EAST;
	
	//determines if sensing dimensions of room;
	private boolean isSensing = true;
	
	//keeps track of room size
	private int width = 1;
	private int height = 0;
	
	//keeps track of vacuum orientation in non-sensing phase
	private int row = 0;
	private int col = 1;
	private String last = "";
	
	public int getAction() {
		// TODO Auto-generated method stub
		if(this.seesDirt()) return this.SUCK;
		
		//if it's already moved around the board, turn it off. Otherwise, move!
		if(isHome()) {
			if(direction == EAST && !bumped()) return this.FORWARD;
			else if(direction == EAST && bumped()) {
				direction = NORTH;
				return this.LEFT;
			}
			else if(direction == NORTH && !bumped()) return this.FORWARD; 
			else return this.OFF;
		}
		
		//figuring out board dimensions for efficient future cleaning
		if(isSensing) {
			return doSensing();
		}
		
		if(width == 1) {
			return widthOne();
		}
		//execute board cleaning after finding board dimensions
		return boardWalk();
		//return this.FORWARD;
	}
	
	//keeps track of how long and wide the board is for future traversal
	public int doSensing() {
		if(!bumped() && direction == NORTH) {
			height += 1;
			row += 1 ;
			last = "FORWARD";
			return this.FORWARD;
		}
		if(!bumped() && direction == EAST) {
			width += 1;
			col += 1;
			last = "FORWARD";
			return this.FORWARD;
		}
		if(bumped() && direction == NORTH) {
			direction = WEST;
			isSensing = false;
			last = "LEFT";
			return this.LEFT;
		}
		if(bumped() && direction == EAST) {
			direction = NORTH;
			last = "LEFT";
			return this.LEFT;
		}
		return this.FORWARD;
	}
	
	//method for cleaning the board
	public int boardWalk() {
		//if bot facing south
		if(direction == SOUTH && last != "FORWARD") {
			row -= 1;
			last = "FORWARD";
			return this.FORWARD;
		}
	
		if(direction == SOUTH && col == 1) {
			direction = EAST;
			last = "LEFT";
			return this.LEFT;
		}
		if(direction == SOUTH && col == width - 1) {
			direction = WEST;
			last = "RIGHT";
			return this.RIGHT;
		}
		
		//when to turn;
		if(direction == WEST && col == 1) {
			direction = SOUTH;
			last = "LEFT";
			return this.LEFT;
		}
		if(direction == EAST && col == width - 1) {
			direction = SOUTH;
			last = "RIGHT";
			return this.RIGHT;
		}
		
		//keeping track of column
		if(direction == WEST) {
			col -= 1;
			last = "FORWARD";
			return this.FORWARD;
		}
		if(direction == EAST) {
			col += 1;
			last = "FORWARD";
			return this.FORWARD;
		}
		
		System.out.println("cool");
		return this.FORWARD;
	}
	
	//EXCEPTION: Width of the board is 1 x n
	public int widthOne() {
		if(bumped()) {
			direction = WEST;
			return this.LEFT;
		}
		if(direction == WEST) {
			direction = SOUTH;
			return this.LEFT;
		}
		return this.FORWARD;
	}
}
