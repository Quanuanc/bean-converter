package cheng.beanconverter;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class App {
    private final Config config;

    public App() {
        this.config = Config.readFromFile().orElseThrow();
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        log.info("app start");
        log.debug("app config: {}", config);
        Map<String, String> ledgerPath = getLedgerZipPath();
        log.debug("ledger path: {}", ledgerPath);
        log.info("app stop");
    }

    public Map<String, String> getLedgerZipPath() {
        String inputFolder = config.getInput();
        Map<String, String> map = new HashMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get(inputFolder))) {
            paths.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .forEach(s -> {
                        if (s.contains("微信支付账单"))
                            map.put("wechat", s);
                        else if (s.contains("alipay_record"))
                            map.put("alipay", s);
                    });
        } catch (IOException e) {
            log.error("getLedgerZipPath error", e);
        }
        return map;
    }

}
