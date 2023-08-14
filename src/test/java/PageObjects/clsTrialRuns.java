package PageObjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class clsTrialRuns 
{		
	public static void main(String args[])
	{
		// Throws error if the input string is all alphabets and you are trying to 
			// extract numbers outta it. So the IF in line #21 becomes mandatory to avoid errors.
		
		// int i = 0;
		// String sText = "Ambika"; 
		
		// String sClean = sText.replaceAll("\\d",""); //remove non-digits #1
		// String sClean = sText.replaceAll("\\D",""); //remove digits	#2
		
		// These three lines are needed if you want to print all numbers available in a string.
		// Line #11 is used to extract the numbers
		// O/P: 232323 where sText = 23Am23bika23
		
		// if(sClean.length()>0)				// #1
		//	i = Integer.parseInt(sClean);	// #1
					
		// System.out.println(i);				// #1
		// System.out.println(Integer.toString(i).length());	// #1
		
		// These two lines will print only the alphabets in a given string.
		// Line #10 is used to extract the alphabets 
		// O/P: Ambika  where sText = 23Am23bika23
		
		// System.out.println(sClean);	#2
		// System.out.println(sClean.length()); #2
		
		Pattern pattern = Pattern.compile("^[[^\\*\\(\\)\\~\\`\\!\\#\\%\\&\\*\\^ ]a-zA-Z0-9_\\-\\.\\+\\@]{8,150}$");
		Matcher matcher = pattern.matcher("Ambika123");
				
		System.out.println("Match found: " + matcher.find());
	
		
	}
}
