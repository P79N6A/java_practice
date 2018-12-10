package com.jiao.spit;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.annotation.Bean;
        import util.IdWorker;

/**
 * Created by jiao on 12/10/2018.
 */
@SpringBootApplication
public class SpitApplication {

    public static void main(String[] args){
        SpringApplication.run(SpitApplication.class, args);
    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
