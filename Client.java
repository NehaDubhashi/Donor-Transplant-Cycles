import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client{

    public static String testFileName = "example.txt";

    public static void main(String[] args){
        File testFile = new File(testFileName);
        try{
            Scanner s = new Scanner(testFile);

            int numRecipients = s.nextInt();
            int numDonors = s.nextInt();

            String donorsLine = s.next();
            String[] donorsArray = donorsLine.split(",",0);
            int[] donorToBenefit = new int[donorsArray.length];
            for(int i=0; i<numDonors; i++){
                donorToBenefit[i] = Integer.parseInt(donorsArray[i]);
            }

            int[][] matchScores = new int[numDonors][numRecipients];
            for(int i=0; i<numDonors; i++){
                String matchscoreLine = s.next();
                String[] matchscoreArray = matchscoreLine.split(",",0);
                for(int j=0; j<numRecipients; j++){
                    matchScores[i][j] = Integer.parseInt(matchscoreArray[j]);
                }	
            }
            int query = s.nextInt();
            s.close();
            DonorGraph graph = new DonorGraph(donorToBenefit, matchScores);
            System.out.println(graph.findCycle(query));
            System.out.println(graph);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}