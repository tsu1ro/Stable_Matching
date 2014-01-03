                                                                     
                                                                     
                                                                     
                                             
/*
																	 * To change this template, choose Tools | Templates
																	 * and open the template in the editor.
																	 */
																	

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Yuichiro Tsukamoto
 * 
 * @author Ecslogon2
 */
class Match {
    Scanner in = new Scanner(System.in);
	
    private double startTime;
    private double endTime;
	private int num;
	String str;
	Integer prefInt[];
	//String[] pref;
	
	
	ArrayList<Integer> guysPrefList;
	ArrayList<Integer> girlsPrefList;
	ArrayList<Integer> freeGuys;
	Map<Integer, ArrayList<Integer>> guysPrefMap;
	Map<Integer,ArrayList<Integer>> girlsPrefMap;
	Map<Integer, Integer> engage;
	Map<Integer,Integer> engageGirl;
	public Match(){
		
		//ArrayList free = new ArrayList<String>(Arrays.asList(guys));
		guysPrefList = new ArrayList<Integer>();
		girlsPrefList = new ArrayList<Integer>();
		//freeGuys = new ArrayList<String>(free);
		guysPrefMap = new TreeMap<Integer, ArrayList<Integer>>();
		girlsPrefMap = new TreeMap<Integer, ArrayList<Integer>>();	
		engage = new TreeMap<Integer, Integer>();
		freeGuys = new ArrayList<Integer>();
		engageGirl = new TreeMap<Integer, Integer>();    //inverted map of "engage" map
	}
	
	public void getPref(){
		
		System.out.print("Type the number of men and women:");
		num = in.nextInt();
		prefInt = new Integer [num];
		System.out.println();
		String pref;
		String[] tempPref={};
		
		//set the boy's preference to list**********************************************************
		System.out.println("Type the boys preference");
		for(int i=1;i<=num;i++){
			System.out.print("m"+i+":");
			guysPrefList = new ArrayList<Integer>();
			pref = in.next();
			tempPref = pref.split(",");
                for(int j=0;j<num;j++){
                    prefInt[j] = Integer.parseInt(tempPref[j]);
                    guysPrefList.add(prefInt[j]);
                }
                System.out.println("prefList="+guysPrefList.toString());
                
                guysPrefMap.put(i,guysPrefList);
                freeGuys.add(i);
        }
		System.out.println();
		System.out.println(guysPrefMap.toString());
			
                
		//set the girl's preference to list*******************************************************
		System.out.println("Type the girls preference");
		for(int i=1;i<=num;i++){
			System.out.print("w"+i+":");
			girlsPrefList = new ArrayList<Integer>();
			pref = in.next();
			tempPref = pref.split(",");
			
            for(int j=0;j<num;j++){
                prefInt[j] = Integer.parseInt(tempPref[j]);
                girlsPrefList.add(prefInt[j]);
            }
            System.out.println("prefList="+girlsPrefList.toString());
            
            girlsPrefMap.put(i,girlsPrefList);
                        
        }
		System.out.println();
		//System.out.println(girlsPrefMap.toString());
		
		System.out.println("Show the boys and girls preference list");
		System.out.println("boy:"+guysPrefMap.toString());
		System.out.println("girl:"+girlsPrefMap.toString());
	}
	
	/**
	 * PreCondtion: guys and girl's preference list has already completed
	 * PostCondition: the matching has done
	 * @param guys
	 * @param guysPref
	 * @param girlsPref
	 * @return the engage list
	 */
	public void match(){
		
		int k = 0;
		
		while(!freeGuys.isEmpty()){
            //System.out.println(freeGuys.toString());
			Integer thisGuy = freeGuys.remove(0);  // chose the guy from free guy list
			ArrayList<Integer> thisGuysPref = guysPrefMap.get(thisGuy);
			for(int i=0;i<thisGuysPref.size();i++){			//continue until guy find the girl
				k = 0;
                                //System.out.println("this guy is "+thisGuy);
                                //System.out.println("this guy's"+k+ "'s preference is "+guysPrefMap.get(i+1).get(k) );
				if(!engage.containsValue(thisGuysPref.get(i))){		//if girls is free
					//System.out.println("girl is free");
					engage.put(thisGuy,thisGuysPref.get(i));
                    //System.out.println("w"+thisGuysPref.get(i)+" has been set to m"+thisGuy);
					engageGirl.put(thisGuysPref.get(i), thisGuy);
					break;										
				}else{		//if girls is not free
					//System.out.println("emgagedGuy is "+engageGirl.get(thisGuysPref.get(i)));
					Integer engagedGuy = engageGirl.get(thisGuysPref.get(i));//engageGirl.get(thisGuysPref.get(i));     // set the engaged guy
					//System.out.println("engagedGuy="+engagedGuy);
					
					if(checkGirlPref(thisGuy, engagedGuy)){		//if girl's preference is heigher than the guy engaging with the girl
						//System.out.println("set the new one");
                        engage.put(thisGuy,guysPrefMap.get(thisGuy).get(i));
                        engage.put(engagedGuy, null);   //set the null
                        engageGirl.put(engagedGuy, null);
                        engageGirl.put(guysPrefMap.get(thisGuy).get(i), thisGuy);
                                                
						freeGuys.add(engagedGuy);
						break;
					}	
					//nothing to do, if the guy who engaged in the girl is ranked heigher
				}
                                
                                k++;
			}
            //showPair();
            //System.out.println();
		}
		
	
	}
	
