package com.lt.base.util;

import com.lt.body.appManager.model.AppManagerModel;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.IconFace;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ApkUtils {


    /**
     * @param filePath
     * @param appManagerModel
     * @throws IOException
     */
    public static void functionDo00(String filePath, AppManagerModel appManagerModel) throws IOException {
        ApkFile apkParser = new ApkFile(new File(filePath));
        List<IconFace> allIcons = apkParser.getAllIcons();
        String path = null;
        if (allIcons != null) {
            for (IconFace allIcon : allIcons) {
                if (allIcon.getPath().contains("xxx")) {
                    path = allIcon.getPath();
                    break;
                }
            }
        }
        ApkMeta apkMeta = apkParser.getApkMeta();
        appManagerModel.setPackage_name(apkMeta.getPackageName());
        appManagerModel.setLabel(apkMeta.getLabel());
        appManagerModel.setVersion_name(apkMeta.getVersionName());
        appManagerModel.setVersion_code(Integer.parseInt(apkMeta.getVersionCode() + ""));
        appManagerModel.setMin_sdk_version(apkMeta.getMinSdkVersion());

        if (!StringUtils.isEmpty(path)) {
            String icon_url = getImg(filePath, path);
            if (icon_url != null) {
                appManagerModel.setIcon_url(icon_url);
                File file = new File(icon_url);

                BarcodeUtils.drawLogoQRCode(file, appManagerModel.getDown_url() + "?versionCode=" + apkMeta.getVersionCode());
            }
        }
        apkParser.close();
    }

    public void functionDo01(String filePath) throws IOException {
        ApkFile apkParser = new ApkFile(new File(filePath));
        List<IconFace> allIcons = apkParser.getAllIcons();
        String path = null;
        if (allIcons != null) {

            path = allIcons.get(0).getPath();


        }


        ApkMeta apkMeta = apkParser.getApkMeta();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("packageName", apkMeta.getPackageName());
        resultMap.put("label", apkMeta.getLabel());
        if (!StringUtils.isEmpty(path)) {
            resultMap.put("icon", path);
            String icon_url = getImg(filePath, path);
            resultMap.put("icon_url", icon_url);
        }
        resultMap.put("versionName", apkMeta.getVersionName());
        resultMap.put("versionCode", apkMeta.getVersionCode());
        resultMap.put("minSdkVersion", apkMeta.getMinSdkVersion());
        apkParser.close();
    }

    public static String getImg(String apkUrl, String tagName) throws IOException {
        ZipFile zipFile = new ZipFile(apkUrl);
        Enumeration<?> enumeration = zipFile.entries();
        ZipEntry zipEntry = null;
        OutputStream outputStream= null;
        InputStream inputStream= null;
        while (enumeration.hasMoreElements()) {
            zipEntry = (ZipEntry) enumeration.nextElement();
            String name = zipEntry.getName();
            //我知道图片的图标名称就叫appicon_logo,所以可以这样获取
            if (name.contains(tagName)) {
                int length = 0;
                byte[] b = new byte[1024];
                String parent = new File(apkUrl).getParent();
                String filePath = parent + "/logo_" + BaseUtils.getUUID() + ".png";
                outputStream = new FileOutputStream(new File(filePath));
                inputStream = zipFile.getInputStream(zipEntry);
                while (-1 != (length = inputStream.read(b))) {
                    outputStream.write(b, 0, length);
                    outputStream.flush();
                }
                outputStream.close();
                inputStream.close();
                zipFile.close();
                return filePath;
            }
        }
        zipFile.close();
        return null;
    }


}
