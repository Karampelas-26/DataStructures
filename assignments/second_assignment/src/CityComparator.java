import java.util.Comparator;

public class CityComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        double res = ((City)o1).calculateDensity() - ((City)o2).calculateDensity();
        if (res > 0) return 1;
        else if(res <0) return -1;
        else return 0;
        }
}
