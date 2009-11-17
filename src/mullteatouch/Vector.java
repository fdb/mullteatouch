package mullteatouch;

import com.sun.jna.Structure;

/**
 * A Vector in the mullteatouch library.
 */

public class Vector extends Structure {
    public Point position;
    public Point velocity;

    @Override
    public String toString() {
        return "Vector{" +
                "pos=" + position +
                ", vel=" + velocity +
                '}';
    }
}
