package redrocketz.project.com.appmvvm.mvvm2.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import redrocketz.project.com.appmvvm.mvvm2.rx.newbus.NewRxBus;

/**
 * Created by snyind on 1/18/18.
 */

@Module
public class NewRxBusModule {

    private NewRxBus newRxBus;

    @Named("NewRxBus")
    @Provides
    @Singleton
    NewRxBus provideRxBus() {
        if (newRxBus == null) {
            newRxBus = new NewRxBus();
        }
        return newRxBus;
    }
}
