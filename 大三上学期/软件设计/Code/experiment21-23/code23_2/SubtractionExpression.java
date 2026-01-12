package experiment23_2;

class SubtractionExpression implements Expression{
    private Expression leftExpression;
    private Expression rightExpression;
    public SubtractionExpression(Expression leftExpression, Expression rightExpression){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() - rightExpression.interpret();
    }
}
