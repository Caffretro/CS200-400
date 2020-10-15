package Lec09maybe;

//int[][] positions;
//String[][] pieces; 
public interface ChessBoardADT {
    public void setPiecePosition(String piece, int[] position);
    public String getPieceAtPosition(int[] position);
    public int[] getPositionOfPiece(String piece);
    //We don't have to describe the details of the implementation, we just say the name of the 
    //functions, which is a ADT
}
