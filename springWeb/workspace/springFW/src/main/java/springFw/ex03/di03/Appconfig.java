package springFw.ex03.di03;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * packageName    : springFW.ex03.di03
 * fileName       : Appconfig
 * author         : Yeong-Huns
 * date           : 2024-05-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-29        Yeong-Huns       최초 생성
 */
@Configurable
@ComponentScan(basePackages = {"springFw.ex03.di03"})
public class Appconfig {
    @Bean
    IHelloService helloService(){
        return new HelloService();
    }
}
