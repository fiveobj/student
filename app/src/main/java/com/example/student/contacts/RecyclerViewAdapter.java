package com.example.student.contacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student.R;
import com.example.student.customclass.RadiusImageView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleHolder>
        implements RecyclerItemView.onSlidingButtonListener{

    private Context context;

    private List<Bitmap> dataImage;    //头像（谁的头像）
    private List<String> dataTitle;     //标题（谁的消息）
    private List<String> datasContent;  //内容（消息内容）
    private List<String> datasTime;     //时间（消息时间）

    private onSlidingViewClickListener onSvcl;

    private RecyclerItemView recyclers;


    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public RecyclerViewAdapter(Context context,
                               List<Bitmap> dataImage,
                               List<String> dataTitle,
                               List<String> datasContent,
                               List<String> datasTime) {
        this.context = context;
        this.dataImage = dataImage;
        this.dataTitle = dataTitle;
        this.datasContent = datasContent;
        this.datasTime = datasTime;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.SimpleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_fragmentitem_iten, parent, false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.SimpleHolder holder, int position) {
        holder.image.setImageBitmap(dataImage.get(position));
        holder.title.setText(dataTitle.get(position));
        holder.content.setText(datasContent.get(position));
        holder.time.setText(datasTime.get(position));
        holder.layout_left.getLayoutParams().width = RecyclerUtils.getScreenWidth(context);

        holder.layout_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"做出操作，进入新的界面或弹框",Toast.LENGTH_SHORT).show();
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    //获得布局下标（点的哪一个）
                    int subscript = holder.getLayoutPosition();
                    onSvcl.onItemClick(view, subscript);
                }
            }
        });
        /*holder.other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"其他："+position,Toast.LENGTH_SHORT).show();
            }
        });*/
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"删除了："+position,Toast.LENGTH_SHORT).show();
                int subscript = holder.getLayoutPosition();
                onSvcl.onDeleteBtnCilck(view,subscript);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataImage.size();
    }

    @Override
    public void onMenuIsOpen(View view) {
        recyclers = (RecyclerItemView) view;
    }

    @Override
    public void onDownOrMove(RecyclerItemView recycler) {
        if(menuIsOpen()){
            if(recyclers != recycler){
                closeMenu();
            }
        }
    }
    class SimpleHolder extends  RecyclerView.ViewHolder {

        public RadiusImageView image;
        public TextView title;
        public TextView content;
        public TextView time;
        public TextView other;
        public TextView delete;
        public LinearLayout layout_left;
        public SimpleHolder(View view) {
            super(view);

            image = (RadiusImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.tv_name);
            content = (TextView) view.findViewById(R.id.tv_detail);
            time = (TextView) view.findViewById(R.id.tv_time);
           // other = (TextView) view.findViewById(R.id.other);
            delete = (TextView) view.findViewById(R.id.delete);
            layout_left = (LinearLayout) view.findViewById(R.id.layout_left);

            ((RecyclerItemView)view).setSlidingButtonListener(RecyclerViewAdapter.this);
        }
    }

    //删除数据
    public void removeData(int position){
        dataImage.remove(position);
//        notifyDataSetChanged();
        notifyItemRemoved(position);

    }

    //关闭菜单
    public void closeMenu() {
        recyclers.closeMenu();
        recyclers = null;

    }

    // 判断是否有菜单打开
    public Boolean menuIsOpen() {
        if(recyclers != null){
            return true;
        }
        return false;
    }

    //设置在滑动侦听器上
    public void setOnSlidListener(onSlidingViewClickListener listener) {
        onSvcl = listener;
    }

    // 在滑动视图上单击侦听器
    public interface onSlidingViewClickListener {
        void onItemClick(View view, int position);
        void onDeleteBtnCilck(View view, int position);
    }

}
