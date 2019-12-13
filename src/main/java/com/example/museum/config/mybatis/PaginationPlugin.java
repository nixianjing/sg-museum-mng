package com.example.museum.config.mybatis;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * @author xianjing.n
 * @date 2019-10-14 19:23
 **/
public class PaginationPlugin extends PluginAdapter {

    public PaginationPlugin() {
    }

    @Override
    public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        List<Element> elements = element.getElements();

        for (int i = 0; i < elements.size(); ++i) {
            String s = (element.getElements().get(i)).getFormattedContent(0);
            if (s.indexOf("count(*)") != -1) {
                s = s.replace("count(*)", "count(id)");
                element.getElements().set(i, new TextElement(s));
            }
        }

        return super.sqlMapCountByExampleElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addLimit(topLevelClass, introspectedTable);
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
        interfaze.addAnnotation("@Repository //auto-generated");
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", "limit != null"));
        isNotNullElement.addElement(new TextElement("${limit}"));
        element.addElement(isNotNullElement);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", "limit != null"));
        isNotNullElement.addElement(new TextElement("${limit}"));
        element.addElement(isNotNullElement);
        return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.replaceInsertcreate_timeAndmodify_time(element);
        return super.sqlMapInsertElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.replaceUpdatemodify_time(element);
        return super.sqlMapUpdateByExampleSelectiveElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.replaceUpdatemodify_time(element);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.replaceUpdatemodify_time(element);
        return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.replaceUpdatemodify_time(element);
        return super.sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.replaceUpdatemodify_time(element);
        return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.replaceUpdatemodify_time(element);
        return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
    }

    private void replaceUpdatemodify_time(XmlElement element) {
        List<Element> elements = element.getElements();

        for (int i = 0; i < elements.size(); ++i) {
            String s = (element.getElements().get(i)).getFormattedContent(0);
            if (s.indexOf("modify_time = #{modify_time,jdbcType=TIMESTAMP},") != -1) {
                s = s.replace("modify_time = #{modify_time,jdbcType=TIMESTAMP},", "modify_time = NOW(),");
                element.getElements().set(i, new TextElement(s));
            }

            if (s.indexOf("modify_time = #{record.modify_time,jdbcType=TIMESTAMP},") != -1) {
                s = s.replace("modify_time = #{record.modify_time,jdbcType=TIMESTAMP},", "modify_time = NOW(),");
                element.getElements().set(i, new TextElement(s));
            }

            if (s.indexOf("#{modify_time,jdbcType=TIMESTAMP}") != -1) {
                s = s.replace("#{modify_time,jdbcType=TIMESTAMP}", "NOW()");
                element.getElements().set(i, new TextElement(s));
            }
        }

    }

    private void replaceInsertcreate_timeAndmodify_time(XmlElement element) {
        List<Element> elements = element.getElements();

        for (int i = 0; i < elements.size(); ++i) {
            String s = ((Element) element.getElements().get(i)).getFormattedContent(0);
            if (s.indexOf("#{create_time,jdbcType=TIMESTAMP}") != -1) {
                s = s.replace("#{create_time,jdbcType=TIMESTAMP}", "NOW()");
                element.getElements().set(i, new TextElement(s));
            }

            if (s.indexOf("modify_time = #{modify_time,jdbcType=TIMESTAMP},") != -1) {
                s = s.replace("modify_time = #{modify_time,jdbcType=TIMESTAMP},", "modify_time = NOW(),");
                element.getElements().set(i, new TextElement(s));
            }

            if (s.indexOf("modify_time = #{record.modify_time,jdbcType=TIMESTAMP},") != -1) {
                s = s.replace("modify_time = #{record.modify_time,jdbcType=TIMESTAMP},", "modify_time = NOW(),");
                element.getElements().set(i, new TextElement(s));
            }

            if (s.indexOf("#{modify_time,jdbcType=TIMESTAMP}") != -1) {
                s = s.replace("#{modify_time,jdbcType=TIMESTAMP}", "NOW()");
                element.getElements().set(i, new TextElement(s));
            }
        }

    }

    private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        CommentGenerator commentGenerator = this.context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(FullyQualifiedJavaType.getStringInstance());
        field.setName("limit");
        field.setInitializationString("null");
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("setLimit");
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "start"));
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "size"));
        method.setReturnType(topLevelClass.getType());
        method.addBodyLine("if (start < 0 || size < 0) throw new RuntimeException(\"no start nor size should be less than 0!\");");
        method.addBodyLine("this.limit = \"limit \" + start + \", \" + size;");
        method.addBodyLine("return this;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.setName("getLimit");
        method.addBodyLine("return limit;");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return super.modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable);
    }
}