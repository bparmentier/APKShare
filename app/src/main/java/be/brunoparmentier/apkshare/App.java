package be.brunoparmentier.apkshare;

import android.graphics.drawable.Drawable;

public final class App {
    private String name;
    private Drawable icon;
    private String apkPath;
    private long apkSize;

    public App(String name, Drawable icon, String apkPath, long apkSize) {
        this.name = name;
        this.icon = icon;
        this.apkPath = apkPath;
        this.apkSize = apkSize;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getApkPath() {
        return apkPath;
    }

    public long getApkSize() {
        return apkSize;
    }
}
