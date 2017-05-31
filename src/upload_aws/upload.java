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
        //����DiskFileItemFactory
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //����һ���ļ��ϴ�������  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        //���ñ��� 
        upload.setHeaderEncoding("UTF-8");  
      //�����ڴ���ٽ�ֵΪ500K
        factory.setSizeThreshold(1024 * 500);  
      //������500K��ʱ�򣬴浽һ����ʱ�ļ�����  
        File linshi = new File("E:\\linshi");
        factory.setRepository(linshi);  
      //�����ϴ����ļ��ܵĴ�С���ܳ���5M 
        upload.setSizeMax(1024 * 1024 * 5); 
        try {  
            //�õ� FileItem �ļ��� items
            List<FileItem> items = upload.parseRequest(request);  
  
            //���� items:  
            for (FileItem item : items) {  
                // ����һ��һ��ı���, ��ӡ��Ϣ  
                if (item.isFormField()) {  
                    String name = item.getFieldName();  
                    String value = item.getString("utf-8");  
  
                    System.out.println(name + ": " + value);  
                      
                      
                }  
                // �����ļ�������ļ����浽 e:\\files Ŀ¼��.  
                else {  
                    String fileName = item.getName();  
                    long sizeInBytes = item.getSize();  
                    System.out.println(fileName);  
                    System.out.println(sizeInBytes);  
  
                    InputStream in = item.getInputStream();  
                    byte[] buffer = new byte[1024];  
                    int len = 0;  
  
                    fileName = "e:\\files\\" + fileName;//�ļ��ϴ���λ��  
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