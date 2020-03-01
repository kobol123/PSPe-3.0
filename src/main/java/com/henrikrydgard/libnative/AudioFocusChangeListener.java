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


package com.henrikrydgard.libnative;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;

public class AudioFocusChangeListener implements OnAudioFocusChangeListener{
	// not used right now, but we may need to use it sometime. So just store it
	// for now.
	private boolean hasAudioFocus = false;
	
	public void onAudioFocusChange(int focusChange) {
		switch (focusChange){
		case AudioManager.AUDIOFOCUS_GAIN:
			hasAudioFocus = true;
			break;
			
		case AudioManager.AUDIOFOCUS_LOSS:
			hasAudioFocus = false;
			break;
		}
	}

	public boolean hasAudioFocus() {
		return hasAudioFocus;
	}
}
