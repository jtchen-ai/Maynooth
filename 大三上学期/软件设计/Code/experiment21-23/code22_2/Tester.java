package experiment22_2;

class Tester extends ReviewPerson{
    private String NAME = "Tester";

    @Override
    public void handle(String program) {
        if("no bug".equals(program)){
            System.out.println(NAME + " thinks there is no problem, he will notify the cto.");
            if(person != null){
                person.handle(program);
            }
        }else{
            System.out.println(NAME  + ": Program has issues. Rejecting submission.");
        }

    }
}
