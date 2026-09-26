package com.example.module;

import android.util.Log;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import io.github.libxposed.api.XposedHooker;
import io.github.libxposed.api.annotation.AfterInvocation;
import io.github.libxposed.api.callbacks.AfterInvocationCallback;
import java.lang.reflect.Field;

public class MainModule extends XposedModule {

    public MainModule(XposedInterface base, ModuleLoadedParam param) {
        super(base, param);
    }

    @Override
    public void onPackageLoaded(PackageLoadedParam param) {
        super.onPackageLoaded(param);
        
        if (!param.getPackageName().equals("com.android.systemui")) return;

        try {
            Class<?> clazz = param.getClassLoader().loadClass("com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle");
            
            hook(clazz, "onDraw", new XposedHooker() {
                @AfterInvocation
                public void after(AfterInvocationCallback callback) {
                    Object handle = callback.getThisObject();
                    
                    setIntField(handle, "mHandleBottom", 12);
                    setIntField(handle, "mHeight", 22);
                    setIntField(handle, "mWidth", 780);
                    setIntField(handle, "mRadius", 11);
                }
            });
        } catch (Throwable t) {
            log(Log.ERROR, "IosBarTuner", "Hook error: " + t.getMessage());
        }
    }

    private void setIntField(Object obj, String fieldName, int value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.setInt(obj, value);
        } catch (Exception ignored) {}
    }
}