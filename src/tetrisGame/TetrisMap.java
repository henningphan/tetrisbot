package tetrisGame;
import java.util.ArrayList;
import java.lang.Math;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Collections;
public class TetrisMap {
	public enum Block{
		Fill,Blank,Empty
	}
	public ArrayList<LinkedList<Block>> map;
	private int linesClearCount;
	final public int col;
	final public int row;
	// When adding a tetromino which columns was it on?
	private int colLast;
	
	public int getcolLast(){
		return colLast;
	}
	public TetrisMap(){
		this(10,20);
	}
	public TetrisMap(int col, int row){
		this.col = col;
		this.row = row;
		map = new ArrayList<LinkedList<Block>>();
		for(int i = 0; i< col; ++i){
			map.add( new LinkedList<Block>());
		}
	}
	public TetrisMap(TetrisMap tet){
		this.col = tet.col;
		this.row = tet.row;
		this.map = new ArrayList<>();
		
		for(LinkedList<Block> list: tet.map){
			this.map.add(copy(list));
		}
	}
	// TODO throws indexoutofbound exception
	public boolean addTetromino(int colidx, TetrominoAbs tet){
		colLast = colidx;
		Vector<Integer> diff= tet.getDiff();
		// get height diff
		for( int c = 0; c<+tet.col; ++c){
			diff.set(c, diff.get(c) + map.get(c+colidx).size());
		}
		int max = Collections.max(diff);
		// create vector of int where int says how many blanks
		// that column needs
		for(int i= 0; i< diff.size(); ++i){
			diff.set(i, Math.abs((diff.get(i)-max)));
		}

		for(int c = colidx; c< colidx+tet.col; ++c){
			for(int loop=0; loop< diff.get(c-colidx); ++loop){
				map.get(c).add(Block.Blank);
				
			}
			map.get(c).addAll(tet.getCleanCol(c-colidx));
		}
		
		
		clearLines();
		return true;
	}
	public int getLowestColSize(){
		int size = row;
		for(LinkedList<Block> list: map){
			if( size > list.size()){
				size = list.size();
			}
		}
		return size;
	}
	public int getHighestColSize(){
		int size = 0;
		for(LinkedList<Block> list: map){
			if( size < list.size()){
				size = list.size();
			}
		}
		return size;
	}

	public int clearLines(){
		int count = 0;
		int heightLimit = getLowestColSize();
		// check all rows which can potentially have a filled line
		for( int row=0; row< heightLimit; ++row){
			boolean removeLine = true;
			// check if line is filled
			for(LinkedList<Block> list: map){
				try{
				if( list.get(row) == Block.Blank){
					removeLine = false;
					break;
				}
				}catch(IndexOutOfBoundsException e){
					removeLine = false;
					break;
				}
			}
			if(removeLine){
				++count;
				// remove filled line
				for(LinkedList<Block> list: map){
					list.remove(row);
				}
				// if a row r is removed all rows with higher index than r
				// get's a new index and next line is skipped
				--row;
			}
		}
		removeTopBlank();
		linesClearCount = count;
		return count;
	}
	// TODO consider this private
	public void removeTopBlank(){
		for(int col = 0; col<this.col; ++col){
			LinkedList<Block> list = map.get(col);
			// if we removed a top blank elem stay at column
			// to see if next is also blank
			try{
				if(list.getLast()== Block.Blank){
					// if its empty just go to next column
					list.removeLast();
					--col;
				}

			}catch(NoSuchElementException e){
			}
		}
	}
	
	private LinkedList<Block> copy(LinkedList<Block> list){
		LinkedList<Block> result = new LinkedList<>();
		// TODO is copy of enums copy by value? ELse we got problems
		for(Block b: list){
			result.add(b);
		}
		return result;
	}
	public boolean isGameOver(){
		return getHighestColSize()>= row;
	}
	// TODO throw indexoutofbound if requesting a higher row than this.row	
	public Vector<Block> getLine(int row){
		Vector<Block> result = new Vector<>();
		for(int col=0; col<this.col; ++col){
			try{
				Block b = map.get(col).get(row);
				result.add(b);
			}catch(IndexOutOfBoundsException e){
				result.add(Block.Empty);
			}
		}
		return result;
	}
	public String getLineAsString(int row){
		Vector<Block> line = getLine(row);
		String result="";
		for(Block b: line){
			result += b==Block.Blank? "O":b==Block.Fill? "X":" ";
		}
		return result;
	}
	public String printMap(){
		String result="";
		for(int row = this.row-1; row>=0; --row){
			result += "#"+getLineAsString(row) + "#"+(row+1)+"\n";
		}
		result+= new String(new char[col+2]).replace('\0', '#');
		
		return result;
	}
	public int getLinesCleared(){
		return linesClearCount;
	}
	
	public static void main(String[] args){
		TetrisMap tetris = new TetrisMap(20,20);
		TetrominoAbs piece = new TetrominoS();
		
		
		for(int i=0; i<1; ++i){
			tetris.addTetromino(0, piece);
			
		}
		System.out.println(tetris.printMap());
		System.out.println(piece.getDiff());
	}
	

}
