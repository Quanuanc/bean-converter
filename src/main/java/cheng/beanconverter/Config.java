package cheng.beanconverter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
@Data
public class Config {
    private String input;
    private String output;

    public static Optional<Config> readFromFile() {
        ClassLoader classLoader = Config.class.getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("config.yml")) {
            LoaderOptions options = new LoaderOptions();
            Yaml yaml = new Yaml(options);
            return Optional.of(yaml.loadAs(is, Config.class));
        } catch (IOException e) {
            log.error("open config.yml error", e);
        }
        return Optional.empty();
    }


}
