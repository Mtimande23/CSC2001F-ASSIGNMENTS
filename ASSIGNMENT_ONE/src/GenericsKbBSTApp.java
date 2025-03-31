
import java.util.Scanner;
import java.io.*;

/**program to load and access general terms 
*and sayings used in mordern day time using a binary search tree.
*@author Themba Shongwe
*16 March 2025.
 */
public class  GenericsKbBSTApp{

    BinarySearchTree<dataStore> tree;

    /**creates tree that stores the data in node form. */
    public GenericsKbBSTApp(){}

    /**load method is to take the file and use each and every line to construct a binary search tree with the nodes having their data being
     * search tree with the nodes having their data being each line.
     * @param fileName is the name of the text file.
    */

    public void load(String fileName){
        tree = new BinarySearchTree<dataStore>();
        int lineCount=0;
        try(Scanner file=new Scanner(new File(fileName))){//to count the number if lines if file does exist.
           while(file.hasNextLine()){
            lineCount++;
            file.nextLine();
           }  
        }
        catch (Exception e) {
          System.out.println("Could not locate Knowledge base.");//if unable to locate file with given file name.
    
        } 
    
        
        try(Scanner file=new Scanner(new File(fileName))){
            for(int i=0;i<lineCount;i++){
                String line=file.nextLine();
                String[] tempArray=line.split("\t",3);//temporary array to hold each element of a linne after splitting each line element by the tab character.
                dataStore data= new dataStore(tempArray[0], tempArray[1], tempArray[2]);
                tree.insert(data);
            }System.out.println("Knowledgebase loaded successfully.");//prints if tree is correctly constructed.
    
        }
        catch(Exception e){
            System.out.println("Could not load knowledge base.");//prints if tree is not correctly constructed.
        }
        

    }

    /**
     * adds/updates node in the binarySearchTree.
     * @param data contains line node data(term,statement,score)
    */

    public void add(dataStore data){

        if(tree.find(data)==null){//if the specific is not already in the Tree, it will add it to the binarySearchTree.
            tree.insert(data);
            System.out.println("Statement for "+data.getTerm()+" has been added.");
        }
        else if(tree.find(data)!=null){//if the specific is already in the Tree, 
            tree.delete(data);//delete old node.
            tree.insert(data);//insert new node.
            System.out.println("Statement for "+data.getTerm()+" has been updated.");
        }
       
    }

    /**
     * searches for keyword and returns string stating accociated statement and confidence score.
     * @param term that is searcheed for 
     */

    public void lookFor(String term){
        dataStore other=new dataStore(term);

            if(tree.find(other)!=null){//if given term is found
            System.out.println("Statement Found: "+(tree.find(other).data).getStatement()+"(Confidence score: "+(tree.find(other).data).getConfidence()+")");
        }
        else{
            System.out.println("Statement not found. :(");
        }
    }
    /**
     * searches for term
     * if term if found, checks if the given statement and statement of node data correspond.
     * if they correpond, return confidence score.
     * @param term is the given term to look for
     * @param statement is the statement supposedly associated with it.
     */

    public void lookFor(String term,String statement){
        dataStore other=new dataStore(term,statement);
            if(tree.find(other)!=null){
                if((tree.find(other).data).compareStatement(other)==0){
                    System.out.println("The statement was found and has a Confidence score of "+(tree.find(other).data).getConfidence()+".");
                }
           
        }
        else{
            System.out.println("Match not found. :(");
        }

    }

    /**main method which is the one the user will interact with.
    */
    public static void main(){

        GenericsKbBSTApp tree= new GenericsKbBSTApp();
        boolean status=true;
        
        while(status){
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Choose an action from the menu:");
                System.out.println();
                System.out.println("1.Load a knowledge base from a file");
                System.out.println("2.Add a new statement to the knowledge base");
                System.out.println("3.Search for a statement in the knowledge base by term");
                System.out.println("4.Search for a statement in the knowledge base by term and sentence");
                System.out.println("5.Quit");
                System.out.print("Enter your choice: ");
                int choice =keyboard.nextInt();

                switch(choice){//depending  on users choice

                    case 1:
                    System.out.print("Enter file name: ");
                    keyboard.nextLine();
                    String name=keyboard.nextLine();
                    tree.load(name);
                    break;

                    case 2:
                    System.out.print("Enter the term: ");
                    keyboard.nextLine();
                    String term=keyboard.nextLine();

                    System.out.print("Enter statement: ");
                    String state=keyboard.nextLine();

                    System.out.print("Enter confidence score: ");
                    String score=keyboard.nextLine();
                    dataStore newData=new dataStore(term,state,score);
                    tree.add(newData);
                    break;

                    case 3:
                        System.out.print("Enter the term to search: ");
                        keyboard.nextLine();
                        String word=keyboard.nextLine();

                        tree.lookFor(word);
                        break;

                    case 4:
                        System.out.print("Enter the term to search: ");
                        keyboard.nextLine();
                        String key=keyboard.nextLine();

                        System.out.print("Enter statement to search for: ");
                        String value=keyboard.nextLine();
                        tree.lookFor(key,value);
                        break;

                    case 5:
                        status=false;
                        System.out.println("Well hope you enjoyed your time here, Hope to see you again soon :) ");
                        break;
                }
            }
        }
}
