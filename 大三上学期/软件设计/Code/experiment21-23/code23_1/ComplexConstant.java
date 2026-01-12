package experiment23_1;

class ComplexConstant implements Expression{
    private ComplexNumber value;
    public ComplexConstant(double real, double imaginary){
        value = new ComplexNumber(real, imaginary);
    }

    @Override
    public ComplexNumber interpret() {
        return value;
    }
}
