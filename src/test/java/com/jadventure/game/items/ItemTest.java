package com.jadventure.game.items;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class ItemTest {

	@Test
	public void createTest() {
		Item fkit = createFkit();
		Item wgun = createWgun();  /*Adding new weapon test */
		Item wsupergun = createWsupergun();

		assertEquals("wgun",wgun.getId());
		assertEquals("pistol",wgun.getName());
		assertEquals("a powerful weapon",wgun.getDescription());
		assertEquals(1,wgun.getLevel());
		assertEquals(Integer.valueOf(3),wgun.getWeight());

		assertEquals("wsupergun",wsupergun.getId());
		assertEquals("machinegun",wsupergun.getName());
		assertEquals("a most powerful weapon in a game",wsupergun.getDescription());
		assertEquals(1,wsupergun.getLevel());
		assertEquals(Integer.valueOf(3),wsupergun.getWeight());

		assertEquals("fkit", fkit.getId());
		assertEquals("first aid kit", fkit.getName());
		assertEquals("some powerful medicine", fkit.getDescription());
        assertEquals(0, fkit.getLevel());
		assertEquals(Integer.valueOf(1), fkit.getWeight());
	}
	
	@Test
	public void checkEqual() {
		Item fkit = createFkit();

		assertEquals(fkit, createFkit());
		assertTrue(fkit.equals(createFkit()));
	}

	@Test
	public void checkNotEqual() {
		Item fkit = createFkit();
		Item egg = createEgg();
		
		assertNotEquals(fkit, egg);
		assertFalse(fkit.equals(egg));
	}

    private Item createFkit() {
        Map<String, Integer> properties = new TreeMap<>();
        properties.put("health", Integer.valueOf(100));
        properties.put("weight", Integer.valueOf(1));
        properties.put("value", Integer.valueOf(30));
        
        Item item = new Item("fkit", "potion", "first aid kit", "some powerful medicine", 0, properties);
        return item;
	}
	
	private Item createWgun() {
        Map<String, Integer> properties = new TreeMap<>();
        properties.put("weight", Integer.valueOf(3));
        properties.put("value", Integer.valueOf(80));
        
        Item item = new Item("wgun", "weapon", "pistol", "a powerful weapon", 1, properties);
        return item;
	}

	private Item createWsupergun() {
        Map<String, Integer> properties = new TreeMap<>();
        properties.put("weight", Integer.valueOf(3));
        properties.put("value", Integer.valueOf(80));
        
        Item item = new Item("wsupergun", "weapon", "machinegun", "a most powerful weapon in a game", 1, properties);
        return item;
	}

    private Item createEgg() {
        Map<String, Integer> properties = new TreeMap<>();
        properties.put("health", Integer.valueOf(2));
        properties.put("weight", Integer.valueOf(1));
        properties.put("value", Integer.valueOf(3));
        
        Item item = new Item("fegg1", "food", "egg", "A nice egg", 1, properties);
        return item;
    }

}
