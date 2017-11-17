package im.schiller.spawnitems.events;

import im.schiller.spawnitems.SpawnItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class PlayerLoggedInEvent {

    public static final String PLAYER_RECEIVED_ITEMS = "spawnitems:has_received_items";

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        // Give items to players
        if (SpawnItems.ITEMS.size() > 0) {

            NBTTagCompound playerData = event.player.getEntityData();
            NBTTagCompound data = playerData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);

            // Player hasn't received the ITEMS
            if (!data.getBoolean(PLAYER_RECEIVED_ITEMS)) {

                // Give ITEMS
                for (Item item : SpawnItems.ITEMS) {
                    ItemStack stack = new ItemStack(item);
                    ItemHandlerHelper.giveItemToPlayer(event.player, stack);
                }

                // Set PLAYER_RECEIVED_ITEMS
                data.setBoolean(PLAYER_RECEIVED_ITEMS, true);
                playerData.setTag(EntityPlayer.PERSISTED_NBT_TAG, data);
            }
        }
    }
}