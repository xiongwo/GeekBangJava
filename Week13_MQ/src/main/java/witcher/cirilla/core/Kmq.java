package witcher.cirilla.core;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new KmqMessage[capacity];
        this.insertIndex = new AtomicInteger(-1);
        this.readIndex = new AtomicInteger(-1);
    }

    private String topic;

    private int capacity;

    private KmqMessage[] queue;

    private AtomicInteger insertIndex;

    private AtomicInteger readIndex;

    public boolean send(KmqMessage message) {
        int index = insertIndex.incrementAndGet();
        if (index > capacity - 1) {
            // throw exception
        }
        queue[index] = message;
        return true;
    }

    public KmqMessage poll() {
        return queue[readIndex.incrementAndGet()];
    }

}
