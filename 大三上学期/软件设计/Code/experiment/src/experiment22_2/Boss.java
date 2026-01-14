package experiment22_2;

class Boss extends ReviewPerson{
    private String NAME = "Boss";

    @Override
    public void handle(String program) {
        if("no bug".equals(program)){
            System.out.println(NAME + " thinks there is no problem. Going online!");
            if(person != null){
                person.handle(program);
            }
        }else{
            System.out.println(NAME  + ": Program has issues. Rejecting submission.");
        }

    }
}
