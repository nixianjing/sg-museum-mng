package com.example.museum.common.utils.word;

import com.example.museum.common.utils.ObjConverter;
import com.example.museum.dto.RelicWordDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xianjing.n
 * @date 2019-11-20 21:40
 **/
public class WordJsoupUtils {


    public static RelicWordDTO getRelicWordDTO(String wordPath, String wordName) {
        try {
            RelicWordDTO relicWordDTO = new RelicWordDTO();
            Map<String,String> jsoupMap =  getJsoupDTO(wordPath, wordName);
            relicWordDTO = ObjConverter.getMapConvertRelicWordDTO(jsoupMap, relicWordDTO, RelicWordDTO.class);
            return relicWordDTO;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,String> getJsoupDTO(String wordPath, String wordName) {
        Map<String,String> relicMap = RelicMapUtils.getRelicMap();
        Map<String,String> dtoMap = new HashMap<>();
        try {
            String html = WordUtils.getWordContent(wordPath, wordName);
            Document document = Jsoup.parse(html);
            Elements elements = document.getElementsByTag("body").get(0).getElementsByTag("table");
            Element elementTable1 = elements.get(1);
            if (Objects.nonNull(elementTable1)) {
                Elements elementsTr = elementTable1.getElementsByTag("tr");
                if (Objects.nonNull(elementsTr) && elementsTr.size() > 0) {
                    for (int i = 0; i < elementsTr.size(); i++) {
                        Elements elementsTb = elementsTr.get(i).getElementsByTag("td");
                        int tbSize = elementsTb.size();
                        if (Objects.nonNull(elementsTb) && elementsTb.size() > 0) {
                            for (int j = 0; j < elementsTb.size(); j++) {
                                String title = elementsTb.get(j).text().trim();
                                title = title.replaceAll(":","");
                                title = title.replaceAll("：","");
                                String relicMapValue = relicMap.get(title);
                                if (Objects.equals("纬线", title) && j == 0) {
                                    title = "织物密度-纬线";
                                    relicMapValue = relicMap.get(title);
                                    String tbValue =  getElementsTbString(elementsTb, j + 1, tbSize);
                                    System.out.println(title + "  ===  " + tbValue);
                                    dtoMap.put(relicMapValue,tbValue);
                                } else if (Objects.equals("纬线", title) && j == 2) {
                                    title = "纱线颜色-纬线";
                                    relicMapValue = relicMap.get(title);
                                    String tbValue =  getElementsTbString(elementsTb, j + 1, tbSize);
                                    System.out.println(title + "  ===  " + tbValue);
                                    dtoMap.put(relicMapValue,tbValue);
                                } else if (Objects.equals("织物密度", title)) {
                                    String tbValue =  getElementsTbString(elementsTb, j + 2, tbSize);
                                    System.out.println(title + "  ===  " + tbValue);
                                    dtoMap.put(relicMapValue,tbValue);
                                } else if (Objects.equals("纱线颜色", title)) {
                                    String tbValue =  getElementsTbString(elementsTb, j + 2, tbSize);
                                    System.out.println(title + "  ===  " + tbValue);
                                    dtoMap.put(relicMapValue,tbValue);
                                } else {
                                    if(Objects.nonNull(relicMapValue)) {
                                        String tbValue = getElementsTbString(elementsTb, j + 1, tbSize);
                                        System.out.println(title + "  ===  " + tbValue);
                                        dtoMap.put(relicMapValue,tbValue);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Element elementTable2 = elements.get(2);
            if (Objects.nonNull(elementTable2)) {
                Elements elementsTr = elementTable2.getElementsByTag("tr");
                if (Objects.nonNull(elementsTr) && elementsTr.size() > 0) {
                    for (int i = 0; i < elementsTr.size(); i++) {
                        Elements elementsTb = elementsTr.get(i).getElementsByTag("td");
                        int tbSize = elementsTb.size();
                        if (Objects.nonNull(elementsTb) && elementsTb.size() > 0) {
                            for (int j = 0; j < elementsTb.size(); j++) {
                                String title = elementsTb.get(j).text().trim();
                                title = title.replaceAll(":","");
                                title = title.replaceAll("：","");
                                String relicMapValue = relicMap.get(title);
                                if(Objects.nonNull(relicMapValue)) {
                                    String tbValue = getElementsTbString(elementsTb, j + 1, tbSize);
                                    System.out.println(title + "  ===  " + tbValue);
                                    dtoMap.put(relicMapValue,tbValue);
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("完成");
            return dtoMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getElementsTbString(Elements elementsTb, Integer count, Integer tbSize) {
        if (count < tbSize) {
            return elementsTb.get(count).text().trim();
        }
        return "";
    }

    public static void main(String[] args) {
        String wordPath = "/Users/nixianjing/word_data_small/";
        String docName = "20191121115706_毛毡袜.doc";
        String docxName = "2019112017390011122_鸡鸣枕.docx";
        getJsoupDTO(wordPath, docxName);
    }
}
