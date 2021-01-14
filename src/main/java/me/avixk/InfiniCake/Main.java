package me.avixk.InfiniCake;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Cake;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class Main extends JavaPlugin implements Listener {
    static Plugin plugin;
    static ItemStack infinicake = null;
    static boolean useAnvil = false;
    @Override
    public void onEnable() {
        plugin = this;
        CakeFile.init();
        saveDefaultConfig();
        useAnvil = getConfig().getBoolean("use_anvil_recipe");
        infinicake = new ItemStack(Material.CAKE);
        ItemMeta cakemeta = infinicake.getItemMeta();
        //List<String> cakelore = new ArrayList<>();
        //cakelore.add("§7");
        if(!getConfig().getString("infinicake_item_name").equals("")){
            cakemeta.setDisplayName(getConfig().getString("infinicake_item_name"));
        }
        //cakemeta.setLore(cakelore);
        infinicake.setItemMeta(cakemeta);
        infinicake.addUnsafeEnchantment(Enchantment.ARROW_INFINITE,1);

        if(getConfig().getBoolean("infinicake_crafting_recipe")){
            NamespacedKey shapelessnsk = new NamespacedKey(Main.this,"shapeless_infinicake");
            if(Bukkit.getRecipe(shapelessnsk) != null)
                Bukkit.removeRecipe(shapelessnsk);
            NamespacedKey shapednsk = new NamespacedKey(Main.this,"infinicake");
            if(Bukkit.getRecipe(shapednsk) != null)
                Bukkit.removeRecipe(shapednsk);
            if(getConfig().getBoolean("recipe.shapeless.enabled")){
                ShapelessRecipe cakerecipe = new ShapelessRecipe(shapelessnsk,infinicake);
                if(getConfig().contains("recipe.shapeless.ingredients.A")){
                    cakerecipe.addIngredient(Material.valueOf(getConfig().getString("recipe.shapeless.ingredients.A")));
                }
                if(getConfig().contains("recipe.shapeless.ingredients.B")){
                    cakerecipe.addIngredient(Material.valueOf(getConfig().getString("recipe.shapeless.ingredients.B")));
                }
                if(getConfig().contains("recipe.shapeless.ingredients.C")){
                    cakerecipe.addIngredient(Material.valueOf(getConfig().getString("recipe.shapeless.ingredients.C")));
                }
                if(getConfig().contains("recipe.shapeless.ingredients.D")){
                    cakerecipe.addIngredient(Material.valueOf(getConfig().getString("recipe.shapeless.ingredients.D")));
                }
                if(getConfig().contains("recipe.shapeless.ingredients.E")){
                    cakerecipe.addIngredient(Material.valueOf(getConfig().getString("recipe.shapeless.ingredients.E")));
                }
                if(getConfig().contains("recipe.shapeless.ingredients.F")){
                    cakerecipe.addIngredient(Material.valueOf(getConfig().getString("recipe.shapeless.ingredients.F")));
                }
                if(getConfig().contains("recipe.shapeless.ingredients.G")){
                    cakerecipe.addIngredient(Material.valueOf(getConfig().getString("recipe.shapeless.ingredients.G")));
                }
                if(getConfig().contains("recipe.shapeless.ingredients.H")){
                    cakerecipe.addIngredient(Material.valueOf(getConfig().getString("recipe.shapeless.ingredients.H")));
                }
                if(getConfig().contains("recipe.shapeless.ingredients.I")){
                    cakerecipe.addIngredient(Material.valueOf(getConfig().getString("recipe.shapeless.ingredients.I")));
                }
                Bukkit.addRecipe(cakerecipe);
            }
            if(getConfig().getBoolean("recipe.shaped.enabled")){
                ShapedRecipe cakerecipe = new ShapedRecipe(shapednsk,infinicake);
                cakerecipe.shape("ABC","DEF","GHI");
                if(!getConfig().getString("recipe.shaped.ingredients.top_left").equals("AIR")){
                    cakerecipe.setIngredient('A',Material.valueOf(getConfig().getString("recipe.shaped.ingredients.top_left")));
                }
                if(!getConfig().getString("recipe.shaped.ingredients.top_middle").equals("AIR")){
                    cakerecipe.setIngredient('B',Material.valueOf(getConfig().getString("recipe.shaped.ingredients.top_middle")));
                }
                if(!getConfig().getString("recipe.shaped.ingredients.top_right").equals("AIR")){
                    cakerecipe.setIngredient('C',Material.valueOf(getConfig().getString("recipe.shaped.ingredients.top_right")));
                }
                if(!getConfig().getString("recipe.shaped.ingredients.mid_left").equals("AIR")){
                    cakerecipe.setIngredient('D',Material.valueOf(getConfig().getString("recipe.shaped.ingredients.mid_left")));
                }
                if(!getConfig().getString("recipe.shaped.ingredients.mid_middle").equals("AIR")){
                    cakerecipe.setIngredient('E',Material.valueOf(getConfig().getString("recipe.shaped.ingredients.mid_middle")));
                }
                if(!getConfig().getString("recipe.shaped.ingredients.mid_right").equals("AIR")){
                    cakerecipe.setIngredient('F',Material.valueOf(getConfig().getString("recipe.shaped.ingredients.mid_right")));
                }
                if(!getConfig().getString("recipe.shaped.ingredients.bot_left").equals("AIR")){
                    cakerecipe.setIngredient('G',Material.valueOf(getConfig().getString("recipe.shaped.ingredients.bot_left")));
                }
                if(!getConfig().getString("recipe.shaped.ingredients.bot_middle").equals("AIR")){
                    cakerecipe.setIngredient('H',Material.valueOf(getConfig().getString("recipe.shaped.ingredients.bot_middle")));
                }
                if(!getConfig().getString("recipe.shaped.ingredients.bot_right").equals("AIR")){
                    cakerecipe.setIngredient('I',Material.valueOf(getConfig().getString("recipe.shaped.ingredients.bot_right")));
                }
                Bukkit.addRecipe(cakerecipe);
            }
        }
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            if(sender.hasPermission("infinicake.admin")){
                if(!(sender instanceof Player)){
                    sender.sendMessage("Console cannot receive infinicakes :(");
                    return true;
                }
                Player p = (Player) sender;
                for(ItemStack item : p.getInventory().getStorageContents()){
                    if(item == null){
                        p.getInventory().addItem(infinicake);
                        p.sendMessage("§aYou have received an infinicake!");
                        return true;
                    }
                }
                p.sendMessage("§cYour inventory cannot hold an infinicake D:");
            }else{
                sender.sendMessage("§cYou do not have permission to get free infinicakes :,(");
            }
        }else if(args[0].equalsIgnoreCase("reload")){
            reloadConfig();
            Bukkit.getPluginManager().disablePlugin(this);
            Bukkit.getPluginManager().enablePlugin(this);
            sender.sendMessage("§aReloaded InfiniCake config.");
        }
        return true;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onAnvilRename(PrepareAnvilEvent e){
        if(!useAnvil)return;
        ItemStack item1 = e.getInventory().getItem(0);
        if(item1 == null) return;
        ItemStack item2 = e.getInventory().getItem(1);
        if(item2 == null) return;
        if(!item1.getType().equals(Material.CAKE))return;
        if(!item2.getType().equals(Material.ENCHANTED_BOOK))return;
        if(!item2.hasItemMeta())return;
        EnchantmentStorageMeta item2meta = (EnchantmentStorageMeta) item2.getItemMeta();
        if(item2meta.hasStoredEnchant(Enchantment.ARROW_INFINITE)){
            ItemStack cakeItem = infinicake.clone();
            if(e.getInventory().getRenameText() != null){
                ItemMeta meta = cakeItem.getItemMeta();
                meta.setDisplayName(e.getInventory().getRenameText());
                cakeItem.setItemMeta(meta);
            }
            e.setResult(cakeItem);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    e.getInventory().setMaximumRepairCost(getConfig().getInt("anvil_cost") + 1);
                    e.getInventory().setRepairCost(getConfig().getInt("anvil_cost"));
                }
            });
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCakeDevour(PlayerInteractEvent e){
        if(e.isCancelled())return;
        if(!e.getAction().equals(Action.RIGHT_CLICK_BLOCK))return;
        if(e.getClickedBlock()==null)return;
        if(e.getClickedBlock().getBlockData() instanceof Cake){
            Cake cake = (Cake) e.getClickedBlock().getBlockData();
            if(CakeFile.isInfiniCake(e.getClickedBlock())){
                if(cake.getBites() >= 6){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {
                            if(e.getClickedBlock().getType().isAir()){
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.this, new Runnable() {
                                    @Override
                                    public void run() {
                                        respawnCake(e.getClickedBlock().getLocation(),true,true);
                                    }
                                },10);
                            }
                        }
                    });
                }
            }
        }
    }

    public void respawnCake(Location loc, boolean drop, boolean effects){
        CakeFile.removeInfiniCake(loc.getBlock());
        Location tospawn = loc;
        if(getConfig().getBoolean("disable_falling_cake")){
            loc.getBlock().setType(Material.CAKE);
            tospawn = loc.add(.5,0,.5);
        }else{
            if(drop){
                int x = 0;
                while(x < 10){
                    x++;
                    if(!loc.clone().add(0,x,0).getBlock().getType().isAir()){
                        break;
                    }
                    tospawn = loc.clone().add(.5,x,.5);
                }
            }
            CakeFile.removeInfiniCake(loc.getBlock());
            FallingBlock b = loc.getWorld().spawnFallingBlock(tospawn,Bukkit.createBlockData(Material.CAKE));
            b.setHurtEntities(getConfig().getBoolean("falling_cakes_destroy_items") || getConfig().getBoolean("falling_cakes_damage_living_entities"));
            b.setDropItem(false);
            b.setMetadata("infinicake",new FixedMetadataValue(this,""));
            final int[] ticks = {0};
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(ticks[0]++ > 100){
                        cancel();
                    }
                    if(b.isDead()){
                        if(!b.getLocation().getBlock().getType().equals(Material.CAKE) && getConfig().getBoolean("drop_infinicake_on_break")){
                            b.getWorld().dropItemNaturally(b.getLocation().clone().add(-.5,0,-.5),infinicake.clone());
                        }
                        cancel();
                    }
                }
            }.runTaskTimer(this,1,1);
        }
        if(effects && getConfig().getBoolean("infinicake_respawn_particles"))tospawn.getWorld().spawnParticle(Particle.BLOCK_DUST, tospawn.clone().add(0,.2,0), 50, .5,.3,.5, Material.CAKE.createBlockData());
        if(effects && getConfig().getBoolean("infinicake_respawn_pop"))tospawn.getWorld().playSound(tospawn.clone().add(0,.5,0), Sound.ENTITY_CHICKEN_EGG,1,.7F);
    }

    @EventHandler
    public void onSandLand(EntityChangeBlockEvent e){
        if(e.getEntity().hasMetadata("infinicake")){
            CakeFile.addInfiniCake(e.getBlock());
        }
    }

    @EventHandler
    public void onCakeMurder(PlayerDeathEvent e){
        if(!(e.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent))return;
        EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) e.getEntity().getLastDamageCause();
        if(!ev.getCause().equals(EntityDamageEvent.DamageCause.FALLING_BLOCK))return;
        if(ev.getDamager() instanceof FallingBlock){
            FallingBlock f = (FallingBlock) ev.getDamager();
            if(!f.getBlockData().getMaterial().equals(Material.CAKE))return;
            if(getConfig().getBoolean("falling_cakes_damage_living_entities"))
                e.setDeathMessage(e.getDeathMessage().replace("falling block","falling " + f.getBlockData().getMaterial().name().toLowerCase().replace("_"," ")));
        }
    }

    @EventHandler
    public void onCakeStomp(EntityDamageByEntityEvent e){
        if(!e.getCause().equals(EntityDamageEvent.DamageCause.FALLING_BLOCK))return;
        if(e.getDamager() instanceof FallingBlock){
            FallingBlock f = (FallingBlock) e.getDamager();
            if(!f.getBlockData().getMaterial().equals(Material.CAKE))return;

            if(e.getEntity() instanceof Item && !getConfig().getBoolean("falling_cakes_destroy_items"))e.setCancelled(true);
            if(e.getEntity() instanceof LivingEntity && !getConfig().getBoolean("falling_cakes_damage_living_entities"))e.setCancelled(true);

        }
    }

    @EventHandler
    public void onCakePhysics(BlockPhysicsEvent e){
        if(e.getBlock().getType().equals(Material.CAKE)){
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    if(e.getBlock().getType().isAir()){
                        if(CakeFile.isInfiniCake(e.getBlock())){

                            //Bukkit.getPlayer("avixk").sendMessage("CAKEPHYSICS");
                            respawnCake(e.getBlock().getLocation().add(.5,0,.5),false, false);
                            /*if(getConfig().getBoolean("drop_infinicake_on_break")){
                                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),infinicake.clone());
                            }*/
                        }
                    }
                }
            });
        }
    }

    @EventHandler
    public void onPistonRetractCake(BlockPistonRetractEvent e){
       /* if(e.getBlock().getRelative(BlockFace.UP).getType().equals(Material.CAKE)){
            if(CakeFile.isInfiniCake(e.getBlock().getRelative(BlockFace.UP))){
                Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.getPlayer("avixk").sendMessage("CAKEPISTONRETRACT1");
                        respawnCake(e.getBlock().getRelative(BlockFace.UP).getLocation().add(.5,0,.5),false,false);
                    }
                },5);
                //Bukkit.getPlayer("avixk").sendMessage("CAKEPISTONRETRACt");
            }
        }*/
        for(Block b : e.getBlocks()){
            if(b.getRelative(BlockFace.UP).getType().equals(Material.CAKE)){
                Block cake = b.getRelative(BlockFace.UP);
                if(CakeFile.isInfiniCake(cake)){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {
                            if(cake.getType().isAir()){
                                //Bukkit.getPlayer("avixk").sendMessage("CAKEPISTONRETRACT2");
                                respawnCake(cake.getLocation().add(.5,0,.5),false,false);
                            }
                        }
                    },5);
                }
            }
        }
    }
    @EventHandler
    public void onPistonExtendCake(BlockPistonExtendEvent e){
        for(Block b : e.getBlocks()){
            if(b.getType().equals(Material.CAKE)){
                if(CakeFile.isInfiniCake(b)){
                    Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {//Bukkit.getPlayer("avixk").sendMessage("CAKEPISTONEXTEND");
                            respawnCake(b.getLocation(),true,true);
                        }
                    },10);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCakePlace(BlockPlaceEvent e){
        if(e.isCancelled())return;
            if(e.getItemInHand().getType().equals(Material.CAKE)){
                if(e.getItemInHand().hasItemMeta()){
                    if(e.getItemInHand().getItemMeta().hasEnchant(Enchantment.ARROW_INFINITE)){
                        CakeFile.addInfiniCake(e.getBlock());
                    }
                }
            }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCakeBreak(BlockBreakEvent e){
        if(e.isCancelled())return;
        if(!e.isDropItems())return;
        if(!e.getBlock().getType().equals(Material.CAKE))return;
        if(!CakeFile.isInfiniCake(e.getBlock()))return;
        CakeFile.removeInfiniCake(e.getBlock());
        if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))return;
        if(getConfig().getBoolean("drop_infinicake_on_break")){
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),infinicake);
        }
    }
}
