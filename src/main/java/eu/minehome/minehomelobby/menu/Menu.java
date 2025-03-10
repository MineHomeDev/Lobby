package eu.minehome.minehomelobby.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.function.Consumer;

public interface Menu extends InventoryHolder {
    void click(Player player, int slot);

    void setItem(int slot, ItemStack item);

    void setItem(int slot, ItemStack item, Consumer<Player> action);

    void onSetItems();

    boolean usePlaceholders();

    void setPlaceholders();

    void update();

    Map<Integer, ItemStack> getItemsMap();
    Map<Integer, Consumer<Player>> getActionsMap();

    default void open(Player player) {
        if (usePlaceholders())
            setPlaceholders();

        onSetItems();
        player.openInventory(getInventory());
    }
}
