package com.adcvn.adcsaleagrotech.checkversionremote;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.adcvn.adcsaleagrotech.common.SystemDialog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class GooglePlayAppVersion extends AsyncTask<String, Void, String> {
    private Dialog pd;
    private final String packageName;
    private Context context;
    private String currentVersion;

    public GooglePlayAppVersion(String packageName, String currentVersion, Context context) {
        this.packageName = packageName;
        this.context = context;
        this.currentVersion = currentVersion;
        pd = SystemDialog.showProcessDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        return getPlayStoreAppVersion(String.format("https://play.google.com/store/apps/details?id=%s", packageName));
    }

    @Override
    protected void onPostExecute(String newVersion) {
        if (!currentVersion.equals(newVersion)){
           openLinkUpdateAppStore();
        }
        pd.dismiss();
    }
    private static String getPlayStoreAppVersion(String appUrlString) {
        String
                currentVersion_PatternSeq = "<div[^>]*?>Current\\sVersion</div><span[^>]*?>(.*?)><div[^>]*?>(.*?)><span[^>]*?>(.*?)</span>",
                appVersion_PatternSeq = "htlgb\">([^<]*)</s";
        try {
            URLConnection connection = new URL(appUrlString).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder sourceCode = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) sourceCode.append(line);
                String versionString = getAppVersion(currentVersion_PatternSeq, sourceCode.toString());
                if (versionString == null) return null;
                return getAppVersion(appVersion_PatternSeq, versionString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String getAppVersion(String patternString, String input) {
        try {
            Pattern pattern = Pattern.compile(patternString);
            if (pattern == null) return null;
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) return matcher.group(1);
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    // mở đường dẫn cài đặt app
    private void openLinkUpdateAppStore(){
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }
}