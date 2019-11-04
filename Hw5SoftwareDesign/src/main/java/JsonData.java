import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.json.simple.JSONArray;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

public class JsonData {

    public static void main(String[] args) throws Exception {
        JSONParser jsonParser = new JSONParser();
        LinkedList<JSONObject> m = new LinkedList<>();
        File f = new File("dblp_papers_v11_first_100_lines.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String readLine = "";
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter a Keyword");
        String Keyword = myObj.nextLine().toLowerCase();
        String inputNumber = myObj.nextLine();
        int InputNumbers = Integer.valueOf(inputNumber);
        System.out.println(InputNumbers);
        //Read Line by Line
        LinkedList<String> FinalReference = new LinkedList<>();
        LinkedList<String> FinalReference1 = new LinkedList<>();

        while ((readLine = b.readLine()) != null) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(readLine);
            JSONObject jsonObject1 = new JSONObject();
            m.add(jsonObject);

        }
    System.out.println("Tier 1");
        int counter = 0;
        String[] Holder = new String[10000000]; //this will be the array to hold all of the references
        // all of the data is now inside of the JSON array.
        for (int i = 0; i < m.size(); i++) { // this is will get all of the tier one papers

            if (m.get(i).get("title").toString().toLowerCase().contains(Keyword)) {
                System.out.println(m.get(i).get("title").toString());
              //  System.out.println((m.get(i).get("references").toString().split(",").length));
                try{
                    Holder = m.get(i).get("references").toString().replace("[", "").replace("]", "").replace("\"", "").split(",");
                }
                catch (Exception ex){

                }

                for(int kk=0; kk < Holder.length;kk++){
                    FinalReference.add(Holder[kk]);      //final reference will have the full size and all of the references
                }
                //Holder will hold all of the Tier 2 IDs
            }
            //   System.out.println(m.get(i).get("id").toString());

        }
        // i need one to check for references == id and i also need another linked list to hold the references for the next round
        //i would need to switch them off because of



        while(counter != InputNumbers-1){
            System.out.println("Tier " + (counter + 2));
         if(counter %2 ==0 || counter == 0){
             for(int i=0; i < m.size();i++){
                 for(int j =0; j < FinalReference.size();j++){
                     if(FinalReference.get(j).contains(m.get(i).get("id").toString())){
                         System.out.println(m.get(i).get("title"));
                         try{
                       //      System.out.println("REFERENCES____________________________________");
                           //  System.out.println(m.get(i).get("references").toString().toString().replace("[", "").replace("]", "").replace("\"", ""));  //all of the new references that you need;
                             Holder = m.get(i).get("references").toString().replace("[", "").replace("]", "").replace("\"", "").split(",");
                             for(int ll=0; ll < Holder.length;ll++){
                                 FinalReference1.add(Holder[ll]);
                             }


                         }catch (Exception ex){

                         }

                     }
                 }
             }

             counter++;
           //  System.out.println(FinalReference);
             FinalReference.clear();
         }else{
             for(int i=0; i < m.size();i++){
                 for(int j =0; j < FinalReference1.size();j++){
                     if(FinalReference1.get(j).contains(m.get(i).get("id").toString())){
                         System.out.println(m.get(i).get("title"));
                         try{
                            // System.out.println("REFERENCES____________________________________");
                             //System.out.println(m.get(i).get("references").toString().toString().replace("[", "").replace("]", "").replace("\"", ""));  //all of the new references that you need;
                             Holder = m.get(i).get("references").toString().replace("[", "").replace("]", "").replace("\"", "").split(",");
                             for(int ll=0; ll < Holder.length;ll++){
                                 FinalReference.add(Holder[ll]);
                             }


                         }catch (Exception ex){

                         }

                     }
                 }
             }

             counter++;
            // System.out.println(FinalReference1);
             FinalReference1.clear();
         }

        }



    }
}


