public class GameData 
{
	private String name;
	
	private double attkdmg;
	private double damage;
	
	private int maxHealth;
	private int health;
	private int healthPoints;
	private int attkdmgPoints;
	
	private int maxExp;

	
	public GameData(String nm, int attk)
	{
		name = nm;
		attkdmg = attk;
	}
	
		public GameData()
	{
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//Name of a monster
	
	public int getHealthPoints() {
		return healthPoints;
	}
	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
	//Number of points assigned to health by the player (initially 0)
	//Increases the maximum health of the player
	
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = 100+10*healthPoints;
	}
	//Maximum health of the player
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
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
	
	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = (double)Math.round((attkdmg/2)+(Math.random()*((attkdmg-attkdmg/2)+1)*1))/1;
	}
	//Calculates for the damage dealt and the rounds it
}
