/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondedition;

import java.io.*;

/**
 *
 * @author christine
 */
public class GroupFile {
    String file_name;
    File file;
    File dir;
    
    public GroupFile(String folder_name, String file_name, String type) {
        this.file_name = file_name;
        dir = new File(folder_name);
        file = new File(file_name+type);
    }
    
    public void createFolder() {
        if (!dir.exists()) {
            try {
                dir.mkdirs();
                System.out.println("Folder is created.");
            } catch (Exception e) {
                System.out.println("Folder is exist.");
                e.printStackTrace();
            }
        }
        
    }
    
    public void createNewFile() {
        
        
        if (!file.exists()) {
            try {
                System.out.println("No files");
                file.createNewFile();
                System.out.println(file_name + " is created.");
            } catch (IOException e) {
                System.out.println("File is exist.");
                e.printStackTrace();
            }
        }
    }
    
    public void writeToFile(String stData) throws IOException {
        FileWriter write = new FileWriter(file);
        boolean append_to_file = true;
        
        PrintWriter print_line = new PrintWriter(write, append_to_file);
        
        print_line.printf("%s"+"%n", stData);
        
        write.close();
        print_line.close();
    }
    
    public String readFile() {
        String path = file_name;
        
        String line, content = "";
        
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(path);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                content += String.format(line + "\n");
            }	

            // Always close files.
            fileReader.close();
            bufferedReader.close();			
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");				
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + path + "'");					
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        return content;
    }
    
    public int readLines() {
        String path = file_name;
        int numberOfLines = 0;
        
        try {
            FileReader file_to_read = new FileReader(path);
            BufferedReader bf = new BufferedReader(file_to_read);

            String aLine;
            while((aLine = bf.readLine()) != null) {
                numberOfLines++;
            }
            bf.close();
        } 
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");				
        } 
        catch(IOException ex) {
            System.out.println("Error reading file '" + path + "'");					
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        return numberOfLines;
    }
}
