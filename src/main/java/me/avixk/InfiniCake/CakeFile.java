package me.avixk.InfiniCake;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CakeFile {
    static File cakeFile;
    static YamlConfiguration cakeConfig = new YamlConfiguration();

    static void init(){
        cakeFile = new File(Main.plugin.getDataFolder() + "/cakes.yml");
        try {
            cakeFile.createNewFile();
            cakeConfig.load(cakeFile);
        } catch (Exception e) {

        }
    }
    static void save(){
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    cakeConfig.save(cakeFile);
                    cakeConfig.load(cakeFile);
                } catch (Exception e) {
                    //Bukkit.getLogger().severe("Cannot save cake file!");//
                }
            }
        });
    }



    static void addInfiniCake(Block block){
        cakeConfig.set(locToString(block.getLocation()),1);
        save();
    }
    static void removeInfiniCake(Block block){
        String s = locToString(block.getLocation());
        if(cakeConfig.contains(s)){
            cakeConfig.set(s,null);
        }
        save();
    }
    static boolean isInfiniCake(Block block){
        //if(!block.getType().equals(Material.CAKE)) return false;
        return cakeConfig.contains(locToString(block.getLocation()));
    }

    static final char separator = ',';
    public static String locToString(Location location){
        return location.getWorld().getName() + separator + location.getBlockX() + separator + location.getBlockY() + separator + location.getBlockZ();
    }
    public static Location locFromString(String locString){
        try{
            String[] s = locString.split(separator+"");
            return new Location(Bukkit.getWorld(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]));
        }catch (Exception ignored){}
        return null;
    }
}
