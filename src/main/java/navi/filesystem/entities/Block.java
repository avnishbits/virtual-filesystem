package navi.filesystem.entities;

import java.sql.Timestamp;

/**
 * @author avnishkg
 * Entity to store the block meta and content
 * Assumptions:
 * Only @{@link String} type content is supported
 */
public class Block {
private Integer id;
private String content;
private Timestamp lastModified;
private Timestamp createdTime;

    public Block(Integer id) {
        this.id = id;
    }

    public Block(Integer id, String content) {
        this.id = id;
        this.content = content;
        lastModified = new Timestamp(System.currentTimeMillis());
        createdTime = new Timestamp(System.currentTimeMillis());
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.lastModified= new Timestamp(System.currentTimeMillis());
        createdTime = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", lastModified=" + lastModified +
                ", createdTime=" + createdTime +
                '}';
    }
}
