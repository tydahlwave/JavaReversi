import java.util.*;

public class Piece implements Printable {

   private final char value;

   public Piece(final char value) {
      this.value = value;
   }

   public void print() {
      System.out.print(value);
   }

   @Override
   public String toString() {
      return String.valueOf(value);
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) return false;
      if (!(obj instanceof Piece)) return false;
      Piece p = (Piece) obj;

      return this.value == p.value;
   }
}
