//Licensed under the Apache License, Version 2.0 (the "License");
//		you may not use this file except in compliance with the License.
//		You may obtain a copy of the License at
//
//		http://www.apache.org/licenses/LICENSE-2.0
//
//		Unless required by applicable law or agreed to in writing, software
//		distributed under the License is distributed on an "AS IS" BASIS,
//		WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//		See the License for the specific language governing permissions and
//		limitations under the License.
package com.rnext.pspe;

import android.graphics.Point;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;

import android.os.Bundle;
import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Map;

import com.henrikrydgard.libnative.NativeActivity;
import com.henrikrydgard.libnative.NativeApp;


import java.lang.reflect.Method;

//import com.google.analytics.tracking.android.EasyTracker;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.RemoteException;

public class ARC extends NativeActivity  {
	//private RewardedVideoAd mAd;
	//InterstitialAd mInterstitialAd;

	private PackageManager packageManager;
	private final Object lockObj = new Object();
	public static int open = 1;
	private long oneAppCacheSize;
	private Context mContext;
	private AppLovinInterstitialAdDialog interstitialAd;
	public boolean mCanShowAd;

	//private final String TAG = PSPEActivity.class.getSimpleName();
	private final Handler mHandler = new Handler();

	private AppLovinAd loadedAd;

	StatFs stat = new StatFs(Environment.getDataDirectory().getAbsolutePath());
	static {


		System.loadLibrary("psppro_jni");
	}





	// Key used by shortcut.
	public static final String SHORTCUT_EXTRA_KEY = "com.rnext.pspe.Shortcuts";

	public static final String TAG = "ARC";

	//private StartAppAd startAppAd = new StartAppAd(this);

	public ARC() {
		super();


	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// In case app launched from homescreen shortcut, get shortcut parameter
		// using Intent extra string. Intent extra will be null if launch normal
		// (from app drawer).


		super.setShortcutParam(getIntent().getStringExtra(SHORTCUT_EXTRA_KEY));



		super.onCreate(savedInstanceState);



		interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance( this ), this );




		//	MobileAds.initialize(this, "ca-app-pub-4825554234903133~1286277464");

		//StartAppSDK.init(this, "210728116", false);
		//StartAppAd.disableSplash();
		//	 mInterstitialAd = new InterstitialAd(this);
		//      mInterstitialAd.setAdUnitId("ca-app-pub-4825554234903133/5008888291");
		// Use an activity context to get the rewarded video instance.
		//	mInterstitialAd.setAdListener(new AdListener() {
		//	@Override
		//	public void onAdLoaded() {
		//		mInterstitialAd.show();
		//	}
		//	});

		//	mInterstitialAd.loadAd(new AdRequest.Builder().build());

		//	mAd = MobileAds.getRewardedVideoAdInstance(this);
		//	mAd.setRewardedVideoAdListener(this);
//		StartAppAd.init(this, getString(R.string.startapp_dev_id), getString(R.string.startapp_app_id));
//		StartAppAd.showSplash(this, savedInstanceState,
//		          new SplashConfig()
//		                 .setTheme(SplashConfig.Theme.GLOOMY)
//
//		                 .setOrientation(SplashConfig.Orientation.AUTO)
//		);


		// This will only ever be used if you have video ads enabled.


		//loadRewardedVideoAd();
	}





//	private void loadRewardedVideoAd() {
//		mAd.loadAd("ca-app-pub-4825554234903133/3149011714", new AdRequest.Builder().build());

