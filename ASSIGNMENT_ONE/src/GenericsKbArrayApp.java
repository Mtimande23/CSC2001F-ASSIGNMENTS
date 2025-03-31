
import java.util.Scanner;
import java.io.*;

/**program to load and access general terms 
*and sayings used in mordern day time using an array.
*@author Themba Shongwe
*16 March 2025.
 */
public class GenericsKbArrayApp{

    GenericsKbArrayApp[] knowledgeArray;
    String term;
    String statement;
    String confidence;
/**default constructor to create object */
    public GenericsKbArrayApp(){

    }
  /**constructor to create object containing each element of a line separated by tabs.
   * each line is then split by its tabs and made into an object with a term,statement and confidence score.
   * @param term the term.
   * @param statement statement accociated with term.
   * @param confidence-score how true is the statement.
  */
      public GenericsKbArrayApp(String term,String statement,String confidence){ //makes each line an object in order to access each  element easier using getter and setters
          this.term=term;
          this.statement=statement;
          this.confidence=confidence;
      }
      /**getter for statement 
       *  @return statement
      */
      public String getStatement(){
          return this.statement;
      }
      /**getter for term
       *  @return term
       */
      public String getTerm(){
          return this.term;
      }
      /**getter for confidence score
       * @return score
       */
      public String getConfidence(){
          return this.confidence;
      }


    /**loadKnowledgeBase method is to take the file and use each and every line to construct an array GenericsKbArrayApp objects.
     * @param fileName is the name of the text file.
    */
    public void loadKnowledgeBase(String fileName){//loads/makes text file into an array of GenericsKb objects.
        int lineCount=0;
        try(Scanner file=new Scanner(new File(fileName))){//to count the number if lines if file does exist
           while(file.hasNextLine()){
            lineCount++;
            file.nextLine();
           }  
        }
        catch (FileNotFoundException e) {
          System.out.println("Could not locate Knowledge base.");
    
        } 
    
        knowledgeArray=new GenericsKbArrayApp[lineCount];
        
        try(Scanner file=new Scanner(new File(fileName))){//after couunting number of lines, create and input GenericsKb objects into the GenericsKbArray.

            GenericsKbArrayApp tempObj;
            for(int j=0;j<lineCount;j++){
                String line=file.nextLine();
                String[] tempArray=line.split("\t",3);//temporary array to hold each element of a linne after splitting each line element by the tab character
           tempObj= new GenericsKbArrayApp(tempArray[0], tempArray[1], tempArray[2]);
           knowledgeArray[j]=tempObj;//imputing the objects into the GenericsKbArray.
         }
         System.out.println("Knowledge base loaded successfully.");
    
        }
        catch(FileNotFoundException e){
            System.out.println("Could not load knowledge base.");
        }
    }
    /**
     * adds/updates array at a specific index with given term and statement .
     * @param obj GenericsKbArrayApp object
    */
    public void update(GenericsKbArrayApp obj){//give the index position to the statement with a higher confidence score.
        int lineUpdate=0;//to account for current index

     for(GenericsKbArrayApp temp:knowledgeArray){
        if(obj.getTerm().equals(temp.getTerm())){
            if(Double.parseDouble(temp.getConfidence())<Double.parseDouble(obj.getConfidence())){
                knowledgeArray[lineUpdate]=obj;
            }
        }
        else{
            lineUpdate++;
        }
        
     }System.out.println("Statement for "+obj.getTerm()+" has been updated."); 

    }

     /**
     * searches for keyword and returns string stating accociated statement and confidence score.
     * @param term that is searched for 
     */

    public void lookFor(String term){//search for an object using only the term
       try{int counter=0;
        for(GenericsKbArrayApp temp:knowledgeArray){
            if(term.equals(temp.getTerm())){
                break;
            }
            else{
                counter++;
            }
        } System.out.println("Statement Found: "+knowledgeArray[counter].getStatement()+"(Confidence score: "+knowledgeArray[counter].getConfidence()+")");
    }
        catch(ArrayIndexOutOfBoundsException e){
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

public void lookFor(String term, String statement){//searching for an object using both its term and statemnent, and telling us what is its confidence score.
    try{int counter=0;
        for(GenericsKbArrayApp temp:knowledgeArray){

            if(term.equals(temp.getTerm())){
            if(statement.equals(temp.getStatement()))
                break;
            }

            else{
                counter++;
            }
        }
            System.out.println("The statement was found and has a Confidence score of "+knowledgeArray[counter].getConfidence()+".");
        
    }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Match not found. :(");
        }
}

/**main method which is the one the user will interact with.
*/
    public static void main(){

        GenericsKbArrayApp knowledgeBase=new GenericsKbArrayApp();
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
                       knowledgeBase.loadKnowledgeBase(name);
                       break;

                    case 2:
                      System.out.print("Enter the term: ");
                      keyboard.nextLine();
                      String term=keyboard.nextLine();

                      System.out.print("Enter statement: ");
                      String state=keyboard.nextLine();

                      System.out.print("Enter confidence score: ");
                      String score=keyboard.nextLine();
                      knowledgeBase.update(new GenericsKbArrayApp(term,state,score));
                      break;

                    case 3:
                    System.out.print("Enter the term to search: ");
                    keyboard.nextLine();
                    String word=keyboard.nextLine();
                    knowledgeBase.lookFor(word);
                    break;

                    case 4:
                    System.out.print("Enter the term to search: ");
                    keyboard.nextLine();
                    String key=keyboard.nextLine();
                    System.out.print("Enter statement to search for: ");
                      String value=keyboard.nextLine();

                    knowledgeBase.lookFor(key,value);
                    break;

                    case 5:
                    status=false;
                    System.out.println("Well hope you enjoyed your time here, Hope to see you again soon :) ");
                    break;
   
                }
            }
          
        }
    
}
