package Model.States.FileTable;

import Exceptions.FileTableException;
import Model.Utils.MyDictionary;
import Model.Utils.MyIDictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileTable implements IFileTable {
    MyIDictionary<String, BufferedReader> fileTable;

    public FileTable() {
        this.fileTable = new MyDictionary<>();
    }

    @Override
    public void openFile(String name) {
        if (fileTable.isDefined(name)) {
            throw new FileTableException("File already opened");
        }

        try {
            BufferedReader fileDescriptor = new BufferedReader(new FileReader(name));
            fileTable.put(name, fileDescriptor);

        } catch (IOException e) {
            throw new FileTableException("Error when trying to open the file");
        }
    }

    @Override
    public void closeFile(String name) {
        if (!fileTable.isDefined(name)) {
            throw new FileTableException("File with this name does not exist");
        }

        try {
            BufferedReader fileDescriptor = fileTable.LookUp(name);
            fileDescriptor.close();
            fileTable.remove(name);
        } catch (IOException e) {
            throw new FileTableException("Error when trying to close the file");
        }
    }

    @Override
    public int readFile(String name) {
        if (!fileTable.isDefined(name)) {
            throw new FileTableException("File with this name does not exist");
        }

        try {
            BufferedReader fileDescriptor = fileTable.LookUp(name);
            String line = fileDescriptor.readLine();
            if (line == null) {
                throw new FileTableException("End of file reached");
            }

            return Integer.parseInt(line);
        } catch (IOException e) {
            throw new FileTableException("Error when trying to read from the file");
        }
    }

    @Override
    public String toString() {
        return fileTable.toString();
    }

    @Override
    public List<String> getFileList() {
        return fileTable.getAllKeys();
    }

}
