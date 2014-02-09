package classformat;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import classformat.cptype.CONSTANT_Class_info;
import classformat.cptype.CONSTANT_Float_info;
import classformat.cptype.CONSTANT_Integer_info;
import classformat.cptype.CONSTANT_Utf8_info;
import classformat.cptype.ConstantPoolType;


public class ResolveClassFile{

  public static void resolveClassFile(File file){
    if(file.exists()){
      try{
        FileInputStream in=new FileInputStream(file);
        DataInputStream dis=new DataInputStream(in);

        //Extract Magic number
        byte[] itemBuf=new byte[4];
		int read=dis.read(itemBuf,0,4);
	    String magic=bytesToHexString(itemBuf,4);
		System.out.println("Magic:"+magic);
		
		//Extract minor version and major version
		read=dis.read(itemBuf,0,2);
		String minorVersion=bytesToHexString(itemBuf, 2);
		System.out.println("Minor version:"+minorVersion);
		
		read=dis.read(itemBuf,0,2);
		String majorVersion=bytesToHexString(itemBuf, 2);
		int mVersion=bytesToInt(itemBuf, 2);
		System.out.println("Major version:"+majorVersion+" int:"+mVersion);
		
		//Resolve Constant pool
		read=dis.read(itemBuf,0,2);
		String pool_count=bytesToHexString(itemBuf, 2);
		int constant_pool_count=bytesToInt(itemBuf,2);
		System.out.println("Constant pool count:"+pool_count+" "+constant_pool_count);
		
		ArrayList<ConstantPoolType> pool=new ArrayList<ConstantPoolType>();
		for(int i=0;i<2;i++){
//		for(int i=0;i<constant_pool_count;i++){
			read=dis.read(itemBuf,0,1);
			int tag=bytesToInt(itemBuf,1);
			switch(tag){
			case 1:
				read=dis.read(itemBuf,0,2);
				int length=bytesToInt(itemBuf,2);
				StringBuilder sb=new StringBuilder();
				boolean newStart=true;
				int bytesLeft=0;
				ArrayList<Byte> part=new ArrayList<Byte>();
				for(int j=0;j<length;j++){
					if(newStart){
						read=dis.read(itemBuf,0,1);
						if((itemBuf[0] & ((byte)1<<7)) == (byte)0){
							//The highest bit is 0
							byte[] res=new byte[1];
							res[0]=itemBuf[0];
							String temp=new String(res,"UTF-8");
							sb.append(temp);
						}else{
							//The first of the bytes
							newStart=false;
							//TODO may include more severe check. Below code not tested
							if((itemBuf[0] & ((byte)1<<5)) == (byte)0){
								bytesLeft=2;
								part.add((byte) (itemBuf[0] & ((byte)15)));
							}else{
								bytesLeft=1;
								part.add((byte)(itemBuf[0] & ((byte)31)));
							}
						}
					}else{
						//The subsequent bytes. Code not tested
						read=dis.read(itemBuf,0,1);
						part.add((byte)(itemBuf[0] & ((byte)63)));
						bytesLeft--;
						
						if(bytesLeft==0){
							String tmp=null;
							if(part.size()==2){
								int current=((part.get(0) & 0xFF) << 6) | (part.get(1) & 0xFF);
								tmp=new String(ByteBuffer.allocate(4).putInt(current).array(),"UTF-8");
							}else{
								int current=((part.get(0) & 0xFF) << 12) | ((part.get(1) & 0xFF) << 6) | (part.get(2) & 0xFF);
								tmp=new String(ByteBuffer.allocate(4).putInt(current).array(),"UTF-8");
							}
							sb.append(tmp);
							newStart=true;
							part=new ArrayList<Byte>();
						}
					}
				}
				CONSTANT_Utf8_info string=new CONSTANT_Utf8_info();
				string.setValue(sb.toString());
				pool.add(string);
				System.out.println("Constant pool: #"+i+"    utf8    "+sb.toString());
				break;
			case 3:
				read=dis.read(itemBuf,0,4);
				int intVal=bytesToInt(itemBuf,4);
				CONSTANT_Integer_info integer=new CONSTANT_Integer_info();
				integer.setValue(intVal);
				pool.add(integer);
				System.out.println("Constant pool: #"+i+"    int    "+intVal);
				break;
			case 4:
				read=dis.read(itemBuf,0,4);
				int floatVal=bytesToInt(itemBuf,4);
				CONSTANT_Float_info floatValue=new CONSTANT_Float_info();
				floatValue.setValue(floatVal);
				System.out.println("Constant pool: #"+i+"    float    "+floatVal);
				pool.add(floatValue);
				break;
			case 7:
				read=dis.read(itemBuf,0,2);
				int class_index=bytesToInt(itemBuf,2);
				CONSTANT_Class_info classinfo=new CONSTANT_Class_info();
				classinfo.setIndex(class_index);
				System.out.println("Constant pool: #"+i+"    class_index    "+class_index);
				break;
			}
		}
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
  }
  

  private static final String bytesToHexString(byte[] bArray,int length){
     StringBuffer sb=new StringBuffer(bArray.length);
     String sTemp;
     for(int i=0;i<bArray.length && i<length;i++){
       sTemp=Integer.toHexString((int)(0xFF & bArray[i]));
       if(sTemp.length()<2)
         sb.append(0);
       sb.append(sTemp.toUpperCase());
     }
   
    return sb.toString();
  }
  
  private static final int bytesToInt(byte[] bArray,int length){
	  int result=bArray[length-1] & 0xFF;
	  for(int i=length-2;i>=0;i--){
		  result=result | (bArray[i] & 0xFF) << 8*(length-1-i);
	  }
	  return result;
  }

  public static void main(String[] args){
    File file=new File("bin/classTest/ClassTest.class");
    System.out.println(file.getAbsolutePath());
    resolveClassFile(file);
  }
}
