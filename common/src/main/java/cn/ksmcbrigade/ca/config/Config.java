package cn.ksmcbrigade.ca.config;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.*;
import java.util.function.BiConsumer;

public class Config {

    public final File config;
    final Map<String,Object> data = new HashMap<>();
    public final BiConsumer<String,Object> callback;
    boolean saving = false;

    public Config(File configFile,BiConsumer<String,Object> callback){
        this.config = configFile;
        this.callback = callback;
        Configs.configs.add(this);
        Configs.startWatchDog();
    }

    public Config(String configFile){
        this(new File(configFile),(s, o)->{});
    }

    JsonObject get() throws IllegalAccessException, NoSuchFieldException {
        JsonObject object = new JsonObject();
        addToJsonObject(object);
        return object;
    }

    public void save(boolean exists) throws IOException, IllegalAccessException, NoSuchFieldException {
        if(!this.config.exists() || exists){
            saving = true;
            Files.write(config.toPath(),get().toString().getBytes());
            saving = false;
        }
    }

    public void load() throws IOException {
        JsonObject object = new JsonParser().parse(new String(Files.readAllBytes(config.toPath()))).getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            JsonElement value = entry.getValue();
            if (value.isJsonPrimitive()) {
                JsonPrimitive primitive = value.getAsJsonPrimitive();
                if (primitive.isString()) {
                    data.put(entry.getKey(), primitive.getAsString());
                } else if (primitive.isBoolean()) {
                    data.put(entry.getKey(), primitive.getAsBoolean());
                }
                else{
                    String str = value.getAsString();
                    String key = entry.getKey();
                    try {
                        data.put(key,Integer.parseInt(str));
                        continue;
                    }
                    catch (Exception e){
                        try {
                            data.put(key,Long.parseLong(str));
                            continue;
                        }
                        catch (Exception ex){
                            //nothing
                        }
                    }
                    try {
                        data.put(key,Double.parseDouble(str));
                        continue;
                    }
                    catch (Exception e){
                        //nothing
                    }
                    try {
                        data.put(key,Float.parseFloat(str));
                        continue;
                    }
                    catch (Exception e){
                        //nothing
                    }
                    try {
                        data.put(key,Long.parseLong(str));
                        continue;
                    }
                    catch (Exception e){
                        //nothing
                    }
                    try {
                        data.put(key,Byte.parseByte(str));
                    }
                    catch (Exception e){
                        //nothing
                    }
                }
            } else if (value.isJsonArray()) {
                JsonArray array = value.getAsJsonArray();
                ArrayList<String> list = new ArrayList<>();
                for (JsonElement element : array) {
                    list.add(element.getAsString());
                }
                data.put(entry.getKey(), list);
            }
        }
    }

    private void addToJsonObject(JsonObject object) throws IllegalAccessException, NoSuchFieldException {
        for (String s : this.data.keySet()) {
            Object o = this.data.get(s);
            if(o instanceof String){
                object.addProperty(s,(String)o);
                continue;
            }
            if(o instanceof Character){
                object.addProperty(s,(Character) o);
                continue;
            }
            if(o instanceof Byte){
                object.addProperty(s,(Byte) o);
                continue;
            }
            if(o instanceof Boolean){
                object.addProperty(s,(boolean) o);
                continue;
            }
            if(o instanceof Integer){
                object.addProperty(s,(int) o);
                continue;
            }
            if(o instanceof Long){
                object.addProperty(s,(long) o);
                continue;
            }
            if(o instanceof Float){
                object.addProperty(s,(float) o);
                continue;
            }
            if(o instanceof Double){
                object.addProperty(s,(double) o);
                continue;
            }
            if(o instanceof JsonArray){
                object.add(s,(JsonArray)o);
                continue;
            }
            JsonPrimitive constructor = new JsonPrimitive(0);
            Field valueField = constructor.getClass().getDeclaredField("value");
            valueField.setAccessible(true);
            valueField.set(constructor,o);
            object.add(s,constructor);
        }
    }


    public Set<String> keySet(){
        return data.keySet();
    }

    public Collection<Object> values(){
        return data.values();
    }






    public void put(String key, Object value){
        data.put(key,value);
        s(key,value);
    }

    public void put(String key,String value){
        data.put(key,value);
        s(key,value);
    }

    public void put(String key,Byte value){
        data.put(key,value);
        s(key,value);
    }

    public void put(String key,int value){
        data.put(key,value);
        s(key,value);
    }

    public void put(String key,boolean value){
        data.put(key,value);
        s(key,value);
    }

    public void put(String key,long value){
        data.put(key,value);
        s(key,value);
    }

    public void put(String key,double value){
        data.put(key,value);
        s(key,value);
    }

    public void put(String key,float value){
        data.put(key,value);
        s(key,value);
    }

    public void put(String key,Enum<?> value){
        data.put(key,value.name());
        s(key,value);
    }

    public void put(String key,List<String> value){
        JsonArray array = new JsonArray();
        for(String v:value){
            array.add(v);
        }
        data.put(key,array);
        s(key,value);
    }

    public Object get(String key){
        return data.get(key);
    }

    public String getString(String key){
        Object obj = data.get(key);
        if(!(obj instanceof String)) throw new RuntimeException("Can't case the object value to String.");
        return (String)obj;
    }

    public Byte getByte(String key){
        Object obj = data.get(key);
        if(!(obj instanceof Byte)) throw new RuntimeException("Can't case the object value to byte.");
        return (Byte) obj;
    }

    public boolean getBoolean(String key){
        Object obj = data.get(key);
        if(!(obj instanceof Boolean)) throw new RuntimeException("Can't case the object value to bool.");
        return (boolean)obj;
    }

    public int getInt(String key){
        Object obj = data.get(key);
        if(!(obj instanceof Integer)) throw new RuntimeException("Can't case the object value to integer.");
        return (int)obj;
    }

    public long getLong(String key){
        Object obj = data.get(key);
        if(!(obj instanceof Long)) throw new RuntimeException("Can't case the object value to long.");
        return (long)obj;
    }

    public float getFloat(String key){
        Object obj = data.get(key);
        if(!(obj instanceof Float)) throw new RuntimeException("Can't case the object value to float.");
        return (float)obj;
    }

    public double getDouble(String key){
        Object obj = data.get(key);
        if(!(obj instanceof Double)) throw new RuntimeException("Can't case the object value to double.");
        return (double)obj;
    }

    public List<String> getArray(String key){
        Object obj = data.get(key);
        if(!(obj instanceof JsonArray)) throw new RuntimeException("Can't case the object value to json array.");
        ArrayList<String> arrayList = new ArrayList<>();
        for(JsonElement v:((JsonArray)obj)) arrayList.add(v.getAsString());
        return arrayList;
    }

    public <T extends Enum<T>> Enum<T> getEnum(String key,Class<T> e){
        Object obj = data.get(key);
        if(!(obj instanceof String)) throw new RuntimeException("Can't case the object value to String.");
        return Enum.valueOf(e,(String)obj);
    }

    private void s(String key,Object value){
        try {
            this.callback.accept(key,value);
            save(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() throws IOException {
        this.saving = true;
        this.data.clear();
        this.load();
        for (String s : this.keySet()) {
            this.callback.accept(s,this.get(s));
        }
        this.saving = false;
    }
}
