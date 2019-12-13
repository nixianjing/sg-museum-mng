package com.example.museum.common.utils.word;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * word文档获取HTML文本字符串
 *
 * @author xianjing.n
 * @date 2019-11-20 20:08
 **/
@Slf4j
public class WordUtils {


    public static String getWordContent(String wordPath, String wordName) throws IOException, TransformerException, ParserConfigurationException {
        String content = null;
        /** 文件路径 **/
        String pathFile = wordPath + File.separator + wordName;
        File f = new File(pathFile);
        if (!f.exists()) {
            return content;
        }
        if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {
            content = wordDocxContent(pathFile);
        } else {
            content = wordDocContent(pathFile);
        }
        return content;
    }

    /**
     * word2003 .doc
     *
     * @param pathFile
     * @return
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    private static String wordDocContent(String pathFile)
            throws IOException, TransformerException, ParserConfigurationException {
        InputStream input = new FileInputStream(new File(pathFile));
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        // 解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        // 也可以使用字符数组流获取解析的内容
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(baos);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        String content = new String(baos.toByteArray());
        baos.close();
        return content;
    }

    /**
     * word2007 .docx
     *
     * @param pathFile
     * @return
     * @throws IOException
     */
    private static String wordDocxContent(String pathFile) throws IOException {
        File f = new File(pathFile);
        //读取文档内容
        InputStream in = new FileInputStream(f);
        XWPFDocument document = new XWPFDocument(in);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(document, baos, null);
        String content = baos.toString();
        baos.close();
        return content;
    }

}
