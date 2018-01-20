package redrocketz.project.com.appmvvm.mvvm1.viewmodel;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import redrocketz.project.com.appmvvm.model.CalculatorModel;
import redrocketz.project.com.appmvvm.mvvm1.schedulers.ISchedulerProvider;
import redrocketz.project.com.appmvvm.object.Calculator;

/**
 * Created by Dell on 1/8/2018.
 */

public class CalculatorViewModel {

    @NonNull
    private final CalculatorModel calculatorModel;

    @NonNull
    private final BehaviorSubject<Calculator> calculatorSubject = BehaviorSubject.create();

    @NonNull
    private final ISchedulerProvider schedulerProvider;

    public CalculatorViewModel(@NonNull CalculatorModel calculatorModel,
                               @NonNull ISchedulerProvider schedulerProvider) {
        this.calculatorModel = calculatorModel;
        this.schedulerProvider = schedulerProvider;
    }

    @NonNull
    public Observable<BigDecimal> calculate() {
        return calculatorSubject
                .observeOn(schedulerProvider.computation())
                .subscribeOn(schedulerProvider.ui())
                .map(calculator -> calculator)
                .flatMap(calculator -> {
                    Observable<BigDecimal> observableResult = null;
                    switch (calculator.getOperator()) {
                        case ADD:
                            observableResult = calculatorModel.add(calculator.getInputValue1(), calculator.getInputValue2());
                            break;
                        case MUL:
                            observableResult = calculatorModel.multiply(calculator.getInputValue1(), calculator.getInputValue2());
                            break;
                        case DIV:
                            observableResult = calculatorModel.divide(calculator.getInputValue1(), calculator.getInputValue2());
                            break;
                        case SUB:
                            observableResult = calculatorModel.subtract(calculator.getInputValue1(), calculator.getInputValue2());
                            break;
                    }
                    return observableResult;
                });
    }

    public void handleCalculate(Calculator calculator) {
        calculatorSubject.onNext(calculator);
    }
}