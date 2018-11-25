package com.jadventure.game.monsters;

import com.jadventure.game.items.ItemStack;

public class Orc extends Monster{

    public Orc(int playerLevel){
		
        this.monsterType = "Orc";
        this.setHealthMax(75 + playerLevel * 8);
        this.setHealth(70 + playerLevel * 5);
        this.setArmour(playerLevel+8);
        this.setDamage(30 + playerLevel * 2);
        this.setIntelligence(1);
        this.setStealth(1);
        this.setDexterity(2);
        this.setCritChance(0.05);
        this.setXPGain(200 + playerLevel * 3);
        this.setGold(playerLevel * 11);
            addRandomItems(playerLevel, "wsupergun"); 
    }
}