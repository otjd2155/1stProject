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
import com.tj.ex.dao.ReviewDao;

public class ReviewModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("reviewFileUp");
		int maxSize = 1024*1024; // 최대 업로드 사이즈 : 1M
		String rFilename = "";
		MultipartRequest mRequest = null;
		try {
			// mRequest 객체 생성한 후 파일 이름 받아오기
			
			mRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames(); 
			//while(params.hasMoreElements()) {
				String param = params.nextElement(); // mPhoto
			//}
		  
			
			
			String orFilename = mRequest.getParameter("orFilename");
			rFilename = mRequest.getFilesystemName(param);
			System.out.println(orFilename);
			if(rFilename==null) {
				rFilename = orFilename;
			}
			// 파라미터값 다 받아오기 -> DB에 넣기
			
			int rNo = Integer.parseInt(mRequest.getParameter("rNo"));
			String rTitle = mRequest.getParameter("rTitle");
			String rContent = mRequest.getParameter("rContent");
			
			ReviewDao rDao = ReviewDao.getInstance();
			
			int result = rDao.reviewModify(rTitle, rContent, rFilename, rNo);
			System.out.println();
			if(result == ProductDao.SUCCESS) {
				
				request.setAttribute("reviewresult", "리뷰글 성공");
			}else {
				request.setAttribute("errorMsg", "리뷰글등록 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// 업로드된 파일을 소스폴더로 복사
				File serverFile = new File(path+"/"+rFilename);
				if(rFilename!=null) {
				if(!rFilename.equals("") && serverFile.exists()) {
					InputStream is = null;
					OutputStream os = null;
					try {
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("D:/mega_IT/source/7_jQuery/osh/WebContent/reviewFileUp/"+rFilename);
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
}
