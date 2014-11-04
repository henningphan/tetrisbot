package tetrisGame;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Vector;

import tetrisGame.TetrisMap.Block;
public abstract class TetrominoAbs implements Tetromino{
	Vector<LinkedList<Block>> map;
	int col;
	int row;
	// TODO fix, doesnt work
	public void rotateRight(){
		Vector<LinkedList<Block>> temp = new Vector<>();
		for(int i=0; i<row;++i){
			temp.add(new LinkedList<Block>());
		}

		for(LinkedList<Block> li: map){
			for(int i=0; i<this.row; ++i){
				try{
					temp.get(i).addFirst(li.get(i));
				}catch(IndexOutOfBoundsException e){
					temp.get(i).addFirst(Block.Blank);
				}
			}
		}
		map = temp;

		removeTopBlanks();
		int rowNew = col;
		col = row;
		row = rowNew;
	}
	/**
	 * Return a vector, each elem represent a columns 
	 * negative count of Blank blocks
	 */
	public Vector<Integer> getDiff(){
		Vector<Integer> result = new Vector<>();
		for(LinkedList<Block> li: map){
			int count = 0;
			Iterator<Block> it = li.iterator();
			while( it.hasNext()){
				if( it.next() == Block.Blank){
					--count;
				}
			}
			result.add(count);
		}
		return result;
	}
	private void removeTopBlanks(){
		for(int col=0; col<map.size(); ++col){
			LinkedList<Block> list = map.get(col);
			try{
				if(list.getLast() == Block.Blank){
					list.removeLast();
					--col;
				}
			}catch(NoSuchElementException e){
			}
		}
	}
	
	/**
	 * To TetrisMap.add needs this when adding a
	 * tetris piece on the map
	 * @param idx
	 * @return
	 */
	public LinkedList<Block> getCleanCol(int idx){
		LinkedList<Block> result = new LinkedList<>();
		for(Block b: map.get(idx)){
			result.add(b);
		}
		while(result.size() > 0 && result.getFirst() == Block.Blank){
			result.removeFirst();
		}
		return result;
	}
	
	public int getCol(){
		return col;
	}
	public int getRow(){
		return row;
	}
}
