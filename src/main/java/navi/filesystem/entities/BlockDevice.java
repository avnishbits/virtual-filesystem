package navi.filesystem.entities;

import java.util.List;

/**
 *@author avnishkg
 *Interface to provide the functionalities of the storage device for read/write in a fix size block
 */
public interface BlockDevice {

    /**
     * Reads content from the specific block
     * @param blockId
     * @return
     */
    public String readBlock(Integer blockId);

    /**
     * Writes @content in specified block
     * @param blockId
     * @param content
     */
    public void writeBlock(Integer blockId, String content);

    /**
     * Returns the contents stored with @blockIds
     * @param blockIds
     * @return
     */
    public String readBlock(List<Integer> blockIds);

    /**
     * Returns the next free block to write
     * @return
     */
    public Integer getNextFreeBlockId();

    /**
     * Deletes the specific block
     * @param blockId
     */
    public void deleteBlock(int blockId);

}
