package kr.or.connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.FileInfoDao;
import kr.or.connect.reservation.dto.FileInfoTable;
import kr.or.connect.reservation.service.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService{
	
	@Autowired
	private FileInfoDao fileInfoDao;

	@Override
	@Transactional(readOnly = true)
	public FileInfoTable getFileInfo(int fileId) {
		return fileInfoDao.selectFileInfo(fileId);
	}
	
}