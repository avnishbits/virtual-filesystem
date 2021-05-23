# virtual-filesystem

Provides implemention for the In memory virutal Files. 

## Key Interfaces 

 1. FileSystem - Interface for the filesystem
 2. BlockDevice - Interface to provide the functionalities of the storage device for read/write in a fix size block
 3. Inode - Container of metadata for any entity in file system

## Asummptions:
 * Each block in block device is of equal size
 * supported file content is of String type only
 * supports only storing files, directory support is not provided. No hierarchy is supported.
 * Doesn't allow overwrite for the same file name

## Steps to Run
```
cd target
java -jar virtual-filesystem

```
