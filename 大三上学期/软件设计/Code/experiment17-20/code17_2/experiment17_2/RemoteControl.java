package experiment17_2;

class RemoteControl {
    private Command command;
    public void setCommand(Command command){
        this.command = command;
    }
    public void invokeCommand(){
        if(command != null){
            command.executeCommand();
        }else{
            System.out.println("No command set.");
        }
    }
}
