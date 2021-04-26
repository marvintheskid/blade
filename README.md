# Blade

Blade is an easy to use command framework based on annotations. It currently only supports Bukkit, but it can be easily extended to more platforms.
To use Blade, you simply have to include it as a dependency and shade it into your final jar.

If you make any changes or improvements to the project, please consider making a pull request
to merge your changes back into the upstream project.

## TODO
- [ ] Fix tab completion

## Example code

Initialize blade:
```java
import me.vaperion.blade.Blade;
import me.vaperion.blade.command.bindings.impl.BukkitBindings;
import me.vaperion.blade.command.container.impl.BukkitCommandContainer;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Blade.of()
                .fallbackPrefix("fallbackPrefix")
                .containerCreator(BukkitCommandContainer.CREATOR)
                .binding(new BukkitBindings())
                .build()
                .register(ExampleCommand.class);
    }

}
```

Command class:
```java
import me.vaperion.blade.command.annotation.*;
import org.bukkit.entity.Player;

public class ExampleCommand {
    
    @Command(value = {"ban", "go away"}, async = true, quoted = false, description = "Ban a player")
    @Permission(value = "blade.command.ban", message = "You are not allowed to execute this command.")
    public static void ban(@Sender Player sender,
                           @Flag(value = 's', description = "Silently ban the player") boolean silent,
                           @Name("target") Player target,
                           @Name("reason") @Combined String reason) {
        sender.sendMessage("Silent: " + silent);
        sender.sendMessage("Target: " + target.getName());
        sender.sendMessage("Reason: " + reason);
    }
    
    @Command("test")
    public static void test(@Sender Player sender,
                            @Range(min = 18) int age,
                            @Optional("100") @Range(max = 100000) double balance) {
        sender.sendMessage("Age: " + age);
        sender.sendMessage("Balance: " + balance);
    }
}
```

## Using Blade

You can use JitPack to easily add Blade to Maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.vaperion</groupId>
        <artifactId>blade</artifactId>
        <version>1.0.0</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```