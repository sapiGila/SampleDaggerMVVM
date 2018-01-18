package redrocketz.project.com.appmvvm.mvvm2.di;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import redrocketz.project.com.appmvvm.mvvm2.app.CalculatorApp;

@Singleton
@Component(modules = {
        /* Use AndroidInjectionModule.class if you're not using support library */
        AndroidSupportInjectionModule.class,
        AppModule.class,
        RxBusModule.class,
        NewRxBusModule.class,
        HomeBuildersModule.class,
        CalculatorBuildersModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(CalculatorApp application);
        AppComponent build();
    }

    void inject(CalculatorApp app);
}