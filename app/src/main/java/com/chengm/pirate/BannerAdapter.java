package com.chengm.pirate;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chengm.pirate.widget.CornerLayout;
import com.chengm.pirate.widget.ScaleGesture;
import com.chengm.pirate.widget.ShareAnim;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author : ChenWJ
 * date : 2019/11/9 11:34
 * description : 适配器
 */
public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {

    private List<Banner> data;
    private Activity context;

    public BannerAdapter(Activity context, List<Banner> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_banner, parent, false);
        final BannerViewHolder holder = new BannerViewHolder(view);

        new ScaleGesture(context)
                .setCustomScale(0.95f)
                .bindToView(holder.csLayout, holder.csLayout)
                .onClick(new ScaleGesture.IClickLis() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, TargetActivity.class);
                        intent.putExtra("data", data.get(holder.getAdapterPosition()));

                        Pair<String, View> pair1 = new Pair<>("image", (View) holder.image);
                        Pair<String, View> pair2 = new Pair<>("title", (View) holder.textView);
                        Pair<String, View> pair3 = new Pair<>("csLayout", (View) holder.csLayout);
                        intent = ShareAnim.createIntentDef(intent, pair1, pair2, pair3);
                        context.startActivity(intent);
                    }
                });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BannerViewHolder holder, final int position) {
        holder.image.setImageResource(data.get(position).getResId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textView;
        CornerLayout csLayout;

        public BannerViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text_title);
            csLayout = itemView.findViewById(R.id.cslayout);
        }
    }
}
