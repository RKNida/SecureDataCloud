/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rcc;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author welcome
 */
public class FileReader {

        public String getFileContent(String path)
    {
        String cont="";
        try
        {
            FileInputStream f=new FileInputStream(path);
            int x=0;

            StringBuffer sb=new StringBuffer();
            do
            {
                x=f.read();
                if(x!=-1)
                {
                    char ch=(char)x;
                    sb.append(ch);
                }
            }while(x!=-1);

            cont=sb.toString();
            f.close();

            
        }

        catch(Exception e)
        {
            System.out.println("Exception in FileReader Class");
        }

        return cont;

    }


}
