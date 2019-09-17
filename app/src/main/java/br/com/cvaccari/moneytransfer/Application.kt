package br.com.cvaccari.moneytransfer

import android.app.Application
import br.com.cvaccari.moneytransfer.data.remote.DataFacade
import br.com.cvaccari.moneytransfer.main.MainContract
import br.com.cvaccari.moneytransfer.main.MainPresenter
import br.com.cvaccari.moneytransfer.moneytransference.MoneyTransferenceContract
import br.com.cvaccari.moneytransfer.moneytransference.MoneyTransferencePresenter
import br.com.cvaccari.moneytransfer.transferencehistory.ExtractContract
import br.com.cvaccari.moneytransfer.transferencehistory.ExtractFacade
import br.com.cvaccari.moneytransfer.transferencehistory.ExtractPresenter
import br.com.cvaccari.moneytransfer.utils.SharedPrefsStorage
import org.kodein.di.KodeinAware
import org.kodein.di.conf.ConfigurableKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class Application() : Application(), KodeinAware {

    override val kodein = ConfigurableKodein()

    init {
        kodein.addConfig {
            bind<DataFacade>() with provider {
                DataFacade()
            }
            bind<ExtractFacade>() with provider {
                ExtractFacade()
            }
            bind<SharedPrefsStorage>() with provider {
                SharedPrefsStorage(this@Application)
            }
            bind<MoneyTransferenceContract.Presenter>() with provider {
                MoneyTransferencePresenter(instance(), instance())
            }
            bind<MainContract.Presenter>() with provider {
                MainPresenter(instance(), instance())
            }
            bind<ExtractContract.Presenter>() with provider {
                ExtractPresenter(instance(), instance(), instance())
            }
        }
    }
}