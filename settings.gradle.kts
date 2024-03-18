rootProject.name = "Starmail"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("spigot-api", "org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")

            version("plugin-yml", "0.6.0")
            plugin("plugin-yml-bukkit", "net.minecrell.plugin-yml.bukkit").versionRef("plugin-yml")
            plugin("shadow", "com.github.johnrengelman.shadow").version("8.1.1")

            library("acf", "co.aikar:acf-paper:0.5.1-SNAPSHOT")
            library("bstats", "org.bstats:bstats-bukkit:3.0.2")
            library("triumph-gui", "dev.triumphteam:triumph-gui:3.1.7")
            library("xseries", "com.github.cryptomorin:XSeries:9.9.0")
            library("dynmap-core-api", "us.dynmap:DynmapCoreAPI:3.4")
            library("nbt-api", "de.tr7zw:item-nbt-api:2.12.2")
        }
    }
}