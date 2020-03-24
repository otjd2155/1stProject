package com.tj.ex.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.ProductDao;

public class ProducWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("productFileUp");
		int maxSize = 1024*1024; // 최대 업로드 사이즈 : 1M
		String pFilename = "";
		MultipartRequest mRequest = null;
		try {
			// mRequest 객체 생성한 후 파일 이름 받아오기
			
			mRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames(); 
			//while(params.hasMoreElements()) {
				String param = params.nextElement(); // mPhoto
			//}
			pFilename = mRequest.getFilesystemName(param);
			pFilename = (pFilename==null)? "NOIMG.JPG" : pFilename;
			// 파라미터값 다 받아오기 -> DB에 넣기
			
			String pName = mRequest.getParameter("pName");
			String pType = mRequest.getParameter("pType");
			String pOrign = mRequest.getParameter("pOrign");
			String pWeight = mRequest.getParameter("pWeight");
			String pPart = mRequest.getParameter("pPart");
			String pUse = mRequest.getParameter("pUse");
			int pPrice = Integer.parseInt(mRequest.getParameter("pPrice"));
			int pStock = Integer.parseInt(mRequest.getParameter("pStock"));
			ProductDao pDao = ProductDao.getInstance();
			
			int result = pDao.productInsert(pName, pType, pFilename, pOrign, pWeight, pPart, pUse, pPrice, pStock);
			System.out.println();
			if(result == ProductDao.SUCCESS) {
				
				request.setAttribute("productInsert", "상품등록 성공");
			}else {
				request.setAttribute("erroMsg", "상품등록 실패");
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// 업로드된 파일을 소스폴더로 복사
				File serverFile = new File(path+"/"+pFilename);
				if(!pFilename.equals("NOIMG.JPG") && serverFile.exists()) {
					InputStream is = null;
					OutputStream os = null;
					try {
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("D:/mega_IT/source/7_jQuery/osh/WebContent/productFileUp/"+pFilename);
						byte[] bs = new byte[(int)serverFile.length()];
						while(true) {
							int readbyteCnt = is.read(bs);
							if(readbyteCnt == -1) break;
							os.write(bs, 0, readbyteCnt);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}finally {
						try {
							if(os!=null) os.close();
							if(is!=null) is.close();
						} catch (Exception e) {}
					}//try-catch-finally
	
				}//execute()
	}
	
}
