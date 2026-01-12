package experiment23_1;

class SubtractionExpression implements Expression{
    private Expression leftExpression;
    private Expression rightExpression;
    public SubtractionExpression(Expression leftExpression, Expression rightExpression){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public ComplexNumber interpret() {
        return leftExpression.interpret().subtract(rightExpression.interpret());
    }
}
