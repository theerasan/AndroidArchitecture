package news.ta.com.news.feature

import android.content.Context
import news.ta.com.news.di.ContextModule
import news.ta.com.news.di.DaggerApplicationComponent
import news.ta.com.news.di.TestNetworkModule
import news.ta.com.news.di.TestRepositoryModule
import news.ta.com.news.di.TestServicesModule
import org.amshove.kluent.mock

class TestEnvironmentBuilder {
    companion object {
        val context = mock<Context>()
        val repositories = TestRepositoryModule()
        val services = TestServicesModule()
        val network = TestNetworkModule()

        fun buildEnvironment() {
            NewsApplication.applicationComponent = DaggerApplicationComponent.builder()
                    .contextModule(ContextModule(context))
                    .servicesModule(services)
                    .repositoryModule(repositories)
                    .build()
        }
    }
}