import java.util.Random;
import java.util.Scanner;

public class Game {

	/**
	 * @param arg
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner (System.in);
		Random rnd=new Random();

		GameData player = new GameData("Player", 10, 100);
		
		
		GameData monster[] = new GameData[6];
		
		monster[0] = new GameData("dog", 4, 30);
		monster[1] = new GameData("boar", 3, 50);
		monster[2] = new GameData("skeleton", 6, 25);
		monster[3] = new GameData("giant ant", 4, 45);
		monster[4] = new GameData("zombie", 7, 20);

	
		
		int start = 0, whatDo, monsterNumber = 0, monsterAppear = 0, action, damageDealtPlayer, damageDealtMonster, levelPoint;
		int level = 1, reqExp = 100, expGained, haveExp = 0;
		int potion = 5, potionChance, flee;
		
			System.out.println("****Monster Rush****");
			System.out.println("Press 1 to begin!");
			start = input.nextInt();
			
			while(player.getCurrentHealth() > 0 && start == 1)
			{
				//Starts the game
				System.out.println("\nWhat would you like to do?");
				System.out.println("Type in the following number to perform the respective action");
				System.out.println("1. Fight a monster");
				System.out.println("2. Use a potion");
				whatDo = input.nextInt();
				//Asks player to choose an action
				
				
				if(whatDo == 2)//Use potion
				{
					if(potion > 0)//Player must have a potion to use it
					{
						if(player.getCurrentHealth() == player.getMaxHealth())
						{
							System.out.println("You are already full health");
						}
						else
						{
						player.setCurrentHealth(player.getCurrentHealth() + 50); //Heals the player for 50 health
						if(player.getCurrentHealth() > player.getMaxHealth())
						{
							player.setCurrentHealth(player.getMaxHealth());
							//Checks to see if the potion heals the player for more than their maximum health, if so, changes it to maximum amount
						}
						System.out.println("You now have " + player.getCurrentHealth() + "/" + player.getMaxHealth() + " health now");
						
						potion--; 
						//Consumes potion (removes 1)
						
						}
					}
				}
				
				if(whatDo == 1)
				{
					monsterAppear = 1;
					//Used so that the "monster has appeared" only displays once
					
					
				}
					while(whatDo == 1)
					{			
						if(monsterAppear == 1)
							{	
							monsterNumber = rnd.nextInt(5);
							//Randomizes a number
							//Player fights a monster with the respective number
							System.out.println(monsterNumber);
							//Remove after ***********************************
							
							System.out.println("\nA wild " + monster[monsterNumber].getName()+ " appears before you");
							monsterAppear = 0;
							
							}
							
						
						System.out.println("What do you want to do?");
						System.out.println("1. Attack monster");
						System.out.println("2. Use a potion");
						System.out.println("3. Flee");
						action = input.nextInt();
						//User chooses a course of action
					
						if(action == 1)//Attack monster
						{
							
							damageDealtMonster = (int)player.calculateDmgDealt();
							System.out.println("\nYou inflicted " + damageDealtMonster + " damage");
							monster[monsterNumber].setCurrentHealth(monster[monsterNumber].getCurrentHealth() - damageDealtMonster);
							System.out.println("The " + monster[monsterNumber].getName() + " has " + monster[monsterNumber].getCurrentHealth() + "/" + monster[monsterNumber].getMaxHealth() + " health remaining");
							//Deals damage to monster (subtracts health by damage)
						
							
							if(monster[monsterNumber].getCurrentHealth() <= 0) //Monster has been defeated
							{
								potionChance = rnd.nextInt(10); //Randomizes a number to determine if player receives a potion
								if(potionChance <= 3)
								{
									potion++;
									System.out.println("\nYou found 1 potion!");//If player has found a potion
								}
								else
									System.out.println("\nYou search the monster and find nothing");//If player has found nothing
								
								
								
								expGained = rnd.nextInt(25) + 5 + ((monster[monsterNumber].getCurrentHealth()*2 + (int)(monster[monsterNumber].getAttkdmg())*5)/3);
								haveExp = haveExp + expGained;
								monster[monsterNumber].setCurrentHealth(20);
								//Player gains experience based on monster, and resets the monsters health
							
								System.out.println("\nYou have defeated the monster and gained " + expGained + " experience" );
								System.out.println("You now have " + haveExp + "/" + reqExp + " experience");
							
								if(haveExp >= reqExp)
								{
									level++;
									haveExp = haveExp - reqExp;
									reqExp = 100+level*20;
									//Increases the level of the player and carries over any experience over the required experience for the next level
									//Required experience for next level is also calculated
									
									
									System.out.println("Congratulations! You have leveled up! You are now level " + level +" You may put 1 point into your attack damage or max health");
									System.out.println("1. Attack Damage");
									System.out.println("2. Max Health");
									levelPoint = input.nextInt();
									//Allows the user to allocate 1 point to attack damage or health if they level up
									
									if(levelPoint == 1)
										{
										player.setAttkdmgPoints(player.getAttkdmgPoints() + 2);
										//Allocates 2 points to attack damage
							
										System.out.println("You now have " + player.getAttkdmgPoints() + " attack damage points");
									
										player.setCurrentHealth(player.getMaxHealth());
										//Restores player's health back to maximum
										}
									
									if(levelPoint == 2)
									{
										player.incVitality();
										//Allocates 2 points to health (10 per point=20+ to maximum health)
										
										System.out.println("You now have " + player.getVitality() + " health points (max health: " + player.getMaxHealth() + ")");
										//Allocates a point to health
										
										player.setCurrentHealth(player.getMaxHealth());
										//Restores player's health back to maximum
									}
								}
								break;
								//Ends the fight if the monsters health reaches 0
							}
							if(monster[monsterNumber].getCurrentHealth() > 0) //Monster is still alive and deals damage to player
							{
							
								damageDealtPlayer = (int)monster[monsterNumber].calculateDmgDealt();
								System.out.println("The " + monster[monsterNumber].getName() +" inflicted " + damageDealtPlayer + " damage to you");
								player.setCurrentHealth(player.getCurrentHealth() - damageDealtPlayer);
								System.out.println("You have " + player.getCurrentHealth() + "/" + player.getMaxHealth() + " health remaining\n");
								//Monster attacks and deals damage to player
								
								if(player.getCurrentHealth() <= 0)
								{
									System.out.println("Game over \nYou have died");
									break;
									//Ends the game if the player's health drops to or below 0
								}
							}
							
						}
						
						if(action == 2)//Use potion
						{
							if(potion > 0)//Player must have a potion to use it
							{
								if(player.getCurrentHealth() == player.getMaxHealth())
								{
									System.out.println("You are already full health");
								}
								else
								{
								player.setCurrentHealth(player.getCurrentHealth() + 50); //Heals the player for 50 health
								if(player.getCurrentHealth() > player.getMaxHealth())
								{
									player.setCurrentHealth(player.getCurrentHealth()-(player.getCurrentHealth()-player.getMaxHealth()));
									//Checks to see if the potion heals the player for more than their maximum health, if so, changes it to maximum amount
								}
								System.out.println("You now have " + player.getCurrentHealth() + "/" + player.getMaxHealth() + " health now");
								
								potion--;
								//Consumes potion (removes 1)
								
		
								//Monster will attack player after using potion
							
								damageDealtPlayer = (int)monster[monsterNumber].calculateDmgDealt();
								System.out.println("The " + monster[monsterNumber].getName() +" inflicted " + damageDealtPlayer + " damage to you");
								player.setCurrentHealth(player.getCurrentHealth() - damageDealtPlayer);
								System.out.println("You have " + player.getCurrentHealth() + "/" + player.getMaxHealth() + " health remaining\n");
								//Monster attacks and deals damage to player
							
								if(player.getCurrentHealth() <= 0)
								{
									System.out.println("Game over \nYou have died");
									break;
									//Ends the game if the player's health drops to or below 0
								}
								}
							}
							else
							{
								System.out.println("You do not have any potions! \n");
							}
						}
						
						if(action == 3)//Player attempts to flee
						{
							flee = rnd.nextInt(11); //Randomizes a number to determine if the player successfully flees
							if(flee < 5) //If the randomized number is less than 6, the player successfully flees, else flees and takes damage
							{
								System.out.println("You successfully fled away from the monster!");
								whatDo = 0;
							}
							else
							{
								System.out.println("You failed to flee away from the monster");
								
								//Deals damage to the player if they fail to flee
							
								damageDealtPlayer = (int)monster[monsterNumber].calculateDmgDealt();
								System.out.println("The " + monster[monsterNumber].getName() +" inflicted " + damageDealtPlayer + " damage to you");
								player.setCurrentHealth(player.getCurrentHealth() - damageDealtPlayer);
								System.out.println("You have " + player.getCurrentHealth() + "/" + player.getMaxHealth() + " health remaining\n");
								//Monster attacks and deals damage to player
							
								if(player.getCurrentHealth() <= 0)
								{
									System.out.println("Game over \nYou have died");
									break;
									//Ends the game if the player's health drops to or below 0
								}
								
							}
						}
				}whatDo = 0;
				//Resets whatDo back to 0, returns player to menu (asks player if they want to fight more monsters
				

			}//Game start
	}

}
