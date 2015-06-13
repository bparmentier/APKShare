package be.brunoparmentier.apkshare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {
    Context context;
    List<App> apps;

    AppAdapter(Context context, List<App> apps){
        this.context = context;
        this.apps = apps;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_row, viewGroup, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppViewHolder appViewHolder, final int i) {
        appViewHolder.appName.setText(apps.get(i).getName());
        long apkSize = apps.get(i).getApkSize();

        appViewHolder.apkSize.setText(getHumanReadableSize(apkSize));
        appViewHolder.appIcon.setImageDrawable(apps.get(i).getIcon());
        appViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareApkIntent = new Intent();
                shareApkIntent.setAction(Intent.ACTION_SEND);
                shareApkIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apps.get(i).getApkPath())));
                shareApkIntent.setType("application/vnd.android.package-archive");

                context.startActivity(Intent.createChooser(shareApkIntent, context.getString(R.string.share_apk_dialog_title)));
            }
        });
    }

    private String getHumanReadableSize(long apkSize) {
        String humanReadableSize;
        if (apkSize < 1024) {
            humanReadableSize = String.format(
                    context.getString(R.string.app_size_b),
                    (double) apkSize
            );
        } else if (apkSize < Math.pow(1024, 2)) {
            humanReadableSize = String.format(
                    context.getString(R.string.app_size_kib),
                    (double) (apkSize / 1024)
            );
        } else if (apkSize < Math.pow(1024, 3)) {
            humanReadableSize = String.format(
                    context.getString(R.string.app_size_mib),
                    (double) (apkSize / Math.pow(1024, 2))
            );
        } else {
            humanReadableSize = String.format(
                    context.getString(R.string.app_size_gib),
                    (double) (apkSize / Math.pow(1024, 3))
            );
        }
        return humanReadableSize;
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AppViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView appName;
        TextView apkSize;
        ImageView appIcon;

        AppViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.app_row);
            appName = (TextView) itemView.findViewById(R.id.app_name);
            apkSize = (TextView) itemView.findViewById(R.id.apk_size);
            appIcon = (ImageView) itemView.findViewById(R.id.app_icon);
        }
    }
}
