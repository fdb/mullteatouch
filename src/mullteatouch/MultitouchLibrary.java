package mullteatouch;

import com.sun.jna.*;

/**
 * The JNA mapping for Mac OS X's mullteatouch library.
 */
public interface MultitouchLibrary extends Library {
    MultitouchLibrary INSTANCE = (MultitouchLibrary) Native.loadLibrary("/System/Library/PrivateFrameworks/MultitouchSupport.framework/MultitouchSupport", MultitouchLibrary.class);


    interface ContactFrameCallback extends Callback {
        void callback(int device, Finger data, int nFingers, double timestamp, int frame);
    }

    Pointer MTDeviceCreateList();
    Pointer MTDeviceCreateDefault();
    void MTRegisterContactFrameCallback(Pointer device, ContactFrameCallback callback);
    void MTDeviceStart(Pointer device, int foo);

    int CFArrayGetCount(Pointer p);

}
