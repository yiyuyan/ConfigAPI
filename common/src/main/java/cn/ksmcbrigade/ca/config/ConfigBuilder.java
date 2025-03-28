package cn.ksmcbrigade.ca.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ConfigBuilder {

    private File file;
    public boolean initing = false;
    private BiConsumer<String,Object> callback = (s, o)->{};
    public Map<String,Object> data = new HashMap<>();

    public ConfigBuilder(File file){
        this.file = file;
    }

    public ConfigBuilder(String file,boolean forceConfigFolder){
        this(new File(file));
        if((!file.startsWith("config/") || !file.startsWith("config\\")) && forceConfigFolder){
            new File("config").mkdirs();
            this.setFile(new File("config/"+this.file.getName()));
        }
    }

    public void setFile(File file){
        this.file = file;
    }

    public void setCallback(BiConsumer<String, Object> callback) {
        this.callback = callback;
    }

    public Config buildOnly(){
        return new Config(this.file, this.callback, this.initing, this.data);
    }

    public Config build() throws IOException, IllegalAccessException, NoSuchFieldException {
        Config config = buildOnly();
        config.save(false);
        config.load();
        return config;
    }
}
