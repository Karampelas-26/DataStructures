import java.text.DecimalFormat;
import java.text.NumberFormat;

public class City implements  CityInterface, Comparable<City>{

    private int ID;
    private String name;
    private int population;
    private int covidCases;
    private final long FACTOR_FOR_DENSITY = 50_000;

    public City(int ID, String name, int population, int covidCases) {
        this.ID = ID;
        this.name = name;
        this.population = population;
        this.covidCases = covidCases;
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
        return this.covidCases;
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
        this.covidCases = CovidCases;
    }

    @Override
    public int compareTo(City obj) {
            double compared = this.calculateDensity() - obj.calculateDensity();
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
        long mul = covid*FACTOR_FOR_DENSITY;
        double density = mul / (double)this.getPopulation();
        NumberFormat df = new DecimalFormat("#0.00");
        return Double.parseDouble((df.format(density)));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("ID=").append(ID);
        sb.append(", Name='").append(name).append('\'');
        sb.append(", Population=").append(population);
        sb.append(", CovidCases=").append(covidCases);
        sb.append('}');
        return sb.toString();
    }
}
