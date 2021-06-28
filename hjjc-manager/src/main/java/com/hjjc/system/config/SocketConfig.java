package com.hjjc.system.config;

import com.hjjc.common.utils.SocketHandler;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@HandlesTypes({WebApplicationInitializer.class})
public class SocketConfig implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
      new Thread(()->{
          ServerSocket sever = null;
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

          }
      }).start();
    }
}

