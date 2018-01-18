package redrocketz.project.com.appmvvm.mvvm2.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import redrocketz.project.com.appmvvm.mvvm2.calculator.domain.interactors.CalculatorUseCase;
import redrocketz.project.com.appmvvm.mvvm2.calculator.domain.model.CalculatorRepository;
import redrocketz.project.com.appmvvm.mvvm2.calculator.viewmodel.factory.CalculatorViewModelFactory;
import redrocketz.project.com.appmvvm.mvvm2.rx.SchedulersFacade;
import redrocketz.project.com.appmvvm.mvvm2.rx.bus.RxBus;
import redrocketz.project.com.appmvvm.mvvm2.rx.newbus.NewRxBus;
import redrocketz.project.com.appmvvm.util.CalculatorUtil;

/**
 * Created by Dell on 1/9/2018.
 */

@Module
public class CalculatorModule {

    @Provides
    CalculatorRepository provideCalculatorService() {
        return new CalculatorRepository(new CalculatorUtil());
    }

    @Provides
    CalculatorViewModelFactory provideCalculatorViewModelFactory(CalculatorUseCase ICalculatorUseCase,
                                                                 SchedulersFacade schedulersFacade,
                                                                 @Named("RxBus") RxBus rxBus,
                                                                 @Named("NewRxBus") NewRxBus newRxBus) {
        return new CalculatorViewModelFactory(ICalculatorUseCase, schedulersFacade, rxBus, newRxBus);
    }
}
