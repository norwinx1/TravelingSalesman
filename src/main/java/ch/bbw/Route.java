public class Route {
    private long length;
    private int destinationId;

    public Route(long length, int destinationId) {
        this.length = length;
        this.destinationId = destinationId;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public int getDestination() {
        return destinationId;
    }

    public void setDestination(int destination) {
        this.destinationId = destination;
    }
}
