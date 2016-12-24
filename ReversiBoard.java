import java.util.*;

public class ReversiBoard implements Printable {

   private final int size;
   private Piece[][] grid;

   public ReversiBoard(final int size) {
      this.size = size;
      grid = new Piece[size][size];

      int half = size / 2;
      grid[half-1][half-1] = new Piece('x');
      grid[half-1][half] = new Piece('o');
      grid[half][half-1] = new Piece('o');
      grid[half][half] = new Piece('x');
   }

   public int getSize() { return size; }

   public int getScore(final Piece p) {
      int count = 0;

      for (int row = 0; row < size; row++) {
         for (int col = 0; col < size; col++) {
            if (grid[row][col] != null && grid[row][col].equals(p)) {
               count++;
            }
         }
      }

      return count;
   }

   public boolean hasValidMove(final Piece p) {
      for (int row = 0; row < size; row++) {
         for (int col = 0; col < size; col++) {
            if (isValidMove(p, row, col)) {
               return true;
            }
         }
      }
      return false;
   }

   private boolean isOpponentPiece(final Piece p, final int row, final int col) {
      return grid[row][col] != null && !grid[row][col].equals(p);
   }

   private boolean lookDown(final Piece p, final int row, final int col) {
      int next = row + 1;
      if (row+1 < size && isOpponentPiece(p, next, col)) {
         next++;
         while (next < size) {
            if (grid[next][col] == null) {
               break;
            } else if (grid[next][col].equals(p)) {
               return true;
            }
            next++;
         }
      }
      return false;
   }

   private boolean lookRight(final Piece p, final int row, final int col) {
      int next = col + 1;
      if (col+1 < size && isOpponentPiece(p, row, next)) {
         next++;
         while (next < size) {
            if (grid[row][next] == null) {
               break;
            } else if (grid[row][next].equals(p)) {
               return true;
            }
            next++;
         }
      }
      return false;
   }

   private boolean lookUp(final Piece p, final int row, final int col) {
      int next = row - 1;
      if (next >= 0 && isOpponentPiece(p, next, col)) {
         next--;
         while (next >= 0) {
            if (grid[next][col] == null) {
               break;
            } else if (grid[next][col].equals(p)) {
               return true;
            }
            next--;
         }
      }
      return false;
   }

   private boolean lookLeft(final Piece p, final int row, final int col) {
      int next = col - 1;
      if (next >= 0 && isOpponentPiece(p, row, next)) {
         next--;
         while (next >= 0) {
            if (grid[row][next] == null) {
               break;
            } else if (grid[row][next].equals(p)) {
               return true;
            }
            next--;
         }
      }
      return false;
   }

   private boolean lookDR(final Piece p, final int row, final int col) {
      int nextRow = row + 1;
      int nextCol = col + 1;
      if (nextRow < size && nextCol < size && isOpponentPiece(p, nextRow, nextCol)) {
         nextRow++;
         nextCol++;
         while (nextRow < size && nextCol < size) {
            if (grid[nextRow][nextCol] == null) {
               break;
            } else if (grid[nextRow][nextCol].equals(p)) {
               return true;
            }
            nextRow++;
            nextCol++;
         }
      }
      return false;
   }

   private boolean lookUL(final Piece p, final int row, final int col) {
      int nextRow = row - 1;
      int nextCol = col - 1;
      if (nextRow >= 0 && nextCol >= 0 && isOpponentPiece(p, nextRow, nextCol)) {
         nextRow--;
         nextCol--;
         while (nextRow >= 0 && nextCol >= 0) {
            if (grid[nextRow][nextCol] == null) {
               break;
            } else if (grid[nextRow][nextCol].equals(p)) {
               return true;
            }
            nextRow--;
            nextCol--;
         }
      }
      return false;
   }

   private boolean lookUR(final Piece p, final int row, final int col) {
      int nextRow = row - 1;
      int nextCol = col + 1;
      if (nextRow >= 0 && nextCol < size && isOpponentPiece(p, nextRow, nextCol)) {
         nextRow--;
         nextCol++;
         while (nextRow >= 0 && nextCol < size) {
            if (grid[nextRow][nextCol] == null) {
               break;
            } else if (grid[nextRow][nextCol].equals(p)) {
               return true;
            }
            nextRow--;
            nextCol++;
         }
      }
      return false;
   }

