package com.its.member.controller;

import com.its.member.dto.FileDTO;
import com.its.member.dto.NewBoardDTO;
import com.its.member.service.FileService;
import com.its.member.service.NewBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final NewBoardService boardFileService;
    private final FileService fileService;
    @GetMapping("/")
    public String saveForm() {
        return "a";
    }


//    @GetMapping("/fileSave-form")
//    public String fileSaveForm() {
//        return "board/fileSave";
//    }
    @PostMapping("/fileSave")
    public String fileSave(@ModelAttribute NewBoardDTO boardFileDTO,
                           MultipartHttpServletRequest mp,
                           Model model) throws IOException {
        NewBoardDTO saveDTO = boardFileService.fileSave(boardFileDTO);
        List<MultipartFile> multipartFileList = mp.getFiles("boardFile");

        List<FileDTO> fileDTOList = new ArrayList<>();
                for (MultipartFile m: multipartFileList) {
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setBoardId(saveDTO.getId());
                    fileDTO.setBoardFile(m);
                    fileDTOList.add(fileService.save(fileDTO));
                }


//        for (int i = 0; i < len1; i++){
//            if (ext.equalsIgnoreCase(b[i])) {
//                for (MultipartFile m: multipartFileList) {
//                    FileDTO fileDTO = new FileDTO();
//                    fileDTO.setBoardId(saveDTO.getId());
//                    fileDTO.setBoardFile(m);
//                    fileDTOList.add(fileService.save1(fileDTO));
//                }
//            }
//        }
//        for (MultipartFile m: multipartFileList) {
//            FileDTO fileDTO = new FileDTO();
//            fileDTO.setBoardId(saveDTO.getId());
//            fileDTO.setBoardFile(m);
//
//            fileDTOList.add(fileService.save(fileDTO));
//        }
        saveDTO.setBoardFileList(fileDTOList);
        model.addAttribute("boardDTO", saveDTO);
//        saveDTO.getBoardDTOList.get(i);
        model.addAttribute("fileDTOList", fileDTOList);
        return "detail";
    }
}
