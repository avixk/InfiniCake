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
            Bukkit.getLogger().info("Cakefile loaded successfully");
        } catch (Exception e) {
            Bukkit.getLogger().warning("Cakefile could not be loaded!! If this is the first time the plugin has been loaded, ignore this.");
        }
    }
    static void save(){
        /*Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, new Runnable() {
            @Override
            public void run() {*/
                try {
                    cakeConfig.save(cakeFile);//TODO FIX NPE
                    //cakeConfig.load(cakeFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    Bukkit.getLogger().info("Cannot save cake file! You probably lost a cake!!");
                }
           /* }
        });*/
    }



    static void addInfiniCake(Block block){
        String lstring = locToString(block.getLocation());
        cakeConfig.set(lstring,1);
        Bukkit.getLogger().info("Added infinicake at " + lstring);
        save();//TODO THIS THROWS NPE
    }
    static void removeInfiniCake(Block block){
        String s = locToString(block.getLocation());
        if(cakeConfig.contains(s)){
            cakeConfig.set(s,null);
            Bukkit.getLogger().info("Removed infinicake at " + s);
            save();
        }else{
            Bukkit.getLogger().info("InfiniCake at " + s + " was supposed to be removed, but was not found in config?");
        }
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
