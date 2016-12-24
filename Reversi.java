import java.util.*;

public class Reversi {

   private ReversiBoard board;
   private ArrayList<Player> players;
   private int playerTurn;

   public Reversi() {
      board = new ReversiBoard(8);
      players = new ArrayList<>();
      playerTurn = 0;
   }

   private void handleGameOver() {
      if (!isGameOver()) return;

      Player p1 = players.get(0);
      Player p2 = players.get(1);
      Player winner = (board.getScore(p1.piece) > board.getScore(p2.piece)) ? p1 : p2;

      System.out.println("Game Over");
      System.out.println(winner.name + " won!");
      System.out.println(p1.name + ": " + board.getScore(p1.piece));
      System.out.println(p2.name + ": " + board.getScore(p2.piece));
   }

   public boolean isGameOver() {
      Player p = players.get(playerTurn);
      return !board.hasValidMove(p.piece);
   }

   public void compete(final int games) throws Exception {
      if (players.size() != 2) {
         throw new Exception("Incorrect number of players. Must have 2 players to start.");
      }

      Player p1 = players.get(0);
      Player p2 = players.get(1);
      int p1Wins = 0;
      int p2Wins = 0;

      System.out.println("Simulating " + games + " games");
      for (int i = 0; i < games; i++) {
         board = new ReversiBoard(8);
         playerTurn = i % 2;
         while (!isGameOver()) {
            takeTurn(players.get(playerTurn));
         }

         int p1Score = board.getScore(p1.piece);
         int p2Score = board.getScore(p2.piece);
         if (p1Score > p2Score) {
            p1Wins++;
         } else {
            p2Wins++;
         }

         if (i % (games / 10) == 0) {
            System.out.println((i / (games / 10) * 10) + "%");
         }
      }

      System.out.println(p1.name + ": " + p1Wins + " wins");
      System.out.println(p2.name + ": " + p2Wins + " wins");
   }

   public void start() throws Exception {
      if (players.size() != 2) {
         throw new Exception("Incorrect number of players. Must have 2 players to start.");
      }

      System.out.println("Othello");
      board.print();
      System.out.println();
      
      playerTurn = (int)(Math.random() * 2);
      while (!isGameOver()) {
         Player current = players.get(playerTurn);
         System.out.println("It is " + current.name + "'s turn (" + current.piece.toString() + ")");
         takeTurn(current);
         board.print();
      }
      handleGameOver();
   }

   private void takeTurn(Player p) {
      boolean validMove = false;
      Move move;
      do {
         move = p.getMove(board);

         validMove = board.isValidMove(p.piece, move.row - 1, move.col - 1);
         if (!validMove) {
            System.out.println("Invalid Move");
         }
      } while (!validMove);

      board.makeMove(p.piece, move.row - 1, move.col - 1);

      playerTurn = (playerTurn + 1) % 2;
   }

   public void addPlayer(final Player p) {
      players.add(p);
   }

   public static void main(String[] args) throws Exception {
      Reversi game = new Reversi();

      // Player p1 = new Human("Tyler", 'x');
      // Player p2 = new Human("Sogio", 'o');
      Player p1 = new TylerPseudoRandomAI("P1", 'x');
      Player p2 = new TylerPseudoRandomAI("P2", 'o');

      game.addPlayer(p1);
      game.addPlayer(p2);

      if (args.length >= 2 && args[0].equals("-t")) {
         game.compete(Integer.valueOf(args[1]));
      } else {
         game.start();
      }
   }
}
