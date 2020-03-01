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

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.InputDevice;
import android.view.InputDevice.MotionRange;
import android.view.KeyEvent;
import android.view.MotionEvent;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class InputDeviceState {
	private static final String TAG = "InputDeviceState";
	
	private static final int deviceId = NativeApp.DEVICE_ID_PAD_0;

	private InputDevice mDevice;
	private int[] mAxes;

	InputDevice getDevice() { return mDevice; }

	@TargetApi(19)
	void logAdvanced(InputDevice device) {
	     Log.i(TAG, "Vendor ID:" + device.getVendorId() + " productId: " + device.getProductId());
	}
	
	public InputDeviceState(InputDevice device) {
	     mDevice = device;
	     int numAxes = 0;
	     for (MotionRange range : device.getMotionRanges()) {
	          if ((range.getSource() & InputDevice.SOURCE_CLASS_JOYSTICK) != 0) {
	               numAxes += 1;
	          }
	     }

	     mAxes = new int[numAxes];

	     int i = 0;
	     for (MotionRange range : device.getMotionRanges()) {
	          if ((range.getSource() & InputDevice.SOURCE_CLASS_JOYSTICK) != 0) {
	               mAxes[i++] = range.getAxis();
	          }
	     }
	     
	     Log.i(TAG, "Registering input device with " + numAxes + " axes: " + device.getName());
	     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
	    	 logAdvanced(device);
	     }
	     NativeApp.sendMessage("inputDeviceConnected", device.getName());
	}
	
	public static float ProcessAxis(InputDevice.MotionRange range, float axisvalue) {
		float absaxisvalue = Math.abs(axisvalue);
		float deadzone = range.getFlat();
		if (absaxisvalue <= deadzone) {
			return 0.0f;
		}
		float normalizedvalue;
		if (axisvalue < 0.0f) {
			normalizedvalue = absaxisvalue / range.getMin();
		} else {
			normalizedvalue = absaxisvalue / range.getMax();
		}

		return normalizedvalue;
	}
	
	public boolean onKeyDown(KeyEvent event) {
		int keyCode = event.getKeyCode();
		boolean repeat = event.getRepeatCount() > 0;
		return NativeApp.keyDown(deviceId, keyCode, repeat);
	}
	
	public boolean onKeyUp(KeyEvent event) {
		int keyCode = event.getKeyCode();
	    return NativeApp.keyUp(deviceId, keyCode);
	}
	
	public boolean onJoystickMotion(MotionEvent event) {
		if ((event.getSource() & InputDevice.SOURCE_CLASS_JOYSTICK) == 0) {
			return false;
		}
		NativeApp.beginJoystickEvent();
		for (int i = 0; i < mAxes.length; i++) {
			int axisId = mAxes[i];
			float value = event.getAxisValue(axisId);
			// TODO: Use processAxis or move that to the C++ code
			NativeApp.joystickAxis(deviceId, axisId, value);
		}
		NativeApp.endJoystickEvent();
		return true;
	}
}
