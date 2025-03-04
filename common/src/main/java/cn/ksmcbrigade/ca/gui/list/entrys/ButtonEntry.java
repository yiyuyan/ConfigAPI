package cn.ksmcbrigade.ca.gui.list.entrys;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ButtonEntry extends ContainerObjectSelectionList.Entry<ButtonEntry>{

    final Button button;

    public ButtonEntry(Button b){
        this.button = b;
    }


    @Override
    public @NotNull List<? extends NarratableEntry> narratables() {
        return List.of(this.button);
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pIndex, int pTop, int pLeft, int pWidth, int pHeight, int pMouseX, int pMouseY, boolean pHovering, float pPartialTick) {
        int j = pTop - 2;
        this.button.setPosition(pWidth/2+50,j);
        this.button.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return List.of(this.button);
    }
}
