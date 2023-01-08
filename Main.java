/* 
By: Christine Shao and Sammy Pater  
Date Started: 06/03/21
Last Edited: 06/16/21
Class: ICS3U12-2, Rob Fader 
Name of Assignment: Final Project ICS: TIC TAC TOE
Functionality: This is a Tic Tac Toe game against a computer AI. 
*/

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Main {
  public static void main(String[] args){
    // For counting how many games the user has played, the move they want to play, and for difficulty. Variables to be used later. 
    int flip = 0, move, totalGames=0;
    double wins = 0;

    //Assigning variable as yes to enter loop once game starts
    String again = "y";
    Random random = new Random();

    String [] board = new String[9];
    //Makes board 

    //fills board with numbers for user ease
    for (int i = 1; i <= 9; i++){
      int k = i;
      //typecasts the int to a string so it can be added to the array
      board[i-1] = Integer.toString(k);
    }
    
    Scanner kb = new Scanner(System.in);

    //menu choice at 4 allows us to automatically enter a loop
    String menuChoice = "4";

    //loop counter checks to make sure that the user has entered at least one input before printing it as an invalid choice
    int loopCounter = 0;

    //prints the menu screen and art
    menuArt();
    menuScreen();

    //Checks for validity of input 
    while (menuChoice.equals("4") && loopCounter >= 0){
      if (menuChoice.equals("1") || menuChoice.equals("3")){
        //will break if input is valid
        break;
      }

      else if (loopCounter > 0){
        //if user has entered at least one input, it will print it as invalid rather than it printing invalid automatically
        System.out.println("This is not a valid input. Please try again.");
        menuChoice = "4";
      }

      //increases the loop counter to enter the else if
      loopCounter ++;

      //goes through the validity check
      menuChoice = kb.nextLine();
      menuChoice = menu(menuChoice);
    }

    //menu choice of 3 indicates an exit or input of n indicates an exit so this will print the exit 
    if (menuChoice.equals("3") || again.equalsIgnoreCase("n") ){

      //credits
      menuCredits();

      //closing statement
      System.out.println("\nThank you for running this program. The program will now terminate.");

      //safe exit
      System.exit(0);
    }

    //do while loop to keep the game going
    do{
    if (menuChoice.equals("1")){ //GAME

      System.out.print("\nPlease choose a difficulty: \n1. Hard mode\n2. Easy mode\n");
      System.out.print("Please enter a NUMBER listed: ");

      String mode = kb.nextLine();
      //returns the diffi
      int diff = difficulty(mode);
      
      printBoard(board);
      int moveCounter = 0;
      String [] finalBoard;

      int randInt = random.nextInt();

      if (diff == 1){//hard
        flip = flipChoice(randInt);
        
        while (moveCounter!= 9){
        String [] board1 = userMove(board);
        moveCounter = moveCounter + 1;

          if (boardCheck(board1, moveCounter)){//user board check
            System.out.println(ANSI_GREEN + "You have won!" + ANSI_RESET);
            printBoard(board1);
            finalBoard = board1;
            wins = wins + 1;
            totalGames = totalGames + 1;
            break;
          }
          if (moveCounter >= 9){//Tie check 
            System.out.println(ANSI_YELLOW + "Neither of you have won. Tie!" + ANSI_RESET);
            printBoard(board1);
            finalBoard = board1;
            wins = wins + 0.5;
            totalGames = totalGames + 1;
            break;
          }
          
          String [] newBoard = compMove(flip, board1);
          printBoard(newBoard);
          moveCounter = moveCounter + 1;

          if (boardCheck(newBoard)){ //comp board check
            System.out.println(ANSI_RED + "The computer has won!" + ANSI_RESET);
            printBoard(newBoard);
            finalBoard = board1;
            totalGames = totalGames + 1;
            break;
          }

        }
      }

      else if (diff == 2){// easy
        flip = flipChoice(randInt, diff);

        while (moveCounter!= 9){
        String [] board1 = userMove(board);
        moveCounter = moveCounter + 1;

        if (boardCheck(board1, moveCounter)){//user board check
          System.out.println(ANSI_GREEN + "You have won!" + ANSI_RESET);
          printBoard(board1);
          finalBoard = board1;
          totalGames = totalGames + 1;
          wins = wins + 1;
          break;
        }
        
        if (moveCounter >= 9){//Tie check 
          System.out.println(ANSI_YELLOW + "Neither of you have won. Tie!" + ANSI_RESET);
          printBoard(board1);
          finalBoard = board1;
          totalGames = totalGames + 1;
          wins = wins + 0.5;
          break;
        }
        String [] newBoard = compMove(board1);
        printBoard(newBoard);
        moveCounter = moveCounter + 1;

        if (boardCheck(newBoard)){ //comp board check
          System.out.println(ANSI_RED + "The computer has won!" + ANSI_RESET);
          printBoard(newBoard);
          finalBoard = board1;
          totalGames = totalGames + 1;
          break;
        }

        } //game while loop
      }//else if
      System.out.print("Do you want to play again? (y/n): ");
      again = kb.nextLine();
      
      again = validity(again, 2);
      
      // Resets board.
      board[0] = "1";
      board[1] = "2";
      board[2] = "3";
      board[3] = "4";
      board[4] = "5";
      board[5] = "6";
      board[6] = "7";
      board[7] = "8";
      board[8] = "9";

      }//if menu choice
    }while(again.equalsIgnoreCase("y"));


    //Repeated again in the case that the user enters n after playing one iteration of the game.
    if (menuChoice.equals("3") || again.equalsIgnoreCase("n") ){
      System.out.println("You have won "+wins+" times in "+ totalGames +" games total.");
      menuCredits();
      System.out.println("\nThank you for running this program. The program will now terminate.");
      System.exit(0);
    }

  } //End of main method

/** This method prints out the menu art for decoration. VOID METHOD made by CHRISTINE */

public static void menuArt(){
  System.out.println("████████╗██╗ ██████╗    ████████╗ █████╗  ██████╗    ████████╗ ██████╗ ███████╗");
  System.out.println("╚══██╔══╝██║██╔════╝    ╚══██╔══╝██╔══██╗██╔════╝    ╚══██╔══╝██╔═══██╗██╔════╝");
  System.out.println("   ██║   ██║██║            ██║   ███████║██║            ██║   ██║   ██║█████╗  ");
  System.out.println("   ██║   ██║██║            ██║   ██╔══██║██║            ██║   ██║   ██║██╔══╝  ");
  System.out.println("   ██║   ██║╚██████╗       ██║   ██║  ██║╚██████╗       ██║   ╚██████╔╝███████╗");
  System.out.println("   ╚═╝   ╚═╝ ╚═════╝       ╚═╝   ╚═╝  ╚═╝ ╚═════╝       ╚═╝    ╚═════╝ ╚══════╝");
  System.out.println("");
  System.out.println("");
}

/** These methods allow text to be coloured! Made by CHRISTINE */

public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";

/** This method prints out the credits menu for the user. VOID METHOD made by CHRISTINE */

public static void menuCredits(){
  System.out.println("\nCreators of Computer AI Tic-Tac-Toe!:");
  System.out.print("\nChristine Shao and Sammy Pater");
  System.out.println("\nDate started: 06/03/2021");
  System.out.println("Date finished: 06/16/2021");
}

/** This method prints out the menu for the user and explains what the program will do. It also prints the statement asking for input on what you want to do next. VOID METHOD made by SAMMY */

public static void menuScreen(){ 
  System.out.print("Hi! Welcome to Tic-Tac-Toe!\n\nTic-Tac-Toe is a game where you have a # shaped grid, and you take turns placing an " + ANSI_BLUE + "X" + ANSI_RESET + " or an " + ANSI_RED + "O" + ANSI_RESET + " until you get 3 in a line or fill all 9 squares. You play against the computer. It is recommended that you read the rules in depth to learn how to play on this format.");

  System.out.print("\n\n1. Play now!\n2. Rules\n3. Exit\n");
  System.out.print("Please enter a NUMBER listed: ");
}

/** This method takes the menu input, prints the rules, and returns the menuChoice value to determine if the user is exiting the program or starting a game. RETURN + PARAMETER METHOD made by SAMMY */

public static String menu(String menuChoice){

  if (menuChoice.equals("1")){
    return menuChoice = "1";
  }

  else if (menuChoice.equals("2")){
    System.out.println("\nThe game is played on a grid that's 3 squares by 3 squares. Player 1 is " + ANSI_BLUE + "X" + ANSI_RESET + ", Computer is " + ANSI_RED + "O" + ANSI_RESET + ". Players take turns putting their marks in empty squares. The squares are number 1-9, top to bottom, left to right.\n" + 
    "\n1|2|3" +
    "\n-+-+-" +
    "\n4|5|6" +
    "\n-+-+-" + 
    "\n7|8|9\n" +
    "\nThe first player to get 3 of their marks in a row (up, down, across, or diagonally) is the winner. When all 9 squares are full, or someone wins, the game is over.\n");
    System.out.println("Wins are worth 1 point, ties are worth 0.5 points and losses are worth 0 points.\n");
    System.out.println("Hard mode includes a computer AI if you want to challenge yourself! Easy mode will have the computer randomly generate moves.\n");

    System.out.println(ANSI_GREEN + "The game will now start!" + ANSI_RESET);
    return menuChoice = "1"; //To enter the loop of games automatically

  }

  else if (menuChoice.equals("3")){
    return menuChoice = "3";
  }
  else{
    return menuChoice = "4";
  }

  }

/** This method returns the difficulty that the user picks to choose which game it will enter based on the computer's coded difficulty. RETURN + PARAMETER METHOD made by SAMMY */

public static int difficulty(String difficulty){
  int diff;

  if (difficulty.equals("1")){
    return diff = 1;
  }

  else {
    return diff = 2;
  }
}

/** This method determines the flip for the computer to decide it's difficulty. Flip of 2 is randomly moving. RETURN + PARAMETER METHOD made by CHRISTINE*/

public static int flipChoice(int flip, int difficulty){ 
  //easy mode
  int diff;
  return flip = 2; //will move randomly
}

/** This method determines the flip for the computer to decide its difficulty. Flip of 1 will guarantee a player block, meaning the user will have to outsmart the computer. RETURN + PARAMETER METHOD made by SAMMY */

public static int flipChoice(int flip){ 
  //hard mode
  return flip = 1; //Will block player
}

/** This method takes the userMove and determines if the space is open, and if it is, it will return from another method (printBoard) to move the user to that spot. RETURN + PARAMETER METHOD made by CHRISTINE */

public static String[] userMove(String[] board){ 
  Scanner kb = new Scanner(System.in);
  System.out.println("What space do you want to move to?: ");
  
  int move = kb.nextInt()-1;
  while (!validity(move)){
    System.out.println("That is not a valid move. Please enter again.");
    move = kb.nextInt()-1;
    validity(move);
  }

  if (validity(move)){
  while (!openSpace(move, board)){
    System.out.println("That space is filled. What space do you want to move to?: ");
    move = kb.nextInt()-1;
    openSpace(move,board);
  }
  }
  return printBoard(move, board, "user");
  
}

/** This method checks the validity of the user's input to see if it is yes or no, and if it isnt it will loop until that is achieved. RETURN + PARAMETER + OVERLOAD METHOD made by CHRISTINE. */

public static String validity(String again, int check){ 
  Scanner kb = new Scanner(System.in);
  
  while (!(again.equalsIgnoreCase("y") || again.equalsIgnoreCase("n"))){
      System.out.print("That is not a valid entry. Please enter (y/n): ");
      again = kb.nextLine();
    }
  return again;
}

/** This method checks if the move inputed by the user is a valid move. RETURN + PARAMETER + OVERLOAD METHOD made by CHRISTINE.*/
public static boolean validity(int move){
  if (move >10 || move < 0){
    return false;
  }
  else{
    return true;
  }
}

/** This method will only be accessed on hard mode. This method is designed to see where the computer can move to automatically win, based on if the space is open and if it has 2 lined up to make the win true. This method will also see if the user is one away from winning, and will block the user if it is. RETURN + PARAMETER METHOD made by CHRISTINE. */

public static String[] compMove(int flipChoice, String [] board){ 
 Random random = new Random();
  if (flipChoice == 1){
    if (board[0].equals("O") && board[1].equals("O") && board[2].equals("3")){
      return printBoard(2, board);
    }
    else if (board[0].equals("O") && board[2].equals("O") && board[1].equals("2")){
      return printBoard(1, board);
    }             
    else if (board[1].equals("O") && board[2].equals("O") && board[0].equals("1")){
      return printBoard(0, board);           
    }
    //Across the second row
    else if (board[3].equals("O") && board[4].equals("O") && board[5].equals("6")){
      return printBoard(5, board);
    }
    else if (board[3].equals("O") && board[5].equals("O") && board[4].equals("5")){
      return printBoard(4, board);
    }
    else if (board[4].equals("O") && board[5].equals("O") && board[3].equals("4")){
      return printBoard(3, board);
    }
  //Across the third row
    else if (board[6].equals("O") && board[7].equals("O") && board[8].equals("9")){
      return printBoard(8, board);
    }                
    else if (board[6].equals("O") && board[8].equals("O") && board[7].equals("8")){
      return printBoard(7, board);
        
    }                 
    else if (board[7].equals("O") && board[8].equals("O") && board[6].equals("7")){
      return printBoard(6, board);
       
    }    

    //Down the first column
    else if (board[0].equals("O") && board[3].equals("O") && board[6].equals("7")){
      return printBoard(6, board);
        
    }                
    else if (board[0].equals("O") && board[6].equals("O") && board[3].equals("4")){
      return printBoard(3, board);
       
    }                  
    else if (board[6].equals("O") && board[3].equals("O") && board[0].equals("1")){
      return printBoard(0, board);
         
    }                
    
    //Down the second column
    else if (board[1].equals("O") && board[4].equals("O") && board[7].equals("8")){
      return printBoard(7, board);
              
    }           
    else if (board[1].equals("O") && board[7].equals("O")&& board[4].equals("5")){
      return printBoard(4, board);
         
    }                
    else if (board[4].equals("O") && board[7].equals("O") && board[1].equals("2")){
      return printBoard(1, board);
                         
    }
    //Down the third column
    else if (board[2].equals("O") && board[5].equals("O") && board[8].equals("9")){
      return printBoard(8, board);
        
    }                
    else if (board[2].equals("O") && board[8].equals("O") && board[5].equals("6")){
      return printBoard(5, board);
        
    }                
    else if (board[5].equals("O") && board[8].equals("O") && board[2].equals("3")){
      return printBoard(2, board);   
    }  

    //Diagonal 1 5 9
    else if (board[0].equals("O") && board[4].equals("O") && board[8].equals("9")){
      return printBoard(8, board);
         
    }                
    else if (board[0].equals("O") && board[8].equals("O") && board[4].equals("5")){
      return printBoard(4, board);
          
    }              
    else if (board[4].equals("O") && board[8].equals("O") && board[0].equals("1")){
      return printBoard(0, board);
        
    }
    //Diagonal 3 5 7
    else if (board[2].equals("O") && board[4].equals("O") && board[6].equals("7")){
      return printBoard(6, board);
       
    }
    else if (board[2].equals("O") && board[6].equals("O") && board[4].equals("5")){
      return printBoard(4, board);
       
    }
    else if (board[4].equals("O") && board[6].equals("O")&& board[2].equals("3")){
      return printBoard(2, board);  
    }

    else if (board[0].equals("X") && board[1].equals("X") && board[2].equals("3")){
      return printBoard(2, board);
    }
    else if (board[0].equals("X") && board[2].equals("X") && board[1].equals("2")){
      return printBoard(1, board);
    }             
    else if (board[1].equals("X") && board[2].equals("X") && board[0].equals("1")){
      return printBoard(0, board);           
    }
    //Across the second row
    else if (board[3].equals("X") && board[4].equals("X") && board[5].equals("6")){
      return printBoard(5, board);
    }
    else if (board[3].equals("X") && board[5].equals("X") && board[4].equals("5")){
      return printBoard(4, board);
    }
    else if (board[4].equals("X") && board[5].equals("X") && board[3].equals("4")){
      return printBoard(3, board);
    }
  //Across the third row
    else if (board[6].equals("X") && board[7].equals("X") && board[8].equals("9")){
      return printBoard(8, board);
    }                
    else if (board[6].equals("X") && board[8].equals("X") && board[7].equals("8")){
      return printBoard(7, board);
        
    }                 
    else if (board[7].equals("X") && board[8].equals("X") && board[6].equals("7")){
      return printBoard(6, board);
       
    }    

    //Down the first column
    else if (board[0].equals("X") && board[3].equals("X") && board[6].equals("7")){
      return printBoard(6, board);
        
    }                
    else if (board[0].equals("X") && board[6].equals("X") && board[3].equals("4")){
      return printBoard(3, board);
       
    }                  
    else if (board[6].equals("X") && board[3].equals("X") && board[0].equals("1")){
      return printBoard(0, board);
         
    }                
    
    //Down the second column
    else if (board[1].equals("X") && board[4].equals("X") && board[7].equals("8")){
      return printBoard(7, board);
              
    }           
    else if (board[1].equals("X") && board[7].equals("X")&& board[4].equals("5")){
      return printBoard(4, board);
         
    }                
    else if (board[4].equals("X") && board[7].equals("X") && board[1].equals("2")){
      return printBoard(1, board);
                         
    }
    //Down the third column
    else if (board[2].equals("X") && board[5].equals("X") && board[8].equals("9")){
      return printBoard(8, board);
        
    }                
    else if (board[2].equals("X") && board[8].equals("X") && board[5].equals("6")){
      return printBoard(5, board);
        
    }                
    else if (board[5].equals("X") && board[8].equals("X") && board[2].equals("3")){
      return printBoard(2, board);   
    }  

    //Diagonal 1 5 9
    else if (board[0].equals("X") && board[4].equals("X") && board[8].equals("9")){
      return printBoard(8, board);
         
    }                
    else if (board[0].equals("X") && board[8].equals("X") && board[4].equals("5")){
      return printBoard(4, board);
          
    }              
    else if (board[4].equals("X") && board[8].equals("X") && board[0].equals("1")){
      return printBoard(0, board);
        
    }
    //Diagonal 3 5 7
    else if (board[2].equals("X") && board[4].equals("X") && board[6].equals("7")){
      return printBoard(6, board);
       
    }
    else if (board[2].equals("X") && board[6].equals("X") && board[4].equals("5")){
      return printBoard(4, board);
       
    }
    else if (board[4].equals("X") && board[6].equals("X")&& board[2].equals("3")){
      return printBoard(2, board);  
    }
    else{
      int randInt = random.nextInt(9);
      String [] b = printBoard(randInt, 1, board);
      return b;
    }
  }

  else {
    int randInt = random.nextInt(9);
    return printBoard(randInt, 1, board);
  }
    
}

/** This method will only be accessed on easy mode. This method will have the computer move to a random spot. RETURN + PARAMETER METHOD made by SAMMY. */
public static String[] compMove(String [] board){
  Random random = new Random();
  int randInt = random.nextInt(9);
  return printBoard(randInt, 1, board);
}

/**This method will check if a space is open and will return true if it is, and if it is false will return false, causing it to enter a loop in other methods until a valid input is found. for USER. RETURN + PARAMETER METHOD made by CHRISTINE.*/
public static boolean openSpace(int move, String[] board){
  if ((!board[move].equals("X")) && !(board[move].equals("O"))){
    return true;
  }
  else{
    return false;
  }

}

/**This method will check if a win condition "3 in a row, horizontal, or diagonal" is met, and if it is, will return true and will print out a win in the main method that will stop the game as someone has won. this is for COMPUTER. RETURN + PARAMETER + OVERLOAD METHOD made by SAMMY*/

public static boolean boardCheck(String[] board){ 
  if ((board[0].equals("O") && board[1].equals("O") && board[2].equals("O")) || (board[0].equals("O") && board[2].equals("O") && board[1].equals("O")) || (board[1].equals("O") && board[2].equals("O") && board[0].equals("O")) || (board[3].equals("O") && board[4].equals("O") && board[5].equals("O")) || (board[3].equals("O") && board[5].equals("O") && board[4].equals("O")) || (board[4].equals("O") && board[5].equals("O") && board[3].equals("O")) || (board[6].equals("O") && board[7].equals("O") && board[8].equals("O")) || (board[6].equals("O") && board[8].equals("O") && board[7].equals("O")) || (board[7].equals("O") && board[8].equals("O") && board[6].equals("O")) || (board[0].equals("O") && board[4].equals("O") && board[8].equals("O")) || (board[0].equals("O") && board[8].equals("O") && board[4].equals("O")) || (board[4].equals("O") && board[8].equals("O") && board[0].equals("O")) || (board[0].equals("O") && board[3].equals("O") && board[6].equals("O")) || (board[0].equals("O") && board[6].equals("O") && board[3].equals("O")) || (board[3].equals("O") && board[6].equals("O") && board[0].equals("O")) || (board[1].equals("O") && board[4].equals("O") && board[7].equals("O")) || (board[1].equals("O") && board[7].equals("O") && board[4].equals("O")) || (board[4].equals("O") && board[7].equals("O") && board[1].equals("O")) || (board[2].equals("O") && board[5].equals("O") && board[8].equals("O")) || (board[2].equals("O") && board[8].equals("O") && board[5].equals("O")) || (board[5].equals("O") && board[8].equals("O") && board[2].equals("O")) || (board[2].equals("O") && board[4].equals("O") && board[6].equals("O")) || (board[2].equals("O") && board[6].equals("O") && board[4].equals("O")) || (board[4].equals("O") && board[6].equals("O") && board[2].equals("O"))){
    return true;
  }
  else{
    return false;
  }
  }//End of method

/**This method will check if a win condition "3 in a row, horizontal, or diagonal" is met, and if it is, will return true and will print out a win in the main method that will stop the game as someone has won. this is for USER. RETURN + PARAMETER + OVERLOAD METHOD made by SAMMY*/

public static boolean boardCheck(String[] board, int user){
  if ((board[0].equals("X") && board[1].equals("X") && board[2].equals("X")) || (board[0].equals("X") && board[2].equals("X") && board[1].equals("X"))|| (board[1].equals("X") && board[2].equals("X") && board[0].equals("X"))|| (board[3].equals("X") && board[4].equals("X") && board[5].equals("X")) || (board[3].equals("X") && board[5].equals("X") && board[4].equals("X")) || (board[4].equals("X") && board[5].equals("X") && board[3].equals("X")) || (board[6].equals("X") && board[7].equals("X") && board[8].equals("X")) || (board[6].equals("X") && board[8].equals("X") && board[7].equals("X")) || (board[7].equals("X") && board[8].equals("X") && board[6].equals("X")) || (board[0].equals("X") && board[4].equals("X") && board[8].equals("X")) || (board[0].equals("X") && board[8].equals("X") && board[4].equals("X")) || (board[4].equals("X") && board[8].equals("X") && board[0].equals("X")) || (board[0].equals("X") && board[3].equals("X") && board[6].equals("X")) || (board[0].equals("X") && board[6].equals("X") && board[3].equals("X")) || (board[3].equals("X") && board[6].equals("X") && board[0].equals("X")) || (board[1].equals("X") && board[4].equals("X") && board[7].equals("X")) || (board[1].equals("X") && board[7].equals("X") && board[4].equals("X")) || (board[4].equals("X") && board[7].equals("X") && board[1].equals("X")) || (board[2].equals("X") && board[5].equals("X") && board[8].equals("X")) || (board[2].equals("X") && board[8].equals("X") && board[5].equals("X")) || (board[5].equals("X") && board[8].equals("X") && board[2].equals("X")) || (board[2].equals("X") && board[4].equals("X") && board[6].equals("X")) || (board[2].equals("X") && board[6].equals("X") && board[4].equals("X")) || (board[4].equals("X") && board[6].equals("X") && board[2].equals("X"))){
    return true;
  }

  else{
    return false;
  }

  }//End of method

/**This method will place the USER's move and return the new updated board. RETURN + PARAMETER + OVERLOAD METHOD made by SAMMY*/

public static String[] printBoard(int move, String [] inputBoard, String user){
  String [] board = inputBoard;
  board[move] = "X";
  return board;
} 

/**This method will print the board and copy the move over. for COMPUTER. RETURN + PARAMETER + OVERLOAD METHOD made by CHRISTINE*/

public static String[] printBoard(int move, String [] inputBoard){
  String [] board = inputBoard;
  board[move] = "O";
  return board;
} 

/**This method will iterate through all the possible moves the computer can go to. Next, it will randomly place a move out of all the possible moves and return the new updated board. RETURN + PARAMETER + OVERLOAD METHOD made by CHRISTINE */

public static String[] printBoard(int inputMove, int comp, String[] inputBoard){
  String [] board = inputBoard;
  ArrayList<String> open = new ArrayList<String>();
  Random random = new Random();
  for (String i: board){
    if (i.matches("-?\\d+(\\.\\d+)?")){
      open.add(i);
    }
  }
  int move = random.nextInt(open.size());
  board[Integer.parseInt(open.get(move))-1] = "O";

  return board;
} 

/** This method prints out the board every time, making the illusion of a hashtag with the - and +. for user aesthetics. RETURN + PARAMETER + OVERLOAD METHOD made by SAMMY. */

public static void printBoard(String [] board){
  System.out.println("");
  // ROW 1 
  if (board[0].equalsIgnoreCase("X")){
    System.out.print(ANSI_BLUE + board[0] + ANSI_RESET+ '|');
  }
  else if (board[0] .equalsIgnoreCase("O")){
    System.out.print(ANSI_RED + board[0] + ANSI_RESET+ '|');
  }
  else{
    System.out.print(board[0]+'|');
  }
  if (board[1] .equalsIgnoreCase("X")){
    System.out.print(ANSI_BLUE + board[1] + ANSI_RESET+ '|');
  }
  else if (board[1] .equalsIgnoreCase("O")){
    System.out.print(ANSI_RED + board[1] + ANSI_RESET+ '|');
  }
  else{
    System.out.print(board[1]+'|');
  }
  if (board[2] .equalsIgnoreCase("X")){
    System.out.print(ANSI_BLUE + board[2] + ANSI_RESET +"\n");
  }
  else if (board[2] .equalsIgnoreCase("O")){
    System.out.print(ANSI_RED + board[2] + ANSI_RESET +"\n");
  }
  else{
    System.out.print(board[2]+ "\n");
  }

  System.out.println("-+-+-");

  // ROW 2
  if (board[3] .equalsIgnoreCase("X")){
    System.out.print(ANSI_BLUE + board[3] + ANSI_RESET+ '|');
  }
  else if (board[3] .equalsIgnoreCase("O")){
    System.out.print(ANSI_RED + board[3] + ANSI_RESET+ '|');
  }
  else{
    System.out.print(board[3]+'|');
  }
  if (board[4] .equalsIgnoreCase("X")){
    System.out.print(ANSI_BLUE + board[4] + ANSI_RESET+ '|');
  }
  else if (board[4] .equalsIgnoreCase("O")){
    System.out.print(ANSI_RED + board[4] + ANSI_RESET+ '|');
  }
  else{
    System.out.print(board[4]+'|');
  }
  if (board[5] .equalsIgnoreCase("X")){
    System.out.print(ANSI_BLUE + board[5] + ANSI_RESET +"\n");
  }
  else if (board[5] .equalsIgnoreCase("O")){
    System.out.print(ANSI_RED + board[5] + ANSI_RESET +"\n");
  }
  else{
    System.out.print(board[5]+"\n");
  }

  System.out.println("-+-+-");

  // ROW 3 

  if (board[6] .equalsIgnoreCase("X")){
    System.out.print(ANSI_BLUE + board[6] + ANSI_RESET+ '|');
  }
  else if (board[6] .equalsIgnoreCase("O")){
    System.out.print(ANSI_RED + board[6] + ANSI_RESET+ '|');
  }
  else{
    System.out.print(board[6]+'|');
  }
  if (board[7] .equalsIgnoreCase("X")){
    System.out.print(ANSI_BLUE + board[7] + ANSI_RESET+ '|');
  }
  else if (board[7] .equalsIgnoreCase("O")){
    System.out.print(ANSI_RED + board[7] + ANSI_RESET+ '|');
  }
  else{
    System.out.print(board[7]+'|');
  }
  if (board[8] .equalsIgnoreCase("X")){
    System.out.print(ANSI_BLUE + board[8] + ANSI_RESET +"\n");
  }
  else if (board[8] .equalsIgnoreCase("O")){
    System.out.print(ANSI_RED + board[8] + ANSI_RESET +"\n");
  }
  else{
    System.out.print(board[8]+"\n");
  }
  
}

} // end of class