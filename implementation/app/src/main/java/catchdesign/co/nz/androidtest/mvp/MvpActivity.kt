package catchdesign.co.nz.androidtest.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import catchdesign.co.nz.androidtest.AppComponent

abstract class MvpActivity<View, Presenter : MvpPresenter<View>> : AppCompatActivity() {

    protected var presenter: Presenter? = null

    val appComponent: AppComponent
        get() = application as AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (presenter == null) {
            presenter = createPresenter()
            if (savedInstanceState != null) {
                presenter!!.onRestoreState(savedInstanceState)
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        presenter!!.attachView(this as View)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        presenter!!.onSaveState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter!!.detachView()
    }

    abstract fun createPresenter(): Presenter?

}