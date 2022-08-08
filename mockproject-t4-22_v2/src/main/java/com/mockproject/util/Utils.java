package com.mockproject.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Strings;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Utils {



    public static String getFullDatetimeFormatVN(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date));
        }

    }

    public static String getDatetimeString(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
        }
    }

    public static String getDateString(Date date) {
        if (date == null) {
            return "";
        } else {
            return (new SimpleDateFormat("yyyy-MM-dd").format(date));
        }
    }

    public static <T> List<T> convertJsonStringToListObject(String jsonString, Class<T[]> objectclass)
            throws Exception {
        jsonString = Strings.isNullOrEmpty(jsonString) ? "[]" : jsonString;
        ObjectMapper mapper = new ObjectMapper();
        List<T> result = Arrays.asList(mapper.readValue(jsonString, objectclass));
        return result;
    }

    public static <T> T convertJsonStringToObject(String jsonString, Class<T> objectclass) throws Exception {
        if (Strings.isNullOrEmpty(jsonString)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        T result = mapper.readValue(jsonString, objectclass);
        return result;
    }

    public static String convertObjectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(object);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertObjectToJsonObject(Object object) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertListObjectToJsonArray(List<?> objects) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            mapper.writeValue(out, objects);
            final byte[] data = out.toByteArray();
            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




    public static <T> String convertObjectToJsonString(Object data) throws Exception {

        if (data == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(data);
        return result;
    }


    public static String getFileExtension(MultipartFile file) {
        String extension = "";

        try {
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;
    }

    public static String getFileName(MultipartFile file) {
        String extension = "";

        try {
            if (!file.isEmpty()) {
                return FilenameUtils.getBaseName(file.getOriginalFilename());
            }
        } catch (Exception e) {
            return "";
        }

        return extension;
    }

    public static String getContentType(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "png":
                return "image/png";
            case "jpg":
                return "image/jpeg";
            case "gif":
                return "image/gif";
            default:
                return "image/jpeg";
        }
    }
}
