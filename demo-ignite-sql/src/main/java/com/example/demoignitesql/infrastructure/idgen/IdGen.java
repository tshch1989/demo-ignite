package com.example.demoignitesql.infrastructure.idgen;

import java.util.concurrent.TimeUnit;

public class IdGen {
    private final long EPOCH_TIME = 1577808000000L;
    private final int timeShift = 32;
    private final long SEQ_MASK = ~(-1L << 32);
    private long sequence = 0L;
    private long lastTime = -1L;

    public synchronized Long nextId(){
        long stime = System.currentTimeMillis();
        if(stime < lastTime){
            throw new RuntimeException("clock move back");
        }
        if(stime == lastTime){
            sequence = (sequence + 1) & SEQ_MASK;
            if(sequence == 0){
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                        return nextId();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else {
            sequence = 0L;
        }
        lastTime = stime;
        return ((stime - EPOCH_TIME) << timeShift)
                | (sequence);
    }

    public static void main(String[] args) {
        IdGen idGen = new IdGen();
        for (int i = 0; i < 100000; i++) {
            Long nextId = idGen.nextId();
            System.out.println(nextId);
        }
    }

}
