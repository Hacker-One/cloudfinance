package com.ctfs.qloudMarket.market_service.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Http请求
 *
 * @author mszhou
 */
@Slf4j
public class HTTPUtils {
    private static final int TIMEOUT = 45000;
    public static final String ENCODING = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(HTTPUtils.class);

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_PATCH = "PATCH";
    public static final String METHOD_HEAD = "HEAD";


    public static final String CONTENT_TYPE="Content-Type";
    public static final String JSON_CONTENT_TYPE="application/json";

    public static final String API_KEY="apikey";



    /**
     * 创建HTTP连接
     *
     * @param url              地址
     * @param method           方法
     * @param headerParameters 头信息
     * @param body             请求内容
     * @return
     * @throws Exception
     */
    public HttpURLConnection createConnection(String url,
                                              String method, Map<String, String> headerParameters, String body)
            throws Exception {
        URL Url = new URL(url);
        trustAllHttpsCertificates();
        HttpURLConnection httpConnection = (HttpURLConnection) Url
                .openConnection();
        // 设置请求时间
        httpConnection.setConnectTimeout(TIMEOUT);

        // 设置 header
        if (headerParameters != null) {

            Iterator<String> iteratorHeader = headerParameters.keySet()
                    .iterator();
            while (iteratorHeader.hasNext()) {
                String key = iteratorHeader.next();
                httpConnection.setRequestProperty(key,
                        headerParameters.get(key));
            }
        }
//        httpConnection.setRequestProperty("Content-Type",
//                "application/x-www-form-urlencoded;charset=" + ENCODING);

        // 设置请求方法
        httpConnection.setRequestMethod(method);
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);
        logger.info("\nlogger:{}",httpConnection.getRequestProperties());
        // 写query数据流
        if (!(body == null || body.trim().equals(""))) {
            OutputStream writer = httpConnection.getOutputStream();
            try {
                writer.write(body.getBytes(ENCODING));
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }

        // 请求结果
        int responseCode = httpConnection.getResponseCode();
        if (responseCode != 200) {
            // throw new Exception(responseCode + ":" + httpConnection.getResponseMessage());
        }

        return httpConnection;
    }









    public String sendJsonBody(String url, String method, Map<String, String> headerParameters, byte[] body)
            throws Exception {
        String encoding = "UTF-8";
        URL Url = new URL(url);
        trustAllHttpsCertificates();
        HttpURLConnection httpConnection = (HttpURLConnection) Url
                .openConnection();
        // 设置请求时间
        httpConnection.setConnectTimeout(TIMEOUT);
        // 设置 header
        String result = null;
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);
        httpConnection.setUseCaches(false);
        httpConnection.setRequestProperty("Connection", "Keep-Alive");
        httpConnection.setRequestProperty("Charset", "UTF-8");
        // 设置文件类型:
        httpConnection.setRequestProperty("Content-Type", "application/json");
        // 设置接收类型否则返回415错误
        //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
        httpConnection.setRequestProperty("accept", "application/json");

