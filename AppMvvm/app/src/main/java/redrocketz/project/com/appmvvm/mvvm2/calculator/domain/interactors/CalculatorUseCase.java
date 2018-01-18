package redrocketz.project.com.appmvvm.mvvm2.calculator.domain.interactors;

import java.math.BigDecimal;

import javax.inject.Inject;

import io.reactivex.Observable;
import redrocketz.project.com.appmvvm.model.CalculatorModel;
import redrocketz.project.com.appmvvm.mvvm2.calculator.domain.model.CalculatorRepository;

/**
 * Created by Dell on 1/9/2018.
 */

public class CalculatorUseCase implements CalculatorModel {

    private final CalculatorRepository calculatorRepository;

    @Inject
    public CalculatorUseCase(CalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
    }

    @Override
    public Observable<BigDecimal> add(BigDecimal op1, BigDecimal op2) {
        return calculatorRepository.add(op1, op2);
    }

    @Override
    public Observable<BigDecimal> multiply(BigDecimal op1, BigDecimal op2) {
        return calculatorRepository.multiply(op1, op2);
    }

    @Override
    public Observable<BigDecimal> divide(BigDecimal op1, BigDecimal op2) {
        return calculatorRepository.divide(op1, op2);
    }

    @Override
    public Observable<BigDecimal> subtract(BigDecimal op1, BigDecimal op2) {
        return calculatorRepository.subtract(op1, op2);
    }
}