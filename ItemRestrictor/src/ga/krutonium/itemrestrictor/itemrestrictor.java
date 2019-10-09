package ga.krutonium.itemrestrictor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class itemrestrictor extends JavaPlugin implements Listener{
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig(); //Does not overwrite if exists

    }
    @Override
    public void onDisable(){

    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInvintoryClick(InventoryClickEvent event) {

        Inventory clicked = event.getClickedInventory();
        Player p = (Player) event.getWhoClicked();
        //Only for Ender Chests or Shulker Box
        Logger log = Bukkit.getLogger();
        if (event.getInventory().getType().toString().equals("SHULKER_BOX") || event.getInventory().getType().toString().equals("ENDER_CHEST")) {
            boolean denied = false;
            if(event.getCurrentItem().getType() == Material.EMERALD){
                event.setCancelled(true);
                p.closeInventory();
                denied = true;
            }
            if(event.getCurrentItem().getType() == Material.EMERALD_BLOCK){
                event.setCancelled(true);
                p.closeInventory();
                denied = true;
            }
            if(event.getCurrentItem().getType() == Material.EMERALD_ORE){
                event.setCancelled(true);
                p.closeInventory();
                denied = true;
            }
            if(denied){
                p.sendMessage(this.getConfig().getString("DenyMessage"));
            }
        }
    }
}