        // 设置请求方法
        httpConnection.setRequestMethod(method);
        // 写query数据流
        // httpConnection.connect();
        //
        // body.
        if (body != null && body.length > 0) {
            // byte[] writebytes = body.getBytes();
            // 设置文件长度
            httpConnection.setRequestProperty("Content-Length", String.valueOf(body.length));
            OutputStream writer = httpConnection.getOutputStream();
            writer.write(body);
            writer.flush();


        }
        // 请求结果
        int responseCode = httpConnection.getResponseCode();
        logger.info("code:{}", responseCode);
        if (responseCode == 201 || responseCode == 200) {
//            result = inputStream2String(httpConnection.getInputStream(),
//                    encoding);\
            result=inputStream2String(httpConnection.getInputStream(),encoding);
            logger.info("success {}", result);

            return result;
        } else {
//            result = inputStream2String(httpConnection.getErrorStream(),
//                    encoding);
            logger.info("error {}", inputStream2String(httpConnection.getErrorStream(),encoding));
            return result;
        }


    }


    /**
     * @param url
     * @param method
     * @param headerParameters
     * @param body
     * @return
     * @throws Exception
     */
    public JSONObject getHeadInnfo(String url,
                                   String method, Map<String, String> headerParameters, String body)
            throws Exception {
        URL Url = new URL(url);
        trustAllHttpsCertificates();
        JSONObject result = new JSONObject();
        HttpURLConnection httpConnection = (HttpURLConnection) Url
                .openConnection();
        // 设置请求时间
        httpConnection.setConnectTimeout(TIMEOUT);
        // 设置 header
        if (headerParameters != null) {
            Iterator<String> iteratorHeader = headerParameters.keySet()
                    .iterator();
            while (iteratorHeader.hasNext()) {
                String key = iteratorHeader.next();
                httpConnection.setRequestProperty(key,
                        headerParameters.get(key));
            }
        }
        httpConnection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded;charset=" + ENCODING);

        // 设置请求方法
        httpConnection.setRequestMethod(method);
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);
        // 写query数据流
        String message = httpConnection.getResponseMessage();
        String code = String.valueOf(httpConnection.getResponseCode());
        httpConnection.getHeaderFields();
        Map headerFields = httpConnection.getHeaderFields();
        System.out.println(headerFields.toString());
        result.put("code", code);
        result.put("message", message);
        if ("202".equals(code)) {
            String uuid = headerFields.get("Docker-Upload-Uuid").toString();
            String location = headerFields.get("Location").toString();
            result.put("uuid", uuid.substring(1, uuid.length() - 1));
            result.put("Location", location.substring(1, location.length() - 1));
        }

        return result;
    }


    /**
     * POST请求
     *
     * @param address          请求地址
     * @param headerParameters 参数
     * @param body
     * @return
     * @throws Exception
     */
    public String post(String address,
                       Map<String, String> headerParameters, String body) throws Exception {

        return proxyHttpRequest(address, METHOD_POST, null,
                getRequestBody(headerParameters));
    }


    public String head(String address, Map<String, String> headerParameters, String body) throws Exception {
        return proxyHttpRequest(address, METHOD_HEAD, null,
                null);
    }

    /**
     * GET请求
     *
     * @param address
     * @param headerParameters
     * @param body
     * @return
     * @throws Exception
     */
    public String get(String address,
                      Map<String, String> headerParameters, String body) throws Exception {
        String url = new StringBuffer(address).append((headerParameters != null && headerParameters.size() > 0) ? new StringBuffer("?").append(getRequestBody(headerParameters)).toString() : "").toString();
        return proxyHttpRequest(url, METHOD_GET, null, null);
    }


//    public byte[] getByte(String address,
//                      Map<String, String> headerParameters, String body) throws Exception {
//        String url = new StringBuffer(address).append((headerParameters != null && headerParameters.size() > 0) ? new StringBuffer("?").append(getRequestBody(headerParameters)).toString() : "").toString();
//        return getFileByteByGet(url, null);
//    }

    /**
     * 读取网络文件
     *
     * @param address
     * @param headerParameters
     * @param file
     * @return
     * @throws Exception
     */
    public String getFile(String address,
                          Map<String, String> headerParameters, File file) throws Exception {
        String result = "fail";

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = createConnection(address, METHOD_POST, null,
                    getRequestBody(headerParameters));
            result = readInputStream(httpConnection.getInputStream(), file);

        } catch (Exception e) {
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }

        return result;
    }

    public byte[] getFileByte(String address,
                              Map<String, String> headerParameters) throws Exception {
        byte[] result = null;

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = createConnection(address, METHOD_POST, null,
                    getRequestBody(headerParameters));
            result = readInputStreamToByte(httpConnection.getInputStream());

        } catch (Exception e) {
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }

        return result;
    }

    /**
     * use the get protocol  download file
     *
     * @param address
     * @param headerParameters
     * @return
     * @throws Exception
     */
    public byte[] getFileByteByGet(String address, Map<String, String> headerParameters) throws Exception {
        byte[] result = null;

        HttpURLConnection httpConnection = null;
        try {
            httpConnection = createConnection(address, METHOD_GET, null,
                    getRequestBody(headerParameters));
            result = readInputStreamToByte(httpConnection.getInputStream());

        } catch (Exception e) {
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }

        }

        return result;
    }


