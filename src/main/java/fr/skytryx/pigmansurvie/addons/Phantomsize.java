package fr.skytryx.pigmansurvie.addons;

import com.destroystokyo.paper.event.entity.PhantomPreSpawnEvent;
import org.bukkit.entity.Phantom;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import javax.swing.text.html.parser.Entity;

public class Phantomsize implements Listener {
    public void csize(Phantom event){
        event.setSize(4);
    }
}
