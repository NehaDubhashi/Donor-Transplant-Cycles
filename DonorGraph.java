import java.util.*;

public class DonorGraph {
    private List<List<Match>> adjList;

    // The donatingTo array indicates which recipient each donor is
    // affiliated with. Specifically, the donor at index i has volunteered
    // to donate a kidney on behalf of recipient donatingTo[i].
    // The matchScores 2d array gives the match scores associated with each
    // donor-recipient pair. Specificically, matchScores[x][y] gives the
    // HLA score for donor x and recipient y.
    public DonorGraph(int[] donorToBenefit, int[][] matchScores){
        adjList = new ArrayList<>();
        for (int i = 0; i < matchScores[0].length; i++) {
            adjList.add(new ArrayList<Match>());
        }
        for (int i = 0; i < matchScores.length; i++) {
            for (int recipient = 0; recipient < matchScores[0].length; recipient++) {
                if (matchScores[i][recipient] >= 60) {
                    adjList.get(donorToBenefit[i]).add(new Match(i, donorToBenefit[i], recipient));
                }
            }
        }
    }

    // Will be used by the autograder to verify your graph's structure.
    // It's probably also going to helpful for your debugging.
    public boolean isAdjacent(int start, int end){
        for(Match m : adjList.get(start)){
            if(m.recipient == end)
                return true;
        }
        return false;
    }

    // Will be used by the autograder to verify your graph's structure.
    // It's probably also going to helpful for your debugging.
    public int getDonor(int beneficiary, int recipient){
        for(Match m : adjList.get(beneficiary)){
            if(m.recipient == recipient)
                return m.donor;
        }
        return -1;
    }


    // returns a chain of matches to make a donor cycle
    // which includes the given recipient.
    // Returns an empty list if no cycle exists. 
    public List<Match> findCycle(int recipient){
        Stack<Match> result = new Stack<>();
        boolean[] seen = new boolean[adjList.size()];
        return helper(recipient, recipient, result, seen);
    }

    // helper method to find a chain of matches, uses a stack
    // to keep track of potential cycles with the target and 
    // backtracks to a new pathway if the cycle is not valid. 
    private Stack<Match> helper(int curr, int target, Stack<Match> result, boolean[] seen) {
        for(Match m : adjList.get(curr)) {
            if(m.recipient == target) {
                result.add(m);
                seen[target] = true;
                return result;
            }
            if(!seen[m.recipient]) {
                result.add(m);
                seen[m.recipient] = true;
                helper(m.recipient, target, result, seen);
                if(seen[target]) {
                    return result;
                }
                else {
                    result.pop();
                }
            }
        }
        return result;
    }

    // returns true or false to indicate whether there
    // is some cycle which includes the given recipient.
    public boolean hasCycle(int recipient){
        if (!findCycle(recipient).isEmpty()) {
            return true;
        }
        return false; // recurse through and see if it exists
    }
}
