package redrocketz.project.com.appmvvm.model;

import java.math.BigDecimal;

import io.reactivex.Observable;

/**
 * Created by Dell on 7/4/2017.
 */

public interface CalculatorModel {
    Observable<BigDecimal> add(BigDecimal op1, BigDecimal op2);
    Observable<BigDecimal> multiply(BigDecimal op1, BigDecimal op2);
    Observable<BigDecimal> divide(BigDecimal op1, BigDecimal op2);
    Observable<BigDecimal> subtract(BigDecimal op1, BigDecimal op2);
}
