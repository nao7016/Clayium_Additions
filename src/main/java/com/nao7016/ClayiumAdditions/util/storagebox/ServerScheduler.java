package com.nao7016.ClayiumAdditions.util.storagebox;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ServerScheduler {

    private static final Queue<Runnable> taskQueue = new ConcurrentLinkedDeque<>();

    /**
     * メインスレッドで実行したい処理をキューに追加
     */
    public static void schedule(Runnable task) {
        taskQueue.offer(task);
    }

    /**
     * サーバーTickごとにメインスレッドでタスクを処理
     */
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Runnable task;
            while ((task = taskQueue.poll()) != null) {
                try {
                    task.run();
                } catch (Exception ignored) {}
            }
        }
    }

    /**
     * 登録メソッド（Mod初期化時に呼ぶ）
     */
    public static void register() {
        FMLCommonHandler.instance().bus().register(new ServerScheduler());
    }
}
