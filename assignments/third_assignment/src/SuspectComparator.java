import java.util.Comparator;

public class SuspectComparator implements Comparator<Suspect> {

    @Override
    public int compare(Suspect susOne, Suspect susTwo) {
        double compared = susOne.calculateSuspicion() - susTwo.calculateSuspicion();
        if (susOne.getTaxedIncome() < 9000.0) {
            if (susTwo.getTaxedIncome() < susOne.getTaxedIncome())
                return -1;
            else if (susTwo.getTaxedIncome() > susOne.getTaxedIncome())
                return 1;
            else
                return 0;
        }
        else {
            if (compared > 0)
                return 1;
            else if (compared < 0)
                return -1;
            else
                return 0;
        }
    }
}
