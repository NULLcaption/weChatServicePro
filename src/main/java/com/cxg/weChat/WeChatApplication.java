package com.cxg.weChat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.cxg.weChat.*.mapper")
@SpringBootApplication
public class WeChatApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WeChatApplication.class, args);
		System.out.println("<-------Bluestruk Running----------> \n"+
				"__________.__                          __                         __    \n" +
				"\\______   \\  |  __ __   ____   _______/  |________   ____ _____  |  | __\n" +
				" |    |  _/  | |  |  \\_/ __ \\ /  ___/\\   __\\_  __ \\_/ __ \\\\__  \\ |  |/ /\n" +
				" |    |   \\  |_|  |  /\\  ___/ \\___ \\  |  |  |  | \\/\\  ___/ / __ \\|    < \n" +
				" |______  /____/____/  \\___  >____  > |__|  |__|    \\___  >____  /__|_ \\\n" +
				"        \\/                 \\/     \\/                    \\/     \\/     \\/");
	}

}
