package navi.filesystem;

import navi.filesystem.commons.Utils;
import navi.filesystem.entities.FileEntity;
import navi.filesystem.entities.VirtualFileSystem;
import navi.filesystem.exceptions.ErrorCodes;
import navi.filesystem.exceptions.FileSystemException;

public class VFSDriver {

    public static final String DEFAULT_OWNER = "Navi";

    public static void main(String[] args) {

        VirtualFileSystem vfs  = new VirtualFileSystem(100,10);
        System.out.println(vfs);
        System.out.println("------Scenario  1 : Writing files for all blocks ---- ");
        String file1 =  writeAFile(vfs,50);
        String file2 = writeAFile(vfs,25);
        String file3= writeAFile(vfs,20);
        vfs.ls();
        System.out.println("------Scenario 2 : Write fail with no space left ---- ");
        try{
            String file4= writeAFile(vfs,10);
        }catch (FileSystemException ex){
            assert(ex.getErrorCode()== ErrorCodes.NO_SPACE_LEFT);
        }

        System.out.println("------Scenario 3 : File with same name---- ");
        try{
            FileEntity fileEntity =  vfs.fopen(file3,DEFAULT_OWNER);
        }catch (FileSystemException ex){
            assert(ex.getErrorCode()== ErrorCodes.NO_SPACE_LEFT);
        }

        System.out.println("------Scenario 4 : Deletion of file ---- ");

        vfs.remove(file2);
        try{
            String content =  vfs.fread(file2);
        }catch (FileSystemException ex){
            assert(ex.getErrorCode()== ErrorCodes.FILE_DOESNT_EXIST);
        }
        vfs.ls();

        System.out.println("------Scenario 6 : Writing the files in deleted blocks ---- ");

        String file4= writeAFile(vfs,10);
        vfs.ls();
        String file5= writeAFile(vfs,10);
        vfs.ls();
        String file6= writeAFile(vfs,10);
        vfs.ls();

        System.out.println("------Scenario 7 : Renaming the files ---- ");

        vfs.rename(file1,"navitest");
        try{
            String content =  vfs.fread(file1);
        }catch (FileSystemException ex){
            assert(ex.getErrorCode()== ErrorCodes.FILE_DOESNT_EXIST);
        }
        vfs.ls();

        System.out.println("------Scenario 8 : Writing with empty file name ---- ");
        try{
            String fileName = null;
            FileEntity file =  vfs.fopen(fileName,DEFAULT_OWNER);
        }catch (FileSystemException ex){
            assert(ex.getErrorCode()== ErrorCodes.INVALID_INPUT);
        }

    }


    public static  String writeAFile(VirtualFileSystem vfs, int sizeOfFile){
        String randomFileName =  Utils.getRandomString(5);
        String content =  Utils.getRandomString(sizeOfFile);
        FileEntity file = vfs.fopen(randomFileName, DEFAULT_OWNER);
        System.out.println("Writing file [size, content] => " + "[ " + sizeOfFile + ", " + content + " ]");
        vfs.fwrite(file,content);
        return file.getName();
    }
}
