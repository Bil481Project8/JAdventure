package com.jadventure.game;

import com.jadventure.game.entities.Player;
import com.jadventure.game.monsters.Monster;
import com.jadventure.game.monsters.MonsterFactory;
import com.jadventure.game.repository.LocationRepository;
import com.jadventure.game.prompts.CommandParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.jadventure.game.GameBeans;
import com.jadventure.game.entities.Entity;

import com.jadventure.game.menus.BattleMenu;
import com.jadventure.game.navigation.Coordinate;
import com.jadventure.game.navigation.ILocation;
import com.jadventure.game.navigation.LocationType;
import com.jadventure.game.repository.ItemRepository;
import com.jadventure.game.items.Item;
import com.jadventure.game.items.ItemStack;
import com.jadventure.game.items.Storage;
import com.google.gson.JsonParser;
import com.jadventure.game.monsters.*;
import com.jadventure.game.entities.*;
import java.util.Map;
import java.io.Reader;
import java.io.FileReader;
import java.util.List;
import java.util.HashMap;


import java.util.ArrayList;
import java.util.Random;
/**
 * This class contains the main loop that takes the input and
 * does the according actions.
 */
public class Game {
//    protected static ItemRepository itemRepo = GameBeans.getItemRepository();
    public ArrayList<Monster> monsterList = new ArrayList<Monster>();
    public MonsterFactory monsterFactory = new MonsterFactory(); 
    public CommandParser parser;
    public Monster monster;
    public int temp=0;//toygar
    Player player = null;
//	Entity entity=null;
    public Game(Player player, String playerType) throws DeathException {
          this.parser = new CommandParser(player);
          this.player = player;
          switch (playerType) {
              case "new": 
                  newGameStart(player);
                  break;
              case "old":
                  QueueProvider.offer("Welcome back, " + player.getName() + "!");
                  QueueProvider.offer("");
                  player.getLocation().print();
                  gamePrompt(player);
                  break;
              default:
                  QueueProvider.offer("Invalid player type");
                  break;
          }
    }
   
    /**
     * Starts a new game.
     * It prints the introduction text first and asks for the name of the player's
     * character and welcomes him / her. After that, it goes to the normal game prompt.
     */
    public void newGameStart(Player player) throws DeathException {
        QueueProvider.offer(player.getIntro());
        String userInput = QueueProvider.take();
        player.setName(userInput);
        LocationRepository locationRepo = GameBeans.getLocationRepository(player.getName());
        this.player.setLocation(locationRepo.getInitialLocation());
        player.save();
        QueueProvider.offer("Welcome to Silliya, " + player.getName() + ".");
        player.getLocation().print();
        gamePrompt(player);
    }

