package redrocketz.project.com.appmvvm.mvvm2.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import redrocketz.project.com.appmvvm.mvvm2.app.CalculatorApp;

/**
 * Created by Dell on 1/9/2018.
 */

@Module
public class AppModule {

    @Provides
    Context provideContext(CalculatorApp application) {
        return application.getApplicationContext();
    }
}
