import java.util.*;

public class Human extends Player {

   private Scanner input = new Scanner(System.in);

   public Human(final String name, final char piece) {
      super(name, piece);
   }

   protected Move getMove(final ReversiBoard board) {
      handleInput();
      int row = input.nextInt();
      handleInput();
      int col = input.nextInt();
      return new Move(row, col);
   }

   private void handleInput() {
      while (!input.hasNextInt()) {
         String text = input.next();
         if (text.toLowerCase().equals("help") || text.toLowerCase().equals("h")) {
            System.out.println("Valid Commands:");
            System.out.println("\"help[h]\"");
            System.out.println("\"quit[q]\"");
            System.out.println("\"[row] [col]\"");
         } else if (text.toLowerCase().equals("quit") || text.toLowerCase().equals("q")) {
            System.exit(0);
         } else {
            System.out.println("Invalid Input");
         }
      }
   }
}
