
public class Unit {
	double chanceOfInfection;
	boolean isInfected;
	boolean isDead;
	boolean isImmune;
	int xCord;
	int yCord;
	public Unit(int x, int y){
		isInfected = false;
		isDead = false;
		isImmune = false;
		chanceOfInfection = 0;
		xCord = x;
		yCord = y;
	}
	
	public void Infect(Unit[][] grid, double modifier){
		try{
			isInfected = true;
			if (grid[xCord+1][yCord].isInfected == false) grid[xCord+1][yCord].ModifyChanceOfInfection(modifier);
			if (grid[xCord+1][yCord+1].isInfected == false) grid[xCord+1][yCord+1].ModifyChanceOfInfection(modifier);
			if (grid[xCord][yCord+1].isInfected == false) grid[xCord][yCord+1].ModifyChanceOfInfection(modifier);
			if (grid[xCord-1][yCord+1].isInfected == false) grid[xCord-1][yCord+1].ModifyChanceOfInfection(modifier);
			if (grid[xCord-1][yCord].isInfected == false) grid[xCord-1][yCord].ModifyChanceOfInfection(modifier);
			if (grid[xCord-1][yCord-1].isInfected == false) grid[xCord-1][yCord-1].ModifyChanceOfInfection(modifier);
			if (grid[xCord][yCord-1].isInfected == false) grid[xCord][yCord-1].ModifyChanceOfInfection(modifier);
			if (grid[xCord+1][yCord-1].isInfected == false) grid[xCord+1][yCord-1].ModifyChanceOfInfection(modifier);
		} catch(IndexOutOfBoundsException e){
			
		}
	}
	
	public void MakeImmune(){
		isImmune = true;
	}
	
	public void Death(){
		isDead = true;
	}
	
	public void ModifyChanceOfInfection(double modifier){
		chanceOfInfection += modifier;
	}
}
