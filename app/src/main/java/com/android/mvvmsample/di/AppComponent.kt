package com.android.mvvmsample.di

import android.app.Application
import com.android.data.di.DataModule
import com.android.domain.di.DomainModule
import com.android.mvvmsample.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        DispatchersModule::class,
        ViewModelFactoryModule::class,
        ViewModelsModule::class,
        ActivitiesModule::class,
        FragmentsModule::class,
        SharedModule::class,
        DomainModule::class,
        DataModule::class // TODO: This is forcing me to add a dependency to data, which is not good!
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainApplication: MainApplication)
}
