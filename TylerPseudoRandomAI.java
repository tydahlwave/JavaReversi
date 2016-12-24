import java.util.*;

public class TylerPseudoRandomAI extends Player {

   public TylerPseudoRandomAI(final String name, final char piece) {
      super(name, piece);
   }

   public TylerPseudoRandomAI(final char piece) {
      super("Tyler-Pseudo-Random", piece);
   }

   public Move getMove(final ReversiBoard board) {
      List<Move> validMoves = getValidMoves(board);
      Random randGen = new Random();
      return validMoves.get(randGen.nextInt(validMoves.size()));
   }

   private List<Move> getValidMoves(final ReversiBoard board) {
      ArrayList<Move> moves = new ArrayList<>();
      int size = board.getSize();

      for (int row = 1; row <= size; row++) {
         for (int col = 1; col <= size; col++) {
            if (board.isValidMove(piece, row - 1, col - 1)) {
               moves.add(new Move(row, col));
            }
         }
      }

      return moves;
   }
}
