package pinch.bubble

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment() {

    companion object {
        private const val URL_KEY = "urlkey"
        fun newInstance(url: String): ArticleFragment {
            val bundle = Bundle().apply {
                putString(URL_KEY, url)
            }

            return ArticleFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleWebView.loadUrl(arguments?.getString(URL_KEY))
    }
}