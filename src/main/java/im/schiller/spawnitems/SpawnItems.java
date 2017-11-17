package im.schiller.spawnitems;

import im.schiller.spawnitems.events.PlayerLoggedInEvent;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod(modid = SpawnItems.MODID,
     version = SpawnItems.VERSION,
     name = SpawnItems.MODNAME,
     serverSideOnly = true,
     acceptableRemoteVersions = "*")
public class SpawnItems {

    // Mod infos
    public static final String MODID = "spawnitems";
    public static final String MODNAME = "Spawn Items";
    public static final String VERSION = "1.0";

    @Instance
    public static SpawnItems INSTANCE;

    // Logger
    public static Logger LOGGER;

    // Configuration
    public static List<Item> ITEMS = new ArrayList<>();
    private String[] itemNames;
    private boolean giveRandomItem = false;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Setup logger
        LOGGER = event.getModLog();

        // Parse configuration
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        Property items = config.get("general", "items", new String[] {});
        Property giveRandomItem = config.get("general", "giveRandomItem", false);
        config.save();

        // Set configuration
        this.giveRandomItem = giveRandomItem.getBoolean();
        this.itemNames = items.getStringList();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Check if ITEMS exist
        for(String itemName : itemNames) {
            Item item = Item.getByNameOrId(itemName);
            if (item == null) {
                LOGGER.error("Failed to get Item with name: {}", itemName);
            } else {
                SpawnItems.ITEMS.add(item);
            }
        }

        // Add random item
        if (this.giveRandomItem) {
            SpawnItems.ITEMS.add(Item.REGISTRY.getRandomObject(new Random()));
        }

        // Register to events
        MinecraftForge.EVENT_BUS.register(new PlayerLoggedInEvent());
    }
}
