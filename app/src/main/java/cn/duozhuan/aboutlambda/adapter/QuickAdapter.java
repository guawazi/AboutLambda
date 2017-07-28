package cn.duozhuan.aboutlambda.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.duozhuan.aboutlambda.R;

/**
 * Created by Administrator on 2017/3/23.
 */

public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH> {

    private List<T> mDatas;
    private RecyclerView recyclerView;
    private ItemClickListener itemClickListener;

    public QuickAdapter() {
        mDatas = new ArrayList<>();
    }

    public QuickAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    protected LinearLayoutManager getLayoutManager(RecyclerView recyclerView) { // 这里提供了默认的layoutManager,不喜欢可以重写
        return new LinearLayoutManager(recyclerView.getContext());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(getLayoutManager(recyclerView));
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    public abstract int getLayoutId(int viewType); // 根据不同的viewType的值,得到对应的布局,子类实现

    @Override
    public int getItemViewType(int position) { // 要实现多布局,子类还要自己实现此方法,该方法默认返回 0
        return super.getItemViewType(position);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) { // viewType 多布局的时候用到
        return VH.get(parent, getLayoutId(viewType)); // 获取不同布局的VH
    }


    public abstract void convert(VH holder, T data, int position); // 绑定数据

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        convert(holder, mDatas.get(position), position);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, position);
            }
        });
    }

    public void addOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void addFirst(List<T> list){
        mDatas.addAll(0,list);
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        mDatas.addAll(list);
        notifyItemInserted(list.size());
    }

    public void clean(){
        mDatas.clear();
        notifyDataSetChanged();
    }

    public static class VH extends RecyclerView.ViewHolder {

        private View mConvertView;
        private SparseArray<View> mViews;

        public VH(View itemView) { // 在多布局中,每一个不同的布局都对应一个VH
            super(itemView);
            mConvertView = itemView;
            mViews = new SparseArray<>(); // 用来存储该布局中所有的view,以便复用
        }

        public static VH get(ViewGroup parent, int layoutId) {  // 获取不同布局的VH
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(view);
        }

        public <T extends View> T getView(int id) { // 先在mViews里面查找,没有就去findViewById,并放入mViews
            View view = mViews.get(id);
            if (view == null) {
                view = mConvertView.findViewById(id);
                mViews.put(id, view);
            }
            return ((T) view);
        }

        public void setText(int id, String value) { // 给布局中的textView 设置text可直接调用此方法
            TextView view = getView(id);
            view.setText(value);
        }

        public void setImage(int id, String imageUrl) { // 给布局中的imageView 设置image可直接调用此方法,默认曹勇Glide 实现
            ImageView view = getView(id);
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .asBitmap()
                    .placeholder(R.mipmap.ic_launcher) // 设置默认图片
                    .error(R.mipmap.ic_launcher)  // 加载失败图片
                    .into(view);
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
