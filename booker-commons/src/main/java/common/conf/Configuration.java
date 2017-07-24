package common.conf;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by phiha on 15/07/2017.
 */
public class Configuration {

    private static Configuration INSTANCE;
//    public static Logger LOG = LoggerFactory.getLogger(Configuration.class);

    private String configFile = "conf/booker.properties";
    private HashMap<String, String> map = new HashMap<>();

    private Configuration() {
        load();
    }

    public static Configuration getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuration();
        }
        return INSTANCE;
    }

    public String getConfig(String key) {
        return map.get(key);
    }

    private void load() {
        load(configFile);
    }

    private void load(String sourceFile) {

        Properties properties = new Properties();

        try {
            properties.load(new FileReader(new File(sourceFile)));
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                map.put(key, value);
            }
        } catch (IOException e) {
//            LOG.error("Load file config " + sourceFile, e);
        }

    }

    public void add(String key, String value) {
        map.put(key, value);
    }


}
