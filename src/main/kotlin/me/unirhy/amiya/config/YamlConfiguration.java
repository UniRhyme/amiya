package me.unirhy.amiya.config;

import me.unirhy.amiya.PluginMain;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

public class YamlConfiguration {
    private File path;
    private Yaml config;
    private InputStream iStream;

    public YamlConfiguration(File path) {
        this.path = path;
        this.config = new Yaml();
        try {
            iStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            PluginMain.INSTANCE.getLogger().error("阿米娅未找到配置! 正在生成新的配置文件.");
            try {
                path.createNewFile();
                iStream = new FileInputStream(path);
            } catch (IOException ex) {
                PluginMain.INSTANCE.getLogger().error("阿米娅无法生成配置!");
                ex.printStackTrace();
                return;
            }
        }
    }
}
