package async.ring;

import java.nio.ByteBuffer;

/**
 * Created by frm on 15/10/15.
 */
public class Message {
    private ByteBuffer buffer;
    public int id;

    public Message(ByteBuffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    // Thank you Java, for your side-effects
    public ByteBuffer safeBuffer() {
        ByteBuffer clone = ByteBuffer.allocate(buffer.capacity());
        buffer.rewind();
        clone.put(buffer);
        buffer.rewind();
        clone.flip();
        return clone;
    }
}
