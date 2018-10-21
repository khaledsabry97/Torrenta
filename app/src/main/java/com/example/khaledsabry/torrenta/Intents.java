package com.example.khaledsabry.torrenta;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Intents {
    private static final Intents ourInstance = new Intents();

    public static Intents getInstance() {
        return ourInstance;
    }

    private Intents() {
    }


    //to share the magnet link with your friend on any app
    public void shareMagnetLink(String magnet)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(magnet));
        MainActivity.getActivity().startActivity(Intent.createChooser(intent, "send to"));

    }

    //pass the magnet link to download through a torrent app
    public void downloadMagnetLink(String magnet)
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setType("application/x-bittorrent");
        i.setData(Uri.parse(magnet));
        Intent intent = generateTorrentIntent(MainActivity.getActivity().getApplicationContext(), i);

        MainActivity.getActivity().startActivity(Intent.createChooser(intent, "send to"));
    }

    /**
     * search for the apps that downloads the torrent magnet
     *
     * @param context the context of the app
     * @param intent  the intent that specify which app we look for and send with it the data
     * @return an intent to call it with founded apps
     */
    private Intent generateTorrentIntent(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            for (ResolveInfo r : resolveInfo) {
                Intent progIntent = (Intent) intent.clone();
                String packageName = r.activityInfo.packageName;

                progIntent.setPackage(packageName);
                if (r.activityInfo.packageName.toLowerCase().contains("torrent"))
                    targetedShareIntents.add(progIntent);

            }
            if (targetedShareIntents.size() > 0) {
                Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0),
                        "Select app to download");

                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                        targetedShareIntents.toArray(new Parcelable[]{}));

                return chooserIntent;
            }
        }
        return null;
    }

}
