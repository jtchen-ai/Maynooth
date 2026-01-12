package experiment22_1;

class CFO implements BudgetHandler{
    private BudgetHandler nextHandler;

    @Override
    public void setNextHandler(BudgetHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean handle(int amount) {
        if(amount <= 50000){
            System.out.println("CFO approved the budget for: " + amount);
            return true;
        }else{
            if(nextHandler != null){
                return nextHandler.handle(amount);
            }
        }
        return false;
    }
}
