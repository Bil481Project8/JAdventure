package com.jadventure.game.monsters;

import com.jadventure.game.entities.Player;

import java.util.Random;

/**
 * The MonsterFactory generates random monsters appropriately according
 * to the player's level and location
 */

/*Created Orc => onurTe */
public class MonsterFactory {
    Random random = new Random();
   
    public Monster generateMonster(Player player) {
        int randomInt = random.nextInt(3) + 1;

        if(player.getLocation().getDangerRating()==0){
            return null;
        }else if (randomInt <= player.getLocation().getDangerRating()+2) {
            switch (player.getLocationType()) {
                case FOREST:
                    return getForestMonster(player.getLevel());
                case SWAMP:
                    return getSwampMonster(player.getLevel());
                case MOUNTAIN:
                    return getMountainMonster(player.getLevel());   
                case CAVE:
                    return getCaveMonster(player.getLevel());
                case PLAINS:
                    return getPlainsMonster(player.getLevel());
                default: // any non-hostile location
                    return null;
            }
        } else {
            return null;
        }
    }
    
    private Monster getForestMonster(int playerLevel) {
        int randomInt = random.nextInt(4);
        if (randomInt == 0)
        	return new Bugbear(playerLevel);
        else if (randomInt == 1)
        	return new Troll(playerLevel);
        else
        	return new Orc(playerLevel); 
    }
    
    private Monster getSwampMonster(int playerLevel) {
    	int randomInt = random.nextInt(2);
        return (randomInt == 1) ? new Goblin(playerLevel) : new Orc(playerLevel);
    }
    
    private Monster getMountainMonster(int playerLevel) {
    	int randomInt = random.nextInt(5);
        if (randomInt == 0)
            return new Giant(playerLevel);
        else if (randomInt == 1)
            return new Troll(playerLevel);
        else if (randomInt == 2)
            return new Wolf(playerLevel);
        else if (randomInt ==3)
            return new Orc(playerLevel);
        else
            return new Skeleton(playerLevel);
    }
    
    private Monster getCaveMonster(int playerLevel) {
    	int randomInt = random.nextInt(4);
        if (randomInt == 0)
            return new Troll(playerLevel);
        else if (randomInt == 1)
            return new Goblin(playerLevel);
        else
            return new Orc(playerLevel);
    }

    private Monster getPlainsMonster(int playerLevel) {
    	int randomInt = random.nextInt(2);
    	return (randomInt == 1) ? new Bugbear(playerLevel) : new Orc(playerLevel);
    }
}
