package com.zff.dexlib;

import android.content.Context;
import android.widget.Toast;

/**
 * @ProjectName: DynamicLoading
 * @Package: com.zff.dexlib
 * @ClassName: ShowToastImpl
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2019/10/17 17:46
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/17 17:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ShowToastImpl implements IShowToast {
    @Override
    public int showToast(Context mContext) {
        Toast.makeText(mContext, "我来自另一个dex文件", Toast.LENGTH_LONG).show();
        return 100;
    }
}