   private boolean lookDL(final Piece p, final int row, final int col) {
      int nextRow = row + 1;
      int nextCol = col - 1;
      if (nextRow < size && nextCol >= 0 && isOpponentPiece(p, nextRow, nextCol)) {
         nextRow++;
         nextCol--;
         while (nextRow < size && nextCol >= 0) {
            if (grid[nextRow][nextCol] == null) {
               break;
            } else if (grid[nextRow][nextCol].equals(p)) {
               return true;
            }
            nextRow++;
            nextCol--;
         }
      }
      return false;
   }

   public boolean isValidMove(final Piece p, final int row, final int col) {
      if (row < 0 || row >= size || col < 0 || col >= size) return false;
      if (grid[row][col] != null) return false;

      return lookRight(p, row, col) ||
             lookDown(p, row, col) ||
             lookLeft(p, row, col) ||
             lookUp(p, row, col) ||
             lookUL(p, row, col) ||
             lookUR(p, row, col) ||
             lookDR(p, row, col) ||
             lookDL(p, row, col);
   }

   public void makeMove(final Piece p, final int row, final int col) {
      grid[row][col] = p;

      // Down
      int next;
      if (lookDown(p, row, col)) {
         next = row+1;
         while (next < size && isOpponentPiece(p, next, col)) {
            grid[next][col] = p;
            next++;
         }
      }

      // Right
      if (lookRight(p, row, col)) {
         next = col+1;
         while (next < size && isOpponentPiece(p, row, next)) {
            grid[row][next] = p;
            next++;
         }
      }

      // Up
      if (lookUp(p, row, col)) {
         next = row-1;
         while (next >= 0 && isOpponentPiece(p, next, col)) {
            grid[next][col] = p;
            next--;
         }
      }

      // Left
      if (lookLeft(p, row, col)) {
         next = col-1;
         while (next >= 0 && isOpponentPiece(p, row, next)) {
            grid[row][next] = p;
            next--;
         }
      }

      // Up-Left
      int nextRow, nextCol;
      if (lookUL(p, row, col)) {
         nextRow = row - 1;
         nextCol = col - 1;
         while (nextRow >= 0 && nextCol >= 0 && isOpponentPiece(p, nextRow, nextCol)) {
            grid[nextRow][nextCol] = p;
            nextRow--;
            nextCol--;
         }
      }

      // Up-Right
      if (lookUR(p, row, col)) {
         nextRow = row - 1;
         nextCol = col + 1;
         while (nextRow >= 0 && nextCol < size && isOpponentPiece(p, nextRow, nextCol)) {
            grid[nextRow][nextCol] = p;
            nextRow--;
            nextCol++;
         }
      }

      // Down-Left
      if (lookDL(p, row, col)) {
         nextRow = row + 1;
         nextCol = col - 1;
         while (nextRow < size && nextCol >= 0 && isOpponentPiece(p, nextRow, nextCol)) {
            grid[nextRow][nextCol] = p;
            nextRow++;
            nextCol--;
         }
      }

      // Down-Right
      if (lookDR(p, row, col)) {
         nextRow = row + 1;
         nextCol = col + 1;
         while (nextRow < size && nextCol < size && isOpponentPiece(p, nextRow, nextCol)) {
            grid[nextRow][nextCol] = p;
            nextRow++;
            nextCol++;
         }
      }
   }

   public void print() {
      System.out.print("  ");
      for (int col = 1; col <= size; col++) {
         System.out.print("" + col + " ");
      }
      System.out.println();

      for (int row = 0; row < size; row++) {
         System.out.print("" + (row+1) + "|");
         for (int col = 0; col < size; col++) {
            if (grid[row][col] == null) {
               System.out.print("_");
            } else {
               System.out.print(grid[row][col].toString());
            }
            System.out.print("|");
         }
         System.out.println();
      }
   }
}
