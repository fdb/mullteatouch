package mullteatouch;

import com.sun.jna.Structure;

/**
 * Data about a finger on a mullteatouch surface.
 */
public class Finger extends Structure {
    public int frame;
    public double timestamp;
    public int identifier;
    public int state;
    public int unknown1;
    public int unknown2;
    public Vector normalized;
    public float size;
    public int unknown3;
    public float angle;
    public float majorAxis;
    public float minorAxis;
    public Vector unknown4;
    public int unknown5;
    public int unknown6;
    public float unknown7;
}
