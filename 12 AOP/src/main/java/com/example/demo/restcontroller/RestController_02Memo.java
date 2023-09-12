package com.example.demo.restcontroller;


import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.service.MemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/memo")
public class RestController_02Memo {
	
	@Autowired
	private MemoService memoService;
	
	//메모확인전체
	@GetMapping(value="/getAll",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MemoDto> getAll() {
		log.info("GET /memo/getAll");
		return memoService.getAllMemo();
	}
	
	//메모확인(단건)
	@GetMapping("/get/{id}")
	public void get(@PathVariable int id) {
		log.info("GET /memo/get... "+id);
	}
	
	//메모쓰기
	@PostMapping("/add")	//http://localhost:8080/app/memo/add
	public void add(@RequestBody MemoDto dto) {
		log.info("POST /memo/add.."+dto);
		memoService.addMemo(dto);
	}
	
	//메모수정
	@PutMapping("/put/{id}/{text}")
	public void put(MemoDto dto) {
		log.info("PUT /memo/put.."+dto);
	}
	
	@PutMapping("/put2")	//http://localhost:8080/app/memo/put2
	public void put2(@RequestBody MemoDto dto) {
		log.info("PUT /memo/put2.."+dto);
		memoService.modifyMemo(dto);
	}
	
	@PatchMapping("/patch/{id}/{text}")
	public void patch(MemoDto dto) {
		log.info("PATCH /memo/patch.."+dto);
	}
	//메모삭제
	@DeleteMapping("/remove/{id}")	//http://localhost:8080/app/memo/remove/{id}
	public void remove(@PathVariable int id) {
		log.info("DELETE /memo/remove.."+id);
		memoService.removeMemo(id);
	}
	
	
}




