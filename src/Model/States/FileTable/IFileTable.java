package Model.States.FileTable;

import Exceptions.FileTableException;

import java.util.List;

public interface IFileTable {
    public void openFile(String name) throws FileTableException;

    public void closeFile(String name) throws FileTableException;

    public int
    readFile(String name) throws FileTableException;

    public String toString();

    public List<String> getFileList();

}
