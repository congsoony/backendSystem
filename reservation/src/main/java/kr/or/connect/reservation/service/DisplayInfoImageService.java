package kr.or.connect.reservation.service;

import static kr.or.connect.reservation.dao.sqls.DisplayInfoImageDaoSqls.BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.sqls.DisplayInfoImageDaoSqls.SELECT_DI_IMAGE;

import java.util.Collections;
import java.util.List;

import kr.or.connect.reservation.dto.DisplayInfoImage;

public interface DisplayInfoImageService {
	List<DisplayInfoImage> getDisplayInfos(int displayId);
}
