package com.southwind.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.southwind.vo.ExcelVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/importExcel")
    public void importExcel(@RequestParam("file")MultipartFile file){
        try {
            EasyExcel.read(file.getInputStream())
                    .head(ExcelVO.class)
                    .sheet()
                    .registerReadListener(new AnalysisEventListener<ExcelVO>() {
                        @Override
                        public void invoke(ExcelVO excelVO, AnalysisContext analysisContext) {
                            System.out.println(excelVO);
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                            System.out.println("======文件解析完成======");
                        }
                    }).doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