//    public InputStream getInputStreamByGet(String address, Map<String, String> headerParameters) throws Exception {
//        InputStream result=null;
//        HttpURLConnection httpConnection = null;
//        try {
//            httpConnection = createConnection(address, METHOD_GET, null,
//                    getRequestBody(headerParameters));
//            result = httpConnection.getInputStream();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (httpConnection != null) {
//                httpConnection.disconnect();
//            }
//
//        }
//
//        return result;
//    }

    /**
     * 读取文件流
     *
     * @param in
     * @return
     * @throws Exception
     */
    public String readInputStream(InputStream in, File file)
            throws Exception {
        FileOutputStream out = null;
        ByteArrayOutputStream output = null;

        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }

            out = new FileOutputStream(file);
            out.write(output.toByteArray());

        } catch (Exception e) {
            throw e;
        } finally {
            if (output != null) {
                output.close();
            }
            if (out != null) {
                out.close();
            }
        }
        return "success";
    }

    public byte[] readInputStreamToByte(InputStream in) throws Exception {
        FileOutputStream out = null;
        ByteArrayOutputStream output = null;
        byte[] byteFile = null;

        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            byteFile = output.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            if (output != null) {
                output.close();
            }
            if (out != null) {
                out.close();
            }
        }

        return byteFile;
    }

    /**
     * HTTP请求
     *
     * @param address          地址
     * @param method           方法
     * @param headerParameters 头信息
     * @param body             请求内容
     * @return
     * @throws Exception
     */
    public String proxyHttpRequest(String address, String method, Map<String, String> headerParameters, String body) throws Exception {
        String result = null;
        HttpURLConnection httpConnection = null;

        try {
            httpConnection = createConnection(address, method, headerParameters, body);

            String encoding = "UTF-8";
            if (httpConnection.getContentType() != null
                    && httpConnection.getContentType().indexOf("charset=") >= 0) {
                encoding = httpConnection.getContentType()
                        .substring(
                                httpConnection.getContentType().indexOf(
                                        "charset=") + 8);
            }
            if ("HEAD".equals(method)) {
                result = String.valueOf(httpConnection.getResponseCode());
            } else {
                result = inputStream2String(httpConnection.getInputStream(),
                        encoding);
                // logger.info("HTTPproxy response: {},{}", address,
                // result.toString());
            }
        } catch (Exception e) {
            // logger.info("HTTPproxy error: {}", e.getMessage());
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return result;
    }




    public String getProxyHttpRequestBody(String address, String method,
                                          Map<String, String> headerParameters, String body) throws Exception {
        String result = null;
        HttpURLConnection httpConnection = null;

        try {
            httpConnection = createConnection(address, method,
                    headerParameters, body);

            String encoding = "UTF-8";
            if (httpConnection.getContentType() != null
                    && httpConnection.getContentType().indexOf("charset=") >= 0) {
                encoding = httpConnection.getContentType()
                        .substring(
                                httpConnection.getContentType().indexOf(
                                        "charset=") + 8);
            }
//		   result = inputStream2String(httpConnection.getInputStream(),
//						encoding);
            // logger.info("HTTPproxy response: {},{}", address,
            // result.toString());
            Map headerFields = httpConnection.getHeaderFields();
            result = JacksonUtils.mapToJson(headerFields).toString();
        } catch (Exception e) {
            // logger.info("HTTPproxy error: {}", e.getMessage());
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return result;
    }


    /**
     * 将参数化为 body
     *
     * @param params
     * @return
     */
    public String getRequestBody(Map<String, String> params) {
        return getRequestBody(params, true);
    }

    /**
     * 将参数化为 body
     *
     * @param params
     * @return
     */
    public String getRequestBody(Map<String, String> params,
                                 boolean urlEncode) {
        StringBuilder body = new StringBuilder();

        Iterator<String> iteratorHeader = params.keySet().iterator();
        while (iteratorHeader.hasNext()) {
            String key = iteratorHeader.next();
            String value = params.get(key);

            if (urlEncode) {
                try {
                    body.append(key + "=" + URLEncoder.encode(value, ENCODING)
                            + "&");
                } catch (UnsupportedEncodingException e) {
                    // e.printStackTrace();
                }
            } else {
                body.append(key + "=" + value + "&");
            }
        }

        if (body.length() == 0) {
            return "";
        }
        return body.substring(0, body.length() - 1);
    }

    /**
     * 读取inputStream 到 string
     *
     * @param input
     * @param encoding
     * @return
     * @throws IOException
     */
    private String inputStream2String(InputStream input, String encoding)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                encoding));
        StringBuilder result = new StringBuilder();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            //   logger.info("line : {}",temp);
            result.append(temp);
        }

        return result.toString();

    }


    /**
     * 设置 https 请求
     *
     * @throws Exception
     */
    private void trustAllHttpsCertificates() throws Exception {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String str, SSLSession session) {
                return true;
            }
        });
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }




    //设置 https 请求证书
    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }


    }

    /**
     * 多笔文件上传
     *
     * @param actionUrl
     * @param uploadFilePaths
     * @return
     */
    public static String uploadFiles(String actionUrl, List<String> uploadFilePaths) {
        logger.info("request URL:{}", actionUrl);
        //String end = "\r\n";
        // String twoHyphens = "--";
        //String boundary = "*****";

        DataOutputStream ds = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {

            URL url = new URL(actionUrl);

            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;


            httpURLConnection.setDoInput(true);

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setUseCaches(false);

            httpURLConnection.setRequestMethod(METHOD_POST);

            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");

            httpURLConnection.setRequestProperty("Charset", "UTF-8");

            // httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");

            int i = 0;
            for (String uploadFile : uploadFilePaths) {
                String filename = uploadFile.substring(uploadFile.lastIndexOf("//") + 1);
                logger.info("fileName {}", filename);
                // ds.writeBytes(twoHyphens + boundary + end);
                //  ds.writeBytes("Content-Disposition: form-data; " + "name=\"file" + i + "\";filename=\"" + filename+ "\"" + end);
                //   ds.writeBytes(end);
                ds = new DataOutputStream(httpURLConnection.getOutputStream());
                FileInputStream fStream = new FileInputStream(uploadFile);
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length = -1;
                while ((length = fStream.read(buffer)) != -1) {
                    ds.write(buffer, 0, length);
                }
                //   ds.writeBytes(end);
                /* close streams */
                fStream.close();
                i++;
            }
            //  ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
            /* close streams */
            ds.flush();
            if (httpURLConnection.getResponseCode() >= 300) {
                logger.info("header info {}:{}", httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields());
            }
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                tempLine = null;
                resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (ds != null) {
                try {
                    ds.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return resultBuffer.toString();
        }
    }

    public static String upLoadFileByAuthored(String actionUrl, File uploadFilePaths) {
        logger.info("request URL:{}  @file length {}", actionUrl, uploadFilePaths.length());
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        DataOutputStream ds = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        try {

            URL url = new URL(actionUrl);

            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;


            httpURLConnection.setDoInput(true);

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setUseCaches(false);
//            httpURLConnection.setRequestProperty("x-http-method-override", "PATCH");

            httpURLConnection.setRequestMethod(METHOD_POST);

            //    httpURLConnection.setRequestMethod(METHOD_POST_PATH);
            //  httpURLConnection.setRequestProperty("Host", "116.196.122.126");


            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");

            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
            httpURLConnection.setRequestProperty("Content-Type", "text/plain");
            ds = new DataOutputStream(httpURLConnection.getOutputStream());
            String filename = uploadFilePaths.getPath();
            String fname = filename.substring(filename.lastIndexOf(File.separator) + 1);
            logger.info("fileName {}", filename);
//           StringBuffer sb = new StringBuffer();
//            sb.append(twoHyphens);
//            sb.append(boundary);
//            sb.append(end);
            // 文件参数,photo参数名可以随意修改
//            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + fname
//                    + "\"" + end);
//
//////            // 参数头设置完以后需要两个换行，然后才是参数内容
//            sb.append(end);
            //         sb.append(end);
            //           logger.info("request {}",sb.toString());
            //          ds.writeBytes(sb.toString());
            FileInputStream fStream = new FileInputStream(uploadFilePaths);
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
            while ((length = fStream.read(buffer)) != -1) {
                ds.write(buffer, 0, length);
            }
            /* close streams */
            fStream.close();

            ds.writeBytes(end + twoHyphens + boundary + twoHyphens + end);
            /* close streams */
            ds.flush();
//            logger.info("header info {}:{},@@@!@!{}", httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields());
            if (httpURLConnection.getResponseCode() >= 300) {
//                logger.info("header info {}:{}", httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields());
            }
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                //  httpURLConnection.get
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                tempLine = null;
                resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
            } else {
                inputStream = httpURLConnection.getErrorStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                tempLine = null;
                resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        } finally {
            if (ds != null) {
                try {
                    ds.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return resultBuffer.toString();
        }
    }

    public JSONObject uploadLayerBlock(String actionUrl, File uploadFilePaths) throws IOException {

        logger.info("request URL:{}         @file length {}", actionUrl, uploadFilePaths.length());
        //  String end = "\r\n";
        //  String twoHyphens = "--";
        //  String boundary = "*****";
        JSONObject result = new JSONObject();
        DataOutputStream ds = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        try {

            URL url = new URL(actionUrl);

            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod(METHOD_PUT);
            //  httpURLConnection.setRequestProperty("x-http-method-override", "PATCH");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
            ds = new DataOutputStream(httpURLConnection.getOutputStream());
            String filename = uploadFilePaths.getPath();
            String fname = filename.substring(filename.lastIndexOf(File.separator) + 1);
            FileInputStream fStream = new FileInputStream(uploadFilePaths);
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
            while ((length = fStream.read(buffer)) != -1) {
                ds.write(buffer, 0, length);
            }
            fStream.close();
            ds.flush();
            if (httpURLConnection.getResponseCode() >= 300) {
                logger.info("header info {}:{}", httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields());
            }
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                result.put("success", "true");
//                Map resultInfo = httpURLConnection.getHeaderFields();
//                logger.info("resultInfo: {}",resultInfo);
//                if(resultInfo!=null) {
//                    result.put("Docker-Content-Digest", resultInfo.get("Docker-Content-Digest"));
//                    result.put("Location", resultInfo.get("Location"));
//                }else {
//                    inputStream = httpURLConnection.getErrorStream();
//                    inputStreamReader = new InputStreamReader(inputStream);
//                    reader = new BufferedReader(inputStreamReader);
//                    tempLine = null;
//                    resultBuffer = new StringBuffer();
//                    while ((tempLine = reader.readLine()) != null) {
//                        resultBuffer.append(tempLine);
//                        resultBuffer.append("\n");
//                    }
//                    result = JSONObject.fromObject(resultBuffer.toString());
//                }
            } else {
                result.put("success", "false");
//                inputStream = httpURLConnection.getErrorStream();
//                inputStreamReader = new InputStreamReader(inputStream);
//                reader = new BufferedReader(inputStreamReader);
//                tempLine = null;
//                resultBuffer = new StringBuffer();
//                while ((tempLine = reader.readLine()) != null) {
//                    resultBuffer.append(tempLine);
//                    resultBuffer.append("\n");
//                }
//                result = JSONObject.fromObject(resultBuffer.toString());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        } finally {
            if (ds != null) {
                ds.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (inputStreamReader != null) {

                inputStreamReader.close();
            }
            if (inputStream != null) {

                inputStream.close();
            }

            return result;
        }
    }


    public static String uploadFile(String actionUrl, File uploadFilePaths, String location) {
        logger.info("request URL:{}         @file length {}", actionUrl, uploadFilePaths.length());
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        DataOutputStream ds = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        try {

            URL url = new URL(actionUrl);

            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;


            httpURLConnection.setDoInput(true);

            httpURLConnection.setDoOutput(true);

            httpURLConnection.setUseCaches(false);
            //  httpURLConnection.setRequestProperty("x-http-method-override", "PATCH");

            httpURLConnection.setRequestMethod(METHOD_PUT);

            //httpURLConnection.setRequestMethod(METHOD_POST);

            //    httpURLConnection.setRequestMethod(METHOD_POST_PATH);
            //  httpURLConnection.setRequestProperty("Host", "116.196.122.126");

            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");

            httpURLConnection.setRequestProperty("Charset", "UTF-8");

            // httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
            ds = new DataOutputStream(httpURLConnection.getOutputStream());
            String filename = uploadFilePaths.getPath();
            String fname = filename.substring(filename.lastIndexOf(File.separator) + 1);
            //     logger.info("fileName {}", filename);
            //  StringBuffer sb = new StringBuffer();
            //   sb.append(twoHyphens);
            //   sb.append(boundary);
            //   sb.append(end);
            // 文件参数,photo参数名可以随意修改
            //   sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + fname
            //         + "\"" + end);
            //   sb.append("Content-Type:application/octet-stream");
////            // 参数头设置完以后需要两个换行，然后才是参数内容
            // sb.append(end);
            //   sb.append("Content-Length:" + String.valueOf(uploadFilePaths.length()));
            //   sb.append(end);
            //   sb.append("Location:"+location);
            //    sb.append(end);
            //      sb.append(end);
//            logger.info("request {}",sb.toString());
            //  ds.writeBytes(sb.toString());
            FileInputStream fStream = new FileInputStream(uploadFilePaths);
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
            while ((length = fStream.read(buffer)) != -1) {
                ds.write(buffer, 0, length);
            }
            /* close streams */
            fStream.close();

            ds.writeBytes(end + twoHyphens + boundary + twoHyphens + end);
            /* close streams */
            ds.flush();
//            logger.info("header info {}:{},@@@!@!{}", httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields());
            if (httpURLConnection.getResponseCode() >= 300) {
//                logger.info("header info {}:{}", httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields());
            }
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //  httpURLConnection.get
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                tempLine = null;
                resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
            } else {
                inputStream = httpURLConnection.getErrorStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                tempLine = null;
                resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (ds != null) {
                try {
                    ds.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return resultBuffer.toString();
        }
    }


    //====================================================================
    //============================= 测试调用   ============================
    //====================================================================
    public static void main(String[] args) {

        try {
            HTTPUtils httpUtils = new HTTPUtils();
            //请求地址(我这里测试使用淘宝提供的手机号码信息查询的接口)
            String address = "https://116.196.90.163:5000/v2/";

            //请求参数
            Map<String, String> params = new HashMap<String, String>();
            params.put("tel", "13777777777");//这是该接口需要的参数

            // 调用 get 请求
            String res = httpUtils.get(address, params, null);
            System.out.println(res);//打印返回参数

            res = res.substring(res.indexOf("{"));//截取
            //	JSONObject result = JSONObject.parseObject(res);//转JSON

            //	System.out.println(result.toString());//打印

        } catch (Exception e) {
            // TODO 异常
            e.printStackTrace();
        }

    }

    public static String enodeString(String info) throws UnsupportedEncodingException {
        return  URLEncoder.encode(info,ENCODING).replace(".","27%");
        //return  null;
    }


    private String getErrorMsg() {
        return null;
    }
}