package cn.ksmcbrigade.ca.gui.list;

import cn.ksmcbrigade.ca.config.Config;
import cn.ksmcbrigade.ca.gui.ArrayConfigGui;
import cn.ksmcbrigade.ca.gui.list.entrys.ArrStringEntry;
import cn.ksmcbrigade.ca.gui.list.entrys.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import org.jetbrains.annotations.NotNull;

public class ArrayConfigList extends ContainerObjectSelectionList<Entry> {

    public ArrayConfigGui last;

    public ArrayConfigList(ArrayConfigGui pKeyBindsScreen, Minecraft pMinecraft, Config config, String key) {
        super(pMinecraft, pKeyBindsScreen.width, pKeyBindsScreen.layout.getContentHeight(), pKeyBindsScreen.layout.getHeaderHeight(), 20);
        this.last = pKeyBindsScreen;
        for (int i = 0; i < config.getArray(key).size(); i++) {
            this.addEntry(new ArrStringEntry(this,config,key,i));
        }
    }

    public int addEntry(@NotNull ArrStringEntry pEntry) {
        return super.addEntry(pEntry);
    }

    @Override
    public int getScrollbarPosition() {
        return super.getScrollbarPosition();
    }
}
