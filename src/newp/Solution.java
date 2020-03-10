package newp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    public static ArrayList Strang(File file_2, boolean Flag){
        ArrayList<String> array = new ArrayList<String>();

        for (File files : file_2.listFiles()){
            if(files.isFile() && files.length() <= 50){
                if(Flag)
                    array.add(files.getName());
                else
                    array.add(files.getAbsolutePath());
            }
            else if(files.isDirectory()){
                File file = new File(files.toString());
                ArrayList<String> arrs = Strang(file,Flag);
                for (String b : arrs) {
                    array.add(b);
                }
            }
        }
        Collections.sort(array);
        return array;
    }

    public static void main(String[] args) throws IOException {
        String[] arr = new String[10];
        arr[0] = "C:\\Users\\Антон\\Desktop\\direct";
        arr[1] = "C:\\Users\\Антон\\Desktop\\NewFileeeeee.txt";

        File file_1 = new File(arr[1]);

        File fileNew = new File(file_1.getParent() + "\\allFilesContent.txt");
        File file_2 = new File(arr[0]);

        FileUtils.renameFile(file_1,fileNew);
        String fileAll = fileNew.getAbsolutePath();

        FileOutputStream fileOutputStream = new FileOutputStream (fileAll,true);

        boolean Flag = true;
        // Отсортированный список с именами файлов
        ArrayList<String> arrayName = Strang(file_2, Flag);
        Flag = false;
        // Просто список с полныйми путями
        ArrayList<String> arrayAbsPath = Strang(file_2, Flag);

        for (String a : arrayName) {
            for (String ab : arrayAbsPath) {
                if(ab.endsWith(a)){
                    FileInputStream fileInputStream = new FileInputStream (ab);

                    while (fileInputStream.available() > 0){
                        fileOutputStream.write(fileInputStream.read());
                    }
                    fileOutputStream.write(10);
                    fileOutputStream.flush();

                    fileInputStream.close();
                }
            }
        }
    }
}
