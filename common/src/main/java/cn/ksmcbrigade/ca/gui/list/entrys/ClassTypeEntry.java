package cn.ksmcbrigade.ca.gui.list.entrys;

import cn.ksmcbrigade.ca.config.Config;
import cn.ksmcbrigade.ca.gui.list.ConfigList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ClassTypeEntry extends Entry{

    final ConfigList instance;
    final Config config;
    final String key;
    final EditBox box;
    final Class<?> type;

    public ClassTypeEntry(ConfigList instance, Config config, String key, Class<?> type){
        this.instance = instance;
        this.config = config;
        this.key = key;
        this.type = type;
        this.box = new EditBox(Minecraft.getInstance().font,50,20, Component.literal(String.valueOf(config.get(key))));
        this.box.setValue(String.valueOf(this.config.get(key)));
        this.box.setFilter(context-> !(CAST(context) instanceof String));
        if(type==Character.class){
            this.box.setMaxLength(1);
        }
        this.box.setResponder((context)->{
            try {
                if(!context.isEmpty()){
                    this.config.put(this.key,CAST(context));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Object CAST(String str) {
        try {
            return type.cast(str);
        }
        catch (Exception e){
            try {
                Method method = type.getDeclaredMethod("parse"+type.getSimpleName().replace("Integer","Int"),String.class);
                method.setAccessible(true);
                return method.invoke(null,str);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException ex) {
                try {
                    if(type== Integer.class){
                        return Integer.parseInt(str);
                    }
                    else if(type== Long.class){
                        return Long.parseLong(str);
                    }
                    else if(type== Double.class){
                        return Double.parseDouble(str);
                    }
                    else if(type== Float.class){
                        return Float.parseFloat(str);
                    }
                    else if(type== Byte.class){
                        return Byte.parseByte(str);
                    }
                    else{
                        return str;
                    }
                } catch (NumberFormatException exc) {
                    return str;
                }
            }
        }
    }

    @Override
    void refreshEntry() {

    }

    @Override
    public List<? extends NarratableEntry> narratables() {
        return List.of(this.box);
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pIndex, int pTop, int pLeft, int pWidth, int pHeight, int pMouseX, int pMouseY, boolean pHovering, float pPartialTick) {
        int i = this.instance.getScrollbarPosition() - this.box.getWidth() - 10;
        int j = pTop - 2;
        this.box.setPosition(i, j);
        this.box.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.drawString(Minecraft.getInstance().font, this.key, pLeft, pTop + pHeight / 2 - 4, -1);
    }

    @Override
    public List<? extends GuiEventListener> children() {
        return List.of(this.box);
    }
}
