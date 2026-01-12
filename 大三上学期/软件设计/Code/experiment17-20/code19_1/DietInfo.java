package experiment19_1;

class DietInfo {
    private String personName;
    private int days;
    private double weight;


    public DietInfo(String personName, int days, double weight){
        this.personName = personName;
        this.days = days;
        this.weight = weight;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public DietInfoMemento save(){
        return new DietInfoMemento(personName, days, weight);
    }
    public void restore(DietInfoMemento dietInfoMemento){
        this.personName = dietInfoMemento.personName;
        this.days = dietInfoMemento.days;
        this.weight = dietInfoMemento.weight;
    }

    public String getPersonName() {
        return personName;
    }

    public int getDays() {
        return days;
    }

    public double getWeight() {
        return weight;
    }

    class DietInfoMemento{
        private String personName;
        private int days;
        private double weight;

        private DietInfoMemento(String personName, int days, double weight){
            this.personName = personName;
            this.days = days;
            this.weight = weight;
        }

        private String getPersonName() {
            return personName;
        }

        private int getDays() {
            return days;
        }

        private double getWeight() {
            return weight;
        }
    }
}