    /**
     * This is the main loop for the player-game interaction. It gets input from the
     * command line and checks if it is a recognised command.
     *
     * This keeps looping as long as the player didn't type an exit command.
     */
    public void gamePrompt(Player player) throws DeathException {
        boolean continuePrompt = true;
 
Random rand = new Random();

int lcount=0;
int sans=0;
int sans2=0;
int xp=0;
String milk="milk";
//Item ititem=new Item();
         JsonObject json = new JsonObject();


     if(lcount==0){
	QueueProvider.offer("\nYour lucky box is ready!!!!!:)\nDo you want to open it [1] or you want to skip it and continue your journey [2] :");
		String opsiyon=QueueProvider.take();
//player.setDamage(player.getDamage()+150);
	sans = rand.nextInt(10) + 1;
		if(opsiyon.equals("1")){
	if(sans<6){
	sans2 = rand.nextInt(10) + 1;
     QueueProvider.offer("\n Something good is about to be happen Lucky You ");

	if(sans2==1||sans2==7){
int sans3= rand.nextInt(10) + 1;	
	if(sans3==10){
     QueueProvider.offer("\nYour lucky box provides you the highest XP you will get from lucky box which is --------------> 1000 XP");

player.setXP(player.getXP()+1000);
 int oldLevel = player.getLevel();
            int newLevel = (int) (0.075 * Math.sqrt(player.getXP()) + 1);
	    if(oldLevel<newLevel){
            player.setLevel(newLevel);
	     QueueProvider.offer("\nLucky You \nYou just "+(newLevel-oldLevel)+" leveled up\nKeep it on "+player.getName());
QueueProvider.offer("\nYour new level is ------>"+newLevel+"\n");
		}

	}
	else if(sans3==9||sans3==8){
	     QueueProvider.offer("\nYour lucky box provides you the second highest XP you will get from lucky box which is --------------> 750 XP");

player.setXP(player.getXP()+750);
 int oldLevel = player.getLevel();
            int newLevel = (int) (0.075 * Math.sqrt(player.getXP()) + 1);
	    if(oldLevel<newLevel){
            player.setLevel(newLevel);
	     QueueProvider.offer("\nLucky You \nYou just "+(newLevel-oldLevel)+" leveled up\nKeep it on "+player.getName());
QueueProvider.offer("\nYour new level is ------>"+newLevel+"\n");
		}
	}else
		{

	     QueueProvider.offer("\nYour lucky box provides you some XP you will get from lucky box which is --------------> 300 XP");
player.setXP(player.getXP()+300);
	    int oldLevel = player.getLevel();
            int newLevel = (int) (0.075 * Math.sqrt(player.getXP()) + 1);
	    if(oldLevel<newLevel){
            player.setLevel(newLevel);
	     QueueProvider.offer("\nLucky You \nYou just "+(newLevel-oldLevel)+" leveled up\nKeep it on "+player.getName());
		QueueProvider.offer("\nYour new level is ------>"+newLevel+"\n");
		}		


	}


	}else if(sans2==2||sans2==3||sans2==4||sans2==5||sans2==6){
int sanslipara=rand.nextInt(10) + 1;
if(sanslipara==10){
	     QueueProvider.offer("\nYour lucky box provides you more gold than you will ever gonna need which is --------------> 5000 Gold\nSpend it in an useful way :) ");
player.setGold(player.getGold()+5000);
}
else if(sanslipara==9||sanslipara==8||sanslipara==7){
	     QueueProvider.offer("\nYour lucky box provides you some gold which is --------------> 2000 Gold");
player.setGold(player.getGold()+2000);
}else{

	     QueueProvider.offer("\nYour lucky box provides you small amount of gold which is --------------> 500 Gold");
player.setGold(player.getGold()+500);
}
	}else{

             QueueProvider.offer("\nYour lucky box provides you an item that will be randomly appointed to you ------------->");


int lucy=rand.nextInt(10) + 1;
if(lucy==2||lucy==1){
Map<String, Integer> properties =new HashMap<String, Integer>();
properties.put("health", 7);
properties.put("weight",2);
properties.put("value", 3);
	Item luit=new Item("fram1","food","raw meat","some delicious raw meat",1,properties);
     QueueProvider.offer("raw meat\n"+player.getName()+" ,if you are gonna look at it enter this command [vb] if you are gonna use it then enter [ve]\n");
	player.addItemToStorage(luit);
}else if(lucy==3||lucy==4){
Map<String, Integer> properties2 =new HashMap<String, Integer>();
properties2.put("healthMax",0);
properties2.put("health", 25);
properties2.put("weight",1);
properties2.put("value",20);
	Item luit2=new Item("pmil2","potion","super milk","this milk must have come from a super cow because it is extra delicious milk",2,properties2);
     QueueProvider.offer("super milk\n"+player.getName()+", if you are gonna look at it enter this command [vb] if you are gonna use it then enter [ve]\n");
	player.addItemToStorage(luit2);


}
else if(lucy==6||lucy==7){
Map<String, Integer> properties3 =new HashMap<String, Integer>();
properties3.put("damage",50);
properties3.put("weight",3);
properties3.put("value",0);
	Item luit3=new Item("katana","weapon","Katana","A long sword once used by a legendary Japanese samurai",3,properties3);
     QueueProvider.offer("Katana\n"+player.getName()+", if you are gonna look at it enter this command [vb] if you are gonna use it then enter [ve]\n");
	player.addItemToStorage(luit3);


}
else if(lucy==5||lucy==8){
Map<String, Integer> properties5 =new HashMap<String, Integer>();
properties5.put("damage",5);
properties5.put("weight",4);
properties5.put("value",50);
	Item luit5=new Item("wspe1","weapon","spear","a basic spear",1,properties5);
     QueueProvider.offer("spear\n"+player.getName()+", if you are gonna look at it enter this command [vb] if you are gonna use it then enter [ve]\n");
	player.addItemToStorage(luit5);


}
else{
Map<String, Integer> properties4 =new HashMap<String, Integer>();
properties4.put("armour", 4);
properties4.put("weight",3);
properties4.put("value",40);
	Item luit4=new Item("arhl1","armour","rusted helmet","a rusted helmet, it will do for now",1,properties4);
     QueueProvider.offer("rusted helmet\n"+player.getName()+", if you are gonna look at it enter this command [vb] if you are gonna use it then enter [ve]\n");
	player.addItemToStorage(luit4);




}


	}//end of item thing

	}else{
		//////kotu bir sey 
     QueueProvider.offer("\n Ooops!!!! I kinda feel that Terrible thing is about to come\nPoor You '''''--'''''\nBe Careful !!\n ");

int optik=rand.nextInt(10) + 1;
if(optik==1||optik==4){
    QueueProvider.offer("\nOh my god back off "+player.getName()+"\n' ' ' ' '\n ' ' ' '\n  ' ' '\n   ' '\n    '\nLooks like acid rain has started and it is absorbing all your strength\nApparently your damage started to get low \n Watch your back my fellow");
player.setDamage(player.getDamage()-15);
}else if(optik==2||optik==3){
    QueueProvider.offer("\nWhat a coincidence my friend "+player.getName()+ " :/ Looks like some poisoned air blew to your face and wounded you\nYour health is decreased\tBe careful next time ");
player.setHealth(player.getHealth()-20);
}else{
QueueProvider.offer("\nSssshhh !!!!!!\nWe are not alone "+player.getName()+"\n'' - ''\nPrepare for battle!!!!!\n");
        List<Monster> monsters = player.getLocation().getMonsters();
	    Monster monsterOpponent=null;

		monsters.add(new Giant(player.getLevel()));		   
		//easy/monsters.add(new Goblin(player.getLevel()));		   
		//easy/monsters.add(new Bugbear(player.getLevel()));		   
		//easy/monsters.add(new Orc(player.getLevel()));		   
		//easy/monsters.add(new Skeleton(player.getLevel()));		   
		monsters.add(new Troll(player.getLevel()));		   
		//easy/monsters.add(new Wolf(player.getLevel()));		   
	    int randomMonster=rand.nextInt(monsters.size()-1);
	 monsterOpponent=monsters.get(randomMonster);
            monsterOpponent.setName(monsterOpponent.monsterType);
	
	      QueueProvider.offer(monsterOpponent.getName()+" is attacking us bro! What are we gonna do "+player.getName());
            new BattleMenu(monsterOpponent, player);

}//end of monster meeting



	}//end of the bad thing


		}else if(opsiyon.equals("2")){
	QueueProvider.offer("\nHope good luck be with you wherever you go "+player.getName());
		}
		else{
		QueueProvider.offer("\nYour lucky box does not exist anymore\n You ruined your chance without clicking neither 1 nor 2 \nBut do not worry it will not be your only chance \nYour luck is about to change again wait and see...\n");

		}                
		}//lcount==0
		


       try {
            while (continuePrompt) {
////////////////////////Kutu sorusu burada
                if(player.borcAldiMi==1){			//sadece borc alindiginda devreye girecek.
			
			player.promptCheck++;	//bizim icin zaman dilimi her bir prompt girişidir.
			temp=10-player.promptCheck;	//10 prompt olarak sectim zamanı,10 prompt sonra tahsil edilecek.
			if(temp!=0)
				System.out.println("\n(You should pay your debt after : "+ temp +" steps.)");
			else if(temp==0){
				int miktar=0;
				
				if((player.borcTipi).equals("1")){	//1. borc tipi odenmemesi halinde 20 can azaltir.
					miktar=player.getGold()-50;
					if(miktar<0){
						if(!(player.getHealth()-20 <= 0))
							player.setHealth(player.getHealth()-20);
						else
							player.setHealth(1);
						System.out.println("\n____You did not pay your debt.Your health decreased to "+ player.getHealth()+"_____");				
					}
					else{
						player.setGold(miktar);
						System.out.println("\n____Thanks for paying back your debt! See you later._____");		
					}
					
				}
				else if((player.borcTipi).equals("2")){	//2. borc tipi odenmemesi halinde 10 hasar azaltir.
					miktar=player.getGold()-50;
					if(miktar<0){
						if(!(player.getDamage()-10 <= 0))
							player.setDamage(player.getDamage()-10);
						else
							player.setDamage(1);
						System.out.println("\n____You did not pay your debt.Your damage decreased to "+ player.getDamage()+"____");		
					}
					else{
						player.setGold(miktar);
						System.out.println("\n____Thanks for paying back your debt! See you later._____");				
					}
					
				}
				else if((player.borcTipi).equals("3")){	//3. borc tipi odenmemesi halinde armor,intelligence ve dexterityi 1 azaltir.
					miktar=player.getGold()-50;
					if(miktar<0){
						if(!(player.getArmour()-1 < 0))
							player.setArmour(player.getArmour()-1);
						else
							player.setArmour(0);
						if(!(player.getDexterity()-1 < 0))
							player.setDexterity(player.getDexterity()-1);
						else
							player.setDexterity(0);
						if(!(player.getIntelligence()-1 < 0))
							player.setIntelligence(player.getIntelligence()-1);
						else
							player.setIntelligence(0);


						System.out.println("\n____You did not pay your debt.Your armour decreased to "+ player.getArmour()+"____");				
						System.out.println("\n____You did not pay your debt.Your intelligence decreased to "+ player.getIntelligence()+"____");				
						System.out.println("\n____You did not pay your debt.Your dexterity decreased to "+ player.getDexterity()+"____");				
					}
					else{
						player.setGold(miktar);
						System.out.println("\n____Thanks for paying back your debt! See you later._____");		
								
					}
				}
				player.borcAldiMi=0;
				player.promptCheck=-1;
			}		
		}      
		QueueProvider.offer("\nPrompt:");
                String command = QueueProvider.take().toLowerCase();
                continuePrompt = parser.parse(player, command);
            }
        } catch (DeathException e) {
            if (e.getLocalisedMessage().equals("replay")) {
                return;
            } else {
                throw e;
            }
        }
    }
}
