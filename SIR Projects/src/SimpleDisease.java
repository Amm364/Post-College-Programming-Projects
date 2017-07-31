/*
 * SIR Model Program. Simulates the spread of a disease through a population. Each unit in the
 * population is either susceptible, infected, recovered, or dead.
 * 6 arguments needed. (1)Population size, (2)initial infected, (3)Rate Of Susceptible to Infected, 
 * (4) Rate Of Infected To Recovered, (5) Rate Of Susceptible to Recovered, (6) Rate Of Infected To Death
 */

import java.util.Random;

public class SimpleDisease {
	public static void main(String args[]){
		// Initialize Rates and other values
		int population = Integer.parseInt(args[0]);
		int infected = Integer.parseInt(args[1]);
		double baseRateSI = Double.parseDouble(args[2]);	// Base SI Rate
		double rateSI = baseRateSI;							// Susceptible to Infected
		double rateIR = Double.parseDouble(args[3]);		// Infected to Recovered
		double rateSR = Double.parseDouble(args[4]);		// Susceptible to Recovered
		double rateDeath = Double.parseDouble(args[5]);		// Infected to Dead
		Random rand = new Random();
		int susceptible = population - infected;
		int recovered = 0;
		int dead = 0;
		int numberOfDays = 0;
		int numberOfNewInfected = 0;
		int[] numberOfRecoveries = {0,0};
		int numberOfDeaths = 0;
		while(true){
			numberOfDays++;
			if(numberOfDays % 500 == 0){		//Check progress every 500 days. (Can be changed)
				System.out.println("-----------------");
				System.out.println("Day: " + numberOfDays + "\nSusceptible: " + susceptible + "\nInfected: " + infected +
						"\nRecovered: " + recovered + "\nDead: " + dead);
				System.out.println("-----------------\n\n");
			}
			numberOfRecoveries = CheckForRecoveries(susceptible,infected,rateSR,rateIR,rand);
			susceptible -= numberOfRecoveries[0];
			infected -= numberOfRecoveries[1];
			recovered += numberOfRecoveries[0] + numberOfRecoveries[1];
			numberOfNewInfected = CheckForNewInfected(susceptible,rateSI,rand);
			susceptible -= numberOfNewInfected;
			infected += numberOfNewInfected;
			numberOfDeaths = CheckForDeaths(infected,rateDeath,rand);
			infected -= numberOfDeaths;
			dead += numberOfDeaths;
			//rateSI = baseRateSI + ((double)infected/(double)population)/10;
			if(dead + recovered == population) break;
		}
		System.out.println("Alive: " + recovered);
		System.out.println("Dead: " + dead);
		System.out.println("Days: " + numberOfDays);
	}
	
	/*
	 * @param infected: Number of infected.
	 * @param rateDeath: Rate of Death.
	 * @param rand: Random variable.
	 */
	private static int CheckForDeaths(int infected, double rateDeath, Random rand) {
		int deaths = 0;
		for(int i = 0;i<infected; i++){
			if (rand.nextDouble() <= rateDeath){
				deaths++;
			}
		}
		return deaths;
	}
	
	/*
	 * @param susceptible: Number of susceptible
	 * @param infected: Number of infected.
	 * @param rateSR: Rate of S to R.
	 * @param rateIR: Rate of I to R.
	 * @param rand: Random variable.
	 */
	private static int[] CheckForRecoveries(int susceptible, int infected, double rateSR, double rateIR, Random rand) {
		int sRecoveries = 0;
		int iRecoveries = 0;
		for(int i = 0;i < susceptible; i++){
			if(rand.nextDouble() <= rateSR){
				sRecoveries++;
			}
		}
		for(int i = 0;i < infected; i++){
			if(rand.nextDouble() <= rateIR){
				iRecoveries++;
			}
		}
		int recoveries[] = {sRecoveries,iRecoveries};
		return recoveries;
	}
	
	/*
	 * @param susceptible: Number of susceptible.
	 * @param rateSI: Rate of S to I.
	 * @param rand: Random variable.
	 */
	private static int CheckForNewInfected(int susceptible, double rateSI, Random rand) {
		int newInfected = 0;
		for(int i = 0;i < susceptible; i++){
			if (rand.nextDouble()<=rateSI){
				newInfected++;
			}
		}
		return newInfected;
	}
}
