public class Match{
    public final int donor; // person donating a healthy kidney
    public final int beneficiary; // person whom the donor wants to receive a kidney
    public final int recipient; // person in need of a kidney who is compatible with the donor.

    public Match(int donor, int beneficiary, int recipient){
        this.donor = donor;
        this.beneficiary = beneficiary;
        this.recipient = recipient;
    }

    public String toString(){
        return "(donor " + donor + " donates to " + recipient + " to benefit " + beneficiary + ")";
    }

    public boolean equals(Object other){
        if(this == other){
            return true;
        }
        if(other == null){
            return false;
        }
        if(getClass() != other.getClass()){
            return false;
        }
        Match otherMatch = (Match) other;
        return this.donor == otherMatch.donor 
                && this.beneficiary == otherMatch.beneficiary 
                && this.recipient == otherMatch.recipient;
    } 
}