package me.sword7.starmail.util.particle;


import com.cryptomorin.xseries.particles.ParticleDisplay;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Particle {

    public static void playCloud(final Player player) {
        Vector dir = player.getLocation().getDirection();
        player.getLocation().getWorld().spawnParticle(ParticleDisplay.of(org.bukkit.Particle.CLOUD).getParticle(), player.getLocation().add(dir.getX(), 1.5, dir.getY()), 16, 0.2, 0.2, 0.2, 0.2f);
    }
}
