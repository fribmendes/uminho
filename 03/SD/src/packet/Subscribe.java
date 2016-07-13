package packet;

import java.util.ArrayList;
import java.util.Collection;

public class Subscribe extends Packet {
    //query
    public Collection<Integer> q_ids;

    public Subscribe() {
        super();
        this.q_ids = new ArrayList<>();
    }
}