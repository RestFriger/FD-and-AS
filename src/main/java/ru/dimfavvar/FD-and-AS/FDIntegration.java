package ru.dimfavvar.fdintegration;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FDIntegration extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Farmer's Delight + AuraSkills интеграция загружена!");
    }

    // Событие: разрушение блока (break)
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        String key = block.getType().getKey().toString();
        String state = block.getBlockData().getAsString();

        double xp = 0;

        // Лук (onions) — age=7
        if ("farmersdelight:onions".equals(key) && state.contains("age=7")) {
            xp = 3.5;
        }
        // Капуста (cabbages) — age=7
        else if ("farmersdelight:cabbages".equals(key) && state.contains("age=7")) {
            xp = 2.0;
        }
        // Колония коричневых грибов (brown_mushroom_colony) — age=3
        else if ("farmersdelight:brown_mushroom_colony".equals(key) && state.contains("age=3")) {
            xp = 2.0;
        }
        // Колония красных грибов (red_mushroom_colony) — age=3
        else if ("farmersdelight:red_mushroom_colony".equals(key) && state.contains("age=3")) {
            xp = 2.0;
        }
        // Рис (rice / rice_panicles) — age=3
        else if (("farmersdelight:rice".equals(key) || "farmersdelight:rice_panicles".equals(key)) && state.contains("age=3")) {
            xp = 3.5;
        }
        // Томаты (tomatoes) — age=7 → но в таблице указано break/interact, поэтому обработаем отдельно
        // Картофель (Wild_potatoes) — без возраста
        else if ("farmersdelight:wild_potatoes".equals(key)) {
            xp = 3.5;
        }
        // Морковь (Wild_carrots) — без возраста
        else if ("farmersdelight:wild_carrots".equals(key)) {
            xp = 3.5;
        }
        // Дикие томаты (wild_tomatoes) — без возраста
        else if ("farmersdelight:wild_tomatoes".equals(key)) {
            xp = 2.5;
        }
        // Дикая свёкла (wild_beetroots) — без возраста
        else if ("farmersdelight:wild_beetroots".equals(key)) {
            xp = 2.5;
        }
        // Дикая капуста (wild_cabbages) — без возраста
        else if ("farmersdelight:wild_cabbages".equals(key)) {
            xp = 2.0;
        }
        // Дикий лук (wild_onions) — без возраста
        else if ("farmersdelight:wild_onions".equals(key)) {
            xp = 3.5;
        }
        // Дикий рис (wild_rice) — без возраста
        else if ("farmersdelight:wild_rice".equals(key)) {
            xp = 3.5;
        }

        if (xp > 0) {
            getServer().dispatchCommand(
                getServer().getConsoleSender(),
                "skills xp add " + event.getPlayer().getName() + " farming " + xp
            );
        }
    }

    // Событие: взаимодействие с блоком (interact) — для томатов
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;

        Block block = event.getClickedBlock();
        String key = block.getType().getKey().toString();
        String state = block.getBlockData().getAsString();

        double xp = 0;

        // Томаты (tomatoes) — age=3, действие: break или interact
        if ("farmersdelight:tomatoes".equals(key) && state.contains("age=3")) {
            xp = 2.5;
        }

        if (xp > 0) {
            getServer().dispatchCommand(
                getServer().getConsoleSender(),
                "skills xp add " + event.getPlayer().getName() + " farming " + xp
            );
        }
    }
}