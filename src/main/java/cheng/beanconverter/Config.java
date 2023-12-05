package cheng.beanconverter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Data
public class Config {
    private String input;
    private String output;

    public static Optional<Config> readFromFile() {
        InputStream is;
        Path localPath = Paths.get("config.yml");
        try {
            if (Files.exists(localPath)) {
                is = Files.newInputStream(localPath);
            } else {
                is = Config.class.getResourceAsStream("/config.yml");
            }
            LoaderOptions options = new LoaderOptions();
            Yaml yaml = new Yaml(options);
            return Optional.of(yaml.loadAs(is, Config.class));
        } catch (IOException e) {
            log.error("open config.yml error", e);
        }
        return Optional.empty();
    }


}
