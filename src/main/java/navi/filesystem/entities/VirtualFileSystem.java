package navi.filesystem.entities;

import navi.filesystem.commons.Utils;
import navi.filesystem.exceptions.ErrorCodes;
import navi.filesystem.exceptions.FileSystemException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author avnishkg
 * Implementation of In memory filesystem
 * provides functionality of fopen(),fclose(),fwrite(),rename() and delete(), ls() files
 * Uses @{@link InMemoryBlockDevice} for stroing files in chunks with pre-defined @blockSize
 * Asummptions:
 * each block in block device is of equal size
 * suppored file content is of String type only
 * supports only storing files, directory support is not provided.
 *Doesn't allow overwrite for the same file name
 * filesnames are case-sensitive
 */
public class VirtualFileSystem implements FileSystem {

    private BlockDevice blockDevice;

    Map<String, FileEntity> fileMetaData;
    private int blockSize;
    private int totalSize;


    public VirtualFileSystem(int totalSize, int blockSize) {
        validateParameters(totalSize, blockSize);
        this.blockDevice = new InMemoryBlockDevice(blockSize, totalSize);
        this.blockSize = blockSize;
        this.totalSize = totalSize;
        fileMetaData = new HashMap<>();
    }

    private void validateParameters(int totalSize, int blockSize) {
        if (totalSize == 0 || blockSize == 0) {
            throw new FileSystemException(ErrorCodes.INVALID_VFS_PARAMS);
        }
        if (blockSize > totalSize) {
            throw new FileSystemException(ErrorCodes.INVALID_BLOCK_SIZE);
        }
        int remainder = totalSize % blockSize;
        if (remainder > 0) {
            throw new FileSystemException(ErrorCodes.INVALID_VFS_PARAMS);
        }
    }

    @Override
    public FileEntity fopen(String fileName, String ownerName) {

        if (Utils.isEmpty(fileName)) {
            throw new FileSystemException(ErrorCodes.INVALID_INPUT);
        }
        // not allowing write of the same file again
        if (fileMetaData.containsKey(fileName)) {
            throw new FileSystemException(ErrorCodes.INVALID_INPUT);
        }
        FileEntity file = new FileEntity(fileName, ownerName);
        return file;
    }

    @Override
    public void fclose(FileEntity file) {
        //does nothing for simplicity
    }

    @Override
    public void fwrite(FileEntity file, String content) {
        if (Utils.isEmpty(content)) {
            throw new FileSystemException(ErrorCodes.INVALID_INPUT);
        }
        if (file == null) {
            throw new FileSystemException(ErrorCodes.INVALID_INPUT);
        }
        if (content.length() > totalSize) {
            throw new FileSystemException(ErrorCodes.INVALID_INPUT);
        }
        int blocksRequired = (new Double(Math.ceil((double) content.length() / (double) blockSize))).intValue();
        List<Integer> blockIdsForFile = new ArrayList<>(blocksRequired);
        int writeCounter = 0;
        while (writeCounter < content.length()) {
            int writeBufferCounter = writeCounter + blockSize;
            int endIndex = writeBufferCounter > content.length() ? content.length() : writeBufferCounter;
            String blockContent = content.substring(writeCounter, endIndex);
            Integer freeBlockId = blockDevice.getNextFreeBlockId();
            blockDevice.writeBlock(freeBlockId, blockContent);
            writeCounter += blockSize;
            blockIdsForFile.add(freeBlockId);
        }
        file.setSize(content.length());
        file.setBlockIds(blockIdsForFile);
        fileMetaData.put(file.getName(), file);
    }

    @Override
    public String fread(String fileName) {
        if (Utils.isEmpty(fileName)) {
            throw new FileSystemException(ErrorCodes.INVALID_INPUT);
        }
        if (!fileMetaData.containsKey(fileName)) {
            throw new FileSystemException(ErrorCodes.FILE_DOESNT_EXIST);
        }
        FileEntity file = fileMetaData.get(fileName);
        return blockDevice.readBlock(file.getBlockIds());
    }

    @Override
    public void rename(String olderFileName, String newFileName) {
        if (Utils.isEmpty(olderFileName) || Utils.isEmpty(newFileName)) {
            throw new FileSystemException(ErrorCodes.INVALID_INPUT);
        }

        if (!fileMetaData.containsKey(olderFileName)) {
            throw new FileSystemException(ErrorCodes.FILE_DOESNT_EXIST);
        }
        FileEntity file = fileMetaData.get(olderFileName);
        file.setName(newFileName);
        fileMetaData.put(newFileName, file);
        fileMetaData.remove(olderFileName);
    }

    @Override
    public void remove(String fileName) {
        if (Utils.isEmpty(fileName)) {
            throw new FileSystemException(ErrorCodes.INVALID_INPUT);
        }

        if (!fileMetaData.containsKey(fileName)) {
            throw new FileSystemException(ErrorCodes.FILE_DOESNT_EXIST);
        }
        FileEntity file = fileMetaData.get(fileName);
        List<Integer> blockIds = file.getBlockIds();
        blockIds.stream().forEach(blockId -> blockDevice.deleteBlock(blockId));
        fileMetaData.remove(fileName);
    }

    @Override
    public void ls() {
        System.out.println("---------Current state of the file system----------");

        System.out.println("Name" + "\t" + "Owner" + "\t" + "Size" + "\t" + "Last Modified" + "\t" + "Block Information");
        fileMetaData.forEach((s, fileEntity) -> System.out.println(fileEntity.toString()));
    }

    @Override
    public String toString() {
        return "Virtual File System [Total Size, BlockSize]" +
                "[ " + totalSize + ", " + blockSize + " ]"
                ;
    }
}
