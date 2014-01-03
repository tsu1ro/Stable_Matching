import java.util.ArrayList;
import java.util.Scanner;
import java.lang.System;

public class Main {

	public static void main(String[] args) {
		Match match = new Match();
		final int NUMBER_OF_PEOPLE = 10;
	
		for(int j = 0;j<10;j++){
		//final int NUMBER_OF_PEOPLE = 10;
		
		
		ArrayList<Integer> guysPref = new ArrayList<Integer>(match.makeRandom(NUMBER_OF_PEOPLE));
		ArrayList<Integer> girlsPref = new ArrayList<Integer>(match.makeRandom(NUMBER_OF_PEOPLE));
		
		for(int i=0;i<NUMBER_OF_PEOPLE;i++){
			match.setGirlsPrefMap(girlsPref, i);
			match.setGuysPrefMap(guysPref, i);
		}
		
		//match.getPref();
		match.setFreeGuys(NUMBER_OF_PEOPLE);
		
		match.start();
		match.match();
		match.end();
		
		System.out.printf("%.7f\n",(match.getEndTime() - match.getStartTime())/1000000000);
		}
		//match.showPair();
	}
}
