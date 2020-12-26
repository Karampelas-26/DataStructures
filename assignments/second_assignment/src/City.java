import java.text.DecimalFormat;
import java.text.NumberFormat;

public class City implements  CityInterface, Comparable<City>{

    private int ID;
    private String name;
    private int population;
    private int CovidCases;
    private final long f = 50_000;
    private double density = calculateDensity();

//    protected static StringDoubleEndedQueueImpl<Integer> IDs = new StringDoubleEndedQueueImpl<>();




    public City(int ID, String name, int population, int covidCases) {

        this.ID = ID;
        this.name = name;
        this.population = population;
        this.CovidCases = covidCases;
        this.density = calculateDensity();
    }

    public City() {
    }


    @Override
    public int getID() {
        return this.ID ;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPopulation() {
        return this.population;
    }

    @Override
    public int getCovidCases() {
        return this.CovidCases;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public void setCovidCases(int CovidCases) {
        this.CovidCases = CovidCases;
    }

    @Override
    public int compareTo(City obj) {
            double compared = this.density - obj.density;
//            System.out.println(this.name+ " "+compared+" "+obj.name);
            if(compared > 0){
                return 1;
            }
            else if (compared < 0){
                return -1;
            }
            else return 0;

    }


    public double calculateDensity( ){
        long covid = (long) this.getCovidCases();
        long mul = covid*f;
        double density = mul / (double)this.getPopulation();
        NumberFormat df = new DecimalFormat("#0.00");

//        System.out.println(this.ID+" "+ this.name+" density "+df.format(density));
        return Double.parseDouble((df.format(density)));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("ID=").append(ID);
        sb.append(", name='").append(name).append('\'');
        sb.append(", population=").append(population);
        sb.append(", CovidCases=").append(CovidCases);
        sb.append(", density=").append(density);
        sb.append('}');
        return sb.toString();
    }
}
