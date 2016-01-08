//TicTacToeTerminal
//Mark Schlax
//5/13/2014

import java.util.Scanner;
public class TicTacToeTerminal
{
 public static void main(String[] args)
 {
  TicTacToe board = new TicTacToe('X', 'O');
  Scanner scan = new Scanner(System.in);
  int player1Choice = 0, player2Choice = 0, gameChoice = 0, smartChoiceEnable = 2, setChar1 = 88, setChar2 = 79;
  System.out.println("Welcome To TicTacToe2\nWould You Like To Review The Rules?\n1:Yes\n2:No");
  do
   {
    System.out.print("Your Choice: ");
    gameChoice = scan.nextInt();
   } while (gameChoice < 1 || gameChoice > 2);
  if (gameChoice == 1)
   System.out.println("The Goal Of TicTacToe2 Is To Get Three Of Your Character In A Row\nYou Will Choose Your Move By Using The Number Associated With Where You Want To Move\nThere Is An Option For SmartChoice Which Will Show The Next Best Move");
  System.out.println("Do You Want To Set Special Characters Instead Of \"X\" and \"O\"?\n1:Yes\n2:No");
  do
   {
    System.out.print("Your Choice: ");
    gameChoice = scan.nextInt();
   } while (gameChoice < 1 || gameChoice > 2);
  if (gameChoice == 1)
   {
    for (int number = 33; number <= 20000; number += 5)
     System.out.println(number + ": " + (char) number + "\t" + (number + 1) + ": " + (char)(number + 1) + "\t" + (number + 2) + ": " + (char)(number + 2) + "\t" + (number + 3) + ": " + (char)(number + 3) + "\t" + (number + 4) + ": " + (char)(number + 4));
    System.out.println("Choose An Int Representing Player 1's Character: ");
    do
    {
     System.out.print("Your Choice: ");
     setChar1 = scan.nextInt();
    } while (setChar1 < 33);
    System.out.println("Choose An Int Representing Player 2's Character: ");
    do
    {
     do
     {
      System.out.print("Your Choice: ");
      setChar2 = scan.nextInt();
     } while (setChar2 == setChar1);
    } while (setChar2 < 33);
   }
  do
  {
   board.player1Char = (char) setChar1;
   board.player2Char = (char) setChar2;
   System.out.print("What Game Do You Want To Play?\n1:Player v. Player\n2:Player v. Computer\n");
   do
   {
    System.out.print("Your Choice: ");
    gameChoice = scan.nextInt();
   } while (gameChoice < 1 || gameChoice > 2);
   System.out.println("Do You Want Smart Choice Enabled?\n1:Yes\n2:No");
   do
   {
    System.out.print("Your Choice: ");
    smartChoiceEnable = scan.nextInt();
   } while (smartChoiceEnable < 1 || smartChoiceEnable > 2);
   if (gameChoice == 1)
   {
    while(board.checkBoard().equals("Winner: Nobody"))
    {
     if ((board.turn%2) == 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
     {
      System.out.println(board);
      do
      {
       System.out.print("Player " + board.player1Char + "'s Choice");
       if (smartChoiceEnable == 1)
        if (board.smartPlay(board.player1Char) != 0)
         System.out.print(" (" + board.smartPlay(board.player1Char) + ")");
        else if (board.smartPlay(board.player2Char) != 0)
         System.out.print(" (" + board.smartPlay(board.player2Char) + ")");
        else if (board.turn >= 2 && board.getIndexValue(5) == board.player1Char)
         System.out.print(" (" + board.neverLose(player2Choice) + ")");
       System.out.print(": ");
       player1Choice = scan.nextInt();
      } while (player1Choice < 1 || player1Choice > 9 || board.getIndexValue(player1Choice) != player1Choice);
      board.setIndexValue(player1Choice, board.player1Char);
     }
     else if ((board.turn%2) != 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
     {
      System.out.println(board);
      do
      {
       System.out.print("Player " + board.player2Char + "'s Choice");
       if (smartChoiceEnable == 1)
        if (board.smartPlay(board.player2Char) != 0)
         System.out.print(" (" + board.smartPlay(board.player2Char) + ")");
        else if (board.smartPlay(board.player1Char) != 0)
         System.out.print(" (" + board.smartPlay(board.player1Char) + ")");
        else if (board.turn >= 2 && board.getIndexValue(5) == board.player2Char)
         System.out.print(" (" + board.neverLose(player1Choice) + ")");
       System.out.print(": ");
       player2Choice = scan.nextInt();
      } while (player2Choice < 1 || player2Choice > 9 || board.getIndexValue(player2Choice) != player2Choice);
      board.setIndexValue(player2Choice, board.player2Char);
     }
     else if (board.turn == 9 && board.checkBoard().equals("Winner: Nobody"))
     {
      System.out.println(board);
      System.out.println(board.checkBoard());
      board.resetBoard();
      board.switchPlayers();
     }
    }
   }
   else if (gameChoice == 2)
   {
    System.out.println("Choose The Dificulty Of The Computer:\n1:Easy\n2:Medium\n3:Chuck Norris");
    do
    {
     System.out.print("Your Choice: ");
     gameChoice = scan.nextInt();
    } while (gameChoice < 1 || gameChoice > 3);
    while(board.checkBoard().equals("Winner: Nobody"))
    {
     if ((board.turn%2) == 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
     {
      System.out.println(board);
      if (board.player1Char == (char) setChar1)
      {
       do
       {
        System.out.print("Player " + board.player1Char + "'s Choice");
        if (smartChoiceEnable == 1)
         if (board.smartPlay(board.player1Char) != 0)
          System.out.print(" (" + board.smartPlay(board.player1Char) + ")");
         else if (board.smartPlay(board.player2Char) != 0)
          System.out.print(" (" + board.smartPlay(board.player2Char) + ")");
         else if (board.turn >= 2 && board.getIndexValue(5) == board.player1Char)
         System.out.print(" (" + board.neverLose(player2Choice) + ")");
        System.out.print(": ");
        player1Choice = scan.nextInt();
       } while (player1Choice < 1 || player1Choice > 9 || board.getIndexValue(player1Choice) != player1Choice);
       board.setIndexValue(player1Choice, board.player1Char);
      }
      else
      {
       if (gameChoice == 1)
        player1Choice = board.nextEasyMove();
       else
       {
        if (board.getIndexValue(5) == 5)
         player1Choice = 5;
        else
         if (board.smartPlay(board.player1Char) != 0)
          player1Choice = board.player1Char;
         else 
          player1Choice = board.neverLose(player2Choice);
       }
       board.setIndexValue(player1Choice, board.player1Char);
       System.out.println("Player " + board.player1Char + "'s Choice: " + player1Choice);
      }
     }
     if ((board.turn%2) != 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
     {
      System.out.println(board);
      if (board.player1Char != (char)setChar1)
      {
       do
       {
        System.out.print("Player " + board.player2Char + "'s Choice");
        if (smartChoiceEnable == 1)
         if (board.smartPlay(board.player2Char) != 0)
          System.out.print(" (" + board.smartPlay(board.player2Char) + ")");
         else if (board.smartPlay(board.player1Char) != 0)
          System.out.print(" (" + board.smartPlay(board.player1Char) + ")");
         else if (board.turn >= 2 && board.getIndexValue(5) == board.player2Char)
          System.out.print(" (" + board.neverLose(player1Choice) + ")");
        System.out.print(": ");
        player2Choice = scan.nextInt();
       } while (player2Choice < 1 || player2Choice > 9 || board.getIndexValue(player2Choice) != player2Choice);
       board.setIndexValue(player2Choice, board.player2Char);
      }
      else 
      {
       if (gameChoice == 1)
        player2Choice = board.nextEasyMove();
       else if (gameChoice == 2)
        player2Choice = board.nextMediumMove();
       else if (gameChoice == 3)
        player2Choice = board.nextHardMove();
       board.setIndexValue(player2Choice, board.player2Char);
       System.out.println("Player " + board.player2Char + "'s Choice: " + player2Choice);
      }
     }
     if (board.turn == 9 && board.checkBoard().equals("Winner: Nobody"))
     {
      System.out.println(board);
      System.out.println(board.checkBoard());
      board.resetBoard();
      board.switchPlayers();
     }
    }
   }
   System.out.println(board);
   System.out.println(board.checkBoard());
   board.resetBoard();
   System.out.print("Do You Want To Play Again?\n1:Yes\n2:No\n");
   do
   {
    System.out.print("Your Choice: ");
    gameChoice = scan.nextInt();
   } while (gameChoice < 1 || gameChoice > 2);
  } while (gameChoice != 2);
  System.out.println("Thanks For Playing\nHave A Nice Day!");
 }
}