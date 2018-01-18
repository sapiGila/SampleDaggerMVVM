package redrocketz.project.com.appmvvm.mvvm2.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import redrocketz.project.com.appmvvm.mvvm2.calculator.view.CalculatorActivity;
import redrocketz.project.com.appmvvm.mvvm2.calculator.view.MainActivity;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class HomeBuildersModule {

    @ContributesAndroidInjector(modules = {CalculatorModule.class})
    abstract MainActivity bindMainActivity();
    // Add bindings for other sub-components here
}