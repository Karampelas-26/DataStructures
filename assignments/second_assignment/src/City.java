import java.text.DecimalFormat;

public class City implements  CityInterface, Comparable<City>{

    private int ID;
    private String name;
    private int population;
    private int CovidCases;

//    protected static StringDoubleEndedQueueImpl<Integer> IDs = new StringDoubleEndedQueueImpl<>();




    public City(int ID, String name, int population, int covidCases) {

        this.ID = ID;
        this.name = name;
        this.population = population;
        this.CovidCases = covidCases;
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
            double compared = this.calculateDensity() - obj.calculateDensity();
            if(compared > 0){
                return 1;
            }
            else if (compared == 0){
                return 0;
            }
            else return -1;

    }


    public double calculateDensity( ){
        double density = (this.getCovidCases() * 50_000)/ this.getPopulation();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        try{
            return Double.parseDouble((df.format(density)));
        } catch (NumberFormatException e){
            System.out.println("Not a number "+this.name);
            return 0;
        }

    }

    @Override
    public String toString() {
        return "City{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", CovidCases=" + CovidCases +
                '}';
    }
}
