package experiment22_2;

abstract class ReviewPerson {
    protected ReviewPerson person;

    public void setPerson(ReviewPerson person) {
        this.person = person;
    }

    public ReviewPerson getPerson() {
        return person;
    }
    public abstract void handle(String program);
}
