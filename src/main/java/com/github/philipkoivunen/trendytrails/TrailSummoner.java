package com.github.philipkoivunen.trendytrails;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TrailSummoner implements Listener {
    public Particle particle;
    public Player player;
    public Boolean isActive = false;
    public Boolean useDust = false;
    public String currentColor;

    public void setParticle(String particle) { this.particle = Particle.valueOf(particle.toUpperCase());}
    public void setPlayer(Player player) { this.player = player;}
    public void setColor(String color) { this.currentColor = color;}
    public void setIsActive(Boolean isActive) {this.isActive = isActive;}
    public void setUseDust(Boolean useDust) {this.useDust = useDust;}
}
