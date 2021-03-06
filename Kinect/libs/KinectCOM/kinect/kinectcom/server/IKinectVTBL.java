package kinect.kinectcom.server;

import com.jniwrapper.*;
import com.jniwrapper.win32.*;
import com.jniwrapper.win32.automation.*;
import com.jniwrapper.win32.automation.impl.*;
import com.jniwrapper.win32.automation.server.*;
import com.jniwrapper.win32.automation.types.*;
import com.jniwrapper.win32.com.*;
import com.jniwrapper.win32.com.impl.*;
import com.jniwrapper.win32.com.server.*;
import com.jniwrapper.win32.com.types.*;
import com.jniwrapper.win32.com.types.GUID;
import kinect.kinectcom.*;
import kinect.kinectcom.impl.*;
import kinect.mscorlib.*;
import kinect.mscorlib.impl.*;
import kinect.stdole.*;
import kinect.stdole.impl.*;

/**
 * Represents VTBL for COM interface IKinect.
 */
public class IKinectVTBL extends IDispatchVTBL
{
    public IKinectVTBL(CoClassMetaInfo classMetaInfo)
    {
        super(classMetaInfo);

        addMembers(
            new VirtualMethodCallback[] {
                new VirtualMethodCallback(
                    "init",
                    new HResult(),
                    new Parameter[] {
                    }
                ),
                new VirtualMethodCallback(
                    "uninit",
                    new HResult(),
                    new Parameter[] {
                    }
                ),
                new VirtualMethodCallback(
                    "learnUser",
                    new HResult(),
                    new Parameter[] {
                        new BStr(),
                        new Int32()
                    }
                ),
                new VirtualMethodCallback(
                    "startTracking",
                    new HResult(),
                    new Parameter[] {
                        new Int32(),
                        new Pointer(new VariantBool())
                    },
                    1
                ),
                new VirtualMethodCallback(
                    "stopTracking",
                    new HResult(),
                    new Parameter[] {
                        new Int32()
                    }
                ),
                new VirtualMethodCallback(
                    "gestureRecordCompleted",
                    new HResult(),
                    new Parameter[] {
                        new BStr(),
                        new BStr()
                    }
                ),
                new VirtualMethodCallback(
                    "recordingCountdownEvent",
                    new HResult(),
                    new Parameter[] {
                        new Int32()
                    }
                ),
                new VirtualMethodCallback(
                    "gestureRecognitionCompleted",
                    new HResult(),
                    new Parameter[] {
                        new BStr()
                    }
                ),
                new VirtualMethodCallback(
                    "recordGesture",
                    new HResult(),
                    new Parameter[] {
                        new BStr(),
                        new BStr()
                    }
                ),
                new VirtualMethodCallback(
                    "recognizeGesture",
                    new HResult(),
                    new Parameter[] {
                        new BStr()
                    }
                ),
                new VirtualMethodCallback(
                    "stopRecGesture",
                    new HResult(),
                    new Parameter[] {
                    }
                ),
                new VirtualMethodCallback(
                    "onAddOnGestureValueChange",
                    new HResult(),
                    new Parameter[] {
                        new SingleFloat()
                    }
                ),
                new VirtualMethodCallback(
                    "contextSelected",
                    new HResult(),
                    new Parameter[] {
                        new BStr()
                    }
                ),
                new VirtualMethodCallback(
                    "storeGestures",
                    new HResult(),
                    new Parameter[] {
                    }
                ),
                new VirtualMethodCallback(
                    "loadGestures",
                    new HResult(),
                    new Parameter[] {
                        new Pointer(new IEnumerableImpl())
                    },
                    0
                ),
                new VirtualMethodCallback(
                    "setTrackingStrategy",
                    new HResult(),
                    new Parameter[] {
                        new Int32()
                    }
                ),
                new VirtualMethodCallback(
                    "trackingStarted",
                    new HResult(),
                    new Parameter[] {
                        new Int32()
                    }
                ),
                new VirtualMethodCallback(
                    "trackingStopped",
                    new HResult(),
                    new Parameter[] {
                        new Int32()
                    }
                ),
                new VirtualMethodCallback(
                    "getObjects",
                    new HResult(),
                    new Parameter[] {
                        new Pointer(new BStr())
                    },
                    0
                ),
                new VirtualMethodCallback(
                    "userDetected",
                    new HResult(),
                    new Parameter[] {
                        new _UserImpl()
                    }
                ),
                new VirtualMethodCallback(
                    "userLost",
                    new HResult(),
                    new Parameter[] {
                        new _UserImpl()
                    }
                ),
                new VirtualMethodCallback(
                    "setDefaultHand",
                    new HResult(),
                    new Parameter[] {
                        new VariantBool()
                    }
                ),
                new VirtualMethodCallback(
                    "loadUsers",
                    new HResult(),
                    new Parameter[] {
                        new Pointer(new BStr())
                    },
                    0
                ),
                new VirtualMethodCallback(
                    "addUser",
                    new HResult(),
                    new Parameter[] {
                        new BStr()
                    }
                ),
                new VirtualMethodCallback(
                    "delUser",
                    new HResult(),
                    new Parameter[] {
                        new BStr()
                    }
                ),
                new VirtualMethodCallback(
                    "setNewObjectContext",
                    new HResult(),
                    new Parameter[] {
                        new BStr()
                    }
                )
            }
        );
    }
}