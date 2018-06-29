package com.georlegacy.general.likom.modules.base;

import com.georlegacy.general.likom.modules.ModuleListeners;

import java.awt.event.ActionListener;
import java.util.HashMap;

public class ModuleHandler {

    private HashMap<String, ActionListener> modules;

    public ModuleHandler() {
        this.modules = new HashMap<String, ActionListener>();

        modules.put("System", new ModuleListeners.System());
        modules.put("HashTags", new ModuleListeners.HashTags());
        modules.put("Interval", new ModuleListeners.Interval());
        modules.put("BlacklistUsers", new ModuleListeners.BlacklistUsers());
        modules.put("Comments", new ModuleListeners.Comments());
    }

    public HashMap<String, ActionListener> getModules() {
        return modules;
    }

    public ActionListener getByName(String name) {
        return modules.getOrDefault(name, null);
    }

}
