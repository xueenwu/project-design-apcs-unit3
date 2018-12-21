//Ask Deppe about helper methods
//how are we supposed to write comments?

import java.util.Scanner;
import java.lang.NumberFormatException;
import java.util.concurrent.Semaphore;

/**
 * Simulates a Game
 * @author Taerim Eom and Jakin Ng
 * @version 12.8.2018
 */
public class Game 
{
	// the number of tries per turn
	public final static int TRIES = 2; 
	// the number of points necessary to advance to the next level
	public final static int LEVEL_THRESHOLD = 5;  
	
	public static void main(String[] args)
	{
		//makes a new Scanner and Player with correct values
		Player player = new Player(1, TRIES, 0);
		GameDisplayer gd = new GameDisplayer(player); 
		//while the player hasn't won, it keeps asking questions
		while (player.getLevel() != 3 && player.getPoints() != LEVEL_THRESHOLD)
		{
			System.out.println(player);
			gd.checkQuestion(player, TRIES);
			player.setTries(TRIES);
		}
		//ends the game
		gd.displayText("You have won!");
	}
	
//	/**
//	 * Checking the answer for the Question of the Problem
//	 * @param sc The Scanner being used for input
//	 * @param player The Player playing the Game
//	 */
//	public static void checkQuestion(GameDisplayer gd, Player player)
//	{
//		//generate a problem at the right level
//		Problem problem = ProblemGenerator.generateProblem(player.getLevel());
//		gd.updateGame(player.getLevel(), player.getTries(), 
//				player.getPoints(), problem.getQuestion());
//		//for the first try, gets the player's answer and performs the necessary actions
//		Semaphore semaphore = new Semaphore(0);
//		semaphore.acquire();
//		String playerAnswer = sc.nextLine();
//		testingAnswer(player, problem, playerAnswer, gd);
//		//uses the rest of the tries and performs necessary actions 
//		while (player.getTries() != TRIES && player.getTries() > 0)
//		{
//			System.out.println("Try again.");
//			playerAnswer = sc.nextLine();
//			testingAnswer(player, problem, playerAnswer, gd);
//		}
//	}
	
	/**
	 * Helper method for checkQuestion
	 * @param player The Player playing the Game
	 * @param problem The Problem being given
	 * @param playerAnswer The player's answer
	 */
	public static void testingAnswer(Player player, 
			Problem problem, String playerAnswer, GameDisplayer gd)
	{
		//if the player has entered an integer value: 
		try
		{
			//sets the player's answer in integer form
			int intAnswer = Integer.parseInt(playerAnswer);
			//if they're right, they do what they need to for a correct answer
			if (intAnswer == problem.getAnswer())
			{
				player.correctAnswer();
			}
			//otherwise consider it a wrong answer
			else
			{
				player.wrongAnswer();
			}
		}
		//otherwise it prompts them simply to enter an integer
		catch (NumberFormatException ex)
		{
			gd.displayText("You must enter an integer.");
		}
	}
}