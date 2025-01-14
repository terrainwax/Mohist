package red.mohist.pluginmanager;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import red.mohist.i18n.Message;

import java.io.File;
import java.util.ArrayList;

public class PluginManagers {

	public static boolean hasPermission(CommandSender sender) {
		if (sender != Bukkit.getServer().getConsoleSender()) {
			if (sender.isOp()) {
				return true;
			}

			sender.sendMessage(Message.getString(Message.command_nopermission));
			return false;
		}
		return true;
	}

	public static boolean loadPluginCommand(CommandSender sender, String label, String[] split) {
		if (!hasPermission(sender)) {
			return true;
		}

		if (split.length < 2) {
			Object[] f = {label};
			sender.sendMessage(Message.getFormatString(Message.pluginscommand_load, f));
			return true;
		}
        String jarName = split[1] + (split[1].endsWith(".jar") ? "" : ".jar");
        File toLoad = new File("plugins" + File.separator + jarName);

        if (!toLoad.exists()) {
            jarName = split[1] + (split[1].endsWith(".jar") ? ".unloaded" : ".jar.unloaded");
            toLoad = new File("plugins" + File.separator + jarName);
            if (!toLoad.exists()) {
				Object[] f = {split[1]};
				sender.sendMessage(Message.getFormatString(Message.pluginscommand_nofile, f));
                return true;
            } else {
                String fileName = jarName.substring(0, jarName.length() - (".unloaded".length()));
                toLoad = new File("plugins" + File.separator + fileName);
                File unloaded = new File("plugins" + File.separator + jarName);
                unloaded.renameTo(toLoad);
            }
        }

		PluginDescriptionFile desc = Control.getDescription(toLoad);
		if (desc == null) {
			Object[] f = {split[1]};
			sender.sendMessage(Message.getFormatString(Message.pluginscommand_noyml, f));
			return true;
		}
        Plugin[] pl = Bukkit.getPluginManager().getPlugins();
        ArrayList<Plugin> plugins = new ArrayList<Plugin>(java.util.Arrays.asList(pl));
        for(Plugin p: plugins) {
            if (desc.getName().equals(p.getName())) {
				Object[] f = {desc.getName()};
				sender.sendMessage(Message.getFormatString(Message.pluginscommand_alreadyloaded, f));
                return true;
            }
        }
		Plugin p = null;
		if ((p = Control.loadPlugin(toLoad)) != null) {
			Control.enablePlugin(p);
			Object[] d = {p.getDescription().getName(), p.getDescription().getVersion()};
			sender.sendMessage(Message.getFormatString(Message.pluginscommand_loaded, d));
		} else {
			Object[] d = {split[1]};
			sender.sendMessage(Message.getFormatString(Message.pluginscommand_notload, d));
		}

		return true;
	}

	public static boolean unloadPluginCommand(CommandSender sender, String label, String[] split) {
		if (!hasPermission(sender)) {
			return true;
		}

		if (split.length < 2) {
			Object[] f = {label};
			sender.sendMessage(Message.getFormatString(Message.pluginscommand_unload, f));
			return true;
		}

		Plugin p = Bukkit.getServer().getPluginManager().getPlugin(split[1]);

		if (p == null) {
			Object[] f = {split[1]};
			sender.sendMessage(Message.getFormatString(Message.pluginscommand_noplugin, f));
		} else {
			if (Control.unloadPlugin(p, true)) {
				Object[] d = {p.getDescription().getName(), p.getDescription().getVersion()};
				sender.sendMessage(Message.getFormatString(Message.pluginscommand_unloaded, d));
			} else {
				Object[] d = {split[1]};
				sender.sendMessage(Message.getFormatString(Message.pluginscommand_notunload, d));
			}
		}

		return true;
	}

	public static boolean reloadPluginCommand(CommandSender sender, String label, String[] split) {
		if (!hasPermission(sender)) {
			return true;
		}

		if (split.length < 2) {
			Object[] f = {label};
			sender.sendMessage(Message.getFormatString(Message.pluginscommand_reload, f));
			return true;
		}

		Plugin p = Bukkit.getServer().getPluginManager().getPlugin(split[1]);

		if (p == null) {
			Object[] f = {split[1]};
			sender.sendMessage(Message.getFormatString(Message.pluginscommand_noplugin, f));
		} else {
			File file = Control.getFile((JavaPlugin) p);

			if (file == null) {
				Object[] f = {p.getName()};
				sender.sendMessage(Message.getFormatString(Message.pluginscommand_nojar, f));
				return true;
			}

			File name = new File("plugins" + File.separator + file.getName());
			JavaPlugin loaded = null;
			if (!Control.unloadPlugin(p, false)) {
				Object[] f = {split[1]};
				sender.sendMessage(Message.getFormatString(Message.pluginscommand_unloaderror, f));
			} else if ((loaded = (JavaPlugin) Control.loadPlugin(name)) == null) {
				Object[] f = {split[1]};
				sender.sendMessage(Message.getFormatString(Message.pluginscommand_nojar, f));
			}

			Control.enablePlugin(loaded);
			Object[] d = {split[1], loaded.getDescription().getVersion()};
			sender.sendMessage(Message.getFormatString(Message.pluginscommand_reloaded, d));
		}
		return true;
	}
}
