//TicTacToeGUI
//Mark Schlax
//5/13/2014

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.GraphicsEnvironment;
public class TicTacToeGUI extends JComponent
{
 final Dimension MaxDimensions;
 TicTacToe board;
 int player1Choice, player2Choice, playerChar, gameType;
 boolean smartChoiceEnabled;
 String frame;
 public static void main(String[] args)
 {
  javax.swing.SwingUtilities.invokeLater(new Runnable()
   {
    public void run()
    {
     createAndShowGUI();
    }
   }
  );
 }
 public static void createAndShowGUI()
 {
  JFrame frame = new JFrame("TicTacToe");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  TicTacToeGUI game = new TicTacToeGUI();
  frame.add(game);
  frame.pack();
  frame.setVisible(true);
 }
 public TicTacToeGUI()
 {
  Dimension temp = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
  MaxDimensions = (temp.height <= temp.width) ? new Dimension((int) (.5 *temp.height), (int) (.6 * temp.height))
                                              : new Dimension((int) (.5 *temp.width), (int) (.6 * temp.width));
  board = new TicTacToe('X', 'O');
  player1Choice = 0;
  player2Choice = 0;
  playerChar = (int) 'X';
  smartChoiceEnabled = false;
  gameType = 0;
  frame = "start";                                              
  addMouseListener(new MouseAdapter()
   {
    public void mousePressed(MouseEvent event)
    {
     processClick(event.getX(), event.getY());
     repaint();
    }
   }
  );
 }
 public void processClick(int x, int y)
 {
  if (x >= 0 && x <= MaxDimensions.width && y >= 0 && y <= MaxDimensions.height)
  {
   if (frame.equals("start"))
   {
    Graphics temp = getGraphics();
    temp.setFont(new Font(null, 0, (int) (MaxDimensions.height/28.0)));
    int[] stringWidth = {temp.getFontMetrics().stringWidth("Rules"), temp.getFontMetrics().stringWidth("Choose Different Chars."), temp.getFontMetrics().stringWidth("Enable SmartChoice("+smartChoiceEnabled+")?"), temp.getFontMetrics().stringWidth("Player vs. Player"), temp.getFontMetrics().stringWidth("Player vs. Easy Comp."), temp.getFontMetrics().stringWidth("Player vs. Medium Comp."), temp.getFontMetrics().stringWidth("Player vs. Chuck Norris")};
    int stringHeight = temp.getFontMetrics().getHeight();
    for (int index = stringWidth.length-1; index >= 0; index--)
     if (x >= (int)(MaxDimensions.width/2.0 - stringWidth[index]/2.0) && y >= (int) ((5*MaxDimensions.height/6.0)/8.0 + (index+1)*MaxDimensions.height/8.0) - stringHeight + (int)(stringHeight/8.0) && x <= (int)(MaxDimensions.width/2.0 + stringWidth[index]/2.0) && y <= (int) ((5*MaxDimensions.height/6.0)/8.0 + (index+1)*MaxDimensions.height/8.0) - stringHeight + (int)(stringHeight/8.0) + stringHeight)
     {
      if (index == 0)
       frame = "rules";
      else if (index == 1)
       frame = "changeChars";
      else if (index == 2)
       smartChoiceEnabled = !smartChoiceEnabled;
      else if (index >= 3)
      {
       gameType = index - 3;
       frame = "ingame";
      }
      break;
     }
   }
   else if (frame.equals("rules"))
    frame = "start";
   else if (frame.equals("changeChars"))
   {
    Graphics temp = getGraphics();
    temp.setFont(new Font(null, 0, (int) (MaxDimensions.height/24.0)));
    int[] stringWidth = {temp.getFontMetrics().stringWidth("Click here to change player1's Char"), temp.getFontMetrics().stringWidth("Click here to change player2's Char")};
    int stringHeight = temp.getFontMetrics().getHeight();
    for (int index = stringWidth.length-1; index >= 0; index--)
     if (x >= (int)(MaxDimensions.width/2.0 - stringWidth[index]/2.0) && y >= (int) ((5*MaxDimensions.height/6.0)/8.0 + (index+1)*MaxDimensions.height/7.0) - stringHeight + (int)(stringHeight/8.0) && x <= (int)(MaxDimensions.width/2.0 + stringWidth[index]/2.0) && y <= (int) ((5*MaxDimensions.height/6.0)/8.0 + (index+1)*MaxDimensions.height/7.0) - stringHeight + (int)(stringHeight/8.0) + stringHeight)
     {
      if (index == 0)
      {
       String str = (String)JOptionPane.showInputDialog(this, "Click here to change player1's Char from " + (char)board.player1Char);
       if (str != null)
       {
        board.player1Char = (str).charAt(0);
        playerChar = board.player1Char;
       }
      }
      else if (index == 1)
      {
       String str = (String)JOptionPane.showInputDialog(this, "Click here to change player2's Char from " + (char)board.player2Char);
       if (str != null)
        board.player2Char = (str).charAt(0);
      }
      break;
     }
    if (frame == "changeChars") 
     frame = "start";
   }
   else if (frame.equals("ingame"))
   {
    if (y >= (MaxDimensions.height/6.0))
    {
     if (gameType == 0)
     {
      if ((board.turn%2) == 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
      {
       if ((player1Choice = (int)((x*1.0/MaxDimensions.width*3) + (3*(int)((y-MaxDimensions.height/6.0)/(5*MaxDimensions.height/6.0)*3))) + 1) == board.getIndexValue(player1Choice))
         board.setIndexValue(player1Choice, board.player1Char);
      }
      else if ((board.turn%2) != 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
      {
       if ((player2Choice = (int)((x*1.0/MaxDimensions.width*3) + (3*(int)((y-MaxDimensions.height/6.0)/(5*MaxDimensions.height/6.0)*3))) + 1) == board.getIndexValue(player2Choice))
         board.setIndexValue(player2Choice, board.player2Char);
      }
      else if (board.turn == 9 && board.checkBoard().equals("Winner: Nobody"))
      {
       board.resetBoard();
       board.switchPlayers();
      }
      else if (!board.checkBoard().equals("Winner: Nobody"))
       frame = "endgame";
     }
     else
     {
      if ((board.turn%2) == 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
      {
       if (board.player1Char == (char) playerChar)
       {
        if ((player1Choice = (int)((x*1.0/MaxDimensions.width*3) + (3*(int)((y-MaxDimensions.height/6.0)/(5*MaxDimensions.height/6.0)*3))) + 1) == board.getIndexValue(player1Choice))
          board.setIndexValue(player1Choice, board.player1Char);
       }
       else
       {
        if (gameType == 1)
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
       }
      }
      if ((board.turn%2) != 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
      {
       if (board.player1Char != (char)playerChar)
       {
        if ((player2Choice = (int)((x*1.0/MaxDimensions.width*3) + (3*(int)((y-MaxDimensions.height/6.0)/(5*MaxDimensions.height/6.0)*3))) + 1) == board.getIndexValue(player2Choice))
         board.setIndexValue(player2Choice, board.player2Char);
       }
       else 
       {
        if (gameType == 1)
         player2Choice = board.nextEasyMove();
        else if (gameType == 2)
         player2Choice = board.nextMediumMove();
        else if (gameType == 3)
         player2Choice = board.nextHardMove();
        board.setIndexValue(player2Choice, board.player2Char);
       }
      }
      if (board.turn == 9 && board.checkBoard().equals("Winner: Nobody"))
      {
       board.resetBoard();
       board.switchPlayers();
      }
      else if (!board.checkBoard().equals("Winner: Nobody"))
       frame = "endgame";
     }
    }
   }
   else if (frame.equals("endgame"))
   {
    board = new TicTacToe('X', 'O');
    player1Choice = 0;
    player2Choice = 0;
    playerChar = (int) 'X';
    smartChoiceEnabled = false;
    gameType = 0;
    frame = "start";            
   }
   repaint();
  }
 }
 public Dimension getPreferredSize()
 {
  return MaxDimensions;
 }
 public int getSpacing()
 {
  return (int)((5.0/6.0*MaxDimensions.height)/3);
 }
 public void paintComponent(Graphics graphic)
 {
  graphic.setColor(new Color(245, 245, 220));
  graphic.fillRect(0, 0, MaxDimensions.width, MaxDimensions.height);
  graphic.setColor(Color.black);
  graphic.drawRect(0, 0, MaxDimensions.width, MaxDimensions.height);
  if (frame.equals("start"))
  {
   graphic.setColor(Color.black);
   graphic.drawLine(0, (int) (MaxDimensions.height/6.0), MaxDimensions.width, (int) (MaxDimensions.height/6.0));
   graphic.setFont(new Font(null, 0, (int) ((MaxDimensions.height/6.0)/4.0)));
   int stringHeight = graphic.getFontMetrics().getHeight();
   String[] greetings = {"Welocome to TicTacToe!" , "Please Select An Option Below:"};
   for (int index = greetings.length - 1; index >= 0; index--)
   {
    int stringWidth = graphic.getFontMetrics().stringWidth(greetings[index]);
    graphic.drawString(greetings[index], (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((MaxDimensions.height/6.0)/4.0 + (index+1)*stringHeight + (int)(stringHeight/8.0)));
   }
   graphic.setFont(new Font(null, 0, (int) (MaxDimensions.height/28.0)));
   stringHeight = graphic.getFontMetrics().getHeight();
   String[] labels = {"Rules", "Choose Different Chars.", "Enable SmartChoice("+smartChoiceEnabled+")?","Player vs. Player", "Player vs. Easy Comp.", "Player vs. Medium Comp.", "Player vs. Chuck Norris"};
   for (int index = labels.length - 1; index >= 0; index--)
   {
    int stringWidth = graphic.getFontMetrics().stringWidth(labels[index]);
    graphic.drawString(labels[index], (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((5*MaxDimensions.height/6.0)/8.0 + (index+1)*MaxDimensions.height/8.0));
    graphic.drawRect((int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((5*MaxDimensions.height/6.0)/8.0 + (index+1)*MaxDimensions.height/8.0) - stringHeight + (int)(stringHeight/8.0), stringWidth, stringHeight);
   }
  }
  else if (frame.equals("rules"))
  {
   graphic.setFont(new Font(null, 0, (int) (MaxDimensions.height/28.0)));
   int stringHeight = graphic.getFontMetrics().getHeight();
   String[] labels = {"The Goal Of TicTacToe Is To:", "Get Three Of Your Character In A Row","You Will Choose Your Move By", "Clicking The Number Associated", "With Where You Want To Move", "There Is An Option For SmartChoice", "Which Will Show The Next Best Move"};
   for (int index = labels.length - 1; index >= 0; index--)
   {
    int stringWidth = graphic.getFontMetrics().stringWidth(labels[index]);
    graphic.drawString(labels[index], (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((5*MaxDimensions.height/6.0)/8.0 + (index+1)*MaxDimensions.height/8.0));
   }
  }
  else if (frame.equals("changeChars"))
  {
   graphic.setFont(new Font(null, 0, (int) (MaxDimensions.height/24)));
   int stringHeight = graphic.getFontMetrics().getHeight();
   String[] labels = {"Click here to change player1's Char", "Click here to change player2's Char"};
   for (int index = labels.length - 1; index >= 0; index--)
   {
    int stringWidth = graphic.getFontMetrics().stringWidth(labels[index]);
    graphic.drawString(labels[index], (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((5*MaxDimensions.height/6.0)/8.0 + (index+1)*MaxDimensions.height/7.0));
    graphic.drawRect((int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((5*MaxDimensions.height/6.0)/8.0 + (index+1)*MaxDimensions.height/7.0) - stringHeight + (int)(stringHeight/8.0), stringWidth, stringHeight);
   }
  }
  else if (frame.equals("ingame"))
  {
   if (gameType == 0)
   {
    if ((board.turn%2) == 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
    {
     String temp = board.player1Char + "'s Choice";
     if (smartChoiceEnabled)
      if (board.smartPlay(board.player1Char) != 0)
       temp += " (" + board.smartPlay(board.player1Char) + ")";
      else if (board.smartPlay(board.player2Char) != 0)
       temp += " (" + board.smartPlay(board.player2Char) + ")";
      else if (board.turn >= 2 && board.getIndexValue(5) == board.player1Char)
       temp += " (" + board.neverLose(player2Choice) + ")";
     graphic.setFont(new Font(null, 0, (int) ((MaxDimensions.height/6.0)/4.0)));
     int stringWidth = graphic.getFontMetrics().stringWidth(temp), stringHeight = graphic.getFontMetrics().getHeight();
     graphic.drawString(temp, (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((MaxDimensions.height/6.0)/4.0 + stringHeight + (int)(stringHeight/8.0)));
    }
    else if ((board.turn%2) != 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
    {
     String temp = board.player2Char + "'s Choice";
     if (smartChoiceEnabled)
      if (board.smartPlay(board.player2Char) != 0)
       temp += " (" + board.smartPlay(board.player2Char) + ")";
      else if (board.smartPlay(board.player1Char) != 0)
       temp += " (" + board.smartPlay(board.player1Char) + ")";
      else if (board.turn >= 2 && board.getIndexValue(5) == board.player2Char)
       temp += " (" + board.neverLose(player1Choice) + ")";
     graphic.setFont(new Font(null, 0, (int) ((MaxDimensions.height/6.0)/4.0)));
     int stringWidth = graphic.getFontMetrics().stringWidth(temp), stringHeight = graphic.getFontMetrics().getHeight();
     graphic.drawString(temp, (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((MaxDimensions.height/6.0)/4.0 + stringHeight + (int)(stringHeight/8.0)));
    }
    else if (board.turn == 9 || !board.checkBoard().equals("Winner: Nobody"))
    {
     String temp = board.checkBoard();
     graphic.setFont(new Font(null, 0, (int) ((MaxDimensions.height/6.0)/4.0)));
     int stringWidth = graphic.getFontMetrics().stringWidth(temp), stringHeight = graphic.getFontMetrics().getHeight();
     graphic.drawString(temp, (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((MaxDimensions.height/6.0)/4.0 + stringHeight + (int)(stringHeight/8.0)));
    }
   }
   else
   {
    if ((board.turn%2) == 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
    {
     if (board.player1Char == (char) playerChar)
     {
      String temp = board.player1Char + "'s Choice";
      if (smartChoiceEnabled)
       if (board.smartPlay(board.player1Char) != 0)
        temp += " (" + board.smartPlay(board.player1Char) + ")";
       else if (board.smartPlay(board.player2Char) != 0)
        temp += " (" + board.smartPlay(board.player2Char) + ")";
       else if (board.turn >= 2 && board.getIndexValue(5) == board.player1Char)
        temp += " (" + board.neverLose(player2Choice) + ")";
      graphic.setFont(new Font(null, 0, (int) ((MaxDimensions.height/6.0)/4.0)));
      int stringWidth = graphic.getFontMetrics().stringWidth(temp), stringHeight = graphic.getFontMetrics().getHeight();
      graphic.drawString(temp, (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((MaxDimensions.height/6.0)/4.0 + stringHeight + (int)(stringHeight/8.0)));
     }
     else
     {
      String temp = "Player " + board.player1Char + "'s Choice: ";
      graphic.setFont(new Font(null, 0, (int) ((MaxDimensions.height/6.0)/4.0)));
      int stringWidth = graphic.getFontMetrics().stringWidth(temp), stringHeight = graphic.getFontMetrics().getHeight();
      graphic.drawString(temp, (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((MaxDimensions.height/6.0)/4.0 + stringHeight + (int)(stringHeight/8.0)));
     }
    }
    if ((board.turn%2) != 0 && board.checkBoard().equals("Winner: Nobody") && board.turn != 9)
    {
     if (board.player1Char != (char)playerChar)
     {
      String temp = board.player2Char + "'s Choice";
      if (smartChoiceEnabled)
       if (board.smartPlay(board.player2Char) != 0)
        temp += " (" + board.smartPlay(board.player2Char) + ")";
       else if (board.smartPlay(board.player1Char) != 0)
        temp += " (" + board.smartPlay(board.player1Char) + ")";
       else if (board.turn >= 2 && board.getIndexValue(5) == board.player2Char)
        temp += " (" + board.neverLose(player1Choice) + ")";
      graphic.setFont(new Font(null, 0, (int) ((MaxDimensions.height/6.0)/4.0)));
      int stringWidth = graphic.getFontMetrics().stringWidth(temp), stringHeight = graphic.getFontMetrics().getHeight();
      graphic.drawString(temp, (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((MaxDimensions.height/6.0)/4.0 + stringHeight + (int)(stringHeight/8.0)));
     }
     else 
     {
      String temp = "Player " + board.player2Char + "'s Choice: ";
      graphic.setFont(new Font(null, 0, (int) ((MaxDimensions.height/6.0)/4.0)));
      int stringWidth = graphic.getFontMetrics().stringWidth(temp), stringHeight = graphic.getFontMetrics().getHeight();
      graphic.drawString(temp, (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((MaxDimensions.height/6.0)/4.0 + stringHeight + (int)(stringHeight/8.0)));
     }
    }
    if (board.turn == 9 || !board.checkBoard().equals("Winner: Nobody"))
    {
     String temp = board.checkBoard();
     graphic.setFont(new Font(null, 0, (int) ((MaxDimensions.height/6.0)/4.0)));
     int stringWidth = graphic.getFontMetrics().stringWidth(temp), stringHeight = graphic.getFontMetrics().getHeight();
     graphic.drawString(temp, (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) ((MaxDimensions.height/6.0)/4.0 + stringHeight + (int)(stringHeight/8.0)));
    }
   }
   int spacing = getSpacing();
   graphic.setFont(new Font(null, 0, spacing/2));
   int charHeight = graphic.getFontMetrics().getHeight();
   graphic.setColor(Color.black);
   graphic.drawLine(0, (int) (MaxDimensions.height/6.0), MaxDimensions.width, (int) (MaxDimensions.height/6.0));
   for (int row = board.board.length - 1; row >= 0; row--)
    for (int col = board.board[row].length - 1; col >= 0; col--)
    {
     graphic.drawLine(col*spacing, row*spacing+(int) (MaxDimensions.height/6.0), col*spacing, row*spacing+spacing+(int) (MaxDimensions.height/6.0));
     graphic.drawLine(col*spacing, row*spacing+(int) (MaxDimensions.height/6.0), col*spacing+spacing, row*spacing+(int) (MaxDimensions.height/6.0));
     String temp = (board.board[row][col] > 9 || board.board[row][col] < 1) ? (char)(board.board[row][col]) + "": "";
     int charWidth = graphic.getFontMetrics().stringWidth(temp);
     graphic.drawString(temp, col*spacing + (int) (.5*spacing) - (int) (.5*charWidth), row*spacing+(int)(.5*spacing) + (int)(.25*charHeight) + (int) (MaxDimensions.height/6.0));
    }
  }
  else if (frame.equals("endgame"))
  {
   graphic.setColor(Color.black);
   graphic.setFont(new Font(null, 0, (int) (MaxDimensions.height/24.0)));
   String[] labels = {board.checkBoard(), "Have a nice day!"};
   int stringHeight = graphic.getFontMetrics().getHeight();
   for (int index = labels.length - 1; index >= 0; index--)
   {
    int stringWidth = graphic.getFontMetrics().stringWidth(labels[index]);
    graphic.drawString(labels[index], (int)(MaxDimensions.width/2.0 - stringWidth/2.0), (int) (MaxDimensions.height/2.0 + (index+1)*stringHeight/2.0 + (index)*stringHeight/2.0));
   }
  }
 }
}