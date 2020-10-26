/*@author Bina 
 * File: Flight.java
 * this program lets user pick a departure city and give option to choose 
 * destination city for round trip
 */ 
package defaultPackage;

import java.io.*;
import java.util.*;

public class Flight {

	private static HashMap<String, ArrayList<String>> route = new HashMap<String, ArrayList<String>>();
	private static ArrayList<String> city = new ArrayList<String>();


	public static void main(String[] args) {

		readFile("flights.txt");

		System.out.println("Welcome to Flight Planner!");
		System.out.println("Here's a list of all the cities in our database: \n");
		getCities(city);		
		
	
		planTrip();
	}

	public static void planTrip() {
		ArrayList<String> trip = new ArrayList<String>();
		
		System.out.println("Let's plan a round-trip route!");
		System.out.println("Enter the departure city: ");
		Scanner input = new Scanner(System.in);
		String departureCity = input.nextLine();
		trip.add(departureCity);
		String nextCity = departureCity;
		
		while(true) {
			String city = getDestinationCity(nextCity);
			trip.add(city);
			if(city.equals(departureCity))break;
			nextCity = city;
			
		}
		FinalTrip(trip);		
				
	}
	
	private static String getDestinationCity(String city) {
		ArrayList<String> travel = travel(city);
		String nextCity = null;
		
		while(true) {
			System.out.println("From " + city + " you can fly directly to : ");
			getCities(travel);
			System.out.println("Where do you want to go from " + city + "? ");
			Scanner input = new Scanner(System.in);
			nextCity = input.nextLine();
			if(travel.contains(nextCity))break;
			System.out.println("You can't get to the city by a direct flight.");
		}
		return nextCity;	
	}

	private static void getCities(ArrayList<String> city) {
		for(int i = 0; i < city.size(); i++) {
			String cities = city.get(i);
			System.out.println(" " + cities);
		}
	}

	private static void readFile(String file) {
		city = new ArrayList<String>();
		route = new HashMap<String, ArrayList<String>>();

		try {

			BufferedReader br = new BufferedReader(new FileReader(file));

			while (true) {
				String line = br.readLine();
				if (line == null) break;
				if (line.length() != 0);
				{
					int devide = line.indexOf("->");
					String departureCity = line.substring(0, devide).trim();
					String destinationCity = line.substring(devide + 2).trim();
					addCity(departureCity);
					addCity(destinationCity);					
					travel(departureCity).add(destinationCity);
				}
			}		
			br.close();
		} catch (IOException  e) {
			System.out.println(e);
		}
	}

	private static void addCity(String cityName) {

		if (!city.contains(cityName)) {
			city.add(cityName);
			route.put(cityName, new ArrayList<String>());
		}
	}

	private static ArrayList<String> travel (String city) {
		return route.get(city);
	}

	private static void FinalTrip(ArrayList<String> trip) {
		System.out.println("The route you've chosen is : ");
		for (int i = 0; i < city.size(); i++) {
			if ( i < 0)
				System.out.println("->");
			System.out.println(trip.get(i));
		}
		System.out.println();
	}

}
