package redrocketz.project.com.appmvvm.mvvm2.calculator.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.math.BigDecimal;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import redrocketz.project.com.appmvvm.mvvm2.calculator.domain.interactors.CalculatorUseCase;
import redrocketz.project.com.appmvvm.mvvm2.common.Response;
import redrocketz.project.com.appmvvm.mvvm2.rx.SchedulersFacade;
import redrocketz.project.com.appmvvm.mvvm2.rx.bus.RxBus;
import redrocketz.project.com.appmvvm.mvvm2.rx.newbus.NewRxBus;
import redrocketz.project.com.appmvvm.object.Calculator;

/**
 * Created by Dell on 1/9/2018.
 */

public class CalculatorViewModel extends ViewModel {


    private final CalculatorUseCase calculatorUseCase;

    private final SchedulersFacade schedulersFacade;

    private CompositeDisposable mainDisposables;

    private CompositeDisposable mainDisposables2;

    private CompositeDisposable calculatorDisposables;

    private final BehaviorSubject<Calculator> calculatorSubject = BehaviorSubject.create();

    private final MutableLiveData<Response> response = new MutableLiveData<>();

    private final RxBus rxBus;

    private final NewRxBus newRxBus;

    public CalculatorViewModel(CalculatorUseCase calculatorUseCase,
                               SchedulersFacade schedulersFacade,
                               RxBus rxBus,
                               NewRxBus newRxBus) {
        this.calculatorUseCase = calculatorUseCase;
        this.schedulersFacade = schedulersFacade;
        this.rxBus = rxBus;
        this.newRxBus = newRxBus;
    }

    @Override
    protected void onCleared() {
        if (calculatorDisposables != null)
            calculatorDisposables.clear();

        if (mainDisposables != null)
            mainDisposables.clear();

        if (mainDisposables2 != null)
            mainDisposables2.clear();
    }

    public MutableLiveData<Response> response() {
        return response;
    }

    @NonNull
    private Observable<BigDecimal> calculate() {
        return calculatorSubject
                .observeOn(schedulersFacade.computation())
                .subscribeOn(schedulersFacade.ui())
                .map(calculator -> calculator)
                .flatMap(calculator -> {
                    Observable<BigDecimal> observableResult = getCalculatorObservable(calculator);
                    return observableResult;
                });
    }

    private Observable<BigDecimal> getCalculatorObservable(Calculator calculator) {
        Observable<BigDecimal> observableResult = null;
        switch (calculator.getOperator()) {
            case ADD:
                observableResult = calculatorUseCase.add(calculator.getInputValue1(), calculator.getInputValue2());
                break;
            case MUL:
                observableResult = calculatorUseCase.multiply(calculator.getInputValue1(), calculator.getInputValue2());
                break;
            case DIV:
                observableResult = calculatorUseCase.divide(calculator.getInputValue1(), calculator.getInputValue2());
                break;
            case SUB:
                observableResult = calculatorUseCase.subtract(calculator.getInputValue1(), calculator.getInputValue2());
                break;
        }
        return observableResult;
    }

    public void calculatorBinding() {
        calculatorDisposables = new CompositeDisposable();
        calculatorDisposables.add(calculate()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(
                        __ -> response.postValue(Response.loading()))
                .subscribe(
                        result -> {
                            if (response.hasActiveObservers()) {
                                response.postValue(Response.success(result));
                                if (rxBus.hasObservers()) {
                                    rxBus.send(new Calculator.CalculateEvent(result));
                                }
                            }
                        },
                        throwable -> response.setValue(Response.error(throwable)))
        );
    }

    public void calculatorBinding2() {
        calculatorDisposables = new CompositeDisposable();
        calculatorDisposables.add(calculate()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(
                        __ -> response.postValue(Response.loading()))
                .subscribe(
                        result -> {
                            if (response.hasActiveObservers()) {
                                response.postValue(Response.success(result));
                                if (newRxBus.hasObservers()) {
                                    newRxBus.send(new Calculator.CalculateEvent(result));
                                }
                            }
                        },
                        throwable -> response.setValue(Response.error(throwable)))
        );
    }

    public void mainBinding() {
        mainDisposables = new CompositeDisposable();
        ConnectableFlowable<Object> tapEventEmitter = rxBus.asFlowable().publish();
        mainDisposables.add(tapEventEmitter
                .subscribe(event -> {
                            if (event instanceof Calculator.CalculateEvent)
                                response.postValue(Response.success(((Calculator.CalculateEvent) event).getResult()));
                        },
                        throwable -> response.setValue(Response.error(throwable)))
        );
        mainDisposables.add(tapEventEmitter.connect());
    }

    public void mainBinding2() {
        mainDisposables2 = new CompositeDisposable();
        mainDisposables2.add(newRxBus
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> {
                    if (object instanceof Calculator.CalculateEvent) {
                        response.postValue(Response.success(((Calculator.CalculateEvent) object).getResult()));
                    }
                }));
    }

    public void unBinding() {
        onCleared();
    }

    public void handleCalculate(Calculator calculator) {
        calculatorSubject.onNext(calculator);
    }
}