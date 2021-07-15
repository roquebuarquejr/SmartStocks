package com.roquebuarque.smartstocks.views

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.BuildConfig
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Sets [ViewBinding] root view as content view for provided dialog,
 * returning the [ViewBinding] builder style to allow for one-line initialization like this:
 *
 *     MyAwesomeBinding.inflate(layoutInflater).setContentView(this)
 */
fun <T : ViewBinding> T.setContentView(dialog: Dialog): T {
	dialog.setContentView(root)

	return this
}

/**
 * [Context] of root view for this [ViewBinding].
 */
val ViewBinding.context: Context
	get() = root.context

/**
 * Create and attach root of [ViewBinding] provided by [inflater] to the [BaseActivity]
 * when it is created. No further actions other than initialization the value with a delegate is needed.
 *
 * Example:
 *     private val viewBinding by viewBinding { ActivityMenuBinding.inflate(it) }
 *
 * Do not use
 *     private val viewBinding by viewBinding(ActivityMenuBinding::inflate)
 * as it will trip our lint unused resource detection.
 *
 * It sucks, but for now there's not much we can do.
 * The bug will soon be filed for that, so hopefully, it'll work in the future!
 */
fun <T : ViewBinding> AppCompatActivity.viewBinding(inflater: (LayoutInflater) -> T): ReadOnlyProperty<AppCompatActivity, T> {
	val delegate = LazyViewBindingDelegate(factory = inflater)

	lifecycle.addObserver(object : LifecycleObserver {
		@Suppress("unused")
		@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
		fun onCreate() {
			delegate.getOrCreateBinding(this@viewBinding)

			lifecycle.removeObserver(this)
		}
	})

	return delegate
}

private class LazyViewBindingDelegate<T : ViewBinding>(
	private val factory: (LayoutInflater) -> T
) : ReadOnlyProperty<AppCompatActivity, T> {

	private var cached: T? = null

	override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
		return getOrCreateBinding(activity = thisRef)
	}

	/**
	 * Get cached ViewBinding or create it from the scratch, attaching it to Activity.
	 */
	fun getOrCreateBinding(activity: Activity): T {
		return cached?.also {
			enforceNotDetached(it)
		} ?: createViewBinding(activity)
	}

	private fun createViewBinding(activity: Activity): T {
		enforceFirstTimeAccessOnMainThread()
		enforceFirstTimeAccessContextAttached(activity)

		if (BuildConfig.DEBUG) {
			diagnoseRedundantSetContentView(activity)
		}

		val layoutInflater = activity.layoutInflater
		val viewBinding = factory(layoutInflater)
		activity.setContentView(viewBinding.root)
		cached = viewBinding
		return viewBinding
	}

	private fun diagnoseRedundantSetContentView(activity: Activity) {
		val rootView = activity.findViewById<ViewGroup>(android.R.id.content)
		val childCount = rootView?.childCount ?: 0

		if (childCount > 0) {
			throw IllegalStateException(
				"setContentView() was already called, and so this Activity already has a content view. " +
						"Please remove the call to setContentView to avoid doing unnecessary View hierarchy work. " +
						"Please note: this crash will only happen in debug build."
			)
		}
	}

	private fun enforceNotDetached(binding: T) {
		if (binding.root.parent == null) {
			throw IllegalStateException("ViewBinding root was detached after creation - have you removed setContentView call?")
		}
	}

	private fun enforceFirstTimeAccessOnMainThread() {
		val looper = Looper.myLooper()

		if (looper !== Looper.getMainLooper()) {
			throw IllegalStateException("First-time access to ViewBinding on background thread is forbidden")
		}
	}

	private fun enforceFirstTimeAccessContextAttached(activity: Activity) {
		if (activity.baseContext == null) {
			throw IllegalStateException("First-time access to ViewBinding must happen only after Context if attached to Activity")
		}
	}
}