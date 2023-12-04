package cheng.beanconverter;

import lombok.extern.slf4j.Slf4j;

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
        log.debug("app start");
        log.debug("app config: {}", config);
        //todo get alipay.zip and wechat.zip from input dir
        log.debug("app stop");
    }

}
