package sample;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Math;

public class Benchmark {
    private double wSpeed;
    private double rSpeed;

    public void bench() throws IOException {
        byte[] test = new byte[100000];
        int iterat=0;
        for (byte Byte : test){
            test[iterat]= (byte) (Math.random()*100);
            iterat+=1;
        }
        for (byte Byte : test){
            System.out.println(Byte);
        }
        System.out.println("всё");
        long oldWriteTime = System.currentTimeMillis();
        double owTime= oldWriteTime/1000.0;
        FileOutputStream out = new FileOutputStream("testFile.txt");
        for (byte b : test) {
            out.write(b);
        }
        out.flush();
        out.close();
        long newWriteTime = System.currentTimeMillis();
        double nwTime= newWriteTime/1000.0;
        this.wSpeed= 100000.0/(nwTime-owTime);

        long oldReadTime = System.currentTimeMillis();
        FileInputStream in = new FileInputStream("testFile.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int numberOfBytedRead;
        while ((numberOfBytedRead = in.read(b)) >= 0) {
            baos.write(b, 0, numberOfBytedRead);
        }
        byte[] textfromfile=baos.toByteArray();
        long newReadTime = System.currentTimeMillis();
        this.rSpeed= 100000.0/(newReadTime-oldReadTime);
    }
    double getwSpeed(){
        return wSpeed;
    }
    double getrSpeed(){
        return  rSpeed;
    }
}
