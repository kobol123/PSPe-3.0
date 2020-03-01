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

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Bundle;
import android.os.Environment;

/**
 * This class will respond to android.intent.action.CREATE_SHORTCUT intent from
 * launcher homescreen. Register this class in AndroidManifest.xml.
 */
public class ShortcutActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Show file selector dialog here.
		SimpleFileChooser fileDialog = new SimpleFileChooser(this,
				Environment.getExternalStorageDirectory(),
				onFileSelectedListener);
		fileDialog.showDialog();
	}
	

	// Create shortcut as response for ACTION_CREATE_SHORTCUT intent.
	private void respondToShortcutRequest(String path) {
		// This is Intent that will be sent when user execute our shortcut on
		// homescreen. Set our app as target Context. Set Main activity as
		// target class. Add any parameter to extra.
		Intent shortcutIntent = new Intent(this, PSPEActivity.class);
		shortcutIntent.putExtra(PSPEActivity.SHORTCUT_EXTRA_KEY, path);

		// This is Intent that will be returned by this method, as response to
		// ACTION_CREATE_SHORTCUT. Wrap shortcut intent inside this intent.
		Intent responseIntent = new Intent();
		responseIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		responseIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getResources()
				.getString(R.string.app_name));
		ShortcutIconResource iconResource = Intent.ShortcutIconResource
				.fromContext(this, R.drawable.ic_launcher);
		responseIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				iconResource);

		setResult(RESULT_OK, responseIntent);

		// Must call finish for result to be returned immediately
		finish();
	}

	// Event when a file is selected on file dialog.
		private SimpleFileChooser.FileSelectedListener onFileSelectedListener = new SimpleFileChooser.FileSelectedListener() {
			public void onFileSelected(File file) {
				// create shortcut using file path
				respondToShortcutRequest(file.getAbsolutePath());
			}
		};

}
