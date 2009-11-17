package mullteatouch;

import com.sun.jna.Structure;

/**
* A Point in the mullteatouch library.
*/
public class Point extends Structure {
    public float x;
    public float y;

    @Override
    public String toString() {
        return "Point{" + x + ", " + y + '}';
    }
}
