import java.io.*;
import java.util.*;

class Item {
	// class to set goodies name and its price
	String item_name;
	int item_price;

	public Item(String item_name, int item_price) {
		this.item_name = item_name;
		this.item_price = item_price;
	}

	public String toString() {
		return this.item_name + ": " + this.item_price;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
	    FileInputStream fis=new FileInputStream("C:\Users\Kishan Achar\Documents\GitHub\HighPeak\input.txt");       //filestream to take input from txt file
	    Scanner sc=new Scanner(fis);
	    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
	    sc.nextLine(); sc.nextLine(); sc.nextLine(); //using split and separating item names and item_price

	    ArrayList<Item> goodies = new ArrayList<Item>(); //to store goodies items

	    while(sc.hasNextLine())  
	    {
	      String current[] = sc.nextLine().split(": ");
	      goodies.add(new Item(current[0], Integer.parseInt(current[1])));
	    }
	    sc.close();

	    Collections.sort(goodies, new Comparator<Item>(){ //sorting the goodies
	      public int compare(Item a, Item b) { 
	        return a.item_price - b.item_price; 
	      } 
	    });

	    int min_difference = goodies.get(goodies.size()-1).item_price;//total all all goodies to be selected
	    int min_index = 0;
	    for(int i=0;i<goodies.size()-number_of_employees+1;i++) {
	      int difference = goodies.get(number_of_employees+i-1).item_price-goodies.get(i).item_price;

	      if(difference<=min_difference) {
	        min_difference = difference;//setting minimum difference
	        min_index = i;
	      }
	    }
	    FileWriter fw = new FileWriter("C:\Users\Kishan Achar\Documents\GitHub\HighPeak\output.txt"); //filestream to write the output in output.txt file
	    for(int i=min_index;i<min_index + number_of_employees; i++) {
	      fw.write(goodies.get(i).toString() + "\n");
	    }

	    fw.write("\nAnd the difference between the chosen goodie with highest item_price and the lowest item_price is " + min_difference);
		  fw.close();
	  }
}