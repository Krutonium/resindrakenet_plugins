package ga.krutonium.hardcorejobs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import ga.krutonium.hardcorejobs.ValidJobs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener, CommandExecutor
{
    private Logger log;
    @Override
    public void onEnable(){
        List<String> jobs = ValidJobs.Jobs();
        log = getLogger();
        log.info("Valid Jobs:");

        for(String s : jobs){
            log.info(s);
        }
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("job").setExecutor(this::onCommand);
        //Bukkit.clearRecipes();

    }
    @Override
    public void onDisable(){}

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (event.getBlock().getType() == Material.STONE) {
            event.getPlayer().sendMessage("You're not a Miner");
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onCraft(CraftItemEvent event){
        event.getWhoClicked().sendMessage("Saw that.");
        event.getWhoClicked().sendMessage(event.getRecipe().getResult().toString());
        if(event.getRecipe().getResult().equals(new ItemStack(Material.DARK_OAK_DOOR, 3))) {
            List<String> Name = new ArrayList<String>();
            Name.add("Crafted by");
            Name.add(event.getWhoClicked().getName());
            ItemMeta meta = event.getRecipe().getResult().getItemMeta();
            meta.setLore(Name);
            meta.setDisplayName("Kappa Door!");
            event.getRecipe().getResult().setItemMeta(meta);
            //This doesn't work. WHY?!
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("You did it!");
        Player target = sender.getServer().getPlayerExact(sender.getName());

        if(target != null){

            ItemMeta meta = target.getInventory().getItemInMainHand().getItemMeta();
            List<String> Name = new ArrayList<String>();
            Name.add("Crafted by");
            Name.add(target.getName());
            meta.setLore(Name);
            meta.setDisplayName("Kappa Door!");
            target.getInventory().getItemInMainHand().setItemMeta(meta);
            //This works? But why not the code above?
        }


        //https://www.spigotmc.org/threads/trying-to-add-a-recipe-to-a-specific-player.399780/
        return true;


        //return super.onCommand(sender, command, label, args);
    }
}
