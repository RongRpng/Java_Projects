<%--
  Created by IntelliJ IDEA.
  User: rongrongfu
  Date: 2020/6/20
  Time: 11:17 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"  %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx"%>
<div>
<%--    a:head b:body c:foot--%>
    <tiles:insertAttribute name="a"/>
    <tiles:insertAttribute name="b"/>
    <tiles:insertAttribute name="c"/>
</div>
