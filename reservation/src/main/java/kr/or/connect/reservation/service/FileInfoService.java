package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.FileInfoTable;

public interface FileInfoService {
	FileInfoTable getFileInfo(int fileId);
	String getRootpath();
}
