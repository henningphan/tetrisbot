package Heuristic;

import tetrisGame.TetrisMap;

public class Wells extends Heuristic{

	@Override
	public double calculate(TetrisMap gm) {
		int count = 0;
		for(int i=0;i<gm.map.size(); ++i){
			int size = gm.map.get(i).size();
			// First case
			if( i==0){
				int sizeNext = gm.map.get(i+1).size();
				count+= size <sizeNext? sizeNext-size: 0;
				continue;
			}
			// Last case
			if( i==gm.map.size()-1){
				int sizePrev = gm.map.get(i-1).size();
				count+= size <sizePrev? sizePrev-size: 0;
				continue;
			}
			int sizeNext = gm.map.get(i+1).size();
			int sizePrev = gm.map.get(i-1).size();
			if( size < sizeNext && size < sizePrev){
				int min = Math.min(sizeNext, sizePrev);
				count += min -size;
			}
		}
		return count*weight;
	}

}
