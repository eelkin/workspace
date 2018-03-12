package game;
import java.util.*;

import mancala.MancalaGame;

public class Minimax {
	int bestMove;
	/*
	 * Create a heuristic with the knowledge that the North player is 
	 * max and the South player is Min.
	 */
	public int heuristic(GameBoard game) {
		// use this line for testing.  Change to play Mancala.
		//North = max, south = min
		//Heuristic = (northMancala + 6*pits that get extra turn + .5empty pits + total stones in pits) 
		//- (southMancala + 6*pits that get extra turn + .5empty pits + total stones in pits)
		
		MancalaGame mancala = ((MancalaGame) game);
		int[] state = mancala.getState();
		//number of stones in each mancala
		int northMancala = state[mancala.MANCALA_NORTH];
		int southMancala = state[mancala.MANCALA_SOUTH];
		
		//extra pits - these will be prioritized since going again is incredible important
		int nExtraPits = 0;
		int sExtraPits = 0;
		
		//empty pits - these also have value, as empty pits can help capture
		int nEmptyPits = 0;
		int sEmptyPits = 0;
		
		//total stones in pits outside mancala for each side
		int totalNorthStones = 0;
		int totalSouthStones = 0;
		//count for pits that get extra turn - north
		
		//counts for pits - south
		for(int i = 0; i < state.length/2; i++) {
			//if value in pit is equal to distance from north/south add 1 - extra
			if(state[i] == mancala.MANCALA_SOUTH - i) sExtraPits++;
			if(state[i] == 0) sEmptyPits++;
			totalSouthStones += state[i];
		}
		//counts for pits - north
		for(int i = (state.length/2)+1; i < state.length; i++) {
			//if value in pit is equal to distance from north/south add 1 - extra
			if(state[i] == mancala.MANCALA_NORTH - i) nExtraPits++;
			if(state[i] == 0) nEmptyPits++;
			totalNorthStones += state[i];
		}
		
		//nEmptyPits = nEmptyPits/2;
		//sEmptyPits = sEmptyPits/2;
		
		//calculates total values for each side based on previous factors
		int northVal = northMancala + 6*nExtraPits + nEmptyPits + totalNorthStones;
		int southVal = southMancala + 6*sExtraPits + sEmptyPits + totalSouthStones;
		
		//heuristic is north (max) - south (min) values
		//if best for north, value is big. If best for south, value is small
		int heuristic = northVal - southVal;
		
		//return game.defaultHeuristic();
		return heuristic;

	}


	/* This method should return the MOVE that gives the best value (don't return the best value itself.)
	 * In Mancala, it's a pit number.
	 * In a Node game, it's the branch number 
	 */

	public int getMove(GameBoard game, int level, boolean max, double alpha, double beta){
		getValue(game,level,max, alpha, beta);
		return bestMove;
	}

	/*
	 * Call at the beginning of the method:
	 * StringWriter.println("Getting value for state: "+game.getStateDescription());
	 * 
	 * When you default to the heuristic:
	 * StringWriter.println("Returning heurustic: "+game.heuristic());
	 * 
	 * Just before you return:
	 * StringWriter.println("Returning for state: "+game.getStateDescription()+": move "+bestMoveSoFar+" evaluating at "+bestValueSoFar);
	 */

	/*
	 * Method getMove:
	 * @param game: The game logic
	 * @param level: how deep the search should go before using the heuristic
	 * @param max: true if this player is "max" and false if this player is "min"
	 * @param alpha & beta: values that percolate up the tree.  Added here for recursion ease.
	 * 
	 * You should use the above 3 lines as you process so that the tester can check not only the final answer but
	 * also the process.
	 */
	
	public double getValue(GameBoard game, int level, boolean max, double alpha, double beta){
		StringWriter.println("Getting value for state: "+game.getStateDescription());
		//if reached the max level or if game over, return utility of state
		if(level == 0 || game.gameOver()) {
			StringWriter.println("Returning heurustic: "+ heuristic(game));
			return heuristic(game);
		}
		//if looking at the max player
		if(max) {
			double bestValue = -1*(Double.MAX_VALUE);
			int bestMove = -1;
			for(int move = 0; move < game.getPossibleMoves().size(); move++) {
				GameBoard clone = game.clone();
				List<Integer> allMoves = clone.getPossibleMoves();
				//clone.moveMade(allMoves.get(move));
				
				if(clone.moveMade(allMoves.get(move))) {
					max = !max;
				}
				
				double newBestValue = getValue(clone, level-1, !max, alpha, beta);

				if(newBestValue >= bestValue) {
					bestValue = newBestValue;
					bestMove = allMoves.get(move);
				}
				if(bestValue >= beta) {
					StringWriter.println("Returning for state: "+game.getStateDescription()+": move "+bestMove+" evaluating at "+bestValue);
					this.bestMove = bestMove;
					return bestValue;
				}
				
				alpha = Math.max(alpha, bestValue);
			}
			StringWriter.println("Returning for state: "+game.getStateDescription()+": move "+bestMove+" evaluating at "+bestValue);
			this.bestMove = bestMove;
			return bestValue;
		} else { //looking at the min player
			double bestValue = Double.MAX_VALUE;
			int bestMove = -1;
			for(int move = 0; move < game.getPossibleMoves().size(); move++) {
				GameBoard clone = game.clone();
				List<Integer> allMoves = clone.getPossibleMoves();
				//clone.moveMade(allMoves.get(move));
				
				if(clone.moveMade(allMoves.get(move))) {
					max = !max;
				}
				
				double newBestValue = getValue(clone, level-1, !max, alpha, beta);
				if(newBestValue <= bestValue) {
					bestValue = newBestValue;
					bestMove = allMoves.get(move);
				}
				
				if(bestValue <= alpha) {
					StringWriter.println("Returning for state: "+game.getStateDescription()+": move "+bestMove+" evaluating at "+bestValue);
					this.bestMove = bestMove;
					return bestValue;
				}
				
				beta = Math.min(beta, bestValue);
			}
			StringWriter.println("Returning for state: "+game.getStateDescription()+": move "+bestMove+" evaluating at "+bestValue);
			this.bestMove = bestMove;
			return bestValue;
		}

	}
	
}
