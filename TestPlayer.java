import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class TestPlayer {
	
	private Player player;
	
	@Before
	public void initializePlayer() {
		
		player = new Player("test", 1, 100);
		
	}

	//Tests for proper values (vitalityPoints, maxHealth when vitality points 
	//are incremented and max health
	@Test
	public void testVitality() {
		
		player.incVitality();
		
		//Checks that player vitality has increased
		assertEquals(2, player.getVitality());
		
		//Checks that max health has increased
		assertEquals(120, player.getMaxHealth());
		
	}
	
	//Tests for proper values when using potions
	@Test
	public void testPotion() {
		
		//Checks that current health has been changed by set
		player.setCurrentHealth(40);
		assertEquals(40, player.getCurrentHealth());
		
		//Checks that potion healed player for 50 (40+50 = 90)
		player.usePotion();
		assertEquals(90, player.getCurrentHealth());
		
		//Checks that a potion has been used (decremented from initial 5)
		assertEquals(4, player.getNumPotion());
		
		
	}
	
	//Tests for current health resetting when leveling up
	@Test
	public void testLevelUp() {
		
		//Checks that health gets reset to max upon level up
		player.setCurrentHealth(50);
		player.addExp(100);
		assertEquals(100, player.getCurrentHealth());
		
	}

}
