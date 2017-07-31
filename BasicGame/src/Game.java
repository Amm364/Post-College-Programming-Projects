import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Game {

	public static void main(String[] args) throws InterruptedException {
		File monsterFile = new File(args[0]);
		Scanner in = null;
		Map map;
		try {
			in = new Scanner(new FileReader(monsterFile));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the Monsters File");
			System.exit(0);
		}
		ArrayList<Monster> monsterList = GetMonsterStats(in);
		map = new Map(new File(args[1]),monsterList);
		Player player = new Player("Alex",100,20,10,15,map.xMax-2,map.yMax-2);
		Monster monster = monsterList.get(0);
		Coordinate end = new Coordinate(1,5);
		PlayGame(player,end,map);
		
	}
	

	private static void PlayGame(Player player, Coordinate end, Map map) throws InterruptedException {
		Random rand = new Random();
		Scanner in = new Scanner(System.in);
		while (player.getLocation()!=end && !player.isDead()){
			Coordinate currentRoom = new Coordinate(player.getLocation().xCord,player.getLocation().yCord);
			if (map.map[player.location.xCord][player.location.yCord].isMonster()){
				if (rand.nextInt(player.stealth) < map.map[player.location.xCord][player.location.yCord].monster.defence){
					while (true){
						player = AttackSequence(player,map.map[player.location.xCord][player.location.yCord].monster);
						break;
					}
				}
			}
			map.getChoices(player);
			while (player.getLocation().xCord==currentRoom.xCord && player.getLocation().yCord==currentRoom.yCord){
				System.out.print(">");
				String answer = in.nextLine();
				System.out.println();
				player = ParseAnwser(answer,player);
			}
		}
	}


	private static Player ParseAnwser(String answer, Player player) {
		String[] words = answer.split(" ");
		if (words[0].toLowerCase().equals("go") || words[0].toLowerCase().equals("move") || words[0].toLowerCase().equals("search") || words[0].toLowerCase().equals("open")){
			if (words[1].toLowerCase().equals("north")){
				player.changeLocation(player.getLocation().xCord,player.getLocation().yCord-1);
			}
			else if (words[1].toLowerCase().equals("east")){
				player.changeLocation(player.getLocation().xCord+1,player.getLocation().yCord);
			}
			else if (words[1].toLowerCase().equals("west")){
				player.changeLocation(player.getLocation().xCord-1,player.getLocation().yCord);
			}
			else if (words[1].toLowerCase().equals("south")){
				player.changeLocation(player.getLocation().xCord,player.getLocation().yCord+1);
			}
			else if (words[1].toLowerCase().equals("chest")){
				//player.openChest();
			}
		}
		return player;
	}


	public static ArrayList<Monster> GetMonsterStats(Scanner in){
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		while(in.hasNext()){
			String info = in.nextLine();
			String[] tokens = info.split(",");
			monsterList.add(new Monster(tokens[0],tokens[1],Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5])));
		}
		return monsterList;
	}
	
	public static Player AttackSequence(Player player, Monster monster) throws InterruptedException{
		System.out.println("Attack sequence has begun.");
		while (true){
			System.out.println("Player is attacking....");
			player.attack(monster);
			if (monster.isDead()){
				System.out.println("The " + monster.getName() + " was killed.");
				break;
			}
			Thread.sleep(3000);
			System.out.println("Monster is attacking....");
			monster.attack(player);
			if (player.isDead()){
				System.out.println(player.getName() + " was killed. Game Over");
				System.exit(0);
			}
			Thread.sleep(3000);
		}
		return player;
	}
}
