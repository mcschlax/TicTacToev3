public class TicTacToe
{
 int[][] board;
 int turn;
 char player1Char, player2Char;
 public TicTacToe(char player1Char, char player2Char)
 {
  board = new int[3][3];
  for (int row = board.length - 1, number = board.length * board[row].length; row >= 0; row--)
   for (int col = board[row].length - 1; col >= 0; col--, number--)
    board[row][col] = number;
  turn = 0;
  this.player1Char = player1Char;
  this.player2Char = player2Char;
 }
 public void nextTurn()
 {
  if (turn < 9)turn++;
  else resetBoard();
 }
 public void switchPlayers()
 {
  char temp = player1Char;
  player1Char = player2Char;
  player2Char = temp;
 }
 public void resetBoard()
 {
  turn = 0;
  for (int row = 0, number = 1; row < 3; row++)
   for (int col = 0; col < 3; col++, number++)
    board[row][col] = number;
 }
 public int getIndexValue(int index)
 {
  if (index <= 3) return board[0][index - 1];
  else if (index <= 6) return board[1][index - 4];
  else return board[2][index - 7];
 }
 public void setIndexValue(int index, char value)
 {
  if (index <= 3) board[0][index - 1] = (int) value;
  else if (index <= 6) board[1][index - 4] = (int) value;
  else if (index <= 9) board[2][index - 7] = (int) value;
  nextTurn();
 }
 public String checkBoard()
 {
  String winner = "Nobody";  
  for (int row = 0; row < 3; row++)
   if(board[row][0] * board[row][1] * board[row][2] == player1Char * player1Char * player1Char)
    winner = player1Char + "";
   else if(board[row][0] * board[row][1] * board[row][2] == player2Char * player2Char * player2Char)
    winner = player2Char + "";
  for (int col = 0; col < 3; col++)
   if(board[0][col] * board[1][col] * board[2][col] == player1Char * player1Char * player1Char)
    winner = player1Char + "";
   else if(board[0][col] * board[1][col] * board[2][col] == player2Char * player2Char * player2Char)
    winner = player2Char + "";
  if (board[0][0] * board[1][1] * board[2][2] == player1Char * player1Char * player1Char)
   winner = player1Char + "";
  else if (board[0][0] * board[1][1] * board[2][2] == player2Char * player2Char * player2Char)
   winner = player2Char + "";
  else if (board[0][2] * board[1][1] * board[2][0] == player1Char * player1Char * player1Char)
   winner = player1Char + "";
  else if (board[0][2] * board[1][1] * board[2][0] == player2Char * player2Char * player2Char)
   winner = player2Char + "";
  return "Winner: " + winner;
 }
 public String toString()
 {
  String printOut;
  if (turn == 0) printOut = "Pre-Game Board";
  else if (turn == 9) printOut = "End-Game Board";
  else printOut = "Turn: " + turn;
  for (int row = 0; row < 3; row++)
  {
   printOut = printOut + "\n";
   for (int col = 0; col < 3; col++)
    if (board[row][col] > 9)
     printOut = printOut + (char)board[row][col] + " ";
    else
     printOut = printOut + board[row][col] + " ";
  }
  return printOut;
 }
 public int smartPlay(char chr)
 {
  int smartChoice = 0;
  for (int row = 0; row < 3; row++)
   for (int number = ((row * 3) + 1); number <= ((row * 3) + 3); number++)
    if (board[row][0] * board[row][1] * board[row][2] == (number * chr * chr))
     smartChoice = number;
  for (int col = 0; col < 3; col++)
   for (int number = (col + 1); number <= (col + 7); number+=3)
    if (board[0][col] * board[1][col] * board[2][col] == (number * chr * chr))
     smartChoice = number;
  for (int number = 1; number <= 9; number += 4)
   if (board[0][0] * board[1][1] * board[2][2] == (number * chr * chr))
    smartChoice = number;
  for (int number = 3; number <= 7; number += 2)
   if (board[0][2] * board[1][1] * board[2][0] == (number * chr * chr))
    smartChoice = number;
  return smartChoice;
 }
 public int neverLose(int opponentsPlay)
 {
  int neverLose = 0;
  if (opponentsPlay + 1 != 4 && opponentsPlay + 1 != 7 && opponentsPlay + 1 != 10 && getIndexValue(opponentsPlay + 1) == opponentsPlay + 1)
   return neverLose = opponentsPlay + 1;
  else if (opponentsPlay - 1 != 0 && opponentsPlay - 1 != 3 && opponentsPlay - 1 != 6 && getIndexValue(opponentsPlay - 1) == opponentsPlay - 1)
   return neverLose = opponentsPlay - 1;
  else if (opponentsPlay - 3 != -2 && opponentsPlay - 3 != -1 && opponentsPlay - 3 != 0 && getIndexValue(opponentsPlay - 3) == opponentsPlay - 3)
   return neverLose = opponentsPlay - 3;
  else if (opponentsPlay + 3 != 10 && opponentsPlay + 3 != 11 && opponentsPlay + 3 != 12 && getIndexValue(opponentsPlay + 3) == opponentsPlay + 3)
   return neverLose = opponentsPlay + 3;
  else
  {
   do
   {
    neverLose = (int) (Math.random() * 9) + 1;
   } while (getIndexValue(neverLose) != neverLose);
   return neverLose;
  }
 }
 public int randomCorner()
 {
  int randomChoice = 0;
  do
  {
    do
    {
     randomChoice = 2*((int)(Math.random() * 5) + 1) - 1;
    } while (randomChoice == 5);
  } while (randomChoice == 5 && randomChoice != board[0][0] && randomChoice != board[0][2] && randomChoice != board[2][0] && randomChoice != board[2][2]);
  return randomChoice;
 }
 public int randomInside()
 {
  int randomChoice = 0;
  do 
  {
   randomChoice = 2*((int)(Math.random() * 5) + 1);
  } while (randomChoice != board[0][1] && randomChoice != board[1][0] && randomChoice != board[1][2] && randomChoice != board[2][1]);
  return randomChoice;
 }
 public int nextEasyMove()
 {
  int easyMove = 0;
  do 
  {
   easyMove = (int) (Math.random() * 9) + 1;
  } while (getIndexValue(easyMove) != easyMove);
  return easyMove;
 }
 public int nextMediumMove()
 {
  int mediumMove = 0;
  if (turn == 1)
   mediumMove = (getIndexValue(5) == 5) ? 5 : randomCorner();
  else if (turn == 3)
   mediumMove = (smartPlay(player1Char) != 0) ? smartPlay(player1Char) : randomCorner();
  else if (turn == 5 || turn == 7)
  {
   if (smartPlay(player2Char) != 0)
    mediumMove = smartPlay(player2Char);
   else if (smartPlay(player1Char) != 0)
    mediumMove = smartPlay(player1Char);
   else
    do
    {
     mediumMove = (int)(Math.random()*9) + 1;
    } while (getIndexValue(mediumMove) != mediumMove);
  }
  return mediumMove;
 }
 public int nextHardMove()
 {
  int hardMove = 0;
  if (turn == 1)
   hardMove = (getIndexValue(5) == 5) ? 5 : randomCorner();
  else if (turn == 3)
  {
   if (getIndexValue(5) == player2Char)
   {
    if (smartPlay(player1Char) != 0)
     hardMove = smartPlay(player1Char);
    else if (getIndexValue(1) != 1 && getIndexValue(9) != 9)
     hardMove = randomInside();
    else if (getIndexValue(3) != 3 && getIndexValue(7) != 7)
     hardMove = randomInside();
    else if (getIndexValue(2) != 2 && getIndexValue(6) != 6)
     hardMove = 3;
    else if (getIndexValue(8) != 8 && getIndexValue(6) != 6)
     hardMove = 9;
    else if (getIndexValue(8) != 8 && getIndexValue(4) != 4)
     hardMove = 7;
    else if (getIndexValue(4) != 4 && getIndexValue(2) != 2)
     hardMove = 1;
    else if (getIndexValue(1) != 1 && getIndexValue(8) != 8)
     hardMove = 7;
    else if (getIndexValue(1) != 1 && getIndexValue(6) != 6)
     hardMove = 3;
    else if (getIndexValue(3) != 3 && getIndexValue(8) != 8)
     hardMove = 9;
    else if (getIndexValue(3) != 3 && getIndexValue(4) != 4)
     hardMove = 1;
    else if (getIndexValue(9) != 9 && getIndexValue(4) != 4)
     hardMove = 7;
    else if (getIndexValue(7) != 7 && getIndexValue(6) != 6)
     hardMove = 9;
    else if (getIndexValue(2) != 2 && getIndexValue(7) != 7)
     hardMove = 1;
    else
     hardMove = randomCorner();
   }
   else
    hardMove = (smartPlay(player1Char) != 0) ? smartPlay(player1Char) : randomCorner();
  }
  else if (turn == 5 || turn == 7)
  {
   if (smartPlay(player2Char) != 0)
    hardMove = smartPlay(player2Char);
   else if (smartPlay(player1Char) != 0)
    hardMove = smartPlay(player1Char);
   else
    do
    {
     hardMove = (int) (Math.random() * 9) + 1;
    } while (getIndexValue(hardMove) != hardMove);
  }
  return hardMove;
 }
}