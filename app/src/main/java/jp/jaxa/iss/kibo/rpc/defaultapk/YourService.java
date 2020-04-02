package jp.jaxa.iss.kibo.rpc.defaultapk;

import android.graphics.Bitmap;
import android.util.Log;

import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.common.HybridBinarizer;
/*
import com.google.zxing.qrcode.QRCodeReader;

import org.opencv.core.Mat;
import org.opencv.objdetect.QRCodeDetector;

import org.opencv.core.Mat;

import org.opencv.objdetect.QRCodeDetector;
import org.opencv.core.Mat;
*/


/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {

    private String pos_x;
    private String pos_y;
    private String pos_z;
    private String qua_x;
    private String qua_y;
    private String qua_z;
    private String qua_w;
    private final Point qr0_point =new Point(11.5, -5.7, 4.5);
    private final Point qr1_point =new Point(11, -6, 5.55);
    private final Point qr2_point =new Point(11, -5.5, 4.33);
    private final Point qr3_point =new Point(10.30, -7.5, 4.7);
    private final Point qr4_point =new Point(11.5, -8, 5);
    private final Point qr5_point =new Point(11, -7.7, 5.55);
    private final Quaternion qr0_quaternion =  new Quaternion((float)0, (float)0, (float)0, (float)1);
    private final Quaternion qr1_quaternion =  new Quaternion((float)0, (float)-0.7071068, (float)0, (float)0.7071068);
    private final Quaternion qr2_quaternion =  new Quaternion((float)0, (float)0.7071068, (float)0, (float)0.7071068);
    private final Quaternion qr3_quaternion =  new Quaternion((float)0, (float)0, (float)1, (float)0);
    private final Quaternion qr4_quaternion =  new Quaternion((float)0, (float)0, (float)0, (float)1);
    private final Quaternion qr5_quaternion =  new Quaternion((float)0, (float)-0.7071068, (float)0, (float)0.7071068);


    @Override
    protected void runPlan1(){
        final int LOOP_MAX = 3;
        int loopCounter = 0;
        api.judgeSendStart();

       // moveToWrapper(10.95, -3.75, 4.5, 0, 0, -0.7071068, 0.7071068);
        moveToWrapper(11, -5.5, 4.33,0, 0.7071068, 0, 0.7071068);
        do { moveToWrapperLittle(11, -5.5, 4.33,0, 0.7071068, 0, 0.7071068);
            pos_z = readQRImage(api.getBitmapNavCam());
            ++loopCounter;
        }while ((pos_z.equals("error") &&loopCounter<LOOP_MAX));
        Log.d("myTag",pos_z);
        api.judgeSendDiscoveredQR(2,pos_z );
        loopCounter = 0;

        moveToWrapper(qr0_point.getX()-0.1177, -5.7+0.0422, 4.5+0.0826, 0, 0, 0, 1);
        do { moveToWrapperLittle(11.5, -5.7, 4.5, 0, 0, 0, 1);
            pos_x = readQRImage(api.getBitmapNavCam());
            ++loopCounter;
        }while ((pos_x.equals("error") &&loopCounter<LOOP_MAX));

        Log.d("myTag",pos_x);
        api.judgeSendDiscoveredQR(0,pos_x );
        loopCounter = 0;



        moveToWrapper(11, -6, 5.55,0, -0.7071068, 0, 0.7071068);
        do { moveToWrapperLittle(11, -6, 5.55,0, -0.7071068, 0, 0.7071068);
            pos_y = readQRImage(api.getBitmapNavCam());
            ++loopCounter;
        }while ((pos_y.equals("error") &&loopCounter<LOOP_MAX));
        Log.d("myTag",pos_y);
        api.judgeSendDiscoveredQR(1,pos_y );
        loopCounter = 0;

        moveToWrapper(10.5, -6, 5.55, 0, 0.7071068, 0, 0.7071068);
        moveToWrapper(10.5, -6.8, 5.55, 0, 0.7071068, 0, 0.7071068);
        moveToWrapper(11, -6.8, 5.55, 0, 0.7071068, 0, 0.7071068);

        moveToWrapper(qr5_point,qr5_quaternion);
        do { moveToWrapperLittle(qr5_point,qr5_quaternion);
            qua_z = readQRImage(api.getBitmapNavCam());
            ++loopCounter;
        }while ((qua_z.equals("error") &&loopCounter<LOOP_MAX));
        Log.d("myTag",qua_z);
        api.judgeSendDiscoveredQR(5,qua_z );
        loopCounter = 0;

        moveToWrapper(qr3_point,qr3_quaternion);
        do { moveToWrapperLittle(qr3_point,qr3_quaternion);
            qua_x = readQRImage(api.getBitmapNavCam());
            ++loopCounter;
        }while ((qua_x.equals("error") &&loopCounter<LOOP_MAX));
        Log.d("myTag",qua_x);
        api.judgeSendDiscoveredQR(3,qua_x );
        loopCounter = 0;

        moveToWrapper(qr4_point,qr4_quaternion);
        do { moveToWrapperLittle(qr4_point,qr4_quaternion);
            qua_y = readQRImage(api.getBitmapNavCam());
            ++loopCounter;
        }while ((qua_y.equals("error") &&loopCounter<LOOP_MAX));
        Log.d("myTag",qua_y);
        api.judgeSendDiscoveredQR(4,qua_y );
        loopCounter = 0;



        float pos_x_f =  Float.parseFloat(pos_x.split(" ")[1]);
        float pos_y_f =  Float.parseFloat(pos_y.split(" ")[1]);
        float pos_z_f =  Float.parseFloat(pos_z.split(" ")[1]);
        float qua_x_f =  Float.parseFloat(qua_x.split(" ")[1]);
        float qua_y_f =  Float.parseFloat(qua_y.split(" ")[1]);
        float qua_z_f =  Float.parseFloat(qua_z.split(" ")[1]);
        Log.d("myTag",pos_x_f+" "+pos_y_f+" "+pos_z_f+" "+qua_x_f+" "+ qua_y_f+" "+qua_z_f);
        double qua_w_f = Math.sqrt(1-(qua_x_f*qua_x_f)-(qua_y_f*qua_y_f)-(qua_z_f*qua_z_f));
        Log.d("myTag", qua_w_f+" ");
        moveToWrapper(11.15, -8, 4.7,qr5_quaternion.getX(),qr5_quaternion.getY(),qr5_quaternion.getZ(),qr5_quaternion.getW());
        moveToWrapper(11.15, -9.2, 4.7,qr5_quaternion.getX(),qr5_quaternion.getY(),qr5_quaternion.getZ(),qr5_quaternion.getW());
        moveToWrapper(pos_x_f,pos_y_f,pos_z_f,qua_x_f,qua_y_f,qua_z_f,qua_w_f);

        api.shutdownFactory();
        api.judgeSendFinishSimulation();


    }

    @Override
    protected void runPlan2(){
        // write here your plan 2
      /*   pos_x=ScanQR();
        Log.d("myTag",pos_x);
        api.judgeSendDiscoveredQR(0,pos_x );
       moveToWrapper(11, -6, 5.55, 0, -0.7071068, 0, 0.7071068);
        pos_y =ScanQR();
        Log.d("myTag",pos_y);
        api.judgeSendDiscoveredQR(1, pos_y);
        moveToWrapper(11, -5.5, 4.33, 0, 0.7071068, 0, 0.7071068);
        pos_z =ScanQR();
        Log.d("myTag",pos_z);
        api.judgeSendDiscoveredQR(2, pos_z);

 moveToWrapper(10.5, -5.5, 4.33, 0, 0.7071068, 0, 0.7071068);
        moveToWrapper(10.5, -6.8, 4.33, 0, 0.7071068, 0, 0.7071068);
        moveToWrapper(11, -6.8, 4.33, 0, 0.7071068, 0, 0.7071068);
        api.laserControl(true);
        moveToWrapper(11.1, -6, 5.55, 0, -0.7071068, 0, 0.7071068);
*/
    }

    @Override
    protected void runPlan3(){
        // write here your plan 3

        String pos_x;
        api.judgeSendStart();
        moveToWrapper(10.95, -3.75, 4.5, 0, 0, -0.7071068, 0.7071068);
        moveToWrapper(11.3, -5.7, 4.5, 0, 0, 0, 1);
        final int LOOP_MAX = 5;
        int loopCounter = 0;
        do {
            pos_x = readQRImage(api.getBitmapNavCam());
            ++loopCounter;
        }while ((pos_x.equals("error") &&loopCounter<LOOP_MAX));
        Log.d("myTag",pos_x);
        api.judgeSendDiscoveredQR(0,pos_x );
        api.shutdownFactory();
        api.judgeSendFinishSimulation();
    }

    // You can add your method
    private void moveToWrapper(double pos_x, double pos_y, double pos_z,
                               double qua_x, double qua_y, double qua_z,
                               double qua_w){

        final int LOOP_MAX = 3;
        final Point point = new Point(pos_x, pos_y, pos_z);
        final Quaternion quaternion = new Quaternion((float)qua_x, (float)qua_y,
                (float)qua_z, (float)qua_w);

        Result result = api.moveTo(point, quaternion, true);

        int loopCounter = 0;
        while(!result.hasSucceeded() || loopCounter < LOOP_MAX){
            result = api.moveTo(point, quaternion, true);
            ++loopCounter;
        }
    }
    private void moveToWrapper(Point point, Quaternion quaternion){

        final int LOOP_MAX = 3;

        Result result = api.moveTo(point, quaternion, true);

        int loopCounter = 0;
        while(!result.hasSucceeded() || loopCounter < LOOP_MAX){
            result = api.moveTo(point, quaternion, true);
            ++loopCounter;
        }
    }
    private void moveToWrapperLittle(double pos_x, double pos_y, double pos_z,
                                     double qua_x, double qua_y, double qua_z,
                                     double qua_w){
        final Point point = new Point(pos_x, pos_y, pos_z);
        final Quaternion quaternion = new Quaternion((float)qua_x, (float)qua_y,
                (float)qua_z, (float)qua_w);

        api.moveTo(point, quaternion, true);


    }
    private void moveToWrapperLittle(Point point, Quaternion quaternion){
        api.moveTo(point, quaternion, true);


    }
    /*private  String ScanQR(){
        final int LOOP_MAX = 3;
        int loopCounter = 0;
        String s_qr;
        do {
            s_qr = readQRImage(api.getBitmapNavCam());
            ++loopCounter;
        }while ((s_qr.equals("error") &&loopCounter<LOOP_MAX));
        return s_qr;
        }*/
    public static String readQRImage(Bitmap bMap) {

        String contents = null;

        int[] intArray = new int[bMap.getWidth()*bMap.getHeight()];
        //copy pixel data from the Bitmap into the 'intArray' array
        bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight());

        LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(), intArray);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        Reader reader = new MultiFormatReader();// use this otherwise ChecksumException
        try { com.google.zxing.Result result = reader.decode(bitmap);
            contents = result.getText();
            //byte[] rawBytes = result.getRawBytes();
            //BarcodeFormat format = result.getBarcodeFormat();
            //ResultPoint[] points = result.getResultPoints();
        } catch (NotFoundException|ChecksumException |FormatException e)
        { e.printStackTrace();
            contents= "error"; }

        return contents;
    }
    /*
    private  String ScanQR(){
        QRCodeReader qrCodeReader =new QRCodeReader();
        Bitmap bMap = api.getBitmapNavCam();
        int[] intArray = new int[bMap.getWidth()*bMap.getHeight()];
        LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(),intArray);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        try{
        com.google.zxing.Result result =qrCodeReader.decode(bitmap);
            return result.getText();
        }catch (NotFoundException e){
            Log.d("myTag", "NotFoundException");
            return "error";
        }catch (ChecksumException e){
            Log.d("myTag", "ChecksumException");
            return "error";
        }catch (FormatException e){
            Log.d("myTag", "FormatException");
            return  "error";
        }

    }

  private QRCodeDetector qrCodeDetector;
    private Mat navCAm;
    private Mat detectedQuadra;
    private Boolean checkDetected;
    private String s;
    private String ScanQR(){
        qrCodeDetector = new QRCodeDetector();
        final int LOOP_MAX = 4;
        int loopCounter = 0;

        do {
            navCAm = api.getMatNavCam();
            if(navCAm==null)
                continue;
            checkDetected= qrCodeDetector.detect(navCAm, detectedQuadra);
            ++loopCounter;
            Log.d("myTag", String.valueOf(loopCounter));
        }while (!checkDetected||(loopCounter<LOOP_MAX));
        s = qrCodeDetector.decode(navCAm,detectedQuadra);
        if(s==null)
            Log.d("myTag",checkDetected.toString()+s);
            api.judgeSendFinishSimulation();
        //String[] s_arr = s.split(", ",2);
    return s;
    }
*/

}

