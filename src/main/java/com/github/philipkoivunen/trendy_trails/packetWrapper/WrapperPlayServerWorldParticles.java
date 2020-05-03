/**
 * PacketWrapper - ProtocolLib wrappers for Minecraft packets
 * Copyright (C) dmulloy2 <http://dmulloy2.net>
 * Copyright (C) Kristian S. Strangeland
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
 */
package com.github.philipkoivunen.trendy_trails.packetWrapper;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedParticle;

public class WrapperPlayServerWorldParticles extends AbstractPacket {
    public static final PacketType TYPE = PacketType.Play.Server.WORLD_PARTICLES;

    public WrapperPlayServerWorldParticles() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperPlayServerWorldParticles(PacketContainer packet) {
        super(packet, TYPE);
    }

    public WrappedParticle getParticle() {
        return handle.getNewParticles().read(0);
    }

    public void setParticleType(WrappedParticle value) {
        handle.getNewParticles().write(0, value);
    }

    public double getX() {
        return handle.getDoubles().read(0);
    }

    public void setX(double value) {
        handle.getDoubles().write(0, value);
    }

    public double getY() {
        return handle.getDoubles().read(1);
    }

    public void setY(double value) {
        handle.getDoubles().write(1, value);
    }

    public double getZ() {
        return handle.getDoubles().read(2);
    }

    public void setZ(double value) {
        handle.getDoubles().write(2, value);
    }

    public float getOffsetX() {
        return handle.getFloat().read(0);
    }

    public void setOffsetX(float value) {
        handle.getFloat().write(0, value);
    }

    public float getOffsetY() {
        return handle.getFloat().read(1);
    }

    public void setOffsetY(float value) {
        handle.getFloat().write(1, value);
    }

    public float getOffsetZ() {
        return handle.getFloat().read(2);
    }

    public void setOffsetZ(float value) {
        handle.getFloat().write(2, value);
    }

    public float getParticleData() {
        return handle.getFloat().read(3);
    }

    public void setParticleData(float value) {
        handle.getFloat().write(3, value);
    }

    public int getNumberOfParticles() {
        return handle.getIntegers().read(0);
    }

    public void setNumberOfParticles(int value) {
        handle.getIntegers().write(0, value);
    }

    public boolean getLongDistance() {
        return handle.getBooleans().read(0);
    }

    public void setLongDistance(boolean value) {
        handle.getBooleans().write(0, value);
    }
}
