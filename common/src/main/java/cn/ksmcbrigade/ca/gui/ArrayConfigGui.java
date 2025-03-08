package cn.ksmcbrigade.ca.gui;

import cn.ksmcbrigade.ca.config.Config;
import cn.ksmcbrigade.ca.config.Configs;
import cn.ksmcbrigade.ca.gui.entry.ArrStringGui;
import cn.ksmcbrigade.ca.gui.list.ArrayConfigList;
import cn.ksmcbrigade.ca.gui.list.entrys.ArrStringEntry;
import cn.ksmcbrigade.ca.gui.list.entrys.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ArrayConfigGui extends OptionsSubScreen {
    public final Config config;
    public final String key;
    public ArrayConfigList list;

    public ArrayConfigGui(Screen pLastScreen, Config config,String key) {
        super(pLastScreen, Minecraft.getInstance().options, Component.literal("Config"));
        this.config = config;
        this.key = key;
    }

    @Override
    protected void addOptions() {

    }

    protected void addContents() {
        this.list = this.layout.addToContents(new ArrayConfigList(this,this.minecraft,this.config,this.key));
    }

    protected void repositionElements() {
        this.layout.arrangeElements();
        this.list.updateSize(this.width, this.layout);
    }

    protected void addFooter() {
        LinearLayout linearlayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
        linearlayout.addChild(Button.builder(Component.literal("Add"),(b)->{
            ArrayList<String> arr = new ArrayList<>(this.config.getArray(this.key));
            int i = arr.size();
            arr.add("");
            this.config.put(this.key,arr);
            Minecraft.getInstance().setScreen(new ArrStringGui(new ArrayConfigGui(this.lastScreen,this.config,this.key),this.config,this.key,i));
        }).width(100).build());
        linearlayout.addChild(Button.builder(Component.literal("Remove"),(b)->{
            if(this.list!=null){
                for (Entry child : this.list.children()) {
                    if(child instanceof ArrStringEntry arrStringEntry){
                        if(arrStringEntry.isFocused()){
                            remove(this.config,this.key,arrStringEntry.getContext());
                        }
                    }
                }
                Minecraft.getInstance().setScreen(new ArrayConfigGui(this.lastScreen,this.config,this.key));
            }
        }).width(100).build());
        linearlayout.addChild(Button.builder(CommonComponents.GUI_DONE, (p_344249_) -> this.onClose()).width(100).build());
    }

    public void render(@NotNull GuiGraphics p_344555_, int p_344302_, int p_344298_, float p_344857_) {
        super.render(p_344555_, p_344302_, p_344298_, p_344857_);
    }

    public static void add(Config config,String key,String context){
        ArrayList<String> arr = new ArrayList<>(config.getArray(key));
        arr.add(context);
        config.put(key,arr);
    }

    public static void remove(Config config,String key,String context){
        ArrayList<String> arr = new ArrayList<>(config.getArray(key));
        arr.remove(context);
        config.put(key,arr);
    }

    public static boolean replace(Config config,String key,String context,String target){
        ArrayList<String> arr = new ArrayList<>(config.getArray(key));
        int i = arr.indexOf(context);
        if(i==-1) return false;
        arr.set(i,target);
        config.put(key,arr);
        return true;
    }
}
