<%@page import="com.check.pojo.Structrue"%>
<%@page import="com.check.constant.VarKey"%>
<%@ page import="com.check.pojo.Dict"%>
<%@ page import="java.util.List"%>
<%@	page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>变量字典核查报告</title>
<style type="text/css">
table {
	margin-left: 2%;
	margin-right: 2%;
	width: 96%;
	border-collapse: collapse;
}

td {
	height: 25px;
	padding: 5px;
}

.head {
	background: #CCEEFF;
	padding-top: 20px;
	padding-bottom: 20px;
	margin-bottom: 5px;
	font-size: xx-large;
	font-weight: bolder;
	margin-top: 20px;
}

.neck {
	font-size: x-large;
	padding-right: 5%;
	font-weight: bold;
}

.div-method {
	font-size: x-large;
	padding: 2%;
	font-weight: bold;
}

.foot {
	margin-top: 80px;
	margin-bottom: 80px;
}

textarea {
	width: 100%;
	overflow: auto;
	word-break: break-all;
	border-color: black;
}

.div-comment {
	margin-top: 3%;
	margin-left: 2%;
	margin-right: 2.5%;
	width: 95.5%;
}

.table-title {
	margin-top: 10px;
	margin-bottom: 10px;
	font-size: larger;
}
</style>
</head>
<body>
	<div class="head" align="center">E201716_金仕生物_信力人工生物心脏瓣膜变量字典核查报告
	</div>
	<br>
	<div class="neck" align="right">测试次数：第1次</div>
	<hr>
	<div class="div-method">一、人工核查</div>
	<table border="1">
		<tr>
			<td width="70%" align="center">核查项目</td>
			<td width="30%" align="center">是否通过</td>
		</tr>
		<tr>
			<td>1.变量字典dictId列应为顺序数字，不能跳号、同号、空值。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
		<tr>
			<td>2.核查表结构的表名是否为规范名称，规范名称见附表1。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
		<tr>
			<td>3.核查表结构的表名为字母和数字的组合。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
		<tr>
			<td>4.日期时间组合的变量要求变量后缀为：dy dm dd th tm ts。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
		<tr>
			<td>5.实验室检测项、单位及评价要求变量后缀举例为：rbc rbcu rbce。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
		<tr>
			<td>6.随访中随访状态变量名为isfw。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
		<tr>
			<td>7.备注页备注内容变量名为content。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
		<tr>
			<td>8.PDC课题中每表中备注变量应为bz_表名。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
		<tr>
			<td>9.PDC课题中多次添加的表必须设置唯一标识符变量且顺序号为1，唯一标识符变量名为表名_num，EDC课题不能设置此变量。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
		<tr>
			<td>10.变量字典中范围列中内容要符合规则。</td>
			<td align="center"><select>
					<option value="null"></option>
					<option>通过</option>
					<option>未通过</option>
			</select></td>
		</tr>
	</table>
	<div class="div-comment">
		备注：
		<textarea cols="50" rows="10" style="resize: none"></textarea>
	</div>
	<div class="div-method">二、程序核查</div>
	<div class="table-title" align="center">变量字典表</div>
	<table border="1">
		<tr>
			<td width="5%">dictId</td>
			<td width="10%">变量</td>
			<td width="10%">变量长度</td>

			<td width="10%">所属CRF</td>
			<td width="10%">所属CRF名称</td>

			<td width="10%">变量lable</td>
			<td width="5%">排序号</td>
			<td width="10%">变量值</td>

			<td width="5%">页码</td>
			<td width="5%">访视</td>
			<td width="10%">访视标记</td>

			<td width="10%">范围</td>
		</tr>
		<%
			String[] colorStr = { "#FFFFBB", "#BBFFEE", "#E8CCFF", "#DDDDDD",
					"#FFFF77", "#FFCCCC" };
			List<String> dTagList = (List) request.getAttribute("dTag");
			if (dTagList != null && dTagList.size() > 0) {
				Map<String, Object> checkResult = (Map) request
						.getAttribute("data");
				for (String strD : dTagList) {
					String tempBl = "";
					int colorIndex = 0;
					if (checkResult.containsKey(strD)) {
		%>
		<tr>
			<td colspan="12" bgcolor="<%=colorStr[5]%>"><%=checkResult.get(strD + "_msg")%></td>
		</tr>
		<%
			List<Dict> dicts = (List) checkResult.get(strD);
						for (Dict dict : dicts) {
		%>
		<tr>
			<td><%=dict.getDictId()%></td>
			<%
				if (VarKey.CASE_TABLE_VAR_NAME.equals(strD)
										|| VarKey.CASE_TABLE_START_VAR.equals(strD)
										|| VarKey.CASE_VAR_HAS_KEY.equals(strD)
										|| VarKey.CASE_ALL_VAR_REPEAT.equals(strD)
										|| VarKey.CASE_FLAG_VAR_REPEAT.equals(strD)
										|| VarKey.CASE_VAR_LENGTH_8.equals(strD)
										|| VarKey.CASE_VAR_SPEC_CODE.equals(strD)
										|| VarKey.CASE_VAR_LENGTH_10.equals(strD)) {
									if (VarKey.CASE_ALL_VAR_REPEAT.equals(strD)
											|| VarKey.CASE_FLAG_VAR_REPEAT
													.equals(strD)) {
										String bl = dict.getBianliang();
										if (!tempBl.equals(bl)) {
											colorIndex++;
											if (colorIndex == 4) {
												colorIndex = 0;
											}
										}
										tempBl = bl;
			%>
			<td bgcolor="<%=colorStr[colorIndex]%>"><%=bl%></td>
			<%
				} else {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getBianliang()%></td>
			<%
				}
								} else {
			%>
			<td><%=dict.getBianliang()%></td>
			<%
				}
								if (VarKey.CASE_SAME_VAR_LENGTH.equals(strD)) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getLen()%></td>
			<%
				} else {
			%>
			<td><%=dict.getLen()%></td>
			<%
				}
			%>
			<td><%=dict.getCrf()%></td>
			<td><%=dict.getCrfname()%></td>
			<td><%=dict.getBname()%></td>
			<td><%=dict.getPaixu()%></td>
			<%
				if (VarKey.CASE_B_VAR_RULE.equals(strD)) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getBvalue()%></td>
			<%
				} else {
			%>
			<td><%=dict.getBvalue()%></td>
			<%
				}
			%>

			<td><%=dict.getPage()%></td>
			<td><%=dict.getView()%></td>
			<%
				if (VarKey.CASE_EQUAL_IS_FLAG.equals(strD)
										|| VarKey.CASE_EQUAL_NOT_FLAG.equals(strD)
										|| VarKey.CASE_Flag_SPEC_CODE.equals(strD)) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=dict.getFlag()%></td>
			<%
				} else {
			%>
			<td><%=dict.getFlag()%></td>
			<%
				}
			%>
			<td><%=dict.getRange()%></td>
		</tr>
		<%
			}
					}
				}
			} else {
		%>
		<tr>
			<td colspan="12" align="center"><b>经核查，无错误</b></td>
		</tr>
		<%
			}
		%>

	</table>
	<br>
	<br>
	<div class="table-title" align="center">表结构表</div>
	<table border="1">
		<tr>
			<td width="10%">Id</td>
			<td width="20%">数据表名</td>
			<td width="20%">表明描述</td>
			<td width="10%">表类型</td>

			<td width="10%">表标识</td>
			<td width="20%">表链接</td>
			<td width="10%">表索引</td>
		</tr>
		<%
			List<String> sTagList = (List) request.getAttribute("sTag");
			if (sTagList != null && sTagList.size() > 0) {
				for (String strS : sTagList) {
					Map<String, Object> checkResult = (Map) request
							.getAttribute("data");
					if (checkResult.containsKey(strS)) {
		%>
		<tr>
			<td colspan="7" bgcolor="<%=colorStr[5]%>"><%=checkResult.get(strS + "_msg")%></td>
		</tr>
		<%
			List<Structrue> structrues = (List) checkResult
								.get(strS);
						for (Structrue s : structrues) {
		%>
		<tr>
			<td><%=s.getId()%></td>
			<%
				if (VarKey.CASE_TABLE_HAS_KEY.equals(strS)) {
			%>
			<td bgcolor="<%=colorStr[4]%>"><%=s.getTableName()%></td>
			<%
				} else {
			%>
			<td><%=s.getTableName()%></td>
			<%
				}
			%>
			<td><%=s.getTableDesc()%></td>
			<td><%=s.getTableType()%></td>
			<td><%=s.getTableItem()%></td>
			<td><%=s.getUrl()%></td>
			<td><%=s.getTableOrder()%></td>
		</tr>
		<%
			}
					}
				}
			} else {
		%>
		<tr>
			<td colspan="7" align="center"><b>经核查，无错误</b></td>
		</tr>
		<%
			}
		%>
	</table>
	<table border="1" class="foot">
		<tr>
			<td align="center" width="50%">核查时间：</td>
			<td align="center" width="50%">核查人签字：</td>
		</tr>
	</table>
</body>
</html>