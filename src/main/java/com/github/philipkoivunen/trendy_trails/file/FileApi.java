package com.github.philipkoivunen.trendy_trails.file;

import com.github.philipkoivunen.trendy_trails.TrailSummoner;
import com.github.philipkoivunen.trendy_trails.TrendyTrailsPlugin;
import com.github.philipkoivunen.trendy_trails.constants.TrailConstants;
import com.github.philipkoivunen.trendy_trails.objects.PlayerTrailsHolder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

public class FileApi {
    private static final String FILE_NAME = "users";
    private static final String USERS_FIELD = "users";
    private static final String PLAYER_ID_FIELD = "player_id";
    private static final String TRAIL_NAME_FIELD = "trail_name";
    private static final String TRAIL_COLOR_FIELD = "trail_color";
    private final File usersFile;

    public FileApi(Plugin plugin) {
        usersFile = new File(plugin.getDataFolder(), FILE_NAME);
    }
    public void fetchUsers() {
        File userFile = new File(usersFile + ".yml");

        CompletableFuture.supplyAsync(() -> {
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(userFile);



            PlayerTrailsHolder playerTrailsHolder = TrendyTrailsPlugin.getInstance().getPlayerTrailsHolder();
            List<Map<String, Object>> entries = (List<Map<String, Object>>)yaml.getList(USERS_FIELD);

            //ConfigurationSection users = yaml.getConfigurationSection("users");
            for (Map<String, Object> entry : entries) {
                UUID player = java.util.UUID.fromString((String) entry.get(PLAYER_ID_FIELD));
                String trailName = (String) entry.get(TRAIL_NAME_FIELD);
                String trailColor = (String) entry.get(TRAIL_COLOR_FIELD);

                playerTrailsHolder.addTrail(player, new TrailSummoner());
                TrailSummoner currentTrail = playerTrailsHolder.getTrail(player);
                        currentTrail.setPlayer(player);
                if(trailName.toUpperCase().equals(TrailConstants.REDSTONE.name()) && trailColor != null) {
                    currentTrail.setParticle(trailName);
                    currentTrail.setColor(trailColor);
                    currentTrail.setIsActive(true);
                    currentTrail.setUseDust(true);
                } else {
                    currentTrail.setParticle(trailName);
                    currentTrail.setColor(null);
                    currentTrail.setIsActive(true);
                    currentTrail.setUseDust(false);
                }
                TrendyTrailsPlugin.getInstance().getPlayerTrailsHolder().addTrail(player, currentTrail);
            }
            try{
                yaml.save(userFile);
            }catch (IOException ex) {
                TrendyTrailsPlugin.getInstance().getLogger().log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }
            return true;
        });

    }

    public void deleteUser(Player player) { }
    public void setUserTrail(PlayerTrailsHolder playerTrailsHolder){
        CompletableFuture.supplyAsync(() -> {
            File userFile = new File(usersFile + ".yml");

            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(userFile);
            List<Map<String, Object>> writeList = new ArrayList<>();

            for(TrailSummoner entry : playerTrailsHolder.getPlayerTrails().values()) {
                Map<String, Object> writeObject = new LinkedHashMap<>();
                writeObject.put(PLAYER_ID_FIELD, entry.player.toString());
                writeObject.put(TRAIL_NAME_FIELD, entry.particle.name());
                writeObject.put(TRAIL_COLOR_FIELD, entry.currentColor);
                writeList.add(writeObject);
            }

            yaml.set(USERS_FIELD, writeList);

            try{
                yaml.save(userFile);
            }catch (IOException ex) {
                TrendyTrailsPlugin.getInstance().getLogger().log(Level.SEVERE, ex.getMessage(), ex);
                return false;
            }
            return true;
        });
    }
}
