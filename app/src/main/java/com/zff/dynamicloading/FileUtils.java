package com.zff.dynamicloading;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ProjectName: DynamicLoading
 * @Package: com.zff.dynamicloading
 * @ClassName: FileUtils
 * @Description: 将assets文件copy到app/data/cache目录
 * @Author: Jeffray
 * @CreateDate: 2019/10/17 18:13
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/17 18:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FileUtils {
    public static void copyFiles(Context context, String fileName, File desFile) {
        InputStream in = null;
        OutputStream out = null;

        try {
            in = context.getApplicationContext().getAssets().open(fileName);
            out = new FileOutputStream(desFile.getAbsolutePath());
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) != -1){
                out.write(bytes, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
