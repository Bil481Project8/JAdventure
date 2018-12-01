package com.jadventure.game.menus;

import java.io.File;
import java.net.Socket;

import com.jadventure.game.DeathException;
import com.jadventure.game.Game;
import com.jadventure.game.GameModeType;
import com.jadventure.game.JAdventure;
import com.jadventure.game.QueueProvider;
import com.jadventure.game.entities.Player;

/**
 * The first menu displayed on user screen
 * @see JAdventure
 * This menu lets the player choose whether to load an exiting game,
 * start a new one, or exit to the terminal.
 */
public class MainMenu extends Menus implements Runnable {
     
    public MainMenu(Socket server, GameModeType mode){
        QueueProvider.startMessenger(mode, server);
    }

    public MainMenu() {
        start();
    }
    
    public void run() {
        start();
    }

    public void start() {
        this.menuItems.add(new MenuItem("Start", "Starts a new Game", "new"));
        this.menuItems.add(new MenuItem("Load", "Loads an existing Game"));
        this.menuItems.add(new MenuItem("Delete", "Deletes an existing Game"));
        this.menuItems.add(new MenuItem("Exit", null, "quit"));
        this.menuItems.add(new MenuItem("PoisonMap", "activate randomly Poison Damage"));

        
        while(true) {
            try {
                MenuItem selectedItem = displayMenu(this.menuItems);
                boolean exit = testOption(selectedItem);
                if (!exit) {
                    break;
                }
            } catch (DeathException e) {
                if (e.getLocalisedMessage().equals("close")) {
                    break;
                }
            }
        }
        QueueProvider.offer("EXIT");
    
    }

    private static boolean testOption(MenuItem m) throws DeathException {
        String key = m.getKey();
        switch (key){
            case "start":
                new ChooseClassMenu();
                break;
            case "PoisonMap":
            QueueProvider.offer("Posinoed Map activaded");
                
                break;    
            case "exit":
                QueueProvider.offer("Goodbye!");
                return false;
            case "load":
                listProfiles();
                Player player = null;
                boolean exit = false;
                while (player == null) {
                    key = QueueProvider.take();
                    if (Player.profileExists(key)) {
                        player = Player.load(key);
                    } else if (key.equals("exit") || key.equals("back")) {
                        exit = true;
                        break;
                    } else {
                        QueueProvider.offer("That user doesn't exist. Try again.");
                    }
                }
                if (exit) {
                    return true;
                }
                new Game(player, "old");
                break;
            case "delete":
                listProfiles();
                exit = false;
                while (!exit) {
                    key = QueueProvider.take();
                    if (Player.profileExists(key)) {
                        String profileName = key;
                        QueueProvider.offer("Are you sure you want to delete " + profileName + "? y/n");
                        key = QueueProvider.take();
                        if (key.equals("y")) {
                            File profile = new File("json/profiles/" + profileName);
                            deleteDirectory(profile);
                            QueueProvider.offer("Profile Deleted");
                            return true;
                        } else {
                            listProfiles();
                            QueueProvider.offer("\nWhich profile do you want to delete?");
                        }
                    } else if (key.equals("exit") || key.equals("back")) {
                        exit = true;
                        break;
                    } else {
                        QueueProvider.offer("That user doesn't exist. Try again.");
                    }
                }
                break;
        }
        return true;
    }

    private static boolean deleteDirectory(File directory) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        return(directory.delete());
    }

    private static void listProfiles() {
        QueueProvider.offer("Profiles:");
        try {
                File file = new File("json/profiles");
                String[] profiles = file.list();
        
            int i = 1;
            for (String name : profiles) {
                if (new File("json/profiles/" + name).isDirectory()) {
                    QueueProvider.offer("  " + name);
                }
                    i += 1;
            }
            QueueProvider.offer("\nWhat is the name of the avatar you want to select? Type 'back' to go back");
        } catch (NullPointerException e) {
            QueueProvider.offer("No profiles found. Type \"back\" to go back.");
        }
    }
}
