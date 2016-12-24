import java.util.*;

public abstract class Player {

   public final String name;
   public final Piece piece;

   public Player(final String name, final char piece) {
      this.name = name;
      this.piece = new Piece(piece);
   }

   public Player(final String name, final Piece piece) {
      this.name = name;
      this.piece = piece;
   }

   abstract protected Move getMove(final ReversiBoard board);
}
