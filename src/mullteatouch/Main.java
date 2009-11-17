package mullteatouch;

import com.sun.jna.Platform;
import com.sun.jna.Pointer;

import javax.swing.*;
import java.awt.*;

public class Main implements MultitouchLibrary.ContactFrameCallback {
    private static FingerPanel fingerPanel;

    public Main() {
        // Check that we're on the correct platform.
        if (!Platform.isMac()) {
            System.out.println("Only works on Mac.");
            System.exit(-1);
        }

        // Initialize the mullteatouch library.
        MultitouchLibrary lib = MultitouchLibrary.INSTANCE;
        Pointer p = lib.MTDeviceCreateList();
        int deviceCount = lib.CFArrayGetCount(p);
        //System.out.println("Number of devices = " + deviceCount);
        if (deviceCount == 0) {
            System.out.println("No multitouch devices found.");
            System.exit(-1);
        }

        // Register the callback.
        Pointer pDevice = lib.MTDeviceCreateDefault();
        // It is important that the callback object is not garbage-collected.
        // Because we're using this, there's no risk for that happening.
        lib.MTRegisterContactFrameCallback(pDevice, this);
        lib.MTDeviceStart(pDevice, 0);

        // Create the GUI.
        JFrame frame = new JFrame("Mullteatouch");
        fingerPanel = new FingerPanel();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(fingerPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setSize(800, 600);
        frame.setVisible(true);

        // The garbage collector kicks in at this point and randomly collects some objects
        // that it considers unreferenced, but are used by the multitouch library.
        // To avoid that, we'll sleep this thread to keep all objects in here alive.
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Main m = new Main();
    }


    public void callback(int device, Finger fingerArray, int nFingers, double timestamp, int frame) {
        // The callback is run in the mullteatouch thread, so all access to the finger panel should pass through
        // SwingUtilities.invokeLater to invoke it on the AWT thread.
        if (nFingers == 0) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    fingerPanel.setFingers(null);
                }
            });
            return;
        }
        final Finger[] fingers = (Finger[]) fingerArray.toArray(nFingers);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fingerPanel.setFingers(fingers);
            }
        });
        for (int i = 0; i < fingers.length; i++) {
            Finger finger = fingers[i];
            System.out.println("finger " + i + " normalized= " + finger.normalized);
        }
    }


}
