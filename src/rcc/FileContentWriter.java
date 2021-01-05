/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcc;

import java.io.FileOutputStream;

/**
 *
 * @author welcome
 */
public class FileContentWriter {
    
     public boolean fileWriter(String path,String content)
    {
        Boolean flag=true;
        try
        {
        FileOutputStream fos= new FileOutputStream(path);
        
        for(int i=0;i<content.length();i++)
        {
            char a=content.charAt(i);
            int x=(int)a;
            fos.write(x);
        }
        fos.close();
        
        }
        catch(Exception e)
        {
            System.out.println("Exception in class Write File is: "+e);
            flag=false;
        }
        return flag;
    }
    
}
