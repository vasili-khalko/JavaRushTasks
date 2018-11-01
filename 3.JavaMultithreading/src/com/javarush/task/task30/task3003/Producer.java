package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

/**
 * Created by Basillio on 07.02.2017.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 9; i++) {
                String itemName = String.format("ShareItem-%d", i+1);
                ShareItem item = new ShareItem(itemName, i+1);
                System.out.format("Элемент ‘%s‘ добавлен\n", itemName);
                queue.offer(item);

                Thread.currentThread().sleep(10);

                if (queue.hasWaitingConsumer()) {
                    System.out.format("Consumer в ожидании!\n");
                }
            }

        } catch (InterruptedException e) {
            return;

        }
    }
}
