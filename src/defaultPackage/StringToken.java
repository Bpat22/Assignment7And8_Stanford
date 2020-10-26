package defaultPackage;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import acmx.export.java.util.ArrayList;

public class StringToken {

	public static void main(String[] args) {

		String file = "flights.txt";
		String delimiter = " ->";
		String line;
		
		List<List<String>> cityList = (List<List<String>>) new ArrayList();
		try (Scanner input = new Scanner(new File(file))){
			while(input.hasNext()) {
				line = input.next();
				StringTokenizer token = new StringTokenizer(delimiter);
				while(token.hasMoreElements())
					System.out.println(token.nextToken());
			}
			cityList.forEach(l -> System.out.println(l));
		}catch (Exception e) {
			System.out.println(e);
		}
	
	}
}
