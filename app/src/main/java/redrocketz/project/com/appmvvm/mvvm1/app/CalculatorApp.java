package redrocketz.project.com.appmvvm.mvvm1.app;

import android.app.Application;
import android.support.annotation.NonNull;

import redrocketz.project.com.appmvvm.model.CalculatorModel;
import redrocketz.project.com.appmvvm.mvvm1.model.CalculatorRepository;
import redrocketz.project.com.appmvvm.mvvm1.schedulers.ISchedulerProvider;
import redrocketz.project.com.appmvvm.mvvm1.schedulers.SchedulerProvider;
import redrocketz.project.com.appmvvm.util.CalculatorUtil;
import redrocketz.project.com.appmvvm.mvvm1.viewmodel.CalculatorViewModel;

/**
 * Created by Dell on 1/8/2018.
 */

public class CalculatorApp extends Application {

    @NonNull
    private final CalculatorModel calculatorModel;

    public CalculatorApp() {
        calculatorModel = new CalculatorRepository(new CalculatorUtil());
    }

    @NonNull
    public CalculatorModel getDataModel() {
        return calculatorModel;
    }

    @NonNull
    public ISchedulerProvider getSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

    @NonNull
    public CalculatorViewModel getViewModel() {
        return new CalculatorViewModel(getDataModel(), getSchedulerProvider());
    }
}
