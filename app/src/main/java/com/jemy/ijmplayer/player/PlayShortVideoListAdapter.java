package com.jemy.ijmplayer.player;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jemy.ijmplayer.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.widget.PLVideoTextureView;

import java.util.ArrayList;

/**
 * Created by jemy on 2019/3/4.
 */

public class PlayShortVideoListAdapter extends Adapter<PlayShortVideoListAdapter.ViewHolder> {

    private ArrayList<VideoItem> mItemList;
    private ViewHolder mCurViewHolder;
    private static String TAG="PlayShortVideoListAdapter";

    public PlayShortVideoListAdapter(ArrayList<VideoItem> arrayList) {
        mItemList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.view_short_video, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        VideoItem videoItem = mItemList.get(position);
        holder.videoPath = videoItem.getVideoPath();
        holder.coverPath = videoItem.getCoverPath();

        holder.nameText.setText(videoItem.getName());
        holder.detailText.setText(videoItem.getTime());
        holder.holderRootView.setTag(position);
        holder.videoView.setLooping(false);
        Log.e(TAG,"-----"+holder.videoPath+" pisition "+position);
        holder.videoView.setOnCompletionListener(new PLOnCompletionListener() {
            @Override
            public void onCompletion() {
                Log.e(TAG,"-----complete");
                    if (!mCurViewHolder.videoView.isPlaying()) {
                        Toast.makeText(mCurViewHolder.videoView.getContext(), "onCompletion", Toast.LENGTH_SHORT).show();
                        mCurViewHolder.videoView.start();
                    } else {
                        Toast.makeText(mCurViewHolder.videoView.getContext(), "onCompletion error", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        holder.videoView.setOnErrorListener(new PLOnErrorListener() {
            @Override
            public boolean onError(int i) {
                Toast.makeText(mCurViewHolder.videoView.getContext(),"onError ::" + i,Toast.LENGTH_SHORT).show();
                Log.i(TAG, "videoViewonError: " + i);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e(TAG,"video size:"+mItemList.size());
        return mItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        PLVideoTextureView videoView;
        ImageView coverImage,img_plus;
        TextView nameText;
        TextView detailText;
        String videoPath;
        String coverPath;
        View topView;
        ImageButton pausePlayImage;
        View holderRootView;

        public ViewHolder(View itemView) {
            super(itemView);
            holderRootView = itemView;
            videoView = itemView.findViewById(R.id.video_texture_view);
            coverImage = itemView.findViewById(R.id.cover_image);
            nameText = itemView.findViewById(R.id.name_text);
            detailText = itemView.findViewById(R.id.detail_text);
            pausePlayImage = itemView.findViewById(R.id.image_pause_play);
            topView = itemView.findViewById(R.id.top_view);

            videoView.setAVOptions(createAVOptions());
            videoView.setCoverView(coverImage);
            videoView.setDisplayAspectRatio(PLVideoTextureView.ASPECT_RATIO_PAVED_PARENT);
            View loadingView = itemView.findViewById(R.id.loading_view);
            videoView.setBufferingIndicator(loadingView);
            videoView.setOnInfoListener(new PLOnInfoListener() {
                @Override
                public void onInfo(int i, int i1) {
                    if (i == PLOnInfoListener.MEDIA_INFO_VIDEO_RENDERING_START) {
                        Log.e(TAG,"first video rendering, extra:"+i1);
                    }
                }
            });

            topView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                        pausePlayImage.setVisibility(View.VISIBLE);
                    } else {
                        videoView.start();
                        pausePlayImage.setVisibility(View.GONE);
                    }
                }
            });
            img_plus = itemView.findViewById(R.id.img_plus);
            img_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //popwindow

                }
            });
        }
    }
    public static AVOptions createAVOptions() {
        AVOptions options = new AVOptions();
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 0);
        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_SW_DECODE);
        options.setInteger(AVOptions.KEY_PREFER_FORMAT, AVOptions.PREFER_FORMAT_MP4);
        options.setInteger(AVOptions.KEY_LOG_LEVEL,  0);
        return options;
    }

    public void setCurViewHolder(ViewHolder viewHolder) {
        Log.e(TAG,"viewHolder "+viewHolder);
        mCurViewHolder = viewHolder;
    }
    public void startCurVideoView() {
        if (mCurViewHolder != null && !mCurViewHolder.videoView.isPlaying()) {
            Log.e(TAG,"init video");
            mCurViewHolder.videoView.setVideoPath(mCurViewHolder.videoPath);
            Log.e(TAG,"init ==:"+mCurViewHolder.videoPath);
            mCurViewHolder.videoView.start();
            mCurViewHolder.pausePlayImage.setVisibility(View.GONE);
        }
    }
    public void stopCurVideoView() {
        if (mCurViewHolder != null) {
            mCurViewHolder.videoView.stopPlayback();
        }
    }
    public void pauseCurVideoView() {
        if (mCurViewHolder != null) {
            mCurViewHolder.videoView.pause();
        }
    }
}
