/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securedatatocloud1;
import securedatatocloud1.Login;
import com.dropbox.core.DbxException;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.ListFolderBuilder;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author welcome
 */
public class UploadFile {
          // DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
 private static final Logger LOG = Logger.getLogger(DropboxService.class.getName());
    public String uploaddropboxFile(DbxClientV2 client,String path) throws  ListFolderErrorException, FileNotFoundException, DbxException, UploadErrorException, IOException
    {
      //  String folderName= null;
        //       System.out.println("hi"+folderName);

           //  folderName=   new securedatatocloud1.Login().getUsername();
     //  System.out.println("hi1"+folderName);
      //  folderName="/Use9"+folderName;

        String userext=new Login().getUsername();
        
                try ( InputStream in = new FileInputStream(path))
        {
            
            File ff=new File(path);
            String fname=ff.getName();
            fname="/"+fname;
     //       FolderMetadata folder = client.files().createFolder(folderName);
        //   System.out.println(folder.getName());
 
            
    FileMetadata metadata = client.files().uploadBuilder(fname).uploadAndFinish(in);
    
      System.out.println("File Uploaded Successfully");
      
  
     
ListFolderBuilder listFolderBuilder = client.files().listFolderBuilder("");
	ListFolderResult result = listFolderBuilder.withRecursive(true).start();
		
	Logger log = Logger.getLogger("thread");
	log.setLevel(Level.INFO);

	while (true) {

		if (result != null) {
			for ( Metadata entry : result.getEntries()) {
				if (entry instanceof FileMetadata){
					
                                    log.info("Added file: "+entry.getPathLower());
                                    
                                    int index=entry.getName().indexOf(userext);
                                    
                                    
                       System.out.println("ndsfbsj"+entry.getName().startsWith(userext));
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

