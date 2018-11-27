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
//yuzde elli iyi yuzde elli kotu 
//iyi ise yuzden 50 item pickUpItem(String itemName)



//yuzde 40 para 
//yuzde 10 xp

//kotu ise
//canvar kesin yuzde 30 skeleton yuzden 5 troll yuzde 10 wolf vs.  
	if(sans<6){
	sans2 = rand.nextInt(10) + 1;
     QueueProvider.offer("\n Something good is about to be happen Lucky You ");

	if(sans2==1||sans2==7){
int sans3= rand.nextInt(10) + 1;	
	if(sans3==10){
     QueueProvider.offer("\nYour lucky box provides you the highest XP you will get from lucky box which is --------------> 1000 XP");

/*public void setXP(int xp) {
        this.xp = xp;
    }	
*/
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

	/////////XP kazanacak 

	}else if(sans2==2||sans2==3||sans2==4||sans2==5||sans2==6){
int sanslipara=rand.nextInt(10) + 1;
	//////////Para kazanacak	
/*
public void setGold(int gold) {
        this.gold = gold;
    }

*/
if(sanslipara==10){
	     QueueProvider.offer("\nYour lucky box provides you more gold than you will ever gonna need which is --------------> 5000 Gold\nSpend it in an useful way :) ");
//if(entity.getGold()!=0)
//entity.setGold(entity.getGold()+5000);
player.setGold(player.getGold()+5000);
}
else if(sanslipara==9||sanslipara==8||sanslipara==7){
	     QueueProvider.offer("\nYour lucky box provides you some gold which is --------------> 2000 Gold");
//if(entity.getGold()!=0)
//entity.setGold(entity.getGold()+2000);
player.setGold(player.getGold()+2000);
}else{

	     QueueProvider.offer("\nYour lucky box provides you small amount of gold which is --------------> 500 Gold");
//if(entity.getGold()!=0)
//entity.setGold(entity.getGold()+500);
player.setGold(player.getGold()+500);
}
	}else{

             QueueProvider.offer("\nYour lucky box provides you an item that will be randomly appointed to you ------------->");

	/////////item cikacak
///                    addItemToStorage(item);
//monster.addRandomItems(2, String... children);
//player.addItemToStorage(itemRepo.getItem(item.getAsString()));
/*	JsonArray items = json.get("items").getAsJsonArray();
            for (JsonElement item : items) {
                player.addItemToStorage(itemRepo.getItem(item.getAsString()));
            }
*/

//player.addItemToStorage(milk);
//	 Storage st=new Storage(5);

//	player.addItemToStorage(itemRepo.getRandomItem(milk,2));
	}

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
}


	}


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
