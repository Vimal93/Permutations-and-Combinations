import java.io.*;
import java.util.ArrayList;
//import java.util.Arrays;

class PermutationsAndCombinations
{
public static void findFrequency(String number, int len) {
    char[] characters = new char[number.length()];
    int[] frequency = new int[number.length()];
	for(int j=0; j<number.length(); j++) {	
	char temp = number.charAt(j);
	for(int k=0; k<characters.length; k++) {
		if(temp == characters[k]) {
			frequency[k]++;
			break;
		}
		else if(characters[k] == 0) {
			characters[k] = temp;
			frequency[k]++;
			break;
		}
	}
	}
	
	String sequence = "";
	for(int k=0; k<characters.length; k++) {
		if(frequency[k]>0)
			sequence = sequence + characters[k];
	}
		
	String[] combElements = new String[number.length()];
	int combIter = 0;
	for(int k=0; k<characters.length; k++) {
		if(frequency[k] > 0) {
			int f = frequency[k];
			String s = "";
			int j = 0;
			int freq = 0;
			while(!(freq+1 > f)) {
				s = s + characters[k];
				freq++;
				combElements[combIter] = s;
				combIter++;
				if(freq+1>len) break;							
			}
		}
	}
	
	System.out.println("Combinations for number : " + number);
	ArrayList<String> combs = new ArrayList<String>();
	combinations(combElements, "", combs, len);
	System.out.println("Number of combinations : "+ combs.size() + "\n");
	
	System.out.println("Permutations for number : " + number);
	ArrayList<String> perms = new ArrayList<String>();
	permutations(characters, frequency, sequence, "", perms, len);
	System.out.println("Number of permutations : " + perms.size());
  }
  
  
  public static void combinations(String[] characters, String prefix, ArrayList<String> combs, int len) {
	for(int i=0; i<characters.length; i++) {
		String newPrefix = "";
		char newChar = ' ';
		if(characters[i] != null) {
			newPrefix = prefix + characters[i];
			newChar = characters[i].charAt(0);
		}
		
		String[] newCharacters = new String[characters.length-1];
		int k=0;
		if(newPrefix.length() == len) {
			System.out.println(newPrefix);
			combs.add(newPrefix);
			continue;
		}
		
		for(int j=i+1; j<characters.length; j++) {
			if(characters[j] == null) 
				break;
			if(newChar == characters[j].charAt(0))
				continue;
			newCharacters[k] = characters[j];
			k++;			
		}
		
		combinations(newCharacters, newPrefix, combs, len);	
	}
	}

  public static void permutations (char[] characters, int[] frequency, String sequence, String prefix, ArrayList<String> perms, int len) {
	for(int i=0; i<sequence.length(); i++) {
		int[] newFrequency = frequency.clone();
		String newPrefix = prefix + sequence.charAt(i);
		String newSequence = "";
		for(int j=0; j<characters.length; j++) {
			if(characters[j] == sequence.charAt(i))
				newFrequency[j]--;
			if(newFrequency[j]>0)
				newSequence = newSequence + characters[j];
		}
		if(newPrefix.length() == len)
		{
			System.out.println(newPrefix);
			perms.add(newPrefix);
			continue;
		}
		permutations(characters, newFrequency, newSequence, newPrefix, perms, len);
	}
	
  }

public static void main(String[] a) {
	int len = Integer.parseInt(a[1]);
	findFrequency(a[0], len);

}

}