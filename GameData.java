public class GameData 
{
	private String name;
	
	private double attkdmg;
	private double damage;
	
	private int maxHealth;
	private int health;

	private int attkdmgPoints;
	

	
	public GameData(String nm, int attk, int maxHealth)
	{
		this.name = nm;
		this.attkdmg = attk;
		this.maxHealth = maxHealth;
		this.health = maxHealth;

	}
	

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//Name of a character
	
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	
	public int getCurrentHealth() {
		return health;
	}
	public void setCurrentHealth(int health) {
		
		if(health > this.maxHealth) {
			this.health = this.maxHealth;
		}
		else if(health < 0) {
			this.health = 0;
		}
		else {
			this.health = health;
		}
		
	}
	//Actual health of the player
	
	public int getAttkdmgPoints() {
		return attkdmgPoints;
	}
	public void setAttkdmgPoints(int attkdmgPoints) {
		this.attkdmgPoints = attkdmgPoints;
	}
	//Points placed into attack damage by player (initially 0)
	//Increases the players damage as more points are placed
	
	public double getAttkdmg() {
		return attkdmg;
	}
	public void setAttkdmg(double attkdmg) {
		this.attkdmg = attkdmg + attkdmgPoints;
	}
	//The total attack damage
	//More attack damage = more damage
	
	public double calculateDmgDealt() {
		return ((double)Math.round((attkdmg/2)+(Math.random()*((attkdmg-attkdmg/2)+1)*1))/1);
	}
	//Calculates for the damage dealt and the rounds it
}
