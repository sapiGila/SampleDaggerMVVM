package redrocketz.project.com.appmvvm.mvvm1.model;

import java.math.BigDecimal;

import io.reactivex.Observable;
import redrocketz.project.com.appmvvm.model.CalculatorModel;
import redrocketz.project.com.appmvvm.util.CalculatorUtil;

/**
 * Created by Dell on 1/8/2018.
 */

public class CalculatorRepository implements CalculatorModel {

    private CalculatorUtil calculatorUtil;

    public CalculatorRepository(CalculatorUtil calculatorUtil) {
        this.calculatorUtil = calculatorUtil;
    }

    @Override
    public Observable<BigDecimal> add(BigDecimal op1, BigDecimal op2) {
        return Observable.create(emitter -> {
            emitter.onNext(calculatorUtil.add(op1, op2));
            emitter.onComplete();
        });
    }

    @Override
    public Observable<BigDecimal> multiply(BigDecimal op1, BigDecimal op2) {
        return Observable.create(emitter -> {
            emitter.onNext(calculatorUtil.multiply(op1, op2));
            emitter.onComplete();
        });
    }

    @Override
    public Observable<BigDecimal> divide(BigDecimal op1, BigDecimal op2) {
        return Observable.create(emitter -> {
            emitter.onNext(calculatorUtil.divide(op1, op2));
            emitter.onComplete();
        });
    }

    @Override
    public Observable<BigDecimal> subtract(BigDecimal op1, BigDecimal op2) {
        return Observable.create(emitter -> {
            emitter.onNext(calculatorUtil.subtract(op1, op2));
            emitter.onComplete();
        });
    }
}