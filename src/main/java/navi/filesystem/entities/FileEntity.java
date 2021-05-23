package navi.filesystem.entities;

/**@author avnishkg
 * File Entity
 */
public class FileEntity extends Inode{

    public FileEntity(String name, String owner) {
        super(name, owner);
    }

    @Override
    public String toString() {
        return super.getName() + "\t" + super.getOwner() + "\t" + super.getSize() + "\t"  + super.getLastModified() + "\t "+"block ids :"+super.getBlockIds().toString();
    }
}
