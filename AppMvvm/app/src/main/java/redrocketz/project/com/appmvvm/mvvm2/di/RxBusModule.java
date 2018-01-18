package redrocketz.project.com.appmvvm.mvvm2.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import redrocketz.project.com.appmvvm.mvvm2.rx.bus.RxBus;

/**
 * Created by snyind on 1/18/18.
 */

@Module
public class RxBusModule {

    private RxBus rxBus;

    @Named("RxBus")
    @Provides
    @Singleton
    RxBus provideRxBus() {
        if (rxBus == null) {
            rxBus = new RxBus();
        }
        return rxBus;
    }
}
