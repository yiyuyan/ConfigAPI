package cn.ksmcbrigade.ca.gui;

import cn.ksmcbrigade.ca.config.Config;
import cn.ksmcbrigade.ca.config.Configs;
import cn.ksmcbrigade.ca.gui.list.ButtonsList;
import cn.ksmcbrigade.ca.gui.list.entrys.ButtonEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ConfigsGui extends OptionsSubScreen {

    ButtonsList list;

    public ConfigsGui(Screen last) {
        super(last,Minecraft.getInstance().options,Component.literal("Configs"));

    }

    @Override
    protected void addContents() {
        this.minecraft = Minecraft.getInstance();
        list = new ButtonsList(this,this.minecraft);
        for (Config config : Configs.configs) {
            this.list.addEntry(new ButtonEntry(Button.builder(Component.literal(config.config.getName().replace(".json","")),(button)-> this.minecraft.setScreen(new ConfigGui(this,config))).build()));
        }
        this.layout.addToContents(list);
    }

    @Override
    protected void addOptions() {

    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        //this.list.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
