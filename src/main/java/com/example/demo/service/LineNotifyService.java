package com.example.demo.service;

import java.io.DataOutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;

@Service
public class LineNotifyService {
	
	String token = "";
	
	public void send(String message) throws Exception {
		// 1. 要發送的資料
        // 2. 存取權杖(也稱為:授權 Token)
        
        // 3. Line Notify 的發送位置
        String lineNotifyUrl = "https://notify-api.line.me/api/notify";
        // 4. 發送前設定 -------------------------------------------------------------------------
        // 發送文字
        byte[] postData = ("message=" + message).getBytes("UTF-8");
        // 發送文字 + 縮略圖 + 網路圖片
        // String picurl = "https://image.cache.u-car.com.tw/articleimage_1091049.jpg";
        // byte[] postData = ("message=" + message + "&stickerPackageId=1&stickerId=113&imageThumbnail=" + picurl + "&imageFullsize=" + picurl).getBytes("UTF-8");

        int postDataLength = postData.length;
        URL url = new URL(lineNotifyUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + token); // 一定要加
        conn.setRequestProperty("charset", "utf-8"); // 可以不用加
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 可以不用加
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength)); // 可以不用加
        conn.setUseCaches(false);
        // 5. 訊息發送 ---------------------------------------------------------------------------
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
            wr.flush();
        }
        // 6. 回應資料 ---------------------------------------------------------------------------
        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            int statusCode = conn.getResponseCode();
            System.out.println(statusCode);
        }
	}
	
	public void send(Product product) throws Exception {
        // 1. 要發送的資料
        String message = String.format("商品名稱: %s\n商品特價: %d", product.getName(), product.getPrice());
        // 2. 存取權杖(也稱為:授權 Token)
        
        // 3. Line Notify 的發送位置
        String lineNotifyUrl = "https://notify-api.line.me/api/notify";
        // 4. 發送前設定 -------------------------------------------------------------------------
        // 發送文字
        byte[] postData = ("message=" + message).getBytes("UTF-8");
        // 發送文字 + 縮略圖 + 網路圖片
        // String picurl = "https://image.cache.u-car.com.tw/articleimage_1091049.jpg";
        // byte[] postData = ("message=" + message + "&stickerPackageId=1&stickerId=113&imageThumbnail=" + picurl + "&imageFullsize=" + picurl).getBytes("UTF-8");

        int postDataLength = postData.length;
        URL url = new URL(lineNotifyUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + token); // 一定要加
        conn.setRequestProperty("charset", "utf-8"); // 可以不用加
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 可以不用加
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength)); // 可以不用加
        conn.setUseCaches(false);
        // 5. 訊息發送 ---------------------------------------------------------------------------
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
            wr.flush();
        }
        // 6. 回應資料 ---------------------------------------------------------------------------
        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            int statusCode = conn.getResponseCode();
            System.out.println(statusCode);
        }
    }
	
	public void send(Product product, String filename) throws Exception {
	     // 1. 要發送的資料
		 String message = String.format("商品名稱: %s\n商品特價: %d", product.getName(), product.getPrice());
         // 2. 存取權杖(也稱為:授權 Token)
	     
	     // 3. Line Notify 的發送位置
	     String lineNotifyUrl = "https://notify-api.line.me/api/notify";
	     // 4. 上傳檔案
	     File file = new File("c:/" + filename);
	     // 5. 發送前設定 -------------------------------------------------------------------------
	     // 標頭檔
	     Map<String, String> headers = new HashMap<>();
	     headers.put("Authorization", "Bearer " + token);
	     HttpPostMultipart multipart = new HttpPostMultipart(lineNotifyUrl, "utf-8", headers);
	     // post參數
	     multipart.addFormField("message", message);
	     // 上傳文件
	     multipart.addFilePart("imageFile", file);
	     // 返回信息
	     String response = multipart.finish();
	     System.out.println(response);
	 } 
}
