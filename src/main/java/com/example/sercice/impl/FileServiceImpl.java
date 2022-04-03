package com.example.sercice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bean.Files;
import com.example.mapper.FileMapper;
import com.example.sercice.FileService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files> implements FileService {
}
