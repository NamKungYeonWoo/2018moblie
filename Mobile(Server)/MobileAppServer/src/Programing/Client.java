package Programing;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
 
public class Client {
    public static void main(String... args){
        //�ڵ� close
        try(Socket client = new Socket()){
            //Ŭ���̾�Ʈ �ʱ�ȭ
/*            InetSocketAddress ipep = new InetSocketAddress("210.89.190.224", 9999);*/
        	InetSocketAddress ipep = new InetSocketAddress("192.0.0.1", 9999);
            //����
            client.connect(ipep);
             
            //send,reciever ��Ʈ�� �޾ƿ���
            //�ڵ� close
            try(OutputStream sender = client.getOutputStream();
                InputStream receiver = client.getInputStream();){
                //�����κ��� ������ �ޱ�
                //11byte
                byte[] data = new byte[11];
                receiver.read(data);
                 
                //���Ÿ޽��� ���
                String message = new String(data);
                String out = String.format("recieve - %s", message);
                System.out.println(out);
                 
                //������ ������ ������
                //2byte
                message = "OK";
                data = message.getBytes();
                sender.write(data, 0, data.length);
            }
        }catch(Throwable e){
            e.printStackTrace();
        }
    }
}
