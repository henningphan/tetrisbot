package Heuristic;

import tetrisGame.TetrisMap;

/**
 * 
 * @author henning
 *
 */
public class Diagonal extends Heuristic{

	@Override
	public int calculate(TetrisMap gm) {
		int leftHeight = gm.map.get(0).size();
		int count= 0;
		for(int i=1; i< gm.col; ++i){
			if(leftHeight < gm.map.get(i).size()){
				++count;
			}
			leftHeight = gm.map.get(i).size();
		}
		return count*weight;
	}
	

}
