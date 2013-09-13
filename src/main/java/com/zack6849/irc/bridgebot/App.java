/*
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.zack6849.irc.bridgebot;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
public class App extends JavaPlugin
{

    public static Plugin app;

    @Override
    public void onEnable()
    {
        Bot.start();
        App.app = this;
        getServer().getPluginManager().registerEvents(new BukkitHandler(this), this);
    }

    @Override
    public void onDisable()
    {
        Bot.stop();
    }
}