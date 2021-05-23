package navi.filesystem.entities;

import navi.filesystem.exceptions.ErrorCodes;
import navi.filesystem.exceptions.FileSystemException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author avnishkg
 *  In memory implementation for @{@link BlockDevice}
 */
public class InMemoryBlockDevice implements BlockDevice {
    private Integer blockSize;
    private Integer totalSize;
    private List<Block> blocks;

    public InMemoryBlockDevice(Integer blockSize, Integer totalSize) {
        this.blockSize = blockSize;
        this.totalSize = totalSize;
        initializeBlocks(blockSize,totalSize);
    }

    private void initializeBlocks(Integer blockSize, Integer totalSize) {
        int noOfBlocks  = totalSize/blockSize;
        blocks = new ArrayList<>(noOfBlocks);
        for(int i=0;i<noOfBlocks;i++){
            blocks.add(null);
        }
    }

    @Override
    public String readBlock(Integer blockId) {
        Block block =  blocks.get(blockId);
        if(block == null){
            throw new FileSystemException(ErrorCodes.INVALID_BLOCK_ID);
        }
        return block.getContent();
    }

    @Override
    public void writeBlock(Integer blockId, String content) {
        Block block = new Block(blockId,content);
        blocks.set(blockId,block);
    }

    @Override
    public String readBlock(List<Integer> blockIds) {

        StringBuilder content =  new StringBuilder();
        for (Integer blockId:blockIds
             ) {
            content.append(readBlock(blockId));
        }
        return content.toString();
    }

    @Override
    public Integer getNextFreeBlockId() {
        int counter =  0;
        Integer blockId =  null;
        for (Block block: blocks
             ) {
            if(block == null){
                blockId = counter;
                break;
            }
            counter  = counter + 1;
        }

        if(blockId == null){
            throw new FileSystemException(ErrorCodes.NO_SPACE_LEFT);
        }
        return blockId;
    }

    @Override
    public void deleteBlock(int blockId) {
        blocks.set(blockId,null);
    }

}
