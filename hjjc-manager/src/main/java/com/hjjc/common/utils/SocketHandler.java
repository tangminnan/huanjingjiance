package com.hjjc.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjjc.common.config.ApplicationContextRegister;
import com.hjjc.information.dao.DataDao;
import com.hjjc.information.dao.DataInitDao;
import com.hjjc.information.dao.DeviceDao;
import com.hjjc.information.dao.OwnerUserDao;
import com.hjjc.information.domain.DataDO;
import com.hjjc.information.domain.DataInitDO;
import com.hjjc.information.domain.DeviceDO;
import com.hjjc.information.domain.OwnerUserDO;
import com.hjjc.information.service.DataInitService;
import com.hjjc.information.service.impl.DataInitServiceImpl;
import com.hjjc.system.config.MessageHandler;
import com.hjjc.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocketHandler implements  Runnable{
    private Socket socket;
    public SocketHandler(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        BufferedReader bufferedReader=null;
        PrintWriter printWriter =null;
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

            String body =null;
            while((body = bufferedReader.readLine())!=null && body.length()>0){
                System.out.println("The Server receive msg: "+body.toString());
                TextMessage textMessage = new TextMessage(body.toString());
                MessageHandler messageHandler = new MessageHandler();
                messageHandler.sendMessage("serversocket", textMessage);
                DataInitDO dataInitDO = new DataInitDO();
                dataInitDO.setData(body);
                dataInitDO.setAddTime(new Date());
                DataInitDao dataInitDao = ApplicationContextRegister.getBean(DataInitDao.class);
                int i  = dataInitDao.save(dataInitDO);
                if(i>0){
                    String[] strings = body.split(",");
                    Map<String,String> map = new HashMap<>();
                    for(int j=0,size = strings.length;j<size;j++){
                        String str = strings[j];
                        String[] entry = str.split(":");
                        map.put(entry[0].trim(),entry[1]);
                        System.out.println(map);
                    }
                    DataDO dataDO = new DataDO();
                    dataDO.setInitId(dataInitDO.getId());
                    dataDO.setAddTime(new Date());
                    dataDO.setGid(map.get("GW_ID").trim());
                    dataDO.setHumidity(map.get("H"));
                    dataDO.setLumen(map.get("Lumen"));
                    dataDO.setTemp(map.get("T"));
                    dataDO.setRssi(map.get("RSSI"));
                    dataDO.setSn(map.get("SN"));
                    dataDO.setVoltage(map.get("V"));
                    dataDO.setType(map.get("TYPE"));
                    dataDO.setLatitude(map.get("S"));
                    dataDO.setLongitude(map.get("E"));
                    dataDO.setDid(map.get("ID").trim());
                    dataDO.setStat(map.get("STAT"));
                    dataDO.setSt(map.get("ST"));
                    DeviceDao deviceDao = ApplicationContextRegister.getBean(DeviceDao.class);
                    Map<String,Object> paramsMap =new HashMap<>();
                    paramsMap.put("identity",map.get("ID").trim());
                    List<DeviceDO> list = deviceDao.list(paramsMap);
                    if(list.size()>0){
                        dataDO.setUserId(list.get(0).getUserId());
                        DataDao dataDao = ApplicationContextRegister.getBean(DataDao.class);
                        dataDao.save(dataDO);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(printWriter!=null){
                printWriter.close();
            }
            if (socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
