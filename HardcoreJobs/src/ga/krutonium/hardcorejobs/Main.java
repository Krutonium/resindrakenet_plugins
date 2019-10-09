package ga.krutonium.hardcorejobs;

import com.pablo67340.SQLiteLib.Main.SQLiteLib;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ga.krutonium.hardcorejobs.ValidJobs;

import java.util.List;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener
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
    }
    @Override
    public void onDisable(){}

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){

        if(event.getBlock().getType() == Material.STONE){
            event.getPlayer().sendMessage("You're not a Miner");
            event.setCancelled(true);
        }
    }
}
