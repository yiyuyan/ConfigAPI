package cn.ksmcbrigade.ca.config;

import java.nio.file.Files;
import java.util.ArrayList;

public class Configs {
    public static ArrayList<Config> configs = new ArrayList<>();
    public static Thread watchdog = null;
    public static boolean stoppedWatchDog = false;
    public static long waitingTime = 1000L;
    public static void startWatchDog(){
        if(watchdog==null){
            watchdog = new Thread(()->{
                while (!stoppedWatchDog){
                    for (Config config : configs) {
                        try {
                            if(!config.config.exists()){
                                config.save(false);
                                continue;
                            }
                            if(!new String(Files.readAllBytes(config.config.toPath())).equals(config.get().toString()) && !config.saving){
                                System.out.println("The config is different from the local config file: "+config.config.getPath());
                                config.data.clear();
                                config.load();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(waitingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"ConfigWatchDog");
            watchdog.start();
        }
    }
}
