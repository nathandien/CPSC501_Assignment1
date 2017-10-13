import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private static Scanner input = new Scanner (System.in);
	private static Random rnd = new Random();
	private static GameData monster[];
	private static Player player;
	/**
	 * @param arg
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Scanner input = new Scanner (System.in);
		//Random rnd=new Random();

		input = new Scanner (System.in);
		rnd = new Random();
		player = new Player("Player", 10, 100);
		
		monster = new GameData[6];
		monster[0] = new GameData("dog", 4, 30);
		monster[1] = new GameData("boar", 3, 50);
		monster[2] = new GameData("skeleton", 6, 25);
		monster[3] = new GameData("giant ant", 4, 45);
		monster[4] = new GameData("zombie", 7, 20);

	
		
		int start = 0, whatDo = 0;

			System.out.println("****Monster Rush****");
			System.out.println("Press 1 to begin!");
			start = input.nextInt();
			
			while(player.getCurrentHealth() > 0 && start == 1)
			{

				whatDo = actionStart();
				//Asks player to choose an action
				
				
				if(whatDo == 2)//Use potion
				{
					player.usePotion();
				}
				
				if(whatDo == 1)
				{
						battle();
				}
					
				whatDo = 0;
				//Resets whatDo back to 0, returns player to menu (asks player if they want to fight more monsters
				

			}//Game start
	}
	
	//Asks player for an action to take
	public static int actionStart() {
		
		System.out.println("\nWhat would you like to do?");
		System.out.println("Type in the following number to perform the respective action");
		System.out.println("1. Fight a monster");
		System.out.println("2. Use a potion");
		
		return input.nextInt();

	}
	
	public static void battle() {
		
		int monsterNumber = rnd.nextInt(5);
		//Randomizes a number
		//Player fights a monster with the respective number
		//System.out.println(monsterNumber);					monsterAppear = 1;
					//Used so that the "monster has appeared" only displays once
		//Remove after ***********************************
		
		System.out.println("\nA wild " + monster[monsterNumber].getName()+ " appears before you");
		
		
		while(true) {
			
			System.out.println("What do you want to do?");
			System.out.println("1. Attack monster");
			System.out.println("2. Use a potion");
			System.out.println("3. Flee");
			
			int action = input.nextInt();
			
			if(action == 1) {
				
				//Player attacks and deals damage to monster
				if(!playerAttack(monsterNumber)) {
					break;
				}
		
				//Monster attacks and deals damage to player
				monsterAttack(monsterNumber);
				
							
			}
			else if(action == 2) {
				
				if(player.getCurrentHealth() == player.getMaxHealth())
				{
					System.out.println("You are already full health");
				}
				else
				{
				
					player.usePotion();
					//Monster will attack player after using potion
					monsterAttack(monsterNumber);
			
				}
				
			}
			else if(action == 3) {
				
				int flee = rnd.nextInt(11); //Randomizes a number to determine if the player successfully flees
				if(flee < 5) //If the randomized number is less than 6, the player successfully flees, else flees and takes damage
				{
					System.out.println("You successfully fled away from the monster!");
					actionStart();
				}
				else
				{
					System.out.println("You failed to flee away from the monster");
					
					//Deals damage to the player if they fail to flee
					monsterAttack(monsterNumber);
					
				}
				
			}
		}
	}
	
	//Player attacks monster
	//Checks to see if monster has been killed (return true or false)
	public static boolean playerAttack(int monsterNum) {
		
		int damageDealtMonster = (int)player.calculateDmgDealt();
		System.out.println("\nYou inflicted " + damageDealtMonster + " damage");
		monster[monsterNum].setCurrentHealth(monster[monsterNum].getCurrentHealth() - damageDealtMonster);
		System.out.println("The " + monster[monsterNum].getName() + " has " + monster[monsterNum].getCurrentHealth() + "/" + monster[monsterNum].getMaxHealth() + " health remaining");
		
		if(monster[monsterNum].getCurrentHealth() <= 0) {
			
			monsterDefeated(monsterNum);
			return false;
			
		}
		return true;
		
	}
	
	//Monster attacks player and deals damage
	//Checks to see if player has died (quit game)
	public static void monsterAttack(int monsterNum) {
		
		int damageDealtPlayer = (int)monster[monsterNum].calculateDmgDealt();
		System.out.println("The " + monster[monsterNum].getName() +" inflicted " + damageDealtPlayer + " damage to you");
		player.setCurrentHealth(player.getCurrentHealth() - damageDealtPlayer);
		System.out.println("You have " + player.getCurrentHealth() + "/" + player.getMaxHealth() + " health remaining\n");
		//Monster attacks and deals damage to player
		
		//Ends the game if the player's health drops to 0
		if(player.getCurrentHealth() <= 0) {
			
			System.out.println("Game over \nYou have died");
			System.exit(0);
			
		}
		
	}
	
	public static void monsterDefeated(int monsterNum) {

			int potionChance = rnd.nextInt(10); //Randomizes a number to determine if player receives a potion
			
			if(potionChance <= 3)
			{
				player.incPotion();
				System.out.println("\nYou found 1 potion!");//If player has found a potion
			}
			else 
			{
				System.out.println("\nYou search the monster and find nothing");//If player has found nothing
			}
			
			//Calculates experience gained based on monster difficulty
			int expGained = rnd.nextInt(25) + 5 + ((monster[monsterNum].getCurrentHealth()*2 + (int)(monster[monsterNum].getAttkdmg())*5)/3);
			
			
			player.addExp(expGained);
			
			//Resets monster health
			monster[monsterNum].setCurrentHealth(monster[monsterNum].getMaxHealth());

		

		}

}
