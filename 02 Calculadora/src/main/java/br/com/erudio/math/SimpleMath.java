package br.com.erudio.math;

public class SimpleMath {
    public Double sum(Double firstNumber, Double secondNumber){
        return firstNumber + secondNumber;
    }
    public Double substrair(Double firstNumber, Double secondNumber){
        return firstNumber - secondNumber;
    }
    public Double multiplicar(Double firstNumber, Double secondNumber){
        return firstNumber * secondNumber;
    }
    public Double dividir(Double firstNumber, Double secondNumber){
        return firstNumber / secondNumber;
    }
    public Double media(Double firstNumber, Double secondNumber){
        return (firstNumber + secondNumber)/2;
    }
    public Double raizQuadrada(Double number){
        return (Double) Math.sqrt(number);
    }
}
