package classformat;

import java.io.*;


public class ResolveClassFile{

  public static void resolveClassFile(File file){
    if(file.exists()){
      try{
        FileInputStream in=new FileInputStream(file);
	DataInputStream dis=new DataInputStream(in);

	byte[] itemBuf=new byte[4];
	int read=dis.read(itemBuf,0,4);
	System.out.println("Bytes read:"+read);

        String magic=bytesToHexString(itemBuf);

	System.out.println("Magic:"+magic);
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
  }

  private static final String bytesToHexString(byte[] bArray){
     StringBuffer sb=new StringBuffer(bArray.length);
     String sTemp;
     for(int i=0;i<bArray.length;i++){
       sTemp=Integer.toHexString((int)(0xFF & bArray[i]));
       if(sTemp.length()<2)
         sb.append(0);
       sb.append(sTemp.toUpperCase());
     }
   
    return sb.toString();
  }

  public static void main(String[] args){
    File file=new File("classTest/ClassTest.class");
    System.out.println(file.getAbsolutePath());
    resolveClassFile(file);
  }
}
