package redrocketz.project.com.appmvvm.mvvm2.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import redrocketz.project.com.appmvvm.mvvm2.calculator.view.CalculatorActivity;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class CalculatorBuildersModule {

    @ContributesAndroidInjector(modules = {CalculatorModule.class})
    abstract CalculatorActivity bindCalculatorActivity();
    // Add bindings for other sub-components here
}