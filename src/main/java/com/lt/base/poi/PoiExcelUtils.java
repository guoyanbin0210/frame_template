package com.lt.base.poi;


import com.lt.base.poi.annotation.GsExcelProperty;
import com.lt.base.model.BaseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Date: 2018-12-14
 * Time: 11:19
 * 通过 Excel 导入导出文件
 */
public class PoiExcelUtils {


    private static final Logger logger = LogManager.getLogger(PoiExcelUtils.class);

    /**
     * 根据文件获取 数组对象
     *
     * @param file 待处理文件
     * @return 处理结果
     */
    public static Map<Integer, List<Object>> getListByFile(File file) throws IOException {
        Map<Integer, List<Object>> returnMap = new HashMap<>();
        if (file.exists()) {
            Workbook workbook;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            String fileName = file.getName().toLowerCase();
            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(bis);
            } else if (fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(bis);
            } else {
                logger.error("上传格式错误 " + file.getName());
                return null;
            }
            doReadWorkBoot(returnMap, workbook);
            workbook.close();
            bis.close();
        }
        return returnMap.isEmpty() ? null : returnMap;
    }

    /**
     * 根据文件获取 数组对象
     *
     * @param multipartFile
     * @return 处理结果
     * @throws IOException
     */
    public static Map<Integer, List<Object>> getListByFile(MultipartFile multipartFile) throws IOException {
        Map<Integer, List<Object>> returnMap = new HashMap<>();
        if (!multipartFile.isEmpty() && !StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            Workbook workbook;
            BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
            String fileName = multipartFile.getOriginalFilename().toLowerCase();
            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(bis);
            } else if (fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(bis);
            } else {
                logger.error("上传格式错误 " + multipartFile.getOriginalFilename());
                return null;
            }
            bis.close();

            doReadWorkBoot(returnMap, workbook);
            workbook.close();
        }
        return returnMap.isEmpty() ? null : returnMap;
    }


    /**
     * 根据文件获取 数组对象
     *
     * @param multipartFile
     * @return 处理结果
     * @throws IOException
     */
    public static List<HashMap<String, Object>> getListByFile(MultipartFile multipartFile, Class<? extends BaseModel> aClass) throws IOException {
        List<HashMap<String, Object>> returnList = new ArrayList<>();
        if (!multipartFile.isEmpty() && !StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            Workbook workbook;
            BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
            String fileName = multipartFile.getOriginalFilename().toLowerCase();
            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(bis);
            } else if (fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(bis);
            } else {
                logger.error("上传格式错误 " + multipartFile.getOriginalFilename());
                return null;
            }
            bis.close();

            doReadWorkBoot(workbook, returnList, aClass);
            workbook.close();
        }
        return returnList.isEmpty() ? null : returnList;
    }


    public static void doReadWorkBoot(Workbook workbook, List<HashMap<String, Object>> returnList, Class<? extends BaseModel> aClass) {

 /*       Sheet sheet = workbook.getSheetAt(0);

        HashMap<String, Object> fieldValueMap;//存放 cell-index 和 class 对应的 field

        ArrayList<String> header = new ArrayList<>();//存放的是 fieldName;
        Field[] fields = aClass.getDeclaredFields();
        int i = 0;
        for (Row row : sheet) { // 行
            if (i == 0) { // 当i==0的时候表示第一行
                for (Cell cell : row) { // 单元格
                    String fieldDes = String.valueOf(cell.getRichStringCellValue());
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(GsExcelProperty.class)) {
                            GsExcelProperty gsExcelProperty = field.getAnnotation(GsExcelProperty.class);
                            if (gsExcelProperty.description().equals(fieldDes)) {
                                header.add(field.getName());
                                break;
                            }
                        }
                    }
                }
            } else {
                int j = 0;
                fieldValueMap = new HashMap<>();
                for (Cell cell : row) { // 单元格
                    String value = header.get(j);
                    j++;
                    switch (cell.getCellType()) {
                        case STRING:
                            fieldValueMap.put(value, cell.getRichStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                fieldValueMap.put(value, cell.getDateCellValue());
                            } else {
                                fieldValueMap.put(value, getCellNumeric(cell));
                            }
                            break;
                        case BOOLEAN:
                            fieldValueMap.put(value, cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            fieldValueMap.put(value, cell.getCellFormula());
                            break;
                        case BLANK:
                            fieldValueMap.put(value, "");
                            break;
                    }
                }

                returnList.add(fieldValueMap);
            }
            i++;
        }*/
    }

    public static void doReadWorkBoot(Map<Integer, List<Object>> returnMap, Workbook workbook) {
        // 打开Excel中的第一个Sheet
   /*     Sheet sheet = workbook.getSheetAt(0);
        // 读取Sheet中的数据
        int i = 0;
        for (Row row : sheet) { // 行
            returnMap.put(i, new ArrayList<>());
            for (Cell cell : row) { // 单元格
                switch (cell.getCellType()) {
                    case STRING:
                        returnMap.get(i).add(cell.getRichStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            returnMap.get(i).add(cell.getDateCellValue());
                        } else {
                            returnMap.get(i).add(getCellNumeric(cell));
                        }
                        break;
                    case BOOLEAN:
                        returnMap.get(i).add(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        returnMap.get(i).add(cell.getCellFormula());
                        break;
                    case BLANK:
                        returnMap.get(i).add("");
                        break;
                }

            }
            i++;
        }*/
    }


    /**
     * 格式化数字，去掉.0
     *
     * @param cell
     * @return
     */
    private static Object getCellNumeric(Cell cell) {
        Object inputValue;
        long longVal = Math.round(cell.getNumericCellValue());
        double doubleVal = cell.getNumericCellValue();
        if (Double.parseDouble(longVal + ".0") == doubleVal) {
            inputValue = longVal;
        } else {
            inputValue = doubleVal;
        }
        return inputValue;
    }

    /////////////////////////////////////////// write /////////////////////////////////////////////

    /**
     * @param fileName 文件名
     * @param response
     * @return
     * @throws IOException
     */
    public static boolean doDownloadWorkbook(String fileName, HttpServletResponse response, Class cla) throws IOException {
        Workbook workbook;
        if (fileName.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else if (fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else {
            logger.error("下载格式错误，仅支持 xls/xlsx 格式。");
            return false;
        }
        Sheet sheet = workbook.createSheet();
        sheet.createFreezePane(0, 1, 0, 1);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();

        //headerStyle.setBorderBottom(BorderStyle.THICK);
        headerStyle.setBottomBorderColor((short) 8);

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);


        Cell headerCell;
        Field[] fields = cla.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            if (field.isAnnotationPresent(GsExcelProperty.class)) {
                GsExcelProperty gsExcelProperty = field.getAnnotation(GsExcelProperty.class);
                headerCell = header.createCell(i); // 创建表头单元格
                headerCell.setCellValue(gsExcelProperty.description());
                headerCell.setCellStyle(headerStyle);
                sheet.autoSizeColumn(i);
                i++;
            }
        }
        //流/或者在服务器本地存储然后下载
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        workbook.close();
        out.close();
        return true;
    }



}
