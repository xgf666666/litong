import android.support.annotation.CallSuper
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import com.xx.baseuilibrary.R
import com.xx.baseuilibrary.adapter.BaseRecycleAdapter
import com.xx.baseuilibrary.adapter.BaseViewHolder
import com.xx.baseuilibrary.adapter.XxRecycleViewScrollListener
import com.xx.baseuilibrary.mvp.contract.BaseMvpRecycleViewContract
import com.xx.baseuilibrary.mvp.lcec2.BaseMvpLcecFragment
import com.xx.baseuilibrary.mvp.presenter.BaseMvpRecycleViewPresenter
import com.xx.baseutilslibrary.common.XxResourceUtil

/**
 * SeasonPlayActivity
 * (。・∀・)ノ
 * Describe： 封装了下拉刷新上拉加载更多的RecycleViewFragment
 * Created by 雷小星🍀 on 2017/8/3 15:40.
 */

abstract class BaseMvpRecycleViewFragment<E> : BaseMvpLcecFragment<List<E>, BaseMvpRecycleViewContract.Model<E>, BaseMvpRecycleViewContract.View<E>, BaseMvpRecycleViewPresenter<E, BaseMvpRecycleViewContract.Model<E>>>(), BaseMvpRecycleViewContract.View<E> {
    val swipeRefreshLayout by lazy {
        getContentView() as SwipeRefreshLayout
    }


    var recyclerView: RecyclerView? = null
        private set
    /**
     * 获取适配器
     *
     * @return
     */
    protected var adapter: BaseRecycleAdapter<E, out BaseViewHolder<E>>? = null
        private set

    /**
     * 获取每页数据条数
     *
     * @return 每页数据条数
     */
    protected abstract val listLimit: Int

    protected abstract val layoutManager: RecyclerView.LayoutManager

    override fun createPresenter(): BaseMvpRecycleViewPresenter<E, BaseMvpRecycleViewContract.Model<E>> {
        return BaseMvpRecycleViewPresenter(createModel())
    }

    /**
     * 创建Adapter
     *
     * @return Adapter
     */
    protected abstract fun createAdapter(): BaseRecycleAdapter<E, out BaseViewHolder<E>>

    /**
     * 创建Model
     *
     * @return Model
     */
    protected abstract fun createModel(): BaseMvpRecycleViewContract.Model<E>


    public override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_list
    }

    @CallSuper
    override fun initView(view: View?) {
        super.initView(view)

        recyclerView = view!!.findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = layoutManager

        adapter = createAdapter()
        if (adapter == null) {
            throw NullPointerException("请构造你的适配器")
        }
        adapter!!.setLoadMoreEnable(true)//开启加载更多
        adapter!!.limit = listLimit
        recyclerView!!.adapter = adapter



        swipeRefreshLayout.setColorSchemeResources(XxResourceUtil.getColorId(mContext, "colorAccent"))
    }


    public override fun initData() {
        loadData(true)
    }

    @CallSuper
    public override fun initEvent(view: View?) {
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener {
            if ((!presenter.isLoading!!)) {
                loadData(true)
            } else {
                showToast("数据加载中,请稍后")
                swipeRefreshLayout.isRefreshing = false
            }
        }
        //上拉加载更多
        recyclerView!!.addOnScrollListener(object : XxRecycleViewScrollListener() {
            override fun getLimit(): Int {
                return listLimit
            }

            override fun onLoadMore() {
                if ((!presenter.isLoading!!) && !adapter!!.isLoadAll) {
                    loadData(false)
                }
            }
        })
    }

    override fun loadData(refresh: Boolean) {
        presenter.loadData(refresh)
    }

    override fun setData(data: List<E>?) {
        showContent()
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
        adapter!!.upDate(data)
    }

    override fun upDateAdd(entities: List<E>) {
        adapter!!.upDateAdd(entities)
    }
}
