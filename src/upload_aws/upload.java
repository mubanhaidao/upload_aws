package upload_aws;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileUploadException;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
  

@WebServlet("/Fileupload")  
public class upload extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");  
        //创建DiskFileItemFactory
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //创建一个文件上传解析器  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        //设置编码 
        upload.setHeaderEncoding("UTF-8");  
      //设置内存的临界值为500K
        factory.setSizeThreshold(1024 * 500);  
      //当超过500K的时候，存到一个临时文件夹中  
        File linshi = new File("E:\\linshi");
        factory.setRepository(linshi);  
      //设置上传的文件总的大小不能超过5M 
        upload.setSizeMax(1024 * 1024 * 5); 
        try {  
            //得到 FileItem 的集合 items
            List<FileItem> items = upload.parseRequest(request);  
  
            //遍历 items:  
            for (FileItem item : items) {  
                // 若是一个一般的表单域, 打印信息  
                if (item.isFormField()) {  
                    String name = item.getFieldName();  
                    String value = item.getString("utf-8");  
  
                    System.out.println(name + ": " + value);  
                      
                      
                }  
                // 若是文件域则把文件保存到 e:\\files 目录下.  
                else {  
                    String fileName = item.getName();  
                    long sizeInBytes = item.getSize();  
                    System.out.println(fileName);  
                    System.out.println(sizeInBytes);  
  
                    InputStream in = item.getInputStream();  
                    byte[] buffer = new byte[1024];  
                    int len = 0;  
  
                    fileName = "e:\\files\\" + fileName;//文件上传的位置  
                    System.out.println(fileName);  
                    OutputStream out = new FileOutputStream(fileName);  
  
                    while ((len = in.read(buffer)) != -1) {  
                        out.write(buffer, 0, len);  
                    }  
  
                    out.close();  
                    in.close();  
                }  
            }  
  
        } 
        catch (FileUploadException e) {  
            e.printStackTrace();  
        }  
  
    }  
} 