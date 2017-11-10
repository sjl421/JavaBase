package com.fzy.socket.udp;


import com.fzy.serializable.SerializableUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * udp数据传输,客户端发送端
 * @author fuzhongyu
 * @date 2017/11/8
 */
public class SocketUdpClient {

    /**
     * 客户端用udp方式发送数据
     * @param ip  远程服务端ip
     * @param port 远程服务端端口
     * @param data  传输数据
     * @return
     * @throws IOException
     */
    public static Object sendData(String ip,Integer port,Object data) {
        try {
            //设置远程服务端地址
            InetSocketAddress inetSocketAddress=new InetSocketAddress(ip,port);
            //创建传输层协议使用UDP发送和接收数据报的socket
            DatagramSocket socket=new DatagramSocket();
            //数据序列化
            byte buf[]= SerializableUtils.serialize(data);
            //放入传输包
            DatagramPacket packet=new DatagramPacket(buf,buf.length);
            packet.setSocketAddress(inetSocketAddress);
            //传输
            socket.send(packet);
            //接收服务端返回数据
            socket.receive(packet);
            if(packet.getData()!=null){
                return SerializableUtils.unserialize(packet.getData());
            }

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("---- 创建socket失败  -----");
        }
        return null;
    }


}
