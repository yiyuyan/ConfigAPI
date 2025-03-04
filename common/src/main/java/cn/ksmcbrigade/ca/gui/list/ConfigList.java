package cn.ksmcbrigade.ca.gui.list;

import cn.ksmcbrigade.ca.config.Config;
import cn.ksmcbrigade.ca.gui.ArrayConfigGui;
import cn.ksmcbrigade.ca.gui.ConfigGui;
import cn.ksmcbrigade.ca.gui.list.entrys.*;
import com.google.gson.JsonArray;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;

public class ConfigList extends ContainerObjectSelectionList<Entry> {

    public final Config config;
    public final ConfigGui configGui;

    public ConfigList(ConfigGui pKeyBindsScreen, Minecraft pMinecraft, Config config) {
        super(pMinecraft, pKeyBindsScreen.width, pKeyBindsScreen.layout.getContentHeight(), pKeyBindsScreen.layout.getHeaderHeight(), 20);
        this.configGui = pKeyBindsScreen;
        this.config = config;

        for (String s : this.config.keySet()) {
            Object value = this.config.get(s);
            if(value instanceof String){
                this.addEntry(new StringEntry(this,this.config,s,null));
            }
            else if((value instanceof Number) || (value instanceof Boolean)){
                this.addEntry(new ClassTypeEntry(this,this.config,s,value.getClass()));
            }
            else if(value instanceof JsonArray){
                this.addEntry(new StringEntry(this,this.config,s,()->Minecraft.getInstance().setScreen(new ArrayConfigGui(this.configGui,this.config,s))));
            }
        }
    }

    @Override
    public int getScrollbarPosition() {
        return super.getScrollbarPosition();
    }
}
