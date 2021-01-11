public class Suspect implements Comparable<Suspect> {
    private int AFM;
    private String firstName;
    private String lastName;
    private double savings;
    private double taxedIncome;
    private double diff;
    public Suspect() {
    }

    public Suspect(int AFM, String firstName, String lastName, double savings, double taxedIncome) {
        this.AFM = AFM;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
        this.diff = calculateSuspicion();
    }

//    public int getAFM() {
//        return AFM;
//    }

    public void setAFM(int AFM) {
        this.AFM = AFM;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getTaxedIncome() {
        return taxedIncome;
    }

    public void setTaxedIncome(double taxedIncome) {
        this.taxedIncome = taxedIncome;
    }

    public int key(){
        return this.AFM;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Suspect{");
        sb.append("AFM=").append(AFM);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", savings=").append(savings);
        sb.append(", taxedIncome=").append(taxedIncome);
        sb.append(", diff=").append(diff);
        sb.append('}');
        return sb.toString();
    }

    public double calculateSuspicion(){
        return this.savings - this.taxedIncome;
    }
    @Override
    public int compareTo(Suspect suspect) {
        double compared = this.calculateSuspicion() - suspect.calculateSuspicion();
        if (compared > 0)
            return 1;
        else if (compared < 0)
            return -1;
        else
            return 0;
    }
}
