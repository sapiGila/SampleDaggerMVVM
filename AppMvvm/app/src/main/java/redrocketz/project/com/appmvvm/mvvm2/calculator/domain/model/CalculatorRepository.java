package redrocketz.project.com.appmvvm.mvvm2.calculator.domain.model;

import java.math.BigDecimal;

import io.reactivex.Observable;
import redrocketz.project.com.appmvvm.util.CalculatorUtil;

/**
 * Created by Dell on 1/9/2018.
 */

public class CalculatorRepository {

    private CalculatorUtil calculatorUtil;

    public CalculatorRepository(CalculatorUtil calculatorUtil) {
        this.calculatorUtil = calculatorUtil;
    }

    public Observable<BigDecimal> add(BigDecimal op1, BigDecimal op2) {
        return Observable.create(emitter -> {
            emitter.onNext(calculatorUtil.add(op1, op2));
            emitter.onComplete();
        });
    }

    public Observable<BigDecimal> multiply(BigDecimal op1, BigDecimal op2) {
        return Observable.create(emitter -> {
            emitter.onNext(calculatorUtil.multiply(op1, op2));
            emitter.onComplete();
        });
    }

    public Observable<BigDecimal> divide(BigDecimal op1, BigDecimal op2) {
        return Observable.create(emitter -> {
            emitter.onNext(calculatorUtil.divideNoTryCatch(op1, op2));
            emitter.onComplete();
        });
    }

    public Observable<BigDecimal> subtract(BigDecimal op1, BigDecimal op2) {
        return Observable.create(emitter -> {
            emitter.onNext(calculatorUtil.subtract(op1, op2));
            emitter.onComplete();
        });
    }
}
