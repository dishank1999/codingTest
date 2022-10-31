import java.io.*;
import java.util.*;


class Goodies {
  String name;
  int price;

  public Goodies(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString() { 
      return this.name + ": " + this.price;
  }
}

public class Main {
  
  public static void main(String[] args) throws Exception {
    
    // input the file
    FileInputStream fis=new FileInputStream("input.txt");       

    // create scanner object to take inputs
    Scanner sc=new Scanner(fis);
    int n = Integer.parseInt(sc.nextLine().split(": ")[1]);   // number of employees
    sc.nextLine(); sc.nextLine(); sc.nextLine();              // to avoid blank/unnecesary lines

    // create list to store the goodies
    ArrayList<Goodies> goodies = new ArrayList<Goodies>();

    while(sc.hasNextLine())  
    {
      String latest[] = sc.nextLine().split(": "); // split current goodies

      // Add the goodies, price to the list by creating new Goodies object
      goodies.add(new Goodies(latest[0], Integer.parseInt(latest[1])));  
    }
    sc.close();

    // sort the list based on the difference between prices of goddies
    Collections.sort(goodies, new Comparator<Goodies>(){
      public int compare(Goodies a, Goodies b) { 
        return a.price - b.price; 
      } 
    });

    int gSize = goodies.size();
    int min_diff = goodies.get(gSize-1).price;  // starting from last goodie
    int min_index = 0;

    for(int i=0; i<gSize-n+1; i++) {
      // calculate the minimum difference between goodies
      int diff = goodies.get(n+i-1).price-goodies.get(i).price;

      // if minimum difference is found, store it along with index
      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    
    

    FileWriter fw = new FileWriter("output.txt");
    fw.write("The goodies selected for distribution are:\n\n");

    for(int i=min_index;i<min_index + n; i++) {
      fw.write(goodies.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
	  fw.close();
  }
}