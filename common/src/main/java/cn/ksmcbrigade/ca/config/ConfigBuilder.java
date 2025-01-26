package cn.ksmcbrigade.ca.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigBuilder {

    private File file;
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

    public Config buildOnly(){
        Config config = new Config(this.file);
        for (String s : this.data.keySet()) {
            config.put(s,this.data.get(s));
        }
        return config;
    }

    public Config build() throws IOException, IllegalAccessException, NoSuchFieldException {
        Config config = buildOnly();
        config.save(false);
        config.load();
        return config;
    }
}
