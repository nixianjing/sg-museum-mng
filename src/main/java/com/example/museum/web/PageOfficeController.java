package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.common.utils.DateUtil;
import com.example.museum.common.utils.FileUtils;
import com.example.museum.common.utils.word.WordJsoupUtils;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.RelicDTO;
import com.example.museum.dto.RelicScanningDTO;
import com.example.museum.dto.RelicWordDTO;
import com.example.museum.po.RelicScanningWordRecordPO;
import com.example.museum.service.RelicScanningService;
import com.example.museum.service.RelicService;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * PageOffice操作word文档
 *
 * @author xianjing.n
 * @date 2019-11-20 02:28
 **/
@Controller
@RequestMapping("word")
public class PageOfficeController extends BaseWebController {

    /**
     * 文物文件删除存放路径
     */
    @Value("${relic.update.path}")
    private String relicUpdatePath;

    @Resource
    private RelicService relicService;

    @Resource
    private RelicScanningService relicScanningService;

    /**
     * 打开word文档
     *
     * @param relicScanningId 文档ID
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/openWord", method = RequestMethod.GET)
    public ModelAndView showWord(Long relicScanningId, HttpServletRequest request, Map<String, Object> map) {
        RelicScanningDTO relicScanningDTO = relicScanningService.getRelicScanningById(relicScanningId);
        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        poCtrl.setServerPage(request.getContextPath() + "/poserver.zz");
        poCtrl.addCustomToolButton("保存", "Save", 1);
        poCtrl.addCustomToolButton("打印", "ShowPrintDlg()", 6);
        poCtrl.addCustomToolButton("全屏切换", "SwitchFullScreen()", 4);
        poCtrl.setSaveFilePage("/word/saveWord?relicScanningId=" + relicScanningId);
        //打开word
        poCtrl.webOpen("file://" + relicScanningDTO.getWordUrl(), OpenModeType.docAdmin, "张三");
        map.put("pageoffice", poCtrl.getHtmlCode("PageOfficeCtrl1"));
        ModelAndView mv = new ModelAndView("Word");
        return mv;
    }

    /**
     * 保存Word文档
     *
     * @param relicScanningId 文档ID
     * @param request
     * @param response
     */
    @RequestMapping("/saveWord")
    public void saveFile(Long relicScanningId, HttpServletRequest request, HttpServletResponse response) {
        AdminUserDTO userToken = this.getUserToken();
        if (Objects.isNull(userToken)) {
            userToken = new AdminUserDTO();
            userToken.setId(new Long(1));
        }
        RelicScanningDTO relicScanningDTO = relicScanningService.getRelicScanningById(relicScanningId);
        RelicDTO relicDTO = relicService.getRelicById(relicScanningDTO.getRelicId());
        /** 复制文件 **/
        String savePath = relicUpdatePath + relicDTO.getRelicName() + "_" + relicScanningDTO.getId() + "/";
        String saveName = DateUtil.dateFormat(new Date(), DateUtil.F_DATE_YYYYMMDDHHMMSS) + "_" + userToken.getName() + "_" + relicScanningDTO.getWordName();
        FileUtils.transferFile(relicScanningDTO.getWordUrl(), savePath, saveName);
        RelicScanningWordRecordPO wordRecordPO = new RelicScanningWordRecordPO();
        wordRecordPO.setWordPath(savePath);
        wordRecordPO.setWordName(saveName);
        wordRecordPO.setWordUrl(savePath + saveName);
        /** 编辑word文档 **/
        FileSaver fs = new FileSaver(request, response);
        fs.saveToFile(relicScanningDTO.getWordPath() + fs.getFileName());
        fs.close();
        /** 解析文件 **/
        RelicWordDTO relicWordDTO = WordJsoupUtils.getRelicWordDTO(relicScanningDTO.getWordPath(), relicScanningDTO.getWordName());
        /** 编辑信息 **/
        relicService.updateRelicScanningWord(relicDTO.getId(), relicScanningDTO.getId(), relicWordDTO, wordRecordPO, userToken.getId());
    }
}
