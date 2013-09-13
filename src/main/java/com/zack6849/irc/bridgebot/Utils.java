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

import org.pircbotx.Colors;

/**
 *
 * @author Zack6849
 */
public class Utils
{
    //shamelessley stolen from TheReverend403

    /**
     * @param s The string to format
     * @return A string that formats all bukkit color codes as irc color codes
     */
    public static String bukkitcolorize(String s)
    {
        String color = "\u00A7";
        return s.replace(color + "0", Colors.BLACK)
                .replace(color + "1", Colors.DARK_BLUE)
                .replace(color + "2", Colors.DARK_GREEN)
                .replace(color + "3", Colors.TEAL)
                .replace(color + "4", Colors.RED)
                .replace(color + "5", Colors.PURPLE)
                .replace(color + "6", Colors.BROWN)
                .replace(color + "7", Colors.LIGHT_GRAY)
                .replace(color + "8", Colors.DARK_GRAY)
                .replace(color + "9", Colors.BLUE)
                .replace(color + "a", Colors.GREEN)
                .replace(color + "b", Colors.CYAN)
                .replace(color + "c", Colors.RED)
                .replace(color + "d", Colors.MAGENTA)
                .replace(color + "e", Colors.YELLOW)
                .replace(color + "f", Colors.WHITE)
                .replace(color + "r", Colors.NORMAL)
                .replace(color + "k", "")
                .replace(color + "l", Colors.BOLD)
                .replace(color + "m", "")
                .replace(color + "n", Colors.UNDERLINE)
                .replace(color + "o", "");
    }

    /**
     * @param s The string to format
     * @return A string that formats all irc color codes as bukkit color codes
     */
    public static String irccolorize(String s)
    {
        String color = "\u00A7";
        return s.replace(Colors.BLACK, color + "0")
                .replace(Colors.DARK_BLUE, color + "1")
                .replace(Colors.DARK_GREEN, color + "2")
                .replace(Colors.TEAL, color + "3")
                .replace(Colors.RED, color + "4")
                .replace(Colors.PURPLE, color + "5")
                .replace(Colors.BROWN, color + "6")
                .replace(Colors.LIGHT_GRAY, color + "7")
                .replace(Colors.DARK_GRAY, color + "8")
                .replace(Colors.BLUE, color + "9")
                .replace(Colors.GREEN, color + "a")
                .replace(Colors.CYAN, color + "b")
                .replace(Colors.RED, color + "c")
                .replace(Colors.MAGENTA, color + "d")
                .replace(Colors.YELLOW, color + "e")
                .replace(Colors.WHITE, color + "f")
                .replace(Colors.NORMAL, color + "r")
                .replace(Colors.BOLD, color + "l")
                .replace(Colors.UNDERLINE, color + "n");
    }
}
