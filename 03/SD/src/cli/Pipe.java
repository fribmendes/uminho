package cli;

import java.io.*;

public class Pipe {
    private Boolean isClosed;

    private PipedOutputStream out_local;
    private PipedInputStream in_local;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private PipedOutputStream out_remote;
    private PipedInputStream in_remote;
    private ObjectOutputStream r_out;
    private ObjectInputStream r_in;

    Pipe() throws IOException {
        isClosed = false;

        out_local = new PipedOutputStream();
        in_local = new PipedInputStream();

        out_remote = new PipedOutputStream();
        in_remote = new PipedInputStream();

        out_local.connect(in_remote);
        in_local.connect(out_remote);

        r_out = new ObjectOutputStream(out_remote);
        r_out.flush();
        in = new ObjectInputStream(in_local);

        out = new ObjectOutputStream(out_local);
        out.flush();
        r_in = new ObjectInputStream(in_remote);
    }

    private Pipe(Pipe p) throws IOException {
        isClosed = p.isClosed;

        out_local = p.out_remote;
        in_local = p.in_remote;

        out = p.r_out;
        in = p.r_in;

        r_out = p.out;
        r_in = p.in;

        out_remote = p.out_local;
        in_remote = p.in_local;
    }

    public Pipe reversed() throws IOException {
        return new Pipe(this);
    }

    public void write(Object obj) throws IOException {
        out.writeObject(obj);
    }

    public Object read() throws IOException, ClassNotFoundException {
        return in.readObject();
    }

    public Boolean isClosed(){
        return isClosed;
    }

    public void close(){
        isClosed = true;

        try {
            out_local.close();
        } catch (IOException e) {}

        try {
            in_local.close();
        } catch (IOException e) {}

        try {
            out_remote.close();
        } catch (IOException e) {}

        try {
            in_remote.close();
        } catch (IOException e) {}
    }

    public OutputStream getRemoteOut(){ return out_remote; }

    public InputStream getRemoteIn(){ return in_remote; }

    public ObjectInputStream getIn(){ return in; }

    public ObjectOutputStream getOut(){ return out; }
}
