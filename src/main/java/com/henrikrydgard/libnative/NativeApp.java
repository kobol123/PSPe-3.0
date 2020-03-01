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




// Note that the display* methods are in NativeRenderer.java 

public class NativeApp {
	public final static int DEVICE_ID_DEFAULT = 0; 
	public final static int DEVICE_ID_KEYBOARD = 1; 
	public final static int DEVICE_ID_MOUSE = 2; 
	public final static int DEVICE_ID_PAD_0 = 10; 
	
	public final static int DEVICE_TYPE_MOBILE = 0;
	public final static int DEVICE_TYPE_TV = 1;
	public final static int DEVICE_TYPE_DESKTOP = 2;

	public static native void init(String model, int deviceType, int xres, int yres, String languageRegion, String apkPath, String dataDir, String externalDir, String libraryDir, String shortcutParam, String installID, int androidVersion);
	public static native void initacc();
	public static native void audioInit();
	public static native void audioShutdown();
	public static native void audioConfig(int optimalFramesPerBuffer, int optimalSampleRate);
	
	public static native boolean isLandscape();
	public static native boolean isAtTopLevel();

	// These have Android semantics: Resume is always called on bootup, after init
	public static native void pause();
	public static native void resume();
	
	//Boot file from arc
	public static native void boot (String FileUri);
	
	// There's not really any reason to ever call shutdown as we can recover from a killed activity.
	public static native void shutdown();

	public static native boolean keyDown(int deviceId, int key, boolean isRepeat);
	public static native boolean keyUp(int deviceId, int key);

	public static native void beginJoystickEvent();
	public static native void joystickAxis(int deviceId, int axis, float value);
	public static native void endJoystickEvent();
	
	public static native boolean mouseWheelEvent(float x, float y);

	// will only be called between init() and shutdown()
	public static native int audioRender(short[] buffer);

	// Sensor/input data. These are asynchronous, beware!
	public static native boolean touch(float x, float y, int data, int pointerId);

	public static native boolean accelerometer(float x, float y, float z , int button);

	public static native boolean accelerometer1(float x, float y, float z , int button);
	
	public static native void sendMessage(String msg, String arg);
	
	public static native String queryConfig(String queryName);
}
 