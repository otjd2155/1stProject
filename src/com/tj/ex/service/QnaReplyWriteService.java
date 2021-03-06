package com.tj.ex.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.ProductDao;
import com.tj.ex.dao.QnaDao;
import com.tj.ex.dto.AdminDto;
import com.tj.ex.dto.MemberDto;

public class QnaReplyWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("qnaFileUp");
		int maxSize = 1024*1024; // 최대 업로드 사이즈 : 1M
		String[] qFilename = {"",""};
		MultipartRequest mRequest = null;
		try {
			// mRequest 객체 생성한 후 파일 이름 받아오기
			
			mRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			int idx = 0;
			while(params.hasMoreElements()) {
				String param = params.nextElement(); // mPhoto
				qFilename[idx] = mRequest.getFilesystemName(param);
				idx++;
			}
			
			//파라미터값 다 받아오기 -> DB에 넣기
			HttpSession session = request.getSession();
			String mId = null;
			String aId = null;
			if(session.getAttribute("member")!=null) {
				mId = ((MemberDto)session.getAttribute("member")).getmId();
			}else if(session.getAttribute("admin")!=null) {
				aId = ((AdminDto)session.getAttribute("admin")).getaId();
			}
			
			String qtitle = mRequest.getParameter("qtitle");
			
			String qContent = mRequest.getParameter("qContent");
			
			String qFilename1 = qFilename[1];
			String qFilename2 = qFilename[0];
			int qGroup = Integer.parseInt(mRequest.getParameter("qGroup"));
			int qStep = Integer.parseInt(mRequest.getParameter("qStep"));  
			int qIndent = Integer.parseInt(mRequest.getParameter("qIndent"));   
			
			QnaDao qDao = QnaDao.getInstance();
			int result = qDao.qnaReply(mId, aId, qtitle, qContent, qFilename1, qFilename2, qGroup, qStep, qIndent);
			System.out.println();
			if(result == ProductDao.SUCCESS) {
				
				request.setAttribute("reviewresult", "리뷰글등록 성공");
			}else {
				request.setAttribute("errorMsg", "리뷰글등록 실패");
			}
			
		} catch (Exception e) {
			System.out.println("예외 메세지 : " +e.getMessage());
		}
		// 업로드된 파일을 소스폴더로 복사
		for(String files : qFilename) {
				if(files!=null) {
					File serverFile = new File(path+"/"+files);
						InputStream is = null;
						OutputStream os = null;
						try {
							is = new FileInputStream(serverFile);
							os = new FileOutputStream("D:/mega_IT/source/7_jQuery/osh/WebContent/qnaFileUp/"+files);
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
