package catchdesign.co.nz.androidtest.mvp

import android.os.Bundle
import android.support.annotation.NonNull
import catchdesign.co.nz.androidtest.AppComponent
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import io.reactivex.subjects.BehaviorSubject

enum class LifecycleEvent {
    VIEW_ATTACHED,
    VIEW_DETACHED,
}

abstract class MvpPresenter<View>(val appComponent: AppComponent) {

    private val lifecycle = BehaviorSubject.create<LifecycleEvent>()
    var view: View? = null

    fun onRestoreState(@NonNull state: Bundle?) {
    }

    fun attachView(view: View) {
        this.view = view
        lifecycle.onNext(LifecycleEvent.VIEW_ATTACHED)

        if (canInvokeOnViewAttached()) {
            onViewAttached()
        }
    }

    protected fun <T> bindtoLifeCycle(): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(lifecycle, LifecycleEvent.VIEW_DETACHED)
    }

    open fun onViewAttached(){

    }

    open fun onViewDetached(){

    }

    private fun isViewAttached(): Boolean {
        return view != null
    }

    private fun canInvokeOnViewAttached(): Boolean {
        return isViewAttached()
    }

    open fun onSaveState(outState: Bundle?) {
    }

    fun detachView() {
        onViewDetached()
        view = null
        lifecycle.onNext(LifecycleEvent.VIEW_DETACHED)
    }
}
