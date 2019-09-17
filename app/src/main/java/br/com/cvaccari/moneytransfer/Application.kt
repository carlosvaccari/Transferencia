package br.com.cvaccari.moneytransfer

import android.app.Application
import br.com.cvaccari.moneytransfer.data.remote.DataFacade
import br.com.cvaccari.moneytransfer.main.MainContract
import br.com.cvaccari.moneytransfer.main.MainPresenter
import br.com.cvaccari.moneytransfer.sendmoney.SendMoneyContract
import br.com.cvaccari.moneytransfer.sendmoney.SendMoneyPresenter
import br.com.cvaccari.moneytransfer.report.ReportContract
import br.com.cvaccari.moneytransfer.report.ReportFacade
import br.com.cvaccari.moneytransfer.report.ReportPresenter
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
            bind<ReportFacade>() with provider {
                ReportFacade()
            }
            bind<SharedPrefsStorage>() with provider {
                SharedPrefsStorage(this@Application)
            }
            bind<SendMoneyContract.Presenter>() with provider {
                SendMoneyPresenter(instance(), instance())
            }
            bind<MainContract.Presenter>() with provider {
                MainPresenter(instance(), instance())
            }
            bind<ReportContract.Presenter>() with provider {
                ReportPresenter(instance(), instance(), instance())
            }
        }
    }
}