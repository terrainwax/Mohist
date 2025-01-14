package red.mohist;

import net.minecraft.server.MinecraftServer;
import red.mohist.i18n.Message;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WatchMohist extends TimerTask
{
    private static ScheduledExecutorService timer = Executors.newScheduledThreadPool(2);
    private static long Time = 0L;
    private static long WarnTime = 0L;
    
    @Override
    public void run() {
        long curTime = System.currentTimeMillis();
        if (WatchMohist.Time > 0L && curTime - WatchMohist.Time > 2000L && curTime - WatchMohist.WarnTime > 30000L) {
            WatchMohist.WarnTime = curTime;
            Mohist.LOGGER.warn(Message.getString(Message.WatchMohist_1));
            Mohist.LOGGER.warn(Message.getFormatString(Message.WatchMohist_2, new Object[]{(curTime - WatchMohist.Time)}));
            Mohist.LOGGER.warn(Message.getString(Message.WatchMohist_3));
            for (StackTraceElement stack : MinecraftServer.getServerInst().primaryThread.getStackTrace()) {
                Mohist.LOGGER.warn(Message.getString(Message.WatchMohist_4) + stack);
            }
            Mohist.LOGGER.warn(Message.getString(Message.WatchMohist_1));
        }
    }
    
    public static void update() {
        WatchMohist.Time = System.currentTimeMillis();
    }
    
    public static void start() {
        timer.scheduleWithFixedDelay(new WatchMohist(), 30000L, 500L, TimeUnit.SECONDS);
    }
    
    public static void stop() {
        timer.shutdownNow();
    }
}
