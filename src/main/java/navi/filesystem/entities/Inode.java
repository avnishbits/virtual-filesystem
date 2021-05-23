package navi.filesystem.entities;

import java.sql.Timestamp;
import java.util.List;

/**
 * Container of metadata for any entity in file system
 */
public abstract  class Inode {
    private String name;
    private int size;
    private String owner;
    private List<Integer> blockIds;
    private Timestamp lastModified;
    private Timestamp createdTime;

    public Inode(String name, String owner) {
        this.name = name;
        this.owner = owner;
        lastModified = new Timestamp(System.currentTimeMillis());
        createdTime = new Timestamp(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        lastModified = new Timestamp(System.currentTimeMillis());
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        lastModified = new Timestamp(System.currentTimeMillis());
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
        lastModified = new Timestamp(System.currentTimeMillis());
    }

    public List<Integer> getBlockIds() {
        return blockIds;
    }

    public void setBlockIds(List<Integer> blockIds) {
        this.blockIds = blockIds;
        this.lastModified = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }


}
