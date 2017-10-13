import java.util.Scanner;

public class Player extends GameData {

	private int maxExp;
	private int currentExp;
	private int level;
	
	private int numPotions;
	private int vitalityPoints;
	
	private Scanner input = new Scanner(System.in);
	
	public Player(String nm, int attk, int maxHP) {
		
		super(nm, attk, maxHP);
		this.vitalityPoints = 0;
		this.numPotions = 5;
		this.maxExp = 100;
		this.currentExp = 0;
		this.level = 1;
	}
	
	
	public int getVitality() {
		return vitalityPoints;
	}
	public void incVitality() {
		this.vitalityPoints += 2;
		this.updateMaxHealth();
	}
	//Number of points assigned to health by the player (initially 0)
	//Increases the maximum health of the player
	
	
	public void updateMaxHealth() {
		super.setMaxHealth(100+10*vitalityPoints);
	}
	//Increase max health
	
	public void usePotion() {
		
		if(numPotions > 0)//Player must have a potion to use it
		{
			if(super.getCurrentHealth() == super.getMaxHealth()) 
			{
				System.out.println("You are already full health");
			}
			else
			{
				super.setCurrentHealth(super.getCurrentHealth() + 50); //Heals the player for 50 health
				System.out.println("You now have " + super.getCurrentHealth() + "/" + super.getMaxHealth() + " health");
	
				numPotions--; 
				//Consumes potion (removes 1)
	
			}
		}
		else {
			System.out.println("No potions remaining");
		}
	
	}
	
	//Adds experience gained to current experience and checks for levelups
	public void addExp(int expGained) {
		
		this.currentExp += expGained;
		
		System.out.println("\nYou have defeated the monster and gained " + expGained + " experience" );
		System.out.println("You now have " + currentExp + "/" + maxExp + " experience");
		
		if(currentExp >= maxExp) {
			levelup();
		}
		
	}
	
	public void levelup() {
		
		this.level++;
		this.currentExp = 0;
		this.maxExp = 100+level*20;
		//Increases the level of the player and carries over any experience over the required experience for the next level
		//Required experience for next level is also calculated
		
		
		System.out.println("Congratulations! You have leveled up! You are now level " + level +" You may put 1 point into your attack damage or max health");
		System.out.println("1. Attack Damage");
		System.out.println("2. Max Health");
		int levelPoint = input.nextInt();
		//Allows the user to allocate 1 point to attack damage or health if they level up
		
		if(levelPoint == 1)
		{
			super.setAttkdmgPoints(super.getAttkdmgPoints() + 2);
			//Allocates 2 points to attack damage

			System.out.println("You now have " + super.getAttkdmgPoints() + " attack damage points");
		
			super.setCurrentHealth(super.getMaxHealth());
			//Restores player's health back to maximum
		}
		else if(levelPoint == 2)
		{
			incVitality();
			//Allocates 2 points to health (10 per point=20+ to maximum health)
			
			System.out.println("You now have " + getVitality() + " health points (max health: " + super.getMaxHealth() + ")");
			//Allocates a point to health
			
			super.setCurrentHealth(super.getMaxHealth());
			//Restores player's health back to maximum
		}
		
	}
	
	public void incPotion() {
		
		this.numPotions++;
		
	}
	
	public int getNumPotion() {
		
		return numPotions;
				
	}
	
}
