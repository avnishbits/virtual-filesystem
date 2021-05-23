package navi.filesystem.entities;

import java.util.List;

/**
 * @author avnishkg
 * Interface for the filesystem
 */
public interface FileSystem {

     /**
      * open the file for write,returns the @{@link FileEntity} instance
      * @param fileName
      * @return
      */
     public FileEntity fopen(String fileName,String ownerName);

     /**
      * closes the file after the write
      * @param file
      */
     public void fclose(FileEntity file);

     /**
      * writes the file content
      * @param file
      * @param content
      */
     public void fwrite(FileEntity file,String content);

     /**
      * read the file content. For simplicity, it will return the whole content of the file at once.
      * @param fileName
      * @return
      */
     public String fread(String fileName);

     /**
      * renames the file name
      * @param olderFileName
      * @param newFileName
      */
     public void rename(String olderFileName, String newFileName);

     /**
      * removes the file.
      * @param fileName
      */
     public void remove(String fileName);


     /**
      * Lists all the files stored in the file system
      */
     public void ls();

}
