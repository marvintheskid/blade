package me.vaperion.blade.bukkit;

import lombok.RequiredArgsConstructor;
import me.vaperion.blade.Blade;
import me.vaperion.blade.bukkit.container.BukkitContainer;
import me.vaperion.blade.container.ContainerCreator;
import me.vaperion.blade.platform.BladeConfiguration;
import me.vaperion.blade.platform.BladePlatform;
import me.vaperion.blade.platform.HelpGenerator;
import me.vaperion.blade.platform.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

@RequiredArgsConstructor
public class BladeBukkitPlatform implements BladePlatform {

    private final JavaPlugin plugin;

    @Override
    public @NotNull Object getPluginInstance() {
        return plugin;
    }

    @Override
    public @NotNull ContainerCreator<?> getContainerCreator() {
        return BukkitContainer.CREATOR;
    }

    @Override
    public void configureBlade(Blade.@NotNull Builder builder, @NotNull BladeConfiguration configuration) {
        configuration.setFallbackPrefix(plugin.getName().toLowerCase(Locale.ROOT));
        configuration.setHelpGenerator(new HelpGenerator.Default());
        configuration.setTabCompleter(new TabCompleter.Default());
    }
}
