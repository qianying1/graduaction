package com.grad.impl.acfunc;

import javax.jws.WebService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grad.util.BaseUtil;

@Service
@Transactional
@WebService(endpointInterface = "com.grad.service.acfunc.IndexService")
public class IndexServiceImpl extends BaseUtil implements IndexService{
	
}
