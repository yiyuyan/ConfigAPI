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
                    for (Config config : new ArrayList<>(configs)) {
                        try {
                            Thread.sleep(waitingTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            if(config.initing || config.saving) continue;
                            if(!config.config.exists()){
                                config.save(false);
                                continue;
                            }
                            if(!new String(Files.readAllBytes(config.config.toPath())).equals(config.get().toString())){
                                System.out.println("The config is different from the local config file: "+config.config.getPath());
                                config.reload();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            },"ConfigWatchDog");
            watchdog.start();
        }
    }
}
