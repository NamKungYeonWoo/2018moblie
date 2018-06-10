package Programing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientUser {

Socket client=null;
    
    String ipAddress;//���� ������ �ּ�
    static final int port=5000;
    
    BufferedReader reader;
    
    InputStream is;
    ObjectInputStream ois;
    
    OutputStream os;
    ObjectOutputStream oos;//��½�Ʈ�� ���� ����
    
    String sendData;//������ ���� �ڷḦ ������ ����
    String receiveData;//�������� ���� �ڷḦ ������
    //����
    
    public ClientUser(){}
    
    public ClientUser(String ip){//������ �����ε�.
        ipAddress=ip;//���� ������ �ּ� ����
        
        try{
            System.out.println("**�����**");
            client=new Socket(ipAddress,port);
//���� �������ּҿ� ��Ʈ��ȣ�� �ָ� Ŭ���̾�Ʈ ��Ĺ��ü��
//�����ǰ� �� ���� ���������� ��Ĺ��ü�� ������.
            reader= new BufferedReader(new InputStreamReader(System.in));
 
            os=client.getOutputStream();
            oos=new ObjectOutputStream(os);
            //��½�Ʈ�� ��ü ����
            is=client.getInputStream();
            ois=new ObjectInputStream(is);
            //�Է½�Ʈ�� ��ü ����
            
            System.out.print("�Է�:");
            
            		while((sendData = reader.readLine()) !=null){
            				oos.writeObject(sendData);//�о���� �ڷḦ ������

            				oos.flush();//��½�Ʈ���� ���.
            				if(sendData.equals("quit")) break;//while�� ����
            					receiveData=(String)ois.readObject();
            					//�������� �ٽ� ���� �ڷḦ �޾� ����.
            					System.out.println(client.getInetAddress()+"�� ����"+ "���� �޽���:"+receiveData);
            					System.out.print("�Է�:");
            				}
            is.close(); ois.close(); os.close(); oos.close();
  			client.close();
  
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);//Ŭ���̾�Ʈ ���α׷� ����
        }
    }//������
    
    public static void main(String[] args) {
          new ClientUser("xxx.xxx.xxx.xxx");
          /* cmd -> ipconfig
           * �� �ڽ� ��ǻ�� �ּҸ� ���ϴ� ip:127.0.0.1
           * �� �ڽ� ��ǻ�� �ּҸ� ���ϴ� :localhost
           * ���� ������ �ּҸ� �Է��ص� �ȴ�.
           * �� �ڽ� �Ŀ� ���� ���α׷��� �����߱� ������
           * 127.0.0.1 �Ǵ� localhost�� ����ص� �ȴ�.
           */
    }
}