//	}


	@SuppressWarnings("deprecation")
	@Override
	public void onStart() {




		super.onStart();


		//interstitialAd.show();


		final SharedPreferences settings =
				getSharedPreferences("localPreferences", MODE_PRIVATE);
		if (settings.getBoolean("isFirstRun", true)) {
			new AlertDialog.Builder(this)
					.setTitle("Alert")
					.setMessage("We use device identifiers to personalize content and ads, provide social networking functionality and analyze our traffic . We also share these identifiers and other information on your device with our partners social networking , advertising and analysis, using this app you consent the use of the device identifiers in this app. See More at http://rnextstudios.fastportatil.com/?page_id=404 .")
					.setNeutralButton("Close message", new DialogInterface.OnClickListener() {



								public void onClick(DialogInterface dialog, int which) {
									settings.edit().putBoolean("isFirstRun", false).commit();
								}



							}
					).show();

		}


//		    	 final SharedPreferences settings =
//		    		        getSharedPreferences("localPreferences", MODE_PRIVATE);
//		    		    if (settings.getBoolean("isFirstRun", true)) {
//		    		      new AlertDialog.Builder(this)
//		    		        .setTitle("Alert")
//		    		        .setMessage("We use device identifiers to personalize content and ads, provide social networking functionality and analyze our traffic . We also share these identifiers and other information on your device with our partners social networking , advertising and analysis, using this app you consent the use of the device identifiers in this app. See More at http://rnextstudios.fastportatil.com/?page_id=404 .")
//		    		        .setNeutralButton("Close message", new DialogInterface.OnClickListener() {
//
//
//
//		    		        	 public void onClick(DialogInterface dialog, int which) {
//		    		               settings.edit().putBoolean("isFirstRun", false).commit();
//		    		        }
//
//
//
//		    		        }
//		    		        ).show();

//		    		    }





//
//		    		    if (mInterstitialAd.isLoaded()) {
//		                    mInterstitialAd.show();
//		                }
//




		//EasyTracker.getInstance(this).activityStart(this);

		ActivityManager activityManger = (ActivityManager) this
				.getSystemService(ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> list = activityManger
				.getRunningAppProcesses();
		if (list != null)
			for (int i = 0; i < list.size(); i++) {
				ActivityManager.RunningAppProcessInfo apinfo = list.get(i);

				String[] pkgList = apinfo.pkgList;
				if (apinfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE) {
					// Process.killProcess(apinfo.pid);
					for (int j = 0; j < pkgList.length; j++) {

						activityManger.killBackgroundProcesses(pkgList[j]);
						activityManger.restartPackage(pkgList[j]);
					}
				}
			}

		try {
			if (packageManager == null) {
				packageManager = mContext.getPackageManager();
			}
			Method localMethod = packageManager.getClass().getMethod("freeStorageAndNotify", Long.TYPE,
					IPackageDataObserver.class);
			Long localLong = getEnvironmentSize() - 1L;
			Object[] arrayOfObject = new Object[2];
			arrayOfObject[0] = localLong;
			localMethod.invoke(packageManager, localLong, mDataObserver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@SuppressWarnings({ "deprecation" })
	private long getEnvironmentSize() {
		File localFile = Environment.getDataDirectory();
		long l1;
		if (localFile == null) {
			return 0L;
		}
		while (true) {
			String str = localFile.getPath();
			StatFs localStatFs = new StatFs(str);
			long l2 = localStatFs.getBlockSize();
			l1 = localStatFs.getBlockCount() * l2;
			return l1;
		}
	}
	private IPackageStatsObserver.Stub mStatsObserver = new IPackageStatsObserver.Stub() {

		@Override
		public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
			synchronized (lockObj) {
				if (succeeded) {
					oneAppCacheSize = pStats.cacheSize;
				} else {
					oneAppCacheSize = 0;
				}
				lockObj.notify();
			}
		}
	};
	private IPackageDataObserver.Stub mDataObserver = new IPackageDataObserver.Stub() {
		@Override
		public void onRemoveCompleted(String packageName, boolean succeeded) throws RemoteException {
			Log.d("AppCleanEngine", "clean " + packageName + " " + succeeded);
		}
	};

	public void setDataObserver(IPackageDataObserver.Stub dataObserver) {
		this.mDataObserver = dataObserver;
	}

	@Override
	public void onUserInteraction() {
		// TODO Auto-generated method stub
		super.onUserInteraction();
		if (open == 1) {
			 // This will only ever be used if you have video ads enabled.
			open =0;
			interstitialAd.show();

			//if (mAd.isLoaded()) {
			//	mAd.show();
			//}



		}
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		 // This will only ever be used if you have video ads enabled.

		//interstitialAd.show();
	}
	@Override
	public void onStop() {



		super.onStop();
	 // This will only ever be used if you have video ads enabled.

		interstitialAd.show();
		//	 if (mInterstitialAd.isLoaded()) {
		//          mInterstitialAd.show();
		//    }
//

		//	    	EasyTracker.getInstance(this).activityStop(this);


	}

	private boolean deleteDirectory(File file, boolean directoryOnly) {
		if (!isExternalStorageWritable()) {
			return false;
		}

		if (file == null || !file.exists() || (directoryOnly && !file.isDirectory())) {
			return true;
		}

		if (file.isDirectory()) {
			final File[] children = file.listFiles();

			if (children != null) {
				for (File child : children) {
					if (!deleteDirectory(child, false)) {
						return false;
					}
				}
			}
		}

		file.delete();

		return true;
	}

	private boolean isExternalStorageWritable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	private void correctRatio(Point sz, float scale) {
		float x = sz.x;
		float y = sz.y;
		float ratio = x / y;
		// Log.i(TAG, "Considering size: " + sz.x + "x" + sz.y + "=" + ratio);
		float targetRatio;
		if (x >= y) {
			targetRatio = 480.0f / 272.0f;
			x = 480.f * scale;
			y = 272.f * scale;
		} else {
			targetRatio = 272.0f / 480.0f;
			x = 272.0f * scale;
			y = 480.0f * scale;
		}
		float correction = targetRatio / ratio;
		// Log.i(TAG, "Target ratio: " + targetRatio + " ratio: " + ratio + " correction: " + correction);
		if (ratio < targetRatio) {
			y *= correction;
		} else {
			x /= correction;
		}
		sz.x = (int)x;
		sz.y = (int)y;
		// Log.i(TAG, "Corrected ratio: " + sz.x + "x" + sz.y);
	}

	@Override
	public void getDesiredBackbufferSize(Point sz) {
		GetScreenSize(sz);
		String config = NativeApp.queryConfig("hwScale");
		int scale;
		try {
			scale = Integer.parseInt(config);
			if (scale == 0) {
				sz.x = 0;
				sz.y = 0;
				return;
			}
		}
		catch (NumberFormatException e) {
			sz.x = 0;
			sz.y = 0;
			return;
		}
		correctRatio(sz, (float)scale);
	}



}