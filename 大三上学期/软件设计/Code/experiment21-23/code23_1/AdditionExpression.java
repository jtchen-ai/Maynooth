package experiment23_1;

class AdditionExpression implements Expression{
    private Expression leftExpression;
    private Expression rightExpression;
    public AdditionExpression(Expression leftExpression, Expression rightExpression){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public ComplexNumber interpret() {
        return leftExpression.interpret().add(rightExpression.interpret());
    }
}
