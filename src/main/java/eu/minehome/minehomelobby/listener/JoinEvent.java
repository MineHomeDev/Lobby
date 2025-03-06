package eu.minehome.minehomelobby.listener;

import eu.minehome.minehomelobby.MinehomeLobby;

import eu.minehome.minehomelobby.manager.WorldUpdate;
import eu.minehome.minehomelobby.utils.LobbyInventory;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static eu.minehome.minehomelobby.data.Data.*;

public class JoinEvent implements Listener {

    @EventHandler
    public void OnPlayerJoinEvent(PlayerJoinEvent e){

        Player p =e.getPlayer();

        if (p.getWorld().getName().equals(lobbyworld)){

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!p.isOnline()) {
                        cancel(); // this cancels it when they leave
                    }
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(actionbarmsg));
                }
            }.runTaskTimer(MinehomeLobby.getInstance() /*<-- your plugin instance*/, 5L, 5L); // again, may be running faster than needed

            if (!joinmsg.contains("null")){
                e.setJoinMessage(joinmsg + e.getPlayer().getName());
            }else {
                e.setJoinMessage(null);
            }
            p.setGameMode(GameMode.SURVIVAL);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.getInventory().clear();
            LobbyInventory.GetLobbyInventory(e.getPlayer());
            new WorldUpdate().DayNightCycle();
            //Edit Join Sound Level up Sound is better for Join event
            p.performCommand("spawn");

        }
    }
}
