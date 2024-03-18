import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    java
    alias(libs.plugins.plugin.yml.bukkit)
    alias(libs.plugins.shadow)
}

version = "1.7.2"
group = "me.sword7"


repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo.mikeprimm.com/")
    maven("https://repo.codemc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    compileOnly(libs.spigot.api)
    compileOnly(libs.dynmap.core.api)

    implementation(libs.acf)
    implementation(libs.nbt.api)
    implementation(libs.xseries)
}

bukkit {
    name = "StarMail"
    version = rootProject.version.toString()
    main = "me.sword7.starmail.StarMail"
    apiVersion = "1.13"
    authors = listOf("sword7", "sarhatabaot")
    description = "A mailing system for Minecraft"
    softDepend = listOf("dynmap")
    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        minimize()
        archiveClassifier.set("")

        relocate("de.tr7zw.changeme.nbtapi", "me.sword7.starmail")
    }

}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
