package tetrisGame;


public interface Tetromino {
	public enum Type{
		O,T,I,J,L,S,Z
	}
	public void rotateRight();
	public int getSides();
	
}
