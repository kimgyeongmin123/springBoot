package com.example.demo.domain.service;


import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.mapper.MemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Service
@Slf4j
public class MemoService {
	
	@Autowired
	private MemoMapper mapper;
	
	@Autowired
	DataSource dataSource;
	
	//전체메모가져오기
	@Transactional(rollbackFor = Exception.class)
	public List<MemoDto> getAllMemo(){
		log.info("MemoService's getAllMemo Call! ");
		return  mapper.selectAll(); 
	}
	//메모작성
	 @Transactional(rollbackFor = Exception.class) 
	public void addMemo(MemoDto dto) {
		log.info("MemoService's addMemo Call! ");
		mapper.insert(dto);
	}	
	
	//메모작성 
	 @Transactional(rollbackFor = Exception.class) 
	public void addMemoTx(MemoDto dto)	 {
		log.info("MemoService's addMemoTx Call! ");
		int id=dto.getId();
		mapper.insert(dto);//01 정상INSERT 
		dto.setId(id);	//PK오류 발생예정인 dto
		mapper.insert(dto);//02	PK오류 발생!!		
	}
	
	@Transactional(rollbackFor = Exception.class) 
	public void modifyMemo(MemoDto dto) {
		log.info("MemoService's modifyMemo Call! ");
		mapper.update(dto);
	}
	
	@Transactional(rollbackFor = Exception.class) 
	public void removeMemo(int id) {
		log.info("MemoService's removeMemo Call! ");
		mapper.delete(id);

	}			
	//메모수정
	//메모삭제



}
