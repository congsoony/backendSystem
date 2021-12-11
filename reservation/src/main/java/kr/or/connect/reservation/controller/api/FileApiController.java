package kr.or.connect.reservation.controller.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.FileInfoTable;
import kr.or.connect.reservation.service.FileInfoService;

@RestController
@RequestMapping("/api/files")
public class FileApiController {
	@Autowired
	private FileInfoService fileInfoService;

	@GetMapping("/{fileId}")
	public void download(@PathVariable int fileId, HttpServletResponse response) {

		String rootPath = fileInfoService.getRootpath();
		
		FileInfoTable fileData = fileInfoService.getFileInfo(fileId);
		// 직접 파일 정보를 변수에 저장해 놨지만, 이 부분이 db에서 읽어왔다고 가정한다.
		String fileName = fileData.getFileName();
		String saveFileName = fileData.getSaveFileName(); // 맥일 경우 "/tmp/connect.png" 로 수정
		String contentType = fileData.getContentType();
		File file = new File(rootPath+File.separator + saveFileName);
		long fileLength = file.length(); // connect.png 파일의 크기와 같아야 합니다. 파일의 크기를 꼭 확인해주세요.
		// 파일의 크기와 같지 않을 경우 프로그램이 멈추지 않고 계속 실행되거나, 잘못된 정보가 다운로드 될 수 있습니다.
		// 위의 파일크기를 구하는 부분은 다음과 같은 방식으로 수정되는 것이 좋습니다. 코드를 간단하게 구현하기 위해서 직접 length를
		// 입력하였습니다.
		// File file = new File(saveFileName);
		// long fileLength = file.length();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (FileInputStream fis = new FileInputStream(rootPath+File.separator + saveFileName);
				OutputStream out = response.getOutputStream();
				BufferedOutputStream bout = new BufferedOutputStream(out);
				BufferedInputStream bis = new BufferedInputStream(fis);) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = bis.read(buffer)) != -1) {
				bout.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}

}
