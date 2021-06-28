package com.hjjc;

import com.hjjc.common.utils.SocketHandler;
import com.hjjc.system.config.MessageHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.hjjc.*.dao")
@SpringBootApplication
public class BootdoApplication extends SpringBootServletInitializer {
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootdoApplication.class);
    }
    public static void main(String[] args) throws IOException {
        SpringApplication.run(BootdoApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    bootdo启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
                " ______                    _   ______            \n" +
                "|_   _ \\                  / |_|_   _ `.          \n" +
                "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
                "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
                " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
                "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
    /*    ServerSocket sever = null;
        try{
            sever = new ServerSocket(8068);
            System.out.println("The server is start at port: "+8068);
            Socket socket = null;
            while (true){
                socket = sever.accept();
                new Thread(new SocketHandler(socket)).start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(sever!=null){
                try {
                    sever.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }*/
    }
}
