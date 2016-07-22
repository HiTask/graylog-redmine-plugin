package ru.sidvi.graylog;

import org.graylog2.plugin.Plugin;
import org.graylog2.plugin.PluginMetaData;
import org.graylog2.plugin.PluginModule;

import java.util.Collection;
import java.util.Collections;

public class RedminePlugin implements Plugin {
    @Override
    public PluginMetaData metadata() {
        return new RedmineMetaData();
    }

    @Override
    public Collection<PluginModule> modules () {
        return Collections.<PluginModule>singletonList(new RedmineModule());
    }
}
