package com.example.multimodule.other.taglib;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperService {
    
    private final SqlSession sqlSession;
    private final SqlSessionFactory sqlSessionFactory;
    
    // 배치작업일 경우
    // 비동기와 함께 작업
    /*public void batchMethod() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            BldgMngMapper mapper = sqlSession.getMapper(BldgMngMapper.class);
            while ((line = reader.readLine()) != null) {
                totalCount++;
                String[] strArr = line.split("\\|");
                FileBldgDTO fileBldgDTO = buildBldgDto(strArr, loginVO);
                fileBldgList.add(fileBldgDTO);
                fileBldgDTO = null;

                // 1000개의 레코드마다 DB에 배치 삽입
                if (fileBldgList.size() == 1000) {
                    try {
                        for (FileBldgDTO fileDTO : fileBldgList) {
                            mapper.regBldg(fileDTO);
                        }
                        sqlSession.flushStatements();
                    } catch (Exception e) {
                        // 데이터가 잘못되었으면 해당 배치에서 하나씩 flush
                        sqlSession.clearCache();
                        for (FileBldgDTO fileDTO : fileBldgList) {
                            try {
                                mapper.regBldg(fileDTO);
                                sqlSession.flushStatements();
                            } catch (Exception err) {
                                failCount++;
                            }
                        }
                    }
                    fileBldgList.clear();
                }
            }
            // 남아 있는 레코드 처리
            if (!fileBldgList.isEmpty()) {
                try {
                    for (FileBldgDTO fileDTO : fileBldgList) {
                        mapper.regBldg(fileDTO);
                    }
                    sqlSession.flushStatements();
                } catch (Exception e) {
                    sqlSession.clearCache();
                    for (FileBldgDTO fileDTO : fileBldgList) {
                        try {
                            mapper.regBldg(fileDTO);
                            sqlSession.flushStatements();
                        } catch (Exception err) {
                            failCount++;
                        }
                    }
                }
                fileBldgList.clear();
            }
            sqlSession.commit();
        }
    }*/
    
    
}
