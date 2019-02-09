import java.util.Comparator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author asido sibuea
 */

// Step 1 : Create a class Student having givenNames and lastName as fields.
class Person {
    String givenNames;
    String lastName;
    
    public Person (String givenNames, String lastName){
        this.givenNames = givenNames;
        this.lastName = lastName;
    }
}

// Step 2 : Create Comparator NamesCompare. 
// Use this class to sort the text file based on last name and given(if you have same last name).
class NamesCompare implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
     int res =  o1.lastName.compareToIgnoreCase(o2.lastName);
        if (res != 0)
            return res;
        return o1.givenNames.compareToIgnoreCase(o2.givenNames);   
    }
}

//Step 3:  Create main class 
public class SortingNames {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

    	// Step 4: Create BufferedReader object to read the input text file
        BufferedReader br = new BufferedReader(new FileReader("D:\\name-sorter\\unsorted-names-list.txt"));
        
        // Step 5:  Create an ArrayList to hold the Person objects.
        ArrayList<Person> listPersonNames = new ArrayList<Person>();
        
        // Step 6 : Read every person record from input text file. For each person record, 
        // create one person object and add that person object into listPersonNames.
        String line = br.readLine();
        
        while(line != null){
            String[] names = line.split(" ");

            String givenNames = "";
            for(int a=0; a < names.length-1; a++){
                givenNames += names[a];
                givenNames += " ";
            }
            
            String lastName = names[names.length-1];
            
            listPersonNames.add(new Person(givenNames, lastName));
            
            line = br.readLine();
        }
        
        // Step 7 : Sort the ArrayList listPersonNames using Collections.sort() method by passing 
        // NamesCompare object to sort the text file.
        Collections.sort(listPersonNames, new NamesCompare());
        
        // Step 8 : Create BufferedWriter object to write the records into output text file.
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\name-sorter\\sorted-names-list.txt"));
        
        // Step 9 : Write each listPersonNames into output text file.
        for (Person person : listPersonNames){
            writer.write(person.givenNames);
            writer.write(" "+person.lastName);
            writer.newLine();
            
            System.out.println(person.givenNames+" "+person.lastName);
        }
        
        // Step 10 : Close the resources.
        br.close();
        writer.close();
    }
}
