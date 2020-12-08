package com.bjtu.redis;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * 文件监听
 */
final class FileListener implements FileAlterationListener {


        public void onStart(FileAlterationObserver fileAlterationObserver) {
            //System.out.println(fileAlterationObserver.getDirectory().getName()+" monitor start scan files..");
        }


        public void onDirectoryCreate(File file) {
            System.out.println(file.getName()+"文件夹已创建");
        }


        public void onDirectoryChange(File file) {
            System.out.println(file.getName()+"文件夹已更改");
        }


        public void onDirectoryDelete(File file) {
            System.out.println(file.getName()+"文件夹已删除");
        }


        public void onFileCreate(File file) {
            System.out.println(file.getName()+"文件夹已创建");
        }


        public void onFileChange(File file) {
            System.out.println(file.getName()+"文件已更改");
            Main.LoadJson();
            System.out.println("JSON文件已重载");
        }

        public void onFileDelete(File file) {
            System.out.println(file.getName()+"文件已删除");
        }

        public void onStop(FileAlterationObserver fileAlterationObserver) {

        }


}
