import java.util.*;

public class TylerRandomAI extends Player {

   public TylerRandomAI(final String name, final char piece) {
      super(name, piece);
   }

   public TylerRandomAI(final char piece) {
      super("Tyler-Random", piece);
   }

   public Move getMove(final ReversiBoard board) {
      int size = board.getSize();
      Random randGen = new Random();
      int randRow = randGen.nextInt(size) + 1;
      int randCol = randGen.nextInt(size) + 1;
      return new Move(randRow, randCol);
   }
}
