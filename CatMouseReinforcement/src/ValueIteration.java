/**
 * This is the template of a class that should run value iteration on
 * a given MDP to compute the optimal policy which is returned in the
 * public <tt>policy</tt> field.  The computed optimal utility is also
 * returned in the public <tt>utility</tt> field.  You need to fill in
 * the constructor.  You may wish to add other fields with other
 * useful information that you want this class to return (for
 * instance, number of iterations before convergence).
 */
public class ValueIteration {

	/** the computed optimal policy for the given MDP **/
	public int policy[];

	/** the computed optimal utility for the given MDP **/
	public double utility[];

	/** the precision used to determine when to stop iterating (called
	 * epsilon in lecture) **/
	public static double precision = 1e-10;

	/**
	 * The constructor for this class.  Computes the optimal policy
	 * for the given <tt>mdp</tt> with given <tt>discount</tt> factor,
	 * and stores the answer in <tt>policy</tt>.  Also stores the
	 * optimal utility in <tt>utility</tt>.
	 */
	public ValueIteration(Mdp mdp, double discount) {
		utility = new double[mdp.numStates];
		double[] nextUtility = new double[utility.length];
		policy = new int[mdp.numStates];
		/*
		for(double i: utility) {
			i = Math.random();
		}
		*/
		double utilityChange = 0;
		boolean unchanged = false;
		while(!unchanged) {
			for(int i = 0; i < utility.length; i++) {
				utility[i] = nextUtility[i];
			}
			for(int state = 0; state < mdp.numStates; state++) {
				int bestAction = 0;
				double maxActionValue = 0;
				for(int action = 0; action < mdp.numActions; action++) {
					double actionValue = expectedUtility(state, action, mdp);
					if(actionValue > maxActionValue) {
						maxActionValue = actionValue;
						bestAction = action;
					}
				}
				policy[state] = bestAction;
				nextUtility[state] = mdp.reward[state] + discount*maxActionValue;
				if(Math.abs(nextUtility[state]-utility[state])>utilityChange) {
					utilityChange = Math.abs(nextUtility[state]-utility[state]);
				}
				
				if(utilityChange < precision*(1-discount)/discount) {
					unchanged = true;
				}
			}
		}
	}

	/*
	 * SUM P(s'|(s,a)U(s')
	 */
	public double expectedUtility(int state, int action, Mdp mdp) {
		double expectedValue = 0;
		//gives a list of all possible next states after taking this action
		int[] nextStates = mdp.nextState[state][action];
		for(int i = 0; i < nextStates.length; i++) {
			int nextState = nextStates[i];
			double prob = mdp.transProb[state][action][i];
			double util = utility[nextState];
			expectedValue += prob*util;
		}
		return expectedValue;
	}
}
