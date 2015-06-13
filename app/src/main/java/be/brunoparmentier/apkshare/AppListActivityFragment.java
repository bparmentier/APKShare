package be.brunoparmentier.apkshare;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppListActivityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_list, container, false);

        List<App> apps = new ArrayList<>();

        /* Get app list */
        final PackageManager pm = getActivity().getPackageManager();
        List<ApplicationInfo> packages =  pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            String name;
            /* Use package name if app label is empty */
            if ((name = String.valueOf(pm.getApplicationLabel(packageInfo))).isEmpty()) {
                name = packageInfo.packageName;
            }
            Drawable icon = pm.getApplicationIcon(packageInfo);
            String apkPath = packageInfo.sourceDir;
            long apkSize = new File(packageInfo.sourceDir).length();

            apps.add(new App(name, icon, apkPath, apkSize));
        }

        /* Sort apps alphabetically */
        Collections.sort(apps, new Comparator<App>() {
            @Override
            public int compare(App app1, App app2) {
                return app1.getName().toLowerCase().compareTo(app2.getName().toLowerCase());
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.app_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        AppAdapter appAdapter = new AppAdapter(getActivity(), apps);
        recyclerView.setAdapter(appAdapter);

        return view;
    }
}
