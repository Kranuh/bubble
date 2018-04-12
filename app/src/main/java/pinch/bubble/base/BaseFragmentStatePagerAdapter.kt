package pinch.bubble.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup

/**
 * Created by timkranen on 25/08/2017.
 */
abstract class BaseFragmentStatePagerAdapter<T : Fragment> (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val fragments = mutableMapOf<Int, T>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as T
        fragments.put(position, fragment)
        return fragment
    }

    override fun getItem(position: Int): T {
        return fragments.getOrPut(position, { createFragment(position) })
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        super.destroyItem(container, position, obj)
        fragments.remove(position)
        destroyFragment(obj as T)
    }

    protected open fun destroyFragment(fragment: T) {
        // no op
    }

    protected abstract fun createFragment(position: Int): T

    override fun getCount(): Int {
        return fragments.size
    }


}