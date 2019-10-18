package com.zff.dynamicloading;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zff.dexlib.IShowToast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDexClass();
            }
        });
    }

    private void loadDexClass() {
        File cacheFile = getDir("dex", MODE_PRIVATE);
        String internalPath = cacheFile.getAbsolutePath() + File.separator + "ishowtoast.jar";
        File dexFile = new File(internalPath);

        try {
            if (!dexFile.exists()) {
                dexFile.createNewFile();
            }
            FileUtils.copyFiles(this, "ishowtoast.jar", dexFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //下面开始加载dex class
        //1.待加载的dex文件路径，如果是外存路径，一定要加上读外存文件的权限,
        //2.解压后的dex存放位置，此位置一定要是可读写且仅该应用可读写
        //3.指向包含本地库(so)的文件夹路径，可以设为null
        //4.父级类加载器，一般可以通过Context.getClassLoader获取到，也可以通过ClassLoader.getSystemClassLoader()取到。
        DexClassLoader dexClassLoader = new DexClassLoader(internalPath, cacheFile.getAbsolutePath(), null, getClassLoader());
        try {
            //该name就是internalPath路径下的dex文件里面的ShowToastImpl这个类的包名+类名
            Class<?> clz = dexClassLoader.loadClass("com.zff.dexlib.ShowToastImpl");
//            IShowToast impl = (IShowToast) clz.newInstance();//通过该方法得到IShowToast类
//            if (impl != null) {
//                //调用打开弹窗
//                impl.showToast(this);
//            }

            Method[] methods = clz.getMethods();
            clz.getMethod("showToast", Context.class).invoke(clz.newInstance(),this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
