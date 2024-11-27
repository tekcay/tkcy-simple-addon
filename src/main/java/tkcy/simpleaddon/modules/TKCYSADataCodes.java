package tkcy.simpleaddon.modules;

import java.util.function.Consumer;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class TKCYSADataCodes {

    private static int nextId = 100;

    public static final int UPDATE_ITEM_STACK = assignId();
    public static final int UPDATE_ITEM_COUNT = assignId();
    public static final int TOTAL_CAPACITY = assignId();

    private static int assignId() {
        return nextId++;
    }

    public static Consumer<PacketBuffer> getItemStackWriter(ItemStack itemStack) {
        return packetBuffer -> packetBuffer.writeItemStack(itemStack);
    }

    public static Consumer<PacketBuffer> getInt(int value) {
        return packetBuffer -> packetBuffer.writeInt(value);
    }
}