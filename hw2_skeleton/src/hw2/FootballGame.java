package hw2;

/**
 * This class encapsulates the logic and state for a simplified
 * game of American football.  
 * 
 * @author Henry Kansanback
 */
public class FootballGame
{
  /**
   * @param TOUCHDOWN_POINTS
   * Number of points awarded for a touchdown.
   */  
  public static final int TOUCHDOWN_POINTS = 6;
  
  /**
   * @param EXTRA_POINTS
   * Number of points awarded for a successful extra point attempt
   * after a touchdown.
   */ 
  public static final int EXTRA_POINTS = 1;
  
  /**
   * Number of points awarded for a field goal.
   */
  public static final int FIELD_GOAL_POINTS = 3;
  
  /**
   * Total length of the field from goal line to goal line, in yards.
   */
  public static final int FIELD_LENGTH = 100;
  
  /**
   * Initial position of the offensive team after receiving a kickoff.
   */ 
  public static final int STARTING_POSITION = 70;
  
  /**
   * Yards required to get a first down.
   */
  public static final int YARDS_FOR_FIRST_DOWN = 10;
  /**
   * @param touchdown
   * a true or false to check whether or not a boolean was just executed
   * @param down
   * an integer value used to count the downs
   * @param yardstogoal
   * an integer value used to measure how close the offense is from the opposing teams goal
   * @param yardstodown
   * an integer value used to measure how far the offense is from getting a first down
   * @param teama
   * the team that begins with the ball (I originally had the offense decided on a sort of coin flip
   * but this turned out to be incorrect
   * @param teamb
   * the team that begins on offense
   * @param pointsa
   * measurement of teama's score
   * @param pointsb
   * measurement of teamb's score
   * @param offense
   * integer value that shows which team is on offense
   */
  private boolean touchdown;
  private int down;
  private int yardstogoal;
  private int yardstodown;
  private int teama;
  private int teamb;
  private int pointsa;
  private int pointsb;
  private int offense;
  public FootballGame()
  {
	  teama = 0;
	  teamb = 1;
	  offense = 0;
	  down = 1;
	  yardstodown = YARDS_FOR_FIRST_DOWN;
	  yardstogoal = STARTING_POSITION;
	  pointsa = 0;
	  pointsb = 0;
	  touchdown = false;
  }
  /**
   * 
   * @return number of yards from the ball to the defense's goal line
   */
  public int getYardsToGoalLine()
  {
	  return yardstogoal;
  }
  /**
   * 
   * @return number of yards to get a first down
   */
  public int getYardsToFirstDown()
  {;
	  return yardstodown;
  }
  /**
   * 
   * @param whichTeam
   * team index 0 or 1
   * @return score for team 0 if the given argument is 0, or the score for team 1 otherwise
   */
  public int getScore(int whichTeam)
  {
	  if(whichTeam == teama)
	  {
		  whichTeam = pointsa;
	  }
	  else if(whichTeam == teamb)
	  {
		  whichTeam = pointsb;
	  }
	  else if(1 < whichTeam || 0 > whichTeam)
	  {
		  whichTeam = 0;
	  }
	  return whichTeam;
  }
  /**
   * 
   * @return the current down as a number 1 through 4
   */
  public int getDown()
  {
	  return down;
  }
  /**
   * 
   * @return index of the team playing offense (0 or 1)
   */
  public int getOffense()
  {
	  return offense;
  }
  /**
   * 
   * @param yards
   * number of yards the ball is advanced
   */
  public void runOrPass(int yards)
  {
	  yardstogoal = yardstogoal - yards;
	  if(offense == 0)
	  {
		  if(yardstogoal < 0)
		  {
			  pointsa = pointsa + TOUCHDOWN_POINTS;
			  touchdown = true;
		  }
	  }
		  if(offense == 1)
		  {
		  if(yardstogoal < 0)
		  {
			  pointsb = pointsb + TOUCHDOWN_POINTS;
			  touchdown = true;
		  }		 
		  }
		  
		  if(yardstogoal <= FIELD_LENGTH && touchdown == false)
		  {
		  if(yards >= yardstodown)
		  {
			  yardstodown = yardstodown - yards;
			  ++down;
			  if(yardstodown < 0)
			  {
			  yardstodown = YARDS_FOR_FIRST_DOWN;
			  down = 1;				  
			  }
			  else if(yardstodown == 0)
			  {
				  yardstodown = YARDS_FOR_FIRST_DOWN;
				  down = 1;
			  }
			  else if(down > 4)
			  {
				  offense = 1 - offense;
				  yardstogoal = FIELD_LENGTH - yardstogoal;
				  down = 1;
				  yardstodown = YARDS_FOR_FIRST_DOWN;
			  }

		  }
		  else if(yards < yardstodown)
		  {
			  yardstodown = yardstodown - yards;
			  down++;
			  if(down > 4)
			  {
				  offense = 1 - offense;
				  yardstogoal = FIELD_LENGTH - yardstogoal;
				  down = 1;
				  yardstodown = YARDS_FOR_FIRST_DOWN;
			  }
		  }
		  }
		  else if(yardstogoal > FIELD_LENGTH)
		  {
			  yardstogoal = FIELD_LENGTH;
		  }		  
  }
  /**
   * 
   * @param success 
   * true if the extra point was successful, false otherwise
   */
  public void extraPoint(boolean success)
  {
	  if(success == true)
	  {
		  if(offense == 0)
		  {
			  down++;
			  pointsa = pointsa + EXTRA_POINTS;
		  }
		  else if(offense == 1)
		  {
			  down++;
			  pointsb = pointsb + EXTRA_POINTS;
		  }
	  }
	  offense = 1 - offense;
	  down = 1;
	  yardstogoal = STARTING_POSITION;
	  touchdown = false;
  }
  /**
   * 
   * @param success
   * true if the attempt was successful, false otherwise
   */
  public void fieldGoal(boolean success)
  {
	  if(success == true)
	  {
		  if(offense == 0)
		  {
			  offense = 1 - offense;
			  pointsa = pointsa + FIELD_GOAL_POINTS;
			  yardstogoal = STARTING_POSITION;
		  }
		  else if(offense == 1)
		  {
			  offense = 1 - offense;
			  pointsb = pointsb + FIELD_GOAL_POINTS;
			  yardstogoal = STARTING_POSITION;
		  }
	  }
	  else if(success == false)
	  {
		  if(offense == 0)
		  {
			  offense = 1 - offense;
			  yardstogoal = 100 - yardstogoal;
		  }
		  else if(offense == 1)
		  {
			  offense = 1 - offense;
			  yardstogoal = 100 - yardstogoal;
		  }		  
	  }
  }
  /**
   * 
   * @param yards
   * number of yards the ball is advanced by the punt
   */
  public void punt(int yards)
  {
	  offense = 1 - offense;	  
	  if(yardstogoal < yards)
	  {
		  down = 1;
		  yardstogoal = FIELD_LENGTH;
	  }
	  else
	  {
		  down = 1;
		  yardstogoal = 100 - (yardstogoal - yards);
	  }
  }
}
