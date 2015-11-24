/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondedition;

/**
 *
 * @author christine
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class JavaExecLab_Data {

    public static void main(String[] args) {
        String[] testData = {"./tmp/data1", "./tmp/data2"};
        try {
            runJava(testData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runJava(String[] testData)
            throws InterruptedException, IOException {
        ProcessBuilder pb = new ProcessBuilder("java", "MyTest");
        pb.directory(new File("/Users/christine/NetBeansProjects/online_test_2"));
        // System.out.println("Configure parameters");
        Process process = null;
        // Map<String, String> env = pb.environment();
        // env.put("name", "ping command");
        // env.put("echoCount", "2");

        System.out.println("Redirect output and error to file");
        File outputFile = new File("./tmp/log.txt");
        File errorFile = new File("./tmp/errlog.txt");
        // pb.redirectOutput(outputFile);
        // pb.redirectError(errorFile);

        for (int i = 0; i < testData.length; i++) {
            pb.inheritIO();
            pb.redirectInput(new File(testData[i]));
            pb.redirectOutput(outputFile);
            pb.redirectError(errorFile);

            process = pb.start();

            System.out.println("Tests start ...");
            int err = process.waitFor();
            if (err != 0) {
                System.out.println((i + 1) + " Error occured!");
                printFile(errorFile);
            } else {
                System.out.println((i + 1) + "st test result is OK!");
                // System.out.println("\nPrint Output:");
                // printFile(outputFile);
            }
            System.out.println("*********************************");
        }

    }

    private static void printFile(File file) throws IOException {
        // System.out.println("*********************************");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        fr.close();
        // System.out.println("*********************************");
    }
}
