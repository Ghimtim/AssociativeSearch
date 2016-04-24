package com.jnu.associativesearch;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zhuang on 2016/3/8.
 */
public class ShellRunning {


    public static synchronized String run1(String cmd){
        Runtime runtime = Runtime.getRuntime();
        StringBuffer strBuff = new StringBuffer();
        InputStream in = null;
        DataOutputStream os = null;
        InputStreamReader isr = null;
      //  OutputStreamWriter osw = null;
        Process process = null;
        BufferedReader br = null;
       // BufferedWriter bw = null;
      //  BufferedReader errorReader = null;
        try {
            process = runtime.exec("ps");


            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("ls -l" + "\n");
            os.flush();

            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
           // System.out.println(br.toString());

           /* InputStream error = process.getErrorStream();
            errorReader = new BufferedReader(new InputStreamReader(error));
            Log.i("ERROR", errorReader.toString());*/




            String line = "";
            while((line = br.readLine()) != null){
                strBuff.append(line);
                strBuff.append("\n");
            }
            System.out.println("stringBuffer:" + strBuff);
            return strBuff.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error cmd";
        }finally {
            try {
                if(os != null){
                    os.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(br != null){
                    br.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
           /* try{
                if(errorReader != null){
                    errorReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }



    public static synchronized void run(String cmd){
        Runtime runtime = Runtime.getRuntime();
        StringBuffer strBuff = new StringBuffer();
        InputStream in = null;
        DataOutputStream os = null;
        // BufferedWriter bw = null;
        //  BufferedReader errorReader = null;
        Process process;
        try {
            process = runtime.exec("su");


            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.flush();

            in = process.getInputStream();
            byte[] buffer = new byte[1024];

            StringBuilder out = new StringBuilder();
            int c = 0;
          /*  while((c=in.read(buffer)) > 0 ){
                out.append(new String(buffer, 0, c));
                Log.i("c",String.valueOf(c));
            }*/

           while (true) {
                c = in.read(buffer);
                out.append(new String(buffer, 0, c));
                if (c < 1024) {
                    // we have read everything
                    break;
                }
            }

            Log.e("fuck", out.toString());


            // System.out.println(br.toString());

           /* InputStream error = process.getErrorStream();
            errorReader = new BufferedReader(new InputStreamReader(error));
            Log.i("ERROR", errorReader.toString());*/



/*
            String line = "";
            while((line = br.readLine()) != null){
                strBuff.append(line);
                strBuff.append("\n");
            }
            System.out.println("stringBuffer:" + strBuff);
            return strBuff.toString();*/
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(os != null){
                    os.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(in != null){
                    in.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
           /* try{
                if(errorReader != null){
                    errorReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }



    public static void suOutputExecute(String command) {
        try {
            int BUFF_LEN = 1024;
            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream stdin = new DataOutputStream(p.getOutputStream());
            // from here all commands are executed with su permissions
            stdin.writeBytes(command + "\n"); // \n executes the command
            InputStream stdout = p.getInputStream();
            byte[] buffer = new byte[BUFF_LEN];
            int read;
            String out = new String();
            // while((read=stdout.read(buffer))>0) won't work here
            while (true) {
                read = stdout.read(buffer);
                out += new String(buffer, 0, read);
                if (read < BUFF_LEN) {
                    // we have read everything
                    break;
                }
            }
            stdout.close();
            Log.e("ROOT", out);
            p.waitFor();
        } catch (Exception e) {
            Log.e("ROOT", "Error", e);
        }
    }


}
