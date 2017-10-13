
public class Player extends GameData {

	private int maxExp;
	private int numPotions;
	private int vitalityPoints;
	
	public Player(String nm, int attk, int maxHP) {
		
		super(nm, attk, maxHP);
		this.vitalityPoints = 0;
		this.numPotions = 5;
		
	}
	
	
	public int getVitality() {
		return vitalityPoints;
	}
	public void incVitality() {
		this.vitalityPoints = vitalityPoints + 2;
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
	
}
