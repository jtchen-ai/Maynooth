package experiment22_2;

class CTO extends ReviewPerson{
    private String NAME = "CTO";
    @Override
    public void handle(String program) {
        if("no bug".equals(program)){
            System.out.println(NAME + " thinks there is no problem, he will notify the boss.");
            if(person != null){
                person.handle(program);
            }
        }else{
            System.out.println(NAME  + ": Program has issues. Rejecting submission.");
        }

    }
}
