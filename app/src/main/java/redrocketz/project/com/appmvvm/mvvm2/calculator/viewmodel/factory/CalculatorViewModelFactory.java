package redrocketz.project.com.appmvvm.mvvm2.calculator.viewmodel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import redrocketz.project.com.appmvvm.mvvm2.calculator.domain.interactors.CalculatorUseCase;
import redrocketz.project.com.appmvvm.mvvm2.calculator.viewmodel.CalculatorViewModel;
import redrocketz.project.com.appmvvm.mvvm2.rx.SchedulersFacade;
import redrocketz.project.com.appmvvm.mvvm2.rx.bus.RxBus;
import redrocketz.project.com.appmvvm.mvvm2.rx.newbus.NewRxBus;

/**
 * Created by Dell on 1/9/2018.
 */

public class CalculatorViewModelFactory implements ViewModelProvider.Factory {

    private final CalculatorUseCase calculatorUseCase;

    private final SchedulersFacade schedulersFacade;

    private final RxBus rxBus;

    private final NewRxBus newRxBus;

    public CalculatorViewModelFactory(CalculatorUseCase calculatorUseCase,
                                      SchedulersFacade schedulersFacade,
                                      RxBus rxBus,
                                      NewRxBus newRxBus) {
        this.calculatorUseCase = calculatorUseCase;
        this.schedulersFacade = schedulersFacade;
        this.rxBus = rxBus;
        this.newRxBus = newRxBus;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CalculatorViewModel.class)) {
            return (T) new CalculatorViewModel(calculatorUseCase, schedulersFacade, rxBus, newRxBus);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
