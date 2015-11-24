/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondedition;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import jdk.nashorn.internal.parser.TokenType;
/**
 *
 * @author christine
 */
class StreamConsumer extends Thread {
    InputStream is;
    String type;
     
    StreamConsumer (InputStream is, String type) {
        this.is = is;
        this.type = type;
    }
     
    public void run () {
        try {
            InputStreamReader isr = new InputStreamReader (is);
            BufferedReader br = new BufferedReader (isr);
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println (type + ">" + line);    
        } catch (IOException ioe) {
            ioe.printStackTrace();  
        }
    }
}

public class JavaExecLab {
    private Runtime rt;
    private Process proc;
    private int exitcode;
    private String file_name, file_path;
    
    public JavaExecLab(String file_name, String file_path) {
        this.file_name = file_name;
        this.file_path = file_path;
    }

/**
 * 
 * @return 
 */    
    public String printResult() {
        String result = "";
        try {
            result = compile(file_name, file_path);
            
            if (exitcode == 0) {
                result = execute(file_name, file_path);
            } else {
                System.out.println("You got compile errors!");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public String compile(String file_name, String file_path) throws Exception {
        rt = Runtime.getRuntime();
        
        String cmd = "javac " + file_name + ".java";
        String line = "";
        String result = "Error:\n";
        
        proc = rt.exec(cmd, null, new File(file_path));

        InputStream stderr = proc.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        while((line=br.readLine()) != null) {
            System.out.println(line);
            result += line+"\n";
        }

        InputStream stdout = proc.getInputStream();
        InputStreamReader osr = new InputStreamReader(stdout);
        BufferedReader obr = new BufferedReader(osr);

        while((line=obr.readLine())!= null) {
            System.out.println(line);
            result += line+"\n";
        }
        exitcode = proc.waitFor();
        
        return result;
    }
    
    public String execute(String file_name, String file_path) throws Exception {
        InputStream ist ;
        String result_exec = "";
        String cmd = "java " + file_name +" \"abc\" \"def\"";
        
        proc = rt.exec(cmd, null, new File(file_path));
        
        exitcode = proc.waitFor();
        
        if(exitcode == 0) {
            ist = proc.getInputStream();
            result_exec = "Output :\n";
        } else{
            ist = proc.getErrorStream();
            result_exec = "Error :\n";
        }
        InputStreamReader isrd = new InputStreamReader(ist);
        BufferedReader bfr = new BufferedReader(isrd);
        
        while (true) {
            String line=bfr.readLine();
            if(line==null) {
                break;
            }else {
                result_exec += line+"\n";
            }
        }
        System.out.println(result_exec);
        
        return result_exec;
    }

//    public static void main(String[] args) {
//        JavaExecLab exec = new JavaExecLab("MyTest", "/Users/christine/NetBeansProjects/online_test_2");
//    }
    
}
