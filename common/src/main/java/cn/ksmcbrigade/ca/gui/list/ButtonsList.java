package cn.ksmcbrigade.ca.gui.list;

import cn.ksmcbrigade.ca.gui.ConfigsGui;
import cn.ksmcbrigade.ca.gui.list.entrys.ButtonEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import org.jetbrains.annotations.NotNull;

public class ButtonsList extends ContainerObjectSelectionList<ButtonEntry> {
    public ButtonsList(ConfigsGui pKeyBindsScreen, Minecraft pMinecraft) {
        super(pMinecraft, pKeyBindsScreen.width, pKeyBindsScreen.layout.getContentHeight(), pKeyBindsScreen.layout.getHeaderHeight(), 20);
    }

    @Override
    public int addEntry(@NotNull ButtonEntry pEntry) {
        return super.addEntry(pEntry);
    }
}
