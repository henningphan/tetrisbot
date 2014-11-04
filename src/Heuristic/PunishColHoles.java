package Heuristic;

import java.util.LinkedList;

import tetrisGame.TetrisMap;
import tetrisGame.TetrisMap.Block;

public class PunishColHoles extends Heuristic {


	@Override
	public int calculate(TetrisMap gm) {
		int holeCount = 0;
		for(LinkedList<Block> li: gm.map){
			boolean holeFound= false;
			for(Block b: li){
				if(b == Block.Blank && !holeFound){
					holeFound = true;
				}else if( b == Block.Fill && holeFound){
					holeFound = false;
					++holeCount;
				}
			}
		}
		return holeCount*weight;
	}

}
