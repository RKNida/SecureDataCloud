/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securedatatocloud1;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v1.DbxEntry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dropbox.core.v2.files.ListFolderBuilder;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import rcc.FileContentWriter;
import rcc.FileReader;
import rcc.ReverseDecryptionHelper;

/**
 *
 * @author welcome
 */
public class DownloadFile {
    securedatatocloud1.Login login = new securedatatocloud1.Login();
     private static final Logger LOG = Logger.getLogger(DropboxService.class.getName());

    //public static String Drive="D://DropBoXFiles";
    public static String Drive="/home/lenovo/Dropbox"; //give path where files will get downloaded from dropbox
    public String downloadDropboxFile(DbxClientV2 client,String filename) throws ListFolderErrorException,DbxException, FileNotFoundException, DbxException, IOException
    {
        
        File folder=new File(Drive);
        if(!folder.exists())
            folder.mkdirs();
        String path=Drive+"//"+filename;
       String fname="/"+filename;
        
        
         OutputStream downloadFile = new FileOutputStream(path);
                try
                {
                FileMetadata metadata = client.files().downloadBuilder(fname)
                        .download(downloadFile);
                }
                finally
                {
                    downloadFile.close();
                }
                
                String key= login.getUsername();
                System.out.println("download file ...key is"+key);
                 //String key="ABCD123";
                String cont = new FileReader().getFileContent(path);

        ReverseDecryptionHelper rdh = new ReverseDecryptionHelper();
        String ens1 = rdh.startDecryption(cont, key);
        new FileContentWriter().fileWriter(path, ens1);
               
                System.out.println("File Downloaded Successfully");
                
                 
            ListFolderBuilder listFolderBuilder = client.files().listFolderBuilder("");
	ListFolderResult result = null;
              try {
                  result = listFolderBuilder.withRecursive(true).start();
             
	Logger log = Logger.getLogger("thread");
	log.setLevel(Level.INFO);

	while (true) {

		if (result != null) {
			for ( Metadata entry : result.getEntries()) {
				if (entry instanceof FileMetadata){
					
                                    log.info("Added file: "+entry.getPathLower());
                                    
                    //                int index=entry.getName().indexOf(userext);
                                    
                                    
                    //   System.out.println("ndsfbsj"+entry.getName().startsWith(userext));
boolean flag;

				}
                                
			}

			if (!result.getHasMore()) {
				log.info("GET LATEST CURSOR");
				return result.getCursor();
			}

			try {
				result = client.files().listFolderContinue(result.getCursor());
			} catch (DbxException e) {
				log.info ("Couldn't get listFolderContinue");
			}
		}
	}
      
            }
                catch (CreateFolderErrorException err) {
                if (err.errorValue.isPath() && err.errorValue.getPathValue().isConflict()) {
                    System.out.println("Something already exists at the path.");
                }
                 else {
                    System.out.print("Some other CreateFolderErrorException occurred...");
                    System.out.print(err.toString());
                }
            } catch (Exception err) {
                System.out.print("Some other Exception occurred...");
                System.out.print(err.toString());
            }
        return null;        
        
    }
    
}
