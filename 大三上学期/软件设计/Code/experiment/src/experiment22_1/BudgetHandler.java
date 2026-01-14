package experiment22_1;

interface BudgetHandler {
    boolean handle(int amount);
    void setNextHandler(BudgetHandler budgetHandler);
}