	/**
	 * 
	 * @param guysPrefLsit
	 * @param girlsPrefList
	 */
	public void match(TreeMap<Integer,ArrayList<Integer>> guysPrefMap,
					  TreeMap<Integer,ArrayList<Integer>> girlsPrefMap){
			
			int k = 0;
			
			while(!freeGuys.isEmpty()){
	            //System.out.println(freeGuys.toString());
				Integer thisGuy = freeGuys.remove(0);  // chose the guy from free guy list
				ArrayList<Integer> thisGuysPref = guysPrefMap.get(thisGuy);
				for(int i=0;i<thisGuysPref.size();i++){			//continue until guy find the girl
					k = 0;
	                                //System.out.println("this guy is "+thisGuy);
	                                //System.out.println("this guy's"+k+ "'s preference is "+guysPrefMap.get(i+1).get(k) );
					if(!engage.containsValue(thisGuysPref.get(i))){		//if girls is free
						//System.out.println("girl is free");
						engage.put(thisGuy,thisGuysPref.get(i));
	                    //System.out.println("w"+thisGuysPref.get(i)+" has been set to m"+thisGuy);
						engageGirl.put(thisGuysPref.get(i), thisGuy);
						break;										
					}else{		//if girls is not free
						//System.out.println("emgagedGuy is "+engageGirl.get(thisGuysPref.get(i)));
						Integer engagedGuy = engageGirl.get(thisGuysPref.get(i));//engageGirl.get(thisGuysPref.get(i));     // set the engaged guy
						//System.out.println("engagedGuy="+engagedGuy);
						
						if(checkGirlPref(thisGuy, engagedGuy)){		//if girl's preference is heigher than the guy engaging with the girl
							//System.out.println("set the new one");
	                        engage.put(thisGuy,guysPrefMap.get(thisGuy).get(i));
	                        engage.put(engagedGuy, null);   //set the null
	                        engageGirl.put(engagedGuy, null);
	                        engageGirl.put(guysPrefMap.get(thisGuy).get(i), thisGuy);
	                                                
							freeGuys.add(engagedGuy);
							break;
						}	
						//nothing to do, if the guy who engaged in the girl is ranked heigher
					}
	                                
	                                k++;
				}
	            //showPair();
	            System.out.println();
			}
			
		
		}
	
	
	
	
	
	/**
	 * PreCondition: haven't know which guy is higher ranked in girls preference
	 * PostCondition: know which men, thisGuy or engagedGuy, are higher ranked  
	 * @param girlsPref
	 * @param thisGuy
	 * @param engagedGuy
	 * @return  true, if this guy is higher ranked in girls preference.
	 * 			false, if engaged guy is higher ranked in girls preference. 
	 */
	public boolean checkGirlPref(Integer thisGuy, Integer engagedGuy){
		
		//System.out.println("checkGirlPref is called");
		//System.out.println("girls indexOf engegedGuy "+girlsPrefList.indexOf(engagedGuy));
		//System.out.println("girls indexOf thisGuy "+girlsPrefList.indexOf(thisGuy));
		if(girlsPrefList.indexOf(thisGuy) < girlsPrefList.indexOf(engagedGuy)){   	//this guy is heigher ranked than engaged guy
			//System.out.println("the guy is heigher ranked than engaged guy");
			return true;
			
		}else{							//engaged guy is heiger ranked than this guy
			//System.out.println("engaged guy is heiger ranked than this guy");

			return false;
		}
		
	}
	
	
	public void showPair(){
		System.out.println("Show engagemap");
		System.out.println(engage.toString());
        //System.out.println("Show engageGirlmap");
		//System.out.println(engageGirl.toString());
                
	}
	
	public ArrayList<Integer> makeRandom(int num){
		ArrayList<Integer> randomList = new ArrayList<Integer>(num);
		for(int i=0; i<num; i++){
			randomList.add(i);
		}
		
	    Collections.shuffle(randomList);
		
		return randomList;
	}
	
	public void setGuysPrefMap(ArrayList<Integer> guysPrefList, int guysNum){
		guysPrefMap.put(guysNum, guysPrefList);
	}
	
	public void setGirlsPrefMap(ArrayList<Integer> girlsPrefList, int girlsNum){
		guysPrefMap.put(girlsNum, guysPrefList);
	}
	
	public void setFreeGuys(int freeGuysNum){
		for(int i=0; i<freeGuysNum; i++){
			freeGuys.add(i);
		}
	}
	
	public void start() {
		startTime = System.nanoTime();
	}
	
	/**
	*計測終了時間をセット.
	*/
	public void end() {
		endTime = System.nanoTime();
	}
	
	public double getStartTime(){
		return startTime;
	}
	
	public double getEndTime(){
		return endTime;
	}
	
}